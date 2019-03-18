package com.wildmobsmod.entity.passive.hyena;

import com.wildmobsmod.main.WildMobsMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderHyena extends RenderLiving
{
	private static final ResourceLocation texture = new ResourceLocation(WildMobsMod.MODID + ":textures/entity/hyena/hyena.png");
	private static final ResourceLocation cubTexture = new ResourceLocation(WildMobsMod.MODID + ":textures/entity/hyena/hyena_cub.png");

	public RenderHyena(ModelBase model, float shadowSize)
	{
		super(model, shadowSize);
		this.shadowSize = 0.7F;
	}

	protected ResourceLocation getEntityTexture(EntityHyena cougar)
	{	
		return texture;	//TODO: Cubs
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityHyena) entity);
	}
}
