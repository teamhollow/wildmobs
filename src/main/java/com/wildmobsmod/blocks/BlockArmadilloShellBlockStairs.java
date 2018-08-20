package com.wildmobsmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockArmadilloShellBlockStairs extends BlockStairs{

	protected BlockArmadilloShellBlockStairs(Block p_i45428_1_, int p_i45428_2_) {
		super(WildMobsModBlocks.armadilloShellBlock, p_i45428_2_);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(1.5F);
        this.setResistance(45F);
        this.setStepSound(soundTypeStone);
        this.setLightOpacity(0);
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon(this.getTextureName());
    }
}
