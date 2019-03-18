package com.wildmobsmod.entity.monster.dreath.mired;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderMiredSummoner extends Render
{
	public RenderMiredSummoner() {}

	public RenderMiredSummoner(Item item)
	{
		this();
	}

	public void doRender(Entity entity, double x, double y, double z, float f1, float f2) {}

	@Override
	protected void bindEntityTexture(Entity entity) {}
	
	@Override
	protected void bindTexture(ResourceLocation resource) {}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}
