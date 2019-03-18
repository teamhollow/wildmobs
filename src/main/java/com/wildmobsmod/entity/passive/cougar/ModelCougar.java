package com.wildmobsmod.entity.passive.cougar;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelCougar extends ModelBase
{
	// fields
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer neck;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer tail;
	ModelRenderer ear1;
	ModelRenderer ear2;
	ModelRenderer nose;

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

	public ModelCougar()
	{
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-3F, -3.6F, -8.75F, 6, 6, 5);
		head.setRotationPoint(0F, 9F, -7.5F);
		head.setTextureSize(64, 64);
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 24, 11);
		body.addBox(-4F, -2F, -3F, 7, 18, 8);
		body.setRotationPoint(0.5F, 11F, -7F);
		body.setTextureSize(64, 64);
		setRotation(body, 1.570796F, 0F, 0F);
		neck = new ModelRenderer(this, 22, 0);
		neck.addBox(-4F, -3F, -4F, 5, 5, 6);
		neck.setRotationPoint(1.5F, 8F, -9F);
		neck.setTextureSize(64, 64);
		setRotation(neck, 1.37881F, 0F, 0F);
		convertToChild(head, neck);
		leg1 = new ModelRenderer(this, 0, 18);
		leg1.addBox(-1F, 0F, -1F, 2, 12, 3);
		leg1.setRotationPoint(-2F, 12F, 5.5F);
		leg1.setTextureSize(64, 64);
		setRotation(leg1, 0F, 0F, 0F);
		leg2 = new ModelRenderer(this, 0, 18);
		leg2.mirror = true;
		leg2.addBox(-1F, 0F, -1F, 2, 12, 3);
		leg2.setRotationPoint(2F, 12F, 5.5F);
		leg2.setTextureSize(64, 64);
		setRotation(leg2, 0F, 0F, 0F);
		leg3 = new ModelRenderer(this, 0, 18);
		leg3.addBox(-1F, 0F, -1F, 2, 12, 3);
		leg3.setRotationPoint(-2F, 12F, -7F);
		leg3.setTextureSize(64, 64);
		setRotation(leg3, 0F, 0F, 0F);
		leg4 = new ModelRenderer(this, 0, 18);
		leg4.mirror = true;
		leg4.addBox(-1F, 0F, -1F, 2, 12, 3);
		leg4.setRotationPoint(2F, 12F, -7F);
		leg4.setTextureSize(64, 64);
		setRotation(leg4, 0F, 0F, 0F);
		tail = new ModelRenderer(this, 10, 18);
		tail.addBox(-1F, 0F, -1F, 2, 12, 2);
		tail.setRotationPoint(0F, 8F, 8F);
		tail.setTextureSize(64, 64);
		setRotation(tail, 0.4574047F, 0F, 0F);
		ear1 = new ModelRenderer(this, 16, 15);
		ear1.addBox(-1F, -1.6F, 0.25F, 2, 2, 1);
		ear1.setRotationPoint(-1.7F, 5F, -13.5F);
		ear1.setTextureSize(64, 64);
		setRotation(ear1, 0F, 0F, 0F);
		convertToChild(head, ear1);
		ear2 = new ModelRenderer(this, 16, 15);
		ear2.addBox(-1F, -1.6F, 0.25F, 2, 2, 1);
		ear2.setRotationPoint(1.7F, 5F, -13.5F);
		ear2.setTextureSize(64, 64);
		setRotation(ear2, 0F, 0F, 0F);
		convertToChild(head, ear2);
		nose = new ModelRenderer(this, 0, 11);
		nose.addBox(-2F, 0.3F, -5.25F, 3, 3, 4);
		nose.setRotationPoint(0.5F, 8F, -13.5F);
		nose.setTextureSize(64, 64);
		setRotation(nose, 0.0349066F, 0F, 0F);
		convertToChild(head, nose);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		if(this.isChild)
		{
			float f6 = 2.0F;
			GL11.glPushMatrix();
			GL11.glScalef(1.3F / f6, 1.3F / f6, 1.3F / f6);
			GL11.glTranslatef(0.0F, 17.0F * f5, 2.0F * f5);
			head.render(f5);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
			GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
			body.render(f5);
			leg1.render(f5);
			leg2.render(f5);
			leg3.render(f5);
			leg4.render(f5);
			tail.render(f5);
			GL11.glPopMatrix();
		}
		else
		{
			head.render(f5);
			body.render(f5);
			leg1.render(f5);
			leg2.render(f5);
			leg3.render(f5);
			leg4.render(f5);
			tail.render(f5);
		}
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
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.head.rotateAngleY = f3 / (360F / (float) Math.PI);
		this.head.rotationPointZ = -8.2F;
		this.tail.rotateAngleZ = MathHelper.cos(f * 0.666F) * 0.5F * f1;
	}

	public void setLivingAnimations(EntityLivingBase living, float f1, float f2, float f3)
	{
		EntityCougar entitycougar = (EntityCougar) living;

		this.body.rotationPointY = 11.0F;
		this.head.rotationPointY = 9.0F;
		this.tail.rotationPointY = 8.0F;
		this.leg1.rotationPointY = 12.0F;
		this.leg2.rotationPointY = 12.0F;
		this.leg3.rotationPointY = 12.0F;
		this.leg4.rotationPointY = 12.0F;

		if(entitycougar.isSneaking())
		{
			this.body.rotationPointY += 3.0F;
			if(this.isChild)
			{
				this.head.rotationPointY += 2.5F;
			}
			else
			{
				this.head.rotationPointY += 5.0F;
			}
			this.tail.rotationPointY += 3.0F;
			this.leg1.rotationPointY += 3.0F;
			this.leg2.rotationPointY += 3.0F;
			this.leg3.rotationPointY += 3.0F;
			this.leg4.rotationPointY += 3.0F;
			this.leg1.rotateAngleX = 0.461799F;
			this.leg2.rotateAngleX = 0.461799F;
			this.leg3.rotateAngleX = -0.461799F;
			this.leg4.rotateAngleX = -0.461799F;
		}
		else
		{
			this.leg1.rotateAngleX = MathHelper.cos(f1 * 0.6662F) * 1.4F * f2;
			this.leg2.rotateAngleX = MathHelper.cos(f1 * 0.6662F + (float) Math.PI) * 1.4F * f2;
			this.leg3.rotateAngleX = MathHelper.cos(f1 * 0.6662F + (float) Math.PI) * 1.4F * f2;
			this.leg4.rotateAngleX = MathHelper.cos(f1 * 0.6662F) * 1.4F * f2;
		}
	}
}
