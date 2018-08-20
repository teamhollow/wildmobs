package com.wildmobsmod.entity.monster.skeletonwolf;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import com.wildmobsmod.entity.monster.skeleton.EntityWMSkeleton;
import com.wildmobsmod.lib.Strings;

@SideOnly(Side.CLIENT)
public class RenderSkeletonWolf extends RenderLiving
{
    private static final ResourceLocation skeletonWolfTextures = new ResourceLocation(Strings.MODID + ":textures/entity/wolf/wolf_skeleton.png");
    private static final ResourceLocation witherSkeletonWolfTextures = new ResourceLocation(Strings.MODID + ":textures/entity/wolf/wither_wolf_skeleton.png");
    private static final String __OBFID = "CL_00001036";

    public RenderSkeletonWolf(ModelBase p_i1269_1_, float p_i1269_3_)
    {
        super(p_i1269_1_, p_i1269_3_);
		this.shadowSize = 0.5F;
    }

    protected void preRenderCallback(EntitySkeletonWolf p_77041_1_, float p_77041_2_)
    {
        if (p_77041_1_.getSkeletonType() == 1)
        {
            GL11.glScalef(1.2F, 1.2F, 1.2F);
        }
    }

    protected void func_82422_c()
    {
        GL11.glTranslatef(0.09375F, 0.1875F, 0.0F);
    }

    protected ResourceLocation getEntityTexture(EntitySkeletonWolf p_110775_1_)
    {
        return p_110775_1_.getSkeletonType() == 1 ? witherSkeletonWolfTextures : skeletonWolfTextures;
    }
    
    protected ResourceLocation getEntityTexture(EntityLiving p_110775_1_)
    {
        return this.getEntityTexture((EntitySkeletonWolf)p_110775_1_);
    }

    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((EntitySkeletonWolf)p_77041_1_, p_77041_2_);
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntitySkeletonWolf)p_110775_1_);
    }
}