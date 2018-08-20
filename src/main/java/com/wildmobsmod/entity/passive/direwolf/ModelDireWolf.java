package com.wildmobsmod.entity.passive.direwolf;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.MathHelper;

public class ModelDireWolf extends ModelBase
{
  //fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer mane;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer tail;
    ModelRenderer ear1;
    ModelRenderer ear2;
    ModelRenderer nose;
  
    //This is really useful for converting the source from a Techne model export
    //which will have absolute rotation points that need to be converted before
    //creating the addChild() relationship
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
    
  public ModelDireWolf()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-3.5F, -3.5F, -6F, 7, 7, 6);
      head.setRotationPoint(0F, 11F, -6F);
      head.setTextureSize(64, 64);
      setRotation(head, 0F, 0F, 0F);
      body = new ModelRenderer(this, 26, 18);
      body.addBox(-3.5F, 0F, -3F, 7, 10, 7);
      body.setRotationPoint(0F, 12F, -0.5F);
      body.setTextureSize(64, 64);
      setRotation(body, 1.422082F, 0F, 0F);
      mane = new ModelRenderer(this, 26, 0);
      mane.addBox(-5F, 0F, -5F, 10, 8, 9);
      mane.setRotationPoint(0F, 11F, -7F);
      mane.setTextureSize(64, 64);
      setRotation(mane, 1.570796F, 0F, 0F);
      leg1 = new ModelRenderer(this, 0, 24);
      leg1.mirror = true;
      leg1.addBox(-1.5F, 0F, -1.5F, 3, 8, 3);
      leg1.setRotationPoint(-2F, 16F, 7F);
      leg1.setTextureSize(64, 64);
      setRotation(leg1, 0F, 0F, 0F);
      leg2 = new ModelRenderer(this, 0, 24);
      leg2.addBox(-1.5F, 0F, -1.5F, 3, 8, 3);
      leg2.setRotationPoint(2F, 16F, 7F);
      leg2.setTextureSize(64, 64);
      setRotation(leg2, 0F, 0F, 0F);
      leg3 = new ModelRenderer(this, 0, 24);
      leg3.mirror = true;
      leg3.addBox(-1.5F, 0F, -1.5F, 3, 9, 3);
      leg3.setRotationPoint(-2.5F, 16F, -4.5F);
      leg3.setTextureSize(64, 64);
      setRotation(leg3, 0F, 0F, 0F);
      leg4 = new ModelRenderer(this, 0, 24);
      leg4.addBox(-1.5F, 0F, -1.5F, 3, 9, 3);
      leg4.setRotationPoint(2.5F, 16F, -4.5F);
      leg4.setTextureSize(64, 64);
      setRotation(leg4, 0F, 0F, 0F);
      tail = new ModelRenderer(this, 13, 24);
      tail.addBox(-1.5F, 0F, -1.5F, 3, 9, 3);
      tail.setRotationPoint(0F, 11F, 8.7F);
      tail.setTextureSize(64, 64);
      setRotation(tail, 0.9069976F, 0F, 0F);
      ear1 = new ModelRenderer(this, 19, 14);
      ear1.addBox(0F, 0F, 0F, 2, 3, 1);
      ear1.setRotationPoint(-3F, 5.5F, -9F);
      ear1.setTextureSize(64, 64);
      setRotation(ear1, 0F, 0F, 0F);
      convertToChild(head, ear1);
      ear2 = new ModelRenderer(this, 19, 14);
      ear2.addBox(0F, 0F, 0F, 2, 3, 1);
      ear2.setRotationPoint(1F, 5.5F, -9F);
      ear2.setTextureSize(64, 64);
      setRotation(ear2, 0F, 0F, 0F);
      convertToChild(head, ear2);
      nose = new ModelRenderer(this, 0, 14);
      nose.addBox(0F, 0F, 0F, 4, 4, 5);
      nose.setRotationPoint(-2F, 10.5F, -15.5F);
      nose.setTextureSize(64, 64);
      setRotation(nose, 0F, 0F, 0F);
      convertToChild(head, nose);
  }
  
  public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
  {
      super.render(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
      this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
    if (this.isChild)
    {
    	float f6 = 2.0F;
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, 6.7F * p_78088_7_, 2.5F * p_78088_7_);
    	head.render(p_78088_7_);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
        GL11.glTranslatef(0.0F, 24.0F * p_78088_7_, 0.0F);
    	body.render(p_78088_7_);
    	mane.render(p_78088_7_);
    	leg1.render(p_78088_7_);
    	leg2.render(p_78088_7_);
    	leg3.render(p_78088_7_);
    	leg4.render(p_78088_7_);
    	tail.render(p_78088_7_);
        GL11.glPopMatrix();
    }
    else
    {
    	head.render(p_78088_7_);
    	body.render(p_78088_7_);
    	mane.render(p_78088_7_);
    	leg1.render(p_78088_7_);
    	leg2.render(p_78088_7_);
    	leg3.render(p_78088_7_);
    	leg4.render(p_78088_7_);
    	tail.render(p_78088_7_);
    }
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_)
  {
      EntityDireWolf entitydirewolf = (EntityDireWolf)p_78086_1_;

      if (entitydirewolf.isAngry())
      {
          this.tail.rotateAngleY = 0.0F;
      }
      else
      {
          this.tail.rotateAngleY = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
      }

      if (entitydirewolf.isSitting())
      {
          this.mane.setRotationPoint(0F, 12F, -7F);
          this.mane.rotateAngleX = ((float)Math.PI * 2F / 5F);
          this.mane.rotateAngleY = 0.0F;
          this.body.setRotationPoint(0F, 14.6F, -1.5F);
          this.body.rotateAngleX = ((float)Math.PI / 4F);
          this.tail.setRotationPoint(0.0F, 20F, 5.5F);
          this.leg1.setRotationPoint(-2F, 22.0F, 3.0F);
          this.leg1.rotateAngleX = ((float)Math.PI * 3F / 2F);
          this.leg2.setRotationPoint(2F, 22.0F, 3.0F);
          this.leg2.rotateAngleX = ((float)Math.PI * 3F / 2F);
          this.leg3.rotateAngleX = 5.811947F;
          this.leg3.setRotationPoint(-2F, 16.0F, -4.0F);
          this.leg4.rotateAngleX = 5.811947F;
          this.leg4.setRotationPoint(2F, 16.0F, -4.0F);
      }
      else
      {
          this.body.setRotationPoint(0F, 12F, -0.5F);
          this.body.rotateAngleX = 1.422082F;
          this.mane.setRotationPoint(0F, 11F, -7F);
          this.mane.rotateAngleX = (1.570796F);
          this.tail.setRotationPoint(0F, 11F, 8.7F);
          this.leg1.setRotationPoint(-2F, 16F, 7F);
          this.leg2.setRotationPoint(2F, 16F, 7F);
          this.leg3.setRotationPoint(-2.5F, 16F, -4.5F);
          this.leg4.setRotationPoint(2.5F, 16F, -4.5F);
          this.leg1.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
          this.leg2.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_78086_3_;
          this.leg3.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_78086_3_;
          this.leg4.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
      }

      this.head.rotateAngleZ = entitydirewolf.getInterestedAngle(p_78086_4_) + entitydirewolf.getShakeAngle(p_78086_4_, 0.0F);
      this.mane.rotateAngleZ = entitydirewolf.getShakeAngle(p_78086_4_, -0.08F);
      this.body.rotateAngleZ = entitydirewolf.getShakeAngle(p_78086_4_, -0.16F);
      this.tail.rotateAngleZ = entitydirewolf.getShakeAngle(p_78086_4_, -0.2F);
  }
  
  public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
  {
      super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
      this.head.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
      this.head.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
      this.tail.rotateAngleX = p_78087_3_ * 0.6F + 0.3F;
  }

}