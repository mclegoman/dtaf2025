/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import com.mclegoman.dtaf2025.client.compatibility.Compatibility;
import com.mclegoman.dtaf2025.common.data.Data;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class MixinPlugin implements IMixinConfigPlugin {
	@Override
	public void onLoad(String mixinPackage) {
		MixinExtrasBootstrap.init();
	}
	@Override
	public String getRefMapperConfig() {
		return null;
	}
	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		if (mixinClassName.equals("com.mclegoman.dtaf2025.mixin.client.gui.VersionOverlay")) return Data.getVersion().isDevelopmentBuild();
		else if (mixinClassName.equals("com.mclegoman.dtaf2025.mixin.client.compatibility.ModMenuConfigMixin")) return Compatibility.isModMenuInstalled();
		return true;
	}
	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
	}
	@Override
	public List<String> getMixins() {
		return null;
	}
	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
	}
	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
	}
}