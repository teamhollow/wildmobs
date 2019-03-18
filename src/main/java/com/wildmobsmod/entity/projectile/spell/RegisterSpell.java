//package com.wildmobsmod.entity.projectile.spell;
//
//import com.wildmobsmod.main.WildMobsMod;
//
//import cpw.mods.fml.common.registry.EntityRegistry;
//
//public class RegisterSpell
//{
//	public static void mainRegistry()
//	{
//		registerModEntity();
//	}
//
//	public static void registerModEntity()
//	{
//		boolean flag = WildMobsMod.enableWizard;
//		if (flag)
//		{
//			createEntity(EntitySpell.class, "Spell", 16777215, 16777215);
//		}
//	}
//
//	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor)
//	{
//		EntityRegistry.registerModEntity(entityClass, entityName, 7, WildMobsMod.modInstance, 82, 1, true);
//	}
//}
