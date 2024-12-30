/*
    dtaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common;

import com.mclegoman.dtaf2025.common.registry.BlockRegistry;
import com.mclegoman.dtaf2025.common.registry.EasterEggsRegistry;
import com.mclegoman.dtaf2025.client.registry.ItemGroupRegistry;
import com.mclegoman.dtaf2025.common.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;

public class AprilFools implements ModInitializer {
	@Override
	public void onInitialize() {
		BlockRegistry.init();
		ItemRegistry.init();
		EasterEggsRegistry.init();
	}
}