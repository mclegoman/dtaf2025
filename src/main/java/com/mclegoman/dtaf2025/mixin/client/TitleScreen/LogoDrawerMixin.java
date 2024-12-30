/*
    dtaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin.client.TitleScreen;

import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.dtaf2025.common.util.Compatibility;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LogoDrawer;
import net.minecraft.util.Identifier;
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
		// Renders a new update name texture, and the minecraft logo texture. The edition texture is disabled.
		context.setShaderColor(1.0F, 1.0F, 1.0F, this.ignoreAlpha ? 1.0F : alpha);
		int x = screenWidth / 2 - 128;
		// When ModMenu is installed and the mods button is in "CLASSIC" mode, we need to make the title render higher.
		if (Compatibility.getModsButtonStyle().equals("CLASSIC")) y -= 12;
		context.drawTexture(Identifier.of(Data.version.getID(), "textures/gui/title/update.png"), x, y, 0.0F, 0.0F, 256, 128, 256, 128);
		context.drawTexture(this.minceraft ? MINCERAFT_TEXTURE : LOGO_TEXTURE, x, y, 0.0F, 0.0F, 256, 44, 256, 64);
		context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		ci.cancel();
	}
}