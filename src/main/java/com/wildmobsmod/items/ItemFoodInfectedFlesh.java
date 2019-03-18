package com.wildmobsmod.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemFoodInfectedFlesh extends ItemWMFood
{
	public ItemFoodInfectedFlesh(int foodValue, float saturation, boolean isWolfFavorite)
	{
		super(foodValue, saturation, isWolfFavorite);
		setPotionEffect(Potion.hunger.id, 400, 0, 0.5F);
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player)
	{
		if(!world.isRemote && world.rand.nextFloat() < 0.1F)
		{
			player.addPotionEffect(new PotionEffect(Potion.poison.id, 200, 0));
		}
		super.onFoodEaten(stack, world, player);
	}
}
