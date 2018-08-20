package com.wildmobsmod.entity.monster.wizard;

import java.util.UUID;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;

import org.lwjgl.opengl.GL11;

import com.mojang.authlib.GameProfile;
import com.wildmobsmod.lib.Strings;

@SideOnly(Side.CLIENT)
public class RenderWizard extends RenderLiving
{
    private static final ResourceLocation wizardTextures = new ResourceLocation(Strings.MODID + ":textures/entity/wizard/wizard.png");
    private static final ResourceLocation voldemortTextures = new ResourceLocation(Strings.MODID + ":textures/entity/wizard/voldemort.png");
    public ModelWizard wizardModel;
    private static final String __OBFID = "CL_00001033";

    public RenderWizard()
    {
        super(new ModelWizard(0.0F), 0.5F);
        this.wizardModel = (ModelWizard)this.mainModel;
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(EntityWizard p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        ItemStack itemstack = p_76986_1_.getHeldItem();
        this.wizardModel.field_82900_g = itemstack != null;
        super.doRender((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected void renderEquippedItems(EntityLiving p_77029_1_, float p_77029_2_)
    {
    	EntityWizard entitywizard = (EntityWizard)p_77029_1_;
        if (entitywizard.getHasArms() == false)
        {
    	}
    	else
    	{
    		GL11.glColor3f(1.0F, 1.0F, 1.0F);
    		super.renderEquippedItems(p_77029_1_, p_77029_2_);
    		ItemStack itemstack = p_77029_1_.getHeldItem();
    		ItemStack itemstack1 = p_77029_1_.func_130225_q(3);
    		Item item;
    		float f1;

    		if (itemstack != null && itemstack.getItem() != null)
    		{
    			item = itemstack.getItem();
    			GL11.glPushMatrix();

    			if (this.mainModel.isChild)
    			{
    				f1 = 0.5F;
    				GL11.glTranslatef(0.0F, 0.625F, 0.0F);
    				GL11.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F);
    				GL11.glScalef(f1, f1, f1);
    			}

    			this.wizardModel.bipedRightArm.postRender(0.0625F);
    			GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);

    			net.minecraftforge.client.IItemRenderer customRenderer = net.minecraftforge.client.MinecraftForgeClient.getItemRenderer(itemstack, net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED);
    			boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED, itemstack, net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D));

    			if (item instanceof ItemBlock && (is3D || RenderBlocks.renderItemIn3d(Block.getBlockFromItem(item).getRenderType())))
    			{
    				f1 = 0.5F;
    				GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
    				f1 *= 0.75F;
    				GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
    				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
    				GL11.glScalef(-f1, -f1, f1);
    			}
    			else if (item == Items.bow)
    			{
    				f1 = 0.625F;
    				GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
    				GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
    				GL11.glScalef(f1, -f1, f1);
    				GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
    				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
    			}
    			else if (item.isFull3D())
    			{
    				f1 = 0.625F;

    				if (item.shouldRotateAroundWhenRendering())
    				{
    					GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    					GL11.glTranslatef(0.0F, -0.125F, 0.0F);
    				}

    				this.func_82422_c();
    				GL11.glScalef(f1, -f1, f1);
    				GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
    				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
    			}
    			else
    			{
    				f1 = 0.375F;
    				GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
    				GL11.glScalef(f1, f1, f1);
    				GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
    				GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
    				GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
    			}

    			float f2;
    			int i;
    			float f5;

    			if (itemstack.getItem().requiresMultipleRenderPasses())
    			{
    				for (i = 0; i < itemstack.getItem().getRenderPasses(itemstack.getItemDamage()); ++i)
    				{
    					int j = itemstack.getItem().getColorFromItemStack(itemstack, i);
    					f5 = (float)(j >> 16 & 255) / 255.0F;
    					f2 = (float)(j >> 8 & 255) / 255.0F;
    					float f3 = (float)(j & 255) / 255.0F;
    					GL11.glColor4f(f5, f2, f3, 1.0F);
    					this.renderManager.itemRenderer.renderItem(p_77029_1_, itemstack, i);
    				}
    			}
    			else
    			{
    				i = itemstack.getItem().getColorFromItemStack(itemstack, 0);
    				float f4 = (float)(i >> 16 & 255) / 255.0F;
    				f5 = (float)(i >> 8 & 255) / 255.0F;
    				f2 = (float)(i & 255) / 255.0F;
    				GL11.glColor4f(f4, f5, f2, 1.0F);
    				this.renderManager.itemRenderer.renderItem(p_77029_1_, itemstack, 0);
    			}

    			GL11.glPopMatrix();
    		}
    	}
    }
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityWizard p_110775_1_)
    {
    	if (p_110775_1_.hasCustomNameTag() && "Voldemort".equals(p_110775_1_.getCustomNameTag()))
    	{
    		return voldemortTextures;
    	}
    	else
    	{
    		return wizardTextures;
    	}
    }

    protected void func_82422_c()
    {
        GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
    }

    protected void preRenderCallback(EntityWizard p_77041_1_, float p_77041_2_)
    {
        float f1 = 0.9375F;
        GL11.glScalef(f1, f1, f1);
    }

    public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((EntityWizard)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((EntityWizard)p_77041_1_, p_77041_2_);
    }

    protected void renderEquippedItems(EntityLivingBase p_77029_1_, float p_77029_2_)
    {
        this.renderEquippedItems((EntityWizard)p_77029_1_, p_77029_2_);
    }

    public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((EntityWizard)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityWizard)p_110775_1_);
    }

    public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((EntityWizard)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}