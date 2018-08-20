package com.wildmobsmod.entity.monster.zomgus;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import com.wildmobsmod.items.WildMobsModItems;

public class EntityZomgus extends EntityZombie
{
    public EntityZomgus(World p_i1732_1_)
    {
        super(p_i1732_1_);
        this.getNavigator().setAvoidsWater(true);
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
    }
    
    public boolean attackEntityAsMob(Entity p_70652_1_)
    {
        if (super.attackEntityAsMob(p_70652_1_))
        {
            if (p_70652_1_ instanceof EntityLivingBase)
            {
                byte b0 = 0;
                
                if (this.worldObj.difficultySetting == EnumDifficulty.EASY)
                {
                    b0 = 5;
                }
                else if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
                {
                    b0 = 8;
                }
                else if (this.worldObj.difficultySetting == EnumDifficulty.HARD)
                {
                    b0 = 10;
                }

                if (b0 > 0)
                {
                    ((EntityLivingBase)p_70652_1_).addPotionEffect(new PotionEffect(19, b0 * 20, 0));
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
    	super.onLivingUpdate();
    	
        if (this.isWet())
        {
            this.attackEntityFrom(DamageSource.drown, 1.0F);
        }
    }
    
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
        int j = this.rand.nextInt(3) + this.rand.nextInt(1 + p_70628_2_);
        
        for (int k = 0; k < j; ++k)
        {
            this.dropItem(WildMobsModItems.infectedFlesh, 1);
        }
    }
    
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_)
    {
        super.onSpawnWithEgg(p_110161_1_);
        this.setVillager(false);
        return p_110161_1_;
    }
}