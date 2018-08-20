package com.wildmobsmod.entity.monster.zomgus;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import cpw.mods.fml.common.registry.EntityRegistry;

import com.wildmobsmod.main.MainRegistry;

public class RegisterZomgus {
	
	public static void mainRegistry(){
		registerModEntity();
	}
	
	public static void registerModEntity(){
		
		createEntity(EntityZomgus.class, "Zomgus", 0x3AA68E, 0x9C8B87);
		
	}
	
	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor){
		int i = MainRegistry.zomgusSpawnRateMushroomIsland;
		int j = MainRegistry.zomgusSpawnRateRoofedForest;
		boolean flag = MainRegistry.enableZomgus;
		if (flag)
		{
	     	EntityRegistry.registerModEntity(entityClass, entityName, 4, MainRegistry.modInstance, 82, 3, true);
	    	EntityRegistry.addSpawn(entityClass, j, 4, 4, EnumCreatureType.monster, BiomeGenBase.getBiome(29), BiomeGenBase.getBiome(157), BiomeGenBase.getBiome(157));
	    	EntityRegistry.addSpawn(entityClass, i, 4, 4, EnumCreatureType.monster, BiomeGenBase.mushroomIsland, BiomeGenBase.mushroomIslandShore);
		}
	}
}
