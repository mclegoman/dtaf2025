/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin.client;

import com.mclegoman.dtaf2025.common.entity.data.air.Air;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InGameHud.class)
public abstract class GameGuiMixin {
	@Redirect(method = "renderAirBubbles", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getAir()I"))
	private int dtaf2025$updateAir(PlayerEntity playerEntity) {
		return Math.min(playerEntity.getAir(), ((Air)playerEntity).dtaf2025$getAir());
	}
}