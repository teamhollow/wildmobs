package com.wildmobsmod.entity.passive.fox;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import cpw.mods.fml.common.registry.EntityRegistry;

import com.wildmobsmod.main.MainRegistry;

public class RegisterFox {
	
	public static void mainRegistry(){
		registerModEntity();
	}
	
	public static void registerModEntity(){
		
		createEntity(EntityFox.class, "Fox", 0xDB7823, 0x2E2E2E);
		
	}
	
	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor){
		int i = MainRegistry.foxSpawnRate;
		boolean flag = MainRegistry.enableFox;
		if (flag)
		{
	    	EntityRegistry.registerModEntity(entityClass, entityName, 1, MainRegistry.modInstance, 82, 3, true);
	    	
	    	BiomeGenBase[] biomes = new BiomeGenBase[0];
	    	biomes = ArrayUtils.addAll(biomes, BiomeDictionary.getBiomesForType(Type.FOREST));
	    	biomes = ArrayUtils.addAll(biomes, BiomeDictionary.getBiomesForType(Type.SANDY));
	    	biomes = ArrayUtils.addAll(biomes, BiomeDictionary.getBiomesForType(Type.COLD));
  	    	EntityRegistry.addSpawn(entityClass, i, 1, 1, EnumCreatureType.creature, biomes);	
		}
	}
}
