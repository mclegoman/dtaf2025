/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.entity;

import com.mclegoman.dtaf2025.common.particle.ParticleRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.conversion.EntityConversionContext;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
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
	protected void initGoals() {
		super.initGoals();
		this.targetSelector.add(1, new ActiveTargetGoal<>(this, SlimeEntity.class, 10, true, false, (target, world) -> Math.abs(target.getY() - this.getY()) <= 4.0));
	}
	public void setSize(int size, boolean heal) {
		int clampedSize = MathHelper.clamp(size, 1, 127);
		this.dataTracker.set(SLIME_SIZE, clampedSize);
		this.refreshPosition();
		this.calculateDimensions();
		int value = clampedSize * 2;
		this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(value * value);
		this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.2F + 0.1F * (float) value);
		this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(value);
		if (heal) this.setHealth(this.getMaxHealth());
		this.experiencePoints = value;
	}
	public void pushAwayFrom(Entity entity) {
		if (entity instanceof SlimeEntity && this.canAttack()) this.damage((LivingEntity)entity);
		super.pushAwayFrom(entity);
	}
	public boolean onKilledOther(ServerWorld world, LivingEntity other) {
		if (other instanceof SlimeEntity slimeEntity) slimeEntity.convertTo(EntityRegistry.moonSlime, EntityConversionContext.create(this, false, false), (slime) -> world.spawnParticles(ParticleTypes.EXPLOSION, this.getX(), this.getBodyY(0.5), this.getZ(), 1, 0.0, 0.0, 0.0, 0.0));
		return super.onKilledOther(world, other);
	}
}
