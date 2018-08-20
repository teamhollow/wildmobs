package com.wildmobsmod.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ArmorFur extends ItemArmor {

	private String [] armourTypes = new String [] {"helmetFur", "chestplateFur", "legsFur", "bootsFur"};

	public ArmorFur(ArmorMaterial armorMaterial, int renderIndex, int armourType){
    	super(armorMaterial, renderIndex, armourType);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer){
		
		if(stack.getItem().equals(WildMobsModItems.helmetFur)|| stack.getItem().equals(WildMobsModItems.chestplateFur)|| stack.getItem().equals(WildMobsModItems.bootsFur)){
		
			return "wildmobsmod:textures/models/armor/fur_layer_1.png";
			
		}
		
		if(stack.getItem().equals(WildMobsModItems.legsFur)){

			return "wildmobsmod:textures/models/armor/fur_layer_2.png";

		}
		else return null;
	}

	@Override
	public void registerIcons(IIconRegister reg){

	    if(this == WildMobsModItems.helmetFur)

		    this.itemIcon = reg.registerIcon("wildmobsmod:fur_helmet");

	    if(this == WildMobsModItems.chestplateFur)

		    this.itemIcon = reg.registerIcon("wildmobsmod:fur_chestplate");

	    if(this == WildMobsModItems.legsFur)

		    this.itemIcon = reg.registerIcon("wildmobsmod:fur_leggings");

        if(this == WildMobsModItems.bootsFur)
			
		    this.itemIcon = reg.registerIcon("wildmobsmod:fur_boots");
	}
}
