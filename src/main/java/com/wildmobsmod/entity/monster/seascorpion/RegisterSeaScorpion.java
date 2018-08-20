package com.wildmobsmod.entity.monster.seascorpion;

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

public class RegisterSeaScorpion {
	
	public static void mainRegistry(){
		registerModEntity();
	}
	
	public static void registerModEntity(){
		
		createEntity(EntitySeaScorpion.class, "SeaScorpion", 0xFFFFFF, 0xFFFFFF);
		
	}
	
	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor){
		int i = MainRegistry.seaScorpionSpawnRate;
		boolean flag = MainRegistry.enableSeaScorpion;
		if (flag)
		{
	    	EntityRegistry.registerModEntity(entityClass, entityName, 26, MainRegistry.modInstance, 82, 2, true);
		}
	}
}