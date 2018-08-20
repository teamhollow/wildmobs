package com.wildmobsmod.entity.passive.direwolf;

import java.util.ArrayList;

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

public class RegisterDireWolf {
	
	public static void mainRegistry(){
		registerModEntity();
	}
	
	public static void registerModEntity(){
		
		createEntity(EntityDireWolf.class, "DireWolf", 0xA59070, 0x5A4823);
		
	}
	
	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor){
		int i = MainRegistry.direWolfSpawnRate;
		boolean flag = MainRegistry.enableDireWolf;
		if (flag)
		{
	    	EntityRegistry.registerModEntity(entityClass, entityName, 14, MainRegistry.modInstance, 82, 3, true);
	    	
	    	BiomeGenBase[] biomes = new BiomeGenBase[0];
	    	biomes = ArrayUtils.addAll(biomes, BiomeDictionary.getBiomesForType(Type.FOREST));
	    	EntityRegistry.addSpawn(entityClass, i, 4, 4, EnumCreatureType.creature, biomes);
		}
	}
}