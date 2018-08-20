package com.wildmobsmod.misc;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

import com.wildmobsmod.main.MainRegistry;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class AquaHealingDrinkWaterHandler
{
	@SubscribeEvent
	public void OnFinnishDrinking(PlayerUseItemEvent.Finish event) 
	{
		if(event.item.getItem() == Items.potionitem && (event.item.getItem().getDamage(event.item) == 0 || event.item.getItem().getDamage(event.item) == 64 || event.item.getItem().getDamage(event.item) == 16) && event.entityLiving.isPotionActive(MainRegistry.aquaHealingID) == true && event.entityLiving.isWet() == false) 
		{
			if (event.entityLiving.getHealth() < event.entityLiving.getMaxHealth() - 1)
			{
				event.entityLiving.heal(2.0F);
			}
			else
			{
				if (event.entityLiving.getHealth() < event.entityLiving.getMaxHealth())
				{
					event.entityLiving.heal(1.0F);
				}
			}
		}
	}
}