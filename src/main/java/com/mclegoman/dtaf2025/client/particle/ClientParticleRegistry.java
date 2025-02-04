/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.particle;

import com.mclegoman.dtaf2025.common.item.ItemRegistry;
import com.mclegoman.dtaf2025.common.particle.ParticleRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.item.ItemStack;

public class ClientParticleRegistry {
	public static void init() {
		ParticleFactoryRegistry.getInstance().register(ParticleRegistry.itemMoonSlime, new CrackParticleItemFactory(new ItemStack(ItemRegistry.moonSlimeBall)));
	}
}
