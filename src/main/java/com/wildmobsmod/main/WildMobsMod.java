package com.wildmobsmod.main;

import com.wildmobsmod.entity.EntityConfig;
import com.wildmobsmod.entity.monster.dreath.EntityConfigDreathMired;
import com.wildmobsmod.entity.passive.hyena.EntityConfigHyena;
import com.wildmobsmod.entity.passive.jellyfish.EntityConfigJellyfish;
import com.wildmobsmod.entity.passive.mouse.EntityConfigMouse;
import com.wildmobsmod.entity.passive.ocelot.EntityConfigOcelot;
import com.wildmobsmod.entity.passive.wolf.EntityConfigWolf;
import com.wildmobsmod.items.WildMobsModItems;
import com.wildmobsmod.misc.AquaHealingDrinkWaterHandler;
import com.wildmobsmod.misc.CreeperAvoidWMOcelotHandler;
import com.wildmobsmod.misc.MobDropsHandler;
import com.wildmobsmod.misc.OcelotHuntMiceHandler;
import com.wildmobsmod.misc.OcelotOverrideHandler;
import com.wildmobsmod.misc.PotionAquaHealing;
import com.wildmobsmod.misc.SeaScorpionSpecialSpawnHandler;
import com.wildmobsmod.misc.SkeletonWolfHandler;
import com.wildmobsmod.misc.WolfOverrideHandler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = WildMobsMod.MODID, name = WildMobsMod.MODNAME, version = WildMobsMod.VERSION, guiFactory = "com.wildmobsmod.main.GUIFactory")
public class WildMobsMod
{
	public static final String MODID = "wildmobsmod", MODNAME = "Wild Mobs", VERSION = "1.5.2";
	
	@SidedProxy(clientSide = "com.wildmobsmod.main.ClientProxy", serverSide = "com.wildmobsmod.main.ServerProxy")
	public static IProxy proxy;

	public static Configuration config;
	
	public static final String CONFIG_CATEGORY_GENERAL = "_general";
	public static final String CONFIG_CATEGORY_ENTITIES = "entities";

	public static int aquaHealingID = 40;
	public static boolean enableCalamari = true;
	public static int skeletonWolfChance = 3;
	public static final boolean enableFoxUnnaturalVariants = true; // flag is still (technically) in use, but no longer configurable (whether a functionality like this should be available in a proper SkinnedEntity-System remains to be decided)

	private static final String[] MIRED_LOOT = {"minecraft:bone, 1, 8", "minecraft:bone, 2, 4", "minecraft:wheat_seeds, 1, 7", "minecraft:wheat_seeds, 2, 2", "minecraft:arrow, 1, 5", "minecraft:arrow, 2, 2", "minecraft:rotten_flesh, 1, 6", "minecraft:stick, 1, 5", "minecraft:torch, 1, 4", "minecraft:bread, 1, 4", "minecraft:poisonous_potato, 1, 3", "minecraft:potato, 1, 2", "minecraft:coal:1, 1, 2", "minecraft:book, 1, 1", "minecraft:book, 2, 1", "minecraft:stone_sword, 1, 1", "minecraft:stone_pickaxe, 1, 1", "minecraft:stone_axe, 1, 1", "minecraft:stone_shovel, 1, 1", "minecraft:leather_helmet, 1, 1", "minecraft:leather_chestplate, 1, 1", "minecraft:leather_leggings, 1, 1", "minecraft:leather_boots, 1, 1", "minecraft:gold_nugget, 1, 1"};
	
	// Monster
	public static final EntityConfigDreathMired DREATH_MIRED_CONFIG = EntityConfigDreathMired.construct("Dreath", 5, 1, 1, true, true, true, 1.0D, true, 3, MIRED_LOOT, false);
	public static final EntityConfig FADED_CONFIG = EntityConfig.construct("Faded", 4, 1, 1, true);
	public static final EntityConfig MAGMAPLANT_CONFIG = EntityConfig.construct("Magma Plant", 8, 2, 4, true);
	public static final EntityConfig SEASCORPION_CONFIG = EntityConfig.construct("Sea Scorpion", 5, 1, 1, true);
	public static final EntityConfig TARANTULA_CONFIG = EntityConfig.construct("Tarantula", 15, 4, 4, true);
	public static final EntityConfig ZOMGUS_CONFIG = EntityConfig.construct("Zomgus", 8, 4, 4, true);
	
	// Passive
	public static final EntityConfig ARMADILLO_CONFIG = EntityConfig.construct("Armadillo", 5, 1, 1, true);
	public static final EntityConfig BISON_CONFIG = EntityConfig.construct("Bison", 4, 6, 10, true);
	public static final EntityConfig BUTTERFLY_CONFIG = EntityConfig.construct("Butterfly", 5, 1, 1, true);
	public static final EntityConfig COUGAR_CONFIG = EntityConfig.construct("Cougar", 4, 1, 1, true);
	public static final EntityConfig CHEETAH_CONFIG = EntityConfig.construct("Cheetah", 4, 1, 5, true);
	public static final EntityConfig DEER_CONFIG = EntityConfig.construct("Deer", 7, 4, 4, true);
	public static final EntityConfig DIREWOLF_CONFIG = EntityConfig.construct("Direwolf", 2, 4, 4, true);
	public static final EntityConfig DRAGONFLY_CONFIG = EntityConfig.construct("Dragonfly", 7, 1, 4, true);
	public static final EntityConfig FOX_CONFIG = EntityConfig.construct("Fox", 6, 1, 1, true);
	public static final EntityConfig GOAT_CONFIG = EntityConfig.construct("Goat", 10, 4, 4, true);
	public static final EntityConfig GOOSE_CONFIG = EntityConfig.construct("Goose", 8, 4, 4, true);
	public static final EntityConfigHyena HYENA_CONFIG = EntityConfigHyena.construct("Hyena", 4, 1, 5, true, true);
	public static final EntityConfigJellyfish JELLYFISH_CONFIG = EntityConfigJellyfish.construct("Jellyfish", 9, 3, 4, true, 2);
	public static final EntityConfigMouse MOUSE_CONFIG = EntityConfigMouse.construct("Mouse", 5, 1, 2, true, true);
	public static final EntityConfigOcelot OCELOT_CONFIG = EntityConfigOcelot.construct("Ocelot", 2, 1, 1, true, 50);
	public static final EntityConfigWolf WOLF_CONFIG = EntityConfigWolf.construct("Wolf", 4, 4, 4, true, 50);

//	public static final EntityConfig WIZARD_CONFIG = EntityConfig.construct("Wizard", 0, false); // Disabled for now;
	

//	public static final EntityConfig SKELETONWOLF_CONFIG = EntityConfig.construct("Skeleton Wolf", 0, 0, 0, true); // individual spawning disabled, use skeletonWolfChance instead

//	
//	 Here's the (unfinished) config settings. The variables are finished, but
//	 the file doesn't generate properly.
//	
//	public static int deerSpawnRate = 7;
//	public static int foxSpawnRate = 6;
//	public static int cougarSpawnRate = 4;
//	public static int bisonSpawnRate = 4;
//	public static int mouseSpawnRate = 5;
//	public static int butterflySpawnRate = 5;
//	public static int tarantulaSpawnRate = 50;
//	public static int dreathSpawnRate = 5;
//	public static int goatSpawnRate = 10;
//	public static int zomgusSpawnRateMushroomIsland = 30;
//	public static int zomgusSpawnRateRoofedForest = 8;
//	public static int direWolfSpawnRate = 1;
//	public static int magmaPlantSpawnRate = 20;
//	public static int dragonflySpawnRate = 7;
//	public static int armadilloSpawnRate = 5;
//	public static int jellyfishSpawnRate = 9;
//	public static int netherMedusaSpawnRate = 2;
//	public static int skeletonWolfSpawnRate = 4;
//	public static int gooseSpawnRateWater = 12;
//	public static int gooseSpawnRateSwamp = 8;
//	public static int seaScorpionSpawnRate = 5;
//	public static int fadedSpawnRate = 4;
//
//	public static boolean enableDeer = true;
//	public static boolean enableFox = true;
//	public static boolean enableCougar = true;
//	public static boolean enableZomgus = true;
//	public static boolean enableBison = true;
//	public static boolean enableWizard = true;
//	public static boolean enableMouse = true;
//	public static boolean enableButterfly = true;
//	public static boolean enableTarantula = true;
//	public static boolean enableDreath = true;
//	public static boolean enableGoat = true;
//	public static boolean enableDireWolf = true;
//	public static boolean enableFoxUnnaturalVariants = true;
//	public static boolean enableDiseasedMice = true;
//	public static boolean enableMagmaPlant = true;
//	public static boolean enableDragonfly = true;
//	public static boolean enableArmadillo = true;
//	public static boolean enableImprovedWolves = true;
//	public static boolean enableJellyfish = true;
//	public static boolean enableSkeletonWolf = true;
//	public static boolean enableBabySkeleton = true;
//	public static boolean enableGoose = true;
//	public static boolean enableImprovedCats = true;
//	public static boolean enableSeaScorpion = true;
//	public static boolean enableWitherSkeletonWolf = false;
//	public static boolean enableBabyDreath = false;
//	public static boolean enableFaded = true;
//
//	public static boolean enableCalamari = true;
//	public static boolean enableAchievementPage = false;
//	public static boolean enableLeatherBlock = true;
//	public static boolean enableBugNet = true;
//	public static boolean enableFur = true;
//	public static boolean enableBisonLeather = true;

//	public static String[] seaScorpionSuitableFoods = new String[] { "minecraft:fish", "wildmobsmod:calamari" };
//	public static String[] miredDropsVeryRare = new String[] { "minecraft:diamond", "minecraft:emerald", "minecraft:golden_apple", "minecraft:diamond_horse_armor", "minecraft:experience_bottle", "minecraft:name_tag" };
//	public static String[] miredDropsRare = new String[] { "minecraft:gold_ingot", "minecraft:iron_horse_armor", "minecraft:iron_sword", "minecraft:iron_axe", "minecraft:golden_sword", "minecraft:compass", "minecraft:saddle", "minecraft:map", "minecraft:golden_carrot" };
//	public static String[] miredDropsCommon = new String[] { "minecraft:iron_ingot", "minecraft:coal", "minecraft:book", "minecraft:iron_shovel", "minecraft:leather", "minecraft:leather_boots", "minecraft:shears", "minecraft:flint", "minecraft:reeds" };

//	public static final String CATEGORY_DEER = "Deer";
//	public static final String CATEGORY_FOX = "Fox";
//	public static final String CATEGORY_MOUSE = "Mouse";
//	public static final String CATEGORY_DREATH = "Dreath";
//	public static final String CATEGORY_SEASCORPION = "Sea Scorpion";
//	public static final String CATEGORY_FADED = "Faded";

	@Metadata
	public static ModMetadata meta;

	@Instance(MODID)
	public static WildMobsMod modInstance;
	
	public static final CreativeTabs TAB_WILDMOBS = new CreativeTabs(MODID)
	{	
		@Override
		public Item getTabIconItem() 
		{
			return WildMobsModItems.fur;
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());
		syncConfig();
		FMLCommonHandler.instance().bus().register(modInstance);
		proxy.registerModObjects();
		proxy.registerRenderThings();
	}

	public static Potion potionAquaHealing;

	public static Achievement achievementTameDireWolf;
	public static Achievement achievementKillWizard;

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		CommonProxy.expandPotionArray();
		//
		// Whether or not Wild Mob achievements should be on it's or achievement
		// page or not.
		//
//		if(WildMobsMod.enableAchievementPage) // Achievements disabled for now - this should be redone and expanded for the new Achievement system
//		{
//			achievementTameDireWolf = new Achievement("achievement.tameDireWolf", "tameDireWolf", 0, 0, WildMobsModItems.thickBone, AchievementList.buildSword).initIndependentStat().registerStat();
//			achievementKillWizard = new Achievement("achievement.killWizard", "killWizard", 2, 0, WildMobsModItems.magicBook, AchievementList.blazeRod).initIndependentStat().registerStat().setSpecial();
//			AchievementPage.registerAchievementPage(new AchievementPage("Wild Mobs", new Achievement[] { WildMobsMod.achievementTameDireWolf, WildMobsMod.achievementKillWizard }));
//		}
//		else
//		{
//			achievementTameDireWolf = new Achievement("achievement.tameDireWolf", "tameDireWolf", 5, -4, WildMobsModItems.thickBone, AchievementList.buildSword).initIndependentStat().registerStat();
//			achievementKillWizard = new Achievement("achievement.killWizard", "killWizard", 0, 12, WildMobsModItems.magicBook, AchievementList.blazeRod).initIndependentStat().registerStat().setSpecial();
//		}

		if(WOLF_CONFIG.isEnabled() && WOLF_CONFIG.getOverrideChance() > 0)
		{
			MinecraftForge.EVENT_BUS.register(new WolfOverrideHandler());
		}

		if(skeletonWolfChance > -1) // Enabled
		{
			MinecraftForge.EVENT_BUS.register(new SkeletonWolfHandler());
		}

		if(MOUSE_CONFIG.isEnabled())
		{
			MinecraftForge.EVENT_BUS.register(new OcelotHuntMiceHandler());
		}

		if(OCELOT_CONFIG.isEnabled())
		{
			MinecraftForge.EVENT_BUS.register(new CreeperAvoidWMOcelotHandler());
			if(OCELOT_CONFIG.getOverrideChance() > 0)
			{
				MinecraftForge.EVENT_BUS.register(new OcelotOverrideHandler());
			}
		}

		if(SEASCORPION_CONFIG.isEnabled())
		{
			MinecraftForge.EVENT_BUS.register(new SeaScorpionSpecialSpawnHandler());
			MinecraftForge.EVENT_BUS.register(new AquaHealingDrinkWaterHandler());
		}
		if(enableCalamari)
		{
			MinecraftForge.EVENT_BUS.register(new MobDropsHandler());
		}
		potionAquaHealing = (new PotionAquaHealing(WildMobsMod.aquaHealingID)).setIconIndex(0, 0).setPotionName("potion.wildmobs.aqua_healing");
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.performCompatRelevantActions();
	}

	public static void syncConfig()
	{
//		// Deer
//		enableDeer = config.getBoolean("Enable Deer", CATEGORY_DEER, true, "");
//		deerSpawnRate = config.getInt("Deer Spawn Rate", CATEGORY_DEER, 7, 0, 100, "Higher values make the mob spawn more often. If the value is set to zero, the mob won't spawn at all.");
//
//		// Fox
//		enableFox = config.getBoolean("Enable Fox", CATEGORY_FOX, true, "");
//		foxSpawnRate = config.getInt("Fox Spawn Rate", CATEGORY_FOX, 7, 0, 100, "Higher values make the mob spawn more often. If the value is set to zero, the mob won't spawn at all.");
//
//		// Mouse
//		enableMouse = config.getBoolean("Enable Mouse", CATEGORY_MOUSE, true, "");
//		enableDiseasedMice = config.getBoolean("Enable Diseased Mice", CATEGORY_MOUSE, true, "");
//		mouseSpawnRate = config.getInt("Mouse Spawn Rate", CATEGORY_MOUSE, 5, 0, 100, "Higher values make the mob spawn more often. If the value is set to zero, the mob won't spawn at all.");
//
//		// Dreath and Mired
//		enableDreath = config.getBoolean("Enable Dreath and Mired", CATEGORY_DREATH, true, "");
//		enableBabyDreath = config.getBoolean("Enable Baby dreath", CATEGORY_DREATH, false, "");
//		dreathSpawnRate = config.getInt("Dreath Spawn Rate", CATEGORY_DREATH, 5, 0, 100, "Higher values make the mob spawn more often. If the value is set to zero, the mob won't spawn at all.");
//		miredDropsVeryRare = config.getStringList("Very Rare Mired Drops", CATEGORY_DREATH, new String[] { "minecraft:diamond", "minecraft:emerald", "minecraft:golden_apple", "minecraft:diamond_horse_armor", "minecraft:experience_bottle", "minecraft:name_tag" },
//				"11% of the items mireds summoned using mired bottles drop.");
//		miredDropsRare = config.getStringList("Rare Mired Drops", CATEGORY_DREATH,
//				new String[] { "minecraft:gold_ingot", "minecraft:iron_horse_armor", "minecraft:iron_sword", "minecraft:iron_axe", "minecraft:golden_sword", "minecraft:compass", "minecraft:saddle", "minecraft:map", "minecraft:golden_carrot" },
//				"23% of the items mireds summoned using mired bottles drop.");
//		miredDropsCommon = config.getStringList("Common Mired Drops", CATEGORY_DREATH,
//				new String[] { "minecraft:iron_ingot", "minecraft:coal", "minecraft:book", "minecraft:iron_shovel", "minecraft:leather", "minecraft:leather_boots", "minecraft:shears", "minecraft:flint", "minecraft:reeds" },
//				"66% of the items mireds summoned using mired bottles drop.");
//
//		// Sea Scorpion
//		enableSeaScorpion = config.getBoolean("Enable Sea Scorpion", CATEGORY_SEASCORPION, true, "");
//		seaScorpionSpawnRate = config.getInt("Sea Scorpion Spawn Rate", CATEGORY_SEASCORPION, 5, 0, 10, "Higher values make the mob spawn more often. If the value is set to zero, the mob won't spawn at all.");
//		seaScorpionSuitableFoods = config.getStringList("Suitable Sea Scorpion Foods", CATEGORY_SEASCORPION, new String[] { "minecraft:fish", "wildmobsmod:calamari" }, "Food items that can be used to heal non-wild sea scorpions.");
//
//		// Faded
//		enableFaded = config.getBoolean("Enable Faded", CATEGORY_FADED, true, "");
//		fadedSpawnRate = config.getInt("Faded Spawn Rate", CATEGORY_FADED, 4, 0, 100, "Higher values make the mob spawn more often. If the value is set to zero, the mob won't spawn at all.");

		skeletonWolfChance = config.getInt("skeletonWolfChance", CONFIG_CATEGORY_ENTITIES, 3, -1, 100, "Chance (in Percent) for a Skeleton Wolf to spawn alongside a Skeleton (0 to disable; -1 to prevent the Skeleton Wolf Entity from being registered)");
		aquaHealingID = config.getInt("aquaHealingId", CONFIG_CATEGORY_GENERAL, 40, 0, 1024, "The Potion ID for Aqua Healing, please do your research before changing this!");
		enableCalamari = config.getBoolean("enableCalamari", CONFIG_CATEGORY_GENERAL, true, "Make Squids drop calamari");
		
		EntityConfig.syncAll(config);
		
		if(config.hasChanged())
		{
			config.save();
		}
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs)
	{
		if(eventArgs.modID.equals(MODID)) syncConfig();
	}
	
	
	public static final String ENTITY_LOADING_FLAG_NAME = "wm_NotNewChecked";
	
	/**
	 *  <b>Required to prevent chanced replacement handlers from rolling every time the entity is loaded from a save!</b><br>
	 *  Creates a Boolean NBTTag ({@link #ENTITY_LOADING_FLAG_NAME}) to track if an entity has been reviewed by this function at any point.
	 *  Can be used to perform actions only when an entity is originally created and added to the world without that action being repeated whenever that entity is loaded from the world save.<br>
	 *  Explicitly setting the Tag to false or deleting it will make the game "forget" the entity is being loaded and is highly discouraged due to potential world-breakage.<br>
	 *  If possible this functionality should be replaced with Vanilla/Forge methods or at least a unified framework (if multiple mods need to do this, they will otherwise all add their own Tags).<br>
	 */
	public static boolean checkIsEntityNew(Entity entity) { 
		NBTTagCompound nbt = entity.getEntityData();
		if(nbt.getBoolean(ENTITY_LOADING_FLAG_NAME)) {
			return false;
		} else {
			nbt.setBoolean(ENTITY_LOADING_FLAG_NAME, true);
			return true;
		}
	}

//	public static Item getItem(String string)
//	{
//		String modId = string.split(":")[0];
//
//		String name = string.split(":")[1];
//
//		return GameRegistry.findItem(modId, name);
//	}
//
//	public static String listToString(String[] stringList, int element)
//	{
//		return stringList[element];
//	}
}
