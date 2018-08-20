package com.wildmobsmod.entity.bases;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.world.World;

public abstract class EntityRideable extends EntityAnimal{
	
	@Override
	protected void entityInit() {
		super.entityInit();
        this.dataWatcher.addObject(21, Byte.valueOf((byte)0));
        this.dataWatcher.addObject(22, Byte.valueOf((byte)0));
	}
    
	public EntityRideable(World par1World) {
		super(par1World);
	}

    public void writeEntityToNBT(NBTTagCompound entity)
    {
        super.writeEntityToNBT(entity);
        entity.setBoolean("Saddle", this.getSaddled());
        entity.setBoolean("IsTame", this.getSaddled());
    }

    public void readEntityFromNBT(NBTTagCompound entity)
    {
        super.readEntityFromNBT(entity);
        this.setSaddled(entity.getBoolean("Saddle"));
        this.setSaddled(entity.getBoolean("IsTame"));
    }
    
    public boolean getSaddled()
    {
        return (this.dataWatcher.getWatchableObjectByte(21) & 1) != 0;
    }

    public void setSaddled(boolean p_70900_1_)
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
    
    public boolean getTamed()
    {
        return (this.dataWatcher.getWatchableObjectByte(22) & 1) != 0;
    }

    public void setTamed(boolean p_70900_1_)
    {
        if (p_70900_1_)
        {
            this.dataWatcher.updateObject(22, Byte.valueOf((byte)1));
        }
        else
        {
            this.dataWatcher.updateObject(22, Byte.valueOf((byte)0));
        }
    }

    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(byte p_70103_1_)
    {
        if (p_70103_1_ == 7)
        {
            this.playTameEffect(true);
        }
        else if (p_70103_1_ == 6)
        {
            this.playTameEffect(false);
        }
        else
        {
            super.handleHealthUpdate(p_70103_1_);
        }
    }
    
    protected void playTameEffect(boolean p_70908_1_)
    {
        String s = "heart";

        if (!p_70908_1_)
        {
            s = "smoke";
        }

        for (int i = 0; i < 7; ++i)
        {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            this.worldObj.spawnParticle(s, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
        }
    }
}