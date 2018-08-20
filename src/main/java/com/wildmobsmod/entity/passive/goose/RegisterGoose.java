package com.wildmobsmod.entity.passive.goose;

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

public class RegisterGoose
{
	
	public static void mainRegistry()
	{
		registerModEntity();
	}
	
	public static void registerModEntity(){
		
		createEntity(EntityGoose.class, "Goose", 0x775943, 0xCFC6C0);
	}
	
	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor){
		int i = MainRegistry.gooseSpawnRateWater;
		int j = MainRegistry.gooseSpawnRateSwamp;
		boolean flag = MainRegistry.enableGoose;
		if (flag)
		{
	    	EntityRegistry.registerModEntity(entityClass, entityName, 23, MainRegistry.modInstance, 82, 3, true);
	    	
	    	BiomeGenBase[] biomes1 = new BiomeGenBase[0];
	    	biomes1 = ArrayUtils.addAll(biomes1, BiomeDictionary.getBiomesForType(Type.RIVER));
	    	biomes1 = ArrayUtils.addAll(biomes1, BiomeDictionary.getBiomesForType(Type.BEACH));
	    	
	    	BiomeGenBase[] biomes2 = new BiomeGenBase[0];
	    	biomes2 = ArrayUtils.addAll(biomes2, BiomeDictionary.getBiomesForType(Type.SWAMP));
	    	
	    	EntityRegistry.addSpawn(entityClass, i, 4, 4, EnumCreatureType.creature, biomes1);
	    	EntityRegistry.addSpawn(entityClass, j, 4, 4, EnumCreatureType.creature, biomes2);
		}
	}
}