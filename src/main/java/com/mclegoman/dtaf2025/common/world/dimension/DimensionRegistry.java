/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.world.dimension;

import com.mclegoman.dtaf2025.client.world.dimension.DimensionData;
import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.dtaf2025.common.world.StateRegistry;
import com.mclegoman.dtaf2025.common.world.structure.StructurePlacer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class DimensionRegistry {
	public static final List<Identifier> spaceIds;
	public static final DimensionData spaceStation;
	public static final DimensionData theMoon;
	public static void init() {
		addSpaceIds();
		// Generates the space station after server started if the structure isn't already generated.
		ServerLifecycleEvents.SERVER_STARTED.register((server) -> server.execute(() -> {
			StateRegistry.SpaceStationState state = (StateRegistry.SpaceStationState)StateRegistry.getState(StateRegistry.StateType.spaceStation, server);
			if (!state.hasGeneratedSpaceStation) {
				StructurePlacer placer = new StructurePlacer(server.getWorld(DimensionRegistry.spaceStation.getWorld()), Identifier.of(Data.getVersion().getID(), "space_station"), new BlockPos(0, 64, 0));
				placer.generate();
				state.hasGeneratedSpaceStation = true;
			}
		}));
	}
	private static void addSpaceIds() {
		spaceIds.add(spaceStation.getId());
		spaceIds.add(theMoon.getId());
	}
	static {
		spaceIds = new ArrayList<>();
		spaceStation = DimensionData.builder(Data.getVersion().getID(), "space_station").build();
		theMoon = DimensionData.builder(Data.getVersion().getID(), "the_moon").build();
	}
}
