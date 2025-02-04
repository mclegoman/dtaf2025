/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.item;

import com.mclegoman.dtaf2025.common.block.BlockRegistry;
import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.dtaf2025.common.entity.EntityRegistry;
import com.mclegoman.dtaf2025.common.sound.SoundRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ItemRegistry {
	public static final Item spaceAir;
	public static final Item moonStone;
	public static final Item crystal;
	public static final Item crystalBlock;
	public static final Item crystalGlass;
	public static final Item crystalGlassPane;
	public static final Item lockedChest;
	public static final Item moonSlimeSpawnEgg;
	public static final Item moonSlimeBall;
	public static final Item musicDiscBrightDay;
	public static void init() {
	}
	static {
		spaceAir = Items.register(BlockRegistry.spaceAir);
		moonStone = Items.register(BlockRegistry.moonStone);
		crystal = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Data.getVersion().getID(), "crystal")), Item::new, new Item.Settings());
		crystalBlock = Items.register(BlockRegistry.crystalBlock);
		crystalGlass = Items.register(BlockRegistry.crystalGlass);
		crystalGlassPane = Items.register(BlockRegistry.crystalGlassPane);
		lockedChest = Items.register(BlockRegistry.lockedChest);
		moonSlimeSpawnEgg = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Data.getVersion().getID(), "moon_slime_spawn_egg")), (settings) -> new SpawnEggItem(EntityRegistry.moonSlime, settings), new Item.Settings());
		moonSlimeBall = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Data.getVersion().getID(), "moon_slime_ball")), Item::new, new Item.Settings());
		musicDiscBrightDay = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Data.getVersion().getID(), "music_disc_bright_day")), Item::new, new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON).jukeboxPlayable(SoundRegistry.jukeboxBrightDay));
	}
}
