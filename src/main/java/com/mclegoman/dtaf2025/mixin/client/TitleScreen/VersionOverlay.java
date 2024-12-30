/*
    dtaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin.client.TitleScreen;

import com.mclegoman.dtaf2025.client.data.ClientData;
import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.dtaf2025.common.util.Compatibility;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class VersionOverlay {
	// This mixin is only enabled if Data.developmentBuild is true.
	@Shadow @Final private boolean doBackgroundFade;
	@Shadow private long backgroundFadeStart;
	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)I"), method = "render")
	private void dtaf2025$render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		// Renders the mod name and version on the title screen above the minecraft version.
		int fadeColor = MathHelper.ceil((this.doBackgroundFade ? MathHelper.clamp(((float)(Util.getMeasuringTimeMs() - this.backgroundFadeStart) / 1000.0F) - 1.0F, 0.0F, 1.0F) : 1.0F) * 255.0F) << 24;
		context.drawTextWithShadow(ClientData.client.textRenderer, Text.translatable(Data.version.getID() + ".development_overlay", Text.translatable(Data.version.getID() + ".name"), Data.version.getFriendlyString()), 2, Compatibility.getModsButtonStyle().equals("CLASSIC") ? 2 : ClientData.client.getWindow().getScaledHeight() - 20, 16777215 | fadeColor);
	}
}