package com.wildmobsmod.entity.passive.deer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.wildmobsmod.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDeer extends RenderLiving{

	private static final ResourceLocation whitetailTextures = new ResourceLocation(Strings.MODID + ":textures/entity/deer/deer_whitetail.png");
	private static final ResourceLocation reindeerTextures = new ResourceLocation(Strings.MODID + ":textures/entity/deer/deer_reindeer.png");
	private static final ResourceLocation rudolphTextures = new ResourceLocation(Strings.MODID + ":textures/entity/deer/deer_rudolph.png");
	private static final ResourceLocation elkTextures = new ResourceLocation(Strings.MODID + ":textures/entity/deer/deer_elk.png");
	private static final ResourceLocation saddledTextures = new ResourceLocation(Strings.MODID + ":textures/entity/deer/deer_saddle.png");

    public RenderDeer(ModelBase p_i1265_1_, ModelBase p_i1265_2_, float p_i1265_3_)
    {
        super(p_i1265_1_, p_i1265_3_);
        this.setRenderPassModel(p_i1265_2_);
        this.shadowSize = 0.7F;
    }
    
    protected void preRenderCallback(EntityDeer p_77041_1_, float p_77041_2_)
    {
        if (p_77041_1_.getSkin() == 2)
        {
            GL11.glScalef(1.1F, 1.1F, 1.1F);
        }
    }

    protected int shouldRenderPass(EntityDeer p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
    	if (p_77032_2_ == 0 && p_77032_1_.getSaddled())
    	{
   			this.bindTexture(saddledTextures); 
    		return 1;
    	}
		return -1;
    }
    
	protected ResourceLocation getEntityTexture(EntityDeer entity) {
	       int type = entity.getDataWatcher().getWatchableObjectByte(20);
	       switch(entity.getDataWatcher().getWatchableObjectByte(20)) {
	       case 0: return whitetailTextures;
	       case 1: {
	            if (entity.hasCustomNameTag() && "Rudolph".equals(entity.getCustomNameTag()))
	            {
                    return rudolphTextures;
	            }
	            else
	            {
                    return reindeerTextures;
	            }
	       }
	       case 2: return elkTextures;
	       default: return whitetailTextures;
	       }
	}
	
    protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        return this.shouldRenderPass((EntityDeer)p_77032_1_, p_77032_2_, p_77032_3_);
    }

    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((EntityDeer)p_77041_1_, p_77041_2_);
    }
    
    @Override
	protected ResourceLocation getEntityTexture(Entity entity){
		return this.getEntityTexture((EntityDeer)entity);
	}
}