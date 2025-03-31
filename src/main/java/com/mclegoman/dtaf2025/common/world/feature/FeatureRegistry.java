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
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;

public class FeatureRegistry {
	public static final RegistryKey<PlacedFeature> oreLeadSmall = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Data.getVersion().getID(), "ore_lead_small"));
	public static final RegistryKey<PlacedFeature> oreLeadMiddle = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Data.getVersion().getID(), "ore_lead_middle"));
	public static final RegistryKey<PlacedFeature> oreLeadUpper = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Data.getVersion().getID(), "ore_lead_upper"));
	public static final Feature<CraterFeatureConfig> crater;
	public static void init() {
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreLeadSmall);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreLeadMiddle);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreLeadUpper);
	}
	static {
		crater = Registry.register(Registries.FEATURE, Identifier.of(Data.getVersion().getID(), "crater"), new CraterFeature(CraterFeatureConfig.codec));
	}
}
