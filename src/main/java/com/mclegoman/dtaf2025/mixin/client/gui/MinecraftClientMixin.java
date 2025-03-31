/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin.client.gui;

import com.mclegoman.dtaf2025.client.gui.PanoramaDataloader;
import com.mclegoman.dtaf2025.common.sound.SoundRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.sound.MusicInstance;
import net.minecraft.sound.MusicType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
	@Inject(method = "getMusicInstance", at = @At("RETURN"), cancellable = true)
	private void dtaf2025$replaceMenuMusic(CallbackInfoReturnable<MusicInstance> cir) {
		if (cir.getReturnValue().music() == MusicType.MENU) cir.setReturnValue(new MusicInstance(SoundRegistry.musicMenu));
	}
	@Inject(method = "onDisconnected", at = @At("HEAD"))
	private void dtaf2025$onDisconnected(CallbackInfo ci) {
		PanoramaDataloader.randomizePanorama();
	}
}
