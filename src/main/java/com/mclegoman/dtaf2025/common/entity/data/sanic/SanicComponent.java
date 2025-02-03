/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.entity.data.sanic;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.nbt.NbtCompound;

public class SanicComponent {
	private final DataTracker dataTracker;
	private final TrackedData<Boolean> dtaf2025_sanic;

	public SanicComponent(DataTracker dataTracker, TrackedData<Boolean> sanic) {
		this.dataTracker = dataTracker;
		this.dtaf2025_sanic = sanic;
	}
	public void writeNbt(NbtCompound nbt) {
		nbt.putBoolean("dtaf2025_Sanic", this.dtaf2025_getSanic());
	}
	public void readNbt(NbtCompound nbt) {
		this.dtaf2025_setSanic(nbt.getBoolean("dtaf2025_Sanic"));
	}
	public void dtaf2025_setSanic(boolean value) {
		this.dataTracker.set(this.dtaf2025_sanic, value);
	}
	public boolean dtaf2025_getSanic() {
		return this.dataTracker.get(this.dtaf2025_sanic);
	}
}

