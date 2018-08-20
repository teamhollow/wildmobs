package com.wildmobsmod.entity.monster.wizard;

import org.lwjgl.opengl.GL11;

import com.wildmobsmod.entity.passive.bison.EntityBison;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelWizard extends ModelVillager
{
    public boolean field_82900_g;
    private ModelRenderer field_82901_h = (new ModelRenderer(this)).setTextureSize(64, 128);
    private ModelRenderer witchHat;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    /** Records whether the model should be rendered holding an item in the left hand, and if that item is a block. */
    public int heldItemLeft;
    /** Records whether the model should be rendered holding an item in the right hand, and if that item is a block. */
    public int heldItemRight;

    public ModelWizard(float p_i1166_1_)
    {
        super(p_i1166_1_, 0.0F, 64, 128);
        this.witchHat = (new ModelRenderer(this)).setTextureSize(64, 128);
        this.witchHat.setRotationPoint(-5.0F, -10.03125F, -5.0F);
        this.witchHat.setTextureOffset(0, 64).addBox(0.0F, 0.0F, 0.0F, 10, 2, 10);
        this.villagerHead.addChild(this.witchHat);
        this.bipedRightArm = (new ModelRenderer(this)).setTextureSize(64, 128);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.setTextureOffset(40, 46).addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4);
        this.bipedLeftArm = (new ModelRenderer(this)).setTextureSize(64, 128);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.setTextureOffset(40, 46).addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4);
        ModelRenderer modelrenderer = (new ModelRenderer(this)).setTextureSize(64, 128);
        modelrenderer.setRotationPoint(1.75F, -4.0F, 2.0F);
        modelrenderer.setTextureOffset(0, 76).addBox(0.0F, 0.0F, 0.0F, 7, 4, 7);
        modelrenderer.rotateAngleX = -0.05235988F;
        modelrenderer.rotateAngleZ = 0.02617994F;
        this.witchHat.addChild(modelrenderer);
        ModelRenderer modelrenderer1 = (new ModelRenderer(this)).setTextureSize(64, 128);
        modelrenderer1.setRotationPoint(1.75F, -4.0F, 2.0F);
        modelrenderer1.setTextureOffset(0, 87).addBox(0.0F, 0.0F, 0.0F, 4, 4, 4);
        modelrenderer1.rotateAngleX = -0.10471976F;
        modelrenderer1.rotateAngleZ = 0.05235988F;
        modelrenderer.addChild(modelrenderer1);
        ModelRenderer modelrenderer2 = (new ModelRenderer(this)).setTextureSize(64, 128);
        modelrenderer2.setRotationPoint(1.75F, -2.0F, 2.0F);
        modelrenderer2.setTextureOffset(0, 95).addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.25F);
        modelrenderer2.rotateAngleX = -0.20943952F;
        modelrenderer2.rotateAngleZ = 0.10471976F;
        modelrenderer1.addChild(modelrenderer2);
    }
    
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
    {
        EntityWizard entitywizard = (EntityWizard)p_78088_1_;
    	this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
    	this.villagerHead.render(p_78088_7_);
    	this.villagerBody.render(p_78088_7_);
    	this.rightVillagerLeg.render(p_78088_7_);
    	this.leftVillagerLeg.render(p_78088_7_);
        if (entitywizard.getHasArms() == false)
        {
  		    this.villagerArms.render(p_78088_7_);
        }
        else
        {
  		    this.bipedRightArm.render(p_78088_7_);
   	    	this.bipedLeftArm.render(p_78088_7_);
        }
    }
    
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
        super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);	
        this.bipedRightArm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 2.0F * p_78087_2_ * 0.5F;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 2.0F * p_78087_2_ * 0.5F;
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        
        this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F);

        this.bipedRightArm.rotateAngleZ += MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
    }
}