package com.wildmobsmod.entity.passive.cougar;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.wildmobsmod.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCougar extends RenderLiving{

	private static final ResourceLocation textures = new ResourceLocation(Strings.MODID + ":textures/entity/cougar/cougar.png");
	private static final ResourceLocation angryTextures = new ResourceLocation(Strings.MODID + ":textures/entity/cougar/cougar_angry.png");

	public RenderCougar(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
        this.shadowSize = 0.6F;
	}

	protected ResourceLocation getEntityTexture(EntityCougar entity) {
	    return entity.getAttackTarget() != null ? angryTextures : textures;
	}

    @Override
	protected ResourceLocation getEntityTexture(Entity entity){
		return this.getEntityTexture((EntityCougar)entity);
	}
}
