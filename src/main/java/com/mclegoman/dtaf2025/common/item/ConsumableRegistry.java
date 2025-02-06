/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.item;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;

public class ConsumableRegistry {
	public static final ConsumableComponent moonSlime;
	static {
		moonSlime = ConsumableComponents.food().consumeSeconds(0.8F).build();
	}
}
