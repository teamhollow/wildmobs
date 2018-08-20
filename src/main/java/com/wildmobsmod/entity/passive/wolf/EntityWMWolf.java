package com.wildmobsmod.entity.passive.wolf;

import java.util.ArrayList;
import java.util.Arrays;

import com.wildmobsmod.entity.passive.bison.EntityBison;

import net.minecraft.block.BlockColored;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

public class EntityWMWolf extends EntityWolf
{
	//
	// Wolves now have random skins when tamed similar to ocelots/cats. Tamed wolves will also be named Dogs instead of staying as Wolves. Adding more wild variants as arctic wolves would be pretty cool.
	//
	
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(21, Byte.valueOf((byte)0));
    }
    
	public EntityWMWolf(World p_i1696_1_) 
	{
		super(p_i1696_1_);
	}

    public void writeEntityToNBT(NBTTagCompound p_70014_1_)
    {
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setInteger("WolfType", this.getTameSkin());
    }

    public void readEntityFromNBT(NBTTagCompound p_70037_1_)
    {
        super.readEntityFromNBT(p_70037_1_);
        this.setTameSkin(p_70037_1_.getInteger("WolfType"));
    }
    
    public int getTameSkin()
    {
        return this.dataWatcher.getWatchableObjectByte(21);
    }

    public void setTameSkin(int p_70912_1_)
    {
        this.dataWatcher.updateObject(21, Byte.valueOf((byte)p_70912_1_));
    }

    public boolean interact(EntityPlayer p_70085_1_)
    {
        ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();

        if (this.isTamed())
        {
        	if (itemstack != null)
        	{
        		if (itemstack.getItem() instanceof ItemFood)
        		{
        			ItemFood itemfood = (ItemFood)itemstack.getItem();

        			if (itemfood.isWolfsFavoriteMeat() && this.dataWatcher.getWatchableObjectFloat(18) < 20.0F)
        			{
        				if (!p_70085_1_.capabilities.isCreativeMode)
        				{
        					--itemstack.stackSize;
        				}

        				this.heal((float)itemfood.func_150905_g(itemstack));

        				if (itemstack.stackSize <= 0)
        				{
        					p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, (ItemStack)null);
        				}

        				return true;
        			}
        		}
        		else if (itemstack.getItem() == Items.dye)
        		{
        			int i = BlockColored.func_150032_b(itemstack.getItemDamage());

        			if (i != this.getCollarColor())
        			{
        				this.setCollarColor(i);

        				if (!p_70085_1_.capabilities.isCreativeMode && --itemstack.stackSize <= 0)
        				{
        					p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, (ItemStack)null);
        				}

        				return true;
        			}
        		}
        	}

        	if (this.func_152114_e(p_70085_1_) && !this.worldObj.isRemote && !this.isBreedingItem(itemstack))
        	{
        		this.aiSit.setSitting(!this.isSitting());
        		this.isJumping = false;
        		this.setPathToEntity((PathEntity)null);
        		this.setTarget((Entity)null);
        		this.setAttackTarget((EntityLivingBase)null);
        	}
        }
        else if (itemstack != null && itemstack.getItem() == Items.bone && !this.isAngry())
        {
        	if (!p_70085_1_.capabilities.isCreativeMode)
        	{
        		--itemstack.stackSize;
            }

            if (itemstack.stackSize <= 0)
            {
                p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, (ItemStack)null);
            }

            if (!this.worldObj.isRemote)
            {
                if (this.rand.nextInt(3) == 0)
                {
                    this.setTamed(true);
                    this.setTameSkin(this.worldObj.rand.nextInt(10));
                    this.setPathToEntity((PathEntity)null);
                    this.setAttackTarget((EntityLivingBase)null);
                    this.aiSit.setSitting(true);
                    this.setHealth(20.0F);
                    this.func_152115_b(p_70085_1_.getUniqueID().toString());
                    this.playTameEffect(true);
                    this.worldObj.setEntityState(this, (byte)7);
                }
                else
                {
                    this.playTameEffect(false);
                    this.worldObj.setEntityState(this, (byte)6);
                }
            }

            return true;
        }

        return super.interact(p_70085_1_);
    }

    public EntityWMWolf createChild(EntityAgeable p_90011_1_)
    {
        EntityWMWolf entitywolf = new EntityWMWolf(this.worldObj);
        String s = this.func_152113_b();

        if (s != null && s.trim().length() > 0)
        {
            entitywolf.func_152115_b(s);
            entitywolf.setTamed(true);
            entitywolf.setTameSkin(this.getTameSkin());
        }

        return entitywolf;
    }

    public String getCommandSenderName()
    {
        if (this.hasCustomNameTag())
        {
            return this.getCustomNameTag();
        }
        else
        {
        	if (this.isTamed() == true)
        	{
        		return StatCollector.translateToLocal("entity.Dog.name");
        	}
        	else
        	{
        		return StatCollector.translateToLocal("entity.Wolf.name");
        	}
        }
    }

	public IEntityLivingData onSpawnWithEgg(IEntityLivingData entity)
	{
		super.onSpawnWithEgg(entity);
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.posZ);
		BiomeGenBase biome = worldObj.getBiomeGenForCoords(i, j);
		ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<BiomeDictionary.Type>(Arrays.asList(BiomeDictionary.getTypesForBiome(worldObj.getBiomeGenForCoords(i, j))));
		if (biome.getEnableSnow())
		{
			this.setTameSkin(1);
		}
		else
		{
			this.setTameSkin(0);
		}
		return entity;
	}
}