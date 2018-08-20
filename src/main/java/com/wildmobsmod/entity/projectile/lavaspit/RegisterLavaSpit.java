package com.wildmobsmod.entity.projectile.lavaspit;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.registry.EntityRegistry;

import com.wildmobsmod.main.MainRegistry;

public class RegisterLavaSpit {
	
	public static void mainRegistry(){
		registerModEntity();
	}
	
	public static void registerModEntity(){
		
		boolean flag = MainRegistry.enableMagmaPlant;
		if (flag)
		{
	    	createEntity(EntityLavaSpit.class, "LavaSpit", 0xFFFFFF, 0xFFFFFF);
		}
		
	}
	
	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor){
		EntityRegistry.registerModEntity(entityClass, entityName, 16, MainRegistry.modInstance, 82, 1, true);
	}
}