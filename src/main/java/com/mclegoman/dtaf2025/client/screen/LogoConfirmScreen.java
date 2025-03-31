/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.screen;

import com.mclegoman.dtaf2025.client.data.ClientData;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LogoDrawer;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

public class LogoConfirmScreen extends ConfirmScreen {
	private final LogoDrawer logoDrawer;
	private final SplashTextRenderer splashText;
	public LogoConfirmScreen(BooleanConsumer callback, Text title, Text message, Text yesText, Text noText) {
		super(callback, title, message, yesText, noText);
		this.logoDrawer = new LogoDrawer(true);
		this.splashText = new SplashTextRenderer("");
	}
	public int getTitleY() {
		return super.getTitleY() + 44;
	}
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		super.render(context, mouseX, mouseY, delta);
		this.logoDrawer.draw(context, this.width, 1.0F);
		if (this.splashText != null && !(Boolean) ClientData.client.options.getHideSplashTexts().getValue()) {
			this.splashText.render(context, this.width, this.textRenderer, MathHelper.ceil(255.0F) << 24);
		}
	}
}
