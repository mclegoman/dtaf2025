/*
    dtaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.world.biome;

import com.mclegoman.dtaf2025.common.data.Data;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class BiomeTags {
	public static final TagKey<Biome> noOxygen;
	static {
		noOxygen = TagKey.of(RegistryKeys.BIOME, Identifier.of(Data.version.getID(), "no_oxygen"));
	}
}
