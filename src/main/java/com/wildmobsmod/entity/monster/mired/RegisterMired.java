package com.wildmobsmod.entity.monster.mired;

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

public class RegisterMired {
	
	public static void mainRegistry(){
		registerModEntity();
	}
	
	public static void registerModEntity(){
		
		createEntity(EntityMired.class, "Mired", 0x000000, 0x2B2B2B);
		
	}
	
	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor){
		boolean flag = MainRegistry.enableDreath;
		if (flag)
		{
	     	EntityRegistry.registerModEntity(entityClass, entityName, 12, MainRegistry.modInstance, 82, 3, true);
		}
	}
}