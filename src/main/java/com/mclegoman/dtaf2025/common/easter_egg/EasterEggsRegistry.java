/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.easter_egg;

import java.util.ArrayList;
import java.util.List;

public class EasterEggsRegistry {
	private final static List<Long> sanicEasterEggSeeds = new ArrayList<>();
	public static void addSharewareSeed(long seed) {
		if (!sanicEasterEggSeeds.contains(seed)) sanicEasterEggSeeds.add(seed);
	}
	public static void init() {
		addSharewareSeed(78665946);
	}
	// When the world seed is "78665946" which the player can set by inputting "SANIC".
	public static boolean isSanicEasterEgg(long seed) {
		return sanicEasterEggSeeds.contains(seed);
	}
}
