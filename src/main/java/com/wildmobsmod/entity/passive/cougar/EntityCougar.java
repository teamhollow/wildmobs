package com.wildmobsmod.entity.passive.cougar;

import java.util.Iterator;
import java.util.List;

import com.wildmobsmod.entity.passive.deer.EntityDeer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityCougar extends EntityAnimal
{
	//
	// For some reason the mob won't register and trying to spawn it crashed the game. Adding more "big cats" is recommended and I've talked about the big cats more in depth on my Discord server.
	//
	
    private int angerTimer;
    
	public EntityCougar(World par1World)
	{
		super(par1World);
		int j = 0;
    	if (this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
    	{
    		j = (int) (120 * 1.8);
    	}
    	else if (this.worldObj.difficultySetting == EnumDifficulty.EASY)
    	{
    		j = (int) (120 * 1.4);
    	}
    	else if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
    	{
    		j = 120;
    	}
    	else if (this.worldObj.difficultySetting == EnumDifficulty.HARD)
    	{
    		j = (int) (120 * 0.6);
    	}
		this.setSize(0.9F, 1.2F);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAILeapAtTarget(this, 0.5F));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.5D, true));
        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 120, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityDeer.class, 300, true));
	}
	
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

	public boolean isAIEnabled()
	{
		return true;
	}
	
    public void updateAITick()
    {
        if (this.getMoveHelper().isUpdating())
        {
            double d0 = this.getMoveHelper().getSpeed();
      
            if (d0 == 1.35D)
            {
            	this.setSneaking(false);
                this.setSprinting(true);
            }
            else
            {
            	this.setSneaking(false);
                this.setSprinting(false);
            }
        	this.angerTimer = 0;
        }
        else
        {
        	if (this.getAttackTarget() != null && !this.isJumping)
        	{
            	this.angerTimer++;
        		if (this.angerTimer > 30)
        		{
                	this.setSneaking(true);    			
        		}
        		else
        		{
            	    this.setSneaking(false);
        		}
        		if (this.angerTimer == 32)
        		{
                	this.angerTimer = 31;		
        		}
        	}
        	else
        	{
            	this.setSneaking(false);
            	this.angerTimer = 0;
        	}
            this.setSprinting(false);
        }
        super.updateAITick();
    }
	
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
	}

    protected void fall(float p_70069_1_) {}
    
	protected void entityInit()
	{
		this.dataWatcher.addObject(21, new Integer(0));
	}

	public void readEntityFromNBT(NBTTagCompound entity)
	{
        entity.setInteger("Anger", this.getAnger());
	}

	public void writeEntityToNBT(NBTTagCompound entity)
	{
        this.setAnger(entity.getInteger("Anger"));
	}
	
    public int getAnger()
    {
        return this.dataWatcher.getWatchableObjectInt(21);
    }

    public void setAnger(int entity)
    {
        this.dataWatcher.updateObject(21, Integer.valueOf((entity)));
    }

    public boolean attackEntityAsMob(Entity p_70652_1_)
    {
    	this.angerTimer = 0;
	    return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 4.0F);
    }
    
    public float getEyeHeight()
    {
        return this.height * 1.0F;
    }
    
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.isEntityInvulnerable())
        {
        	this.angerTimer = 0;
            return false;
        }
        else
        {
        	this.angerTimer = 0;
            return super.attackEntityFrom(p_70097_1_, p_70097_2_);
        }
    }
    
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
        List list = this.worldObj.getEntitiesWithinAABB(this.getClass(), AxisAlignedBB.getBoundingBox(this.posX, this.posY, this.posZ, this.posX + 1.0D, this.posY + 1.0D, this.posZ + 1.0D).expand(10.0D, 10.0D, 10.0D));
        Iterator iterator = list.iterator();

        while (iterator.hasNext())
        {
            Entity entity = (Entity)iterator.next();

            if (entity != null)
            {
            	if (entity instanceof EntityPlayer)
            	{
            		if (this.getAnger() < 240)
            		{
            			int i = this.getAnger();
            			i++;
            			this.setAnger(i);
            		}
            	}
            	else
            	{
            		this.setAnger(0);
            	}
            }
        	else
        	{
        		this.setAnger(0);
        	}
        }
    }
    
    protected int getExperiencePoints(EntityPlayer p_70693_1_)
    {
        return 5;
    }
    
    protected String getLivingSound()
    {
        return this.getAttackTarget() != null ? "wildmobsmod:mob.cougar.angry" : null;
    }
    
    protected String getHurtSound()
    {
        return "wildmobsmod:mob.cougar.hurt";
    }
    
    protected String getDeathSound()
    {
        return "wildmobsmod:mob.cougar.hurt";
    }
    
    protected float getSoundVolume()
    {
        return 0.4F;
    }

	@Override
    public EntityCougar createChild(EntityAgeable p_90011_1_)
    {
        return null;
    }

}
