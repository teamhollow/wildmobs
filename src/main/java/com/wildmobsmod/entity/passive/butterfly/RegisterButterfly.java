package com.wildmobsmod.entity.passive.butterfly;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import cpw.mods.fml.common.registry.EntityRegistry;

import com.wildmobsmod.main.MainRegistry;

public class RegisterButterfly {
	
	public static void mainRegistry(){
		registerModEntity();
	}
	
	public static void registerModEntity(){
		
		createEntity(EntityButterfly.class, "Butterfly", 0xCC5621, 0xEFD71F);
		
	}
	
	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor){
		int i = MainRegistry.butterflySpawnRate;
		boolean flag = MainRegistry.enableButterfly;
		if (flag)
		{
	    	EntityRegistry.registerModEntity(entityClass, entityName, 9, MainRegistry.modInstance, 82, 1, true);
	    	
	    	BiomeGenBase[] biomes = new BiomeGenBase[0];
	    	biomes = ArrayUtils.addAll(biomes, BiomeDictionary.getBiomesForType(Type.FOREST));
	    	biomes = ArrayUtils.addAll(biomes, BiomeDictionary.getBiomesForType(Type.PLAINS));
	    	biomes = ArrayUtils.addAll(biomes, BiomeDictionary.getBiomesForType(Type.SAVANNA));
	    	biomes = ArrayUtils.addAll(biomes, BiomeDictionary.getBiomesForType(Type.SWAMP));
	    	biomes = ArrayUtils.addAll(biomes, BiomeDictionary.getBiomesForType(Type.JUNGLE));
  	    	EntityRegistry.addSpawn(entityClass, i, 1, 1, EnumCreatureType.ambient, biomes);
		}
	}
}