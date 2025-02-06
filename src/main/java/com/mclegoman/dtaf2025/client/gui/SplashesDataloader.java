/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.gui;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mclegoman.luminance.client.translation.Translation;
import com.mclegoman.luminance.client.util.JsonDataLoader;
import com.mclegoman.luminance.common.util.Couple;
import com.mclegoman.luminance.common.util.LogType;
import com.mclegoman.dtaf2025.common.data.Data;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.profiler.Profiler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Environment(EnvType.CLIENT)
public class SplashesDataloader extends JsonDataLoader implements IdentifiableResourceReloadListener {
	public static final List<Couple<String, Boolean>> registry = new ArrayList<>();
	public static final String id = "splashes";
	private static Couple<String, Boolean> splashText;
	public static Couple<String, Boolean> getSplashText() {
		return splashText;
	}
	public static void randomizeSplashText() {
		if (registry.size() > 1) {
			List<Couple<String, Boolean>> splashes = new ArrayList<>(registry);
			if (getSplashText() != null) splashes.remove(getSplashText());
			splashText = splashes.get(new Random().nextInt(splashes.size()));
		} else {
			if (registry.size() == 1) splashText = registry.getFirst();
			else splashText = new Couple<>("", false);
		}
	}
	public SplashesDataloader() {
		super(new Gson(), id);
	}
	private void add(String text, Boolean translatable) {
		try {
			Couple<String, Boolean> splash = new Couple<>(text, translatable);
			if (!registry.contains(splash)) registry.add(splash);
		} catch (Exception error) {
			Data.getVersion().sendToLog(LogType.ERROR, Translation.getString("Failed to add splash text to registry: {}", error));
		}
	}
	private void reset() {
		try {
			registry.clear();
		} catch (Exception error) {
			Data.getVersion().sendToLog(LogType.ERROR, Translation.getString("Failed to reset splash text registry: {}", error));
		}
	}
	@Override
	public void apply(Map<Identifier, JsonElement> prepared, ResourceManager manager, Profiler profiler) {
		try {
			reset();
			for (Resource resource : manager.getAllResources(Identifier.of(Data.getVersion().getID(), id + ".json"))) {
				JsonObject reader = JsonHelper.deserialize(resource.getReader());
				if (JsonHelper.getBoolean(reader, "replace", false)) reset();
				JsonArray translatableTexts = JsonHelper.getArray(reader, "translatable");
				for (JsonElement splashText : translatableTexts) add(splashText.getAsString(), true);
				JsonArray literalTexts = JsonHelper.getArray(reader, "literal");
				for (JsonElement splashText : literalTexts) add(splashText.getAsString(), false);
			}
			randomizeSplashText();
		} catch (Exception error) {
			Data.getVersion().sendToLog(LogType.ERROR, Translation.getString("Failed to load splash text from dataloader: {}", error));
		}
	}
	@Override
	public Identifier getFabricId() {
		return Identifier.of(Data.getVersion().getID(), id);
	}
}