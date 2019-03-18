package com.wildmobsmod.entity.passive.deer;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelDeerSaddle extends ModelBase
{
	// fields
	ModelRenderer body;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer head;
	ModelRenderer neck1;
	ModelRenderer neck2;
	ModelRenderer nose;
	ModelRenderer ear1;
	ModelRenderer ear2;
	ModelRenderer antler1;
	ModelRenderer antler2;
	ModelRenderer elkantler1;
	ModelRenderer elkantler2;

	protected static void convertToChild(ModelRenderer parent, ModelRenderer child)
	{
		// move child rotation point to be relative to parent
		child.rotationPointX -= parent.rotationPointX;
		child.rotationPointY -= parent.rotationPointY;
		child.rotationPointZ -= parent.rotationPointZ;
		// make rotations relative to parent
		child.rotateAngleX -= parent.rotateAngleX;
		child.rotateAngleY -= parent.rotateAngleY;
		child.rotateAngleZ -= parent.rotateAngleZ;
		// create relationship
		parent.addChild(child);
	}

	public ModelDeerSaddle()
	{
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this, 0, 33);
		body.addBox(-4.5F, -5F, 0F, 9, 10, 20);
		body.setRotationPoint(0F, 9F, -10F);
		body.setTextureSize(64, 64);
		setRotation(body, 0F, 0F, 0F);
		leg1 = new ModelRenderer(this, 44, 0);
		leg1.addBox(-2F, 0F, -2F, 4, 16, 4);
		leg1.setRotationPoint(-3F, 8F, 7F);
		leg1.setTextureSize(64, 64);
		setRotation(leg1, 0F, 0F, 0F);
		leg2 = new ModelRenderer(this, 44, 0);
		leg2.addBox(-2F, 0F, -2F, 4, 16, 4);
		leg2.setRotationPoint(3F, 8F, 7F);
		leg2.setTextureSize(64, 64);
		setRotation(leg2, 0F, 0F, 0F);
		leg3 = new ModelRenderer(this, 44, 0);
		leg3.addBox(-2F, 0F, -2F, 4, 16, 4);
		leg3.setRotationPoint(-3F, 8F, -7F);
		leg3.setTextureSize(64, 64);
		setRotation(leg3, 0F, 0F, 0F);
		leg4 = new ModelRenderer(this, 44, 0);
		leg4.addBox(-2F, 0F, -2F, 4, 16, 4);
		leg4.setRotationPoint(3F, 8F, -7F);
		leg4.setTextureSize(64, 64);
		setRotation(leg4, 0F, 0F, 0F);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-3F, -11F, -9F, 6, 6, 7);
		head.setRotationPoint(0F, 8F, -7.5F);
		head.setTextureSize(64, 64);
		setRotation(head, 0F, 0F, 0F);
		neck1 = new ModelRenderer(this, 20, 13);
		neck1.addBox(-2.5F, -8.5F, -3F, 5, 9, 7);
		neck1.setRotationPoint(0F, 7F, -7.5F);
		neck1.setTextureSize(64, 64);
		setRotation(neck1, 0.6981317F, 0F, 0F);
		convertToChild(head, neck1);
		neck2 = new ModelRenderer(this, 45, 21);
		neck2.addBox(-1.5F, -0.5F, -3F, 3, 8, 3);
		neck2.setRotationPoint(0F, 1.8F, -12F);
		neck2.setTextureSize(64, 64);
		convertToChild(head, neck2);
		setRotation(neck2, 0.3141593F, 0F, 0F);
		nose = new ModelRenderer(this, 27, 0);
		nose.addBox(-2F, -1.5F, -4F, 4, 4, 4);
		nose.setRotationPoint(0F, 0F, -16.5F);
		nose.setTextureSize(64, 64);
		setRotation(nose, 0F, 0F, 0F);
		convertToChild(head, nose);
		ear1 = new ModelRenderer(this, 1, 27);
		ear1.addBox(-1.5F, -5F, -0.5F, 3, 5, 1);
		ear1.setRotationPoint(-2F, -2F, -11.5F);
		ear1.setTextureSize(64, 64);
		setRotation(ear1, 0F, -0.1745329F, -0.7853982F);
		convertToChild(head, ear1);
		ear2 = new ModelRenderer(this, 1, 27);
		ear2.addBox(-1.5F, -5F, -0.5F, 3, 5, 1);
		ear2.setRotationPoint(2F, -2F, -11.5F);
		ear2.setTextureSize(64, 64);
		setRotation(ear2, 0F, 0.1745329F, 0.7853982F);
		convertToChild(head, ear2);
		antler1 = new ModelRenderer(this, 0, 4);
		antler1.addBox(0F, -12F, -3F, 0, 12, 10);
		antler1.setRotationPoint(-2F, -3F, -13.5F);
		antler1.setTextureSize(64, 64);
		setRotation(antler1, 0F, 0.2617994F, -0.3490659F);
		convertToChild(head, antler1);
		antler2 = new ModelRenderer(this, 0, 4);
		antler2.addBox(0F, -12F, -3F, 0, 12, 10);
		antler2.setRotationPoint(2F, -3F, -13.5F);
		antler2.setTextureSize(64, 64);
		setRotation(antler2, 0F, -0.2617994F, 0.3490659F);
		convertToChild(head, antler2);
		elkantler1 = new ModelRenderer(this, 41, 21);
		elkantler1.addBox(0F, -9F, -4F, 0, 11, 17);
		elkantler1.setRotationPoint(-2F, -3F, -13.5F);
		elkantler1.setTextureSize(64, 64);
		setRotation(elkantler1, 0F, -0.1745329F, -0.3490659F);
		convertToChild(head, elkantler1);
		elkantler2 = new ModelRenderer(this, 41, 21);
		elkantler2.addBox(0F, -9F, -4F, 0, 11, 17);
		elkantler2.setRotationPoint(2F, -3F, -13.5F);
		elkantler2.setTextureSize(64, 64);
		setRotation(elkantler2, 0F, 0.1745329F, 0.3490659F);
		convertToChild(head, elkantler2);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		float f6 = 2.0F;
		GL11.glPushMatrix();
		head.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glScalef(2.3F / f6, 2.3F / f6, 2.3F / f6);
		GL11.glTranslatef(0.0F, -0.08F, 0.0F);
		body.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		GL11.glPopMatrix();
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		float f6 = (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.head.rotateAngleY = f3 / (360F / (float) Math.PI);
		this.head.rotateAngleX += 0.1F;
		this.leg1.rotateAngleX = MathHelper.cos(f * 0.666F) * 1.4F * f1;
		this.leg2.rotateAngleX = MathHelper.cos(f * 0.666F + (float) Math.PI) * 1.4F * f1;
		this.leg3.rotateAngleX = MathHelper.cos(f * 0.666F + (float) Math.PI) * 1.4F * f1;
		this.leg4.rotateAngleX = MathHelper.cos(f * 0.666F) * 1.4F * f1;
	}
}
