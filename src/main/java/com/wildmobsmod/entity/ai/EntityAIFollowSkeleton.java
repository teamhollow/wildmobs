package com.wildmobsmod.entity.ai;

import java.util.Iterator;
import java.util.List;

import com.wildmobsmod.entity.monster.skeleton.EntityWMSkeleton;
import com.wildmobsmod.entity.monster.skeletonwolf.EntitySkeletonWolf;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;

public class EntityAIFollowSkeleton extends EntityAIBase
{
    /** The child that is following its parent. */
    EntitySkeletonWolf theMob;
    Entity targetMob;
    double field_75347_c;
    private int field_75345_d;
    private static final String __OBFID = "CL_00001586";

    public EntityAIFollowSkeleton(EntitySkeletonWolf p_i1626_1_, double p_i1626_2_)
    {
        this.theMob = p_i1626_1_;
        this.field_75347_c = p_i1626_2_;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	Entity skeleton = null;
    	double d0 = Double.MAX_VALUE;

    	if (this.theMob.entityToFollow != null)
    	{
    		double d1 = this.theMob.getDistanceSqToEntity(theMob.entityToFollow);

    		if (d1 <= d0)
    		{
    			d0 = d1;
    			skeleton = theMob.entityToFollow;
    		}

    		if (skeleton == null)
    		{
    			return false;
    		}
    		else if (d0 < 25.0D)
    		{
    			return false;
    		}
    		else
    		{
    			return true;
    		}
    	}
    	else
    	{
    		return false;
    	}
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        if (theMob.entityToFollow == null)
        {
        	return false;
        }
        else
        {
            double d0 = this.theMob.getDistanceSqToEntity(theMob.entityToFollow);
            if (d0 <= 256.0D)
            {
            	this.theMob.entityToFollow = null;
            }
            return d0 >= 25.0D && d0 <= 256.0D;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.field_75345_d = 0;
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.targetMob = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
    	if (--this.field_75345_d <= 0)
    	{
    		this.field_75345_d = 10;
    		if (this.theMob.entityToFollow != null)
    		{
    			this.theMob.getNavigator().tryMoveToEntityLiving(theMob.entityToFollow, this.field_75347_c);
    		}
    	}
    }
}