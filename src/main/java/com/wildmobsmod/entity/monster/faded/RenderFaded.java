package com.wildmobsmod.entity.monster.faded;

import org.lwjgl.opengl.GL11;

import com.wildmobsmod.entity.monster.tarantula.EntityTarantula;
import com.wildmobsmod.lib.Strings;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;

public class RenderFaded extends RenderZombie
{
    private static final ResourceLocation fadedTextures = new ResourceLocation(Strings.MODID + ":textures/entity/zombie/faded.png");
    
    public RenderFaded()
    {
        super();
    }

    protected void preRenderCallback(EntityZombie p_77041_1_, float p_77041_2_)
    {
        GL11.glScalef(0.75F, 0.75F, 0.75F);
    }

    protected ResourceLocation getEntityTexture(EntityZombie p_110775_1_)
    {
        return fadedTextures;
    }
    
    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((EntityZombie)p_77041_1_, p_77041_2_);
    }
}