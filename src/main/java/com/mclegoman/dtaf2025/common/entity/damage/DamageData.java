/*
    dtaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.entity.damage;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class DamageData {
	private final RegistryKey<DamageType> type;
	private DamageData(RegistryKey<DamageType> type) {
		this.type = type;
	}
	public RegistryKey<DamageType> getType() {
		return this.type;
	}
	public DamageSource getSource(World world) {
		 return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(getType()));
	}
	public static Builder builder(String modId, String name) {
		return new Builder(modId, name);
	}
	public static class Builder {
		private final RegistryKey<DamageType> type;
		public Builder(String modId, String name) {
			this.type = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(modId, name));
		}
		public DamageData build() {
			return new DamageData(type);
		}
	}
}
