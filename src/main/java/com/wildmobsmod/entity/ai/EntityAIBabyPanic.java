package com.wildmobsmod.entity.ai;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class EntityAIBabyPanic extends EntityAIBase
{
    private EntityAgeable theEntityCreature;
    private double speed;
    private double randPosX;
    private double randPosY;
    private double randPosZ;
    private static final String __OBFID = "CL_00001604";

    public EntityAIBabyPanic(EntityAgeable p_i1645_1_, double p_i1645_2_)
    {
        this.theEntityCreature = p_i1645_1_;
        this.speed = p_i1645_2_;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.theEntityCreature.getGrowingAge() >= 0)
        {
            return false;
        }
        else if (this.theEntityCreature.getAITarget() == null && !this.theEntityCreature.isBurning())
        {
            return false;
        }
        else
        {
            Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.theEntityCreature, 5, 4);

            if (vec3 == null)
            {
                return false;
            }
            else
            {
                this.randPosX = vec3.xCoord;
                this.randPosY = vec3.yCoord;
                this.randPosZ = vec3.zCoord;
                return true;
            }
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.theEntityCreature.getNavigator().tryMoveToXYZ(this.randPosX, this.randPosY, this.randPosZ, this.speed);
        
        List list = this.theEntityCreature.worldObj.getEntitiesWithinAABB(this.theEntityCreature.getClass(), AxisAlignedBB.getBoundingBox(this.theEntityCreature.posX, this.theEntityCreature.posY, this.theEntityCreature.posZ, this.theEntityCreature.posX + 1.0D, this.theEntityCreature.posY + 1.0D, this.theEntityCreature.posZ + 1.0D).expand(8.0D, 8.0D, 8.0D));
        Iterator iterator = list.iterator();

        while (iterator.hasNext())
        {
            EntityCreature entitycreature = (EntityCreature)iterator.next();

            if (this.theEntityCreature != entitycreature && entitycreature.getAttackTarget() == null && ((EntityAnimal)entitycreature).getAge() >= 0 && !entitycreature.isOnSameTeam(this.theEntityCreature.getAITarget()) && this.theEntityCreature.worldObj.rand.nextInt(2) == 0)
            {
                entitycreature.setAttackTarget(this.theEntityCreature.getAITarget());
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.theEntityCreature.getNavigator().noPath();
    }
}