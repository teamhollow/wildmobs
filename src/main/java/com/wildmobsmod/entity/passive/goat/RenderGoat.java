package com.wildmobsmod.entity.passive.goat;

import org.lwjgl.opengl.GL11;

import com.wildmobsmod.entity.passive.deer.EntityDeer;
import com.wildmobsmod.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderGoat extends RenderLiving{

	private static final ResourceLocation whiteTextures = new ResourceLocation(Strings.MODID + ":textures/entity/goat/goat_white.png");
	private static final ResourceLocation brownTextures = new ResourceLocation(Strings.MODID + ":textures/entity/goat/goat_brown.png");
	private static final ResourceLocation grayTextures = new ResourceLocation(Strings.MODID + ":textures/entity/goat/goat_gray.png");
	private static final ResourceLocation splotchedTextures = new ResourceLocation(Strings.MODID + ":textures/entity/goat/goat_splotched.png");

    public RenderGoat(ModelBase p_i1265_1_, float p_i1265_3_)
    {
        super(p_i1265_1_, p_i1265_3_);
        this.shadowSize = 0.4F;
    }
    
	protected ResourceLocation getEntityTexture(EntityGoat entity) {
	       int type = entity.getDataWatcher().getWatchableObjectByte(20);
	       switch(entity.getDataWatcher().getWatchableObjectByte(20)) {
	       case 0: return whiteTextures;
	       case 1: return brownTextures;
	       case 2: return grayTextures;
	       case 3: return splotchedTextures;
	       default: return whiteTextures;
	       }
	}

    @Override
	protected ResourceLocation getEntityTexture(Entity entity){
		return this.getEntityTexture((EntityGoat)entity);
	}
}