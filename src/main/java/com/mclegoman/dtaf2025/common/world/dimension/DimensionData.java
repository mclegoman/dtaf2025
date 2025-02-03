/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.world.dimension;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;

public class DimensionData {
	private final RegistryKey<DimensionOptions> options;
	private final RegistryKey<World> world;
	private final RegistryKey<DimensionType> type;
	private final Identifier id;
	private DimensionData(RegistryKey<DimensionOptions> options, RegistryKey<World> world, RegistryKey<DimensionType> type, Identifier id) {
		this.options = options;
		this.world = world;
		this.type = type;
		this.id = id;
	}
	public RegistryKey<DimensionOptions> getOptions() {
		return this.options;
	}
	public RegistryKey<World> getWorld() {
		return this.world;
	}
	public RegistryKey<DimensionType> getType() {
		return this.type;
	}
	public Identifier getId() {
		return this.id;
	}
	public static Builder builder(String modId, String name) {
		return new Builder(modId, name);
	}
	public static class Builder {
		private final RegistryKey<DimensionOptions> options;
		private final RegistryKey<World> world;
		private final RegistryKey<DimensionType> type;
		private final Identifier id;
		public Builder(String modId, String name) {
			this.options = RegistryKey.of(RegistryKeys.DIMENSION, Identifier.of(modId, name));
			this.world = RegistryKey.of(RegistryKeys.WORLD, Identifier.of(modId, name));
			this.type = RegistryKey.of(RegistryKeys.DIMENSION_TYPE, Identifier.of(modId, name));
			this.id = Identifier.of(modId, name);
		}
		public DimensionData build() {
			return new DimensionData(this.options, this.world, this.type, this.id);
		}
	}
}
