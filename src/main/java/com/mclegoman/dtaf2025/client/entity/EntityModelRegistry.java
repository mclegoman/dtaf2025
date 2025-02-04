package com.mclegoman.dtaf2025.client.entity;

import com.mclegoman.dtaf2025.client.entity.renderer.MoonSlimeEntityRenderer;
import com.mclegoman.dtaf2025.common.entity.EntityRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class EntityModelRegistry {
	//public static final EntityModelLayer moonSlime;
	//public static final EntityModelLayer moonSlimeOuter;
	public static void init() {
		EntityRendererRegistry.register(EntityRegistry.moonSlime, MoonSlimeEntityRenderer::new);
		//EntityModelLayerRegistry.registerModelLayer(moonSlime, SlimeEntityModel::getInnerTexturedModelData);
		//EntityModelLayerRegistry.registerModelLayer(moonSlimeOuter, SlimeEntityModel::getOuterTexturedModelData);
	}
	static {
		//moonSlime = new EntityModelLayer(Identifier.of(Data.getVersion().getID(), "moon_slime"), "main");
		//moonSlimeOuter = new EntityModelLayer(Identifier.of(Data.getVersion().getID(), "moon_slime"), "outer");
	}
}
