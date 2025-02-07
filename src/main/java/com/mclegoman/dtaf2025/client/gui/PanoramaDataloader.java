/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.gui;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mclegoman.dtaf2025.client.data.ClientData;
import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.luminance.client.util.JsonDataLoader;
import com.mclegoman.luminance.common.util.Couple;
import com.mclegoman.luminance.common.util.LogType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.profiler.Profiler;

import java.util.*;
import java.util.stream.IntStream;

@Environment(EnvType.CLIENT)
public class PanoramaDataloader extends JsonDataLoader implements IdentifiableResourceReloadListener {
	public static final Map<Identifier, Couple<Identifier, Boolean>> registry = new HashMap<>();
	private static Couple<Identifier, Boolean> currentPanorama;
	public static Couple<Identifier, Boolean> getPanorama() {
		return currentPanorama != null ? currentPanorama : getDefaultPanorama();
	}
	private static Couple<List<Identifier>, Identifier> getPanoramaAssets(Identifier identifier) {
		List<Identifier> assets = new ArrayList<>();
		IntStream.range(0, 6).forEach((face) -> assets.add(identifier.withPath(identifier.getPath() + "/panorama_" + face + ".png")));
		Identifier overlayTexture = identifier.withPath(identifier.getPath() + "/panorama_overlay" + ".png");
		return new Couple<>(assets, overlayTexture);
	}
	public static void randomizePanorama() {
		List<Couple<Identifier, Boolean>> panoramas = new ArrayList<>(registry.values());
		currentPanorama = !panoramas.isEmpty() ? panoramas.get(new Random().nextInt(panoramas.size())) : getDefaultPanorama();
	}
	public static final String id = "panorama";
	public PanoramaDataloader() {
		super(new Gson(), id);
	}
	@Override
	public void apply(Map<Identifier, JsonElement> prepared, ResourceManager manager, Profiler profiler) {
		registry.clear();
		prepared.forEach((identifier, jsonElement) -> {
			Identifier assets = Identifier.of(JsonHelper.getString(jsonElement.getAsJsonObject(), "asset_id"));
			assets = assets.withPath("textures/" + assets.getPath());
			boolean validPanorama = true;
			for (Identifier asset : getPanoramaAssets(assets).getFirst()) {
				if (ClientData.client.getResourceManager().getResource(asset).isEmpty()) {
					validPanorama = false;
					Data.getVersion().sendToLog(LogType.WARN, "Could not find asset '" + asset + "' for panorama with id '" + identifier + "', ignoring panorama!");
					break;
				}
			}
			if (validPanorama) registry.put(identifier, new Couple<>(assets, ClientData.client.getResourceManager().getResource(getPanoramaAssets(assets).getSecond()).isPresent()));
		});
		if (registry.isEmpty()) registry.put(Identifier.ofVanilla("background"), getDefaultPanorama());
		randomizePanorama();
		TitleScreenHelper.updateCubeMapRenderer();
	}
	private static Couple<Identifier, Boolean> getDefaultPanorama() {
		return new Couple<>(Identifier.of("textures/gui/title/background"), true);
	}
	@Override
	public Identifier getFabricId() {
		return Identifier.of(Data.getVersion().getID(), id);
	}
}