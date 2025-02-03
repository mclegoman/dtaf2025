/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.item;

import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.dtaf2025.common.item.ItemRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ItemGroupRegistry {
	protected static final RegistryKey<ItemGroup> dtaf2025;
	public static void init() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
			content.addAfter(Items.AMETHYST_SHARD, ItemRegistry.crystal);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
			content.addAfter(Items.AMETHYST_BLOCK, ItemRegistry.crystalBlock);
			content.addBefore(Items.END_STONE, ItemRegistry.moonStone);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
			content.addBefore(Items.END_STONE, ItemRegistry.moonStone);
		});
		ItemGroupEvents.modifyEntriesEvent(dtaf2025).register(content -> {
			content.add(ItemRegistry.crystal);
			content.add(ItemRegistry.crystalBlock);
			content.add(ItemRegistry.crystalGlass);
			content.add(ItemRegistry.crystalGlassPane);
			content.add(ItemRegistry.lockedChest);
			content.add(ItemRegistry.spaceAir);
			content.add(ItemRegistry.moonStone);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(content -> {
			content.add(ItemRegistry.spaceAir);
		});
	}
	public static RegistryKey<ItemGroup> register(Identifier id, ItemGroup itemGroup) {
		RegistryKey<ItemGroup> key = RegistryKey.of(Registries.ITEM_GROUP.getKey(), id);
		Registry.register(Registries.ITEM_GROUP, key, itemGroup);
		return key;
	}
	static {
		dtaf2025 = register(Identifier.of(Data.version.getID(), Data.version.getID()), FabricItemGroup.builder().icon(() -> new ItemStack(ItemRegistry.crystal)).displayName(Text.translatable("itemGroup." + Data.version.getID(), Text.translatable(Data.version.getID() + ".name"))).build());
	}
}
