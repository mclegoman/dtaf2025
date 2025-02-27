/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin.client.overlay;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.resource.ResourceReload;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;
import java.util.function.IntSupplier;

@Mixin(SplashOverlay.class)
public abstract class SplashOverlayMixin {
	@Mutable @Shadow @Final private static IntSupplier BRAND_ARGB;
	@Shadow @Final private static int MONOCHROME_BLACK;
	@Inject(method = "<init>", at = @At("RETURN"))
	private void dtaf2025$replaceColor(MinecraftClient client, ResourceReload monitor, Consumer exceptionHandler, boolean reloading, CallbackInfo ci) {
		BRAND_ARGB = () -> (Boolean) MinecraftClient.getInstance().options.getMonochromeLogo().getValue() ? MONOCHROME_BLACK : ColorHelper.getArgb(255, 30, 30, 31);
	}
}