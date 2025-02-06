/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin.common.entity;

import com.mclegoman.dtaf2025.common.easter_egg.EasterEggRegistry;
import com.mclegoman.dtaf2025.common.enchantment.EnchantmentRegistry;
import com.mclegoman.dtaf2025.common.entity.damage.DamageRegistry;
import com.mclegoman.dtaf2025.common.entity.data.air.Air;
import com.mclegoman.dtaf2025.common.entity.data.air.AirComponent;
import com.mclegoman.dtaf2025.common.util.Tags;
import com.mclegoman.dtaf2025.common.world.dimension.DimensionRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.Set;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Air {
	@Shadow protected abstract int getNextAirOnLand(int air);
	@Shadow @Nullable public abstract EntityAttributeInstance getAttributeInstance(RegistryEntry<EntityAttribute> attribute);
	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}
	@Inject(method = "getGravity", at = @At("RETURN"), cancellable = true)
	private void dtaf2025$getGravity(CallbackInfoReturnable<Double> cir) {
		double gravity = cir.getReturnValue();
		if (this.isPlayer() && EasterEggRegistry.getSanic()) gravity *= 2.0F;
		if (DimensionRegistry.spaceIds.contains(this.getWorld().getDimension().effects())) {
			if (!EnchantmentRegistry.hasHeavyFooted((LivingEntity)(Object) this)) gravity *= 0.16F;
		} else {
			if (EnchantmentRegistry.hasHeavyFooted((LivingEntity)(Object) this)) gravity *= 1.94F;
		}
		cir.setReturnValue(gravity);
	}
	@Inject(method = "getAttributeValue", at = @At("RETURN"), cancellable = true)
	private void dtaf2025$getAttributeValue(RegistryEntry<EntityAttribute> attribute, CallbackInfoReturnable<Double> cir) {
			if (this.dtaf2025$getAttributeId(attribute).isPresent()) {
				Identifier attributeId = this.dtaf2025$getAttributeId(attribute).get();
				if (this.dtaf2025$getAttributeId(EntityAttributes.SAFE_FALL_DISTANCE).isPresent()) {
					if (attributeId.equals(this.dtaf2025$getAttributeId(EntityAttributes.SAFE_FALL_DISTANCE).get())) {
						if (DimensionRegistry.spaceIds.contains(this.getWorld().getDimension().effects())) {
							if (!EnchantmentRegistry.hasHeavyFooted((LivingEntity)(Object) this)) cir.setReturnValue(cir.getReturnValue() * 1.94F);
						}
					}
				}
				if (this.dtaf2025$getAttributeId(EntityAttributes.FALL_DAMAGE_MULTIPLIER).isPresent()) {
					if (attributeId.equals(this.dtaf2025$getAttributeId(EntityAttributes.FALL_DAMAGE_MULTIPLIER).get())) {
						if (DimensionRegistry.spaceIds.contains(this.getWorld().getDimension().effects())) {
							if (!EnchantmentRegistry.hasHeavyFooted((LivingEntity)(Object) this)) cir.setReturnValue(cir.getReturnValue() * 0.16F);
						}
					}
				}
			}
	}

	@Inject(method = "getJumpVelocity(F)F", at = @At("RETURN"), cancellable = true)
	private void dtaf2025$getJumpVelocity(CallbackInfoReturnable<Float> cir) {
		if (this.isPlayer() && EasterEggRegistry.getSanic()) cir.setReturnValue(cir.getReturnValue() * 2.0F);
	}
	@Inject(method = "baseTick", at = @At("RETURN"))
	private void dtaf2025$baseTick(CallbackInfo ci) {
		if (this.isAlive()) {
			if (!this.getType().isIn(Tags.EntityType.canBreatheInSpace)) {
				boolean isInAir = true;
				boolean spaceAir = this.getWorld().getBlockState(BlockPos.ofFloored(this.getX(), this.getEyeY(), this.getZ())).isIn(Tags.Block.spaceAir);
				// If the player is standing next to space air, pretend they are in it (This allows the player to have levers and the like, without the enchantment).
				if (!spaceAir) for (Direction direction : Direction.values()) {
					if (this.getWorld().getBlockState(BlockPos.ofFloored(this.getX(), this.getEyeY(), this.getZ()).offset(direction)).isIn(Tags.Block.spaceAir)) {
						spaceAir = true;
						break;
					}
				}
				if (!EnchantmentRegistry.hasSpaceBreathing((LivingEntity)(Object) this) && !spaceAir) {
					if (this.getWorld().getBiome(BlockPos.ofFloored(this.getX(), this.getEyeY(), this.getZ())).isIn(Tags.WorldGen.Biome.noOxygen)) {
						if (!this.isInvulnerable()) {
							this.dtaf2025$setAir(this.dtaf2025$getNextAirSpace(this.dtaf2025$getAir()));
							if (this.dtaf2025$getAir() <= -20) {
								this.dtaf2025$setAir(0);
								if (getWorld() instanceof ServerWorld serverWorld) {
									float amount = switch (serverWorld.getDifficulty()) {
										case PEACEFUL, EASY -> 4.0F;
										case NORMAL -> 8.0F;
										case HARD -> 16.0F;
									};
									this.damage(serverWorld, DamageRegistry.spaceSuffocation.getSource(serverWorld), amount);
								}
							}
						}
						isInAir = false;
					}
				}
				if (isInAir && this.dtaf2025$getAir() < this.getMaxAir()) this.dtaf2025$setAir(this.getNextAirOnLand(this.dtaf2025$getAir()));
			}
			if (this.getServer() != null) {
				if (this.getWorld().getDimension().effects().equals(Identifier.ofVanilla("overworld"))) {
					BlockPos pos = this.getBlockPos();
					if (pos.getY() > this.getWorld().getDimension().height() + 80) {
						ServerWorld spaceStation = this.getServer().getWorld(DimensionRegistry.spaceStation.getWorld());
						if (spaceStation != null) this.teleportTo(new TeleportTarget(spaceStation, new Vec3d(pos.getX(), spaceStation.getBottomY(), pos.getZ()), Vec3d.ZERO, this.getYaw(), this.getPitch(), PositionFlag.combine(PositionFlag.DELTA, Set.of(PositionFlag.X_ROT)), TeleportTarget.NO_OP));
					}
				} else if (this.getWorld().getDimension().effects().equals(DimensionRegistry.spaceStation.getId())) {
					BlockPos pos = this.getBlockPos();
					if (pos.getY() < this.getWorld().getDimension().minY() - 16) {
						ServerWorld overworld = this.getServer().getWorld(World.OVERWORLD);
						if (overworld != null) this.teleportTo(new TeleportTarget(overworld, new Vec3d(pos.getX(), overworld.getHeight() + 64, pos.getZ()), Vec3d.ZERO, this.getYaw(), this.getPitch(), PositionFlag.combine(PositionFlag.DELTA, Set.of(PositionFlag.X_ROT)), TeleportTarget.NO_OP));
					} else if (pos.getY() > this.getWorld().getDimension().height() + 80) {
						ServerWorld moon = this.getServer().getWorld(DimensionRegistry.theMoon.getWorld());
						if (moon != null) this.teleportTo(new TeleportTarget(moon, new Vec3d(pos.getX(), moon.getHeight() + 64, pos.getZ()), Vec3d.ZERO, this.getYaw(), this.getPitch(), PositionFlag.combine(PositionFlag.DELTA, Set.of(PositionFlag.X_ROT)), TeleportTarget.NO_OP));
					}
				} else if (this.getWorld().getDimension().effects().equals(DimensionRegistry.theMoon.getId())) {
					BlockPos pos = this.getBlockPos();
					if (pos.getY() > this.getWorld().getDimension().height() + 80) {
						ServerWorld spaceStation = this.getServer().getWorld(DimensionRegistry.spaceStation.getWorld());
						if (spaceStation != null) this.teleportTo(new TeleportTarget(spaceStation, new Vec3d(pos.getX(), spaceStation.getHeight() + 64, pos.getZ()), Vec3d.ZERO, this.getYaw(), this.getPitch(), PositionFlag.combine(PositionFlag.DELTA, Set.of(PositionFlag.X_ROT)), TeleportTarget.NO_OP));
					}
				}
			}
		}
	}
	@Unique
	protected int dtaf2025$getNextAirSpace(int air) {
		int amount = switch (this.getWorld().getDifficulty()) {
			case PEACEFUL, EASY -> 5;
			case NORMAL -> 10;
			case HARD -> 20;
		};
		EntityAttributeInstance oxygenBonusAttribute = this.getAttributeInstance(EntityAttributes.OXYGEN_BONUS);
		double oxygenBonus = (oxygenBonusAttribute != null) ? oxygenBonusAttribute.getValue() : 0.0;
		return oxygenBonus > 0.0 && (this.random.nextDouble() >= 1.0 / (oxygenBonus + 1.0)) ? air - amount : air - (amount * 2);
	}
	@Unique
	private AirComponent airComponent;
	@Unique
	private static final TrackedData<Integer> air;
	@Inject(method = "<init>", at = @At("TAIL"))
	private void dtaf2025$addAirComponent(EntityType<?> entityType, World world, CallbackInfo ci) {
		this.airComponent = new AirComponent(this.dataTracker, air);
	}
	@Inject(method = "initDataTracker", at = @At("TAIL"))
	private void dtaf2025$initDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
		builder.add(air, getMaxAir());
	}
	@Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
	private void dtaf2025$writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
		this.airComponent.writeNbt(nbt);
	}
	@Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
	private void dtaf2025$readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
		this.airComponent.readNbt(nbt);
	}
	@Unique
	private Optional<Identifier> dtaf2025$getAttributeId(RegistryEntry<EntityAttribute> attribute) {
		return attribute.getKey().map(RegistryKey::getValue);
	}
	public int dtaf2025$getAir() {
		return this.airComponent.dtaf2025_getAir();
	}
	public void dtaf2025$setAir(int value) {
		this.airComponent.dtaf2025_setAir(Math.clamp(value, -20, this.getMaxAir()));
	}
	static {
		air = DataTracker.registerData(LivingEntityMixin.class, TrackedDataHandlerRegistry.INTEGER);
	}
}