package com.wildmobsmod.entity.monster.wizard;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.registry.EntityRegistry;

import com.wildmobsmod.main.MainRegistry;

public class RegisterWizard {
	
	public static void mainRegistry(){
		registerModEntity();
	}
	
	public static void registerModEntity(){
		
		createEntity(EntityWizard.class, "Wizard", 0x4F5678, 0xB5A59B);
		
	}
	
	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor){
		boolean flag = MainRegistry.enableWizard;
		if (flag)
		{
	    	EntityRegistry.registerModEntity(entityClass, entityName, 6, MainRegistry.modInstance, 82, 3, true);	
    	}
	}
}