package com.wildmobsmod.entity.passive.mouse;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelMouse extends ModelBase
{
	// fields
	ModelRenderer head;
	ModelRenderer ear1;
	ModelRenderer ear2;
	ModelRenderer body;
	ModelRenderer tail;
	ModelRenderer foot1;
	ModelRenderer foot2;
	ModelRenderer foot3;
	ModelRenderer foot4;

	protected void convertToChild(ModelRenderer parent, ModelRenderer child)
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

	public ModelMouse()
	{
		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-1.5F, -1.5F, -3.5F, 3, 3, 4);
		head.setRotationPoint(0F, 21F, -3F);
		head.setTextureSize(64, 32);
		setRotation(head, 0F, 0F, 0F);
		ear1 = new ModelRenderer(this, 14, 0);
		ear1.addBox(-1F, -2F, 0F, 2, 2, 0);
		ear1.setRotationPoint(-1.5F, 20.2F, -4F);
		ear1.setTextureSize(64, 32);
		setRotation(ear1, -0.1745329F, 0F, 0F);
		convertToChild(head, ear1);
		ear2 = new ModelRenderer(this, 14, 0);
		ear2.addBox(-1F, -2F, 0F, 2, 2, 0);
		ear2.setRotationPoint(1.5F, 20.2F, -4F);
		ear2.setTextureSize(64, 32);
		setRotation(ear2, -0.1745329F, 0F, 0F);
		convertToChild(head, ear2);
		body = new ModelRenderer(this, 0, 7);
		body.addBox(-2.5F, -1.5F, 0F, 5, 4, 7);
		body.setRotationPoint(0F, 20.5F, -3F);
		body.setTextureSize(64, 32);
		setRotation(body, 0F, 0F, 0F);
		tail = new ModelRenderer(this, 0, 18);
		tail.addBox(-0.5F, 0F, -0.5F, 1, 9, 1);
		tail.setRotationPoint(0F, 21F, 3.5F);
		tail.setTextureSize(64, 32);
		setRotation(tail, 1.343904F, 0F, 0F);
		foot1 = new ModelRenderer(this, 19, 0);
		foot1.addBox(-0.5F, 0F, -2F, 1, 1, 3);
		foot1.setRotationPoint(-1.5F, 23F, 2.5F);
		foot1.setTextureSize(64, 32);
		setRotation(foot1, 0F, 0F, 0F);
		foot2 = new ModelRenderer(this, 19, 0);
		foot2.addBox(-0.5F, 0F, -2F, 1, 1, 3);
		foot2.setRotationPoint(1.5F, 23F, 2.5F);
		foot2.setTextureSize(64, 32);
		setRotation(foot2, 0F, 0F, 0F);
		foot3 = new ModelRenderer(this, 19, 4);
		foot3.addBox(-0.5F, 0F, -1.5F, 1, 1, 2);
		foot3.setRotationPoint(-1.2F, 23F, -2F);
		foot3.setTextureSize(64, 32);
		setRotation(foot3, 0F, 0F, 0F);
		foot4 = new ModelRenderer(this, 19, 4);
		foot4.addBox(-0.5F, 0F, -1.5F, 1, 1, 2);
		foot4.setRotationPoint(1.2F, 23F, -2F);
		foot4.setTextureSize(64, 32);
		setRotation(foot4, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		float f6 = 2.0F;
		GL11.glPushMatrix();
		GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
		GL11.glTranslatef(0.0F, 8.0F * f5, 0.0F);
		head.render(f5);
		body.render(f5);
		tail.render(f5);
		foot1.render(f5);
		foot2.render(f5);
		foot3.render(f5);
		foot4.render(f5);
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
		EntityMouse entitymouse = (EntityMouse) entity;
		float f6 = (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX += 0.25F;
		this.foot1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 0.6F * f1;
		this.foot2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 0.6F * f1;
		this.foot3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 0.6F * f1;
		this.foot4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 0.6F * f1;
		this.tail.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.6F * f1;
	}

}
