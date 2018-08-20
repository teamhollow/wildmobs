package com.wildmobsmod.items;

import java.util.List;

import com.wildmobsmod.entity.monster.mired.EntityMiredSummoner;
import com.wildmobsmod.entity.monster.wizard.EntityWizard;
import com.wildmobsmod.entity.passive.butterfly.EntityButterfly;
import com.wildmobsmod.entity.passive.dragonfly.EntityDragonfly;
import com.wildmobsmod.entity.projetile.spell.EntitySpell;
import com.wildmobsmod.main.MainRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemMiredBottle extends Item {
    @SideOnly(Side.CLIENT)
    
	public ItemMiredBottle(){
        this.maxStackSize = 1;
        this.setMaxDamage(0);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setTextureName("wildmobsmod:mired_bottle");
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

    		EntityMiredSummoner entitymiredsummoner = new EntityMiredSummoner(p_77648_3_);
    		entitymiredsummoner.setLocationAndAngles((double)p_77648_4_ + 0.5D, (double)p_77648_5_ + d0, (double)p_77648_6_ + 0.5D, MathHelper.wrapAngleTo180_float(p_77648_3_.rand.nextFloat() * 360.0F), 0.0F);
    		p_77648_3_.spawnEntityInWorld(entitymiredsummoner);
    		entitymiredsummoner.setLifeTime(1200);

            ItemStack itemstack =  p_77648_2_.inventory.getCurrentItem();

            if (itemstack != null && itemstack.getItem() == WildMobsModItems.miredBottle && ! p_77648_2_.capabilities.isCreativeMode)
            {
                if (itemstack.stackSize-- == 1)
                {
                	 p_77648_2_.inventory.setInventorySlotContents(p_77648_2_.inventory.currentItem, new ItemStack(Items.glass_bottle));
                }
                else if (! p_77648_2_.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle)))
                {
                	 p_77648_2_.dropPlayerItemWithRandomChoice(new ItemStack(Items.glass_bottle, 1, 0), false);
                }

                return true;
            }
    		return true;
    	}
    }
}