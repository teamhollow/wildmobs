package com.wildmobsmod.entity.projectile.seascorpionegg;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.wildmobsmod.entity.monster.seascorpion.EntitySeaScorpion;
import com.wildmobsmod.items.WildMobsModItems;

public class EntitySeaScorpionEgg extends EntityThrowable
{
	public EntitySeaScorpionEgg(World p_i1779_1_)
	{
		super(p_i1779_1_);
	}

	public EntitySeaScorpionEgg(World p_i1780_1_, EntityLivingBase p_i1780_2_)
	{
		super(p_i1780_1_, p_i1780_2_);
	}

	public EntitySeaScorpionEgg(World p_i1781_1_, double p_i1781_2_, double p_i1781_4_, double p_i1781_6_)
	{
		super(p_i1781_1_, p_i1781_2_, p_i1781_4_, p_i1781_6_);
	}

	protected void onImpact(MovingObjectPosition p_70184_1_)
	{
		if (p_70184_1_.entityHit != null)
		{
			p_70184_1_.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
		}

		if (!this.worldObj.isRemote)
		{
			EntitySeaScorpion entityseascorpion = new EntitySeaScorpion(this.worldObj);
			entityseascorpion.setGrowingAge(-24000);
			entityseascorpion.setIsWild(false);
			entityseascorpion.setSize(2);
			entityseascorpion.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
			this.worldObj.spawnEntityInWorld(entityseascorpion);
		}

		String s = "iconcrack_" + Item.getIdFromItem(WildMobsModItems.seaScorpionEgg);

		for (int j = 0; j < 8; ++j)
		{
			this.worldObj.spawnParticle(s, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}

		if (!this.worldObj.isRemote)
		{
			this.setDead();
		}
	}
}