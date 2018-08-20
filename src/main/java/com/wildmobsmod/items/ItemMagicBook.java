package com.wildmobsmod.items;

import com.wildmobsmod.entity.monster.wizard.EntityWizard;
import com.wildmobsmod.entity.passive.butterfly.EntityButterfly;
import com.wildmobsmod.main.MainRegistry;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class ItemMagicBook extends Item {
	public ItemMagicBook(){
        this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setTextureName("wildmobsmod:book_magic");
    }
	
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack p_77636_1_)
    {
        return true;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
    	if (p_77648_3_.isRemote)
    	{
    		return true;
    	}
    	else
    	{
    		Block block = p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_);
    		p_77648_4_ += Facing.offsetsXForSide[p_77648_7_];
    		p_77648_5_ += Facing.offsetsYForSide[p_77648_7_];
    		p_77648_6_ += Facing.offsetsZForSide[p_77648_7_];
    		double d0 = 0.0D;

    		if (p_77648_7_ == 1 && block.getRenderType() == 11)
    		{
    			d0 = 0.5D;
    		}

    		EntityWizard entityliving = new EntityWizard(p_77648_3_);
    		entityliving.setLocationAndAngles((double)p_77648_4_ + 0.5D, (double)p_77648_5_ + d0, (double)p_77648_6_ + 0.5D, MathHelper.wrapAngleTo180_float(p_77648_3_.rand.nextFloat() * 360.0F), 0.0F);
    		entityliving.rotationYawHead = entityliving.rotationYaw;
    		entityliving.renderYawOffset = entityliving.rotationYaw;
    		entityliving.onSpawnWithEgg((IEntityLivingData)null);
    		p_77648_3_.spawnEntityInWorld(entityliving);
    		entityliving.playLivingSound();
    		p_77648_1_.stackSize --;
    		return true;
    	}
    }
}
