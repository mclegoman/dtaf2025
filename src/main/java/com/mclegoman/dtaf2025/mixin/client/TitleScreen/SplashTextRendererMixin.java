/*
    dtaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin.client.TitleScreen;

import com.mclegoman.dtaf2025.client.gui.SplashesDataloader;
import com.mclegoman.dtaf2025.common.util.Compatibility;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SplashTextRenderer.class)
public abstract class SplashTextRendererMixin {
	@Inject(at = @At("HEAD"), method = "render", cancellable = true)
	private void dtaf2025$render(DrawContext context, int screenWidth, TextRenderer textRenderer, int alpha, CallbackInfo ci) {
		if (SplashesDataloader.getSplashText() != null) {
			context.getMatrices().push();
			context.getMatrices().translate((float)screenWidth / 2.0F + 123.0F, 69.0F, 0.0F);
			context.getMatrices().multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-20.0F));
			float f = 1.8F - MathHelper.abs(MathHelper.sin((float)(Util.getMeasuringTimeMs() % 1000L) / 1000.0F * 6.2831855F) * 0.1F);
			f = f * 100.0F / (float)(textRenderer.getWidth(SplashesDataloader.getSplashText().getSecond() ? Text.translatable(SplashesDataloader.getSplashText().getFirst()) : Text.literal(SplashesDataloader.getSplashText().getFirst())) + 32);
			context.getMatrices().scale(f, f, f);
			context.drawCenteredTextWithShadow(textRenderer, SplashesDataloader.getSplashText().getSecond() ? Text.translatable(SplashesDataloader.getSplashText().getFirst()) : Text.literal(SplashesDataloader.getSplashText().getFirst()), 0, Compatibility.getModsButtonStyle().equals("CLASSIC") ? -20 : -8, 16776960 | alpha);
			context.getMatrices().pop();
		}
		ci.cancel();
	}
}