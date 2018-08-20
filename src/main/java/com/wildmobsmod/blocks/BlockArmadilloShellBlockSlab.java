package com.wildmobsmod.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockArmadilloShellBlockSlab extends BlockSlab{

	public BlockArmadilloShellBlockSlab(boolean p_i45410_1_){
		super(p_i45410_1_, Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(1.5F);
        this.setResistance(45F);
        this.setStepSound(soundTypeStone);
        this.useNeighborBrightness = true;
        this.setBlockName("armadillo_shell_block_slab");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		if (item != Item.getItemFromBlock(WildMobsModBlocks.armadilloShellBlockSlabDouble))
		{
			list.add(new ItemStack(item, 1, 0));
		}
	}
	
	public String name (int par1)
	{
		return super.getUnlocalizedName();
	}

	@SideOnly(Side.CLIENT)
	private static boolean isBlockSingleSlab(Block block)
	{
		return block == WildMobsModBlocks.armadilloShellBlockSlabSingle;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int i, int j, int k)
	{
		return isBlockSingleSlab(this) ? Item.getItemFromBlock(this) : (this == WildMobsModBlocks.armadilloShellBlockSlabDouble ? Item.getItemFromBlock(WildMobsModBlocks.armadilloShellBlockSlabSingle) : Item.getItemFromBlock(WildMobsModBlocks.armadilloShellBlockSlabSingle));
	}
	
	@Override
	public Item getItemDropped(int i, Random rand, int j)
	{
		return Item.getItemFromBlock(WildMobsModBlocks.armadilloShellBlockSlabSingle);
	}
	
	protected ItemStack createStackedBlock(int i)
	{
		return new ItemStack(WildMobsModBlocks.armadilloShellBlockSlabSingle, 2, 0);
	}

	@Override
	public String func_150002_b(int p_150002_1_) {
		return super.getUnlocalizedName();
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon(this.getTextureName());
    }
}
