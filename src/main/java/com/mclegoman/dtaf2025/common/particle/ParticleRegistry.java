/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.particle;

import com.mclegoman.dtaf2025.common.data.Data;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ParticleRegistry {
	public static final SimpleParticleType itemMoonSlime;
	public static void init() {
	}
	private static SimpleParticleType register(Identifier identifier, boolean alwaysShow) {
		return Registry.register(Registries.PARTICLE_TYPE, identifier, FabricParticleTypes.simple(alwaysShow));
	}
	static {
		itemMoonSlime = register(Identifier.of(Data.getVersion().getID(), "item_moon_slime"), false);
	}
}
