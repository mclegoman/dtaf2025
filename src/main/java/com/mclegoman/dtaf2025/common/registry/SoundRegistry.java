/*
    dtaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.registry;

import com.mclegoman.dtaf2025.common.data.Data;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundRegistry {
	public static final SoundEvent lockedChestStore;
	public static void init() {
	}
	static {
		lockedChestStore = Registry.register(Registries.SOUND_EVENT, Identifier.of(Data.version.getID(), "locked_chest_store"), SoundEvent.of(Identifier.of(Data.version.getID(), "locked_chest_store")));
	}
}
