package com.mclegoman.mclmaf2025.common.data;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

public class Data {
	public static ModContainer modContainer;
	public static String modId;
	public static String modVersion;
	public static boolean developmentBuild;
	public static void init() {
	}
	static {
		modId = "mclmaf2025";
		modContainer = FabricLoader.getInstance().getModContainer(modId).isPresent() ? FabricLoader.getInstance().getModContainer(modId).get() : null;
		assert modContainer != null;
		modVersion = String.valueOf(modContainer.getMetadata().getVersion());
		developmentBuild = true;
	}
}
