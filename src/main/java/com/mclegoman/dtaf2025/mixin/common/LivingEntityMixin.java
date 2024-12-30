/*
    dtaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin.common;

import com.mclegoman.dtaf2025.common.entity.data.Sanic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
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
	@Inject(method = "getJumpVelocity", at = @At("RETURN"), cancellable = true)
	private void dtaf2025$getJumpVelocity(CallbackInfoReturnable<Float> cir) {
		if (this.isPlayer()) {
			PlayerEntity player = this.getWorld().getPlayerByUuid(this.getUuid());
			if (player != null) {
				if (((Sanic)player).dtaf2025$isSanic()) cir.setReturnValue(cir.getReturnValue() * 2.0F);
			}
		}
	}
}
