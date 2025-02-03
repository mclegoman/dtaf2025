/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.screen.locked_chest;

import com.mclegoman.dtaf2025.common.data.Data;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SteveCoWidget extends ClickableWidget {
	public SteveCoWidget(int x, int y, Text message) {
		super(x, y, 256, 64, message);
	}
	protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
		renderWidget(context, this.getX(), this.getY());
	}
	public static void renderWidget(DrawContext context, int x, int y) {
		context.drawTexture(RenderLayer::getGuiTextured, Identifier.of(Data.version.getID(), "textures/gui/locked_chest.png"), x, y, 0.0F, 0.0F, 256, 48, 256, 64);
	}
	protected void appendClickableNarrations(NarrationMessageBuilder builder) {
	}
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		return false;
	}
	public void playDownSound(SoundManager soundManager) {
	}
}

