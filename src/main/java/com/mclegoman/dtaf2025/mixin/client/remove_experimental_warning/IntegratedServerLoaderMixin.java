/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin.client.remove_experimental_warning;

import net.minecraft.server.integrated.IntegratedServerLoader;
import net.minecraft.world.level.storage.LevelStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IntegratedServerLoader.class)
public class IntegratedServerLoaderMixin {
	@Inject(method = "showBackupPromptScreen", at = @At("HEAD"), cancellable = true)
	private void dtaf2025$removeBackupPromptScreen(LevelStorage.Session session, boolean customized, Runnable callback, Runnable onCancel, CallbackInfo ci) {
		callback.run();
		ci.cancel();
	}
}