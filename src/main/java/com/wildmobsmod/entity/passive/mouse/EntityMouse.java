package com.wildmobsmod.entity.passive.mouse;

import java.util.ArrayList;
import java.util.Arrays;

import com.wildmobsmod.entity.ai.EntityAIAdultAttackOnCollide;
import com.wildmobsmod.entity.ai.EntityAIBabyPanic;
import com.wildmobsmod.entity.ai.EntityAIEatCrops;
import com.wildmobsmod.entity.ai.EntityAIMoveToCrops;
import com.wildmobsmod.entity.passive.deer.EntityDeer;
import com.wildmobsmod.entity.passive.fox.EntityFox;
import com.wildmobsmod.entity.passive.goat.EntityGoat;
import com.wildmobsmod.items.WildMobsModItems;
import com.wildmobsmod.main.MainRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

public class EntityMouse extends EntityAnimal
{
	//
	// Mice will now breed after eating, and won't eat when unable to breed. The babies will instantly grow up.
	//
	
	public int hunger;
	
	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(20, Byte.valueOf((byte)0));
		this.dataWatcher.addObject(19, Byte.valueOf((byte)0));
		this.dataWatcher.addObject(21, Byte.valueOf((byte)0));
	}
	
	public EntityMouse(World par1World) {
		super(par1World);
		this.setSize(0.4F, 0.3F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIEatCrops(this));
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 1.15D, 1.35D));
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityVillager.class, 20.0F, 1.15D, 1.35D));
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityIronGolem.class, 20.0F, 1.15D, 1.35D));
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntitySnowman.class, 8.0F, 1.15D, 1.35D));
        this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(4, new EntityAIMoveToCrops(this, 1.0D));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
	}
	
	public boolean isAIEnabled()
	{
		return true;
	}
	
    public boolean canTriggerWalking()
    {
        return false;
    }
    
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4.0D);
		if (this.getDiseased() == true)
		{
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.15000001192092896D);
		}
		else
		{
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
		}
	}

    public void writeEntityToNBT(NBTTagCompound entity)
    {
        super.writeEntityToNBT(entity);
        entity.setInteger("Variant", this.getSkin());
        entity.setBoolean("IsDiseased", this.getDiseased());
        entity.setInteger("BreedingCounter", this.getMateCounter());
    }

    public void readEntityFromNBT(NBTTagCompound entity)
    {
        super.readEntityFromNBT(entity);
        this.setSkin(entity.getInteger("Variant"));
        this.setDiseased(entity.getBoolean("IsDiseased"));
        this.setMateCounter(entity.getInteger("BreedingCounter"));
    }

    public int getSkin()
    {
        return this.dataWatcher.getWatchableObjectByte(20);
    }

    public void setSkin(int entity)
    {
        this.dataWatcher.updateObject(20, Byte.valueOf((byte)entity));
    }
    
    public boolean getDiseased()
    {
        return (this.dataWatcher.getWatchableObjectByte(19) & 1) != 0;
    }

    public void setDiseased(boolean p_70900_1_)
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
    
    public int getMateCounter()
    {
        return this.dataWatcher.getWatchableObjectByte(21);
    }

    public void setMateCounter(int entity)
    {
        this.dataWatcher.updateObject(21, Byte.valueOf((byte)entity));
    }
    
	protected String getLivingSound()
	{
		return "wildmobsmod:mob.mouse.idle";
	}

	protected String getHurtSound()
	{
		return "wildmobsmod:mob.mouse.hurt";
	}

	protected String getDeathSound()
	{
		return "wildmobsmod:mob.mouse.death";
	}

	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
	{
	}

	protected float getSoundVolume()
	{
		return 0.4F;
	}
	
    public boolean isBreedingItem(ItemStack entity)
    {
        return false;
    }
    
	protected Item getDropItem()
	{
		return WildMobsModItems.rawMouse;
	}

	protected void dropFewItems(boolean f1, int f2)
	{
		if (this.isBurning())
		{
			this.dropItem(WildMobsModItems.cookedMouse, 1);
		}
		else
		{
			this.dropItem(WildMobsModItems.rawMouse, 1);
		}
	}
	
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData entity)
	{
		boolean flag = MainRegistry.enableDiseasedMice;
		Object p_110161_1_1 = super.onSpawnWithEgg(entity);
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.posZ);
		BiomeGenBase biome = worldObj.getBiomeGenForCoords(i, j);
		ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<BiomeDictionary.Type>(Arrays.asList(BiomeDictionary.getTypesForBiome(worldObj.getBiomeGenForCoords(i, j))));
		if (this.rand.nextInt(12) == 0 && flag)
		{
			this.addPotionEffect(new PotionEffect(Potion.hunger.id, 100000, 0));
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.15000001192092896D);
			this.setDiseased(true);
		}
		
        int k = 0;
        
        if (p_110161_1_1 instanceof EntityMouse.GroupData)
        {
            k = ((EntityMouse.GroupData)p_110161_1_1).field_111106_b;
        }
        else
        {
        	if (biome.getEnableSnow())
        	{
        		if (this.rand.nextInt(10) == 0)
        		{
        			k = 3;
        		}
        		else
        		{
        			if (this.rand.nextInt(2) == 0)
        			{
        				k = 0;
        			}
        			else
        			{
        				k = 1;
        			}
        		}
        	}
        	if (biomeTypesList.contains(BiomeDictionary.Type.SANDY))
        	{
        		if (this.rand.nextInt(10) == 0)
        		{
        			k = 1;
        		}
        		else
        		{
        			k = 4;
        		}
        	}
    		else if (biomeTypesList.contains(BiomeDictionary.Type.MOUNTAIN))
    		{
    			if (this.rand.nextInt(3) == 0)
    			{
    				k = 0;
    			}
    			else
    			{
    				k = 2;
    			}
    		}
    		else
    		{
    			int l = this.rand.nextInt(3);
    			if (l == 0)
    			{
    				k = 0;
    			}
    			else if (l == 1)
    			{
    				k = 1;
    			}
    			else
    			{
    				k = 2;
    			}
    		}
        	
        	p_110161_1_1 = new EntityMouse.GroupData(k);
        }
        
        this.setSkin(k);
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
	
    public void onLivingUpdate()
    {
    	if (this.getDiseased() == true)
    	{
			this.addPotionEffect(new PotionEffect(Potion.hunger.id, 100000, 0));
    	}
    	if (this.getMateCounter() >= 4 && this.getGrowingAge() <= 0)
    	{
            this.func_146082_f(null);
            this.setMateCounter(0);
    	}
    	if (this.getGrowingAge() < 0)
    	{
    		this.setGrowingAge(0);
    	}
    	if (this.hunger > 0)
    	{
    		this.hunger--;
    	}

        super.onLivingUpdate();
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
    	if (p_70097_1_ instanceof EntityDamageSource && p_70097_1_.getEntity() instanceof EntityLivingBase && this.getDiseased() == true && !this.worldObj.isRemote)
    	{
    		if (p_70097_1_ instanceof EntityDamageSourceIndirect)
    		{
    		}
    		else
    		{
                if (this.rand.nextInt(2) == 0)
                {
                	Entity entity = p_70097_1_.getEntity();
                	((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.hunger.id, 30 * 20, 0));
                }
    		}
    	}
		return super.attackEntityFrom(p_70097_1_, p_70097_2_);
    }
	
	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) 
	{
        EntityMouse entitymouse = (EntityMouse)p_90011_1_;
        EntityMouse entitymouse1 = new EntityMouse(this.worldObj);
        int i = entitymouse.getSkin();
        entitymouse1.setSkin(i);
        return entitymouse1;
	}
}