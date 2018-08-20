package com.wildmobsmod.potions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionAquaHealing extends Potion
{
	public PotionAquaHealing(int par1, boolean par2, int par3)
	{
		super(par1, par2, par3);
	}

	@Override
	public boolean isBadEffect()
	{
		return false;
	}

	public void performEffect(EntityLivingBase p_76394_1_, int p_76394_2_)
	{
        if (p_76394_1_.getHealth() < p_76394_1_.getMaxHealth() && p_76394_1_.isWet())
        {
            p_76394_1_.heal(1.0F);
        }
	}
    /**
     * checks if Potion effect is ready to be applied this tick.
     */
	public boolean isReady(int p_76397_1_, int p_76397_2_)
	{
		int k;

        k = 30 >> p_76397_2_;
        return k > 0 ? p_76397_1_ % k == 0 : true;
	}

	public Potion setIconIndex(int par1, int par2) 
	{
		super.setIconIndex(par1, par2);
		return (Potion)this;
	}

	@Override
	public int getStatusIconIndex() 
	{
		ResourceLocation r = new ResourceLocation("wildmobsmod","textures/gui/inventory.png");

		ITextureObject texture = Minecraft.getMinecraft().renderEngine.getTexture(r);
		Minecraft.getMinecraft().renderEngine.bindTexture(r);

		return super.getStatusIconIndex();
	}
}