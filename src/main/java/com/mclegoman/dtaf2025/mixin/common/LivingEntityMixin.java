/*
    dtaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin.common;

import com.mclegoman.dtaf2025.common.enchantment.EnchantmentRegistry;
import com.mclegoman.dtaf2025.common.entity.damage.DamageRegistry;
import com.mclegoman.dtaf2025.common.entity.data.air.Air;
import com.mclegoman.dtaf2025.common.entity.data.air.AirComponent;
import com.mclegoman.dtaf2025.common.entity.data.sanic.Sanic;
import com.mclegoman.dtaf2025.common.world.biome.BiomeTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
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

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Air {
	@Shadow protected abstract int getNextAirOnLand(int air);

	@Shadow @Nullable public abstract EntityAttributeInstance getAttributeInstance(RegistryEntry<EntityAttribute> attribute);

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}
	@Inject(method = "getGravity", at = @At("RETURN"), cancellable = true)
	private void dtaf2025$getGravity(CallbackInfoReturnable<Double> cir) {
		if (this.isPlayer()) {
			PlayerEntity player = this.getWorld().getPlayerByUuid(this.getUuid());
			if (player != null) {
				if (((Sanic)player).dtaf2025$isSanic()) cir.setReturnValue(cir.getReturnValue() * 2.0F);
			}
		}
	}
	@Inject(method = "getJumpVelocity(F)F", at = @At("RETURN"), cancellable = true)
	private void dtaf2025$getJumpVelocity(CallbackInfoReturnable<Float> cir) {
		if (this.isPlayer()) {
			PlayerEntity player = this.getWorld().getPlayerByUuid(this.getUuid());
			if (player != null) {
				if (((Sanic)player).dtaf2025$isSanic()) cir.setReturnValue(cir.getReturnValue() * 2.0F);
			}
		}
	}
	@Inject(method = "baseTick", at = @At("RETURN"))
	private void dtaf2025$baseTick(CallbackInfo ci) {
		if (this.isAlive()) {
			boolean isInAir = true;
			if (!EnchantmentRegistry.hasSpaceBreathing((LivingEntity)(Object) this)) {
				if (this.getWorld().getBiome(BlockPos.ofFloored(this.getX(), this.getEyeY(), this.getZ())).isIn(BiomeTags.noOxygen)) {
					if (!this.isInvulnerable()) {
						this.dtaf2025$setAir(this.dtaf2025$getNextAirSpace(this.dtaf2025$getAir()));
						if (this.dtaf2025$getAir() <= -20) {
							this.dtaf2025$setAir(0);
							this.damage(DamageRegistry.spaceSuffocation.getSource(this.getWorld()), 8.0F);
						}
					}
					isInAir = false;
				}
			}
			if (isInAir && this.dtaf2025$getAir() < this.getMaxAir()) this.dtaf2025$setAir(this.getNextAirOnLand(this.dtaf2025$getAir()));
		}
	}
	@Unique
	protected int dtaf2025$getNextAirSpace(int air) {
		EntityAttributeInstance oxygenBonusAttribute = this.getAttributeInstance(EntityAttributes.GENERIC_OXYGEN_BONUS);
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