/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.screen.locked_chest;

import com.mclegoman.dtaf2025.client.data.ClientData;
import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.dtaf2025.client.compatibility.Compatibility;
import com.mclegoman.luminance.client.translation.Translation;
import com.mclegoman.luminance.common.util.LogType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.*;
import net.minecraft.text.Text;

import java.net.URI;

@Environment(EnvType.CLIENT)
public class LockedChestInfoScreen extends Screen {
	protected final GridWidget grid;
	protected GridWidget.Adder gridAdder;
	private final Screen parent;
	public LockedChestInfoScreen(Screen parent) {
		super(Translation.getTranslation(Data.getVersion().getID(), "locked_chest"));
		this.parent = parent;
		this.grid = new GridWidget();
	}
	protected void init() {
		this.grid.getMainPositioner().alignHorizontalCenter().margin(2);
		this.gridAdder = grid.createAdder(1);

		//this.gridAdder.add(new SteveCoWidget(0, 0, Translation.getTranslation(Data.getVersion().getID(), "locked_chest")));
		this.gridAdder.add(new EmptyWidget(24, 24));

		Text messageText = Translation.getTranslation(Data.getVersion().getID(), "locked_chest.info.message");
		MultilineTextWidget message = new MultilineTextWidget(messageText, this.textRenderer);
		message.setCentered(true);
		message.setMaxWidth(this.width - 4);
		this.gridAdder.add(message);

		Text wikiText = Translation.getTranslation(Data.getVersion().getID(), "locked_chest.info.wiki");
		this.gridAdder.add(new PressableTextWidget(this.grid.getX(), this.grid.getY(), this.textRenderer.getWidth(wikiText), 9, wikiText, (text) -> {
			try {
				ConfirmLinkScreen.open(new LockedChestInfoScreen(parent), new URI("https://minecraft.wiki/w/Locked_chest"), true);
			} catch (Exception error) {
				Data.getVersion().sendToLog(LogType.ERROR, error.getLocalizedMessage());
			}
		}, this.textRenderer));

		this.gridAdder.add(new EmptyWidget(24, 24));

		Text warningText = Translation.getTranslation(Data.getVersion().getID(), "locked_chest.info.warning");
		MultilineTextWidget warning = new MultilineTextWidget(warningText, this.textRenderer);
		warning.setCentered(true);
		warning.setMaxWidth(this.width - 4);
		this.gridAdder.add(warning);

		this.grid.refreshPositions();
		this.grid.forEachChild(this::addDrawableChild);
		SimplePositioningWidget.setPos(this.grid, getNavigationFocus());

		addDrawableChild(ButtonWidget.builder(Translation.getTranslation(Data.getVersion().getID(), "locked_chest.info.exit"), (button) -> exit()).size(20, 20).position(4, this.height - 24).build());
	}

	public void resize(MinecraftClient client, int width, int height) {
		ClientData.client.setScreen(new LockedChestInfoScreen(parent));
	}

	public boolean shouldCloseOnEsc() {
		return false;
	}

	private boolean exit() {
		ClientData.client.setScreen(parent);
		return true;
	}

	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (keyCode == 256) return exit();
		else return super.keyPressed(keyCode, scanCode, modifiers);
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		super.render(context, mouseX, mouseY, delta);
		SteveCoWidget.renderWidget(context, this.width / 2 - 128, 30);
		if (Data.getVersion().isDevelopmentBuild()) context.drawTextWithShadow(ClientData.client.textRenderer, Text.translatable(Data.getVersion().getID() + ".development_overlay", Text.translatable(Data.getVersion().getID() + ".name"), Data.getVersion().getFriendlyString()), 2, Compatibility.getModsButtonStyle().equals("CLASSIC") ? 2 : ClientData.client.getWindow().getScaledHeight() - 20, 0xFFFFFF);
	}
}
