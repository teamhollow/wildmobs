package com.wildmobsmod.misc;

import com.wildmobsmod.entity.passive.wolf.EntityWMWolf;
import com.wildmobsmod.main.WildMobsMod;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class WolfOverrideHandler
{
	@SubscribeEvent(priority=EventPriority.HIGHEST)
	public void onWolfSpawn(EntityJoinWorldEvent event)
	{
		final Entity entity = event.entity;
		if(entity.getClass() == EntityWolf.class && WildMobsMod.checkIsEntityNew(entity) && Math.random() * 100 < WildMobsMod.WOLF_CONFIG.getOverrideChance())
		{
			EntityWolf wolf = (EntityWolf) entity;
			EntityWMWolf newWolf = new EntityWMWolf(event.world);
			newWolf.setLocationAndAngles(wolf.posX, wolf.posY, wolf.posZ, wolf.rotationYaw, wolf.rotationPitch);
			newWolf.setGrowingAge(wolf.getGrowingAge());
			wolf.setDead();
			event.setCanceled(true);
			event.world.spawnEntityInWorld(newWolf);
		}
	}
}
