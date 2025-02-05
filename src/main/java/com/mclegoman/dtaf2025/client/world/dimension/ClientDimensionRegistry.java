/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.world.dimension;

import com.mclegoman.dtaf2025.common.world.dimension.DimensionRegistry;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class ClientDimensionRegistry {
	private static final Map<Identifier, DimensionEffects> effectsMap = new HashMap<>();
	public static void init() {
		addEffectType(DimensionRegistry.spaceStation.getId(), new SpaceEffect());
		addEffectType(DimensionRegistry.theMoon.getId(), new SpaceEffect());
	}
	public static void addEffectType(Identifier id, DimensionEffects effects) {
		effectsMap.putIfAbsent(id, effects);
	}
	public static DimensionEffects getEffectType(Identifier id) {
		return effectsMap.getOrDefault(id, null);
	}
}
