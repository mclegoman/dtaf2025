/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.sound;

import com.mclegoman.dtaf2025.common.data.Data;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundRegistry {
	public static final SoundEvent lockedChestStore;
	public static final SoundEvent spacePortalAmbient;
	public static final SoundEvent recordBrightDay;
	public static final RegistryKey<JukeboxSong> jukeboxBrightDay;
	public static RegistryEntry.Reference<SoundEvent> musicSpace;
	public static MusicSound musicMenu;
	public static void init() {
	}
	private static SoundEvent registerSoundEvent(Identifier identifier) {
		return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
	}
	private static RegistryEntry.Reference<SoundEvent> registerSoundEventReference(Identifier identifier) {
		return Registry.registerReference(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
	}
	static {
		lockedChestStore = registerSoundEvent(Identifier.of(Data.getVersion().getID(), "locked_chest_store"));
		spacePortalAmbient = registerSoundEvent(Identifier.of(Data.getVersion().getID(), "space_portal_ambient"));
		recordBrightDay = registerSoundEvent(Identifier.of(Data.getVersion().getID(), "music_disc.bright_day"));
		jukeboxBrightDay = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(Data.getVersion().getID(), "bright_day"));
		musicSpace = registerSoundEventReference(Identifier.of(Data.getVersion().getID(), "music.space"));
		musicMenu = new MusicSound(musicSpace, 20, 600, true);
	}
}
