/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin.client.gui;

import com.mclegoman.dtaf2025.client.compatibility.Compatibility;
import com.mclegoman.dtaf2025.common.data.Data;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LogoDrawer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LogoDrawer.class)
public abstract class LogoDrawerMixin {
	@Shadow @Final private boolean ignoreAlpha;
	@Shadow @Final private boolean minceraft;
	@Shadow @Final public static Identifier LOGO_TEXTURE;
	@Shadow @Final public static Identifier MINCERAFT_TEXTURE;
	@Inject(at = @At("HEAD"), method = "draw(Lnet/minecraft/client/gui/DrawContext;IFI)V", cancellable = true)
	private void dtaf2025$draw(DrawContext context, int screenWidth, float alpha, int y, CallbackInfo ci) {
		int x = screenWidth / 2 - 128;
		int a = ColorHelper.getWhite(this.ignoreAlpha ? 1.0F : alpha);
		y -= 4;
		if (Compatibility.getModsButtonStyle().equals("CLASSIC")) y -= 12;
		context.drawTexture(RenderLayer::getGuiTextured, Identifier.of(Data.getVersion().getID(), "textures/gui/title/update.png"), x, y, 0.0F, 0.0F, 256, 128, 256, 128, a);
		context.drawTexture(RenderLayer::getGuiTextured, this.minceraft ? MINCERAFT_TEXTURE : LOGO_TEXTURE, x, y, 0.0F, 0.0F, 256, 44, 256, 64, a);
		ci.cancel();
	}
}