/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.screen;

import com.mclegoman.dtaf2025.client.config.AprilFoolsConfig;
import com.mclegoman.dtaf2025.client.data.ClientData;
import com.mclegoman.dtaf2025.client.shaders.Shaders;
import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.luminance.client.translation.Translation;
import com.mclegoman.luminance.common.util.LogType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LogoDrawer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.gui.widget.*;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

import net.minecraft.util.math.MathHelper;

public class ConfigScreen extends Screen {
	private final SplashTextRenderer splashText;
	private final Screen parent;
	private final LogoDrawer logoDrawer;
	private final GridWidget grid;
	public ConfigScreen(Screen parent) {
		super(Text.literal(""));
		this.parent = parent;
		this.logoDrawer = new LogoDrawer(true);
		this.grid = new GridWidget();
		this.splashText = new SplashTextRenderer("");
	}
	public void init() {
		this.clearChildren();
		try {
			this.grid.getMainPositioner().marginX(4).marginBottom(4).alignHorizontalCenter();
			GridWidget.Adder gridAdder = this.grid.createAdder(2);

			gridAdder.add(new EmptyWidget(44, 44), 2);

			gridAdder.add(new SliderWidget(gridAdder.getGridWidget().getX(), gridAdder.getGridWidget().getY(), 150, 20, Translation.getConfigTranslation(Data.getVersion().getID(), "bloom_strength", new Object[]{Text.literal(Shaders.getRawBloomStrength() + "%")}, false), Shaders.getRawBloomStrength() / 100.0F) {
				@Override
				protected void updateMessage() {
					setMessage(Translation.getConfigTranslation(Data.getVersion().getID(), "bloom_strength", new Object[]{Text.literal(Shaders.getRawBloomStrength() + "%")}, false));
				}

				@Override
				protected void applyValue() {
					AprilFoolsConfig.config.bloomStrength.setValue((int) ((value) * 100), true);
				}
			});

			gridAdder.add(new SliderWidget(gridAdder.getGridWidget().getX(), gridAdder.getGridWidget().getY(), 150, 20, Translation.getConfigTranslation(Data.getVersion().getID(), "easter_egg_strength", new Object[]{Text.literal(Shaders.getRawEasterEggStrength() + "%")}, false), Shaders.getRawEasterEggStrength() / 100.0F) {
				@Override
				protected void updateMessage() {
					setMessage(Translation.getConfigTranslation(Data.getVersion().getID(), "easter_egg_strength", new Object[]{Text.literal(Shaders.getRawEasterEggStrength() + "%")}, false));
				}

				@Override
				protected void applyValue() {
					AprilFoolsConfig.config.easterEggStrength.setValue((int) ((value) * 100), true);
				}
			});

			gridAdder.add(ButtonWidget.builder(Translation.getConfigTranslation(Data.getVersion().getID(),  "can_supply_crates_open_shop", new Object[]{AprilFoolsConfig.config.canSupplyCratesOpenShop.value()}), (button) -> {
				AprilFoolsConfig.config.canSupplyCratesOpenShop.setValue(!AprilFoolsConfig.config.canSupplyCratesOpenShop.value(), true);
				button.setMessage(Translation.getConfigTranslation(Data.getVersion().getID(),  "can_supply_crates_open_shop", new Object[]{AprilFoolsConfig.config.canSupplyCratesOpenShop.value()}, false));
			}).width(308).build(), 2);

			gridAdder.add(ButtonWidget.builder(Translation.getConfigTranslation(Data.getVersion().getID(),  "show_photosensitivity_warning", new Object[]{AprilFoolsConfig.config.showPhotosensitivityWarningOnStartup.value()}), (button) -> {
				AprilFoolsConfig.config.showPhotosensitivityWarningOnStartup.setValue(!AprilFoolsConfig.config.showPhotosensitivityWarningOnStartup.value(), true);
				button.setMessage(Translation.getConfigTranslation(Data.getVersion().getID(),  "show_photosensitivity_warning", new Object[]{AprilFoolsConfig.config.showPhotosensitivityWarningOnStartup.value()}, false));
			}).width(308).build(), 2);

			this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, (button) -> this.close()).width(200).position(this.width / 2 - 100, this.height - 26).build());

			this.grid.refreshPositions();
			this.grid.forEachChild(this::addDrawableChild);
			this.initTabNavigation();
		} catch (Exception error) {
			Data.getVersion().sendToLog(LogType.ERROR, Translation.getString("Failed to initialize config screen: {}", error));
		}

	}
	public void initTabNavigation() {
		SimplePositioningWidget.setPos(this.grid, this.getNavigationFocus());
	}
	public Text getNarratedTitle() {
		return Text.translatable("dtaf2025.name");
	}
	public boolean shouldCloseOnEsc() {
		return true;
	}
	public boolean shouldPause() {
		return false;
	}
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		super.render(context, mouseX, mouseY, delta);
		this.logoDrawer.draw(context, this.width, 1.0F);
		if (this.splashText != null && !(Boolean) ClientData.client.options.getHideSplashTexts().getValue()) {
			this.splashText.render(context, this.width, this.textRenderer, MathHelper.ceil(255.0F) << 24);
		}
	}
	public void resize(MinecraftClient client, int width, int height) {
		ClientData.client.setScreen(new ConfigScreen(this.parent));
	}

	@Override
	public void close() {
		ClientData.client.setScreen(this.parent);
	}
}
