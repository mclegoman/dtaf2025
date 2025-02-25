/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.item;

import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.dtaf2025.common.item.ItemRegistry;
import com.mclegoman.dtaf2025.common.util.Tags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ItemGroupRegistry {
	protected static final RegistryKey<ItemGroup> dtaf2025;
	public static void init() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
			content.addAfter(Items.AMETHYST_SHARD, ItemRegistry.crystal);
			content.addAfter(Items.SLIME_BALL, ItemRegistry.moonSlimeBall);
			content.addAfter(Items.NETHER_BRICK, ItemRegistry.moonBrick);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
			content.addAfter(Items.END_STONE, ItemRegistry.moonStone);
			content.addAfter(ItemRegistry.moonStone, ItemRegistry.moonBricks);
			content.addAfter(Items.ANCIENT_DEBRIS, ItemRegistry.crystalOre);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
			content.addAfter(Items.OBSERVER, ItemRegistry.pulser);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
			content.addAfter(Items.AMETHYST_BLOCK, ItemRegistry.crystalBlock);
			content.addAfter(ItemRegistry.crystalBlock, ItemRegistry.crystalStairs);
			content.addAfter(ItemRegistry.crystalStairs, ItemRegistry.crystalSlab);
			content.addAfter(ItemRegistry.crystalSlab, ItemRegistry.crystalWall);
			content.addBefore(Items.END_STONE, ItemRegistry.moonStone);
			content.addAfter(ItemRegistry.moonStone, ItemRegistry.moonBricks);
			content.addAfter(ItemRegistry.moonBricks, ItemRegistry.crackedMoonBricks);
			content.addAfter(ItemRegistry.crackedMoonBricks, ItemRegistry.moonBrickStairs);
			content.addAfter(ItemRegistry.moonBrickStairs, ItemRegistry.moonBrickSlab);
			content.addAfter(ItemRegistry.moonBrickSlab, ItemRegistry.moonBrickWall);
			content.addAfter(ItemRegistry.moonBrickWall, ItemRegistry.chiseledMoonBricks);
		});
		ItemGroupEvents.modifyEntriesEvent(dtaf2025).register((content) -> {
			content.add(ItemRegistry.moonStone);
			content.add(ItemRegistry.moonBrick);
			content.add(ItemRegistry.moonBricks);
			content.add(ItemRegistry.crackedMoonBricks);
			content.add(ItemRegistry.moonBrickStairs);
			content.add(ItemRegistry.moonBrickSlab);
			content.add(ItemRegistry.moonBrickWall);
			content.add(ItemRegistry.chiseledMoonBricks);
			content.add(ItemRegistry.reinforcedMoonStone);
			content.add(ItemRegistry.crystalOre);
			content.add(ItemRegistry.crystal);
			content.add(ItemRegistry.crystalBlock);
			content.add(ItemRegistry.crystalStairs);
			content.add(ItemRegistry.crystalSlab);
			content.add(ItemRegistry.crystalWall);
			content.add(ItemRegistry.crystalGlass);
			content.add(ItemRegistry.crystalGlassPane);
			content.add(ItemRegistry.crystalWings);
			content.add(ItemRegistry.pulser);
			content.add(ItemRegistry.moonSlimeSpawnEgg);
			content.add(ItemRegistry.moonSlimeBall);
			content.add(ItemRegistry.musicDiscBrightDay);
			content.add(ItemRegistry.lockedChest);
			content.add(ItemRegistry.spaceAir);
			content.getContext().lookup().getOptional(RegistryKeys.ENCHANTMENT).ifPresent((registryWrapper) -> registryWrapper.streamEntries().forEach(enchantment -> {
				if (enchantment.isIn(Tags.Enchantment.dtaf2025)) content.add(EnchantmentHelper.getEnchantedBookWith(new EnchantmentLevelEntry(enchantment,  enchantment.value().getMaxLevel())));
			}));
			content.getContext().lookup().getOptional(RegistryKeys.PAINTING_VARIANT).ifPresent((registryWrapper) -> registryWrapper.streamEntries().filter((painting) -> painting.isIn(Tags.PaintingVariant.dtaf2025)).forEach((painting) -> {
				NbtComponent nbtComponent = NbtComponent.DEFAULT.with(content.getContext().lookup().getOps(NbtOps.INSTANCE), PaintingEntity.VARIANT_MAP_CODEC, painting).getOrThrow().apply((nbt) -> nbt.putString("id", "minecraft:painting"));
				ItemStack itemStack = new ItemStack(Items.PAINTING);
				itemStack.set(DataComponentTypes.ENTITY_DATA, nbtComponent);
				content.add(itemStack);
			}));
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(content -> {
			content.add(ItemRegistry.spaceAir);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
			content.addAfter(Items.MUSIC_DISC_PIGSTEP, ItemRegistry.musicDiscBrightDay);
			content.addAfter(Items.ELYTRA, ItemRegistry.crystalWings);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
			content.addAfter(Items.SLIME_SPAWN_EGG, ItemRegistry.moonSlimeSpawnEgg);
		});
	}
	public static RegistryKey<ItemGroup> register(Identifier id, ItemGroup itemGroup) {
		RegistryKey<ItemGroup> key = RegistryKey.of(Registries.ITEM_GROUP.getKey(), id);
		Registry.register(Registries.ITEM_GROUP, key, itemGroup);
		return key;
	}
	static {
		dtaf2025 = register(Identifier.of(Data.getVersion().getID(), Data.getVersion().getID()), FabricItemGroup.builder().icon(() -> new ItemStack(ItemRegistry.crystal)).displayName(Text.translatable("itemGroup." + Data.getVersion().getID(), Text.translatable(Data.getVersion().getID() + ".name"))).build());
	}
}
