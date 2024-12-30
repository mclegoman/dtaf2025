/*
    dtaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common;

import com.mclegoman.dtaf2025.common.block.BlockRegistry;
import com.mclegoman.dtaf2025.common.easteregg.EasterEggsRegistry;
import com.mclegoman.dtaf2025.common.enchantment.EnchantmentRegistry;
import com.mclegoman.dtaf2025.common.item.ItemRegistry;
import com.mclegoman.dtaf2025.common.sound.SoundRegistry;
import net.fabricmc.api.ModInitializer;

public class AprilFools implements ModInitializer {
	@Override
	public void onInitialize() {
		SoundRegistry.init();
		BlockRegistry.init();
		ItemRegistry.init();
		EnchantmentRegistry.init();
		EasterEggsRegistry.init();
	}
}