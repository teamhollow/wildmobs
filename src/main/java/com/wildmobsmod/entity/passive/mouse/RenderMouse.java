package com.wildmobsmod.entity.passive.mouse;

import com.wildmobsmod.entity.passive.deer.EntityDeer;
import com.wildmobsmod.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderMouse extends RenderLiving{

	private static final ResourceLocation darkBrownTextures = new ResourceLocation(Strings.MODID + ":textures/entity/mouse/mouse_dark_brown.png");
	private static final ResourceLocation lightBrownTextures = new ResourceLocation(Strings.MODID + ":textures/entity/mouse/mouse_light_brown.png");
	private static final ResourceLocation grayTextures = new ResourceLocation(Strings.MODID + ":textures/entity/mouse/mouse_gray.png");
	private static final ResourceLocation whiteTextures = new ResourceLocation(Strings.MODID + ":textures/entity/mouse/mouse_white.png");
	private static final ResourceLocation sandTextures = new ResourceLocation(Strings.MODID + ":textures/entity/mouse/mouse_sand.png");

	public RenderMouse(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
        this.shadowSize = 0.2F;
	}

	protected ResourceLocation getEntityTexture(EntityMouse entity) {
	       int type = entity.getDataWatcher().getWatchableObjectByte(20);
	       switch(entity.getDataWatcher().getWatchableObjectByte(20)) {
	       case 0: return darkBrownTextures;
	       case 1: return lightBrownTextures;
	       case 2: return grayTextures;
	       case 3: return whiteTextures;
	       case 4: return sandTextures;
	       default: return darkBrownTextures;
	       }
	}

    @Override
	protected ResourceLocation getEntityTexture(Entity entity){
		return this.getEntityTexture((EntityMouse)entity);
	}
}