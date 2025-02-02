/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin.common;

import com.mclegoman.dtaf2025.common.entity.data.sanic.Sanic;
import com.mclegoman.dtaf2025.common.entity.data.sanic.SanicComponent;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements Sanic {
	@Unique
	private SanicComponent sanicComponent;
	@Unique
	private static final TrackedData<Boolean> sanic;
	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}
	@Inject(method = "<init>", at = @At("TAIL"))
	private void dtaf2025$addSanicComponent(World world, BlockPos pos, float yaw, GameProfile gameProfile, CallbackInfo ci) {
		this.sanicComponent = new SanicComponent(this.dataTracker, sanic);
	}
	@Inject(method = "initDataTracker", at = @At("TAIL"))
	private void dtaf2025$initDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
		builder.add(sanic, false);
	}
	@Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
	private void dtaf2025$writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
		this.sanicComponent.writeNbt(nbt);
	}
	@Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
	private void dtaf2025$readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
		this.sanicComponent.readNbt(nbt);
	}
	@Inject(method = "getMovementSpeed", at = @At("RETURN"), cancellable = true)
	private void dtaf2025$getMovementSpeed(CallbackInfoReturnable<Float> cir) {
		if (this.dtaf2025$isSanic()) cir.setReturnValue(cir.getReturnValue() * 2.0F);
	}
	public boolean dtaf2025$isSanic() {
		return this.sanicComponent.dtaf2025_getSanic();
	}
	public void dtaf2025$setSanic(boolean value) {
		if (dtaf2025$isSanic() != value) this.sanicComponent.dtaf2025_setSanic(value);
	}
	static {
		sanic = DataTracker.registerData(PlayerEntityMixin.class, TrackedDataHandlerRegistry.BOOLEAN);
	}
}
