package com.wildmobsmod.entity.projetile.spell;

import com.wildmobsmod.entity.monster.magmaplant.EntityMagmaPlant;
import com.wildmobsmod.entity.monster.skeleton.EntityWMSkeleton;
import com.wildmobsmod.entity.monster.skeletonwolf.EntitySkeletonWolf;
import com.wildmobsmod.entity.monster.zomgus.EntityZomgus;
import com.wildmobsmod.entity.passive.jellyfish.EntityJellyfish;
import com.wildmobsmod.main.MainRegistry;

import cpw.mods.fml.common.FMLCommonHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntitySpell extends EntityThrowable
{  
	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(23, Byte.valueOf((byte)0));
		this.dataWatcher.addObject(24, Byte.valueOf((byte)0));
	}
	
    public EntitySpell(World p_i1773_1_)
    {
        super(p_i1773_1_);
        this.setSize(0.6F, 0.6F);
    }

    public EntitySpell(World p_i1774_1_, EntityLivingBase p_i1774_2_)
    {
    	super(p_i1774_1_, p_i1774_2_);
    }

    public EntitySpell(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_)
    {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }
    
    public void writeEntityToNBT(NBTTagCompound entity)
    {
        super.writeEntityToNBT(entity);
        entity.setInteger("LifeTime", this.getLifeTime());
        entity.setInteger("Spell", this.getSpell());
    }

    public void readEntityFromNBT(NBTTagCompound entity)
    {
        super.readEntityFromNBT(entity);
        this.setSpell(entity.getInteger("Spell"));
    }
    
    public int getSpell()
    {
        return this.dataWatcher.getWatchableObjectByte(24);
    }

    public void setSpell(int entity)
    {
        this.dataWatcher.updateObject(24, Byte.valueOf((byte)entity));
    }
    
    public int getLifeTime()
    {
        return this.dataWatcher.getWatchableObjectByte(23);
    }

    public void setLifeTime(int entity)
    {
        this.dataWatcher.updateObject(23, Byte.valueOf((byte)entity));
    }

    public void onUpdate()
    {
    	int i;
    	if (this.getLifeTime() > 0 && this.getSpell() == 0)
    	{
    		for (i = 0; i < 1; ++i)
    		{
    			MainRegistry.proxy.generateEntitySpellFX(this, this.posX, this.posY, this.posZ, this.motionX * 0.5, this.motionY * 0.5, this.motionZ * 0.5, 0.2F, 0.8F, 0.0F);
    		}
    	}
    	else if (this.getLifeTime() > 0 && this.getSpell() == 1)
    	{
    		for (i = 0; i < 1; ++i)
    		{
    			MainRegistry.proxy.generateEntitySpellFX(this, this.posX, this.posY, this.posZ, this.motionX * 0.5, this.motionY * 0.5, this.motionZ * 0.5, 0.6F, 0.2F, 0.0F);
    		}
    	}
    	else if (this.getLifeTime() > 0 && this.getSpell() == 2)
    	{
    		for (i = 0; i < 1; ++i)
    		{
    			MainRegistry.proxy.generateEntitySpellFX(this, this.posX, this.posY, this.posZ, this.motionX * 0.5, this.motionY * 0.5, this.motionZ * 0.5, 0.5F, 0.8F, 0.3F);
    		}
    	}

		int j = this.getLifeTime();
    	if (this.getLifeTime() <= 80)
    	{
    		++j;
    		this.setLifeTime(j);
    	}
    	else
    	{
    		this.setDead();
    	}

        float f3 = this.getGravityVelocity();

        this.motionY += (double)f3;

        super.onUpdate();
    }

    protected void onImpact(MovingObjectPosition p_70227_1_)
    {
    	if (!this.worldObj.isRemote)
    	{
    		if (this.getSpell() == 0 && p_70227_1_.entityHit != null && p_70227_1_.entityHit.riddenByEntity != this.getThrower())
    		{
    			float f = 0;

    			if (this.worldObj.difficultySetting == EnumDifficulty.EASY)
    			{
    				f = 3;
    			}
    			if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
    			{
    				f = 4;
    			}
    			else if (this.worldObj.difficultySetting == EnumDifficulty.HARD)
    			{
    				f = 5;
    			}

    			p_70227_1_.entityHit.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.getThrower()), (float)f);
    		}
    		else if (this.getSpell() == 1 && p_70227_1_.entityHit != null && p_70227_1_.entityHit.riddenByEntity != this.getThrower())
    		{
                byte b0 = 0;

                if (this.worldObj.difficultySetting == EnumDifficulty.EASY)
                {
                    b0 = 5;
                }
                else if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
                {
                    b0 = 10;
                }
                else if (this.worldObj.difficultySetting == EnumDifficulty.HARD)
                {
                    b0 = 15;
                }
                
    			((EntityLivingBase)p_70227_1_.entityHit).addPotionEffect(new PotionEffect(MainRegistry.aquaHealingID, b0 * 20, 0));
    		}
    	}
    }
}