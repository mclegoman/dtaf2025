/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.entity.data.air;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.nbt.NbtCompound;

public class AirComponent {
	private final DataTracker dataTracker;
	private final TrackedData<Integer> dtaf2025_air;

	public AirComponent(DataTracker dataTracker, TrackedData<Integer> air) {
		this.dataTracker = dataTracker;
		this.dtaf2025_air = air;
	}
	public void writeNbt(NbtCompound nbt) {
		nbt.putInt("dtaf2025_air", this.dtaf2025_getAir());
	}
	public void readNbt(NbtCompound nbt) {
		this.dtaf2025_setAir(nbt.getInt("dtaf2025_air"));
	}
	public void dtaf2025_setAir(int value) {
		this.dataTracker.set(this.dtaf2025_air, value);
	}
	public int dtaf2025_getAir() {
		return this.dataTracker.get(this.dtaf2025_air);
	}
}

