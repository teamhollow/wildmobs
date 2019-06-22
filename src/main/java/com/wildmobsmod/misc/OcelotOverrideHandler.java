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
		final Entity entity = event.entity;
		if(entity.getClass() == EntityOcelot.class && WildMobsMod.checkIsEntityNew(entity) && Math.random() * 100 < WildMobsMod.OCELOT_CONFIG.getOverrideChance())
		{
			EntityOcelot ocelot = (EntityOcelot) entity;
			EntityWMOcelot newOcelot = new EntityWMOcelot(event.world);
			newOcelot.setLocationAndAngles(ocelot.posX, ocelot.posY, ocelot.posZ, ocelot.rotationYaw, ocelot.rotationPitch);
			newOcelot.setGrowingAge(ocelot.getGrowingAge());
			ocelot.setDead();
			event.setCanceled(true);
			event.world.spawnEntityInWorld(newOcelot);
		}
	}
}
