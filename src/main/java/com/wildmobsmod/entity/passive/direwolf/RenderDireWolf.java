package com.wildmobsmod.entity.passive.direwolf;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import com.wildmobsmod.lib.Strings;

@SideOnly(Side.CLIENT)
public class RenderDireWolf extends RenderLiving
{
    private static final ResourceLocation direWolfTextures = new ResourceLocation(Strings.MODID + ":textures/entity/direwolf/direwolf.png");
    private static final ResourceLocation tamedDireWolfTextures = new ResourceLocation(Strings.MODID + ":textures/entity/direwolf/direwolf_tame.png");
    private static final ResourceLocation anrgyDireWolfTextures = new ResourceLocation(Strings.MODID + ":textures/entity/direwolf/direwolf_angry.png");
    private static final ResourceLocation direWolfCollarTextures = new ResourceLocation(Strings.MODID + ":textures/entity/direwolf/direwolf_collar.png");

    public RenderDireWolf(ModelBase p_i1269_1_, ModelBase p_i1269_2_, float p_i1269_3_)
    {
        super(p_i1269_1_, p_i1269_3_);
        this.setRenderPassModel(p_i1269_2_);
        this.shadowSize = 0.6F;
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityDireWolf p_77044_1_, float p_77044_2_)
    {
        return p_77044_1_.getTailRotation();
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityDireWolf p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        if (p_77032_2_ == 0 && p_77032_1_.getWolfShaking())
        {
            float f1 = p_77032_1_.getBrightness(p_77032_3_) * p_77032_1_.getShadingWhileShaking(p_77032_3_);
            this.bindTexture(direWolfTextures);
            GL11.glColor3f(f1, f1, f1);
            return 1;
        }
        if (p_77032_2_ == 1 && p_77032_1_.isTamed())
        {
            this.bindTexture(direWolfCollarTextures);
            int j = p_77032_1_.getCollarColor();
            GL11.glColor3f(EntitySheep.fleeceColorTable[j][0], EntitySheep.fleeceColorTable[j][1], EntitySheep.fleeceColorTable[j][2]);
            return 1;
        }
        else
        {
            return -1;
        }
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityDireWolf p_110775_1_)
    {
        return p_110775_1_.isTamed() ? tamedDireWolfTextures : (p_110775_1_.isAngry() ? anrgyDireWolfTextures : direWolfTextures);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        return this.shouldRenderPass((EntityDireWolf)p_77032_1_, p_77032_2_, p_77032_3_);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityLivingBase p_77044_1_, float p_77044_2_)
    {
        return this.handleRotationFloat((EntityDireWolf)p_77044_1_, p_77044_2_);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityDireWolf)p_110775_1_);
    }
}