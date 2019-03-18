package com.wildmobsmod.entity.bases;

import java.util.Iterator;
import java.util.List;

import com.wildmobsmod.entity.passive.deer.EntityDeer;
import com.wildmobsmod.entity.passive.goat.EntityGoat;
import com.wildmobsmod.items.WildMobsModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public abstract class EntityLargePredator extends EntityAnimal		// Adapted from original EntityCougar
{
	//
	// For some reason the mob won't register and trying to spawn it crashed the
	// game. Adding more "big cats" is recommended and I've talked about the big
	// cats more in depth on my Discord server.
	//
	
	public static final double INTRUDER_RANGE = 8.0D;

	private int angerTimer;
	private float attackDamage;
	protected EntityPlayer intruder;

	protected EntityLargePredator(World world, Class... preyClasses)
	{
		super(world);
		attackDamage = 4F; // EnumDifficulty.NORMAL
		if(this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
		{
			attackDamage *= 0.5F;
		}
		else if(this.worldObj.difficultySetting == EnumDifficulty.EASY)
		{
			attackDamage *= 0.75F;
		}
		else if(this.worldObj.difficultySetting == EnumDifficulty.HARD)
		{
			attackDamage *= 1.8F;
		}
		this.setSize(0.9F, 1.2F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAILeapAtTarget(this, 0.5F));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.5D, true));
		this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		for(Class prey : preyClasses) {
			this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, prey, 300, true));
		}
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	public void updateAITick()
	{
		if(this.getMoveHelper().isUpdating())
		{
			this.setSprinting(this.getMoveHelper().getSpeed() == 1.35D);
			this.setSneaking(false);
			this.angerTimer = 0;
		}
		else
		{
			if(this.getAttackTarget() != null && !this.isJumping)
			{
				this.angerTimer++;
				if(this.angerTimer > 30)
				{
					this.setSneaking(true);
				}
				else
				{
					this.setSneaking(false);
				}
				if(this.angerTimer == 32)
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

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
	}

	protected void fall(float distance) {}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(21, new Integer(0));
	}

	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		this.setAnger(nbt.getInteger("anger"));
	}

	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setInteger("anger", this.getAnger());
	}

	public int getAnger()
	{
		return this.dataWatcher.getWatchableObjectInt(21);
	}

	public void setAnger(int entity)
	{
		this.dataWatcher.updateObject(21, Integer.valueOf((entity)));
	}

	public boolean attackEntityAsMob(Entity target)
	{
		this.angerTimer = 0;
		return target.attackEntityFrom(DamageSource.causeMobDamage(this), attackDamage);
	}

	public float getEyeHeight()
	{
		return this.height * 1.0F;
	}

	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		this.angerTimer = 0;
		if(this.isEntityInvulnerable())
		{
			return false;
		}
		else
		{
			return super.attackEntityFrom(source, amount);
		}
	}

	public void onLivingUpdate() // aggregate aggression towards players less than 8m away and ultimately attack them; CAN see invisible players and ignores invulnerable ones
	{
		super.onLivingUpdate();
		int anger = getAnger();
		if(intruder == null) {
			EntityPlayer player = worldObj.getClosestPlayerToEntity(this, INTRUDER_RANGE);
			if(player != null && !player.capabilities.disableDamage) {
				anger++;
				if(anger > 200) {
					intruder = player;
					setAttackTarget(player);
				}
			} else {
				anger--;
			}
		} else {
			EntityLivingBase target = getAttackTarget();
			if(target != null && getDistanceToEntity(intruder) > (anger > 0 ? INTRUDER_RANGE * 2 : INTRUDER_RANGE)) {
				if(target == intruder) {
					setAttackTarget(null);
				}
				intruder = null;
			}
			anger--;
		}
		this.setAnger(Math.max(anger, 0));
	}
	

	protected Item getDropItem()
	{
		return WildMobsModItems.fur;
	}

	protected void dropFewItems(boolean playerkill, int looting)
	{
		int j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + looting);

		for(int k = 0; k < j; ++k)
		{
			this.dropItem(WildMobsModItems.fur, 1);
		}
	}


	protected int getExperiencePoints(EntityPlayer player)
	{
		return 7;
	}

	public abstract int getMaxSpawnedInChunk();
	
	protected abstract String getLivingSound();

	protected abstract String getHurtSound();

	protected abstract String getDeathSound();

	protected float getSoundVolume()
	{
		return 0.4F;
	}
}
