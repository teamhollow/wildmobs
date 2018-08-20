package com.wildmobsmod.entity.monster.zomgus;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelZomgus extends ModelBiped
{
	//fields
    ModelRenderer mushroom1;
    ModelRenderer mushroom2;
    ModelRenderer mushroom3;
    ModelRenderer mushroom4;
    ModelRenderer mushroom5;
    ModelRenderer mushroom6;
    
    private static final String __OBFID = "CL_00000865";

    protected void convertToChild(ModelRenderer parParent, ModelRenderer parChild)
    {
      // move child rotation point to be relative to parent
      parChild.rotationPointX -= parParent.rotationPointX;
      parChild.rotationPointY -= parParent.rotationPointY;
      parChild.rotationPointZ -= parParent.rotationPointZ;
      // make rotations relative to parent
      parChild.rotateAngleX -= parParent.rotateAngleX;
      parChild.rotateAngleY -= parParent.rotateAngleY;
      parChild.rotateAngleZ -= parParent.rotateAngleZ;
      // create relationship
      parParent.addChild(parChild);
    }
    
    public ModelZomgus()
    {
        this(0.0F, 0.0F, false);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
    }

    public ModelZomgus(float p_i1165_1_, float p_i1165_2_, boolean p_i1165_3_)
    {
        super(p_i1165_1_, 0.0F, 64, p_i1165_3_ ? 32 : 64);

            this.mushroom1 = new ModelRenderer(this, 25, 0);
            this.mushroom1.addBox(-2.5F, -5F, 0F, 5, 5, 0);
            this.mushroom1.setRotationPoint(-1F, -8F, 1F);
            this.setRotation(mushroom1, 0F, 0.7853982F, 0F);
            this.convertToChild(bipedHead, mushroom1);
            this.mushroom2 = new ModelRenderer(this, 25, 0);
            this.mushroom2.addBox(-2.5F, -5F, 0F, 5, 5, 0);
            this.mushroom2.setRotationPoint(-1F, -8F, 1F);;
            this.setRotation(mushroom2, 0F, -0.7853982F, 0F);
            this.convertToChild(bipedHead, mushroom2);
            this.mushroom3 = new ModelRenderer(this, 25, 0);
            this.mushroom3.addBox(-2.5F, -4F, 0F, 5, 4, 0);
            this.mushroom3.setRotationPoint(-6F, 6F, -2F);
            this.setRotation(mushroom3, 1.570796F, 0F, -0.7853982F);
            this.convertToChild(bipedRightArm, mushroom3);
            this.mushroom4 = new ModelRenderer(this, 25, 0);
            this.mushroom4.addBox(-2.5F, -4F, 0F, 5, 4, 0);
            this.mushroom4.setRotationPoint(-6F, 6F, -2F);
            this.setRotation(mushroom4, 1.570796F, 0F, 0.7853982F);
            this.convertToChild(bipedRightArm, mushroom4);
            this.mushroom5 = new ModelRenderer(this, 36, 0);
            this.mushroom5.addBox(-1.5F, -3F, 0F, 3, 3, 0);
            this.mushroom5.setRotationPoint(6F, 9F, -2F);
            this.setRotation(mushroom5, 1.570796F, 0F, -0.7853982F);
            this.convertToChild(bipedLeftArm, mushroom5);
            this.mushroom6 = new ModelRenderer(this, 36, 0);
            this.mushroom6.addBox(-1.5F, -3F, 0F, 3, 3, 0);
            this.mushroom6.setRotationPoint(6F, 9F, -2F);
            this.setRotation(mushroom6, 1.570796F, 0F, 0.7853982F);
            this.convertToChild(bipedLeftArm, mushroom6);
    }

    public int func_82897_a()
    {
        return 10;
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
        super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
        float f6 = MathHelper.sin(this.onGround * (float)Math.PI);
        float f7 = MathHelper.sin((1.0F - (1.0F - this.onGround) * (1.0F - this.onGround)) * (float)Math.PI);
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F);
        this.bipedLeftArm.rotateAngleY = 0.1F - f6 * 0.6F;
        this.bipedRightArm.rotateAngleX = -((float)Math.PI / 2F);
        this.bipedLeftArm.rotateAngleX = -((float)Math.PI / 2F);
        this.bipedRightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
        this.bipedLeftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
        this.bipedRightArm.rotateAngleZ += MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
    }
}