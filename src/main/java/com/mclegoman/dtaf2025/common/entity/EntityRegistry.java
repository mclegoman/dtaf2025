/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.entity;

import com.mclegoman.dtaf2025.common.data.Data;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class EntityRegistry {
	public static final EntityType<MoonSlimeEntity> moonSlime;
	public static void init() {
		FabricDefaultAttributeRegistry.register(moonSlime, MoonSlimeEntity.createAttributes());
	}
	private static <T extends Entity> EntityType<T> register(Identifier id, EntityType.Builder<T> type) {
		RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, id);
		return Registry.register(Registries.ENTITY_TYPE, key, type.build(key));
	}
	static {
		moonSlime = register(Identifier.of(Data.getVersion().getID(), "moon_slime"), EntityType.Builder.create(MoonSlimeEntity::new, SpawnGroup.MONSTER).dimensions(0.52F, 0.52F).eyeHeight(0.325F).spawnBoxScale(4.0F).maxTrackingRange(10));
	}
}
