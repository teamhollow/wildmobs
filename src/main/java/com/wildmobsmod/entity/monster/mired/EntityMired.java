package com.wildmobsmod.entity.monster.mired;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.ImmutablePair;

import com.wildmobsmod.items.WildMobsModItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityMired extends EntityMob
{
	//
	// Mireds can now have items inside them, but only mired summoned by mired summoners should have items.
	//
	
	@Override
	protected void entityInit() {
		super.entityInit();
        this.dataWatcher.addObject(21, Byte.valueOf((byte)0));
		this.dataWatcher.addObject(22, new Integer(0));
	}
	
    public EntityMired(World p_i1740_1_)
    {
        super(p_i1740_1_);
        this.setSize(0.6F, 0.7F);
        this.isImmuneToFire = true;
        this.setLifeTime(200);
        this.setFlaming(false);
        this.experienceValue = 0;
    }
    
    public void writeEntityToNBT(NBTTagCompound entity)
    {
        super.writeEntityToNBT(entity);
        entity.setBoolean("Flaming", this.getFlaming());
        entity.setInteger("LifeTime", this.getLifeTime());
    }

    public void readEntityFromNBT(NBTTagCompound entity)
    {
        super.readEntityFromNBT(entity);
        this.setFlaming(entity.getBoolean("Flaming"));
        this.setLifeTime(entity.getInteger("LifeTime"));
    }
    
    public boolean getFlaming()
    {
        return (this.dataWatcher.getWatchableObjectByte(21) & 1) != 0;
    }

    public void setFlaming(boolean p_70900_1_)
    {
        if (p_70900_1_)
        {
            this.dataWatcher.updateObject(21, Byte.valueOf((byte)1));
        }
        else
        {
            this.dataWatcher.updateObject(21, Byte.valueOf((byte)0));
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

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "wildmobsmod:mob.mired.say";
    }

    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack()
    {
        double d0 = 8.0D;
        return this.worldObj.getClosestVulnerablePlayerToEntity(this, d0);
    }
    
    protected String getHurtSound()
    {
        return null;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return null;
    }
    
    protected void fall(float p_70069_1_) {}

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity p_70785_1_, float p_70785_2_)
    {
    	byte b0 = 0;

    	if (this.worldObj.difficultySetting == EnumDifficulty.EASY)
    	{
    		b0 = 3;
    	}
    	if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
    	{
    		b0 = 4;
    	}
    	else if (this.worldObj.difficultySetting == EnumDifficulty.HARD)
    	{
    		b0 = 6;
    	}

    	if (this.attackTime <= 0 && p_70785_2_ < 1.2F && p_70785_1_.boundingBox.maxY > this.boundingBox.minY && p_70785_1_.boundingBox.minY < this.boundingBox.maxY)
    	{
    		int i = this.worldObj.difficultySetting.getDifficultyId() * 2;
    		this.attackTime = 20;
    		this.attackEntityAsMob(p_70785_1_);
    		this.setDead();
    		if (this.rand.nextInt(2) == 0)
    		{
    			((EntityLivingBase)p_70785_1_).addPotionEffect(new PotionEffect(Potion.blindness.id, 20 * b0, 0));
    		}
    		if (this.getFlaming() == true)
    		{
    			p_70785_1_.setFire(i);
    		}
    	}
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.renderYawOffset = this.rotationYaw;
        if (this.isEntityAlive() == false)
        {
        	this.setCurrentItemOrArmor(0, null);
        }
        super.onUpdate();
    }
    
    protected void updateEntityActionState()
    {
        super.updateEntityActionState();

        if (!this.worldObj.isRemote)
        {
            int i;
            int j;
            int k;
            int i1;

            if (this.entityToAttack == null && !this.hasPath())
            {
                i = MathHelper.floor_double(this.posX);
                j = MathHelper.floor_double(this.posY + 0.5D);
                k = MathHelper.floor_double(this.posZ);
                int l1 = this.rand.nextInt(6);
                Block block = this.worldObj.getBlock(i + Facing.offsetsXForSide[l1], j + Facing.offsetsYForSide[l1], k + Facing.offsetsZForSide[l1]);
                i1 = this.worldObj.getBlockMetadata(i + Facing.offsetsXForSide[l1], j + Facing.offsetsYForSide[l1], k + Facing.offsetsZForSide[l1]);
                this.updateWanderPath();
            }
            else if (this.entityToAttack != null && !this.hasPath())
            {
                this.entityToAttack = null;
            }
        }
    }
    
    public void onLivingUpdate()
    {
        if (this.isWet() && this.getFlaming() == true)
        {
            this.setFlaming(false);
        }
        if (this.motionY < 0)
        {
        	this.motionY *= 0.7D;
        }
        for (int i = 0; i < 4; ++i)
        {
        	this.worldObj.spawnParticle("largesmoke", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
        	if (this.getFlaming() == true)
        	{
        		this.worldObj.spawnParticle("flame", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);		
        	}
        }
        if (this.worldObj.getBlock(MathHelper.floor_double(this.posX), (int)this.posY, MathHelper.floor_double(this.posZ)) == Blocks.fire)
        {
        	this.setFlaming(true);
        }
        else if (this.worldObj.getBlock(MathHelper.floor_double(this.posX), (int)this.posY, MathHelper.floor_double(this.posZ)) == Blocks.lava)
        {
        	this.setFlaming(true);
        }
		if (!this.worldObj.isRemote && this.isValidLightLevel() == true)
		{
			int i = this.getLifeTime();
			
			if (i <= 0)
			{
				this.setDead();
			}
			else
			{
				i--;
				this.setLifeTime(i);
			}
		}
		else if (!this.worldObj.isRemote && this.isValidLightLevel() == false)
		{
			int i = this.getLifeTime();
			int j;
			
			if (i <= 0)
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

    public float getBlockPathWeight(int p_70783_1_, int p_70783_2_, int p_70783_3_)
    {
        return super.getBlockPathWeight(p_70783_1_, p_70783_2_, p_70783_3_);
    }

    protected void dropFewItems(boolean f1, int f2)
    {
    	if (this.getEquipmentInSlot(0) != null)
    	{
    		this.dropItem(this.getEquipmentInSlot(0).getItem(), 1);
    	}
    }

    protected void dropEquipment(boolean p_82160_1_, int p_82160_2_)
    {
    }

    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
    	if (this.isEntityInvulnerable())
    	{
    		return false;
    	}
    	else
    	{
    		if (!this.worldObj.isRemote)
    		{
    			if (p_70097_1_ == DamageSource.inWall)
    			{
    				return false;
    			}
    			else
    			{
    				return super.attackEntityFrom(p_70097_1_, p_70097_2_);
    			}
    		}
    		else
    		{
    			return super.attackEntityFrom(p_70097_1_, p_70097_2_);
    		}
    	}
    }

    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float p_70070_1_)
    {
        return 15728880;
    }
    
    public float getBrightness(float p_70013_1_)
    {
        return 1.0F;
    }
}