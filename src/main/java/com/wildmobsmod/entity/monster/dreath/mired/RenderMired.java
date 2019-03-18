package com.wildmobsmod.entity.monster.dreath.mired;

import org.lwjgl.opengl.GL11;

import com.wildmobsmod.main.WildMobsMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderMired extends RenderLiving
{

	private static final ResourceLocation miredTextures = new ResourceLocation(WildMobsMod.MODID + ":textures/entity/dreath/mired.png");

	public RenderMired(ModelBase model, float shadowSize)
	{
		super(model, shadowSize);
	}

	protected ResourceLocation getEntityTexture(EntityMired mired)
	{
		return miredTextures;
	}

	protected void renderEquippedItems(EntityMired mired, float p_77029_2_)
	{
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		super.renderEquippedItems(mired, p_77029_2_);
		ItemStack itemstack = mired.getHeldItem();

		if(itemstack != null)
		{
			GL11.glPushMatrix();
			float f1;

			GL11.glTranslatef(0.2F, 1.3F, 0.0F);

			if(itemstack.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType()))
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

			this.renderManager.itemRenderer.renderItem(mired, itemstack, 0);

			if(itemstack.getItem().requiresMultipleRenderPasses())
			{
				this.renderManager.itemRenderer.renderItem(mired, itemstack, 1);
			}

			GL11.glPopMatrix();
		}
	}

	protected void renderEquippedItems(EntityLivingBase living, float partialTick)
	{
		this.renderEquippedItems((EntityMired) living, partialTick);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityMired) entity);
	}
}
