package com.wildmobsmod.entity.monster.dreath.mired;

import com.wildmobsmod.main.WildMobsMod;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityMiredSummoner extends Entity
{
	//
	// This entity is spawned when a mired bottle is used on the ground. It will
	// randomly spawn mireds with random items in them which can be changed in
	// the config. It will disappear after some time. The feature
	// is basically a minigame with a chance to get some rare items.
	//
	
	public static final int EXPECTED_LIFETIME = 1200;
	private static final int DROPPING_THRESHOLD = EXPECTED_LIFETIME - 60, DROPPING_DURATION = DROPPING_THRESHOLD - 100, AVERAGE_DROPPING_TRIES = 10;
	
	private final int miredSpawnChance;
	private int remainingDrops;

	public EntityMiredSummoner(World world)
	{
		super(world);
		this.setSize(1.0F, 1.0F);
		remainingDrops = WildMobsMod.DREATH_MIRED_CONFIG.getMiredDropCount();
		miredSpawnChance = DROPPING_DURATION / (AVERAGE_DROPPING_TRIES * remainingDrops);
	}

	protected void entityInit()
	{
		this.dataWatcher.addObject(21, new Integer(0));
	}

	protected void readEntityFromNBT(NBTTagCompound nbt)
	{
		this.setLifeTime(nbt.getInteger("LifeTime"));
	}

	protected void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("LifeTime", this.getLifeTime());
	}

	public int getLifeTime()
	{
		return this.dataWatcher.getWatchableObjectInt(21);
	}

	public void setLifeTime(int lifetime)
	{
		this.dataWatcher.updateObject(21, Integer.valueOf((lifetime)));
	}

	public void onUpdate()
	{
		for(int i = 0; i < 4; ++i)
		{
			this.worldObj.spawnParticle("largesmoke", this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width, this.posY + this.rand.nextDouble() * (double) this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width, 0.0D, 0.0D, 0.0D);
		}
		if(!this.worldObj.isRemote)
		{
			int lifetime = this.getLifeTime();
			if(lifetime <= 0)
			{
				if(WildMobsMod.DREATH_MIRED_CONFIG.getGuaranteeDropCount()) {
					while(remainingDrops-- > 0) {
						spawnMired((int) posX, (int) posY, (int) posZ, WildMobsMod.DREATH_MIRED_CONFIG.getRandomLootStack());
					}
				}
				this.setDead();
			}
			else
			{
				lifetime--;
				this.setLifeTime(lifetime);
			}
			//Spawn loot
			if(lifetime <= DROPPING_THRESHOLD && remainingDrops > 0) {
				if(this.rand.nextInt(miredSpawnChance) == 0) {
					if(trySpawnMired()) {
						remainingDrops--;
					}
				}
			}
		}
		super.onUpdate();
	}
	
	private boolean trySpawnMired() {
		// 1210 positions in range, 121 expected to be valid -> AVERAGE_DROPPING_TRIES constant
		int miredX = ((int) posX) + this.rand.nextInt(11) - 5;
		int miredY = ((int) posY) + this.rand.nextInt(10) - 4;
		int miredZ = ((int) posZ) + this.rand.nextInt(11) - 5;
		Block block = worldObj.getBlock(miredX, miredY, miredZ);
		if((block.isAir(worldObj, miredX, miredY, miredZ) || block.getCollisionBoundingBoxFromPool(worldObj, miredX, miredY, miredZ) == null) && worldObj.getBlock(miredX, miredY - 1, miredZ).getMaterial().blocksMovement())
		{
			ItemStack loot = WildMobsMod.DREATH_MIRED_CONFIG.getRandomLootStack();
			if(loot != null && loot.isItemStackDamageable()) {	// randomize damage on tools
				loot.setItemDamage(worldObj.rand.nextInt(loot.getMaxDamage()));
			}
			spawnMired(miredX, miredY, miredZ, loot);
			return true;
		}
		return false;
	}
	
	private void spawnMired(int x, int y, int z, ItemStack loot) {
		EntityMired entitymired = new EntityMired(worldObj);
		entitymired.setLocationAndAngles(x, y, z, rotationYaw, rotationPitch);
		entitymired.setCurrentItemOrArmor(0, loot);
		worldObj.spawnEntityInWorld(entitymired);
	}
}
