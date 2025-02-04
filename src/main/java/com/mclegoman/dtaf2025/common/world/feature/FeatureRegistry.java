/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.world.feature;

import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.dtaf2025.common.world.feature.crater.CraterFeature;
import com.mclegoman.dtaf2025.common.world.feature.crater.CraterFeatureConfig;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;

public class FeatureRegistry {
	public static final Feature<CraterFeatureConfig> crater;
	public static void init() {
	}
	static {
		crater = Registry.register(Registries.FEATURE, Identifier.of(Data.version.getID(), "crater"), new CraterFeature(CraterFeatureConfig.codec));
	}
}
