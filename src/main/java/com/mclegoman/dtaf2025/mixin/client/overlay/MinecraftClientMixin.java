/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin.client.overlay;

import com.mclegoman.dtaf2025.client.data.ClientData;
import com.mclegoman.dtaf2025.common.sound.SoundRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
	@Shadow public abstract SoundManager getSoundManager();
	@Inject(method = "onInitFinished", at = @At("RETURN"))
	private void dtaf2025$playStartupSound(MinecraftClient.LoadingContext loadingContext, CallbackInfoReturnable<Runnable> cir) {
		if (!ClientData.hasPlayedStartupSound) {
			ClientData.hasPlayedStartupSound = true;
			getSoundManager().play(PositionedSoundInstance.master(SoundRegistry.startup, 1.0F));
		}
	}
}