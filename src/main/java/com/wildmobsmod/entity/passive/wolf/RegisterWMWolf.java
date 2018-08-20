package com.wildmobsmod.entity.passive.wolf;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager.BiomeType;
import cpw.mods.fml.common.registry.EntityRegistry;

import com.wildmobsmod.main.MainRegistry;

public class RegisterWMWolf {
	
	public static void mainRegistry(){
		registerModEntity();
	}
	
	public static void registerModEntity(){
		
		createEntity(EntityWMWolf.class, "Wolf", 0xB5B5B5, 0x965A3E);
		
	}
	
	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor){
		boolean flag = MainRegistry.enableImprovedWolves;
		if (flag)
		{
	    	EntityRegistry.registerModEntity(entityClass, entityName, 18, MainRegistry.modInstance, 82, 3, true);
		}
	}
}