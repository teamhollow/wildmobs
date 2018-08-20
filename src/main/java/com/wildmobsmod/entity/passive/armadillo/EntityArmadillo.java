package com.wildmobsmod.entity.passive.armadillo;

import com.wildmobsmod.items.WildMobsModItems;
import com.wildmobsmod.main.MainRegistry;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;

public class EntityArmadillo extends EntityAnimal
{
	@Override
	protected void entityInit() {
		super.entityInit();
        this.dataWatcher.addObject(20, Byte.valueOf((byte)0));
	}
    public void writeEntityToNBT(NBTTagCompound entity)
    {
        super.writeEntityToNBT(entity);
        entity.setInteger("BallTimer", this.getBallTimer());
    }

    public void readEntityFromNBT(NBTTagCompound entity)
    {
        super.readEntityFromNBT(entity);
        this.setBallTimer(entity.getInteger("BallTimer"));
    }

    public int getBallTimer()
    {
        return this.dataWatcher.getWatchableObjectByte(20);
    }

    public void setBallTimer(int entity)
    {
        this.dataWatcher.updateObject(20, Byte.valueOf((byte)entity));
    }
	
	public EntityArmadillo(World par1World) {
		super(par1World);
		this.setSize(0.4F, 0.5F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMate(this, 1.0D));
		if (MainRegistry.enableButterfly == true)
		{
			this.tasks.addTask(2, new EntityAITempt(this, 1.25D, WildMobsModItems.butterfly, false));
		}
		if (MainRegistry.enableDragonfly == true)
		{
			this.tasks.addTask(2, new EntityAITempt(this, 1.25D, WildMobsModItems.dragonfly, false));
		}
		if (MainRegistry.enableButterfly == false && MainRegistry.enableDragonfly == false)
		{
			this.tasks.addTask(2, new EntityAITempt(this, 1.25D, Items.wheat_seeds, false));
			this.tasks.addTask(2, new EntityAITempt(this, 1.25D, Items.pumpkin_seeds, false));
			this.tasks.addTask(2, new EntityAITempt(this, 1.25D, Items.melon_seeds, false));
		}
		this.tasks.addTask(3, new EntityAIFollowParent(this, 1.25D));
		this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
	}

    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

	public boolean isAIEnabled(){
		return true;
	}
	
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
	}
    
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
        	if (p_70097_1_ instanceof EntityDamageSource && p_70097_1_.isProjectile() == false)
        	{
        		this.setBallTimer(100);
        		return false;
        	}
        	else if (p_70097_1_.isProjectile() == true && this.getBallTimer() > 0)
        	{
        		return false;
        	}
        	else
        	{
        		return super.attackEntityFrom(p_70097_1_, p_70097_2_);
        	}
        }
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "wildmobsmod:mob.armadillo.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "wildmobsmod:mob.armadillo.hurt";
    }
    
    protected float getSoundVolume()
    {
        return 0.4F;
    }
    
    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack p_70877_1_)
    {
    	if (MainRegistry.enableButterfly == true && MainRegistry.enableDragonfly == true)
    	{
    		return p_70877_1_.getItem() == WildMobsModItems.butterfly || p_70877_1_.getItem() == WildMobsModItems.dragonfly;
    	}
    	else if (MainRegistry.enableButterfly == true && MainRegistry.enableDragonfly == false)
    	{
    		return p_70877_1_.getItem() == WildMobsModItems.butterfly;
    	}
    	else if (MainRegistry.enableButterfly == false && MainRegistry.enableDragonfly == true)
    	{
    		return p_70877_1_.getItem() == WildMobsModItems.dragonfly;
    	}
    	else
    	{
            return p_70877_1_ != null && p_70877_1_.getItem() instanceof ItemSeeds;
    	}
    }
    
    public boolean interact(EntityPlayer p_70085_1_)
    {
    	ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();

    	if (super.interact(p_70085_1_))
    	{
    		return true;
    	}
    	else if (!this.worldObj.isRemote)
    	{
    		if (itemstack != null && itemstack.getItem() == WildMobsModItems.armadilloSpawnEgg)
    		{
            	EntityArmadillo entityageable = this.createChild(this);
                entityageable.setGrowingAge(-24000);
            	entityageable.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
            	worldObj.spawnEntityInWorld(entityageable);

                if (itemstack.hasDisplayName())
                {
                    entityageable.setCustomNameTag(itemstack.getDisplayName());
                }

                if (!p_70085_1_.capabilities.isCreativeMode)
                {
                    --itemstack.stackSize;

                    if (itemstack.stackSize <= 0)
                    {
                        p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, (ItemStack)null);
                    }
                }
    			return true;
    		}
    		else
    		{
    			return false;
    		}
    	}
    	else
    	{
    		return false;
    	}
    }
    
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
    	int j = this.rand.nextInt(3) + 2 + this.rand.nextInt(1 + p_70628_2_);

    	for (int k = 0; k < j; ++k)
    	{
    		this.dropItem(WildMobsModItems.armadilloShell, 1);
    	}
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        if (this.getBallTimer() > 0)
        {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
            this.setBallTimer(this.getBallTimer() - 1);
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);  	
        }
    	if (this.onGround == true && this.getBallTimer() > 98)
    	{
    		this.jump();
            this.playSound("wildmobsmod:mob.armadillo.hurt", this.getSoundVolume(), this.getSoundPitch());
    	}
    }

	public EntityArmadillo createChild(EntityAgeable p_90011_1_)
	{
		return new EntityArmadillo(this.worldObj);
	}
}
