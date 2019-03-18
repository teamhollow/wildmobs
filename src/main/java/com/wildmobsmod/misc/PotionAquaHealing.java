package com.wildmobsmod.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionAquaHealing extends Potion
{
	public PotionAquaHealing(int id)
	{
		super(id, false, 564902);
	}

	@Override
	public boolean isBadEffect()
	{
		return false;
	}

	public void performEffect(EntityLivingBase living, int amp)
	{
		if(living.getHealth() < living.getMaxHealth() && living.isWet())
		{
			living.heal(1.0F);
		}
	}

	public boolean isReady(int i, int j)
	{
		int k;
		k = 30 >> j;
		return k > 0 ? i % k == 0 : true;
	}

	public Potion setIconIndex(int i, int j)
	{
		super.setIconIndex(i, j);
		return this;
	}

	@Override
	public int getStatusIconIndex()
	{
		ResourceLocation r = new ResourceLocation("wildmobsmod", "textures/gui/inventory.png");
		ITextureObject texture = Minecraft.getMinecraft().renderEngine.getTexture(r);
		Minecraft.getMinecraft().renderEngine.bindTexture(r);
		return super.getStatusIconIndex();
	}
}
