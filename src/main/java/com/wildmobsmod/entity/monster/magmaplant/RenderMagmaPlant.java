package com.wildmobsmod.entity.monster.magmaplant;

import org.lwjgl.opengl.GL11;

import com.wildmobsmod.entity.monster.dreath.EntityDreath;
import com.wildmobsmod.entity.passive.deer.EntityDeer;
import com.wildmobsmod.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderMagmaPlant extends RenderLiving{
	
    private static final ResourceLocation stage0Textures = new ResourceLocation(Strings.MODID + ":textures/entity/magmaplant/magma_plant_stage_0.png");
    private static final ResourceLocation stage1Textures = new ResourceLocation(Strings.MODID + ":textures/entity/magmaplant/magma_plant_stage_1.png");
    private static final ResourceLocation stage2Textures = new ResourceLocation(Strings.MODID + ":textures/entity/magmaplant/magma_plant_stage_2.png");
    private static final ResourceLocation stage3Textures = new ResourceLocation(Strings.MODID + ":textures/entity/magmaplant/magma_plant_stage_3.png");

    public RenderMagmaPlant(ModelBase p_i1253_1_, float p_i1253_2_)
    {
    	super(p_i1253_1_, p_i1253_2_);
    }
    
    protected void preRenderCallback(EntityMagmaPlant p_77041_1_, float p_77041_2_)
    {
        GL11.glScalef(0.8F, 0.8F, 0.8F);
    }

    protected ResourceLocation getEntityTexture(EntityMagmaPlant p_110775_1_)
    {
	       int type = p_110775_1_.getDataWatcher().getWatchableObjectByte(19);
	       switch(p_110775_1_.getDataWatcher().getWatchableObjectByte(19)) {
	       case 0: return stage0Textures;
	       case 1: return stage1Textures;
	       case 2: return stage2Textures;
	       case 3: return stage3Textures;
	       default: return stage3Textures;
	       }
    }
    
    protected ResourceLocation getEntityTexture(EntityLiving p_110775_1_)
    {
        return this.getEntityTexture((EntityMagmaPlant)p_110775_1_);
    }
    
    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((EntityMagmaPlant)p_77041_1_, p_77041_2_);
    }
    
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityMagmaPlant)p_110775_1_);
    }
}