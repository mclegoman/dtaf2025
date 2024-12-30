/*
    dtaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.world.dimension;

import com.mclegoman.dtaf2025.common.data.Data;

public class DimensionRegistry {
	public static final DimensionData spaceStation;
	static {
		spaceStation = DimensionData.builder(Data.version.getID(), "space_station").build();
	}
}
