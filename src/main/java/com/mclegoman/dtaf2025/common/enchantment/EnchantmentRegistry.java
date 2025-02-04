/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.enchantment;

import com.mclegoman.dtaf2025.common.data.Data;
import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.util.function.UnaryOperator;

public class EnchantmentRegistry {
	public static final RegistryKey<Enchantment> spaceBreathing;
	public static final ComponentType<Unit> spaceBreathingEffect;
	public static final RegistryKey<Enchantment> heavyFooted;
	public static final ComponentType<Unit> heavyFootedEffect;
	public static void init() {}
	public static boolean hasSpaceBreathing(LivingEntity entity) {
		MutableBoolean mutableBoolean = new MutableBoolean(false);
		EnchantmentHelper.forEachEnchantment(entity.getEquippedStack(EquipmentSlot.HEAD), (enchantment, level) -> {
			if (enchantment.value().effects().contains(spaceBreathingEffect)) mutableBoolean.setTrue();
		});
		return mutableBoolean.getValue();
	}
	public static boolean hasHeavyFooted(LivingEntity entity) {
		MutableBoolean mutableBoolean = new MutableBoolean(false);
		EnchantmentHelper.forEachEnchantment(entity.getEquippedStack(EquipmentSlot.FEET), (enchantment, level) -> {
			if (enchantment.value().effects().contains(heavyFootedEffect)) mutableBoolean.setTrue();
		});
		return mutableBoolean.getValue();
	}
	private static <T> ComponentType<T> registerType(Identifier id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
		return Registry.register(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, id, builderOperator.apply(ComponentType.builder()).build());
	}
	static {
		spaceBreathing = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(Data.getVersion().getID(), "space_breathing"));
		spaceBreathingEffect = registerType(Identifier.of(Data.getVersion().getID(), "space_breathing"), builder -> builder.codec(Unit.CODEC));
		heavyFooted = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(Data.getVersion().getID(), "heavy_footed"));
		heavyFootedEffect = registerType(Identifier.of(Data.getVersion().getID(), "heavy_footed"), builder -> builder.codec(Unit.CODEC));
	}
}

