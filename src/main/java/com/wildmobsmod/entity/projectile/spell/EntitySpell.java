package com.wildmobsmod.entity.projectile.spell;

import com.wildmobsmod.main.ClientProxy;
import com.wildmobsmod.main.WildMobsMod;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySpell extends EntityThrowable
{
	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(23, Byte.valueOf((byte) 0));
		this.dataWatcher.addObject(24, Byte.valueOf((byte) 0));
	}

	public EntitySpell(World world)
	{
		super(world);
		this.setSize(0.6F, 0.6F);
	}

	public EntitySpell(World world, EntityLivingBase thrower)
	{
		super(world, thrower);
	}

	public EntitySpell(World world, double xPos, double yPos, double zPos)
	{
		super(world, xPos, yPos, zPos);
	}

	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setInteger("LifeTime", this.getLifeTime());
		nbt.setInteger("Spell", this.getSpell());
	}

	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		this.setLifeTime(nbt.getInteger("LifeTime"));
		this.setSpell(nbt.getInteger("Spell"));
	}

	public int getSpell()
	{
		return this.dataWatcher.getWatchableObjectByte(24);
	}

	public void setSpell(int spellId)
	{
		this.dataWatcher.updateObject(24, Byte.valueOf((byte) spellId));
	}

	public int getLifeTime()
	{
		return this.dataWatcher.getWatchableObjectByte(23);
	}

	public void setLifeTime(int ticks)
	{
		this.dataWatcher.updateObject(23, Byte.valueOf((byte) ticks));
	}

	public void onUpdate()
	{
		int age = this.getLifeTime();
		if(this.worldObj.isRemote && age > 0)
		{
			switch(this.getSpell())
			{
				case 0 : ClientProxy.generateEntitySpellFX(this, this.posX, this.posY, this.posZ, this.motionX * 0.5, this.motionY * 0.5, this.motionZ * 0.5, 0.2F, 0.8F, 0.0F); break;
				case 1 : ClientProxy.generateEntitySpellFX(this, this.posX, this.posY, this.posZ, this.motionX * 0.5, this.motionY * 0.5, this.motionZ * 0.5, 0.6F, 0.2F, 0.0F); break;
				case 2 : ClientProxy.generateEntitySpellFX(this, this.posX, this.posY, this.posZ, this.motionX * 0.5, this.motionY * 0.5, this.motionZ * 0.5, 0.5F, 0.8F, 0.3F); break;
			}
		}
		if(age <= 80)
		{
			age++;
			this.setLifeTime(age);
		}
		else
		{
			this.setDead();
		}
		this.motionY += (double) this.getGravityVelocity(); // negate gravity
		super.onUpdate();
	}

	protected void onImpact(MovingObjectPosition pos)
	{
		if(!this.worldObj.isRemote && pos.entityHit != null && pos.entityHit.riddenByEntity != this.getThrower())
		{
			int power = 0;
			switch(this.worldObj.difficultySetting)
			{
				case NORMAL : power = 2; break;
				case HARD : power = 3; break;
				case EASY : power = 1; break;
				case PEACEFUL : power = 0; break;
			}
			switch(this.getSpell())
			{
				case 0 : pos.entityHit.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.getThrower()), power + 2F); break;
				case 1 : ((EntityLivingBase) pos.entityHit).addPotionEffect(new PotionEffect(WildMobsMod.aquaHealingID, power * 100, 0)); break;
			}
		}
	}
}
