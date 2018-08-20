package com.wildmobsmod.entity.ai;

import java.util.Iterator;
import java.util.List;

import com.wildmobsmod.entity.monster.faded.EntityFaded;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;

public class EntityAIFollowLeaderFaded extends EntityAIBase
{
    EntityFaded theFaded;
    EntityFaded theLeaderFaded;
    double field_75347_c;
    private int field_75345_d;

    public EntityAIFollowLeaderFaded(EntityFaded p_i1626_1_, double p_i1626_2_)
    {
        this.theFaded = p_i1626_1_;
        this.field_75347_c = p_i1626_2_;
    }
    
    public boolean shouldExecute()
    {
        if (this.theFaded.getIsLeader() == true || this.theFaded.getAttackTarget() != null)
        {
            return false;
        }
        else
        {
            List list = this.theFaded.worldObj.getEntitiesWithinAABB(this.theFaded.getClass(), this.theFaded.boundingBox.expand(8.0D, 4.0D, 8.0D));
            EntityFaded entityfaded = null;
            double d0 = Double.MAX_VALUE;
            Iterator iterator = list.iterator();
            
            while (iterator.hasNext())
            {
                EntityFaded entityfaded1 = (EntityFaded)iterator.next();

                if (entityfaded1.getIsLeader() == true)
                {
                    double d1 = this.theFaded.getDistanceSqToEntity(entityfaded1);

                    if (d1 <= d0)
                    {
                        d0 = d1;
                        entityfaded = entityfaded1;
                    }
                }
            }

            if (entityfaded == null)
            {
                return false;
            }
            else if (d0 < 25.0D)
            {
                return false;
            }
            else
            {
                this.theLeaderFaded = entityfaded;
                return true;
            }
        }
    }
    
    public boolean continueExecuting()
    {
        if (!this.theLeaderFaded.isEntityAlive() || this.theFaded.getAttackTarget() != null)
        {
            return false;
        }
        else
        {
            double d0 = this.theFaded.getDistanceSqToEntity(this.theLeaderFaded);
            return d0 >= 25.0D && d0 <= 256.0D;
        }
    }
    
    public void startExecuting()
    {
        this.field_75345_d = 0;
    }
    
    public void resetTask()
    {
        this.theLeaderFaded = null;
    }
    
    public void updateTask()
    {
    	double d0 = this.theFaded.getDistanceSqToEntity(this.theLeaderFaded);

    	if (--this.field_75345_d <= 0)
    	{
    		this.field_75345_d = 10;
    		this.theFaded.getNavigator().tryMoveToEntityLiving(this.theLeaderFaded, this.field_75347_c);
    	}
    }
}