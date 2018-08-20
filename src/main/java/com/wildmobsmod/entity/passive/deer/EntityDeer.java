package com.wildmobsmod.entity.passive.deer;

import java.util.ArrayList;
import java.util.Arrays;

import com.wildmobsmod.entity.ai.EntityAIAvoidEntityWild;
import com.wildmobsmod.entity.ai.EntityAIDeerTempt;
import com.wildmobsmod.entity.bases.EntityRideable;
import com.wildmobsmod.entity.passive.fox.EntityFox;
import com.wildmobsmod.items.WildMobsModItems;
import com.wildmobsmod.main.MainRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ForgeHooks;

public class EntityDeer extends EntityRideable
{
	//
	// They now run away from players if the player doesn't hold any wheat. Making the deer be able to eat more kinds of food would be nice.
	//
	
    private EntityAIDeerTempt aiTempt;
    
	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(20, Byte.valueOf((byte)0));
	}
    
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData entity)
    {
    	super.onSpawnWithEgg(entity);
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.posZ);
		BiomeGenBase biome = worldObj.getBiomeGenForCoords(i, j);
		if (biome.biomeID == 32)
		{
			this.setSkin(2);
		}
		else if (biome.biomeID == 33)
		{
			this.setSkin(2);
		}
		else if (biome.biomeID == 160)
		{
			this.setSkin(2);
		}
		else if (biome.biomeID == 161)
		{
			this.setSkin(2);
		}
		else if (biome.temperature <= 0.2F)
		{
			this.setSkin(1);
		}
        else
        {
            this.setSkin(0);
        }
		return entity;
    }
    
	public EntityDeer(World par1World) {
		super(par1World);
        this.getNavigator().setAvoidsWater(true);
        this.setSize(1.0F, 1.5F);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, this.aiTempt = new EntityAIDeerTempt(this, 1.15D, Items.wheat, true));
        this.tasks.addTask(4, new EntityAIAvoidEntityWild(this, EntityPlayer.class, 10.0F, 0.8D, 1.2D));
        this.tasks.addTask(5, new EntityAIFollowParent(this, 1.25D));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
	}
	
    public int getMaxSpawnedInChunk()
    {
        return 6;
    }

	public boolean isAIEnabled()
	{
		return true;
	}
	
    public void onUpdate()
    {
    	super.onUpdate();
		if (this.getSkin() == 2)
		{
	    	this.setSize(1.15F, 1.725F);
		}
		else
		{
	    	this.setSize(1.0F, 1.5F);
		}
    }
	
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
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
 
    public float getEyeHeight()
    {
        return this.height * 1.15F;
    }
    
    protected String getHurtSound()
    {
        return "wildmobsmod:mob.deer.hurt";
    }
    
    protected String getDeathSound()
    {
        return "wildmobsmod:mob.deer.hurt";
    }
    
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    protected Item getDropItem()
    {
        return WildMobsModItems.cookedVenison;
    }
    
    protected void dropFewItems(boolean f1, int f2)
    {
    	if (!(this.getSkin() == 2))
    	{
            int j = this.rand.nextInt(3) + this.rand.nextInt(1 + f2);
            int k;

            for (k = 0; k < j; ++k)
            {
            	if (MainRegistry.enableFur)
            	{
            		this.dropItem(WildMobsModItems.fur, 1);
            	}
            	else
            	{
            		this.dropItem(Items.leather, 1);
            	}
            }

            j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + f2);

            for (k = 0; k < j; ++k)
            {
                if (this.isBurning())
                {
                     this.dropItem(WildMobsModItems.cookedVenison, 1);
                }
                else
                {
                     this.dropItem(WildMobsModItems.rawVenison, 1);
                }
            }
    	}
    	else
    	{
            int j = this.rand.nextInt(4) + this.rand.nextInt(1 + f2);
            int k;

            for (k = 0; k < j; ++k)
            {
            	if (MainRegistry.enableFur)
            	{
            		this.dropItem(WildMobsModItems.fur, 1);
            	}
            	else
            	{
            		this.dropItem(Items.leather, 1);
            	}
            }

            j = this.rand.nextInt(4) + 1 + this.rand.nextInt(1 + f2);

            for (k = 0; k < j; ++k)
            {
            	if (this.isBurning())
            	{
            		this.dropItem(WildMobsModItems.cookedVenison, 1);
            	}
            	else
            	{
            		this.dropItem(WildMobsModItems.rawVenison, 1);
            	}
            }
    	}
    	if (this.getSaddled())
    	{
    		this.dropItem(Items.saddle, 1);
    	}
    }

    public boolean interact(EntityPlayer p_70085_1_)
    {
    	ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();

    	if (super.interact(p_70085_1_))
    	{
    		return true;
    	}
    	else if (this.getSaddled() && !this.worldObj.isRemote && (this.riddenByEntity == null || this.riddenByEntity == p_70085_1_))
    	{
    		if (itemstack != null && itemstack.getItem() == Items.wheat)
    		{
    			return false;
    		}
    		else
    		{
    			p_70085_1_.mountEntity(this);
    			this.setPathToEntity((PathEntity)null);
    			this.setTarget((Entity)null);
    			return true;
    		}
    	}
    	else if (itemstack != null && (!this.getSaddled()) && itemstack.getItem() == Items.saddle && this.getGrowingAge() >= 0 && this.getTamed())
    	{
    		this.setSaddled(true);
    		itemstack.stackSize --;
    		this.playSound("mob.horse.leather", 0.5F, 1.0F);
    		return true;
    	}
    	else if (itemstack != null && itemstack.getItem() == Items.wheat && this.getGrowingAge() >= 0 && !this.getTamed() && this.aiTempt.isRunning())
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
            		this.setPathToEntity((PathEntity)null);
            		this.playTameEffect(true);
            		this.worldObj.setEntityState(this, (byte)7);
            	}
            	else
            	{
            		this.playTameEffect(false);
            		this.worldObj.setEntityState(this, (byte)6);
            	}
            	return true;
            }
    	}
    	if (itemstack != null && (itemstack.getItem() == WildMobsModItems.deerSpawnEgg || itemstack.getItem() == WildMobsModItems.reindeerSpawnEgg || itemstack.getItem() == WildMobsModItems.elkSpawnEgg) && !this.worldObj.isRemote)
		{
        	EntityDeer entityageable = this.createChild(this);
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
    
    public void onLivingUpdate()
    {
    	if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer)
    	{
    		this.setPathToEntity((PathEntity)null);
    		this.setTarget((Entity)null);
    	}
        super.onLivingUpdate();
    }
    
    public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_)
    {
        if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase && this.getSaddled())
        {
            this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.rotationYaw;
            this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
            this.setRotation(this.rotationYaw, this.rotationPitch);
            this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
            p_70612_1_ = ((EntityLivingBase)this.riddenByEntity).moveStrafing * 0.25F;
            p_70612_2_ = ((EntityLivingBase)this.riddenByEntity).moveForward * 0.45F;

            if (p_70612_2_ <= 0.0F)
            {
                p_70612_2_ *= 0.25F;
            }


            this.stepHeight = 1.0F;

            if (!this.worldObj.isRemote)
            {
                this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
                super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
            }

            this.prevLimbSwingAmount = this.limbSwingAmount;
            double d1 = this.posX - this.prevPosX;
            double d0 = this.posZ - this.prevPosZ;
            float f4 = MathHelper.sqrt_double(d1 * d1 + d0 * d0) * 4.0F;

            if (f4 > 1.0F)
            {
                f4 = 1.0F;
            }

            this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.1F;
            this.limbSwing += this.limbSwingAmount;
        }
        else
        {
            super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
        }
    }

    /**
     * Gets the name of this command sender (usually username, but possibly "Rcon")
     */
    public String getCommandSenderName()
    {
        if (this.hasCustomNameTag())
        {
            return this.getCustomNameTag();
        }
        else
        {
            int i = this.getSkin();

            switch (i)
            {
                case 0:
                default:
                    return StatCollector.translateToLocal("entity.wildmobsmod.Deer.name");
                case 1:
                    return StatCollector.translateToLocal("entity.wildmobsmod.Reindeer.name");
                case 2:
                    return StatCollector.translateToLocal("entity.wildmobsmod.Elk.name");
            }
        }
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack p_70877_1_)
    {
    	if (this.getTamed() == true)
    	{
    		return p_70877_1_.getItem() == Items.wheat;
    	}
    	else
    	{
    		return p_70877_1_.getItem() == null;
    	}
    }
    
	@Override
    public EntityDeer createChild(EntityAgeable p_90011_1_)
    {
        EntityDeer entitydeer = (EntityDeer)p_90011_1_;
        EntityDeer entitydeer1 = new EntityDeer(this.worldObj);
        int i = entitydeer.getSkin();
        boolean tameness = entitydeer.getTamed();
        entitydeer1.setSkin(i);
        entitydeer1.setTamed(tameness);
        return entitydeer1;
    }
}
