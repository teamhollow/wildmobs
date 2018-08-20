package com.wildmobsmod.misc;
import com.wildmobsmod.entity.passive.cougar.EntityCougar;
import com.wildmobsmod.entity.passive.ocelot.EntityWMOcelot;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class CreeperAvoidWMOcelotHandler
{ 
	@SubscribeEvent
	public void OnEntityJoinWorld(EntityJoinWorldEvent event) 
	{
		if(!event.world.isRemote) 
		{
			if (event.entity.getClass() == EntityCreeper.class) 
			{
				((EntityLiving)event.entity).tasks.addTask(3, new EntityAIAvoidEntity((EntityCreature) event.entity, EntityWMOcelot.class, 6.0F, 1.0D, 1.2D));
			}
		}
	}
}