package com.wildmobsmod.entity.ai;

import com.wildmobsmod.entity.monster.faded.EntityFaded;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;

public class EntityAIFadedNearestAttackableTarget extends EntityAINearestAttackableTarget
{
	public EntityAIFadedNearestAttackableTarget(EntityCreature p_i1663_1_, Class p_i1663_2_, int p_i1663_3_, boolean p_i1663_4_) {
		super(p_i1663_1_, p_i1663_2_, p_i1663_3_, p_i1663_4_);
	}

	public boolean shouldExecute()
	{
		return (((EntityFaded)this.taskOwner).getEquipmentInSlot(0) == null || ((EntityFaded)this.taskOwner).getHandEquipment() == null) && super.shouldExecute();
	}
}