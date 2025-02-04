/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.painting;

import com.mclegoman.dtaf2025.common.data.Data;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class PaintingRegistry {
	public static final RegistryKey<PaintingVariant> moon;
	public static void init() {}
	static {
		moon = RegistryKey.of(RegistryKeys.PAINTING_VARIANT, Identifier.of(Data.getVersion().getID(), "moon"));
	}
}

