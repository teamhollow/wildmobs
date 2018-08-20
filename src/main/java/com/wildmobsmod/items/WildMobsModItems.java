package com.wildmobsmod.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemFood;
import net.minecraftforge.common.util.EnumHelper;

import com.wildmobsmod.main.MainRegistry;

import cpw.mods.fml.common.registry.GameRegistry;

public class WildMobsModItems {


    public static ArmorMaterial enumArmorMaterialFur = EnumHelper.addArmorMaterial("FUR", 5, new int[] {1, 3, 2, 1}, 15);
    public static ArmorMaterial enumArmorMaterialBisonLeather = EnumHelper.addArmorMaterial("BISON_LEATHER", 5, new int[] {1, 3, 2, 1}, 15);
    
	public static void mainRegistry(){
    	initItems();
    	registerItems();
    }

    public static Item rawVenison = new ItemFood(32, 4, true);
    public static Item cookedVenison = new ItemFood(32, 8, true);
    public static Item fur;
    public static Item bisonLeather;
    public static Item cougarHide;
    public static Item helmetFur;
    public static Item chestplateFur;
    public static Item legsFur;
    public static Item bootsFur;
    public static Item infectedFlesh = new ItemFood(32, 4, true);
    public static Item rawBisonMeat = new ItemFood(32, 4, true);
    public static Item cookedBisonMeat = new ItemFood(32, 8, true);
    public static Item magicWand;
    public static Item thickBone;
    public static Item rawMouse = new ItemFood(32, 1, true);
    public static Item cookedMouse = new ItemFood(32, 4, true);
    public static Item helmetBison;
    public static Item chestplateBison;
    public static Item legsBison;
    public static Item bootsBison;
    public static Item butterfly;
    public static Item bugNet;
    public static Item magicBook;
    public static Item rawChevon = new ItemFood(32, 3, true);
    public static Item cookedChevon = new ItemFood(32, 6, true);
    public static Item rawCalamari = new ItemFood(32, 2, true);
    public static Item cookedCalamari = new ItemFood(32, 5, true);
    public static Item dragonfly;
    public static Item armadilloShell;
    public static Item slimeDrop;
    public static Item magmaCreamDrop;
    public static Item magmaPlantSeed;
    public static Item jellyfish;
    public static Item netherJellyfish;
    public static Item rawGoose = new ItemFood(32, 2, true);
    public static Item cookedGoose = new ItemFood(32, 6, true);
    public static Item roastedMagmaPlantSeed = new ItemFood(32, 2, false).setAlwaysEdible();
    public static Item miredBottle;
    public static Item seaScorpionBucket;
    public static Item seaScorpionEgg;
    public static Item goldenSeaEgg;
    
    // Spawn eggs
    public static Item deerSpawnEgg = new ItemWildMobsMonsterPlacer("Deer", 0x775943, 0xCFC6C0, 0, false).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item reindeerSpawnEgg = new ItemWildMobsMonsterPlacer("Deer", 0xD1BCAE, 0x7D624F, 1, false, "Reindeer").setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item elkSpawnEgg = new ItemWildMobsMonsterPlacer("Deer", 0xBD9071, 0x755143, 2, false, "Elk").setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item foxSpawnEgg = new ItemWildMobsMonsterPlacer("Fox", 0xDB7823, 0x2E2E2E, 0, false).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item arcticFoxSpawnEgg = new ItemWildMobsMonsterPlacer("Fox", 0xEEEEE4, 0xB0B0B0, 1, false, "ArcticFox").setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item desertFoxSpawnEgg = new ItemWildMobsMonsterPlacer("Fox", 0xD9A871, 0xB08D66, 2, false, "DesertFox").setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item cougarSpawnEgg = new ItemWildMobsMonsterPlacer("Cougar", 0xB3896D, 0xFFFFFF, 0, false).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item zomgusSpawnEgg = new ItemWildMobsMonsterPlacer("Zomgus", 0x3AA68E, 0x9C8B87, 0, false).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item bisonSpawnEgg = new ItemWildMobsMonsterPlacer("Bison", 0x382E2A, 0x70513A, 0, false).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item wizardSpawnEgg = new ItemWildMobsMonsterPlacer("Wizard", 0x4F5678, 0xB5A59B, 0, false).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item mouseSpawnEgg = new ItemWildMobsMonsterPlacer("Mouse", 0x7A6858, 0xEDA8A8, 0, false).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item butterflySpawnEgg = new ItemWildMobsMonsterPlacer("Butterfly", 0xCC5621, 0xEFD71F, 0, false).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item tarantulaSpawnEgg = new ItemWildMobsMonsterPlacer("Tarantula", 0x1A1812, 0xA30F0F, 0, false).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item dreathSpawnEgg = new ItemWildMobsMonsterPlacer("Dreath", 0x86A868, 0x474747, 0, false).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item miredSpawnEgg = new ItemWildMobsMonsterPlacer("Mired", 0x000000, 0x2B2B2B, 0, false).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item goatSpawnEgg = new ItemWildMobsMonsterPlacer("Goat", 0xB5B5B5, 0x965A3E, 0, false).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item direWolfSpawnEgg = new ItemWildMobsMonsterPlacer("DireWolf", 0xA59070, 0x5A4823, 0, false).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item magmaPlantSpawnEgg = new ItemWildMobsMonsterPlacer("MagmaPlant", 0x9F452B, 0xF5835D, 0, false).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item dragonflySpawnEgg = new ItemWildMobsMonsterPlacer("Dragonfly", 0x616BBA, 0xF0D737, 0, false).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item armadilloSpawnEgg = new ItemWildMobsMonsterPlacer("Armadillo", 0xB5AEAC, 0xBCA593, 0, false).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item jellyfishSpawnEgg = new ItemWildMobsMonsterPlacer("Jellyfish", 0xF5A51B, 0xBD4707, 0, false).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item netherJellyfishSpawnEgg = new ItemWildMobsMonsterPlacer("Jellyfish", 0xA63719, 0xF5A51B, 6, true, "NetherJellyfish").setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item skeletonWolfSpawnEgg = new ItemWildMobsMonsterPlacer("SkeletonWolf", 0xC4A68F, 0x737373, 0, false).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item gooseSpawnEgg = new ItemWildMobsMonsterPlacer("Goose", 0x9E7A59, 0x333333, 0, false).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item wildCatSpawnEgg = new ItemWildMobsMonsterPlacer("Cat", 0x807B78, 0xFFFFFF, 1, false, "WildCat").setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item seaScorpionSpawnEgg = new ItemWildMobsMonsterPlacer("SeaScorpion", 0xA2AD57, 0x615A1F, 0, false).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    public static Item fadedSpawnEgg = new ItemWildMobsMonsterPlacer("Faded", 0x997650, 0x7F7D75, 0, false).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
    
	public static void initItems(){

    	rawVenison = new FoodRawVenison(4, true).setUnlocalizedName("venison");
    	cookedVenison = new FoodCookedVenison(8, true).setUnlocalizedName("cooked_venison");
    	fur = new ItemFur().setUnlocalizedName("fur");
    	bisonLeather = new ItemBisonLeather().setUnlocalizedName("bison_leather");
    	cougarHide = new ItemCougarHide().setUnlocalizedName("cougar_hide");
    	helmetFur = new ArmorFur(enumArmorMaterialFur, 5, 0).setUnlocalizedName("fur_helmet");
    	chestplateFur = new ArmorFur(enumArmorMaterialFur, 5, 1).setUnlocalizedName("fur_chestplate");
    	legsFur = new ArmorFur(enumArmorMaterialFur, 5, 2).setUnlocalizedName("fur_leggings");
    	bootsFur = new ArmorFur(enumArmorMaterialFur, 5, 3).setUnlocalizedName("fur_boots");
    	infectedFlesh = new FoodInfectedFlesh(4, true).setUnlocalizedName("infected_flesh");
    	rawBisonMeat = new FoodRawBisonMeat(4, true).setUnlocalizedName("bison_meat");
    	cookedBisonMeat = new FoodCookedBisonMeat(8, true).setUnlocalizedName("cooked_bison_meat");
    	thickBone = new ItemThickBone().setUnlocalizedName("thick_bone");
    	rawMouse = new FoodRawMouse(1, true).setUnlocalizedName("mouse");
    	cookedMouse = new FoodCookedMouse(4, true).setUnlocalizedName("cooked_mouse");
    	helmetBison = new ArmorBisonLeather(enumArmorMaterialBisonLeather, 5, 0).setUnlocalizedName("bison_leather_helmet");
    	chestplateBison = new ArmorBisonLeather(enumArmorMaterialBisonLeather, 5, 1).setUnlocalizedName("bison_leather_chestplate");
    	legsBison = new ArmorBisonLeather(enumArmorMaterialBisonLeather, 5, 2).setUnlocalizedName("bison_leather_leggings");
    	bootsBison = new ArmorBisonLeather(enumArmorMaterialBisonLeather, 5, 3).setUnlocalizedName("bison_leather_boots");
    	butterfly = new ItemButterfly().setUnlocalizedName("butterfly");
       	bugNet = new ItemBugNet().setUnlocalizedName("bug_net");
       	magicBook = new ItemMagicBook().setUnlocalizedName("magic_book");
    	rawChevon = new FoodRawChevon(3, true).setUnlocalizedName("chevon");
    	cookedChevon = new FoodCookedChevon(6, true).setUnlocalizedName("cooked_chevon");
    	rawCalamari = new FoodRawCalamari(2, true).setUnlocalizedName("calamari");
    	cookedCalamari = new FoodCookedCalamari(5, true).setUnlocalizedName("cooked_calamari");
       	dragonfly = new ItemDragonfly().setUnlocalizedName("dragonfly");
       	armadilloShell = new ItemArmadilloShell().setUnlocalizedName("armadillo_shell");
       	slimeDrop = new ItemSlimeDrop().setUnlocalizedName("slime_drop");
       	magmaCreamDrop = new ItemMagmaCreamDrop().setUnlocalizedName("magma_cream_drop");
       	magmaPlantSeed = new ItemMagmaPlantSeed().setUnlocalizedName("magma_plant_seed");
       	jellyfish = new ItemJellyfishBucket().setUnlocalizedName("jellyfish_bucket");
       	netherJellyfish = new ItemNetherJellyfishBucket().setUnlocalizedName("jellyfish_bucket.nether");
    	rawGoose = new FoodRawGoose(2, true).setUnlocalizedName("goose");
    	cookedGoose = new FoodCookedGoose(6, true).setUnlocalizedName("cooked_goose");
    	roastedMagmaPlantSeed = ((ItemFood) new FoodRoastedMagmaPlantSeed(2, false).setUnlocalizedName("roasted_magma_plant_seed")).setAlwaysEdible();
       	miredBottle = new ItemMiredBottle().setUnlocalizedName("mired_bottle");
       	seaScorpionBucket = new ItemSeaScorpionBucket().setUnlocalizedName("sea_scorpion_bucket");
       	seaScorpionEgg = new ItemSeaScorpionEgg().setUnlocalizedName("sea_scorpion_egg");
    	magicWand = new ItemMagicWand().setUnlocalizedName("magic_wand");
    	goldenSeaEgg = new FoodGoldenSeaEgg(4, false).setUnlocalizedName("golden_sea_egg");
	}


	public static void registerItems(){

		if (MainRegistry.enableDeer)
		{
        	GameRegistry.registerItem(rawVenison, "venison");
        	GameRegistry.registerItem(cookedVenison, "cooked_venison");
		}
		if (MainRegistry.enableCougar)
		{
         	GameRegistry.registerItem(cougarHide, "cougar_hide");
		}
		if (MainRegistry.enableZomgus)
		{
        	GameRegistry.registerItem(infectedFlesh, "infected_flesh");
		}
		if (MainRegistry.enableBison)
		{
        	GameRegistry.registerItem(rawBisonMeat, "bison_meat");
        	GameRegistry.registerItem(cookedBisonMeat, "cooked_bison_meat");
        	GameRegistry.registerItem(thickBone, "thick_bone");
		}
		if (MainRegistry.enableWizard)
		{
        	GameRegistry.registerItem(magicWand, "magic_wand");
        	GameRegistry.registerItem(magicBook, "magic_book");
		}
		if (MainRegistry.enableMouse)
		{
        	GameRegistry.registerItem(rawMouse, "mouse");
        	GameRegistry.registerItem(cookedMouse, "cooked_mouse");
		}
		if (MainRegistry.enableButterfly)
		{
        	GameRegistry.registerItem(butterfly, "butterfly");
		}
		if (MainRegistry.enableBugNet)
		{
        	GameRegistry.registerItem(bugNet, "bug_net");
		}
		if (MainRegistry.enableBisonLeather)
		{
        	GameRegistry.registerItem(bisonLeather, "bison_leather");
        	GameRegistry.registerItem(helmetBison, "bison_leather_helmet");
        	GameRegistry.registerItem(chestplateBison, "bison_leather_chestplate");
        	GameRegistry.registerItem(legsBison, "bison_leather_leggings");
        	GameRegistry.registerItem(bootsBison, "bison_leather_boots");
		}
		if (MainRegistry.enableFur)
		{
        	GameRegistry.registerItem(helmetFur, "fur_helmet");
        	GameRegistry.registerItem(chestplateFur, "fur_chestplate");
        	GameRegistry.registerItem(legsFur, "fur_leggings");
        	GameRegistry.registerItem(bootsFur, "fur_boots");
        	GameRegistry.registerItem(fur, "fur");	
		}
		if (MainRegistry.enableGoat)
		{
        	GameRegistry.registerItem(rawChevon, "chevon");
        	GameRegistry.registerItem(cookedChevon, "cooked_chevon");
		}
		if (MainRegistry.enableCalamari)
		{
        	GameRegistry.registerItem(rawCalamari, "calamari");
        	GameRegistry.registerItem(cookedCalamari, "cooked_calamari");
		}
		if (MainRegistry.enableDragonfly)
		{
        	GameRegistry.registerItem(dragonfly, "dragonfly");
		}
		if (MainRegistry.enableArmadillo)
		{
        	GameRegistry.registerItem(armadilloShell, "armadillo_shell");
		}
		if (MainRegistry.enableJellyfish)
		{
        	GameRegistry.registerItem(slimeDrop, "slime_drop");
        	GameRegistry.registerItem(magmaCreamDrop, "magma_cream_drop");
        	GameRegistry.registerItem(jellyfish, "jellyfish_buket");
        	GameRegistry.registerItem(netherJellyfish, "nether_jellyfish_bucket");
		}
		if (MainRegistry.enableSeaScorpion)
		{
        	GameRegistry.registerItem(seaScorpionBucket, "sea_scorpion_bucket");
        	GameRegistry.registerItem(seaScorpionEgg, "sea_scorpion_egg");
        	GameRegistry.registerItem(goldenSeaEgg, "golden_sea_egg");
		}
		if (MainRegistry.enableMagmaPlant)
		{
        	GameRegistry.registerItem(magmaPlantSeed, "magma_plant_seed");
        	GameRegistry.registerItem(roastedMagmaPlantSeed, "roasted_magma_plant_seed");
		}
		if (MainRegistry.enableGoose)
		{
        	GameRegistry.registerItem(rawGoose, "goose");
        	GameRegistry.registerItem(cookedGoose, "cooked_goose");
		}
		if (MainRegistry.enableDreath)
		{
        	GameRegistry.registerItem(miredBottle, "mired_bottle");
		}
		
		//
		//Spawn eggs
		//
		if (MainRegistry.enableDeer)
		{
			GameRegistry.registerItem(deerSpawnEgg, "spawn_egg_deer");
			GameRegistry.registerItem(reindeerSpawnEgg, "spawn_egg_reindeer");
			GameRegistry.registerItem(elkSpawnEgg, "spawn_egg_elk");
		}
		if (MainRegistry.enableFox)
		{
			GameRegistry.registerItem(foxSpawnEgg, "spawn_egg_fox");
			GameRegistry.registerItem(arcticFoxSpawnEgg, "spawn_egg_arctic");
			GameRegistry.registerItem(desertFoxSpawnEgg, "spawn_egg_desert");
		}
		if (MainRegistry.enableCougar)
		{
			GameRegistry.registerItem(cougarSpawnEgg, "spawn_egg_cougar");
		}
		if (MainRegistry.enableZomgus)
		{
			GameRegistry.registerItem(zomgusSpawnEgg, "spawn_egg_zomgus");
		}
		if (MainRegistry.enableBison)
		{
    		GameRegistry.registerItem(bisonSpawnEgg, "spawn_egg_bison");
		}
		if (MainRegistry.enableWizard)
		{
    		GameRegistry.registerItem(wizardSpawnEgg, "spawn_egg_wizard");
		}
		if (MainRegistry.enableMouse)
		{
    		GameRegistry.registerItem(mouseSpawnEgg, "spawn_egg_mouse");
		}
		if (MainRegistry.enableButterfly)
		{
    		GameRegistry.registerItem(butterflySpawnEgg, "spawn_egg_butterfl");
		}
		if (MainRegistry.enableTarantula)
		{
			GameRegistry.registerItem(tarantulaSpawnEgg, "spawn_egg_tarantula");
		}
		if (MainRegistry.enableDreath)
		{
			GameRegistry.registerItem(dreathSpawnEgg, "spawn_egg_dreath");
			GameRegistry.registerItem(miredSpawnEgg, "spawn_egg_mired");
		}
		if (MainRegistry.enableGoat)
		{
    		GameRegistry.registerItem(goatSpawnEgg, "spawn_egg_goat");
		}
		if (MainRegistry.enableDireWolf)
		{
			GameRegistry.registerItem(direWolfSpawnEgg, "spawn_egg_dire_wolf");
		}
		if (MainRegistry.enableMagmaPlant)
		{
			GameRegistry.registerItem(magmaPlantSpawnEgg, "spawn_egg_magma_plant");
		}
		if (MainRegistry.enableDragonfly)
		{
			GameRegistry.registerItem(dragonflySpawnEgg, "spawn_egg_dragonfly");
		}
		if (MainRegistry.enableArmadillo)
		{
			GameRegistry.registerItem(armadilloSpawnEgg, "spawn_egg_armadillo");
		}
		if (MainRegistry.enableJellyfish)
		{
			GameRegistry.registerItem(jellyfishSpawnEgg, "spawn_egg_jellyfish");
			GameRegistry.registerItem(netherJellyfishSpawnEgg, "spawn_egg_nether_jellyfish");
		}
		if (MainRegistry.enableSkeletonWolf)
		{
			GameRegistry.registerItem(skeletonWolfSpawnEgg, "spawn_egg_skeleton_wolf");
		}
		if (MainRegistry.enableGoose)
		{
			GameRegistry.registerItem(gooseSpawnEgg, "spawn_egg_goose");
		}
		if (MainRegistry.enableImprovedCats)
		{
			GameRegistry.registerItem(wildCatSpawnEgg, "spawn_egg_wild_cat");
		}
		if (MainRegistry.enableSeaScorpion)
		{
			GameRegistry.registerItem(seaScorpionSpawnEgg, "spawn_egg_sea_scorpion");
		}
		if (MainRegistry.enableFaded)
		{
			GameRegistry.registerItem(fadedSpawnEgg, "spawn_egg_faded");
		}
	}
}