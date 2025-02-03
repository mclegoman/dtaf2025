/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.util;

import com.mclegoman.dtaf2025.common.data.Data;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class Tags {
	public static class Blocks {
		public static final TagKey<Block> spaceAir;
		static {
			spaceAir = TagKey.of(RegistryKeys.BLOCK, Identifier.of(Data.version.getID(), "space_air"));
		}
	}
	public static class Biome {
		public static final TagKey<net.minecraft.world.biome.Biome> noOxygen;
		static {
			noOxygen = TagKey.of(RegistryKeys.BIOME, Identifier.of(Data.version.getID(), "no_oxygen"));
		}
	}
}
