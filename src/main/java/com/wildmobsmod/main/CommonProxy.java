package com.wildmobsmod.main;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.logging.log4j.Level;

import com.wildmobsmod.blocks.WildMobsModBlocks;
import com.wildmobsmod.entity.WildMobsModEntities;
import com.wildmobsmod.items.WildMobsModItems;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.potion.Potion;
import net.minecraftforge.classloading.FMLForgePlugin;

public abstract class CommonProxy implements IProxy
{
	@Override
	public void registerModObjects()
	{
		WildMobsModBlocks.registerBlocks();
		WildMobsModItems.registerAll();
		CraftingManager.registerAll();

		WildMobsModEntities.registerAllEntities();
		
//		RegisterDeer.mainRegistry();
//		RegisterFox.mainRegistry();
//		RegisterCougar.mainRegistry();
//		RegisterZomgus.mainRegistry();
//		RegisterBison.mainRegistry();
//		RegisterWizard.mainRegistry();
//		RegisterSpell.mainRegistry();
//		RegisterMouse.mainRegistry();
//		RegisterButterfly.mainRegistry();
//		RegisterTarantula.mainRegistry();
//		RegisterDreath.mainRegistry();
//		RegisterMired.mainRegistry();
//		RegisterGoat.mainRegistry();
//		RegisterDireWolf.mainRegistry();
//		RegisterMagmaPlant.mainRegistry();
//		RegisterLavaSpit.mainRegistry();
//		RegisterDragonfly.mainRegistry();
//		RegisterWMWolf.mainRegistry();
//		RegisterArmadillo.mainRegistry();
//		RegisterJellyfish.mainRegistry();
//		RegisterSkeletonWolf.mainRegistry();
//		RegisterGoose.mainRegistry();
//		RegisterMiredSummoner.mainRegistry();
//		RegisterWMOcelot.mainRegistry();
//		RegisterSeaScorpion.mainRegistry();
//		RegisterSeaScorpionEgg.mainRegistry();
//		RegisterTarantulaHair.mainRegistry();
//		RegisterFaded.mainRegistry();

	}
	
	@Override
	public void performCompatRelevantActions()
	{
		WildMobsModEntities.registerAllSpawns();
		CraftingManager.addOreDictionaryCompat();
	}
	
	public static void expandPotionArray()
	{
		try
		{
			final int length = Potion.potionTypes.length;
			if(length < 256)
			{
				Potion[] newPotionTypes = new Potion[256];
				System.arraycopy(Potion.potionTypes, 0, newPotionTypes, 0, length);
				
				Field field = Potion.class.getField(FMLForgePlugin.RUNTIME_DEOBF ? "field_76425_a" : "potionTypes");
				field.setAccessible(true);
				Field modifiersField = Field.class.getDeclaredField("modifiers");
				modifiersField.setAccessible(true);
				modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
				
				field.set(null, newPotionTypes);
				FMLLog.log(WildMobsMod.MODID, Level.INFO, "Expanded Potion Array to 256 slots.");
			}
			else
			{
				FMLLog.log(WildMobsMod.MODID, Level.INFO, "Potion Array has already been expanded by another mod :)");
			}
		}
		catch(Exception e)
		{
			FMLLog.log(WildMobsMod.MODID, Level.ERROR, e, "Failed to expand Potion Array! Please report this (including the following StackTrace) to mod author.");
		}
	}
}
