/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.effects;

import com.mclegoman.dtaf2025.common.data.Data;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class EffectRegistry {
	public static final RegistryEntry<StatusEffect> leadPoisoning;
	public static void init() {
	}
	private static RegistryEntry<StatusEffect> register(Identifier identifier, StatusEffect statusEffect) {
		return Registry.registerReference(Registries.STATUS_EFFECT, identifier, statusEffect);
	}
	static {
		leadPoisoning = register(Identifier.of(Data.getVersion().getID(), "lead_poisoning"), new LeadPoisoningStatusEffect(StatusEffectCategory.HARMFUL, 0x131011));
	}
}
