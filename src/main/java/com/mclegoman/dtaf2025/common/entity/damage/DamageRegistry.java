/*
    dtaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.entity.damage;

import com.mclegoman.dtaf2025.common.data.Data;

public class DamageRegistry {
	public static final DamageData spaceSuffocation;
	static {
		spaceSuffocation = DamageData.builder(Data.version.getID(), "space_suffocation").build();
	}
}

