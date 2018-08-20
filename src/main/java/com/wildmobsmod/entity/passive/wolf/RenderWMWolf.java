package com.wildmobsmod.entity.passive.wolf;

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
public class RenderWMWolf extends RenderLiving
{
    private static final ResourceLocation wolfTextures = new ResourceLocation("textures/entity/wolf/wolf.png");
    private static final ResourceLocation tamedWolfTextures = new ResourceLocation("textures/entity/wolf/wolf_tame.png");
    private static final ResourceLocation tamedWolfBerneseTextures = new ResourceLocation(Strings.MODID + ":textures/entity/wolf/wolf_tame_bernese.png");
    private static final ResourceLocation tamedWolfBlackTextures = new ResourceLocation(Strings.MODID + ":textures/entity/wolf/wolf_tame_black.png");
    private static final ResourceLocation tamedWolfBrownTextures = new ResourceLocation(Strings.MODID + ":textures/entity/wolf/wolf_tame_brown.png");
    private static final ResourceLocation tamedWolfDalmatianTextures = new ResourceLocation(Strings.MODID + ":textures/entity/wolf/wolf_tame_dalmatian.png");
    private static final ResourceLocation tamedWolfHoundTextures = new ResourceLocation(Strings.MODID + ":textures/entity/wolf/wolf_tame_hound.png");
    private static final ResourceLocation tamedWolfHuskyTextures = new ResourceLocation(Strings.MODID + ":textures/entity/wolf/wolf_tame_husky.png");
    private static final ResourceLocation tamedWolfPointerTextures = new ResourceLocation(Strings.MODID + ":textures/entity/wolf/wolf_tame_pointer.png");
    private static final ResourceLocation tamedWolfShepherdTextures = new ResourceLocation(Strings.MODID + ":textures/entity/wolf/wolf_tame_shepherd.png");
    private static final ResourceLocation tamedWolfShibaTextures = new ResourceLocation(Strings.MODID + ":textures/entity/wolf/wolf_tame_shiba.png");
    private static final ResourceLocation tamedWolfWhiteTextures = new ResourceLocation(Strings.MODID + ":textures/entity/wolf/wolf_tame_white.png");
    private static final ResourceLocation anrgyWolfTextures = new ResourceLocation("textures/entity/wolf/wolf_angry.png");
    private static final ResourceLocation wolfCollarTextures = new ResourceLocation("textures/entity/wolf/wolf_collar.png");
    private static final String __OBFID = "CL_00001036";

    public RenderWMWolf(ModelBase p_i1269_1_, ModelBase p_i1269_2_, float p_i1269_3_)
    {
        super(p_i1269_1_, p_i1269_3_);
        this.setRenderPassModel(p_i1269_2_);
		this.shadowSize = 0.5F;
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityWMWolf p_77044_1_, float p_77044_2_)
    {
        return p_77044_1_.getTailRotation();
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityWMWolf p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        if (p_77032_2_ == 0 && p_77032_1_.getWolfShaking())
        {
            float f1 = p_77032_1_.getBrightness(p_77032_3_) * p_77032_1_.getShadingWhileShaking(p_77032_3_);
            if (p_77032_1_.isTamed() == false)
            {
                this.bindTexture(wolfTextures);
            }
            else if (p_77032_1_.getTameSkin() == 0)
            {
                this.bindTexture(tamedWolfTextures);
            }
            else if (p_77032_1_.getTameSkin() == 1)
            {
                this.bindTexture(tamedWolfBerneseTextures);
            }
            else if (p_77032_1_.getTameSkin() == 2)
            {
                this.bindTexture(tamedWolfBlackTextures);
            }
            else if (p_77032_1_.getTameSkin() == 3)
            {
                this.bindTexture(tamedWolfBrownTextures);
            }
            else if (p_77032_1_.getTameSkin() == 4)
            {
                this.bindTexture(tamedWolfDalmatianTextures);
            }
            else if (p_77032_1_.getTameSkin() == 5)
            {
                this.bindTexture(tamedWolfHoundTextures);
            }
            else if (p_77032_1_.getTameSkin() == 6)
            {
                this.bindTexture(tamedWolfHuskyTextures);
            }
            else if (p_77032_1_.getTameSkin() == 7)
            {
                this.bindTexture(tamedWolfPointerTextures);
            }
            else if (p_77032_1_.getTameSkin() == 8)
            {
                this.bindTexture(tamedWolfShepherdTextures);
            }
            else if (p_77032_1_.getTameSkin() == 9)
            {
                this.bindTexture(tamedWolfShibaTextures);
            }
            else if (p_77032_1_.getTameSkin() == 10)
            {
                this.bindTexture(tamedWolfWhiteTextures);
            }
            GL11.glColor3f(f1, f1, f1);
            return 1;
        }
        else if (p_77032_2_ == 1 && p_77032_1_.isTamed())
        {
            this.bindTexture(wolfCollarTextures);
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
    protected ResourceLocation getEntityTexture(EntityWMWolf p_110775_1_)
    {
        return p_110775_1_.isTamed() && p_110775_1_.getTameSkin() == 0 ? tamedWolfTextures : p_110775_1_.isTamed() && p_110775_1_.getTameSkin() == 1 ? tamedWolfBerneseTextures : p_110775_1_.isTamed() && p_110775_1_.getTameSkin() == 2 ? tamedWolfBlackTextures : p_110775_1_.isTamed() && p_110775_1_.getTameSkin() == 3 ? tamedWolfBrownTextures : p_110775_1_.isTamed() && p_110775_1_.getTameSkin() == 4 ? tamedWolfDalmatianTextures : p_110775_1_.isTamed() && p_110775_1_.getTameSkin() == 5 ? tamedWolfHoundTextures  : p_110775_1_.isTamed() && p_110775_1_.getTameSkin() == 6 ? tamedWolfHuskyTextures : p_110775_1_.isTamed() && p_110775_1_.getTameSkin() == 7 ? tamedWolfPointerTextures : p_110775_1_.isTamed() && p_110775_1_.getTameSkin() == 8 ? tamedWolfShepherdTextures : p_110775_1_.isTamed() && p_110775_1_.getTameSkin() == 9 ? tamedWolfShibaTextures : p_110775_1_.isTamed() && p_110775_1_.getTameSkin() == 10 ? tamedWolfWhiteTextures : (p_110775_1_.isAngry() ? anrgyWolfTextures : wolfTextures);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        return this.shouldRenderPass((EntityWMWolf)p_77032_1_, p_77032_2_, p_77032_3_);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityLivingBase p_77044_1_, float p_77044_2_)
    {
        return this.handleRotationFloat((EntityWMWolf)p_77044_1_, p_77044_2_);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityWMWolf)p_110775_1_);
    }
}