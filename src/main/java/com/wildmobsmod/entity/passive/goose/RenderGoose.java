package com.wildmobsmod.entity.passive.goose;

import org.lwjgl.opengl.GL11;

import com.wildmobsmod.entity.passive.jellyfish.EntityJellyfish;
import com.wildmobsmod.lib.Strings;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderGoose extends RenderLiving {
	private static final ResourceLocation gooseTextures = new ResourceLocation(Strings.MODID + ":textures/entity/goose.png");

	public RenderGoose(ModelBase p_i1253_1_, float p_i1253_2_)
	{
		super(p_i1253_1_, p_i1253_2_);
		this.shadowSize = 0.5F;
	}

	protected void preRenderCallback(EntityGoose p_77041_1_, float p_77041_2_)
	{
		GL11.glScalef(1.2F, 1.2F, 1.2F);
	}

	protected ResourceLocation getEntityTexture(EntityGoose p_110775_1_)
	{
		return gooseTextures;
	}

	protected ResourceLocation getEntityTexture(EntityLiving p_110775_1_)
	{
		return this.getEntityTexture((EntityGoose)p_110775_1_);
	}

	protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
	{
		this.preRenderCallback((EntityGoose)p_77041_1_, p_77041_2_);
	}

	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return this.getEntityTexture((EntityGoose)p_110775_1_);
	}
}
