package com.wildmobsmod.entity.passive.hyena;

import com.wildmobsmod.entity.ai.EntityAINocturnalActions;
import com.wildmobsmod.entity.bases.EntityLargePredator;
import com.wildmobsmod.entity.passive.armadillo.EntityArmadillo;
import com.wildmobsmod.entity.passive.bison.EntityBison;
import com.wildmobsmod.entity.passive.deer.EntityDeer;
import com.wildmobsmod.entity.passive.goat.EntityGoat;
import com.wildmobsmod.main.WildMobsMod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityHyena extends EntityLargePredator
{
	protected static final float NOCTURNAL_LIGHTLEVEL = 0.5F;
	
	public EntityHyena(World world)
	{
		super(world, EntityArmadillo.class, EntityHorse.class, EntityBison.class, EntityCow.class, EntitySheep.class, EntityDeer.class, EntityGoat.class);
		this.setSize(0.9F, 1.3F);
		targetTasks.addTask(5, new EntityAINocturnalActions(this, new EntityAINearestAttackableTarget(this, EntityVillager.class, 500, true), NOCTURNAL_LIGHTLEVEL, true));
	}

	public int getMaxSpawnedInChunk()
	{
		return WildMobsMod.HYENA_CONFIG.getMaxPackSize();
	}
	
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
	}
	
	@Override
	protected Entity findPlayerToAttack() // Aggressive at night (similar to spiders, but with smaller engagement range)
	{
		return WildMobsMod.HYENA_CONFIG.getAttackPlayersAtNight() && worldObj.difficultySetting != EnumDifficulty.PEACEFUL && getBrightness(1.0F) < NOCTURNAL_LIGHTLEVEL ? worldObj.getClosestVulnerablePlayerToEntity(this, INTRUDER_RANGE) : null;
	}

	protected String getLivingSound()
	{
		return this.getAttackTarget() != null ? "wildmobsmod:entity.hyena.angry" : "wildmobsmod:entity.hyena.idle";
	}

	protected String getHurtSound()
	{
		return "wildmobsmod:entity.hyena.hurt";
	}

	protected String getDeathSound()
	{
		return "wildmobsmod:entity.hyena.hurt";
	}
	
	@Override
	public EntityHyena createChild(EntityAgeable entity)
	{
		return null; //TODO Cubs
	}
}
