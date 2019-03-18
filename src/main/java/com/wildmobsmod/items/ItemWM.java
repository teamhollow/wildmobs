package com.wildmobsmod.items;

import com.wildmobsmod.main.WildMobsMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemWM extends Item
{
	public Item setInternalName(String name)
	{
		setUnlocalizedName(name);
		setTextureName(name);
		return this;
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return WildMobsMod.TAB_WILDMOBS;
	}
	
	@Override
	public Item setTextureName(String texture)
	{
		return super.setTextureName(WildMobsMod.MODID + ":" + texture);
	}

	@Override
	public Item setUnlocalizedName(String name)
	{
		return super.setUnlocalizedName(WildMobsMod.MODID + ":" + name);
	}
}
