/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.item;

import com.mclegoman.dtaf2025.common.util.Tags;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LodestoneTrackerComponent;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.World;

import java.util.Optional;

public class LeadCompassItem extends Item {
	public LeadCompassItem(Item.Settings settings) {
		super(settings);
	}
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if (world instanceof ServerWorld serverWorld) {
			LodestoneTrackerComponent lodestoneTrackerComponent = stack.get(DataComponentTypes.LODESTONE_TRACKER);
			if (lodestoneTrackerComponent == null) {
				stack.set(DataComponentTypes.LODESTONE_TRACKER, new LodestoneTrackerComponent(getLocation(serverWorld, entity.getBlockPos()), true));
			} else {
				LodestoneTrackerComponent lodestoneTrackerComponent2 = lodestoneTrackerComponent.forWorld(serverWorld);
				if (lodestoneTrackerComponent2 != lodestoneTrackerComponent) {
					stack.set(DataComponentTypes.LODESTONE_TRACKER, lodestoneTrackerComponent2);
				}
			}
		}
	}
	private Optional<GlobalPos> getLocation(ServerWorld serverWorld, BlockPos currentPos) {
		BlockPos pos = serverWorld.locateStructure(Tags.Structure.leadLocators, currentPos, 100, false);
		return pos != null ? Optional.of(GlobalPos.create(serverWorld.getRegistryKey(), pos)) : Optional.empty();
	}
}