/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.screen.locked_chest;

import com.mclegoman.dtaf2025.client.data.ClientData;
import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.dtaf2025.common.sound.SoundRegistry;
import com.mclegoman.luminance.client.translation.Translation;
import com.mclegoman.luminance.common.util.LogType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.*;
import net.minecraft.client.input.KeyCodes;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import org.jetbrains.annotations.Nullable;

import java.net.URI;

@Environment(EnvType.CLIENT)
public class LockedChestScreen extends Screen {
	protected final GridWidget grid;
	protected GridWidget.Adder gridAdder;
	private final Screen parent;
	private boolean openedStore;
	public LockedChestScreen() {
		this(ClientData.client.currentScreen);
	}
	public LockedChestScreen(Screen parent) {
		this (parent, false);
	}
	public LockedChestScreen(Screen parent, boolean openedStore) {
		super(Translation.getTranslation(Data.getVersion().getID(), "locked_chest"));
		this.grid = new GridWidget();
		this.parent = parent;
		this.openedStore = openedStore;
	}
	protected void init() {
		this.grid.getMainPositioner().alignHorizontalCenter().margin(2);
		this.gridAdder = grid.createAdder(2);
		//this.gridAdder.add(new SteveCoWidget(0, 0, Translation.getTranslation(Data.getVersion().getID(), "locked_chest")), 2);
		this.gridAdder.add(new EmptyWidget(24, 24));
		this.gridAdder.add(new TextWidget(Translation.getTranslation(Data.getVersion().getID(), "locked_chest.message.required"), this.textRenderer), 2);
		this.gridAdder.add(new TextWidget(Translation.getTranslation(Data.getVersion().getID(), "locked_chest.message.store"), this.textRenderer), 2);
		this.gridAdder.add(new EmptyWidget(24, 24), 2);
		this.gridAdder.add(ButtonWidget.builder(Translation.getTranslation(Data.getVersion().getID(), "locked_chest.cancel"), (button) -> this.exit()).tooltip(Tooltip.of(Translation.getTranslation(Data.getVersion().getID(), "locked_chest.cancel.hover"))).build(), 1);
		StoreButtonWidget storeButton = StoreButtonWidget.storeBuilder(Translation.getTranslation(Data.getVersion().getID(), "locked_chest.store"), (button) -> {
			try {
				URI uri = new URI("https://shop.minecraft.net/");
				Util.getOperatingSystem().open(uri);
			} catch (Exception error) {
				Data.getVersion().sendToLog(LogType.ERROR, error.getLocalizedMessage());
			}
			button.active = false;
			this.openedStore = true;
		}).tooltip(Tooltip.of(Translation.getTranslation(Data.getVersion().getID(), "locked_chest.store.hover"))).build();
		storeButton.active = !this.openedStore;
		addDrawableChild(ButtonWidget.builder(Translation.getTranslation(Data.getVersion().getID(), "locked_chest.info"), (button) -> ClientData.client.setScreen(new LockedChestInfoScreen(new LockedChestScreen(this.parent, this.openedStore)))).size(20, 20).position(4, this.height - 24).build());

		this.gridAdder.add(storeButton, 1);
		this.grid.refreshPositions();
		this.grid.forEachChild(this::addDrawableChild);
		SimplePositioningWidget.setPos(this.grid, getNavigationFocus());
	}

	public boolean shouldCloseOnEsc() {
		return false;
	}
	private boolean exit() {
		ClientData.client.setScreen(this.parent);
		return true;
	}

	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (keyCode == 256) return exit();
		else return super.keyPressed(keyCode, scanCode, modifiers);
	}

	private static class StoreButtonWidget extends ButtonWidget {
		protected StoreButtonWidget(int x, int y, int width, int height, Text message, PressAction onPress, NarrationSupplier narrationSupplier) {
			super(x, y, width, height, message, onPress, narrationSupplier);
		}
		@Override
		public void playDownSound(SoundManager soundManager) {
			soundManager.play(PositionedSoundInstance.master(SoundRegistry.lockedChestStore, 1.0F));
		}
		@Override
		public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
			if (this.active && this.visible && KeyCodes.isToggle(keyCode)) {
					this.playDownSound(MinecraftClient.getInstance().getSoundManager());
					this.onPress();
					return true;
			}
			return super.keyPressed(keyCode, scanCode, modifiers);
		}
		@Override
		public boolean mouseClicked(double mouseX, double mouseY, int button) {
			if (this.active && this.visible && this.isValidClickButton(button)) {
				if (this.isMouseOver(mouseX, mouseY)) {
					this.playDownSound(MinecraftClient.getInstance().getSoundManager());
					this.onClick(mouseX, mouseY);
					return true;
				}
			}
			return super.mouseClicked(mouseX, mouseY, button);
		}
		public static StoreBuilder storeBuilder(Text message, PressAction onPress) {
			return new StoreBuilder(message, onPress);
		}
		@Environment(EnvType.CLIENT)
		public static class StoreBuilder {
			private final Text message;
			private final ButtonWidget.PressAction onPress;
			@Nullable
			private Tooltip tooltip;
			private int x;
			private int y;
			private int width = 150;
			private int height = 20;
			public StoreBuilder(Text message, ButtonWidget.PressAction onPress) {
				this.message = message;
				this.onPress = onPress;
			}
			public StoreBuilder position(int x, int y) {
				this.x = x;
				this.y = y;
				return this;
			}
			public StoreBuilder width(int width) {
				this.width = width;
				return this;
			}
			public StoreBuilder size(int width, int height) {
				this.width = width;
				this.height = height;
				return this;
			}
			public StoreBuilder dimensions(int x, int y, int width, int height) {
				return this.position(x, y).size(width, height);
			}
			public StoreBuilder tooltip(@Nullable Tooltip tooltip) {
				this.tooltip = tooltip;
				return this;
			}
			public StoreButtonWidget build() {
				StoreButtonWidget buttonWidget = new StoreButtonWidget(this.x, this.y, this.width, this.height, this.message, this.onPress, ButtonWidget.DEFAULT_NARRATION_SUPPLIER);
				buttonWidget.setTooltip(this.tooltip);
				return buttonWidget;
			}
		}
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		super.render(context, mouseX, mouseY, delta);
		SteveCoWidget.renderWidget(context, this.width / 2 - 128, 30);
		if (Data.getVersion().isDevelopmentBuild()) context.drawTextWithShadow(ClientData.client.textRenderer, Text.translatable(Data.getVersion().getID() + ".development_overlay", Text.translatable(Data.getVersion().getID() + ".name"), Data.getVersion().getFriendlyString()), 2, 2, 0xFFFFFF);
	}
}
