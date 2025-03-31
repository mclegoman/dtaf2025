/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.entity.damage;

import com.mclegoman.dtaf2025.common.data.Data;

public class DamageRegistry {
	public static final DamageData spaceSuffocation;
	public static final DamageData leadPoisoning;
	static {
		spaceSuffocation = DamageData.builder(Data.getVersion().getID(), "space_suffocation").build();
		leadPoisoning = DamageData.builder(Data.getVersion().getID(), "lead_poisoning").build();
	}
}