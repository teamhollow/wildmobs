package com.wildmobsmod.misc;

import com.wildmobsmod.entity.monster.skeletonwolf.EntitySkeletonWolf;
import com.wildmobsmod.entity.passive.cougar.EntityCougar;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SkeletonLookAtSkeletonWolfHandler
{ 
	@SubscribeEvent
	public void OnEntityJoinWorld(EntityJoinWorldEvent event) 
	{
		if(!event.world.isRemote) 
		{
			if (event.entity.getClass() == EntitySkeleton.class) 
			{
				((EntityLiving)event.entity).tasks.addTask(6, new EntityAIWatchClosest((EntityCreature) event.entity, EntitySkeletonWolf.class, 8.0F));
			}
		}
	}
}