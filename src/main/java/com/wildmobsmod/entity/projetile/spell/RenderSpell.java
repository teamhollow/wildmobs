package com.wildmobsmod.entity.projetile.spell;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSpell extends Render
{

    public RenderSpell()
    {
    }

    public RenderSpell(Item p_i1260_1_)
    {
        this();
    }
    
    public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
    }
    
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return null;
    }
}