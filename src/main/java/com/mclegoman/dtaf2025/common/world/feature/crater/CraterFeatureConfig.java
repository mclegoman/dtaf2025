/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.world.feature.crater;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;

public record CraterFeatureConfig(IntProvider radius, IntProvider depth) implements FeatureConfig {
	public static final Codec<CraterFeatureConfig> codec = RecordCodecBuilder.create((instance) -> instance.group(IntProvider.VALUE_CODEC.fieldOf("radius").forGetter(CraterFeatureConfig::radius), IntProvider.VALUE_CODEC.fieldOf("depth").forGetter(CraterFeatureConfig::depth)).apply(instance, CraterFeatureConfig::new));
	public CraterFeatureConfig(IntProvider radius, IntProvider depth) {
		this.radius = radius;
		this.depth = depth;
	}
	public IntProvider radius() {
		return this.radius;
	}
	public IntProvider depth() {
		return this.depth;
	}
}