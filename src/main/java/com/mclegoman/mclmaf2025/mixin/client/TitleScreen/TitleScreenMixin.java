/*
    mclmaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclmaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclmaf2025.mixin.client.TitleScreen;

import com.mclegoman.mclmaf2025.client.data.ClientData;
import com.mclegoman.mclmaf2025.client.gui.TitleScreenHelper;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
	protected TitleScreenMixin(Text title) {
		super(title);
	}
	@Inject(at = @At("HEAD"), method = "renderPanoramaBackground", cancellable = true)
	private void mclmaf2025$renderPanoramaBackground(DrawContext context, float delta, CallbackInfo ci) {
		TitleScreenHelper.rotatingCubeMapRenderer.render(context, ClientData.client.getWindow().getScaledWidth(), ClientData.client.getWindow().getScaledHeight(), 1.0F, this.getPanoramaTickDelta());
		ci.cancel();
	}
}