package com.wildmobsmod.main;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.wildmobsmod.blocks.WildMobsModBlocks;
import com.wildmobsmod.entity.monster.dreath.RegisterDreath;
import com.wildmobsmod.entity.monster.faded.RegisterFaded;
import com.wildmobsmod.entity.monster.magmaplant.RegisterMagmaPlant;
import com.wildmobsmod.entity.monster.mired.RegisterMired;
import com.wildmobsmod.entity.monster.mired.RegisterMiredSummoner;
import com.wildmobsmod.entity.monster.seascorpion.RegisterSeaScorpion;
import com.wildmobsmod.entity.monster.skeleton.RegisterWMSkeleton;
import com.wildmobsmod.entity.monster.skeletonwolf.RegisterSkeletonWolf;
import com.wildmobsmod.entity.monster.tarantula.RegisterTarantula;
import com.wildmobsmod.entity.monster.wizard.RegisterWizard;
import com.wildmobsmod.entity.monster.zomgus.RegisterZomgus;
import com.wildmobsmod.entity.passive.armadillo.RegisterArmadillo;
import com.wildmobsmod.entity.passive.bison.RegisterBison;
import com.wildmobsmod.entity.passive.butterfly.RegisterButterfly;
import com.wildmobsmod.entity.passive.cougar.RegisterCougar;
import com.wildmobsmod.entity.passive.deer.RegisterDeer;
import com.wildmobsmod.entity.passive.direwolf.RegisterDireWolf;
import com.wildmobsmod.entity.passive.dragonfly.RegisterDragonfly;
import com.wildmobsmod.entity.passive.fox.RegisterFox;
import com.wildmobsmod.entity.passive.goat.RegisterGoat;
import com.wildmobsmod.entity.passive.goose.RegisterGoose;
import com.wildmobsmod.entity.passive.jellyfish.RegisterJellyfish;
import com.wildmobsmod.entity.passive.mouse.RegisterMouse;
import com.wildmobsmod.entity.passive.ocelot.RegisterWMOcelot;
import com.wildmobsmod.entity.passive.wolf.RegisterWMWolf;
import com.wildmobsmod.entity.projectile.lavaspit.RegisterLavaSpit;
import com.wildmobsmod.entity.projectile.seascorpionegg.RegisterSeaScorpionEgg;
import com.wildmobsmod.entity.projectile.tarantulahair.RegisterTarantulaHair;
import com.wildmobsmod.entity.projetile.spell.RegisterSpell;
import com.wildmobsmod.items.WildMobsModItems;
import com.wildmobsmod.lib.Strings;
import com.wildmobsmod.misc.AquaHealingDrinkWaterHandler;
import com.wildmobsmod.misc.CatBreedsHandler;
import com.wildmobsmod.misc.CreeperAvoidWMOcelotHandler;
import com.wildmobsmod.misc.MobDropsHandler;
import com.wildmobsmod.misc.OcelotHuntMiceHandler;
import com.wildmobsmod.misc.SeaScorpionSpawner;
import com.wildmobsmod.misc.SkeletonHandler;
import com.wildmobsmod.misc.SkeletonLookAtSkeletonWolfHandler;
import com.wildmobsmod.misc.WolfBreedsHandler;
import com.wildmobsmod.potions.PotionAquaHealing;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
@Mod(modid = Strings.MODID, name = Strings.name, version = Strings.VERSION)
public class MainRegistry
{
    
	@SidedProxy(clientSide = "com.wildmobsmod.main.ClientProxy", serverSide = "com.wildmobsmod.main.ServerProxy")
	public static ServerProxy proxy;

	//
	// Here's the (unfinished) config settings. The variables are finished, but the file doesn't generate properly.
	//
	public static Configuration config;
	public static int aquaHealingID = 40;
	
	public static int deerSpawnRate = 7;
	public static int foxSpawnRate = 6;
	public static int cougarSpawnRate = 4;
	public static int bisonSpawnRate = 4;
	public static int mouseSpawnRate = 5;
	public static int butterflySpawnRate = 5;
	public static int tarantulaSpawnRate = 50;
	public static int dreathSpawnRate = 5;
	public static int goatSpawnRate = 10;
	public static int zomgusSpawnRateMushroomIsland = 30;
	public static int zomgusSpawnRateRoofedForest = 8;
	public static int direWolfSpawnRate = 1;
	public static int magmaPlantSpawnRate = 20;
	public static int dragonflySpawnRate = 7;
	public static int armadilloSpawnRate = 5;
	public static int jellyfishSpawnRate = 9;
	public static int netherMedusaSpawnRate = 2;
	public static int skeletonWolfSpawnRate = 4;
	public static int gooseSpawnRateWater = 12;
	public static int gooseSpawnRateSwamp = 8;
	public static int seaScorpionSpawnRate = 5;
	public static int fadedSpawnRate = 4;
	
	public static boolean enableDeer = true;
	public static boolean enableFox = true;
	public static boolean enableCougar = true;
	public static boolean enableZomgus = true;
	public static boolean enableBison = true;
	public static boolean enableWizard = true;
	public static boolean enableMouse = true;
	public static boolean enableButterfly = true;
	public static boolean enableTarantula = true;
	public static boolean enableDreath = true;
	public static boolean enableGoat = true;
	public static boolean enableDireWolf = true;
	public static boolean enableFoxUnnaturalVariants = true;
	public static boolean enableDiseasedMice = true;
	public static boolean enableMagmaPlant = true;
	public static boolean enableDragonfly = true;
	public static boolean enableArmadillo = true;
	public static boolean enableImprovedWolves = true;
	public static boolean enableJellyfish = true;
	public static boolean enableSkeletonWolf = true;
	public static boolean enableBabySkeleton = true;
	public static boolean enableGoose = true;
	public static boolean enableImprovedCats = true;
	public static boolean enableSeaScorpion = true;
	public static boolean enableWitherSkeletonWolf = false;
	public static boolean enableBabyDreath = false;
	public static boolean enableFaded = true;
	
	public static boolean enableCalamari = true;
	public static boolean enableAchievementPage = false;
	public static boolean enableLeatherBlock = true;
	public static boolean enableBugNet = true;
	public static boolean enableFur = true;
	public static boolean enableBisonLeather = true;
	
    public static String[] seaScorpionSuitableFoods = new String[] {"minecraft:fish", "wildmobsmod:calamari"};
    public static String[] miredDropsVeryRare = new String[] {"minecraft:diamond", "minecraft:emerald", "minecraft:golden_apple", "minecraft:diamond_horse_armor", "minecraft:experience_bottle", "minecraft:name_tag"};
    public static String[] miredDropsRare = new String[] {"minecraft:gold_ingot", "minecraft:iron_horse_armor", "minecraft:iron_sword", "minecraft:iron_axe", "minecraft:golden_sword", "minecraft:compass", "minecraft:saddle", "minecraft:map", "minecraft:golden_carrot"};
    public static String[] miredDropsCommon = new String[] {"minecraft:iron_ingot", "minecraft:coal", "minecraft:book", "minecraft:iron_shovel", "minecraft:leather", "minecraft:leather_boots", "minecraft:shears", "minecraft:flint", "minecraft:reeds"};

    public static final String CATEGORY_DEER = "Deer";
    public static final String CATEGORY_FOX = "Fox";
    public static final String CATEGORY_MOUSE = "Mouse";
    public static final String CATEGORY_DREATH = "Dreath";
    public static final String CATEGORY_SEASCORPION = "Sea Scorpion";
    public static final String CATEGORY_FADED = "Faded";
    
    @Metadata
    public static ModMetadata meta;
    
    @Instance(Strings.MODID)
    public static MainRegistry modInstance;
    
    @EventHandler
    public void PreInit(FMLPreInitializationEvent event)
    {
    	config = new Configuration(event.getSuggestedConfigurationFile());
        syncConfig();
    	FMLCommonHandler.instance().bus().register(modInstance);
    	
    	WildMobsModBlocks.mainRegistry();
    	WildMobsModItems.mainRegistry();
    	CraftingManager.mainRegistry();
    	RegisterDeer.mainRegistry();
    	RegisterFox.mainRegistry();
    	RegisterCougar.mainRegistry();
    	RegisterZomgus.mainRegistry();
    	RegisterBison.mainRegistry();
    	RegisterWizard.mainRegistry();
    	RegisterSpell.mainRegistry();
    	RegisterMouse.mainRegistry();
    	RegisterButterfly.mainRegistry();
    	RegisterTarantula.mainRegistry();
    	RegisterDreath.mainRegistry();
    	RegisterMired.mainRegistry();
    	RegisterGoat.mainRegistry();
    	RegisterDireWolf.mainRegistry();
    	RegisterMagmaPlant.mainRegistry();
    	RegisterLavaSpit.mainRegistry();
    	RegisterDragonfly.mainRegistry();
    	RegisterWMWolf.mainRegistry();
    	RegisterArmadillo.mainRegistry();
    	RegisterJellyfish.mainRegistry();
    	RegisterSkeletonWolf.mainRegistry();
    	RegisterWMSkeleton.mainRegistry();
    	RegisterGoose.mainRegistry();
    	RegisterMiredSummoner.mainRegistry();
    	RegisterWMOcelot.mainRegistry();
    	RegisterSeaScorpion.mainRegistry();
    	RegisterSeaScorpionEgg.mainRegistry();
    	RegisterTarantulaHair.mainRegistry();
    	RegisterFaded.mainRegistry();
    	
    	proxy.registerRenderThings();
    	
    	MinecraftForge.EVENT_BUS.register(new MobDropsHandler());
    	
    	Potion[] potionTypes = null;
    	
    	for (Field f : Potion.class.getDeclaredFields()) 
    	{
    		f.setAccessible(true);
    		try 
    		{
    			if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) 
    			{
    				Field modfield = Field.class.getDeclaredField("modifiers");
    				modfield.setAccessible(true);
    				modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

    				potionTypes = (Potion[])f.get(null);
    				final Potion[] newPotionTypes = new Potion[256];
    				System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
    				f.set(null, newPotionTypes);
    			}
    		}
    		catch (Exception e) 
    		{
    			System.err.println("Severe error, please report this to the mod author:");
    			System.err.println(e);
    		}
    	}
    }
    
	public static Potion potionAquaHealing;
    
	public static Achievement achievementTameDireWolf;
	public static Achievement achievementKillWizard;
    
    @EventHandler
    public void init(FMLInitializationEvent e)
    {
    	//
    	// Whether or not Wild Mob achievements should be on it's or achievement page or not.
    	//
    	if (MainRegistry.enableAchievementPage)
    	{
        	achievementTameDireWolf = new Achievement("achievement.tameDireWolf", "tameDireWolf", 0, 0, WildMobsModItems.thickBone, AchievementList.buildSword).initIndependentStat().registerStat();
        	achievementKillWizard = new Achievement("achievement.killWizard", "killWizard", 2, 0, WildMobsModItems.magicBook, AchievementList.blazeRod).initIndependentStat().registerStat().setSpecial();
    		AchievementPage.registerAchievementPage(new AchievementPage("Wild Mobs", new Achievement[] {MainRegistry.achievementTameDireWolf, MainRegistry.achievementKillWizard}));
    	}
    	else
    	{
    		achievementTameDireWolf = new Achievement("achievement.tameDireWolf", "tameDireWolf", 5, -4, WildMobsModItems.thickBone, AchievementList.buildSword).initIndependentStat().registerStat();
    		achievementKillWizard = new Achievement("achievement.killWizard", "killWizard", 0, 12, WildMobsModItems.magicBook, AchievementList.blazeRod).initIndependentStat().registerStat().setSpecial();
    	}

    	if (MainRegistry.enableImprovedWolves == true)
    	{
    		MinecraftForge.EVENT_BUS.register(new WolfBreedsHandler());
    	}
    	
    	if (MainRegistry.enableSkeletonWolf == true)
    	{
    		MinecraftForge.EVENT_BUS.register(new SkeletonLookAtSkeletonWolfHandler());
    		MinecraftForge.EVENT_BUS.register(new SkeletonHandler());
    	}
    	
    	if (MainRegistry.enableMouse == true)
    	{
    		MinecraftForge.EVENT_BUS.register(new OcelotHuntMiceHandler());
    	}
    	
    	if (MainRegistry.enableImprovedCats == true)
    	{
    		MinecraftForge.EVENT_BUS.register(new CreeperAvoidWMOcelotHandler());
    		MinecraftForge.EVENT_BUS.register(new CatBreedsHandler());
    	}
    	
    	if (MainRegistry.enableSeaScorpion == true)
    	{
    		MinecraftForge.EVENT_BUS.register(new SeaScorpionSpawner());
    		potionAquaHealing = (new PotionAquaHealing(MainRegistry.aquaHealingID, true, 564902)).setIconIndex(0, 0).setPotionName("potion.aqua_healing");
    		MinecraftForge.EVENT_BUS.register(new AquaHealingDrinkWaterHandler());
    	}
    }

	public static void syncConfig()
	{
        // Deer
        enableDeer = config.getBoolean("Enable Deer", CATEGORY_DEER, true, "");
        deerSpawnRate = config.getInt("Deer Spawn Rate", CATEGORY_DEER, 7, 0, 100, "Higher values make the mob spawn more often. If the value is set to zero, the mob won't spawn at all.");
        
        // Fox
        enableFox = config.getBoolean("Enable Fox", CATEGORY_FOX, true, "");
        foxSpawnRate = config.getInt("Fox Spawn Rate", CATEGORY_FOX, 7, 0, 100, "Higher values make the mob spawn more often. If the value is set to zero, the mob won't spawn at all.");
        
        // Mouse
        enableMouse = config.getBoolean("Enable Mouse", CATEGORY_MOUSE, true, "");
        enableDiseasedMice = config.getBoolean("Enable Diseased Mice", CATEGORY_MOUSE, true, "");
        mouseSpawnRate = config.getInt("Mouse Spawn Rate", CATEGORY_MOUSE, 5, 0, 100, "Higher values make the mob spawn more often. If the value is set to zero, the mob won't spawn at all.");
        
        // Dreath and Mired
        enableDreath = config.getBoolean("Enable Dreath and Mired", CATEGORY_DREATH, true, "");
        enableBabyDreath = config.getBoolean("Enable Baby dreath", CATEGORY_DREATH, false, "");
        dreathSpawnRate = config.getInt("Dreath Spawn Rate", CATEGORY_DREATH, 5, 0, 100, "Higher values make the mob spawn more often. If the value is set to zero, the mob won't spawn at all.");
        miredDropsVeryRare = config.getStringList("Very Rare Mired Drops", CATEGORY_DREATH, new String[] {"minecraft:diamond", "minecraft:emerald", "minecraft:golden_apple", "minecraft:diamond_horse_armor", "minecraft:experience_bottle", "minecraft:name_tag"}, "11% of the items mireds summoned using mired bottles drop.");
        miredDropsRare = config.getStringList("Rare Mired Drops", CATEGORY_DREATH, new String[] {"minecraft:gold_ingot", "minecraft:iron_horse_armor", "minecraft:iron_sword", "minecraft:iron_axe", "minecraft:golden_sword", "minecraft:compass", "minecraft:saddle", "minecraft:map", "minecraft:golden_carrot"}, "23% of the items mireds summoned using mired bottles drop.");
        miredDropsCommon = config.getStringList("Common Mired Drops", CATEGORY_DREATH, new String[] {"minecraft:iron_ingot", "minecraft:coal", "minecraft:book", "minecraft:iron_shovel", "minecraft:leather", "minecraft:leather_boots", "minecraft:shears", "minecraft:flint", "minecraft:reeds"}, "66% of the items mireds summoned using mired bottles drop.");
        
        // Sea Scorpion
        enableSeaScorpion = config.getBoolean("Enable Sea Scorpion", CATEGORY_SEASCORPION, true, "");
        seaScorpionSpawnRate = config.getInt("Sea Scorpion Spawn Rate", CATEGORY_SEASCORPION, 5, 0, 10, "Higher values make the mob spawn more often. If the value is set to zero, the mob won't spawn at all.");
        seaScorpionSuitableFoods = config.getStringList("Suitable Sea Scorpion Foods", CATEGORY_SEASCORPION, new String[] {"minecraft:fish", "wildmobsmod:calamari"}, "Food items that can be used to heal non-wild sea scorpions.");
        
        // Faded
        enableFaded = config.getBoolean("Enable Faded", CATEGORY_FADED, true, "");
        fadedSpawnRate = config.getInt("Faded Spawn Rate", CATEGORY_FADED, 4, 0, 100, "Higher values make the mob spawn more often. If the value is set to zero, the mob won't spawn at all.");

		if(config.hasChanged())
		{
			config.save();
		}
	}
    
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if(eventArgs.modID.equals(Strings.MODID))
			syncConfig();
	}

	public static Item getItem(String string)
	{
		String modId = string.split(":")[0];

		String name = string.split(":")[1];

		return GameRegistry.findItem(modId, name);
	}

	public static String listToString(String[] stringList, int element)
	{
		return stringList[element];
	}
}