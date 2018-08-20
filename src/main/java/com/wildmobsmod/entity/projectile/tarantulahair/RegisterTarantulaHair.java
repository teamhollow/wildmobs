package com.wildmobsmod.entity.projectile.tarantulahair;

import com.wildmobsmod.main.MainRegistry;

import cpw.mods.fml.common.registry.EntityRegistry;

public class RegisterTarantulaHair {
	
	public static void mainRegistry(){
		registerModEntity();
	}
	
	public static void registerModEntity(){
		
		boolean flag = MainRegistry.enableTarantula;
		if (flag)
		{
	    	createEntity(EntityTarantulaHair.class, "TarantulaHair", 0xFFFFFF, 0xFFFFFF);
		}
		
	}
	
	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor){
		EntityRegistry.registerModEntity(entityClass, entityName, 30, MainRegistry.modInstance, 82, 1, true);
	}
}