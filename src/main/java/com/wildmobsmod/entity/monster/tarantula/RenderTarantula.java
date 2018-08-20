package com.wildmobsmod.entity.monster.tarantula;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import com.wildmobsmod.lib.Strings;

@SideOnly(Side.CLIENT)
public class RenderTarantula extends RenderSpider
{
    private static final ResourceLocation tarantulaTextures = new ResourceLocation(Strings.MODID + ":textures/entity/tarantula/tarantula.png");
    private static final String __OBFID = "CL_00000982";

    public RenderTarantula()
    {
        this.shadowSize *= 1.2F;
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityTarantula p_77041_1_, float p_77041_2_)
    {
        GL11.glScalef(1.2F, 1.2F, 1.2F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityTarantula p_110775_1_)
    {
        return tarantulaTextures;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySpider p_110775_1_)
    {
        return this.getEntityTexture((EntityTarantula)p_110775_1_);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((EntityTarantula)p_77041_1_, p_77041_2_);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityTarantula)p_110775_1_);
    }
}