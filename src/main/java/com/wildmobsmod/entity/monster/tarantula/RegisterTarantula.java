package com.wildmobsmod.entity.monster.tarantula;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import cpw.mods.fml.common.registry.EntityRegistry;

import com.wildmobsmod.main.MainRegistry;

public class RegisterTarantula {
	
	public static void mainRegistry(){
		registerModEntity();
	}
	
	public static void registerModEntity(){
		
		createEntity(EntityTarantula.class, "Tarantula", 0x1A1812, 0xA30F0F);
		
	}
	
	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor){
		int i = MainRegistry.tarantulaSpawnRate;
		boolean flag = MainRegistry.enableTarantula;
		if (flag)
		{
	     	EntityRegistry.registerModEntity(entityClass, entityName, 10, MainRegistry.modInstance, 82, 3, true);
	     	
	    	BiomeGenBase[] biomes = new BiomeGenBase[0];
	    	biomes = ArrayUtils.addAll(biomes, BiomeDictionary.getBiomesForType(Type.JUNGLE));
	    	biomes = ArrayUtils.addAll(biomes, BiomeDictionary.getBiomesForType(Type.SANDY));
	    	EntityRegistry.addSpawn(entityClass, i, 4, 4, EnumCreatureType.monster, biomes);
		}
	}
}

