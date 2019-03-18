package com.wildmobsmod.misc;

import com.wildmobsmod.entity.monster.seascorpion.EntitySeaScorpion;
import com.wildmobsmod.main.WildMobsMod;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class SeaScorpionSpecialSpawnHandler
{
	@SubscribeEvent(priority=EventPriority.LOW)
	public void onSpawnSeaScorpion(EntityJoinWorldEvent event)
	{
		if(event.entity.getClass() == EntitySeaScorpion.class && WildMobsMod.checkIsEntityNew(event.entity))
		{
			EntitySeaScorpion scorpion = (EntitySeaScorpion) event.entity;
			if(scorpion.posY <= 40 && event.world.rand.nextInt(20) == 0)
			{
				scorpion.setSize(10);
				scorpion.setIsSeaMonster(true);
			}
			else
			{
				if(event.world.rand.nextInt(3) == 0) scorpion.trySpawnPack();
			}
		}
	}
}
