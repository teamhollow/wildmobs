package com.wildmobsmod.items;

import com.wildmobsmod.main.WildMobsMod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemFoodGoldenSeaEgg extends ItemWMFood
{
	public ItemFoodGoldenSeaEgg(int foodValue, float saturation, boolean isWolfFavorite)
	{
		super(foodValue, saturation, isWolfFavorite);
		setPotionEffect(WildMobsMod.aquaHealingID, 1200, 0, 1F);
		setAlwaysEdible();
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
	{
		if(!world.isRemote)
		{
			player.heal(10F);
			player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 1200, 0));
		}
		return super.onEaten(stack, world, player);
	}
}
