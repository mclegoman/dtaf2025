/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.util;

import com.mclegoman.dtaf2025.client.keybindings.Keybindings;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class ClientTick {
	public static void init() {
		ClientTickEvents.START_CLIENT_TICK.register((client) -> {
			Keybindings.tick();
		});
	}
}
