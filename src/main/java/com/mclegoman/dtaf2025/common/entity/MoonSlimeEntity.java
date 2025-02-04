package com.mclegoman.dtaf2025.common.entity;

import com.mclegoman.dtaf2025.common.particle.ParticleRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.world.World;

public class MoonSlimeEntity extends SlimeEntity {
	public MoonSlimeEntity(EntityType<? extends SlimeEntity> entityType, World world) {
		super(entityType, world);
	}
	public static DefaultAttributeContainer.Builder createAttributes() {
		return MobEntity.createMobAttributes().add(EntityAttributes.MAX_HEALTH).add(EntityAttributes.MOVEMENT_SPEED).add(EntityAttributes.ATTACK_DAMAGE);
	}
	protected ParticleEffect getParticles() {
		return ParticleRegistry.itemMoonSlime;
	}
}
