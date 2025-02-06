/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.item;

import net.minecraft.component.type.FoodComponent;

public class FoodRegistry {
	public static final FoodComponent moonSlime;
	static {
		moonSlime = (new FoodComponent.Builder()).nutrition(2).saturationModifier(0.3F).build();
	}
}
