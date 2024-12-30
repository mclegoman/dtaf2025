/*
    dtaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.item;

import com.mclegoman.dtaf2025.common.block.BlockRegistry;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemRegistry {
	public static final Item crystal;
	public static final Item crystalBlock;
	public static final Item crystalGlass;
	public static final Item crystalGlassPane;
	public static final Item lockedChest;
	public static void init() {
	}
	static {
		crystal = Registry.register(Registries.ITEM, Identifier.of("dtaf2025", "crystal"), new Item(new Item.Settings()));
		crystalBlock = Registry.register(Registries.ITEM, Identifier.of("dtaf2025", "crystal_block"), new BlockItem(BlockRegistry.crystalBlock, new Item.Settings()));
		crystalGlass = Registry.register(Registries.ITEM, Identifier.of("dtaf2025", "crystal_glass"), new BlockItem(BlockRegistry.crystalGlass, new Item.Settings()));
		crystalGlassPane = Registry.register(Registries.ITEM, Identifier.of("dtaf2025", "crystal_glass_pane"), new BlockItem(BlockRegistry.crystalGlassPane, new Item.Settings()));
		lockedChest = Registry.register(Registries.ITEM, Identifier.of("dtaf2025", "locked_chest"), new BlockItem(BlockRegistry.lockedChest, new Item.Settings()));
	}
}
