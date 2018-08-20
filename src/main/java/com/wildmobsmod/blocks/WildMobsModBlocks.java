package com.wildmobsmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.util.RegistryNamespaced;

import com.wildmobsmod.items.ItemArmadilloShellBlockSlab;
import com.wildmobsmod.main.MainRegistry;

import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;

public class WildMobsModBlocks {
	
    public static void mainRegistry(){
    	initBlocks();
    	registerBlocks();
    }
    
    public static final RegistryNamespaced blockRegistry = GameData.getBlockRegistry();
    public static final Block.SoundType soundTypeCloth = new Block.SoundType("cloth", 1.0F, 1.0F);
    
    public static Block bisonBlock;
    public static Block leatherBlock;
    public static Block furBlock;
    public static Block furCarpet;
    public static Block leatherCarpet;
    public static Block bisonCarpet;
    public static Block bladderBlockStage0;
    public static Block bladderBlockStage1;
    public static Block bladderBlockStage2;
    public static Block armadilloShellBlock;
    public static Block armadilloShellBlockStairs;
    public static BlockSlab armadilloShellBlockSlabSingle;
    public static BlockSlab armadilloShellBlockSlabDouble;
    public static Block armadilloShellBlockCracked;
    public static Block armadilloShellBlockPatterned;
    
    public static void initBlocks(){
    	bisonBlock = new BlockBisonBlock().setBlockName("bison_block");
    	leatherBlock = new BlockLeatherBlock().setBlockName("leather_block");
    	furBlock = new BlockFurBlock().setBlockName("fur_block");
    	furCarpet = new BlockFurCarpet().setHardness(0.1F).setStepSound(soundTypeCloth).setBlockName("fur_carpet").setLightOpacity(0);
    	leatherCarpet = new BlockLeatherCarpet().setHardness(0.1F).setStepSound(soundTypeCloth).setBlockName("leather_carpet").setLightOpacity(0);
    	bisonCarpet = new BlockBisonCarpet().setHardness(0.1F).setStepSound(soundTypeCloth).setBlockName("bison_carpet").setLightOpacity(0);
    	bladderBlockStage0 = new BlockBladderBlockStage0().setBlockName("bladder_block_damaged_0");
    	bladderBlockStage1 = new BlockBladderBlockStage1().setBlockName("bladder_block_damaged_1");
    	bladderBlockStage2 = new BlockBladderBlockStage2().setBlockName("bladder_block_damaged_2");
    	armadilloShellBlock = new BlockArmadilloShellBlock().setBlockName("armadillo_shell_block").setBlockTextureName("wildmobsmod:armadillo_shell_block");
    	armadilloShellBlockStairs = new BlockArmadilloShellBlockStairs(armadilloShellBlockStairs, 0).setBlockName("armadillo_shell_block_stairs").setBlockTextureName("wildmobsmod:armadillo_shell_block");
    	armadilloShellBlockSlabSingle = (BlockSlab) new BlockArmadilloShellBlockSlab(false).setBlockTextureName("wildmobsmod:armadillo_shell_block");
    	armadilloShellBlockSlabDouble = (BlockSlab) new BlockArmadilloShellBlockSlab(true).setBlockTextureName("wildmobsmod:armadillo_shell_block");
    	armadilloShellBlockCracked = new BlockArmadilloShellBlock().setBlockName("armadillo_shell_block_cracked").setBlockTextureName("wildmobsmod:armadillo_shell_block_cracked");
    	armadilloShellBlockPatterned = new BlockArmadilloShellBlock().setBlockName("armadillo_shell_block_patterned").setBlockTextureName("wildmobsmod:armadillo_shell_block_patterned");
    }
    
    public static void registerBlocks(){
    	
		if (MainRegistry.enableBisonLeather)
		{
			GameRegistry.registerBlock(bisonBlock, "bisonBlock");
			GameRegistry.registerBlock(bisonCarpet, "bisonCarpet");
		}

		if (MainRegistry.enableLeatherBlock)
		{
			GameRegistry.registerBlock(leatherBlock, "leatherBlock");
			GameRegistry.registerBlock(leatherCarpet, "leatherCarpet");
		}

		if (MainRegistry.enableFur)
		{
         	GameRegistry.registerBlock(furBlock, "furBlock");
         	GameRegistry.registerBlock(furCarpet, "furCarpet");
		}
		if (MainRegistry.enableMagmaPlant)
		{
			GameRegistry.registerBlock(bladderBlockStage0, "bladderBlockStage0");
			GameRegistry.registerBlock(bladderBlockStage1, "bladderBlockStage1");
			GameRegistry.registerBlock(bladderBlockStage2, "bladderBlockStage2");
		}
		if (MainRegistry.enableArmadillo)
		{
			GameRegistry.registerBlock(armadilloShellBlock, "armadilloShellBlock");
			GameRegistry.registerBlock(armadilloShellBlockStairs, "armadilloShellBlockStairs");
			GameRegistry.registerBlock(armadilloShellBlockSlabSingle, ItemArmadilloShellBlockSlab.class, "armadilloShellBlockSlabSingle");
			GameRegistry.registerBlock(armadilloShellBlockSlabDouble, ItemArmadilloShellBlockSlab.class, "armadilloShellBlockSlabDouble");
			GameRegistry.registerBlock(armadilloShellBlockCracked, "armadilloShellBlockCracked");
			GameRegistry.registerBlock(armadilloShellBlockPatterned, "armadilloShellBlockPatterned");
		}
    }
}