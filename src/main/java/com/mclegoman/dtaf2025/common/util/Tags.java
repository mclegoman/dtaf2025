/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.util;

import com.mclegoman.dtaf2025.common.data.Data;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class Tags {
	public static class Structure {
		public static final TagKey<net.minecraft.world.gen.structure.Structure> leadLocators;
		static {
			leadLocators = TagKey.of(RegistryKeys.STRUCTURE, Identifier.of(Data.getVersion().getID(), "lead_locators"));
		}
	}
	public static class EntityType {
		public static final TagKey<net.minecraft.entity.EntityType<?>> canBreatheInSpace;
		static {
			canBreatheInSpace = TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(Data.getVersion().getID(), "can_breathe_in_space"));
		}
	}
	public static class Block {
		public static final TagKey<net.minecraft.block.Block> moonOreReplaceables;
		public static final TagKey<net.minecraft.block.Block> spaceAir;
		public static final TagKey<net.minecraft.block.Block> spacePortal;
		public static final TagKey<net.minecraft.block.Block> spacePortalSurfaces;
		static {
			moonOreReplaceables = TagKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "moon_ore_replaceables"));
			spaceAir = TagKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "space_air"));
			spacePortal = TagKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "space_portal"));
			spacePortalSurfaces = TagKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "space_portal_surfaces"));
		}
	}
	public static class WorldGen {
		public static class Biome {
			public static final TagKey<net.minecraft.world.biome.Biome> leadLocators;
			public static final TagKey<net.minecraft.world.biome.Biome> noOxygen;
			public static final TagKey<net.minecraft.world.biome.Biome> space;
			static {
				leadLocators = TagKey.of(RegistryKeys.BIOME, Identifier.of(Data.getVersion().getID(), "lead_locators"));
				noOxygen = TagKey.of(RegistryKeys.BIOME, Identifier.of(Data.getVersion().getID(), "no_oxygen"));
				space = TagKey.of(RegistryKeys.BIOME, Identifier.of(Data.getVersion().getID(), "space"));
			}
		}
	}
	public static class Enchantment {
		public static final TagKey<net.minecraft.enchantment.Enchantment> dtaf2025;
		static {
			dtaf2025 = TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(Data.getVersion().getID(), "dtaf2025"));
		}
	}
	public static class PaintingVariant {
		public static final TagKey<net.minecraft.entity.decoration.painting.PaintingVariant> dtaf2025;
		static {
			dtaf2025 = TagKey.of(RegistryKeys.PAINTING_VARIANT, Identifier.of(Data.getVersion().getID(), "dtaf2025"));
		}
	}
}
