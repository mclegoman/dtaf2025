/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/MCLegoMan/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.screen.config;

import com.mclegoman.dtaf2025.client.config.ClientConfigHelper;
import com.mclegoman.dtaf2025.client.data.ClientData;
import com.mclegoman.dtaf2025.client.gui.SplashesDataloader;
import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.dtaf2025.common.util.Compatibility;
import com.mclegoman.luminance.client.translation.Translation;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LogoDrawer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.*;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class ClientConfigScreen extends Screen {
	protected final GridWidget grid;
	protected GridWidget.Adder gridAdder;
	private boolean shouldSave;
	private final Screen parent;

	public ClientConfigScreen(Screen parent) {
		this(parent, false);
	}

	private ClientConfigScreen(Screen parent, boolean shouldSave) {
		super(Translation.getConfigTranslation(Data.version.getID(), "config"));
		this.grid = new GridWidget();
		this.parent = parent;
		this.shouldSave = shouldSave;
	}

	protected void init() {
		this.grid.getMainPositioner().alignHorizontalCenter().margin(2).alignVerticalCenter().relativeY(30);
		this.gridAdder = grid.createAdder(2);
		if (ClientConfigHelper.get("actually_open_shop").isPresent()) {
			this.gridAdder.add(ButtonWidget.builder(Translation.getConfigTranslation(Data.version.getID(), "actually_open_shop", new Object[]{ClientConfigHelper.get("actually_open_shop").get()}), (button) -> {
				this.shouldSave = ClientConfigHelper.toggle("actually_open_shop");
				refresh();
			}).tooltip(Tooltip.of(Translation.getConfigTranslation(Data.version.getID(), "actually_open_shop.hover"))).build(), 2);
		}
		addDrawableChild(ButtonWidget.builder(Translation.getConfigTranslation(Data.version.getID(), "close"), (button) -> this.exit()).tooltip(Tooltip.of(Translation.getConfigTranslation(Data.version.getID(), "close.hover"))).size(300, 20).position((this.width / 2) - 150, this.height - 24).build());
		this.grid.refreshPositions();
		this.grid.forEachChild(this::addDrawableChild);
		SimplePositioningWidget.setPos(this.grid, getNavigationFocus());
	}

	private void refresh() {
		ClientData.client.setScreen(new ClientConfigScreen(this.parent, this.shouldSave));
	}

	public boolean shouldCloseOnEsc() {
		return false;
	}

	private boolean exit() {
		if (this.shouldSave) ClientConfigHelper.save();
		ClientData.client.setScreen(parent);
		return true;
	}

	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (keyCode == 256) return exit();
		else return super.keyPressed(keyCode, scanCode, modifiers);
	}

	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		super.render(context, mouseX, mouseY, delta);
		// Logo Drawing
		new LogoDrawer(true).draw(context, this.width, 1.0F);
		if (SplashesDataloader.getSplashText() != null) {
			context.getMatrices().push();
			context.getMatrices().translate((float)this.width / 2.0F + 123.0F, 69.0F, 0.0F);
			context.getMatrices().multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-20.0F));
			float f = 1.8F - MathHelper.abs(MathHelper.sin((float)(Util.getMeasuringTimeMs() % 1000L) / 1000.0F * 6.2831855F) * 0.1F);
			f = f * 100.0F / (float)(textRenderer.getWidth(SplashesDataloader.getSplashText().getSecond() ? Text.translatable(SplashesDataloader.getSplashText().getFirst()) : Text.literal(SplashesDataloader.getSplashText().getFirst())) + 32);
			context.getMatrices().scale(f, f, f);
			context.drawCenteredTextWithShadow(textRenderer, SplashesDataloader.getSplashText().getSecond() ? Text.translatable(SplashesDataloader.getSplashText().getFirst()) : Text.literal(SplashesDataloader.getSplashText().getFirst()), 0, Compatibility.getModsButtonStyle().equals("CLASSIC") ? -20 : -8, 16776960);
			context.getMatrices().pop();
		}

		if (Data.version.isDevelopmentBuild()) context.drawTextWithShadow(ClientData.client.textRenderer, Text.translatable(Data.version.getID() + ".development_overlay", Text.translatable(Data.version.getID() + ".name"), Data.version.getFriendlyString()), 2, Compatibility.getModsButtonStyle().equals("CLASSIC") ? 2 : ClientData.client.getWindow().getScaledHeight() - 20, 0xFFFFFF);
	}
}
