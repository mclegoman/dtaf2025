/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin.client.compatibility;

import com.terraformersmc.modmenu.config.ModMenuConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = ModMenuConfig.class, remap = false)
public abstract class ModMenuConfigMixin {
	@ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/terraformersmc/modmenu/config/option/EnumConfigOption;<init>(Ljava/lang/String;Ljava/lang/Enum;)V"))
	private static void dtaf2025$updateDefaultConfig(Args args) {
		// If mod menu is installed, we replace the default value of mods_button_style, to replace realms.
		if (args.get(0).equals("mods_button_style")) args.set(1, ModMenuConfig.TitleMenuButtonStyle.REPLACE_REALMS);
	}
}