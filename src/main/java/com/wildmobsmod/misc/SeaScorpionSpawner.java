package com.wildmobsmod.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.wildmobsmod.entity.monster.seascorpion.EntitySeaScorpion;
import com.wildmobsmod.entity.monster.skeleton.EntityWMSkeleton;
import com.wildmobsmod.main.MainRegistry;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class SeaScorpionSpawner
{ 
	public final IEntitySelector seaScorpion = new IEntitySelector()
	{
		public boolean isEntityApplicable(Entity p_82704_1_)
		{
			return p_82704_1_.isEntityAlive() && ((EntitySeaScorpion)p_82704_1_).getIsWild() == true;
		}
	};

	@SubscribeEvent
	public void OnLivingUpdate(LivingUpdateEvent event)
	{
		if(!event.entity.worldObj.isRemote) 
		{
			if (event.entity instanceof EntityPlayer && event.entity.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && event.entity.worldObj.provider instanceof WorldProviderSurface) 
			{
				for (int k = 0; k < MainRegistry.seaScorpionSpawnRate; ++k)
				{
					double x;
					double y;
					double z;

					int i1;
					i1 = event.entity.worldObj.rand.nextInt(2);
					if (i1 == 0)
					{
						x = event.entity.posX + 24.D + event.entity.worldObj.rand.nextInt(128);
					}
					else
					{
						x = event.entity.posX - 24.D - event.entity.worldObj.rand.nextInt(128);
					}

					y = 1 + event.entity.worldObj.rand.nextInt(255);

					int i2;
					i2 = event.entity.worldObj.rand.nextInt(2);
					if (i2 == 0)
					{
						z = event.entity.posZ + 24.D + event.entity.worldObj.rand.nextInt(128);
					}
					else
					{
						z = event.entity.posZ - 24.D - event.entity.worldObj.rand.nextInt(128);
					}

					EntitySeaScorpion entityseascorpion = new EntitySeaScorpion(event.entity.worldObj);
					entityseascorpion.setPosition(x, y, z);
					entityseascorpion.onSpawnWithEgg((IEntityLivingData)null);
					int i = MathHelper.floor_double(entityseascorpion.posX);
					int j = MathHelper.floor_double(entityseascorpion.posZ);
					boolean spawnPack = true;
					ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<BiomeDictionary.Type>(Arrays.asList(BiomeDictionary.getTypesForBiome(event.entity.worldObj.getBiomeGenForCoords(i, j))));
					if (entityseascorpion.posY <= 40 && event.entity.worldObj.rand.nextInt(20) == 0 && biomeTypesList.contains(BiomeDictionary.Type.OCEAN))
					{
						entityseascorpion.setSize(10);
						entityseascorpion.setIsSeaMonster(true);
						spawnPack = false;
					}
					List list1 = event.entity.worldObj.selectEntitiesWithinAABB(EntitySeaScorpion.class, entityseascorpion.boundingBox.expand(272.0D, 272.0D, 272.0D), this.seaScorpion);
					int seaScorpionAmountFar = list1.size();
					List list2 = event.entity.worldObj.selectEntitiesWithinAABB(EntitySeaScorpion.class, entityseascorpion.boundingBox.expand(10.0D, 10.0D, 10.0D), this.seaScorpion);
					int seaScorpionAmountNear = list2.size();
					if (biomeTypesList.contains(BiomeDictionary.Type.OCEAN))
					{
						if (event.entity.worldObj.rand.nextInt(2) == 0)
						{
							if (entityseascorpion.getCanSpawnHere() == true && seaScorpionAmountFar < 40 && seaScorpionAmountNear < 10)
							{
								event.entity.worldObj.spawnEntityInWorld(entityseascorpion);
								if (spawnPack = true)
								{
									entityseascorpion.spawnPack();
								}
							}
						}
					}
					else
					{
						if (entityseascorpion.getCanSpawnHere() == true && seaScorpionAmountFar < 40 && seaScorpionAmountNear < 10)
						{
							event.entity.worldObj.spawnEntityInWorld(entityseascorpion);
							if (spawnPack = true)
							{
								entityseascorpion.spawnPack();
							}
						}
					}
				}
			}
		}
	}
}