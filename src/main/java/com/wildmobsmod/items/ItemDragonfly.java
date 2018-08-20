package com.wildmobsmod.items;

import java.util.List;

import com.wildmobsmod.entity.monster.wizard.EntityWizard;
import com.wildmobsmod.entity.passive.butterfly.EntityButterfly;
import com.wildmobsmod.entity.passive.dragonfly.EntityDragonfly;
import com.wildmobsmod.entity.projetile.spell.EntitySpell;
import com.wildmobsmod.main.MainRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemDragonfly extends Item {
    public static final String[] field_150923_a = new String[] {"blue", "cyan", "green", "orange", "red", "yellow"};
    public static final String[] field_150921_b = new String[] {"blue", "cyan", "green", "orange", "red", "yellow"};
    public static final int[] field_150922_c = new int[] {1973019, 11743532, 3887386, 5320730, 2437522, 8073150};
    @SideOnly(Side.CLIENT)
    private IIcon[] field_150920_d;
    
	public ItemDragonfly(){
        this.maxStackSize = 1;
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setTextureName("wildmobsmod:dragonfly");
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int p_77617_1_)
    {
        int j = MathHelper.clamp_int(p_77617_1_, 0, 15);
        return this.field_150920_d[j];
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack p_77667_1_)
    {
        int i = MathHelper.clamp_int(p_77667_1_.getItemDamage(), 0, 15);
        return super.getUnlocalizedName() + "." + field_150923_a[i];
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
    	if (p_77648_3_.isRemote)
    	{
    		return true;
    	}
    	else
    	{
            int i = p_77648_1_.getItemDamage();
    		Block block = p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_);
    		p_77648_4_ += Facing.offsetsXForSide[p_77648_7_];
    		p_77648_5_ += Facing.offsetsYForSide[p_77648_7_];
    		p_77648_6_ += Facing.offsetsZForSide[p_77648_7_];
    		double d0 = 0.0D;

    		if (p_77648_7_ == 1 && block.getRenderType() == 11)
    		{
    			d0 = 0.5D;
    		}

    		EntityDragonfly entityliving = new EntityDragonfly(p_77648_3_);
    		entityliving.setLocationAndAngles((double)p_77648_4_ + 0.5D, (double)p_77648_5_ + d0, (double)p_77648_6_ + 0.5D, MathHelper.wrapAngleTo180_float(p_77648_3_.rand.nextFloat() * 360.0F), 0.0F);
    		entityliving.rotationYawHead = entityliving.rotationYaw;
    		entityliving.renderYawOffset = entityliving.rotationYaw;
    		entityliving.onSpawnWithEgg((IEntityLivingData)null);
            entityliving.setSkin(i);
            if (p_77648_1_.hasDisplayName())
            {
                entityliving.setCustomNameTag(p_77648_1_.getDisplayName());
            }
    		p_77648_3_.spawnEntityInWorld(entityliving);
    		p_77648_1_.stackSize --;
    		return true;
    	}
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_)
    {
        for (int i = 0; i < 6; ++i)
        {
            p_150895_3_.add(new ItemStack(p_150895_1_, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_)
    {
        this.field_150920_d = new IIcon[field_150921_b.length];

        for (int i = 0; i < field_150921_b.length; ++i)
        {
            this.field_150920_d[i] = p_94581_1_.registerIcon(this.getIconString() + "_" + field_150921_b[i]);
        }
    }
}
