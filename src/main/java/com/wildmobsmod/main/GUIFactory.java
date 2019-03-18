package com.wildmobsmod.main;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.IModGuiFactory.RuntimeOptionCategoryElement;
import cpw.mods.fml.client.IModGuiFactory.RuntimeOptionGuiHandler;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class GUIFactory implements IModGuiFactory
{
	@Override
	public void initialize(Minecraft minecraftInstance) {}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return ModGuiConfig.class;
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {	
		return null;
	}
	
	public static class ModGuiConfig extends GuiConfig
	{
		public ModGuiConfig(GuiScreen guiScreen) {
			super(guiScreen, getAllConfigElements(WildMobsMod.config, WildMobsMod.CONFIG_CATEGORY_GENERAL, WildMobsMod.CONFIG_CATEGORY_ENTITIES), WildMobsMod.MODID, true, true, GuiConfig.getAbridgedConfigPath(WildMobsMod.config.toString()));
		}
		
		private static List<IConfigElement> getAllConfigElements(Configuration config, String... flattenCategories) {
			Set<String> inlines = Sets.newHashSet(flattenCategories);
			List<IConfigElement> elements = new LinkedList<>();
			for(String name : config.getCategoryNames()) {
				ConfigCategory cat = config.getCategory(name);
				if(cat != null) {
					if(inlines.contains(name)) {
						elements.addAll(new ConfigElement(cat).getChildElements());	// getChildElements is *not* recursive!
					} else if(!cat.isChild()) {
						elements.add(new ConfigElement(cat));
					}
				}
			}
			return elements;
		}
	}
}
