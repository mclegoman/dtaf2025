package com.mclegoman.dtaf2025.common.particle;

import com.mclegoman.dtaf2025.common.data.Data;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ParticleRegistry {
	public static final SimpleParticleType itemMoonSlime;
	public static void init() {
	}
	private static SimpleParticleType register(Identifier identifier, boolean alwaysShow) {
		return Registry.register(Registries.PARTICLE_TYPE, identifier, new SimpleParticleType(alwaysShow));
	}
	static {
		itemMoonSlime = register(Identifier.of(Data.version.getID(), "item_moon_slime"), false);
	}
}
