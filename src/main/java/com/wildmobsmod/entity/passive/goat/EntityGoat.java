package com.wildmobsmod.entity.passive.goat;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.wildmobsmod.items.WildMobsModItems;

public class EntityGoat extends EntityAnimal
{
	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(20, Byte.valueOf((byte)0));
	}

    public EntityGoat(World p_i1683_1_)
    {
        super(p_i1683_1_);
        this.setSize(0.5F, 0.9F);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Items.wheat, false));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Items.gold_ingot, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.2D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }
    
    public void writeEntityToNBT(NBTTagCompound entity)
    {
        super.writeEntityToNBT(entity);
        entity.setInteger("Variant", this.getSkin());
    }

    public void readEntityFromNBT(NBTTagCompound entity)
    {
        super.readEntityFromNBT(entity);
        this.setSkin(entity.getInteger("Variant"));
    }

    public int getSkin()
    {
        return this.dataWatcher.getWatchableObjectByte(20);
    }

    public void setSkin(int entity)
    {
        this.dataWatcher.updateObject(20, Byte.valueOf((byte)entity));
    }
    
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData entity)
	{
		Object p_110161_1_1 = super.onSpawnWithEgg(entity);
		
        int i = 0;

        if (p_110161_1_1 instanceof EntityGoat.GroupData)
        {
            i = ((EntityGoat.GroupData)p_110161_1_1).field_111106_b;
        }
        else
        {
        	i = this.worldObj.rand.nextInt(4);

        	p_110161_1_1 = new EntityGoat.GroupData(i);
        }

        this.setSkin(i);
		return (IEntityLivingData)p_110161_1_1;
	}
	
    public static class GroupData implements IEntityLivingData
    {
        public int field_111106_b;

        public GroupData(int p_i1684_2_)
        {
            this.field_111106_b = p_i1684_2_;
        }
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.17000000298023224D);
    }
    
    protected String getLivingSound()
    {
        return "wildmobsmod:mob.goat.say";
    }
    
    protected String getHurtSound()
    {
        return "wildmobsmod:mob.goat.say";
    }
    
    protected String getDeathSound()
    {
        return "wildmobsmod:mob.goat.say";
    }
    
    protected float getSoundVolume()
    {
        return 0.4F;
    }
    
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
    	int j = this.rand.nextInt(2) + 1 + this.rand.nextInt(1 + p_70628_2_);

    	for (int k = 0; k < j; ++k)
    	{
    		if (this.getSkin() == 0)
    		{
    			this.entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 0), 0.0F);
    		}
    		else if (this.getSkin() == 1)
    		{
    			this.entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 12), 0.0F);
    		}
    		else if (this.getSkin() == 2)
    		{
    			this.entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 8), 0.0F);
    		}
    		else if (this.getSkin() == 3)
    		{
    			if (this.rand.nextInt(2) == 0)
    			{
    				this.entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 0), 0.0F);
    			}
    			else
    			{
    				this.entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 12), 0.0F);
    			}
    		}
    	}
    	
		int j1 = this.rand.nextInt(2) + 1 + this.rand.nextInt(1 + p_70628_2_);

		for (int k = 0; k < j1; ++k)
		{
			if (this.isBurning())
			{
				this.dropItem(WildMobsModItems.cookedChevon, 1);
			}
			else
			{
				this.dropItem(WildMobsModItems.rawChevon, 1);	
			}
		}
    }
    
    public boolean interact(EntityPlayer p_70085_1_)
    {
        ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();

        if (itemstack != null && itemstack.getItem() == Items.bucket && !p_70085_1_.capabilities.isCreativeMode)
        {
            if (itemstack.stackSize-- == 1)
            {
                p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, new ItemStack(Items.milk_bucket));
            }
            else if (!p_70085_1_.inventory.addItemStackToInventory(new ItemStack(Items.milk_bucket)))
            {
                p_70085_1_.dropPlayerItemWithRandomChoice(new ItemStack(Items.milk_bucket, 1, 0), false);
            }

            return true;
        }
        else if (itemstack != null && itemstack.getItem() == Items.gold_ingot && !p_70085_1_.capabilities.isCreativeMode)
        {
            itemstack.stackSize--;
            
            if (!this.worldObj.isRemote)
            {
    	    	this.playSound("random.break", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
            	this.dropItem(Items.gold_nugget, 9);
            	return true;
            }

            return false;
        }
    	else if (itemstack != null && itemstack.getItem() == WildMobsModItems.goatSpawnEgg && !this.worldObj.isRemote)
		{
        	EntityGoat entityageable = this.createChild(this);
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
            return super.interact(p_70085_1_);
        }
    }
    
    public EntityGoat createChild(EntityAgeable p_90011_1_)
    {
        EntityGoat entitygoat = (EntityGoat)p_90011_1_;
        EntityGoat entitygoat1 = new EntityGoat(this.worldObj);
        int i = entitygoat.getSkin();
        entitygoat1.setSkin(i);
        return entitygoat1;
    }
}