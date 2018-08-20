package com.wildmobsmod.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;


public class BlockBisonBlock extends Block{
	
	public BlockBisonBlock(){
        super(Material.cloth);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(0.8F);
        this.setResistance(4F);
        this.setStepSound(soundTypeCloth);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg)
    {
        this.blockIcon = reg.registerIcon("wildmobsmod:bison_leather_block");
    }

}

