package com.wildmobsmod.misc;

import java.util.Random;

import com.wildmobsmod.entity.ai.EntityAIFollowSkeleton;
import com.wildmobsmod.entity.monster.skeletonwolf.EntitySkeletonWolf;
import com.wildmobsmod.entity.passive.cougar.EntityCougar;
import com.wildmobsmod.entity.passive.wolf.EntityWMWolf;
import com.wildmobsmod.items.WildMobsModItems;
import com.wildmobsmod.main.MainRegistry;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class WolfBreedsHandler
{ 
	@SubscribeEvent
	public void OnEntityJoinWorld(EntityJoinWorldEvent event) 
	{
		if(!event.world.isRemote) 
		{
			if (event.entity.getClass() == EntityWolf.class) 
			{
				EntityWMWolf spawnEntity = new EntityWMWolf(event.world);
				spawnEntity.setPosition(event.entity.posX, event.entity.posY, event.entity.posZ);
				spawnEntity.setAngles(event.entity.rotationPitch, event.entity.rotationYaw);
				event.world.spawnEntityInWorld(spawnEntity);
				event.entity.setDead();
			}
		}
	}
}