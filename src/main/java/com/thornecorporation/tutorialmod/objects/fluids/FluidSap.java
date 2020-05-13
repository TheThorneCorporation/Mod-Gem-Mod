package com.thornecorporation.tutorialmod.objects.fluids;

import com.thornecorporation.tutorialmod.events.RegistryEvents;

import com.thornecorporation.tutorialmod.lists.BlockList;
import com.thornecorporation.tutorialmod.lists.FluidList;
import com.thornecorporation.tutorialmod.lists.ItemList;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.FluidAttributes;

public abstract class FluidSap extends FlowingFluid{

	@Override
	public Fluid getStillFluid() {
		//get sap source block
		return FluidList.sap;
	}

	@Override
	public Fluid getFlowingFluid() {
		//get flowing sap fluid
		return FluidList.flowing_sap;
	}

	@Override
	protected boolean canSourcesMultiply() {
		//you cannot make an infinite sap source
		return false;
	}

	@Override
	protected void beforeReplacingBlock(IWorld worldIn, BlockPos pos, BlockState state) {
		// TODO I don't know how to use this yet
		
	}

	@Override
	protected int getSlopeFindDistance(IWorldReader worldIn) {
		// TODO it flows the same slope as water
		return 8;
	}

	@Override
	protected int getLevelDecreasePerBlock(IWorldReader worldIn) {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public Item getFilledBucket() {
		// TODO Auto-generated method stub
		return ItemList.SAP_BUCKET.get();
	}

	@Override
	protected boolean func_215665_a(IFluidState state, IBlockReader world, BlockPos pos,
			Fluid fluid, Direction direction) {
		// TODO Auto-generated method stub
		return direction == Direction.DOWN && !fluid.isIn(FluidList.Tags.SAP);
	}

	@Override
	public int getTickRate(IWorldReader p_205569_1_) {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	protected float getExplosionResistance() {
		// TODO Auto-generated method stub
		return 100.0f;
	}

	@Override
	protected BlockState getBlockState(IFluidState state) {
		// TODO Auto-generated method stub
		return BlockList.SAP.get().getDefaultState().with(FlowingFluidBlock.LEVEL, Integer.valueOf(getLevelFromState(state)));
	}
	
	@Override
	public boolean isEquivalentTo(Fluid fluidIn)
	{
		return fluidIn == FluidList.sap || fluidIn == FluidList.flowing_sap;
	}
	
	@Override
	protected FluidAttributes createAttributes()
	{
		return FluidAttributes
			.builder(RegistryEvents.location("blocks/sap_still"), RegistryEvents.location("blocks/sap_flow"))
			.translationKey("block.thorneislearningmods.sap")
			.build(this);
	}
	
	public static class Source extends FluidSap
	{
		
		
		@Override
		public boolean isSource(IFluidState state) {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public int getLevel(IFluidState state) {
			// TODO Auto-generated method stub
			return 8;
		}
		
		
	}
	
	public static class Flowing extends FluidSap
	{
		@Override
		protected void fillStateContainer(Builder<Fluid, IFluidState> builder)
		{
			super.fillStateContainer(builder);
			builder.add(LEVEL_1_8);
		}
		
		@Override
		public boolean isSource(IFluidState state) {
			return false;
		}

		@Override
		public int getLevel(IFluidState state) {
			// TODO Auto-generated method stub
			return state.get(FluidSap.LEVEL_1_8);
		}
		
	}
}
