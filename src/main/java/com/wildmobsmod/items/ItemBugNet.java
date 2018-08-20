package com.wildmobsmod.items;

import java.util.ArrayList;
import java.util.Random;

import com.wildmobsmod.entity.passive.butterfly.EntityButterfly;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockColored;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class ItemBugNet extends Item {
	public ItemBugNet(){
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setTextureName("wildmobsmod:bug_net");
        this.maxStackSize = 1;
        this.setMaxDamage(59);
    }
	
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }	
}
