/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin.common;

import com.mclegoman.dtaf2025.common.enchantment.EnchantmentRegistry;
import com.mclegoman.dtaf2025.common.entity.damage.DamageRegistry;
import com.mclegoman.dtaf2025.common.entity.data.air.Air;
import com.mclegoman.dtaf2025.common.entity.data.air.AirComponent;
import com.mclegoman.dtaf2025.common.network.Packets;
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
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
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
		if (this.isPlayer() && Packets.Values.isSanic) gravity *= 2.0F;
		if (this.getWorld().getDimension().effects().equals(DimensionRegistry.theMoon.getId())) {
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
						if (this.getWorld().getDimension().effects().equals(DimensionRegistry.theMoon.getId())) {
							if (!EnchantmentRegistry.hasHeavyFooted((LivingEntity)(Object) this)) cir.setReturnValue(cir.getReturnValue() * 1.94F);
						}
					}
				}
				if (this.dtaf2025$getAttributeId(EntityAttributes.FALL_DAMAGE_MULTIPLIER).isPresent()) {
					if (attributeId.equals(this.dtaf2025$getAttributeId(EntityAttributes.FALL_DAMAGE_MULTIPLIER).get())) {
						if (this.getWorld().getDimension().effects().equals(DimensionRegistry.theMoon.getId())) {
							if (!EnchantmentRegistry.hasHeavyFooted((LivingEntity)(Object) this)) cir.setReturnValue(cir.getReturnValue() * 0.16F);
						}
					}
				}
			}
	}

	@Inject(method = "getJumpVelocity(F)F", at = @At("RETURN"), cancellable = true)
	private void dtaf2025$getJumpVelocity(CallbackInfoReturnable<Float> cir) {
		if (this.isPlayer() && Packets.Values.isSanic) cir.setReturnValue(cir.getReturnValue() * 2.0F);
	}
	@Inject(method = "baseTick", at = @At("RETURN"))
	private void dtaf2025$baseTick(CallbackInfo ci) {
		if (this.isAlive()) {
			if (!this.getType().isIn(Tags.EntityType.canBreatheInSpace)) {
				boolean isInAir = true;
				if (!EnchantmentRegistry.hasSpaceBreathing((LivingEntity)(Object) this) && !this.getWorld().getBlockState(BlockPos.ofFloored(this.getX(), this.getEyeY(), this.getZ())).isIn(Tags.Block.spaceAir)) {
					if (this.getWorld().getBiome(BlockPos.ofFloored(this.getX(), this.getEyeY(), this.getZ())).isIn(Tags.WorldGen.Biome.noOxygen)) {
						if (!this.isInvulnerable()) {
							this.dtaf2025$setAir(this.dtaf2025$getNextAirSpace(this.dtaf2025$getAir()));
							if (this.dtaf2025$getAir() <= -20) {
								this.dtaf2025$setAir(0);
								if (getWorld() instanceof ServerWorld serverWorld) this.damage(serverWorld, DamageRegistry.spaceSuffocation.getSource(serverWorld), 8.0F);
							}
						}
						isInAir = false;
					}
				}
				if (isInAir && this.dtaf2025$getAir() < this.getMaxAir()) this.dtaf2025$setAir(this.getNextAirOnLand(this.dtaf2025$getAir()));
			}
		}
	}
	@Unique
	protected int dtaf2025$getNextAirSpace(int air) {
		EntityAttributeInstance oxygenBonusAttribute = this.getAttributeInstance(EntityAttributes.OXYGEN_BONUS);
		double oxygenBonus = (oxygenBonusAttribute != null) ? oxygenBonusAttribute.getValue() : 0.0;
		return oxygenBonus > 0.0 && (this.random.nextDouble() >= 1.0 / (oxygenBonus + 1.0)) ? air - 10 : air - 20;
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