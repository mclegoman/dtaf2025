/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.particle;

import net.minecraft.client.particle.CrackParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.SimpleParticleType;

public class CrackParticleItemFactory extends CrackParticle.Factory<SimpleParticleType> {
	private final ItemStack itemStack;
	public CrackParticleItemFactory(ItemStack itemStack) {
		this.itemStack = itemStack;
	}
	public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
		return new CrackParticle(clientWorld, d, e, f, this.getItemRenderState(this.itemStack, clientWorld));
	}
}
