/*
    mclmaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclmaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclmaf2025.common.entity.data;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.nbt.NbtCompound;

public class SanicComponent {
	private final DataTracker dataTracker;
	private final TrackedData<Boolean> sanic;

	public SanicComponent(DataTracker dataTracker, TrackedData<Boolean> sanic) {
		this.dataTracker = dataTracker;
		this.sanic = sanic;
	}
	public void writeNbt(NbtCompound nbt) {
		nbt.putBoolean("Sanic", this.getSanic());
	}
	public void readNbt(NbtCompound nbt) {
		this.setSanic(nbt.getBoolean("Sanic"));
	}
	public void setSanic(boolean value) {
		this.dataTracker.set(this.sanic, value);
	}
	public boolean getSanic() {
		return this.dataTracker.get(this.sanic);
	}
}

