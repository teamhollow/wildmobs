package com.wildmobsmod.entity.passive.fox;


import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

import com.wildmobsmod.entity.ai.EntityAIMeatTempt;
import com.wildmobsmod.entity.passive.bison.EntityBison;
import com.wildmobsmod.entity.passive.deer.EntityDeer;
import com.wildmobsmod.entity.passive.mouse.EntityMouse;
import com.wildmobsmod.items.WildMobsModItems;
import com.wildmobsmod.main.MainRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBeg;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.TempCategory;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class EntityFox extends EntityAnimal{

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
		ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<BiomeDictionary.Type>(Arrays.asList(BiomeDictionary.getTypesForBiome(worldObj.getBiomeGenForCoords(i, j))));
		if (biome.getEnableSnow())
		{
			this.setSkin(1);
		}
		else if (biomeTypesList.contains(BiomeDictionary.Type.SANDY))
		{
			this.setSkin(2);
		}
		else
		{
			this.setSkin(0);
		}
		return entity;
	}

	public EntityFox(World par1World) {
		super(par1World);
		this.setSize(0.51F, 0.68F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 1.5D));
		this.tasks.addTask(2, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.0D, true));
		this.tasks.addTask(4, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(5, new EntityAIMeatTempt(this, 1.0D, false));
		this.tasks.addTask(6, new EntityAIFollowParent(this, 1.25D));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityMouse.class, 250, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityChicken.class, 250, true));
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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
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
    
    public void setAttackTarget(EntityLivingBase entity)
    {
        super.setAttackTarget(entity);
    }

    public boolean attackEntityAsMob(Entity p_70652_1_)
    {
    	return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
    }
    
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            return super.attackEntityFrom(p_70097_1_, p_70097_2_);
        }
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.playSound("mob.wolf.step", 0.15F, 1.0F);
    }

    protected String getHurtSound()
    {
        return "mob.wolf.hurt";
    }

    protected String getDeathSound()
    {
        return "mob.wolf.death";
    }
    
    protected float getSoundVolume()
    {
        return 0.4F;
    }
    
    public float getEyeHeight()
    {
        return this.height * 0.8F;
    }
    
    public boolean isBreedingItem(ItemStack entity)
    {
        return entity == null ? false : (!(entity.getItem() instanceof ItemFood) ? false : ((ItemFood)entity.getItem()).isWolfsFavoriteMeat());
    }

    protected Item getDropItem()
    {
        return WildMobsModItems.fur;
    }

    protected void dropFewItems(boolean f1, int f2)
    {
    	if (this.getSkin() > 2 && this.getSkin() < 5)
    	{
    		int j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + f2);

    		for (int k = 0; k < j; ++k)
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
    	}
    	else
    	{
    		int j = this.rand.nextInt(2) + 1 + this.rand.nextInt(1 + f2);

    		for (int k = 0; k < j; ++k)
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
    		if (itemstack != null && (itemstack.getItem() == WildMobsModItems.foxSpawnEgg || itemstack.getItem() == WildMobsModItems.arcticFoxSpawnEgg || itemstack.getItem() == WildMobsModItems.desertFoxSpawnEgg))
    		{
            	EntityFox entityageable = this.createChild(this);
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
                    return StatCollector.translateToLocal("entity.wildmobsmod.Fox.name");
                case 1:
                    return StatCollector.translateToLocal("entity.wildmobsmod.ArcticFox.name");
                case 2:
                    return StatCollector.translateToLocal("entity.wildmobsmod.DesertFox.name");
            }
        }
    }

    public EntityFox createChild(EntityAgeable p_90011_1_)
    {
		boolean flag = MainRegistry.enableFoxUnnaturalVariants;
        EntityFox entityfox = (EntityFox)p_90011_1_;
        EntityFox entityfox1 = new EntityFox(this.worldObj);
        int i = entityfox.getSkin();
        if (this.rand.nextInt(12) == 0 && flag)
        {
            entityfox1.setSkin(this.worldObj.rand.nextInt(2) + 3);
        }
        else
        {
        	entityfox1.setSkin(i);
        }
        return entityfox1;
    }

}
