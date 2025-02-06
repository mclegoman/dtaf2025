/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin.client.title_screen;

import com.mclegoman.dtaf2025.client.compatibility.Compatibility;
import com.mclegoman.dtaf2025.client.data.ClientData;
import com.mclegoman.dtaf2025.client.gui.TitleScreenHelper;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TitleScreen.class, priority = 100)
public abstract class TitleScreenMixin extends Screen {
	protected TitleScreenMixin(Text title) {
		super(title);
	}
	@Inject(at = @At("HEAD"), method = "renderPanoramaBackground", cancellable = true)
	private void dtaf2025$renderPanoramaBackground(DrawContext context, float delta, CallbackInfo ci) {
		TitleScreenHelper.rotatingCubeMapRenderer.render(context, ClientData.client.getWindow().getScaledWidth(), ClientData.client.getWindow().getScaledHeight(), 1.0F, delta);
		ci.cancel();
	}
	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 2), method = "addNormalWidgets", cancellable = true)
	private void dtaf2025$renderPanoramaBackground(int y, int spacingY, CallbackInfoReturnable<Integer> cir) {
		if (Compatibility.getModsButtonStyle().equals("none")) {
			cir.setReturnValue(y);
		}
	}
}