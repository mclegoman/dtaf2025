/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common;

import com.mclegoman.dtaf2025.common.block.BlockRegistry;
import com.mclegoman.dtaf2025.common.command.CommandRegistry;
import com.mclegoman.dtaf2025.common.easter_egg.EasterEggsRegistry;
import com.mclegoman.dtaf2025.common.enchantment.EnchantmentRegistry;
import com.mclegoman.dtaf2025.common.item.ItemRegistry;
import com.mclegoman.dtaf2025.common.painting.PaintingRegistry;
import com.mclegoman.dtaf2025.common.sound.SoundRegistry;
import com.mclegoman.dtaf2025.common.world.dimension.DimensionRegistry;
import net.fabricmc.api.ModInitializer;

public class AprilFools implements ModInitializer {
	@Override
	public void onInitialize() {
		SoundRegistry.init();
		ItemRegistry.init();
		BlockRegistry.init();
		PaintingRegistry.init();
		EnchantmentRegistry.init();
		DimensionRegistry.init();
		EasterEggsRegistry.init();
		CommandRegistry.init();
	}
}