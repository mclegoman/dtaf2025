/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.block;

import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.dtaf2025.common.item.ItemRegistry;
import com.mclegoman.dtaf2025.common.sound.SoundRegistry;
import com.mclegoman.dtaf2025.common.util.Tags;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;

public class SpacePortalBaseBlock extends Block {
	public static final MapCodec<SpacePortalBaseBlock> codec = createCodec(SpacePortalBaseBlock::new);
	public static final BooleanProperty active;
	protected MapCodec<SpacePortalBaseBlock> getCodec() {
		return codec;
	}
	public SpacePortalBaseBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(active, false));
	}
	protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (stack.isOf(ItemRegistry.crystal)) {
			if (world.getBlockState(pos.up()).isAir()) {
				world.playSound((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.BLOCKS, 1.0F, world.random.nextFloat() * 0.4F + 0.8F, false);
				world.playSound((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundRegistry.spacePortalAmbient, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.4F + 0.8F, false);
				world.setBlockState(pos.up(), BlockRegistry.spacePortal.getDefaultState());
				world.setBlockState(pos, state.with(active, true));
				stack.decrementUnlessCreative(1, player);
				return ActionResult.SUCCESS;
			} else {
				return ActionResult.FAIL;
			}
		} else return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
	}
	protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, @Nullable WireOrientation wireOrientation, boolean notify) {
		if (state.get(active) && !world.getBlockState(pos.up()).isIn(Tags.Block.spacePortal)) world.setBlockState(pos, state.with(active, false));
		else if (!state.get(active) && world.getBlockState(pos.up()).isIn(Tags.Block.spacePortal)) world.setBlockState(pos, state.with(active, true));
		super.neighborUpdate(state, world, pos, sourceBlock, wireOrientation, notify);
	}
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(active);
	}
	static {
		active = BooleanProperty.of(Data.getVersion().getID() + "_active");
	}
}
