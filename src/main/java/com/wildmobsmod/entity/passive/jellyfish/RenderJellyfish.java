package com.wildmobsmod.entity.passive.jellyfish;

import org.lwjgl.opengl.GL11;

import com.wildmobsmod.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderJellyfish extends RenderLiving{
	
    private static final ResourceLocation orangeTextures = new ResourceLocation(Strings.MODID + ":textures/entity/jellyfish/jellyfish_orange.png");
    private static final ResourceLocation blueTextures = new ResourceLocation(Strings.MODID + ":textures/entity/jellyfish/jellyfish_blue.png");
    private static final ResourceLocation pinkTextures = new ResourceLocation(Strings.MODID + ":textures/entity/jellyfish/jellyfish_pink.png");
    private static final ResourceLocation redTextures = new ResourceLocation(Strings.MODID + ":textures/entity/jellyfish/jellyfish_red.png");
    private static final ResourceLocation yellowTextures = new ResourceLocation(Strings.MODID + ":textures/entity/jellyfish/jellyfish_yellow.png");
    private static final ResourceLocation whiteTextures = new ResourceLocation(Strings.MODID + ":textures/entity/jellyfish/jellyfish_white.png");
    private static final ResourceLocation netherTextures = new ResourceLocation(Strings.MODID + ":textures/entity/jellyfish/jellyfish_nether.png");

    public RenderJellyfish(ModelBase p_i1253_1_, float p_i1253_2_)
    {
    	super(p_i1253_1_, p_i1253_2_);
    	this.shadowSize = 0.4F;
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityJellyfish p_77041_1_, float p_77041_2_)
    {
    	if (p_77041_1_.getNether() == false)
    	{
    		GL11.glScalef(p_77041_1_.wideness / 2, p_77041_1_.highness / 2, p_77041_1_.wideness / 2);
    	}
    	else
    	{
    		GL11.glScalef(p_77041_1_.wideness / 2 * 1.5F, p_77041_1_.highness / 2 * 1.5F, p_77041_1_.wideness / 2 * 1.5F);
    	}
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityJellyfish p_110775_1_)
    {
    	int type = p_110775_1_.getDataWatcher().getWatchableObjectByte(20);
    	switch(p_110775_1_.getDataWatcher().getWatchableObjectByte(20)) {
    	case 0: return orangeTextures;
    	case 1: return blueTextures;
    	case 2: return pinkTextures;
    	case 3: return redTextures;
    	case 4: return yellowTextures;
    	case 5: return whiteTextures;
    	case 6: return netherTextures;
    	default: return orangeTextures;
    	}
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityLiving p_110775_1_)
    {
        return this.getEntityTexture((EntityJellyfish)p_110775_1_);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((EntityJellyfish)p_77041_1_, p_77041_2_);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityJellyfish)p_110775_1_);
    }

}