package com.wildmobsmod.entity.monster.mired;

import org.lwjgl.opengl.GL11;

import com.wildmobsmod.entity.monster.dreath.EntityDreath;
import com.wildmobsmod.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderMired extends RenderLiving{

	private static final ResourceLocation miredTextures = new ResourceLocation(Strings.MODID + ":textures/entity/dreath/mired.png");

	public RenderMired(ModelBase p_i1253_1_, float p_i1253_2_)
	{
		super(p_i1253_1_, p_i1253_2_);
	}
	protected ResourceLocation getEntityTexture(EntityMired p_110775_1_)
	{
		return miredTextures;
	}
	
    protected void renderEquippedItems(EntityMired p_77029_1_, float p_77029_2_)
    {
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        super.renderEquippedItems(p_77029_1_, p_77029_2_);
        ItemStack itemstack = p_77029_1_.getHeldItem();

        if (itemstack != null)
        {
        	GL11.glPushMatrix();
        	float f1;

        	GL11.glTranslatef(0.2F, 1.3F, 0.0F);

        	if (itemstack.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType()))
        	{
        		f1 = 0.5F;
        		GL11.glTranslatef(0.0F, 0.0F, -0.3125F);
        		f1 *= 0.75F;
        		GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
        		GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        		GL11.glScalef(f1, -f1, f1);
        	}
        	
        	f1 = 0.375F;
        	
        	GL11.glScalef(f1, f1, f1);
        	
        	GL11.glRotatef(194.0F, 20.0F, -4.0F, -10.0F);

        	this.renderManager.itemRenderer.renderItem(p_77029_1_, itemstack, 0);

            if (itemstack.getItem().requiresMultipleRenderPasses())
            {
                this.renderManager.itemRenderer.renderItem(p_77029_1_, itemstack, 1);
            }

            GL11.glPopMatrix();
        }
    }

    protected void renderEquippedItems(EntityLivingBase p_77029_1_, float p_77029_2_)
    {
        this.renderEquippedItems((EntityMired)p_77029_1_, p_77029_2_);
    }
    
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return this.getEntityTexture((EntityMired)p_110775_1_);
	}
}