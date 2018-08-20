package com.wildmobsmod.entity.projectile.lavaspit;

import com.wildmobsmod.main.MainRegistry;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityLavaSpit extends EntityThrowable
{
    private static final String __OBFID = "CL_00001722";

	@Override
	protected void entityInit() {
	}
	
    public EntityLavaSpit(World p_i1773_1_)
    {
        super(p_i1773_1_);
        this.setSize(0.5F, 0.5F);
    }

    public EntityLavaSpit(World p_i1774_1_, EntityLivingBase p_i1774_2_)
    {
        super(p_i1774_1_, p_i1774_2_);
    }

    public EntityLavaSpit(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_)
    {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }
    
    public void onUpdate()
    {
    	int i;
    	for (i = 0; i < 2; ++i)
    	{
			MainRegistry.proxy.generateEntityMagmaSpitFX(this, this.posX, this.posY, this.posZ, this.worldObj.rand.nextGaussian() * 0.01D, this.worldObj.rand.nextGaussian() * 0.01D, this.worldObj.rand.nextGaussian() * 0.01D);
    	}
    	for (i = 0; i < 2; ++i)
    	{
    		this.worldObj.spawnParticle("smoke", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
    	}
        if (this.isWet())
        {
    		this.setDead();
    	}

    	super.onUpdate();
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition p_70227_1_)
    {
    	if (!this.worldObj.isRemote)
    	{
    		if (p_70227_1_.entityHit != null)
    		{
    			float f = 0;

    			if (this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
    			{
    				f = 0;
    			}
    			else if (this.worldObj.difficultySetting == EnumDifficulty.EASY)
    			{
    				f = 4;
    			}
    			else if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
    			{
    				f = 5;
    			}
    			else if (this.worldObj.difficultySetting == EnumDifficulty.HARD)
    			{
    				f = 6;
    			}

    			if (p_70227_1_.entityHit.isImmuneToFire() == false)
    			{
    				p_70227_1_.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)f);
    			}

    			if (p_70227_1_.entityHit instanceof EntityLivingBase)
    			{
    				int i = 0;

        			if (this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
        			{
        				i = 0;
        			}
        			else if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
    				{
    					i = 4;
    				}
    				else if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
    				{
    					i = 5;
    				}
    				else if (this.worldObj.difficultySetting == EnumDifficulty.HARD)
    				{
    					i = 6;
    				}

    				p_70227_1_.entityHit.setFire(i);
    			}
    		}
    		this.setDead();
    	}
    }
}