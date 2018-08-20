package com.wildmobsmod.entity.passive.deer;

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

public class RegisterDeer {
	
	public static void mainRegistry(){
		registerModEntity();
	}
	
	public static void registerModEntity(){
		
		createEntity(EntityDeer.class, "Deer", 0x775943, 0xCFC6C0);
		
	}
	
	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor){
		int i = MainRegistry.deerSpawnRate;
		boolean flag = MainRegistry.enableDeer;
		if (flag)
		{
	    	EntityRegistry.registerModEntity(entityClass, entityName, 0, MainRegistry.modInstance, 82, 3, true);
	    	
	    	BiomeGenBase[] biomes = new BiomeGenBase[0];
	    	biomes = ArrayUtils.addAll(biomes, BiomeDictionary.getBiomesForType(Type.FOREST));
	    	biomes = ArrayUtils.addAll(biomes, BiomeDictionary.getBiomesForType(Type.COLD));
	    	biomes = ArrayUtils.addAll(biomes, BiomeDictionary.getBiomesForType(Type.SWAMP));
	    	biomes = ArrayUtils.addAll(biomes, BiomeDictionary.getBiomesForType(Type.MOUNTAIN));
         	EntityRegistry.addSpawn(entityClass, i, 4, 4, EnumCreatureType.creature, biomes);
		}
	}
}
