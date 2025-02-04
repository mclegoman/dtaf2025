/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.data;

import com.mclegoman.luminance.api.mod.ModContainer;
import com.mclegoman.luminance.api.mod.ModHelper;
import com.mclegoman.luminance.common.util.Version;

import java.util.Optional;

public class Data extends com.mclegoman.luminance.common.data.Data {
	private static Version version;
	public static Version getVersion() {
		if (version == null) {
			Optional<ModContainer> modContainer = ModHelper.getModContainer("dtaf2025");
			modContainer.ifPresent((container) -> {
				version = Version.parse(container.metadata());
			});
		}
		if (version != null) return version;
		else throw new NullPointerException("dtaf2025 version couldn't be obtained!");
	}
}