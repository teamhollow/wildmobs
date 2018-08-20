package com.wildmobsmod.entity.monster.faded;

import java.util.ArrayList;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

import com.wildmobsmod.main.MainRegistry;

import cpw.mods.fml.common.registry.EntityRegistry;

public class RegisterFaded {

	public static void mainRegistry(){
		registerModEntity();
	}

	public static void registerModEntity()
	{
		createEntity(EntityFaded.class, "Faded", 0xFFFFFF, 0xFFFFFF);
	}

	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor)
	{
		int i = MainRegistry.fadedSpawnRate;
		boolean flag = MainRegistry.enableFaded;
		if (flag)
		{
			EntityRegistry.registerModEntity(entityClass, entityName, 29, MainRegistry.modInstance, 82, 3, true);
			
	    	ArrayList<BiomeGenBase> all_biomesList = new ArrayList<BiomeGenBase>();
	    	for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray()) {
	    		if (biome != null) 
	    		{		
	    			if (biome != biome.mushroomIsland && biome != biome.mushroomIslandShore)
	    			{
	    				all_biomesList.add(biome);
	    			}
	    		}
	    	}
	    	BiomeGenBase[] all_biomes_array = new BiomeGenBase[all_biomesList.size()];
	    	all_biomes_array = all_biomesList.toArray(all_biomes_array);
	    	EntityRegistry.addSpawn(entityClass, i, 1, 1, EnumCreatureType.monster, all_biomes_array);
		}
	}
}