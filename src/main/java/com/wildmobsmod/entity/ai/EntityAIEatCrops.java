package com.wildmobsmod.entity.ai;

import com.wildmobsmod.entity.passive.mouse.EntityMouse;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAIEatCrops extends EntityAIBase
{
	private EntityMouse field_151500_b;
	private World field_151501_c;
	private static final String __OBFID = "CL_00001582";

	public EntityAIEatCrops(EntityMouse p_i45314_1_)
	{
		this.field_151500_b = p_i45314_1_;
		this.field_151501_c = p_i45314_1_.worldObj;
		this.setMutexBits(7);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute()
	{
		if (this.field_151500_b.getRNG().nextInt(25) != 0)
		{
			return false;
		}
		else
		{
			if (this.field_151500_b.hunger <= 0)
			{
				int i = MathHelper.floor_double(this.field_151500_b.posX);
				int j = MathHelper.floor_double(this.field_151500_b.posY);
				int k = MathHelper.floor_double(this.field_151500_b.posZ);
				return this.field_151501_c.getBlock(i, j - 1, k) == Blocks.farmland;
			}
			else
			{
				return false;
			}
		}
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting()
	{
		this.field_151501_c.setEntityState(this.field_151500_b, (byte)10);
		this.field_151500_b.getNavigator().clearPathEntity();
	}

	/**
	 * Resets the task
	 */
	public void resetTask()
	{
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting()
	{
		return false;
	}

	/**
	 * Updates the task
	 */
	public void updateTask()
	{
		int i = MathHelper.floor_double(this.field_151500_b.posX);
		int j = MathHelper.floor_double(this.field_151500_b.posY);
		int k = MathHelper.floor_double(this.field_151500_b.posZ);

		if (this.field_151501_c.getBlock(i, j - 1, k) == Blocks.farmland && this.field_151501_c.getBlock(i, j, k) instanceof IGrowable)
		{
			if (this.field_151501_c.getGameRules().getGameRuleBooleanValue("mobGriefing"))
			{
				this.field_151501_c.playAuxSFX(2001, i, j , k, Block.getIdFromBlock(Blocks.grass));
				this.field_151501_c.setBlock(i, j, k, Blocks.air, 0, 2);
				this.field_151501_c.setBlock(i, j - 1, k, Blocks.dirt, 0, 2);
				this.field_151500_b.setMateCounter(((EntityMouse) this.field_151500_b).getMateCounter() + 1);
				this.field_151500_b.hunger = 200;
			}
		}
	}
}