package com.wildmobsmod.entity.passive.hyena;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

/**
 * Model by DoctorHyena
 * Created using Tabula 4.1.1
 */
public class ModelHyena extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer leg1;
    public ModelRenderer leg4;
    public ModelRenderer leg2;
    public ModelRenderer tail;
    public ModelRenderer leg3;
    public ModelRenderer body;
    public ModelRenderer headChild;
    public ModelRenderer headChild_1;
    public ModelRenderer headChild_2;
    public ModelRenderer headChild_3;
    public ModelRenderer shape14;

    public ModelHyena() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.tail = new ModelRenderer(this, 0, 10);
        this.tail.setRotationPoint(0.0F, 8.0F, 7.4F);
        this.tail.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(tail, 0.5462880558742251F, 0.0F, 0.0F);
        this.headChild_1 = new ModelRenderer(this, 0, 25);
        this.headChild_1.setRotationPoint(-3.0F, -1.8F, -6.3F);
        this.headChild_1.addBox(-1.5F, -3.0F, 0.25F, 4, 4, 1, 0.0F);
        this.setRotateAngle(headChild_1, 0.0F, 0.0F, -0.22759093446006054F);
        this.leg1 = new ModelRenderer(this, 8, 10);
        this.leg1.setRotationPoint(-2.2F, 12.0F, 6.2F);
        this.leg1.addBox(-1.5F, 0.0F, -1.0F, 3, 12, 3, 0.0F);
        this.leg2 = new ModelRenderer(this, 8, 10);
        this.leg2.mirror = true;
        this.leg2.setRotationPoint(2.2F, 12.0F, 6.2F);
        this.leg2.addBox(-1.5F, 0.0F, -1.0F, 3, 12, 3, 0.0F);
        this.leg3 = new ModelRenderer(this, 8, 10);
        this.leg3.setRotationPoint(-2.2F, 12.0F, -5.4F);
        this.leg3.addBox(-1.5F, 0.0F, -1.0F, 3, 12, 3, 0.0F);
        this.shape14 = new ModelRenderer(this, 36, 41);
        this.shape14.setRotationPoint(0.0F, -1.5F, -1.4F);
        this.shape14.addBox(-1.0F, -1.0F, -4.0F, 2, 2, 8, 0.0F);
        this.setRotateAngle(shape14, -0.36425021489121656F, 0.0F, 0.0F);
        this.headChild_2 = new ModelRenderer(this, 0, 25);
        this.headChild_2.setRotationPoint(2.0F, -2.0F, -6.3F);
        this.headChild_2.addBox(-1.5F, -3.0F, 0.25F, 4, 4, 1, 0.0F);
        this.setRotateAngle(headChild_2, 0.0F, 0.0F, 0.22759093446006054F);
        this.headChild_3 = new ModelRenderer(this, 26, 41);
        this.headChild_3.mirror = true;
        this.headChild_3.setRotationPoint(0.0F, -2.8F, -8.5F);
        this.headChild_3.addBox(-2.0F, 0.3F, -5.25F, 4, 4, 4, 0.0F);
        this.body = new ModelRenderer(this, 20, 15);
        this.body.setRotationPoint(0.0F, 10.6F, -4.5F);
        this.body.addBox(-4.0F, -2.0F, -5.0F, 8, 15, 9, 0.0F);
        this.setRotateAngle(body, 1.5481070465189704F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 7.0F, -4.1F);
        this.head.addBox(-3.0F, -3.6F, -10.0F, 6, 5, 5, 0.0F);
        this.setRotateAngle(head, 0.136659280431156F, 0.0F, 0.0F);
        this.leg4 = new ModelRenderer(this, 8, 10);
        this.leg4.mirror = true;
        this.leg4.setRotationPoint(2.2F, 12.0F, -5.4F);
        this.leg4.addBox(-1.5F, 0.0F, -1.0F, 3, 12, 3, 0.0F);
        this.headChild = new ModelRenderer(this, 1, 32);
        this.headChild.setRotationPoint(2.5F, -0.3F, -3.9F);
        this.headChild.addBox(-5.0F, -3.0F, -4.0F, 5, 8, 6, 0.0F);
        this.setRotateAngle(headChild, 1.0016444577195458F, 0.0F, 0.0F);
        this.head.addChild(this.headChild_1);
        this.head.addChild(this.shape14);
        this.head.addChild(this.headChild_2);
        this.head.addChild(this.headChild_3);
        this.head.addChild(this.headChild);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
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

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }  

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.head.rotateAngleY = f3 / (360F / (float) Math.PI);
		this.head.rotationPointZ = -4.2F;
		this.tail.rotateAngleZ = MathHelper.cos(f * 0.666F) * 0.5F * f1;
	}

	public void setLivingAnimations(EntityLivingBase living, float f1, float f2, float f3)
	{
		EntityHyena entityhyena = (EntityHyena) living;

		this.body.rotationPointY = 11.0F;
		this.head.rotationPointY = 7.5F;
		this.tail.rotationPointY = 8.0F;
		this.leg1.rotationPointY = 12.0F;
		this.leg2.rotationPointY = 12.0F;
		this.leg3.rotationPointY = 12.0F;
		this.leg4.rotationPointY = 12.0F;

		if(entityhyena.isSneaking())
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
