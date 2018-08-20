package com.wildmobsmod.entity.monster.seascorpion;

import org.lwjgl.opengl.GL11;

import com.wildmobsmod.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderSeaScorpion extends RenderLiving
{
	
    private static final ResourceLocation seaScorpionTextures = new ResourceLocation(Strings.MODID + ":textures/entity/sea_scorpion.png");

    public RenderSeaScorpion(ModelBase p_i1253_1_, float p_i1253_2_)
    {
        super(p_i1253_1_, p_i1253_2_);
    }
    
    public void doRender(EntitySeaScorpion p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        super.doRender((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
    
    protected void preRenderCallback(EntitySeaScorpion p_77041_1_, float p_77041_2_)
    {
    	int i = p_77041_1_.getSize() + 1;
    	if (p_77041_1_.isChild() == false)
    	{
    		GL11.glScalef(0.25F + (i * 0.25F), 0.25F + (i * 0.25F), 0.25F + (i * 0.25F));
    	}
    	else
    	{
    		GL11.glScalef((0.25F + (i * 0.25F)) / 2, (0.25F + (i * 0.25F)) / 2, (0.25F + (i * 0.25F)) / 2);
    	}
        this.shadowSize = 0.15F + (i * 0.15F);
    }
    
    protected void rotateCorpse(EntitySeaScorpion p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
    {
        float f1 = p_77043_1_.prevSeaScorpionPitch + (p_77043_1_.seaScorpionPitch - p_77043_1_.prevSeaScorpionPitch) * p_77043_4_;
        
        GL11.glRotatef(180.0F - p_77043_3_, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(f1, 1.0F, 0.0F, 0.0F);
        
        if (p_77043_1_.deathTime > 0)
        {
            float f3 = ((float)p_77043_1_.deathTime + p_77043_4_ - 1.0F) / 20.0F * 1.6F;
            f3 = MathHelper.sqrt_float(f3);

            if (f3 > 1.0F)
            {
                f3 = 1.0F;
            }

            GL11.glRotatef(f3 * this.getDeathMaxRotation(p_77043_1_), 0.0F, 0.0F, 1.0F);
        }
        else
        {
            String s = EnumChatFormatting.getTextWithoutFormattingCodes(p_77043_1_.getCommandSenderName());

            if (s.equals("Dinnerbone") || s.equals("Grumm"))
            {
                GL11.glTranslatef(0.0F, p_77043_1_.height + 0.1F, 0.0F);
                GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
            }
        }
    }
    
    public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((EntitySeaScorpion)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
    
    protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
    {
        this.rotateCorpse((EntitySeaScorpion)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
    }
    
    public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((EntitySeaScorpion)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
    
    protected ResourceLocation getEntityTexture(EntitySeaScorpion p_110775_1_)
    {
        return seaScorpionTextures;
    }

    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((EntitySeaScorpion)p_77041_1_, p_77041_2_);
    }
    
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntitySeaScorpion)p_110775_1_);
    }
    
    public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((EntitySeaScorpion)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}