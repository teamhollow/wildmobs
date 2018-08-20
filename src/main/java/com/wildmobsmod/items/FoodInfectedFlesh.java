package com.wildmobsmod.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.wildmobsmod.main.MainRegistry;

public class FoodInfectedFlesh extends ItemFood
{

    private int potionId2;
    
    private int potionDuration2;
    
    private int potionAmplifier2;
    
    private float potionEffectProbability2;
    
	private float saturationModifier;

	public FoodInfectedFlesh(int j, boolean b){
		super(j, b);
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setTextureName("wildmobsmod:infected_flesh");
		this.saturationModifier = 0.1f;
		this.setPotionEffect(17, 30, 0, 0.80F);
		this.setPotionEffect2(19, 10, 0, 0.55F);
    }
	
    protected void onFoodEaten(ItemStack p_77849_1_, World p_77849_2_, EntityPlayer p_77849_3_)
    {
    	super.onFoodEaten(p_77849_1_, p_77849_2_, p_77849_3_);
    	
        if (!p_77849_2_.isRemote && this.potionId2 > 0 && p_77849_2_.rand.nextFloat() < this.potionEffectProbability2)
        {
            p_77849_3_.addPotionEffect(new PotionEffect(this.potionId2, this.potionDuration2 * 20, this.potionAmplifier2));
        }
    }
	
    public ItemFood setPotionEffect2(int p_77844_1_, int p_77844_2_, int p_77844_3_, float p_77844_4_)
    {
        this.potionId2 = p_77844_1_;
        this.potionDuration2 = p_77844_2_;
        this.potionAmplifier2 = p_77844_3_;
        this.potionEffectProbability2 = p_77844_4_;
        return this;
    }
}