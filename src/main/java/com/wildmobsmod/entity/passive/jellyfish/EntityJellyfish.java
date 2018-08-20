package com.wildmobsmod.entity.passive.jellyfish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.wildmobsmod.entity.passive.goat.EntityGoat;
import com.wildmobsmod.items.WildMobsModItems;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

public class EntityJellyfish extends EntityWaterMob
{
    //
	// You may add a custom drop if you have good ideas, but for now the normal jellyfish drop slime and the nether medusae drop magma cream.
	//
	
	public float swimTimer;
	public float wideness;
	public float highness;
	
	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(20, Byte.valueOf((byte)0));
		this.dataWatcher.addObject(19, Byte.valueOf((byte)0));
	}

	public EntityJellyfish(World p_i1695_1_) {
		super(p_i1695_1_);
		this.setSize(0.6F, 0.4F);
		this.wideness = 2.0F;
		this.highness = 2.0F;
		this.isImmuneToFire = true;
	}
	
    public void writeEntityToNBT(NBTTagCompound entity)
    {
        super.writeEntityToNBT(entity);
        entity.setInteger("Variant", this.getSkin());
        entity.setBoolean("IsNether", this.getNether());
    }

    public void readEntityFromNBT(NBTTagCompound entity)
    {
        super.readEntityFromNBT(entity);
        this.setSkin(entity.getInteger("Variant"));
        this.setNether(entity.getBoolean("IsNether"));
    }
    
    public int getSkin()
    {
        return this.dataWatcher.getWatchableObjectByte(20);
    }

    public void setSkin(int entity)
    {
        this.dataWatcher.updateObject(20, Byte.valueOf((byte)entity));
    }
    
    public boolean getNether()
    {
        return (this.dataWatcher.getWatchableObjectByte(19) & 1) != 0;
    }

    public void setNether(boolean p_70900_1_)
    {
        if (p_70900_1_)
        {
            this.dataWatcher.updateObject(19, Byte.valueOf((byte)1));
        }
        else
        {
            this.dataWatcher.updateObject(19, Byte.valueOf((byte)0));
        }
    }
    
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData entity)
	{
		Object p_110161_1_1 =  super.onSpawnWithEgg(entity);
		if (this.worldObj.provider instanceof WorldProviderHell)
		{
			this.setSkin(6);
			this.setNether(true);
		}
		else
		{
	        int i = 0;

	        if (p_110161_1_1 instanceof EntityJellyfish.GroupData)
	        {
	            i = ((EntityJellyfish.GroupData)p_110161_1_1).field_111106_b;
	        }
	        else
	        {
	        	i = this.worldObj.rand.nextInt(6);

	        	p_110161_1_1 = new EntityJellyfish.GroupData(i);
	        }

	        this.setSkin(i);
			this.setNether(false);
		}
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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4.0D);
	}
	
	protected void fall(float p_70069_1_){}
	
	protected void updateFallState(double p_70064_1_, boolean p_70064_3_)
	{
		if (this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).getMaterial() != Material.water)
		{
			super.updateFallState(p_70064_1_, p_70064_3_);
		}
	}

    protected String getLivingSound()
    {
        return null;
    }

    protected String getHurtSound()
    {
        return "wildmobsmod:mob.fish.hurt";
    }

    protected String getDeathSound()
    {
        return "wildmobsmod:mob.fish.hurt";
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }

    public boolean handleWaterMovement()
    {
        return false;
    }

    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
        
    	if (this.getNether() == true)
    	{
    		this.isImmuneToFire = true;
	    	this.setSize(0.6F, 0.6F);
    	}
    	else
    	{
    		this.isImmuneToFire = false;
	    	this.setSize(0.4F, 0.4F);
    	}
    	
    	//
    	// Swimming AI
    	//
    	if (this.getNether() == false)
    	{
    		if (this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).getMaterial() == Material.water)
    		{
    			this.swimTimer++;

    			if (!this.worldObj.isRemote)
    			{
    				if (this.swimTimer > 12)
    				{
    					this.motionX = 0.0D;
    					this.motionY -= 0.05D;
    					this.motionY *= 0.3D;
    					this.motionZ = 0.0D;
    				}
    				else
    				{
    					this.motionX = 0.0D;
    					this.motionY += 0.065D;
    					this.motionY *= 0.7D;
    					this.motionZ = 0.0D;
    				}
    			}
    		}
    		else
    		{
    			if (!this.worldObj.isRemote)
    			{
                    this.motionX = 0.0D;
                    this.motionY -= 0.08D;
                    this.motionY *= 0.9800000190734863D;
                    this.motionZ = 0.0D;
    			}
    		}
    		
    		if (this.swimTimer >= 60)
    		{
    			if (this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY) + 1, MathHelper.floor_double(this.posZ)).getMaterial() != Material.water)
    			{
    				this.swimTimer = this.worldObj.rand.nextInt(4);
    			}
    			else if (this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY) - 1, MathHelper.floor_double(this.posZ)).getMaterial() != Material.water)
    			{
      				this.swimTimer = this.worldObj.rand.nextInt(6) + 3;
    			}
    			else
    			{
    				this.swimTimer = this.worldObj.rand.nextInt(7);
    			}
    		}

    		//
    		// Animations
    		//   
    		if (this.swimTimer > 39 && this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).getMaterial() == Material.water)
    		{
    			this.wideness = this.wideness + 0.025F;
    		}
    		else if (this.swimTimer > 0 && this.swimTimer < 20 && this.wideness > 2.0F && this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).getMaterial() == Material.water)
    		{
    			this.wideness = this.wideness - 0.15F;
    		}
    		if (this.wideness < 2.0F)
    		{
    			this.wideness = 2.0F;
    		}

    		if (this.swimTimer > 39 && this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).getMaterial() == Material.water)
    		{
    			this.highness = this.highness - 0.0125F;
    		}
    		else if (this.swimTimer > 0 && this.swimTimer < 20 && this.highness < 2.0F && this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).getMaterial() == Material.water)
    		{
    			this.highness = this.highness + 0.075F;
    		}
    		if (this.highness > 2.0F)
    		{
    			this.highness = 2.0F;
    		}

    		if (this.swimTimer == 20)
    		{
    			this.wideness = 2.0F;
    			this.highness = 2.0F;
    		}
    	}
    	
    	else
    	{
    		this.swimTimer++;

    		if (!this.worldObj.isRemote)
    		{
    			if (this.swimTimer > 12)
    			{
    				this.motionX = 0.0D;
    				this.motionY -= 0.05D;
    				this.motionY *= 0.3D;
    				this.motionZ = 0.0D;
    			}
    			else
    			{
    				this.motionX = 0.0D;
    				this.motionY += 0.065D;
    				this.motionY *= 0.7D;
    				this.motionZ = 0.0D;
    			}
    		}
    		
    		if (this.swimTimer >= 60)
    		{
    			if (this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY) + 1, MathHelper.floor_double(this.posZ)).isNormalCube())
    			{
      				this.swimTimer = this.worldObj.rand.nextInt(4);
    			}
    			else if (this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY) - 1, MathHelper.floor_double(this.posZ)).isNormalCube())
    			{
      				this.swimTimer = this.worldObj.rand.nextInt(6) + 3;
    			}
    			else
    			{
    				this.swimTimer = this.worldObj.rand.nextInt(7);
    			}
    		}

    		//
    		// Animations
    		//   
    		if (this.swimTimer > 39)
    		{
    			this.wideness = this.wideness + 0.025F;
    		}
    		else if (this.swimTimer > 0 && this.swimTimer < 20 && this.wideness > 2.0F)
    		{
    			this.wideness = this.wideness - 0.15F;
    		}
    		if (this.wideness < 2.0F)
    		{
    			this.wideness = 2.0F;
    		}

    		if (this.swimTimer > 39)
    		{
    			this.highness = this.highness - 0.0125F;
    		}
    		else if (this.swimTimer > 0 && this.swimTimer < 20 && this.highness < 2.0F)
    		{
    			this.highness = this.highness + 0.075F;
    		}
    		if (this.highness > 2.0F)
    		{
    			this.highness = 2.0F;
    		}

    		if (this.swimTimer == 20)
    		{
    			this.wideness = 2.0F;
    			this.highness = 2.0F;
    		}
    	}
    }

    public void onEntityUpdate()
    {
        int i = this.getAir();
        super.onEntityUpdate();

        if (this.isEntityAlive() && this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).getMaterial() != Material.water && this.getNether() == false)
        {
            --i;
            this.setAir(i);

            if (this.getAir() == -20)
            {
                this.setAir(0);
                this.attackEntityFrom(DamageSource.drown, 2.0F);
            }
        }
        else
        {
            this.setAir(300);
        }
    }

    protected Item getDropItem()
    {
        return WildMobsModItems.slimeDrop;
    }

    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
    	int j = this.rand.nextInt(4 + p_70628_2_) - 1;

    	if (this.getNether() == false)
    	{
    		for (int k = 0; k < j; ++k)
    		{
    			this.dropItem(WildMobsModItems.slimeDrop, 1);
    		}
    	}
    	else
    	{
    		for (int k = 0; k < j; ++k)
    		{
    			this.dropItem(WildMobsModItems.magmaCreamDrop, 1);
    		}
    	}
    }

    public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_)
    {
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
    }

    public boolean getCanSpawnHere()
    {
    	if (this.getNether() == true)
    	{
    		return super.getCanSpawnHere();
    	}
    	else
    	{
    		return this.posY > 0.0D && this.posY < 55.0D && super.getCanSpawnHere();
    	}
    }

    public String getCommandSenderName()
    {
        if (this.hasCustomNameTag())
        {
            return this.getCustomNameTag();
        }
        else
        {
        	if (this.getNether() == false)
        	{
        		return StatCollector.translateToLocal("entity.wildmobsmod.Jellyfish.name");
        	}
        	else
        	{
        		return StatCollector.translateToLocal("entity.wildmobsmod.NetherJellyfish.name");
        	}
        }
    }
    
    public boolean interact(EntityPlayer p_70085_1_)
    {
        ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();
        int i = this.getSkin();
        
        if (itemstack != null && itemstack.getItem() == Items.water_bucket && this.getSkin() != 6 && this.getNether() == false)
        {
            if (itemstack.stackSize-- == 1)
            {
                p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, new ItemStack(WildMobsModItems.jellyfish, 1, i));
                this.setDead();
            }
            else if (!p_70085_1_.inventory.addItemStackToInventory(new ItemStack(WildMobsModItems.jellyfish, 1, i)))
            {
                p_70085_1_.dropPlayerItemWithRandomChoice(new ItemStack(WildMobsModItems.jellyfish, 1, i), false);
                this.setDead();
            }

            return true;
        }
        else if (itemstack != null && itemstack.getItem() == Items.lava_bucket && this.getSkin() == 6 && this.getNether() == true)
        {
            if (itemstack.stackSize-- == 1)
            {
                p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, new ItemStack(WildMobsModItems.netherJellyfish, 1));
                this.setDead();
            }
            else if (!p_70085_1_.inventory.addItemStackToInventory(new ItemStack(WildMobsModItems.netherJellyfish, 1)))
            {
                p_70085_1_.dropPlayerItemWithRandomChoice(new ItemStack(WildMobsModItems.netherJellyfish, 1), false);
                this.setDead();
            }

            return true;
        }
        else
        {
            return super.interact(p_70085_1_);
        }
    }
    
    protected boolean isValidLightLevel()
    {
        return true;
    }
}