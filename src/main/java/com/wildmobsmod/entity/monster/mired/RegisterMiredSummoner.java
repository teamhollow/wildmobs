package com.wildmobsmod.entity.monster.mired;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.registry.EntityRegistry;

import com.wildmobsmod.main.MainRegistry;

public class RegisterMiredSummoner {
	
	public static void mainRegistry(){
		registerModEntity();
	}
	
	public static void registerModEntity(){
		
		boolean flag = MainRegistry.enableDreath;
		if (flag)
		{
	    	createEntity(EntityMiredSummoner.class, "MiredSummoner", 0xFFFFFF, 0xFFFFFF);
		}
		
	}
	
	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor){
		EntityRegistry.registerModEntity(entityClass, entityName, 24, MainRegistry.modInstance, 82, 1, true);
	}
}