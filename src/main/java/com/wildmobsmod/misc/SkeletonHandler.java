package com.wildmobsmod.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import com.wildmobsmod.entity.monster.skeleton.EntityWMSkeleton;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SkeletonHandler
{ 
	@SubscribeEvent
	public void OnLivingUpdate(LivingUpdateEvent event)
	{
		if(!event.entity.worldObj.isRemote) 
		{
			if (event.entity.getClass() == EntitySkeleton.class && event.entity.ticksExisted > 2) 
			{
				EntityWMSkeleton spawnEntity = new EntityWMSkeleton(event.entity.worldObj);
				spawnEntity.setPosition(event.entity.posX, event.entity.posY, event.entity.posZ);
				spawnEntity.onSpawnWithEgg((IEntityLivingData)null);
				event.entity.worldObj.spawnEntityInWorld(spawnEntity);
				Entity riddenEntity = event.entity.ridingEntity;
				event.entity.mountEntity(null);
				event.entity.setDead();
				spawnEntity.mountEntity(riddenEntity);
			}
		}
	}
}