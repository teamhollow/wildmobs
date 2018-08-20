package com.wildmobsmod.entity.monster.wizard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.wildmobsmod.entity.projetile.spell.EntitySpell;
import com.wildmobsmod.items.WildMobsModItems;
import com.wildmobsmod.main.MainRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EntityWizard extends EntityMob implements IRangedAttackMob
{
	//
	// TODO: Add more spells to the wizard and a new drop, I wasn't happy with the magic wand as it was quite op so I removed it for now. The wand still exists as an item, but they cannot be acquired in survival
	// and don't do anything.
	//
	
	@Override
	protected void entityInit() {
		super.entityInit();
        this.dataWatcher.addObject(20, Byte.valueOf((byte)0));
	}
	
    private int drinking;
    private int drinkSound;
    private int armTime;
    private int attackCooldown;
    
    private static final Item[] wizardDrops = new Item[] {Items.speckled_melon, Items.magma_cream, Items.ghast_tear, Items.glass_bottle, Items.golden_apple};
	
    public EntityWizard(World p_i1744_1_)
    {
        super(p_i1744_1_);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0D, 29, 10.0F));
        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, true));
        this.setHasArms(false);
        this.experienceValue = 10;
        this.attackCooldown = 0;
    }
    
    public boolean getHasArms()
    {
        return (this.dataWatcher.getWatchableObjectByte(20) & 1) != 0;
    }

    public void setHasArms(boolean p_70900_1_)
    {
        if (p_70900_1_)
        {
            this.dataWatcher.updateObject(20, Byte.valueOf((byte)1));
        }
        else
        {
            this.dataWatcher.updateObject(20, Byte.valueOf((byte)0));
        }
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(36.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    }

    public boolean isAIEnabled()
    {
        return true;
    }
    
    public void onLivingUpdate()
    {
        if (!this.worldObj.isRemote)
        {
        	if (this.rand.nextFloat() < 0.02F && this.getHealth() < this.getMaxHealth() && !this.isPotionActive(Potion.regeneration))
        	{
        		if (this.drinking <= 0)
        		{
        			this.drinking = 26;
        			this.setCurrentItemOrArmor(0, new ItemStack(Items.potionitem, 1, 8289));
                	this.equipmentDropChances[0] = 0.085F;
        		}
        	}
            else if (this.rand.nextFloat() < 0.10F && this.isInsideOfMaterial(Material.water) && !this.isPotionActive(Potion.waterBreathing))
            {
        		if (this.drinking <= 0)
        		{
        			this.drinking = 26;
        			this.setCurrentItemOrArmor(0, new ItemStack(Items.potionitem, 1, 8237));
                	this.equipmentDropChances[0] = 0.085F;
        		}
            }
            else if (this.rand.nextFloat() < 0.10F && this.isBurning() && !this.isPotionActive(Potion.fireResistance))
            {
        		if (this.drinking <= 0)
        		{
        			this.drinking = 26;
        			this.setCurrentItemOrArmor(0, new ItemStack(Items.potionitem, 1, 16307));
                	this.equipmentDropChances[0] = 0.085F;
        		}
            }
            else if (this.rand.nextFloat() < 0.10F && this.isPotionActive(Potion.poison))
            {
        		if (this.drinking <= 0)
        		{
        			this.drinking = 26;
        			this.setCurrentItemOrArmor(0, new ItemStack(Items.milk_bucket, 1));
                	this.equipmentDropChances[0] = 0.085F;
        		}
            }
            else if (this.rand.nextFloat() < 0.10F && this.isPotionActive(Potion.wither))
            {
        		if (this.drinking <= 0)
        		{
        			this.drinking = 26;
        			this.setCurrentItemOrArmor(0, new ItemStack(Items.milk_bucket, 1));
                	this.equipmentDropChances[0] = 0.085F;
        		}
            }
            else if (this.rand.nextFloat() < 0.07F && this.isPotionActive(Potion.moveSlowdown))
            {
        		if (this.drinking <= 0)
        		{
        			this.drinking = 26;
        			this.setCurrentItemOrArmor(0, new ItemStack(Items.milk_bucket, 1));
                	this.equipmentDropChances[0] = 0.085F;
        		}
            }
            else if (this.rand.nextFloat() < 0.2F && this.getAttackTarget() != null && this.getHealth() < 22.0F && !this.isPotionActive(Potion.field_76444_x))
            {
        		if (this.drinking <= 0)
        		{
        			this.drinking = 26;
        			if (this.worldObj.difficultySetting == EnumDifficulty.HARD)
        			{
        			    this.setCurrentItemOrArmor(0, new ItemStack(Items.golden_apple, 1, 1));
        			}
        			else
        			{
        			    this.setCurrentItemOrArmor(0, new ItemStack(Items.golden_apple, 1, 0));
        			}
                	this.equipmentDropChances[0] = 0.085F;
        		}
            }
            
            if (this.rand.nextFloat() < 0.005F && this.getAttackTarget() != null && this.getAttackTarget().getDistanceSqToEntity(this) <= 36.0D)
            {
                this.teleportRandomly();
            }
            else if (this.rand.nextFloat() < 0.008F && this.getAttackTarget() != null && this.getAttackTarget().getDistanceSqToEntity(this) <= 36.0D && this.getHealth() < 15.0F)
            {
                this.teleportRandomly();
            }
            else if (this.rand.nextFloat() < 0.05F && this.drinking > 0)
            {
                this.teleportRandomly();
            }
            
            if  (this.drinking <= 0)
            {
                this.setCurrentItemOrArmor(0, new ItemStack(WildMobsModItems.magicWand));
            }
            else
            {
            	if (this.drinking == 1)
            	{
            		ItemStack itemstack = this.getHeldItem();
            		if (itemstack != null && itemstack.getItem() == Items.potionitem)
            		{
            			List list = Items.potionitem.getEffects(itemstack);

            			if (list != null)
            			{
            				Iterator iterator = list.iterator();

            				while (iterator.hasNext())
            				{
            					PotionEffect potioneffect = (PotionEffect)iterator.next();
            					this.addPotionEffect(new PotionEffect(potioneffect));
            				}
            			}
            		}
            		else if (itemstack != null && itemstack.getItem() == Items.milk_bucket)
            		{
          				this.curePotionEffects(itemstack);
            		}
            		else if (itemstack != null && itemstack.getItem() == Items.golden_apple && itemstack.getItemDamage() == 0)
            		{
          				this.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 20 * 120, 0));
          				this.addPotionEffect(new PotionEffect(Potion.regeneration.id, 20 * 5, 1));
            		}
            		else if (itemstack != null && itemstack.getItem() == Items.golden_apple && itemstack.getItemDamage() == 1)
            		{
          				this.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 20 * 120, 3));
          				this.addPotionEffect(new PotionEffect(Potion.regeneration.id, 20 * 20, 1));
          				this.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 20 * 300, 0));
          				this.addPotionEffect(new PotionEffect(Potion.resistance.id, 20 * 300, 0));
            		}
                    this.setCurrentItemOrArmor(0, new ItemStack(WildMobsModItems.magicWand));
                	this.equipmentDropChances[0] = 0.0F;
            		if (itemstack != null && itemstack.getItemUseAction() == EnumAction.eat)
            		{
                        this.playSound("random.burp", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
            		}
            	}
            	this.drinking --;
            	if (this.drinkSound == 0)
            	{
            		ItemStack itemstack = this.getHeldItem();
            		if (itemstack != null && itemstack.getItem() == Items.golden_apple)
            		{
                        this.playSound("random.eat", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
            		}
            		else
            		{
						this.playSound("random.eat", 0.5F + 0.5F * (float)this.rand.nextInt(2), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            		}
                    this.drinkSound = 3;
            	}
            	else
            	{
                    this.drinkSound --;
            	}
            }
        }
        super.onLivingUpdate();
    }
    
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {  
		    if (p_70097_1_ instanceof EntityDamageSourceIndirect)
		    {
		    	for (int i = 0; i < 64; ++i)
		    	{
		    		if (this.teleportRandomly())
		    		{
		    			return true;
		    		}
		    	}
		    	return super.attackEntityFrom(p_70097_1_, p_70097_2_);
		    }
		    else
		    {
				return super.attackEntityFrom(p_70097_1_, p_70097_2_);
		    }   
        }
    }
    
    protected boolean teleportRandomly()
    {
        double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 16.0D;
        double d1 = this.posY + (double)(this.rand.nextInt(16) - 8);
        double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 16.0D;
        return this.teleportTo(d0, d1, d2);
    }
    
    protected boolean teleportTo(double p_70825_1_, double p_70825_3_, double p_70825_5_)
    {
        EnderTeleportEvent event = new EnderTeleportEvent(this, p_70825_1_, p_70825_3_, p_70825_5_, 0);
        if (MinecraftForge.EVENT_BUS.post(event)){
            return false;
        }
        double d3 = this.posX;
        double d4 = this.posY;
        double d5 = this.posZ;
        this.posX = event.targetX;
        this.posY = event.targetY;
        this.posZ = event.targetZ;
        boolean flag = false;
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.posY);
        int k = MathHelper.floor_double(this.posZ);

        if (this.worldObj.blockExists(i, j, k))
        {
            boolean flag1 = false;

            while (!flag1 && j > 0)
            {
                Block block = this.worldObj.getBlock(i, j - 1, k);

                if (block.getMaterial().blocksMovement())
                {
                    flag1 = true;
                }
                else
                {
                    --this.posY;
                    --j;
                }
            }

            if (flag1)
            {
                this.setPosition(this.posX, this.posY, this.posZ);

                if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox))
                {
                    flag = true;
                }
            }
        }

        if (!flag)
        {
            this.setPosition(d3, d4, d5);
            return false;
        }
        else
        {
            short short1 = 128;

            for (int l = 0; l < short1; ++l)
            {
                double d6 = (double)l / ((double)short1 - 1.0D);
                float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
                double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * (double)this.height;
                double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
            }

            this.worldObj.playSoundEffect(d3, d4, d5, "wildmobsmod:mob.wizard.teleport", 1.0F, 1.0F);
            this.playSound("wildmobsmod:mob.wizard.teleport", 1.0F, 1.0F);
            this.attackCooldown = 10;
            return true;
        }
    }
    
    public void updateAITick()
    {
        ItemStack itemstack = this.getHeldItem();
        if (this.getAttackTarget() == null && itemstack.getItem() == WildMobsModItems.magicWand)
        {
        	if (this.armTime <= 1)
        	{
        		this.armTime ++;
        	}
        	else
        	{
                this.setHasArms(false);
        	}
        }
        else
        {
        	if (this.armTime >= -1)
        	{
        		this.armTime --;
        	}
        	else
        	{
                this.setHasArms(true);
        	}
        }
    	if (this.attackCooldown > 0)
    	{
    		this.attackCooldown --;
    	}
    }
    
    protected String getLivingSound()
    {
        return "wildmobsmod:mob.wizard.say";
    }
    
    protected String getHurtSound()
    {
        return "wildmobsmod:mob.wizard.hurt";
    }
    
    protected String getDeathSound()
    {
        return "wildmobsmod:mob.wizard.hurt";
    }
    
    protected float getSoundVolume()
    {
        return 0.4F;
    }
    
    protected void dropFewItems(boolean f1, int f2)
    {
        int j = this.rand.nextInt(3) + 1;

        for (int k = 0; k < j; ++k)
        {
            int l = this.rand.nextInt(2);

            if (f2 > 0)
            {
                l += this.rand.nextInt(f2 + 1);
            }

            for (int i1 = 0; i1 < l; ++i1)
            {
                Item item = wizardDrops[this.rand.nextInt(wizardDrops.length)];
                this.dropItem(item, 1);
            }
        }
    }
    
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_)
    {
        p_110161_1_ = super.onSpawnWithEgg(p_110161_1_);

        this.setCurrentItemOrArmor(0, new ItemStack(WildMobsModItems.magicWand));
    	this.equipmentDropChances[0] = 0.0F;
    	
        return p_110161_1_;
    }
    
    protected boolean canDespawn()
    {
        return false;
    }
    
    public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_)
    {
    	if (this.attackCooldown <= 0 && this.drinking <= 0)
    	{
		    EntitySpell entityspell = new EntitySpell(this.worldObj, this);
	     	double d0 = p_82196_1_.posX - this.posX;
		    double d1 = p_82196_1_.posY + (double)p_82196_1_.getEyeHeight() - 2.100000023841858D - entityspell.posY;
	    	double d2 = p_82196_1_.posZ - this.posZ;
	    	float f1 = MathHelper.sqrt_double(d0 * d0 + d2 * d2) * 0.4F;
	    	float f2 = MathHelper.sqrt_double(d0 * d0 + d2 * d2) * 0.15F;
	    	int spell = this.rand.nextInt(10);
	    	if (spell < 2)
	    	{
	    		entityspell.setSpell(1);
	    	}
	    	else
	    	{
	    		entityspell.setSpell(0);
	    	}
	    	entityspell.setThrowableHeading(d0, d1 + (double)f2, d2, 1.0F, 4.0F);
	    	this.playSound("wildmobsmod:random.spell.spell", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
	    	this.worldObj.spawnEntityInWorld(entityspell);
    	}
    }
    
    protected float applyPotionDamageCalculations(DamageSource p_70672_1_, float p_70672_2_)
    {
        p_70672_2_ = super.applyPotionDamageCalculations(p_70672_1_, p_70672_2_);

        if (p_70672_1_.getEntity() == this)
        {
            p_70672_2_ = 0.0F;
        }

        if (p_70672_1_.isMagicDamage())
        {
            p_70672_2_ = (float)((double)p_70672_2_ * 0.15D);
        }

        return p_70672_2_;
    }
}