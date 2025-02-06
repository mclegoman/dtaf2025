/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.keybindings;

import com.mclegoman.dtaf2025.client.data.ClientData;
import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.luminance.client.keybindings.KeybindingHelper;
import com.mclegoman.luminance.client.translation.Translation;
import com.mclegoman.luminance.common.util.LogType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.option.KeyBinding;

public class Keybindings {
	private static final KeyBinding takePanoramicScreenshots;
	public static void init() {
		Data.getVersion().sendToLog(LogType.INFO, Translation.getString("Initializing keybindings!"));
	}
	public static void tick() {
		if (takePanoramicScreenshots != null && takePanoramicScreenshots.wasPressed()) ClientData.client.takePanorama(ClientData.client.runDirectory, 1024, 1024);
	}
	static {
		takePanoramicScreenshots = FabricLoader.getInstance().isDevelopmentEnvironment() ? KeybindingHelper.getKeybinding(Data.getVersion().getID(), Data.getVersion().getID(), "takePanoramicScreenshots", -1) : null;
	}
}
