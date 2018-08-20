package com.wildmobsmod.entity.monster.seascorpion;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import com.wildmobsmod.entity.bases.EntityMobTameable;
import com.wildmobsmod.items.WildMobsModItems;
import com.wildmobsmod.main.MainRegistry;

public class EntitySeaScorpion extends EntityMobTameable
{
	//
	// This mob is completely finished and shouldn't be changed at all. There's a lot of information about it on my Discord server.
	//
	
	private ChunkCoordinates spawnPosition;
	private Entity closestLivingEntity;
	private EntitySeaScorpion.Sorter theNearestAttackableTargetSorter;
    private int eatingTimer;
    public float seaScorpionPitch;
    public float prevSeaScorpionPitch;
    private float nextSeaScorpionPitch;
    private double prevPosY;
    private int timeToCalculatePosY;
    private double d1;
    private int timeToJump;
    
    ItemStack food;

	public final IEntitySelector targetEntitySelectorMob = new IEntitySelector()
	{
		public boolean isEntityApplicable(Entity p_82704_1_)
		{
			return p_82704_1_.isEntityAlive() && EntitySeaScorpion.this.getEntitySenses().canSee(p_82704_1_) && EntitySeaScorpion.this.worldObj.getBlock(MathHelper.floor_double(p_82704_1_.posX), MathHelper.floor_double(p_82704_1_.posY), MathHelper.floor_double(p_82704_1_.posZ)).getMaterial() == Material.water && p_82704_1_ != EntitySeaScorpion.this && !(p_82704_1_ instanceof EntitySeaScorpion) && !(p_82704_1_ instanceof EntityCreeper && !(p_82704_1_ instanceof EntityGhast) && ((EntityMob)p_82704_1_).getActivePotionEffect(MainRegistry.potionAquaHealing) == null);
		}
	};
	
	public final IEntitySelector targetEntitySelectorHuman = new IEntitySelector()
	{
		public boolean isEntityApplicable(Entity p_82704_1_)
		{
			return p_82704_1_.isEntityAlive() && EntitySeaScorpion.this.getEntitySenses().canSee(p_82704_1_) && EntitySeaScorpion.this.worldObj.getBlock(MathHelper.floor_double(p_82704_1_.posX), MathHelper.floor_double(p_82704_1_.posY), MathHelper.floor_double(p_82704_1_.posZ)).getMaterial() == Material.water && p_82704_1_ != EntitySeaScorpion.this && p_82704_1_ instanceof EntityPlayer && (!((EntityPlayer)p_82704_1_).capabilities.disableDamage && ((EntityPlayer)p_82704_1_).getActivePotionEffect(MainRegistry.potionAquaHealing) == null);
		}
	};
	
	public final IEntitySelector targetEntitySelectorLiving = new IEntitySelector()
	{
		public boolean isEntityApplicable(Entity p_82704_1_)
		{
			return p_82704_1_.isEntityAlive() && EntitySeaScorpion.this.getEntitySenses().canSee(p_82704_1_) && EntitySeaScorpion.this.worldObj.getBlock(MathHelper.floor_double(p_82704_1_.posX), MathHelper.floor_double(p_82704_1_.posY), MathHelper.floor_double(p_82704_1_.posZ)).getMaterial() == Material.water && p_82704_1_ != EntitySeaScorpion.this && !(p_82704_1_ instanceof EntitySeaScorpion) && !(p_82704_1_ instanceof EntityCreeper) && !(p_82704_1_ instanceof EntityGhast);
		}
	};

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(20, Byte.valueOf((byte)0));
        this.dataWatcher.addObject(21, new Integer(0));
        this.dataWatcher.addObject(22, new Integer(0));
        this.dataWatcher.addObject(23, new Integer(0));
		this.dataWatcher.addObject(24, Byte.valueOf((byte)0));
	}

	public EntitySeaScorpion(World p_i1738_1_) {
		super(p_i1738_1_);
		this.setSize(0.8F, 0.2F);
        this.setIsWild(true);
        this.setIsSeaMonster(false);
		this.theNearestAttackableTargetSorter = new EntitySeaScorpion.Sorter(this);
	}

	public void writeEntityToNBT(NBTTagCompound entity)
	{
		super.writeEntityToNBT(entity);
		entity.setBoolean("IsWild", this.getIsWild());
		entity.setInteger("Age", this.getGrowingAge());
		entity.setInteger("Regeneration", this.getRegeneration());
		entity.setInteger("Size", this.getSize());
		entity.setBoolean("IsSeaMonster", this.getIsSeaMonster());
	}

	public void readEntityFromNBT(NBTTagCompound entity)
	{
		super.readEntityFromNBT(entity);
		this.setIsWild(entity.getBoolean("IsWild"));
        this.setGrowingAge(entity.getInteger("Age"));
        this.setRegeneration(entity.getInteger("Regeneration"));
        this.setSize(entity.getInteger("Size"));
		this.setIsSeaMonster(entity.getBoolean("IsSeaMonster"));
	}

	public boolean getIsWild()
	{
		return (this.dataWatcher.getWatchableObjectByte(20) & 1) != 0;
	}

	public void setIsWild(boolean p_70900_1_)
	{
		if (p_70900_1_)
		{
			this.dataWatcher.updateObject(20, Byte.valueOf((byte)1));
		}
		else
		{
			this.dataWatcher.updateObject(20, Byte.valueOf((byte)0));
		}
	}

    public int getGrowingAge()
    {
        return this.dataWatcher.getWatchableObjectInt(21);
    }

    public void setGrowingAge(int entity)
    {
    	this.dataWatcher.updateObject(21, Integer.valueOf(entity));
    }
    
    public int getRegeneration()
    {
        return this.dataWatcher.getWatchableObjectInt(22);
    }

    public void setRegeneration(int entity)
    {
    	this.dataWatcher.updateObject(22, Integer.valueOf(entity));
    }
    
    public int getSize()
    {
        return this.dataWatcher.getWatchableObjectInt(23);
    }

    public void setSize(int entity)
    {
    	this.dataWatcher.updateObject(23, Integer.valueOf(entity));
    	this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)(8 + 4 * entity));
    	this.experienceValue = 3 + entity;
    	this.setHealth(this.getMaxHealth());
    	this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(this.getSize());
    }
    
	public boolean getIsSeaMonster()
	{
		return (this.dataWatcher.getWatchableObjectByte(24) & 1) != 0;
	}

	public void setIsSeaMonster(boolean p_70900_1_)
	{
		if (p_70900_1_)
		{
			this.dataWatcher.updateObject(24, Byte.valueOf((byte)1));
		}
		else
		{
			this.dataWatcher.updateObject(24, Byte.valueOf((byte)0));
		}
	}
    
    protected void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
    	this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
    }

	protected void fall(float p_70069_1_)
	{
		if (this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).getMaterial() != Material.water)
		{
			super.fall(p_70069_1_);
		}
	}

	protected void updateFallState(double p_70064_1_, boolean p_70064_3_)
	{
		if (this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).getMaterial() != Material.water)
		{
			super.updateFallState(p_70064_1_, p_70064_3_);
		}
	}

    public boolean handleWaterMovement()
    {
        return false;
    }

	public boolean canBreatheUnderwater()
	{
		return true;
	}

	protected boolean canTriggerWalking()
	{
		return false;
	}

	protected boolean canDespawn()
	{
		return this.getIsWild() && !(this.getIsSeaMonster());
	}

	public float getEyeHeight()
	{
		return this.height * 0.5F;
	}

	protected String getLivingSound()
	{
		return this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).getMaterial() == Material.water ? "wildmobsmod:mob.seascorpion.saywater" : null;
	}

	protected String getHurtSound()
	{
		return this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).getMaterial() == Material.water ? "wildmobsmod:mob.seascorpion.hurtwater" : "wildmobsmod:mob.seascorpion.hurtland";
	}

	protected String getDeathSound()
	{
		return this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).getMaterial() == Material.water ? "wildmobsmod:mob.seascorpion.hurtwater" : "wildmobsmod:mob.seascorpion.hurtland";
	}

    public int getTalkInterval()
    {
        return 80;
    }
    
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();
        
		if (this.spawnPosition != null)
		{
			this.checkBlockCollision();
		}
		
		//
		// Vertical movement
		//
		if (this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).getMaterial() == Material.water)
		{
			if (this.timeToJump > 0)
			{
				this.motionY = 0.2D;
				this.timeToJump--;
			}
			else
			{
				if (this.spawnPosition != null )
				{
					double d1 = (double)this.spawnPosition.posY + 0.1D - this.posY;
					if (this.entityToAttack == null)
					{
						this.motionY += (Math.signum(d1) * 0.699999988079071 - this.motionY) * 0.15000000149011612D;
						if (this.spawnPosition.posY - 0.5D > this.posY || this.spawnPosition.posY + 0.5D < this.posY)
						{
							this.motionY *= 0.6D;
						}
						else
						{
							this.motionY *= 0.4D;
						}
					}
					else
					{
						this.motionY += (Math.signum(d1) * 0.699999988079071 - this.motionY) * 0.15000000149011612D;
						this.motionY *= 0.8D;
					}
				}
				else
				{
					this.motionY = 0.0D;
					this.motionY *= 0.0D;
				}
			}
			
			if (this.entityToAttack != null && this.worldObj.getBlock(MathHelper.floor_double(this.entityToAttack.posX), MathHelper.floor_double(this.entityToAttack.posY + this.entityToAttack.getEyeHeight()), MathHelper.floor_double(this.entityToAttack.posZ)).isNormalCube() == false && this.worldObj.getBlock(MathHelper.floor_double(this.entityToAttack.posX), MathHelper.floor_double(this.entityToAttack.posY + this.entityToAttack.getEyeHeight()), MathHelper.floor_double(this.entityToAttack.posZ)).getMaterial() != Material.water && this.rand.nextInt(30) == 0)
			{
				if (this.getDistanceSqToEntity(this.entityToAttack) < 16.0D)
				{
					this.timeToJump = 20;
				}
			}
		}
		
		if (this.timeToJump > 0 && this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).isNormalCube() == false && this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).getMaterial() != Material.water && this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY) - 1, MathHelper.floor_double(this.posZ)).getMaterial() == Material.water)
		{
			this.timeToJump = 0;
			this.waterJump();
		}
		
		if ((this.spawnPosition == null || this.rand.nextInt(100) == 0 || this.spawnPosition.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 2.0F || this.spawnPosition.getDistanceSquared((int)this.posX, (int)this.spawnPosition.posY, (int)this.posZ) < 1.0F) && this.entityToAttack == null)
		{
			this.spawnPosition = new ChunkCoordinates((int)this.posX + this.rand.nextInt(12) - this.rand.nextInt(12), (int)this.posY + this.rand.nextInt(8) - 2, (int)this.posZ + this.rand.nextInt(12) - this.rand.nextInt(12));
		}
		else if (this.entityToAttack != null)
		{
			this.spawnPosition = null;
			if ((int)this.entityToAttack.posY < (int)(this.entityToAttack.posY + this.entityToAttack.getEyeHeight()) && this.worldObj.getBlock((int)this.entityToAttack.posX, (int)(this.entityToAttack.posY + this.entityToAttack.getEyeHeight()), (int)this.entityToAttack.posZ).getMaterial() == Material.water)
			{
				this.spawnPosition = new ChunkCoordinates((int)this.entityToAttack.posX - 1, (int)this.entityToAttack.posY + 1, (int)this.entityToAttack.posZ);
			}
			else
			{
				this.spawnPosition = new ChunkCoordinates((int)this.entityToAttack.posX - 1, (int)this.entityToAttack.posY, (int)this.entityToAttack.posZ);
			}
		}
		
		if (this.spawnPosition != null && this.worldObj.getBlock(this.spawnPosition.posX, this.spawnPosition.posY, this.spawnPosition.posZ).getMaterial() != Material.water || this.spawnPosition.posY < 1)
		{
			this.spawnPosition = null;
		}
		
		if (this.spawnPosition != null && this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).getMaterial() == Material.water)
		{
			double d0 = (double)this.spawnPosition.posX + 0.5D - this.posX;
			double d2 = (double)this.spawnPosition.posZ + 0.5D - this.posZ;
			double speed;
			if (this.entityToAttack == null)
			{
				speed = 0.07D;
			}
			else
			{
				speed = 0.12D;
			}
			this.motionX += (Math.signum(d0) * speed - this.motionX) * 0.5D;
			this.motionZ += (Math.signum(d2) * speed - this.motionZ) * 0.5D;
			if (this.spawnPosition != null)
			{
	    		this.moveForward = 0.05F;
			}
		}
		
		if (this.getGrowingAge() >= 0)
		{
			if (this.getIsSeaMonster() == true || (this.hasCustomNameTag() && "Leviathan".equals(this.getCustomNameTag())))
			{
				this.findLivingToAttack();
			}
			else
			{
				if (this.getIsWild() == false)
				{
					this.findMobToAttack();
				}
				else
				{
					float f = this.getBrightness(1.0F);

					if (this.canSeaScorpionSeeSky() == true && this.worldObj.isDaytime() == true && this.worldObj.isThundering() == false && this.worldObj.isRaining() == false)
					{
					}
					else
					{
						if (f < 0.5F)
						{
							this.findHumanToAttack();
						}
					}
				}
			}
		}
		
		EntityLivingBase target = this.getAttackTarget();
		
		if (this.entityToAttack != null)
		{
			if (this.getGrowingAge() < 0)
			{
				this.setTarget(null);
			}
			else
			{
				if (this.canAttackTargetBeSeen(this.entityToAttack) == false)
				{
					this.setTarget(null);
				}
				else
				{
					if (this.worldObj.getBlock(MathHelper.floor_double(this.entityToAttack.posX), MathHelper.floor_double(this.entityToAttack.posY), MathHelper.floor_double(this.entityToAttack.posZ)).getMaterial() != Material.water && this.worldObj.getBlock(MathHelper.floor_double(this.entityToAttack.posX), MathHelper.floor_double(this.entityToAttack.posY - 1), MathHelper.floor_double(this.entityToAttack.posZ)).getMaterial() != Material.water)
					{
						this.setTarget(null);
					}
				}
			}
		}
		
		int i = this.getGrowingAge();

		if (i < 0)
		{
			++i;
			this.setGrowingAge(i);
		}
		else if (i > 0)
		{
			--i;
			this.setGrowingAge(i);
		}

		int j = this.getRegeneration();

		if (j < 0)
		{
			++j;
			this.setRegeneration(j);
		}
		else if (j > 0)
		{
			--j;
			this.setRegeneration(j);
		}
		else if (j == 0 && this.getIsWild() == false && this.getHealth() < this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue() && this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).getMaterial() == Material.water)
		{
			if (this.entityToAttack != null)
			{
				this.setRegeneration(50);
			}
			else
			{
				this.setRegeneration(80);
			}
			this.heal(1);
		}
		
        if (this.eatingTimer > 0 && this.worldObj.isRemote)
        {
            String s = "iconcrack_" + Item.getIdFromItem(this.food.getItem());
            if (this.food.getHasSubtypes())
            {
                s = s + "_" + this.food.getItemDamage();
            }
            
        	for (int k = 0; k < 3; ++k)
        	{
        		double d0 = this.rand.nextGaussian() * 0.01D;
        		double d1 = this.rand.nextGaussian() * 0.01D;
        		double d2 = this.rand.nextGaussian() * 0.01D;
        		this.worldObj.spawnParticle(s, this.posX + (double)(this.rand.nextFloat() * this.width * 1.5F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height) - 0.5D, this.posZ + (double)(this.rand.nextFloat() * this.width * 1.5F) - (double)this.width, d0, d1, d2);
        	}
        }
        
		if (this.eatingTimer < 0)
		{
			this.eatingTimer++;
		}
		else if (this.eatingTimer > 0)
		{
			this.eatingTimer--;
		}
		
		//
		// Sea scorpion pitch
		//
		this.prevSeaScorpionPitch = this.seaScorpionPitch;
		
		double d0 = (double)MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		
		if (d0 < 0)
		{
			d0 = -d0;
		}
		
		if (this.timeToCalculatePosY > 0)
		{
			this.timeToCalculatePosY--;
		}
		else
		{
			this.timeToCalculatePosY = 2;
			this.d1 = -(this.prevPosY - this.posY);
			this.prevPosY = this.posY;
		}
		
		this.nextSeaScorpionPitch = (float)((d0 * 100) * (this.d1 * 10));
		
		if (this.seaScorpionPitch < this.nextSeaScorpionPitch - 4.0F)
		{
			this.seaScorpionPitch = this.seaScorpionPitch + 2.0F;
		}
		else if (this.seaScorpionPitch > this.nextSeaScorpionPitch + 4.0F)
		{
			this.seaScorpionPitch = this.seaScorpionPitch - 2.0F;
		}
	}
	
	protected void waterJump()
	{
		this.motionY = 0.4D;

		if (this.isPotionActive(Potion.jump))
		{
			this.motionY += (double)((float)(this.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
		}

		this.motionX *= 1.5D;
		this.motionZ *= 1.5D;

		this.isAirBorne = true;
	}
	
    private float updateRotation(float p_75652_1_, float p_75652_2_, float p_75652_3_)
    {
        float f3 = MathHelper.wrapAngleTo180_float(p_75652_2_ - p_75652_1_);

        if (f3 > p_75652_3_)
        {
            f3 = p_75652_3_;
        }

        if (f3 < -p_75652_3_)
        {
            f3 = -p_75652_3_;
        }

        return p_75652_1_ + f3;
    }
    
	public void updateEntityActionState()
	{
		super.updateEntityActionState();
		this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;
	}
	
    protected void checkBlockCollision()
    {
    	int x = this.spawnPosition.posX;
    	int y = this.spawnPosition.posY;
    	int z = this.spawnPosition.posZ;
    	
		if (z > this.posZ && this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ) + 1).isNormalCube() && (int)this.posY == y)
		{
			this.spawnPosition = null;
			if (this.entityToAttack == null)
			{
				this.spawnPosition = new ChunkCoordinates((int)this.posX + this.rand.nextInt(12) - this.rand.nextInt(12), (int)this.posY + this.rand.nextInt(8) - 2, (int)this.posZ - this.rand.nextInt(12));
			}
		}
		else if (x < this.posX && this.worldObj.getBlock(MathHelper.floor_double(this.posX) - 1, MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).isNormalCube() && (int)this.posY == y)
		{
			this.spawnPosition = null;
			if (this.entityToAttack == null)
			{
				this.spawnPosition = new ChunkCoordinates((int)this.posX + this.rand.nextInt(12), (int)this.posY + this.rand.nextInt(8) - 2, (int)this.posZ + this.rand.nextInt(12) - this.rand.nextInt(12));
			}
		}
		else if (z < this.posZ && this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ) - 1).isNormalCube() && (int)this.posY == y)
		{
			this.spawnPosition = null;
			if (this.entityToAttack == null)
			{
				this.spawnPosition = new ChunkCoordinates((int)this.posX + this.rand.nextInt(12) - this.rand.nextInt(12), (int)this.posY + this.rand.nextInt(8) - 2, (int)this.posZ + this.rand.nextInt(12));
			}
		}
		else if (x > this.posX && this.worldObj.getBlock(MathHelper.floor_double(this.posX) + 1, MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).isNormalCube() && (int)this.posY == y)
		{
			this.spawnPosition = null;
			if (this.entityToAttack == null)
			{
				this.spawnPosition = new ChunkCoordinates((int)this.posX - this.rand.nextInt(12), (int)this.posY + this.rand.nextInt(8) - 2, (int)this.posZ + this.rand.nextInt(12) - this.rand.nextInt(12));
			}
		}
    }
    
	public void onUpdate()
	{
		super.onUpdate();
		if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL && this.getIsWild() == true)
		{
			this.setDead();
		}
		if (this.worldObj.isRemote)
		{
			this.setScaleForAge(this.isChild(), this.getSize());
		}
	}
	
	public void onEntityUpdate()
	{
        int i = this.getAir();
        super.onEntityUpdate();

        if (this.isEntityAlive() && this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).getMaterial() != Material.water)
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
    
    public boolean canAttackTargetBeSeen(Entity p_70685_1_)
    {
        return this.worldObj.rayTraceBlocks(Vec3.createVectorHelper(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ), Vec3.createVectorHelper(p_70685_1_.posX, p_70685_1_.posY + 0.1, p_70685_1_.posZ)) == null;
    }

    public boolean attackEntityAsMob(Entity p_70652_1_)
    {
    	if (this.getGrowingAge() >= 0 && this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).getMaterial() == Material.water)
    	{
            float f = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
            int i = 0;

            if (p_70652_1_ instanceof EntityLivingBase)
            {
                f += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase)p_70652_1_);
                i += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase)p_70652_1_);
            }

            boolean flag;

            if (this.getIsWild() == false && !(p_70652_1_ instanceof EntityPlayer))
            {
            	flag = p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), f + 5);
            }
            else
            {
            	flag = p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), f);
            }

            if (flag)
            {
                if (i > 0)
                {
                    p_70652_1_.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F));
                    this.motionX *= 0.6D;
                    this.motionZ *= 0.6D;
                }

                int j = EnchantmentHelper.getFireAspectModifier(this);

                if (j > 0)
                {
                    p_70652_1_.setFire(j * 4);
                }

                if (p_70652_1_ instanceof EntityLivingBase)
                {
                    EnchantmentHelper.func_151384_a((EntityLivingBase)p_70652_1_, this);
                }

                EnchantmentHelper.func_151385_b(this, p_70652_1_);
            }

            return flag;
    	}
    	else
    	{
    		return false;
    	}
    }

	protected void findMobToAttack()
	{
		List list = this.worldObj.selectEntitiesWithinAABB(EntityMob.class, this.boundingBox.expand(16.0D, 16.0D, 16.0D), this.targetEntitySelectorMob);
		Collections.sort(list, this.theNearestAttackableTargetSorter);

		if (list.isEmpty())
		{
		}
		else if (this.entityToAttack == null)
		{
			this.entityToAttack = (EntityLivingBase)list.get(0);
		}
	}
	
	protected void findHumanToAttack()
	{
		List list = this.worldObj.selectEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(16.0D, 16.0D, 16.0D), this.targetEntitySelectorHuman);
		Collections.sort(list, this.theNearestAttackableTargetSorter);

		if (list.isEmpty())
		{
		}
		else if (this.entityToAttack == null)
		{
			this.entityToAttack = (EntityLivingBase)list.get(0);
		}
	}

	protected void findLivingToAttack()
	{
		List list = this.worldObj.selectEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(16.0D, 16.0D, 16.0D), this.targetEntitySelectorLiving);
		Collections.sort(list, this.theNearestAttackableTargetSorter);

		if (list.isEmpty())
		{
		}
		else if (this.entityToAttack == null)
		{
			this.entityToAttack = (EntityLivingBase)list.get(0);
		}
	}
	
	protected Entity findPlayerToAttack()
	{
		return null;
	}

    public void setScaleForAge(boolean p_98054_1_, int p_98054_2_)
    {
    	int i = p_98054_2_ + 1;
        this.setSize((p_98054_1_ ? (0.2F + (i * 0.2F)) / 2 : 0.2F + (i * 0.2F)), (p_98054_1_ ?( 0.025F + (i * 0.025F)) / 2 : 0.05F + (i * 0.05F)));
    }
    
    public boolean isChild()
    {
        return this.getGrowingAge() < 0;
    }
    
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else if (super.attackEntityFrom(p_70097_1_, p_70097_2_))
        {
            Entity entity = p_70097_1_.getEntity();

            if (this.riddenByEntity != entity && this.ridingEntity != entity)
            {
            	if (entity != this && this.getAge() >= 0)
            	{
            		this.entityToAttack = entity;
            	}

            	return true;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }
    
    public boolean interact(EntityPlayer p_70085_1_)
    {
        ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();
        
        if (itemstack != null && itemstack.getItem() == Items.water_bucket && this.getIsWild() == false && this.getIsSeaMonster() == false && !this.worldObj.isRemote)
        {
        	int i;
        	
        	if (this.getGrowingAge() >= 0 && this.getSize() == 0)
        	{
        		i = 1;
        	}
        	else if (this.getGrowingAge() < 0 && this.getSize() == 0)
        	{
        		i = 2;
        	}
        	else if (this.getGrowingAge() < 0 && this.getSize() == 1)
        	{
        		i = 3;
        	}
        	else if (this.getGrowingAge() < 0 && this.getSize() == 2)
        	{
        		i = 0;
        	}
        	else
        	{
        		i = 4;
        	}
        	
        	if (i < 4)
        	{
        		if (itemstack.stackSize-- == 1)
        		{
        			p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, new ItemStack(WildMobsModItems.seaScorpionBucket, 1, i));
        			this.setDead();
        		}
        		else if (!p_70085_1_.inventory.addItemStackToInventory(new ItemStack(WildMobsModItems.seaScorpionBucket, 1)))
        		{
        			p_70085_1_.dropPlayerItemWithRandomChoice(new ItemStack(WildMobsModItems.seaScorpionBucket, 1, i), false);
        			this.setDead();
        		}
                return true;
        	}
        	else
        	{
        		return super.interact(p_70085_1_);
        	}
        }
        else if (itemstack != null && itemstack.getItem() == WildMobsModItems.seaScorpionSpawnEgg && !this.worldObj.isRemote)
		{
        	EntitySeaScorpion entityseascorpion = new EntitySeaScorpion(this.worldObj);
        	entityseascorpion.setGrowingAge(-24000);
        	entityseascorpion.setSize(this.getSize());
        	entityseascorpion.setIsWild(this.getIsWild());
        	entityseascorpion.setIsSeaMonster(this.getIsSeaMonster());
        	entityseascorpion.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
        	worldObj.spawnEntityInWorld(entityseascorpion);

            if (itemstack.hasDisplayName())
            {
            	entityseascorpion.setCustomNameTag(itemstack.getDisplayName());
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
        else if (itemstack.getItem() instanceof ItemFood && !this.worldObj.isRemote)
        {
        	ItemFood itemfood = (ItemFood)itemstack.getItem();

        	if (itemstack != null && this.isHealingItem(itemstack) == true && this.getHealth() < this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue() && this.getIsWild() == false)
        	{
        		if (!p_70085_1_.capabilities.isCreativeMode)
        		{
        			--itemstack.stackSize;
        		}

        		this.heal((float)itemfood.func_150905_g(itemstack));
        		
        		this.food = itemstack;
        		this.eatingTimer = 10;
        		
        		if (itemstack.stackSize <= 0)
        		{
        			p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, (ItemStack)null);
        		}

        		return true;
        	}
        	else
        	{
        		return super.interact(p_70085_1_);
        	}
        }
        else
        {
        	return super.interact(p_70085_1_);
        }
    }

	public boolean isHealingItem(ItemStack p_70877_1_)
    {
    	Item[] suitableFoods;
    	
    	suitableFoods = new Item[MainRegistry.seaScorpionSuitableFoods.length];
    	
    	for (int k = 0; k < MainRegistry.seaScorpionSuitableFoods.length; ++k)
    	{
    		suitableFoods[k] = MainRegistry.getItem(MainRegistry.listToString(MainRegistry.seaScorpionSuitableFoods, k));
    	}

    	return p_70877_1_ != null && Arrays.asList(suitableFoods).contains(p_70877_1_.getItem());
    }

	public static class Sorter implements Comparator
	{
		private final Entity theEntity;

		public Sorter(Entity p_i1662_1_)
		{
			this.theEntity = p_i1662_1_;
		}

		public int compare(Entity p_compare_1_, Entity p_compare_2_)
		{
			double d0 = this.theEntity.getDistanceSqToEntity(p_compare_1_);
			double d1 = this.theEntity.getDistanceSqToEntity(p_compare_2_);
			return d0 < d1 ? -1 : (d0 > d1 ? 1 : 0);
		}

		public int compare(Object p_compare_1_, Object p_compare_2_)
		{
			return this.compare((Entity)p_compare_1_, (Entity)p_compare_2_);
		}
	}

	protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
	{
		if (this.getGrowingAge() >= 0)
		{
			int i = this.rand.nextInt(1 + this.getSize()) + this.rand.nextInt(1 + p_70628_2_);
			int j = this.rand.nextInt(100);
			int l = this.rand.nextInt(100);

			for (int k = 0; k < i; ++k)
			{
				if (this.isBurning())
				{
					if (j >= 0 && j < 60)
					{
						this.entityDropItem(new ItemStack(Items.cooked_fished, 1, 0), 0.0F);
					}
					else if (j >= 60 && j < 85)
					{
						this.entityDropItem(new ItemStack(Items.cooked_fished, 1, 1), 0.0F);
					}
					else if (j >= 85 && j < 87)
					{
						this.entityDropItem(new ItemStack(Items.fish, 1, 2), 0.0F);
					}
					else
					{
						this.entityDropItem(new ItemStack(Items.fish, 1, 3), 0.0F);
					}
				}
				else
				{
					if (j >= 0 && j < 60)
					{
						this.entityDropItem(new ItemStack(Items.fish, 1, 0), 0.0F);
					}
					else if (j >= 60 && j < 85)
					{
						this.entityDropItem(new ItemStack(Items.fish, 1, 1), 0.0F);
					}
					else if (j >= 85 && j < 87)
					{
						this.entityDropItem(new ItemStack(Items.fish, 1, 2), 0.0F);
					}
					else
					{
						this.entityDropItem(new ItemStack(Items.fish, 1, 3), 0.0F);
					}
				}
			}

			if (l <= 4 + this.getSize() + p_70628_2_)
			{
				this.dropItem(WildMobsModItems.seaScorpionEgg, 1);
			}
		}
	}

	public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_)
    {
    	p_110161_1_ = super.onSpawnWithEgg(p_110161_1_);
    	
    	int i = this.rand.nextInt(8);
    	
    	if (i == 0 || i == 1 || i == 2 || i == 3)
    	{
    		this.setSize(1);
    	}
    	else if (i == 4 || i == 5 || i == 6)
    	{
    		this.setSize(2);
    	}
    	else
    	{
    		this.setSize(4);
    	}
    	
    	this.setIsWild(true);
    	return p_110161_1_;
    }

    public void spawnPack()
    {
    	for (int k = 0; k < 3; ++k)
    	{
    		double x;
    		double y;
    		double z;

    		x = this.posX + this.worldObj.rand.nextInt(5)- this.worldObj.rand.nextInt(5);
    		y = this.posY + this.worldObj.rand.nextInt(2)- this.worldObj.rand.nextInt(2);
    		z = this.posZ + this.worldObj.rand.nextInt(5)- this.worldObj.rand.nextInt(5);

    		EntitySeaScorpion entityseascorpion = new EntitySeaScorpion(this.worldObj);
    		entityseascorpion.setPosition(x, y, z);
    		entityseascorpion.onSpawnWithEgg((IEntityLivingData)null);
    		entityseascorpion.setSize(this.getSize());
    		if (entityseascorpion.getCanSpawnHere() == true)
    		{
    			this.worldObj.spawnEntityInWorld(entityseascorpion);
    		}
    	}
    }

    public boolean canSeaScorpionSeeSky()
    {
    	boolean returnTrue = false;

    	int i = 0;

    	int x = MathHelper.floor_double(this.posX);
    	int y = MathHelper.floor_double(this.boundingBox.minY);
    	int z = MathHelper.floor_double(this.posZ);

    	for (int k = y; k < 255; ++k)
    	{
    		Block block = this.worldObj.getBlock(x, k, z);

    		if (block.isNormalCube())
    		{
    			k = 255;
    			returnTrue = false;
    		}
    		else
    		{
    			if (k >= 255)
    			{
    				returnTrue = true;
    			}
    		}

    		i = k;
    	}

    	return returnTrue;
    }

	public boolean getCanSpawnHere()
	{
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.boundingBox.minY);
		int k = MathHelper.floor_double(this.posZ);
		Block block = this.worldObj.getBlock(i, j, k);
    	
		if (this.worldObj.isDaytime() && !this.worldObj.getWorldInfo().isThundering())
		{
			return this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && block.getMaterial() == Material.water && this.canSeaScorpionSeeSky() == true;
		}
		else
		{
			return this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && block.getMaterial() == Material.water;
		}
	}
}