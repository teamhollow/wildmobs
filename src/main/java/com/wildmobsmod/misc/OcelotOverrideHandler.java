package com.wildmobsmod.misc;

import com.wildmobsmod.entity.passive.ocelot.EntityWMOcelot;
import com.wildmobsmod.main.WildMobsMod;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class OcelotOverrideHandler
{
	@SubscribeEvent(priority=EventPriority.HIGHEST)
	public void onOcelotSpawn(EntityJoinWorldEvent event)
	{
		if(event.entity.getClass() == EntityOcelot.class && WildMobsMod.checkIsEntityNew(event.entity) && Math.random() * 100 < WildMobsMod.OCELOT_CONFIG.getOverrideChance())
		{
			EntityOcelot ocelot = (EntityOcelot) event.entity;
			EntityWMOcelot newOcelot = new EntityWMOcelot(event.world);
			newOcelot.setPosition(ocelot.posX, ocelot.posY, ocelot.posZ);
			newOcelot.setAngles(ocelot.rotationPitch, ocelot.rotationYaw);
			newOcelot.setGrowingAge(ocelot.getGrowingAge());
			ocelot.setDead();
			event.setCanceled(true);
			event.world.spawnEntityInWorld(newOcelot);
		}
	}
}
