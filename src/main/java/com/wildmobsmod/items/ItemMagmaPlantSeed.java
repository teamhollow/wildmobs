package com.wildmobsmod.items;

import com.wildmobsmod.entity.monster.magmaplant.EntityMagmaPlant;
import com.wildmobsmod.entity.passive.butterfly.EntityButterfly;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemMagmaPlantSeed extends Item {
	public ItemMagmaPlantSeed(){
        this.maxStackSize = 16;
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setTextureName("wildmobsmod:magma_plant_seed");
    }
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
    	if (p_77648_3_.isRemote)
    	{
    		return true;
    	}
    	else
    	{
            int i = p_77648_1_.getItemDamage();
    		Block block = p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_);
    		p_77648_4_ += Facing.offsetsXForSide[p_77648_7_];
    		p_77648_5_ += Facing.offsetsYForSide[p_77648_7_];
    		p_77648_6_ += Facing.offsetsZForSide[p_77648_7_];
    		double d0 = 0.0D;

    		if (p_77648_7_ == 1 && block.getRenderType() == 11)
    		{
    			d0 = 0.5D;
    		}

    		Block block1 = p_77648_3_.getBlock(p_77648_4_, p_77648_5_ - 1, p_77648_6_);

    		if (block1 == Blocks.netherrack)
    		{
    			EntityMagmaPlant entityliving = new EntityMagmaPlant(p_77648_3_);
    			entityliving.setLocationAndAngles((double)p_77648_4_ + 0.5D, (double)p_77648_5_ + d0, (double)p_77648_6_ + 0.5D, MathHelper.wrapAngleTo180_float(p_77648_3_.rand.nextFloat() * 360.0F), 0.0F);
    			if (entityliving.worldObj.checkNoEntityCollision(entityliving.boundingBox))
    			{
    				entityliving.rotationYawHead = entityliving.rotationYaw;
    				entityliving.renderYawOffset = entityliving.rotationYaw;
    				entityliving.onSpawnWithEgg((IEntityLivingData)null);
    				entityliving.setStage(0);
    				entityliving.setGrowingAge(p_77648_3_.rand.nextInt(10000) + 10000);
    				entityliving.setIsWild(false);
    				p_77648_3_.spawnEntityInWorld(entityliving);
    				p_77648_1_.stackSize --;
    				return true;
    			}
    			else
    			{
    				return false;
    			}
    		}
    		else if (block1 == Blocks.quartz_ore)
    		{
    			EntityMagmaPlant entityliving = new EntityMagmaPlant(p_77648_3_);
    			entityliving.setLocationAndAngles((double)p_77648_4_ + 0.5D, (double)p_77648_5_ + d0, (double)p_77648_6_ + 0.5D, MathHelper.wrapAngleTo180_float(p_77648_3_.rand.nextFloat() * 360.0F), 0.0F);
    			if (entityliving.worldObj.checkNoEntityCollision(entityliving.boundingBox))
    			{
    				entityliving.rotationYawHead = entityliving.rotationYaw;
    				entityliving.renderYawOffset = entityliving.rotationYaw;
    				entityliving.onSpawnWithEgg((IEntityLivingData)null);
    				entityliving.setStage(0);
    				entityliving.setGrowingAge(p_77648_3_.rand.nextInt(10000) + 10000);
    				entityliving.setIsWild(false);
    				p_77648_3_.spawnEntityInWorld(entityliving);
    				p_77648_1_.stackSize --;
    				return true;
    			}
    			else
    			{
    				return false;
    			}
    		}
    		else
    		{
    			return false;
    		}
    	}
    }
}