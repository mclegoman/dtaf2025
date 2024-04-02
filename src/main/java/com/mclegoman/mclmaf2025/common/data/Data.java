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
		modContainer = FabricLoader.getInstance().getModContainer("mclmaf2025").get();
		modId = modContainer.getMetadata().getId();
		modVersion = String.valueOf(modContainer.getMetadata().getVersion());
		developmentBuild = false;
	}
}
