/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.easter_egg;

public class EasterEggRegistry {
	private static boolean isSanic;
	public static boolean getSanic() {
		return isSanic;
	}
	public static void setSanic(boolean value) {
		isSanic = value;
	}
	static {
		isSanic = false;
	}
}
