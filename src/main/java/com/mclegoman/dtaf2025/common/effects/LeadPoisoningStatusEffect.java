/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.effects;

import com.mclegoman.dtaf2025.common.entity.damage.DamageRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

public class LeadPoisoningStatusEffect extends StatusEffect {
    protected LeadPoisoningStatusEffect(StatusEffectCategory statusEffectCategory, int i) {
        super(statusEffectCategory, i);
    }
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        entity.damage(world, DamageRegistry.leadPoisoning.getSource(world), entity.getHealth());
        return true;
    }
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}