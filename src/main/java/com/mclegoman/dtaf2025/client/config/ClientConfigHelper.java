/*
    Luminance
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/Luminance
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.config;

import com.mclegoman.luminance.client.translation.Translation;
import com.mclegoman.luminance.common.data.Data;
import com.mclegoman.luminance.common.util.LogType;

import java.util.Optional;

public class ClientConfigHelper {
	public static void init() {
		try {
			ClientConfig.init();
		} catch (Exception error) {
			Data.version.sendToLog(LogType.WARN, Translation.getString("Failed to initialize config!: {}", error));
		}
	}
	public static void save() {
		try {
			ClientConfig.save();
		} catch (Exception error) {
			Data.version.sendToLog(LogType.WARN, "Failed to save config!");
		}
	}
	public static boolean reset(boolean save) {
		boolean configChanged = false;
		try {
			configChanged = set("actually_open_shop", true);
		} catch (Exception error) {
			Data.version.sendToLog(LogType.WARN, "Failed to reset config!");
		}
		if (save) save();
		return configChanged;
	}
	public static boolean set(String id, boolean value) {
		boolean configChanged = false;
		try {
			switch (id) {
				case "actually_open_shop" -> {
					ClientConfig.actuallyOpenShop = value;
					configChanged = true;
				}
				default -> Data.version.sendToLog(LogType.WARN, Translation.getString("Failed to set {} config value!: Invalid Key", id));
			}
		} catch (Exception error) {
			Data.version.sendToLog(LogType.WARN, Translation.getString("Failed to set {} config value!: {}", id, error));
		}
		return configChanged;
	}
	public static boolean toggle(String id) {
		boolean configChanged = false;
		try {
			switch (id) {
				case "actually_open_shop" -> {
					ClientConfig.actuallyOpenShop = !ClientConfig.actuallyOpenShop;
					configChanged = true;
				}
				default -> Data.version.sendToLog(LogType.WARN, Translation.getString("Failed to set {} config value!: Invalid Key", id));
			}
		} catch (Exception error) {
			Data.version.sendToLog(LogType.WARN, Translation.getString("Failed to set {} config value!: {}", id, error));
		}
		return configChanged;
	}
	public static Optional<Boolean> get(String id) {
		switch (id) {
			case "actually_open_shop" -> {
				return Optional.of(ClientConfig.actuallyOpenShop);
			}
			default -> {
				Data.version.sendToLog(LogType.WARN, Translation.getString("Failed to get {} config value!: Invalid Key", id));
				return Optional.empty();
			}
		}
	}
}