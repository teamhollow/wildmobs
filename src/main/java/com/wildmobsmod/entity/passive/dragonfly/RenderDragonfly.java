package com.wildmobsmod.entity.passive.dragonfly;

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
public class RenderDragonfly extends RenderLiving{

	private static final ResourceLocation blueTextures = new ResourceLocation(Strings.MODID + ":textures/entity/dragonfly/dragonfly_blue.png");
	private static final ResourceLocation cyanTextures = new ResourceLocation(Strings.MODID + ":textures/entity/dragonfly/dragonfly_cyan.png");
	private static final ResourceLocation greenTextures = new ResourceLocation(Strings.MODID + ":textures/entity/dragonfly/dragonfly_green.png");
	private static final ResourceLocation orangeTextures = new ResourceLocation(Strings.MODID + ":textures/entity/dragonfly/dragonfly_orange.png");
	private static final ResourceLocation redTextures = new ResourceLocation(Strings.MODID + ":textures/entity/dragonfly/dragonfly_red.png");
	private static final ResourceLocation yellowTextures = new ResourceLocation(Strings.MODID + ":textures/entity/dragonfly/dragonfly_yellow.png");

    public RenderDragonfly(ModelBase p_i1265_1_, float p_i1265_2_)
    {
        super(p_i1265_1_, p_i1265_2_);
        this.shadowSize = 0.2F;
    }

    
	protected ResourceLocation getEntityTexture(EntityDragonfly entity) {
	       int type = entity.getDataWatcher().getWatchableObjectByte(20);
	       switch(entity.getDataWatcher().getWatchableObjectByte(20)) {
	       case 0: return blueTextures;
	       case 1: return cyanTextures;
	       case 2: return greenTextures;
	       case 3: return orangeTextures;
	       case 4: return redTextures;
	       case 5: return yellowTextures;
	       default: return blueTextures;
	       }
	}
	

    @Override
	protected ResourceLocation getEntityTexture(Entity entity){
		return this.getEntityTexture((EntityDragonfly)entity);
	}
}