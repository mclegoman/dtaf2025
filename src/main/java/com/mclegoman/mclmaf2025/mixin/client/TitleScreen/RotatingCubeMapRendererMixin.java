/*
    mclmaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclmaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclmaf2025.mixin.client.TitleScreen;

import com.mclegoman.mclmaf2025.common.data.Data;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RotatingCubeMapRenderer.class)
public abstract class RotatingCubeMapRendererMixin {
	@Shadow @Mutable
	private static Identifier OVERLAY_TEXTURE;
	@Inject(at = @At("HEAD"), method = "render")
	private void mclmaf2025$render(DrawContext context, int width, int height, float alpha, float tickDelta, CallbackInfo ci) {
		OVERLAY_TEXTURE = new Identifier(Data.version.getID(), "textures/gui/title/background/panorama_overlay.png");
	}
}