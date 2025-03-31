/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin.client.gui;

import com.mclegoman.dtaf2025.client.gui.PanoramaDataloader;
import com.mclegoman.luminance.common.util.Couple;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RotatingCubeMapRenderer.class)
public abstract class RotatingCubeMapRendererMixin {
	@Mutable @Shadow @Final public static Identifier OVERLAY_TEXTURE;
	@Inject(at = @At("HEAD"), method = "render")
	private void dtaf2025$render(DrawContext context, int width, int height, float alpha, float tickDelta, CallbackInfo ci) {
		Couple<Identifier, Boolean> panoramaData = PanoramaDataloader.getPanorama();
		OVERLAY_TEXTURE = panoramaData.getSecond() ? Identifier.of(panoramaData.getFirst().getNamespace(), panoramaData.getFirst().getPath() + "/panorama_overlay.png") : Identifier.ofVanilla("textures/gui/title/background/panorama_overlay.png");
	}
}