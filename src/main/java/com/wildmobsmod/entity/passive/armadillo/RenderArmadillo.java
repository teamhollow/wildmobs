package com.wildmobsmod.entity.passive.armadillo;

import com.wildmobsmod.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderArmadillo extends RenderLiving{
	
    private static final ResourceLocation armadilloTextures = new ResourceLocation(Strings.MODID + ":textures/entity/armadillo/armadillo.png");
    private static final ResourceLocation armadilloBallTextures = new ResourceLocation(Strings.MODID + ":textures/entity/armadillo/armadillo_ball.png");

    public RenderArmadillo(ModelBase p_i1253_1_, float p_i1253_2_)
    {
        super(p_i1253_1_, p_i1253_2_);
        this.shadowSize = 0.3F;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityArmadillo p_110775_1_)
    {
        return p_110775_1_.getBallTimer() <= 0 ? armadilloTextures : armadilloBallTextures;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityArmadillo)p_110775_1_);
    }
}