package com.mclegoman.dtaf2025.common.block;

import com.mclegoman.dtaf2025.common.sound.SoundRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class LeadLampBlock extends RedstoneLampBlock {
	public LeadLampBlock(Settings settings) {
		super(settings);
	}
	protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		boolean receivingRedstonePower = world.isReceivingRedstonePower(pos);
		if (state.get(LIT) != receivingRedstonePower) {
			world.setBlockState(pos, state.cycle(LIT), 2);
			if (world.isReceivingRedstonePower(pos)) world.playSound(null, pos, SoundRegistry.leadLampOn, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.4F + 0.8F);
		}
		if (receivingRedstonePower && random.nextInt(100) == 0) world.playSound(null, pos, SoundRegistry.leadLampOn, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.4F + 0.8F);
		world.scheduleBlockTick(pos, this, 2);
	}
}
