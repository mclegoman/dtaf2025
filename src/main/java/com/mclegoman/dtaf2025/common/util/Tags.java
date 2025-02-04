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
	public static class EntityType {
		public static final TagKey<net.minecraft.entity.EntityType<?>> canBreatheInSpace;
		static {
			canBreatheInSpace = TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(Data.getVersion().getID(), "can_breathe_in_space"));
		}
	}
	public static class Block {
		public static final TagKey<net.minecraft.block.Block> spaceAir;
		static {
			spaceAir = TagKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "space_air"));
		}
	}
	public static class WorldGen {
		public static class Biome {
			public static final TagKey<net.minecraft.world.biome.Biome> noOxygen;
			static {
				noOxygen = TagKey.of(RegistryKeys.BIOME, Identifier.of(Data.getVersion().getID(), "no_oxygen"));
			}
		}
	}
}
