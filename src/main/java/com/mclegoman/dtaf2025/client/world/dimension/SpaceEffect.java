/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.world.dimension;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class SpaceEffect extends DimensionEffects {
	public SpaceEffect() {
		super(Float.NaN, true, SkyType.NONE, true, false);
	}
	public Vec3d adjustFogColor(Vec3d color, float sunHeight) {
		return color.multiply(0.0D);
	}
	public boolean useThickFog(int camX, int camY) {
		return false;
	}
	public float[] getFogColorOverride(float skyAngle, float tickDelta) {
		return null;
	}
}