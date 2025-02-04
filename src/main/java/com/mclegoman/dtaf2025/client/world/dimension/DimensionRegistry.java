/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.world.dimension;

import com.mclegoman.dtaf2025.common.data.Data;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class DimensionRegistry {
	public static final DimensionData spaceStation;
	public static final DimensionData theMoon;
	private static final Map<Identifier, DimensionEffects> effectsMap = new HashMap<>();
	public static void init() {
		addEffectType(spaceStation.getId(), new SpaceEffect());
		addEffectType(theMoon.getId(), new SpaceEffect());
	}
	public static void addEffectType(Identifier id, DimensionEffects effects) {
		effectsMap.putIfAbsent(id, effects);
	}
	public static DimensionEffects getEffectType(Identifier id) {
		return effectsMap.getOrDefault(id, null);
	}
	static {
		spaceStation = DimensionData.builder(Data.getVersion().getID(), "space_station").build();
		theMoon = DimensionData.builder(Data.getVersion().getID(), "the_moon").build();
	}
}
