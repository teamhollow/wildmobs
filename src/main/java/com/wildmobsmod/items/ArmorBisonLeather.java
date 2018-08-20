package com.wildmobsmod.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ArmorBisonLeather extends ItemArmor {

	private String [] armourTypes = new String [] {"helmetBison", "chestplateBison", "legsBison", "bootsBison"};

	public ArmorBisonLeather(ArmorMaterial armorMaterial, int renderIndex, int armourType){
    	super(armorMaterial, renderIndex, armourType);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer){
		
		if(stack.getItem().equals(WildMobsModItems.helmetBison)|| stack.getItem().equals(WildMobsModItems.chestplateBison)|| stack.getItem().equals(WildMobsModItems.bootsBison)){
		
			return "wildmobsmod:textures/models/armor/bison_layer_1.png";
			
		}
		
		if(stack.getItem().equals(WildMobsModItems.legsBison)){

			return "wildmobsmod:textures/models/armor/bison_layer_2.png";

		}
		else return null;
	}

	@Override

	public void registerIcons(IIconRegister reg){

	    if(this == WildMobsModItems.helmetBison)

		    this.itemIcon = reg.registerIcon("wildmobsmod:bison_helmet");

	    if(this == WildMobsModItems.chestplateBison)

		    this.itemIcon = reg.registerIcon("wildmobsmod:bison_chestplate");

	    if(this == WildMobsModItems.legsBison)

		    this.itemIcon = reg.registerIcon("wildmobsmod:bison_leggings");

        if(this == WildMobsModItems.bootsBison)
			
		    this.itemIcon = reg.registerIcon("wildmobsmod:bison_boots");
	}
}