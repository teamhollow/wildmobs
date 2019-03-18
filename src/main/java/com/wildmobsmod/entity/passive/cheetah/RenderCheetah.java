package com.wildmobsmod.entity.passive.cheetah;

import com.wildmobsmod.main.WildMobsMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderCheetah extends RenderLiving
{
	private static final ResourceLocation texture = new ResourceLocation(WildMobsMod.MODID + ":textures/entity/cheetah.png");
	private static final ResourceLocation angryTexture = texture;	// TODO: Add texture (perhaps)

	public RenderCheetah(ModelBase model, float shadowSize)
	{
		super(model, shadowSize);
		this.shadowSize = 0.7F;
	}

	protected ResourceLocation getEntityTexture(EntityCheetah cheetah)
	{
//		return cheetah.getAttackTarget() != null ? angryTexture : texture;		// angry textures disabled for now
		return texture;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityCheetah) entity);
	}
}
