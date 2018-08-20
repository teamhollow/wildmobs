package com.wildmobsmod.misc;

import java.util.Random;

import com.wildmobsmod.entity.ai.EntityAIFollowSkeleton;
import com.wildmobsmod.entity.monster.skeleton.EntityWMSkeleton;
import com.wildmobsmod.entity.monster.skeletonwolf.EntitySkeletonWolf;
import com.wildmobsmod.entity.passive.cougar.EntityCougar;
import com.wildmobsmod.entity.passive.ocelot.EntityWMOcelot;
import com.wildmobsmod.entity.passive.wolf.EntityWMWolf;
import com.wildmobsmod.items.WildMobsModItems;
import com.wildmobsmod.main.MainRegistry;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
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

public class CatBreedsHandler
{ 
	@SubscribeEvent
	public void OnEntityJoinWorld(EntityJoinWorldEvent event) 
	{
		if(!event.entity.worldObj.isRemote) 
		{
			if (event.entity.getClass() == EntityOcelot.class) 
			{
				EntityWMOcelot spawnEntity = new EntityWMOcelot(event.entity.worldObj);
				spawnEntity.setPosition(event.entity.posX, event.entity.posY, event.entity.posZ);
				spawnEntity.setAngles(event.entity.rotationPitch, event.entity.rotationYaw);
				spawnEntity.setGrowingAge(((EntityOcelot)event.entity).getGrowingAge());
				event.entity.worldObj.spawnEntityInWorld(spawnEntity);
				event.entity.setDead();
			}
		}
	}
}