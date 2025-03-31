/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.item;

import com.mclegoman.dtaf2025.common.effects.EffectRegistry;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

import java.util.List;

public class FoodRegistry {
	public static class Food {
		public static final FoodComponent moonSlime;
		public static final FoodComponent lead;
		static {
			moonSlime = new FoodComponent.Builder().nutrition(2).saturationModifier(0.3F).build();
			lead = new FoodComponent.Builder().nutrition(-20).saturationModifier(-20.0F).alwaysEdible().build();
		}
	}
	public static class Consumable {
		public static final ConsumableComponent moonSlime;
		public static final ConsumableComponent lead;
		static {
			lead = ConsumableComponents.food().consumeEffect(new ApplyEffectsConsumeEffect(List.of(new StatusEffectInstance(EffectRegistry.leadPoisoning, 1, 0)))).consumeSeconds(0.0F).build();
			moonSlime = ConsumableComponents.food().consumeSeconds(0.8F).build();
		}
	}
}
