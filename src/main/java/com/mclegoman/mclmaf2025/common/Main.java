package com.mclegoman.mclmaf2025.common;

import com.mclegoman.mclmaf2025.common.data.Data;
import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {
	@Override
	public void onInitialize() {
		Data.init();
	}
}