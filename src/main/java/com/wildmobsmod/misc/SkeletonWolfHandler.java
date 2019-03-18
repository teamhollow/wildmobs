package com.wildmobsmod.misc;

import com.wildmobsmod.entity.monster.skeletonwolf.EntitySkeletonWolf;
import com.wildmobsmod.main.WildMobsMod;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class SkeletonWolfHandler
{
	@SubscribeEvent(priority=EventPriority.LOW)
	public void onSkeletonSpawn(EntityJoinWorldEvent event)
	{
		if(event.entity instanceof EntitySkeleton)
		{
			EntitySkeleton skeleton = (EntitySkeleton) event.entity;
			skeleton.tasks.addTask(6, new EntityAIWatchClosest(skeleton, EntitySkeletonWolf.class, 8.0F)); // tasks aren't saved
			if(WildMobsMod.checkIsEntityNew(event.entity) && Math.random() * 100 < WildMobsMod.skeletonWolfChance)
			{
				EntitySkeletonWolf wolf = new EntitySkeletonWolf(event.world);
				wolf.setLocationAndAngles(skeleton.posX, skeleton.posY, skeleton.posZ, skeleton.rotationYaw, 0.0F);
				wolf.onSpawnWithEgg((IEntityLivingData) null);
				wolf.setSkeletonType(skeleton.getSkeletonType() == 1 ? 1 : 0);
				event.world.spawnEntityInWorld(wolf);
				wolf.entityToFollow = skeleton;
			}
		}
	}
}
