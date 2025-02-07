/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class TitleScreenHelper {
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
	static {
		updateCubeMapRenderer();
	}
}
