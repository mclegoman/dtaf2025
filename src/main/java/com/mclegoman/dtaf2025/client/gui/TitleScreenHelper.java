/*
    dtaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.gui;

import com.mclegoman.dtaf2025.common.data.Data;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

public class TitleScreenHelper {
	public static final CubeMapRenderer cubeMapRenderer;
	public static final RotatingCubeMapRenderer rotatingCubeMapRenderer;
	public static void init() {
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SplashesDataloader());
	}
	static {
		cubeMapRenderer = new CubeMapRenderer(Identifier.of(Data.version.getID(), "textures/gui/title/background/panorama"));
		rotatingCubeMapRenderer = new RotatingCubeMapRenderer(cubeMapRenderer);
	}
}
