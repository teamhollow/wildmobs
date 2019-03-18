package com.wildmobsmod.entity.monster.dreath.mired;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityMired extends EntityMob
{
	//
	// Mireds can now have items inside them, but only mired summoned by mired
	// summoners should have items.
	//

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(21, Byte.valueOf((byte) 0));
		this.dataWatcher.addObject(22, new Integer(0));
	}

	public EntityMired(World world)
	{
		super(world);
		this.setSize(0.6F, 0.7F);
		this.isImmuneToFire = true;
		this.setLifeTime(200);
		this.setFlaming(false);
		this.experienceValue = 0;
	}

	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setBoolean("Flaming", this.getFlaming());
		nbt.setInteger("LifeTime", this.getLifeTime());
	}

	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		this.setFlaming(nbt.getBoolean("Flaming"));
		this.setLifeTime(nbt.getInteger("LifeTime"));
	}

	public boolean getFlaming()
	{
		return (this.dataWatcher.getWatchableObjectByte(21) & 1) != 0;
	}

	public void setFlaming(boolean flag)
	{
		if(flag)
		{
			this.dataWatcher.updateObject(21, Byte.valueOf((byte) 1));
		}
		else
		{
			this.dataWatcher.updateObject(21, Byte.valueOf((byte) 0));
		}
	}

	public int getLifeTime()
	{
		return this.dataWatcher.getWatchableObjectInt(22);
	}

	public void setLifeTime(int entity)
	{
		this.dataWatcher.updateObject(22, Integer.valueOf((entity)));
	}

	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.UNDEAD;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.9500000238418579D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
	}

	protected boolean canTriggerWalking()
	{
		return false;
	}

	protected String getLivingSound()
	{
		return "wildmobsmod:entity.mired.say";
	}

	protected Entity findPlayerToAttack()
	{
		return this.worldObj.getClosestVulnerablePlayerToEntity(this, 8.0D);
	}

	protected String getHurtSound()
	{
		return null;
	}

	protected String getDeathSound()
	{
		return null;
	}

	protected void fall(float distance) {}

	protected void attackEntity(Entity target, float distance)
	{
		byte difficultyFactor = 0;

		if(this.worldObj.difficultySetting == EnumDifficulty.EASY)
		{
			difficultyFactor = 3;
		}
		if(this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
		{
			difficultyFactor = 4;
		}
		else if(this.worldObj.difficultySetting == EnumDifficulty.HARD)
		{
			difficultyFactor = 6;
		}

		if(this.attackTime <= 0 && distance < 1.2F && target.boundingBox.maxY > this.boundingBox.minY && target.boundingBox.minY < this.boundingBox.maxY)
		{
			int i = this.worldObj.difficultySetting.getDifficultyId() * 2;
			this.attackTime = 20;
			this.attackEntityAsMob(target);
			this.setDead();
			if(this.rand.nextInt(2) == 0)
			{
				((EntityLivingBase) target).addPotionEffect(new PotionEffect(Potion.blindness.id, 20 * difficultyFactor, 0));
			}
			if(this.getFlaming() == true)
			{
				target.setFire(i);
			}
		}
	}

	public void onUpdate()
	{
		this.renderYawOffset = this.rotationYaw;
		if(this.isEntityAlive() == false)
		{
			this.setCurrentItemOrArmor(0, null);
		}
		super.onUpdate();
	}

	protected void updateEntityActionState()
	{
		super.updateEntityActionState();

		if(!this.worldObj.isRemote)
		{
			int i;
			int j;
			int k;
			int i1;

			if(this.entityToAttack == null && !this.hasPath())
			{
				i = MathHelper.floor_double(this.posX);
				j = MathHelper.floor_double(this.posY + 0.5D);
				k = MathHelper.floor_double(this.posZ);
				int l1 = this.rand.nextInt(6);
				Block block = this.worldObj.getBlock(i + Facing.offsetsXForSide[l1], j + Facing.offsetsYForSide[l1], k + Facing.offsetsZForSide[l1]);
				i1 = this.worldObj.getBlockMetadata(i + Facing.offsetsXForSide[l1], j + Facing.offsetsYForSide[l1], k + Facing.offsetsZForSide[l1]);
				this.updateWanderPath();
			}
			else if(this.entityToAttack != null && !this.hasPath())
			{
				this.entityToAttack = null;
			}
		}
	}

	public void onLivingUpdate()
	{
		if(this.isWet() && this.getFlaming() == true)
		{
			this.setFlaming(false);
		}
		if(this.motionY < 0)
		{
			this.motionY *= 0.7D;
		}
		for(int i = 0; i < 4; ++i)
		{
			this.worldObj.spawnParticle("largesmoke", this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width, this.posY + this.rand.nextDouble() * (double) this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width, 0.0D, 0.0D, 0.0D);
			if(this.getFlaming() == true)
			{
				this.worldObj.spawnParticle("flame", this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width, this.posY + this.rand.nextDouble() * (double) this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width, 0.0D, 0.0D, 0.0D);
			}
		}
		if(this.worldObj.getBlock(MathHelper.floor_double(this.posX), (int) this.posY, MathHelper.floor_double(this.posZ)) == Blocks.fire)
		{
			this.setFlaming(true);
		}
		else if(this.worldObj.getBlock(MathHelper.floor_double(this.posX), (int) this.posY, MathHelper.floor_double(this.posZ)) == Blocks.lava)
		{
			this.setFlaming(true);
		}
		if(!this.worldObj.isRemote && this.isValidLightLevel() == true)
		{
			int i = this.getLifeTime();

			if(i <= 0)
			{
				this.setDead();
			}
			else
			{
				i--;
				this.setLifeTime(i);
			}
		}
		else if(!this.worldObj.isRemote && this.isValidLightLevel() == false)
		{
			int i = this.getLifeTime();
			int j;

			if(i <= 0)
			{
				this.setDead();
			}
			else
			{
				j = i - 2;
				this.setLifeTime(j);
			}
		}

		super.onLivingUpdate();
	}

	public float getBlockPathWeight(int x, int y, int z)
	{
		return super.getBlockPathWeight(x, y, z);
	}

	protected void dropFewItems(boolean playerkill, int looting)
	{
		if(this.getEquipmentInSlot(0) != null)
		{
			this.entityDropItem(getEquipmentInSlot(0), 0.0F);
		}
	}

	protected void dropEquipment(boolean playerkill, int looting) {}

	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		return !this.worldObj.isRemote && source == DamageSource.inWall ? false : super.attackEntityFrom(source, amount);
	}

	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float partialTickTime)
	{
		return 15728880;
	}

	public float getBrightness(float partialTickTime)
	{
		return 1.0F;
	}
}
