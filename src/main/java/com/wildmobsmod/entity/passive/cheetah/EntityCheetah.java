package com.wildmobsmod.entity.passive.cheetah;

import com.wildmobsmod.entity.bases.EntityLargePredator;
import com.wildmobsmod.entity.passive.bison.EntityBison;
import com.wildmobsmod.entity.passive.deer.EntityDeer;
import com.wildmobsmod.entity.passive.goat.EntityGoat;
import com.wildmobsmod.main.WildMobsMod;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.world.World;

public class EntityCheetah extends EntityLargePredator
{
	public EntityCheetah(World world)
	{
		super(world, EntityBison.class, EntityCow.class, EntitySheep.class, EntityDeer.class,  EntityGoat.class,  EntityChicken.class);
		this.setSize(0.9F, 1.0F);
	}

	public int getMaxSpawnedInChunk()
	{
		return WildMobsMod.CHEETAH_CONFIG.getMaxPackSize();
	}
	
	protected String getLivingSound()
	{
		return this.getAttackTarget() != null ? "wildmobsmod:entity.cheetah.angry" : null;
	}

	protected String getHurtSound()
	{
		return "wildmobsmod:entity.cougar.hurt";	// TODO: Own sound
	}

	protected String getDeathSound()
	{
		return "wildmobsmod:entity.cougar.hurt";	// TODO: Own sound
	}
	
	@Override
	public EntityCheetah createChild(EntityAgeable entity)
	{
		return null;
	}
}
