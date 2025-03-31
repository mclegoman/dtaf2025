/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin.client.gui;

import com.mclegoman.dtaf2025.client.data.ClientData;
import com.mclegoman.dtaf2025.client.gui.TitleScreenHelper;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.AccessibilityOnboardingScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = AccessibilityOnboardingScreen.class, priority = 100)
public abstract class AccessibilityOnboardingScreenMixin extends Screen {
	protected AccessibilityOnboardingScreenMixin(Text title) {
		super(title);
	}
	@Inject(at = @At("HEAD"), method = "renderPanoramaBackground", cancellable = true)
	private void dtaf2025$renderPanoramaBackground(DrawContext context, float delta, CallbackInfo ci) {
		TitleScreenHelper.rotatingCubeMapRenderer.render(context, ClientData.client.getWindow().getScaledWidth(), ClientData.client.getWindow().getScaledHeight(), 1.0F, delta);
		ci.cancel();
	}
	@Inject(at = @At("HEAD"), method = "init")
	private void dtaf2025$init(CallbackInfo ci) {
		TitleScreenHelper.showFlashingLightsWarning();
	}
}