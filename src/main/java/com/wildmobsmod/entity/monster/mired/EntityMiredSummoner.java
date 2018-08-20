package com.wildmobsmod.entity.monster.mired;

import com.wildmobsmod.main.MainRegistry;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Facing;
import net.minecraft.world.World;

public class EntityMiredSummoner extends Entity
{
	//
	// This entity is spawned when a mired bottle is used on the ground. It will randomly spawn mireds with random items in them which can be changed in the config. It will disappear after some time. The feature
	// is basically a minigame with a chance to get some rare items.
	//
	
	public EntityMiredSummoner(World p_i1582_1_)
	{
		super(p_i1582_1_);
        this.setSize(1.0F, 1.0F);
	}

	protected void entityInit()
	{
		this.dataWatcher.addObject(21, new Integer(0));
	}

	protected void readEntityFromNBT(NBTTagCompound entity)
	{
        entity.setInteger("LifeTime", this.getLifeTime());
	}

	protected void writeEntityToNBT(NBTTagCompound entity)
	{
        this.setLifeTime(entity.getInteger("LifeTime"));
	}
	
    public int getLifeTime()
    {
        return this.dataWatcher.getWatchableObjectInt(21);
    }

    public void setLifeTime(int entity)
    {
        this.dataWatcher.updateObject(21, Integer.valueOf((entity)));
    }

	public void onUpdate()
	{
		for (int i = 0; i < 4; ++i)
		{
			this.worldObj.spawnParticle("largesmoke", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
		}

		if (!this.worldObj.isRemote)
		{
			int i = this.getLifeTime();

			if (i <= 0)
			{
				this.setDead();
			}
			else
			{
				i--;
				this.setLifeTime(i);
			}


			//
			// Summoning Mireds
			//
			int x = (int)this.posX;
			int y = (int)this.posY;
			int z = (int)this.posZ;

			if (this.rand.nextInt(15) == 0)
			{
				int miredX = x;
				int miredY = y;
				int miredZ = z;
				
				for (int j = 0; j < 4; ++j)
				{
					if (miredX == x && miredY == y && miredZ == z)
					{
						miredX = x + this.rand.nextInt(20) - this.rand.nextInt(20);
						miredY = y + this.rand.nextInt(20) - this.rand.nextInt(20);
						miredZ = z + this.rand.nextInt(20) - this.rand.nextInt(20);

						Block block = this.worldObj.getBlock(miredX, miredY, miredZ);
						Block block1 = this.worldObj.getBlock(miredX, miredY - 1, miredZ);

						if (block == Blocks.air && block1.isNormalCube())
						{
					    	Item[] commonDrops;
					    	
					    	commonDrops = new Item[MainRegistry.miredDropsCommon.length];
					    	
					    	for (int k = 0; k < MainRegistry.miredDropsCommon.length; ++k)
					    	{
					    		commonDrops[k] = MainRegistry.getItem(MainRegistry.listToString(MainRegistry.miredDropsCommon, k));
					    	}
					    	
					    	Item[] rareDrops;
					    	
					    	rareDrops = new Item[MainRegistry.miredDropsRare.length];
					    	
					    	for (int k = 0; k < MainRegistry.miredDropsRare.length; ++k)
					    	{
					    		rareDrops[k] = MainRegistry.getItem(MainRegistry.listToString(MainRegistry.miredDropsRare, k));
					    	}
					    	
					    	Item[] veryRareDrops;
					    	
					    	veryRareDrops = new Item[MainRegistry.miredDropsVeryRare.length];
					    	
					    	for (int k = 0; k < MainRegistry.miredDropsVeryRare.length; ++k)
					    	{
					    		veryRareDrops[k] = MainRegistry.getItem(MainRegistry.listToString(MainRegistry.miredDropsVeryRare, k));
					    	}
					    	
							Item itemCommon = commonDrops[this.rand.nextInt(commonDrops.length)];
							Item itemRare = rareDrops[this.rand.nextInt(rareDrops.length)];
							Item itemVeryRare = veryRareDrops[this.rand.nextInt(veryRareDrops.length)];

							EntityMired entitymired = new EntityMired(this.worldObj);
							entitymired.setLocationAndAngles(miredX, miredY, miredZ, this.rotationYaw, this.rotationPitch);
							
							int l = this.rand.nextInt(100);
							
							if (l < 23)
							{
								entitymired.setCurrentItemOrArmor(0, new ItemStack(itemRare));
							}
							else if (l >= 23 && l < 34)
							{
								entitymired.setCurrentItemOrArmor(0, new ItemStack(itemVeryRare));
							}
							else
							{
								entitymired.setCurrentItemOrArmor(0, new ItemStack(itemCommon));
							}
							worldObj.spawnEntityInWorld(entitymired);
						}
						else
						{
							miredX = x;
							miredY = y;
							miredZ = z;
						}
					}
				}
			}
		}

		super.onUpdate();
	}
}