/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.config;

import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.luminance.config.LuminanceConfigHelper;
import org.quiltmc.config.api.ReflectiveConfig;
import org.quiltmc.config.api.annotations.Comment;
import org.quiltmc.config.api.annotations.IntegerRange;
import org.quiltmc.config.api.annotations.SerializedName;
import org.quiltmc.config.api.values.TrackedValue;

public class AprilFoolsConfig extends ReflectiveConfig {
	// For more info on Quilt Config, see https://wiki.quiltmc.org/en/configuration/getting-started.
	public static final AprilFoolsConfig config;
	@Comment("Sets the dtaf2025_bloomStrength dynamic uniform (int)0-100%, outputs 0.0F-1.0F.")
	@IntegerRange(min = 0, max = 100)
	@SerializedName("bloom_strength")
	public final TrackedValue<Integer> bloomStrength = this.value(40);
	@Comment("Sets the dtaf2025_easterEggStrength dynamic uniform (int)0-100%, outputs 0.0F-1.0F.")
	@IntegerRange(min = 0, max = 100)
	@SerializedName("easter_egg_strength")
	public final TrackedValue<Integer> easterEggStrength = this.value(100);
	@Comment("Sets whether Steve Co. Supply Crates open the Minecraft Shop.")
	@SerializedName("can_supply_crates_open_shop")
	public final TrackedValue<Boolean> canSupplyCratesOpenShop = this.value(true);
	@Comment("Sets whether the photosensitivity warning is shown on startup.")
	@SerializedName("show_photosensitivity_warning")
	public final TrackedValue<Boolean> showPhotosensitivityWarningOnStartup = this.value(true);
	public static void init() {
	}
	static {
		config = LuminanceConfigHelper.register(LuminanceConfigHelper.SerializerType.PROPERTIES, "", Data.getVersion().getID(), AprilFoolsConfig.class);
	}
}
