/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.gui;

import com.mclegoman.dtaf2025.client.config.AprilFoolsConfig;
import com.mclegoman.dtaf2025.client.data.ClientData;
import com.mclegoman.dtaf2025.client.screen.LogoConfirmScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.resource.ResourceType;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class TitleScreenHelper {
	public static boolean hasSeenFlashingLightsWarning;
	public static CubeMapRenderer cubeMapRenderer;
	public static RotatingCubeMapRenderer rotatingCubeMapRenderer;
	public static void init() {
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new PanoramaDataloader());
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SplashesDataloader());
	}
	public static void updateCubeMapRenderer() {
		Identifier panoramaDir = PanoramaDataloader.getPanorama().getFirst();
		cubeMapRenderer = new CubeMapRenderer(Identifier.of(panoramaDir.getNamespace(), panoramaDir.getPath() + "/panorama"));
		rotatingCubeMapRenderer = new RotatingCubeMapRenderer(cubeMapRenderer);
	}
	public static void showFlashingLightsWarning() {
		if (!TitleScreenHelper.hasSeenFlashingLightsWarning) {
			if (AprilFoolsConfig.config.showPhotosensitivityWarningOnStartup.value()) {
				Screen currentScreen = ClientData.client.currentScreen;
				ClientData.client.setScreen(new LogoConfirmScreen((confirm) -> {
					if (confirm) {
						TitleScreenHelper.hasSeenFlashingLightsWarning = true;
						ClientData.client.setScreen(currentScreen);
					} else ClientData.client.scheduleStop();
				}, Text.translatable("dtaf2025.flashing_lights").formatted(Formatting.BOLD), Text.translatable("dtaf2025.flashing_lights.description"), ScreenTexts.PROCEED, Text.translatable("menu.quit")));
			} else TitleScreenHelper.hasSeenFlashingLightsWarning = true;
		}
	}
	static {
		updateCubeMapRenderer();
	}
}
