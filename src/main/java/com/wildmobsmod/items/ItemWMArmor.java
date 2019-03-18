package com.wildmobsmod.items;

import com.wildmobsmod.main.WildMobsMod;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemWMArmor extends ItemArmor
{
	protected final String iconName, textureName;

	public ItemWMArmor(ArmorMaterial armorMaterial, int renderIndex, int armourType, String iconName, String textureName)
	{
		super(armorMaterial, renderIndex, armourType);
		this.iconName = iconName;
		this.textureName = textureName;
	}
	
	@Override
	public CreativeTabs getCreativeTab()
	{
		return WildMobsMod.TAB_WILDMOBS;
	}
	
	@Override
	public Item setUnlocalizedName(String name)
	{
		return super.setUnlocalizedName(WildMobsMod.MODID + ":" + name);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
	{
		return textureName;
	}

	@Override
	public void registerIcons(IIconRegister reg)
	{
		itemIcon = reg.registerIcon(iconName);
	}
}
