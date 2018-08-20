package com.wildmobsmod.entity.monster.magmaplant;

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

public class RegisterMagmaPlant {
	
	public static void mainRegistry(){
		registerModEntity();
	}
	
	public static void registerModEntity(){
		
		createEntity(EntityMagmaPlant.class, "MagmaPlant", 0xA59070, 0x5A4823);
		
	}
	
	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor){
		int i = MainRegistry.magmaPlantSpawnRate;
		boolean flag = MainRegistry.enableMagmaPlant;
		if (flag)
		{
	    	EntityRegistry.registerModEntity(entityClass, entityName, 15, MainRegistry.modInstance, 100, 1, true);
	    	
	    	BiomeGenBase[] biomes = new BiomeGenBase[0];
	    	biomes = ArrayUtils.addAll(biomes, BiomeDictionary.getBiomesForType(Type.NETHER));
	    	EntityRegistry.addSpawn(entityClass, i, 2, 4, EnumCreatureType.monster, biomes);
		}
	}
}