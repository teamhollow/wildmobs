package com.wildmobsmod.entity.monster.skeletonwolf;

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

public class RegisterSkeletonWolf {
	
	public static void mainRegistry(){
		registerModEntity();
	}
	
	public static void registerModEntity(){
		
		createEntity(EntitySkeletonWolf.class, "SkeletonWolf", 0x775943, 0xCFC6C0);
		
	}
	
	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor){
		boolean flag = MainRegistry.enableSkeletonWolf;
		if (flag)
		{
	    	EntityRegistry.registerModEntity(entityClass, entityName, 21, MainRegistry.modInstance, 82, 3, true);
		}
	}
}