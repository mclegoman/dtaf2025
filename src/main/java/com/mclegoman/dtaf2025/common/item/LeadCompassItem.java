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
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
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
			if (serverWorld.getRegistryKey() == World.OVERWORLD && stack.get(DataComponentTypes.LODESTONE_TRACKER) == null) {
				stack.set(DataComponentTypes.LODESTONE_TRACKER, new LodestoneTrackerComponent(getLocation(serverWorld, entity.getBlockPos()), false));
			}
		}
	}
	private Optional<GlobalPos> getLocation(ServerWorld serverWorld, BlockPos currentPos) {
		BlockPos pos = serverWorld.locateStructure(Tags.Structure.leadLocators, currentPos, 100, false);
		return pos != null ? Optional.of(GlobalPos.create(serverWorld.getRegistryKey(), pos)) : Optional.empty();
	}
	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		if (world instanceof ServerWorld serverWorld) {
			if (serverWorld.getRegistryKey() == World.OVERWORLD) {
				ItemStack stack = hand.equals(Hand.MAIN_HAND) ? user.getEquippedStack(EquipmentSlot.MAINHAND) : user.getEquippedStack(EquipmentSlot.OFFHAND);
				Optional<GlobalPos> pos = getLocation(serverWorld, user.getBlockPos());
				stack.set(DataComponentTypes.LODESTONE_TRACKER, new LodestoneTrackerComponent(pos, false));
				return ActionResult.SUCCESS;
			} else {
				return ActionResult.FAIL;
			}
		}
		return ActionResult.PASS;
	}
	public LodestoneTrackerComponent check(LodestoneTrackerComponent component, ServerWorld world) {
		if (component.tracked() && component.target().isPresent()) {
			return component.target().get().dimension() != world.getRegistryKey() ? component : new LodestoneTrackerComponent(Optional.empty(), true);
		} else {
			return component;
		}
	}
}