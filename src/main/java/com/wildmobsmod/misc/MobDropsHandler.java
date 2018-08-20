package com.wildmobsmod.misc;

import java.util.ArrayList;
import java.util.Random;

import com.wildmobsmod.items.WildMobsModItems;
import com.wildmobsmod.main.MainRegistry;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MobDropsHandler {
    
    protected Random rand;
    
	@SubscribeEvent
    public void onMobDrops(LivingDropsEvent event)
	{
        this.rand = new Random();
        
		if (event.entity instanceof EntitySquid && MainRegistry.enableCalamari == true)
		{
			ItemStack stack = new ItemStack(WildMobsModItems.rawCalamari);
			ItemStack stack1 = new ItemStack(WildMobsModItems.cookedCalamari);
			
	    	int j = this.rand.nextInt(2) + 1 + this.rand.nextInt(1);

	    	for (int k = 0; k < j; ++k)
	    	{
				if (event.entity.isBurning())
				{
					EntityItem drop = new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, stack1);
					event.drops.add(drop);
				}
				else
				{
					EntityItem drop = new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, stack);
					event.drops.add(drop);
				}
	    	}
		}
	}
}
