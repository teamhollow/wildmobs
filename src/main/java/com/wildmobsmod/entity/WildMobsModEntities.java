package com.wildmobsmod.entity;

import org.apache.commons.lang3.ArrayUtils;

import com.wildmobsmod.entity.monster.dreath.EntityDreath;
import com.wildmobsmod.entity.monster.dreath.mired.EntityMired;
import com.wildmobsmod.entity.monster.dreath.mired.EntityMiredSummoner;
import com.wildmobsmod.entity.monster.faded.EntityFaded;
import com.wildmobsmod.entity.monster.magmaplant.EntityMagmaPlant;
import com.wildmobsmod.entity.monster.seascorpion.EntitySeaScorpion;
import com.wildmobsmod.entity.monster.skeletonwolf.EntitySkeletonWolf;
import com.wildmobsmod.entity.monster.tarantula.EntityTarantula;
import com.wildmobsmod.entity.monster.wizard.EntityWizard;
import com.wildmobsmod.entity.monster.zomgus.EntityZomgus;
import com.wildmobsmod.entity.passive.armadillo.EntityArmadillo;
import com.wildmobsmod.entity.passive.bison.EntityBison;
import com.wildmobsmod.entity.passive.butterfly.EntityButterfly;
import com.wildmobsmod.entity.passive.cheetah.EntityCheetah;
import com.wildmobsmod.entity.passive.cougar.EntityCougar;
import com.wildmobsmod.entity.passive.deer.EntityDeer;
import com.wildmobsmod.entity.passive.direwolf.EntityDireWolf;
import com.wildmobsmod.entity.passive.dragonfly.EntityDragonfly;
import com.wildmobsmod.entity.passive.fox.EntityFox;
import com.wildmobsmod.entity.passive.goat.EntityGoat;
import com.wildmobsmod.entity.passive.goose.EntityGoose;
import com.wildmobsmod.entity.passive.hyena.EntityHyena;
import com.wildmobsmod.entity.passive.jellyfish.EntityConfigJellyfish;
import com.wildmobsmod.entity.passive.jellyfish.EntityJellyfish;
import com.wildmobsmod.entity.passive.mouse.EntityMouse;
import com.wildmobsmod.entity.passive.ocelot.EntityWMOcelot;
import com.wildmobsmod.entity.passive.wolf.EntityWMWolf;
import com.wildmobsmod.entity.projectile.lavaspit.EntityLavaSpit;
import com.wildmobsmod.entity.projectile.seascorpionegg.EntitySeaScorpionEgg;
import com.wildmobsmod.entity.projectile.spell.EntitySpell;
import com.wildmobsmod.entity.projectile.tarantulahair.EntityTarantulaHair;
import com.wildmobsmod.main.WildMobsMod;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class WildMobsModEntities
{
	private static final EntityRegistrar DREATH = EntityRegistrar.construct(EntityDreath.class, WildMobsMod.DREATH_MIRED_CONFIG, "Dreath", 11, 82, 3, true, EnumCreatureType.monster, Type.SWAMP);
	private static final EntityRegistrar FADED = EntityRegistrar.constructDisjunct(EntityFaded.class, WildMobsMod.FADED_CONFIG, "Faded", 29, 82, 3, true, EnumCreatureType.monster, Type.SPOOKY, Type.DEAD);
	private static final EntityRegistrar MAGMAPLANT = EntityRegistrar.construct(EntityMagmaPlant.class, WildMobsMod.MAGMAPLANT_CONFIG, "MagmaPlant", 15, 100, 1, true, EnumCreatureType.monster, Type.NETHER);
	private static final EntityRegistrar SEASCORPION = EntityRegistrar.construct(EntitySeaScorpion.class, WildMobsMod.SEASCORPION_CONFIG, "SeaScorpion", 26, 82, 2, true, EnumCreatureType.waterCreature, Type.OCEAN);
	private static final EntityRegistrar TARANTULA = EntityRegistrar.constructDisjunct(EntityTarantula.class, WildMobsMod.TARANTULA_CONFIG, "Tarantula", 10, 82, 3, true, EnumCreatureType.monster, Type.JUNGLE, Type.SANDY);
	private static final EntityRegistrar ZOMGUS = EntityRegistrar.construct(EntityZomgus.class, WildMobsMod.ZOMGUS_CONFIG, "Zomgus", 4, 82, 3, true, EnumCreatureType.monster, EntityRegistrar.excludeBiomes(BiomeDictionary.getBiomesForType(Type.MUSHROOM), BiomeGenBase.mushroomIsland, BiomeGenBase.mushroomIslandShore));

	private static final EntityRegistrar ARMADILLO = EntityRegistrar.constructDisjunct(EntityArmadillo.class, WildMobsMod.ARMADILLO_CONFIG, "Armadillo", 19, 82, 3, true, EnumCreatureType.creature, Type.SAVANNA, Type.JUNGLE);
	private static final EntityRegistrar BISON = EntityRegistrar.constructDisjunct(EntityBison.class, WildMobsMod.BISON_CONFIG, "Bison", 5, 82, 3, true, EnumCreatureType.creature, Type.SAVANNA, Type.PLAINS);
	private static final EntityRegistrar BUTTERFLY = EntityRegistrar.construct(EntityButterfly.class, WildMobsMod.BUTTERFLY_CONFIG, "Butterfly", 9, 82, 1, true, EnumCreatureType.ambient, EntityRegistrar.excludeBiomes(EntityRegistrar.getBiomesFromTypesDisjunct(Type.FOREST, Type.PLAINS, Type.SAVANNA, Type.SWAMP, Type.JUNGLE), BiomeDictionary.getBiomesForType(Type.COLD)));
	private static final EntityRegistrar CHEETAH = EntityRegistrar.construct(EntityCheetah.class, WildMobsMod.CHEETAH_CONFIG, "Cheetah", 31, 82, 3, true, EnumCreatureType.creature, Type.SAVANNA);
	private static final EntityRegistrar COUGAR = EntityRegistrar.constructDisjunct(EntityCougar.class, WildMobsMod.COUGAR_CONFIG, "Cougar", 2, 82, 3, true, EnumCreatureType.creature, Type.MOUNTAIN, Type.MESA);
	private static final EntityRegistrar DEER = EntityRegistrar.constructDisjunct(EntityDeer.class, WildMobsMod.DEER_CONFIG, "Deer", 0, 82, 3, true, EnumCreatureType.creature, Type.COLD, Type.FOREST, Type.MOUNTAIN, Type.SWAMP);
	private static final EntityRegistrar DIREWOLF = EntityRegistrar.constructConjunct(EntityDireWolf.class, WildMobsMod.DIREWOLF_CONFIG, "DireWolf", 14, 82, 3, true, EnumCreatureType.creature, Type.FOREST, Type.DENSE);
	private static final EntityRegistrar DRAGONFLY = EntityRegistrar.construct(EntityDragonfly.class, WildMobsMod.DRAGONFLY_CONFIG, "Dragonfly", 17, 82, 1, true, EnumCreatureType.ambient, EntityRegistrar.excludeBiomes(EntityRegistrar.getBiomesFromTypesDisjunct(Type.BEACH, Type.RIVER, Type.SWAMP, Type.JUNGLE), BiomeDictionary.getBiomesForType(Type.COLD)));
	private static final EntityRegistrar FOX = EntityRegistrar.constructDisjunct(EntityFox.class, WildMobsMod.FOX_CONFIG, "Fox", 1, 82, 3, true, EnumCreatureType.creature, Type.FOREST, Type.SANDY, Type.COLD);
	private static final EntityRegistrar GOAT = EntityRegistrar.construct(EntityGoat.class, WildMobsMod.GOAT_CONFIG, "Goat", 13, 82, 3, true, EnumCreatureType.creature, Type.MOUNTAIN);
	private static final EntityRegistrar GOOSE = EntityRegistrar.constructDisjunct(EntityGoose.class, WildMobsMod.GOOSE_CONFIG, "Goose", 23, 82, 3, true, EnumCreatureType.creature, Type.BEACH, Type.RIVER);
	private static final EntityRegistrar HYENA = EntityRegistrar.construct(EntityHyena.class, WildMobsMod.HYENA_CONFIG, "Hyena", 32, 82, 3, true, EnumCreatureType.creature, ArrayUtils.addAll(EntityRegistrar.getBiomesFromTypesConjunct(Type.DRY, Type.HOT, Type.SANDY), EntityRegistrar.getBiomesFromTypesDisjunct(Type.SAVANNA)));
	private static final EntityRegistrar JELLYFISH = EntityRegistrar.construct(EntityJellyfish.class, WildMobsMod.JELLYFISH_CONFIG, "Jellyfish", 20, 82, 3, true, EnumCreatureType.waterCreature, Type.OCEAN);
	private static final EntityRegistrar MOUSE = EntityRegistrar.constructDisjunct(EntityMouse.class, WildMobsMod.MOUSE_CONFIG, "Mouse", 8, 82, 3, true, EnumCreatureType.creature, Type.FOREST, Type.PLAINS, Type.SWAMP, Type.SAVANNA, Type.SANDY, Type.MOUNTAIN, Type.JUNGLE);
	private static final EntityRegistrar OCELOT = EntityRegistrar.constructDisjunct(EntityWMOcelot.class, WildMobsMod.OCELOT_CONFIG, "WMOcelot", 25, 82, 3, true, EnumCreatureType.creature, Type.JUNGLE, Type.FOREST);
	private static final EntityRegistrar WOLF = EntityRegistrar.construct(EntityWMWolf.class, WildMobsMod.WOLF_CONFIG, "WMWolf", 18, 82, 3, true, EnumCreatureType.creature, Type.FOREST);
	
	
	public static void registerAllEntities()
	{
		EntityRegistrar.registerAllEntities();
		if(WildMobsMod.DREATH_MIRED_CONFIG.getRegisterMiredEntities())
		{
			EntityRegistry.registerModEntity(EntityMired.class, "Mired", 12, WildMobsMod.modInstance, 82, 3, true);
			EntityRegistry.registerModEntity(EntityMiredSummoner.class, "MiredSummoner", 24, WildMobsMod.modInstance, 82, 1, true);
		}
		if(WildMobsMod.skeletonWolfChance > -1)
		{
			EntityRegistry.registerModEntity(EntitySkeletonWolf.class, "SkeletonWolf", 21, WildMobsMod.modInstance, 82, 3, true);
		}
//		if(WildMobsMod.WIZARD_CONFIG.isEnabled()) // Disabled for now
//		{
//			EntityRegistry.registerModEntity(EntityWizard.class, "Wizard", 6, WildMobsMod.modInstance, 82, 3, true);
//		}
		EntityConfigJellyfish cfg_jellyfish = WildMobsMod.JELLYFISH_CONFIG;
		if(cfg_jellyfish.isEnabled() && cfg_jellyfish.getNetherSpawnrate() > 0)
		{	// This is kinda hacky...
			EntityRegistry.addSpawn(EntityJellyfish.class, cfg_jellyfish.getNetherSpawnrate(), cfg_jellyfish.getNetherMinPackSize(), cfg_jellyfish.getNetherMaxPackSize(), EnumCreatureType.ambient, BiomeDictionary.getBiomesForType(Type.NETHER));
		}
		registerProjectiles();
	}
	
	public static void registerAllSpawns()
	{
		EntityRegistrar.registerAllSpawns();
	}
	
	private static void registerProjectiles()
	{
		if(WildMobsMod.MAGMAPLANT_CONFIG.isEnabled())
		{
			EntityRegistry.registerModEntity(EntityLavaSpit.class, "LavaSpit", 16, WildMobsMod.modInstance, 82, 1, true);
		}
		EntityRegistry.registerModEntity(EntitySeaScorpionEgg.class, "SeaScorpionEgg", 27, WildMobsMod.modInstance, 82, 1, true);
//		EntityRegistry.registerModEntity(EntitySpell.class, "Spell", 7, WildMobsMod.modInstance, 82, 1, true); // Disabled for now
		if(WildMobsMod.TARANTULA_CONFIG.isEnabled())
		{
			EntityRegistry.registerModEntity(EntityTarantulaHair.class, "TarantulaHair", 30, WildMobsMod.modInstance, 82, 1, true);
		}
	}
}
