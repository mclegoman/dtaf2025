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
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.Unit;

public class ItemRegistry {
	public static final Item spaceAir;
	public static final Item moonStone;
	public static final Item moonBrick;
	public static final Item moonBricks;
	public static final Item crackedMoonBricks;
	public static final Item moonBrickStairs;
	public static final Item moonBrickSlab;
	public static final Item moonBrickWall;
	public static final Item chiseledMoonBricks;
	public static final Item crystalOre;
	public static final Item crystal;
	public static final Item crystalBlock;
	public static final Item crystalStairs;
	public static final Item crystalSlab;
	public static final Item crystalWall;
	public static final Item crystalGlass;
	public static final Item crystalGlassPane;
	public static final Item crystalWings;
	public static final Item pulser;
	public static final Item lockedChest;
	public static final Item moonSlimeSpawnEgg;
	public static final Item moonSlimeBall;
	public static final Item musicDiscBrightDay;
	public static void init() {
	}
	static {
		spaceAir = Items.register(BlockRegistry.spaceAir);
		moonStone = Items.register(BlockRegistry.moonStone);
		crystalOre = Items.register(BlockRegistry.crystalOre);
		crystal = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Data.getVersion().getID(), "crystal")), Item::new, new Item.Settings());
		moonBrick = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Data.getVersion().getID(), "moon_brick")), Item::new, new Item.Settings());
		moonBricks = Items.register(BlockRegistry.moonBricks);
		crackedMoonBricks = Items.register(BlockRegistry.crackedMoonBricks);
		moonBrickStairs = Items.register(BlockRegistry.moonBrickStairs);
		moonBrickSlab = Items.register(BlockRegistry.moonBrickSlab);
		moonBrickWall = Items.register(BlockRegistry.moonBrickWall);
		chiseledMoonBricks = Items.register(BlockRegistry.chiseledMoonBricks);
		crystalBlock = Items.register(BlockRegistry.crystalBlock);
		crystalStairs = Items.register(BlockRegistry.crystalStairs);
		crystalSlab = Items.register(BlockRegistry.crystalSlab);
		crystalWall = Items.register(BlockRegistry.crystalWall);
		crystalGlass = Items.register(BlockRegistry.crystalGlass);
		crystalGlassPane = Items.register(BlockRegistry.crystalGlassPane);
		crystalWings = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Data.getVersion().getID(), "crystal_wings")), Item::new, new Item.Settings().maxDamage(864).rarity(Rarity.EPIC).component(DataComponentTypes.GLIDER, Unit.INSTANCE).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.builder(EquipmentSlot.CHEST).equipSound(SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA).model(RegistryKey.of(RegistryKey.ofRegistry(Identifier.ofVanilla("equipment_asset")), Identifier.of(Data.getVersion().getID(), "crystal_wings"))).damageOnHurt(false).build()).repairable(Items.PHANTOM_MEMBRANE));
		pulser = Items.register(BlockRegistry.pulser);
		lockedChest = Items.register(BlockRegistry.lockedChest);
		moonSlimeSpawnEgg = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Data.getVersion().getID(), "moon_slime_spawn_egg")), (settings) -> new SpawnEggItem(EntityRegistry.moonSlime, settings), new Item.Settings());
		moonSlimeBall = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Data.getVersion().getID(), "moon_slime_ball")), Item::new, new Item.Settings().food(FoodRegistry.moonSlime, ConsumableRegistry.moonSlime));
		musicDiscBrightDay = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Data.getVersion().getID(), "music_disc_bright_day")), Item::new, new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON).jukeboxPlayable(SoundRegistry.jukeboxBrightDay));
	}
}
