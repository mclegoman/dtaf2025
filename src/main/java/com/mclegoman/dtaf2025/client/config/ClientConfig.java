/*
    Luminance
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/Luminance
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.config;

import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.luminance.client.translation.Translation;
import com.mclegoman.luminance.common.util.Couple;
import com.mclegoman.luminance.common.util.LogType;
import com.mclegoman.luminance.config.ConfigProvider;
import net.darktree.simplelibs.config.SimpleConfig;

public class ClientConfig {
	protected static final String id = Data.version.getID() + "+client";
	protected static SimpleConfig config;
	protected static ConfigProvider configProvider;
	protected static boolean actuallyOpenShop;
	protected static void init() {
		try {
			configProvider = new ConfigProvider();
			create();
			config = SimpleConfig.of(id).provider(configProvider).request();
			assign();
			save();
		} catch (Exception error) {
			Data.version.sendToLog(LogType.WARN, Translation.getString("Failed to initialize {} config: {}", id, error));
		}
	}
	protected static void create() {
		configProvider.add(new Couple<>("actually_open_shop", true));
	}
	protected static void assign() {
		actuallyOpenShop = config.getOrDefault("actually_open_shop", true);
	}
	protected static void save() {
		Data.version.sendToLog(LogType.INFO,"Writing config to file.");
		configProvider.setConfig("actually_open_shop", actuallyOpenShop);
		configProvider.saveConfig(Data.version, id);
	}
}