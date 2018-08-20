package com.wildmobsmod.entity.monster.skeleton;

import java.util.Calendar;
import java.util.UUID;

import com.wildmobsmod.entity.ai.EntityAICallForSkeletonWolf;
import com.wildmobsmod.entity.monster.skeletonwolf.EntitySkeletonWolf;
import com.wildmobsmod.main.MainRegistry;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;

public class EntityWMSkeleton extends EntityMob implements IRangedAttackMob
{
	//
	// This mob replaces the vanilla skeleton to allow baby skeletons and skeleton wolf spawning.
	//
	
    private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
    private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2D, false);
    private static final UUID babySpeedBoostUUID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
    private static final AttributeModifier babySpeedBoostModifier = new AttributeModifier(babySpeedBoostUUID, "Baby speed boost", 0.4D, 1);
    private boolean field_146076_bu = false;
    private float field_146074_bv = -1.0F;
    private float field_146073_bw;

    public EntityWMSkeleton(World p_i1741_1_)
    {
        super(p_i1741_1_);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIRestrictSun(this));
        this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntitySkeletonWolf.class, 8.0F));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));

        if (p_i1741_1_ != null && !p_i1741_1_.isRemote)
        {
            this.setCombatTask();
        }
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(12, new Byte((byte)0));
        this.dataWatcher.addObject(13, new Byte((byte)0));
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "mob.skeleton.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.skeleton.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.skeleton.death";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.playSound("mob.skeleton.step", 0.15F, 1.0F);
    }
    
    /**
     * If Animal, checks if the age timer is negative
     */
    public boolean isChild()
    {
        return this.getDataWatcher().getWatchableObjectByte(12) == 1;
    }

    /**
     * Get the experience points the entity currently has.
     */
    protected int getExperiencePoints(EntityPlayer p_70693_1_)
    {
        if (this.isChild())
        {
            this.experienceValue = (int)((float)this.experienceValue * 2.5F);
        }

        return super.getExperiencePoints(p_70693_1_);
    }

    /**
     * Set whether this zombie is a child.
     */
    public void setChild(boolean p_82227_1_)
    {
        this.getDataWatcher().updateObject(12, Byte.valueOf((byte)(p_82227_1_ ? 1 : 0)));

        if (this.worldObj != null && !this.worldObj.isRemote)
        {
            IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
            iattributeinstance.removeModifier(babySpeedBoostModifier);

            if (p_82227_1_)
            {
                iattributeinstance.applyModifier(babySpeedBoostModifier);
            }
        }
    }
    
    public boolean attackEntityAsMob(Entity p_70652_1_)
    {
        if (super.attackEntityAsMob(p_70652_1_))
        {
            if (this.getSkeletonType() == 1 && p_70652_1_ instanceof EntityLivingBase)
            {
                ((EntityLivingBase)p_70652_1_).addPotionEffect(new PotionEffect(Potion.wither.id, 200));
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote && !this.isChild())
        {
            float f = this.getBrightness(1.0F);

            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)))
            {
                boolean flag = true;
                ItemStack itemstack = this.getEquipmentInSlot(4);

                if (itemstack != null)
                {
                    if (itemstack.isItemStackDamageable())
                    {
                        itemstack.setItemDamage(itemstack.getItemDamageForDisplay() + this.rand.nextInt(2));

                        if (itemstack.getItemDamageForDisplay() >= itemstack.getMaxDamage())
                        {
                            this.renderBrokenItemStack(itemstack);
                            this.setCurrentItemOrArmor(4, (ItemStack)null);
                        }
                    }

                    flag = false;
                }

                if (flag)
                {
                    this.setFire(8);
                }
            }
        }

        if (this.worldObj.isRemote && this.getSkeletonType() == 1 && this.isChild() == false)
        {
            this.setSize(0.72F, 2.34F);
        }
        else if (this.worldObj.isRemote && this.getSkeletonType() == 1 && this.isChild() == true)
        {
            this.setSize(0.36F, 1.17F);
        }
        else if (this.worldObj.isRemote && this.getSkeletonType() != 1 && this.isChild() == false)
        {
            this.setSize(0.6F, 1.8F);
        }
        else if (this.worldObj.isRemote && this.getSkeletonType() != 1 && this.isChild() == true)
        {
            this.setSize(0.3F, 0.9F);
        }

        super.onLivingUpdate();
    }

    /**
     * Handles updating while being ridden by an entity
     */
    public void updateRidden()
    {
        super.updateRidden();

        if (this.ridingEntity instanceof EntityCreature)
        {
            EntityCreature entitycreature = (EntityCreature)this.ridingEntity;
            this.renderYawOffset = entitycreature.renderYawOffset;
        }
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource p_70645_1_)
    {
        super.onDeath(p_70645_1_);

        if (p_70645_1_.getSourceOfDamage() instanceof EntityArrow && p_70645_1_.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)p_70645_1_.getEntity();
            double d0 = entityplayer.posX - this.posX;
            double d1 = entityplayer.posZ - this.posZ;

            if (d0 * d0 + d1 * d1 >= 2500.0D)
            {
                entityplayer.triggerAchievement(AchievementList.snipeSkeleton);
            }
        }
    }

    protected Item getDropItem()
    {
        return Items.arrow;
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
        int j;
        int k;

        if (this.getSkeletonType() == 1)
        {
            j = this.rand.nextInt(3 + p_70628_2_) - 1;

            for (k = 0; k < j; ++k)
            {
                this.dropItem(Items.coal, 1);
            }
        }
        else
        {
            j = this.rand.nextInt(3 + p_70628_2_);

            for (k = 0; k < j; ++k)
            {
                this.dropItem(Items.arrow, 1);
            }
        }

        j = this.rand.nextInt(3 + p_70628_2_);

        for (k = 0; k < j; ++k)
        {
            this.dropItem(Items.bone, 1);
        }
    }

    protected void dropRareDrop(int p_70600_1_)
    {
        if (this.getSkeletonType() == 1)
        {
            this.entityDropItem(new ItemStack(Items.skull, 1, 1), 0.0F);
        }
    }

    /**
     * Makes entity wear random armor based on difficulty
     */
    protected void addRandomArmor()
    {
        super.addRandomArmor();
        this.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
    }

    public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_)
    {
        p_110161_1_ = super.onSpawnWithEgg(p_110161_1_);

        if (this.getRNG().nextInt(20) == 0 && MainRegistry.enableBabySkeleton == true)
        {
        	this.setChild(true);
        }
        
        if (this.worldObj.provider instanceof WorldProviderHell && this.getRNG().nextInt(5) > 0)
        {
            this.tasks.addTask(4, this.aiAttackOnCollide);
            this.setSkeletonType(1);
            this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_sword));
            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
        }
        else
        {
            this.tasks.addTask(4, this.aiArrowAttack);
            this.addRandomArmor();
            this.enchantEquipment();
        }

        if (this.getRNG().nextInt(30) == 0 && this.getSkeletonType() != 1 && MainRegistry.enableSkeletonWolf == true)
        {
        	EntitySkeletonWolf entityskeletonwolf = new EntitySkeletonWolf(this.worldObj);
        	entityskeletonwolf.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
        	entityskeletonwolf.onSpawnWithEgg((IEntityLivingData)null);
        	entityskeletonwolf.setSkeletonType(0);
        	this.worldObj.spawnEntityInWorld(entityskeletonwolf);
        	entityskeletonwolf.entityToFollow = this;
        	if (this.isChild() == true)
        	{
        		this.mountEntity(entityskeletonwolf);
        	}
        }
        else if (this.getRNG().nextInt(30) == 0 && this.getSkeletonType() == 1 && MainRegistry.enableWitherSkeletonWolf == true && MainRegistry.enableSkeletonWolf == true)
        {
        	EntitySkeletonWolf entityskeletonwolf = new EntitySkeletonWolf(this.worldObj);
        	entityskeletonwolf.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
        	entityskeletonwolf.onSpawnWithEgg((IEntityLivingData)null);
        	entityskeletonwolf.setSkeletonType(1);
        	this.worldObj.spawnEntityInWorld(entityskeletonwolf);
        	entityskeletonwolf.entityToFollow = this;
        	if (this.isChild() == true)
        	{
        		this.mountEntity(entityskeletonwolf);
        	}
        }

        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * this.worldObj.func_147462_b(this.posX, this.posY, this.posZ));

        if (this.getEquipmentInSlot(4) == null)
        {
            Calendar calendar = this.worldObj.getCurrentDate();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
            {
                this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
                this.equipmentDropChances[4] = 0.0F;
            }
        }

        return p_110161_1_;
    }

    /**
     * sets this entity's combat AI.
     */
    public void setCombatTask()
    {
        this.tasks.removeTask(this.aiAttackOnCollide);
        this.tasks.removeTask(this.aiArrowAttack);
        ItemStack itemstack = this.getHeldItem();

        if (itemstack != null && itemstack.getItem() == Items.bow)
        {
            this.tasks.addTask(4, this.aiArrowAttack);
        }
        else
        {
            this.tasks.addTask(4, this.aiAttackOnCollide);
        }
    }

    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_)
    {
        EntityArrow entityarrow = new EntityArrow(this.worldObj, this, p_82196_1_, 1.6F, (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
        int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
        int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());
        entityarrow.setDamage((double)(p_82196_2_ * 2.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.difficultySetting.getDifficultyId() * 0.11F));

        if (i > 0)
        {
            entityarrow.setDamage(entityarrow.getDamage() + (double)i * 0.5D + 0.5D);
        }

        if (j > 0)
        {
            entityarrow.setKnockbackStrength(j);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem()) > 0 || this.getSkeletonType() == 1)
        {
            entityarrow.setFire(100);
        }

        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityarrow);
    }

    /**
     * Return this skeleton's type.
     */
    public int getSkeletonType()
    {
        return this.dataWatcher.getWatchableObjectByte(13);
    }

    /**
     * Set this skeleton's type.
     */
    public void setSkeletonType(int p_82201_1_)
    {
        this.dataWatcher.updateObject(13, Byte.valueOf((byte)p_82201_1_));
        this.isImmuneToFire = p_82201_1_ == 1;

        if (p_82201_1_ == 1)
        {
            this.setSize(0.72F, 2.34F);
        }
        else
        {
            this.setSize(0.6F, 1.8F);
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound p_70037_1_)
    {
        super.readEntityFromNBT(p_70037_1_);

        if (p_70037_1_.hasKey("SkeletonType", 99))
        {
            byte b0 = p_70037_1_.getByte("SkeletonType");
            this.setSkeletonType(b0);
        }
        if (p_70037_1_.getBoolean("IsBaby"))
        {
            this.setChild(true);
        }

        this.setCombatTask();
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound p_70014_1_)
    {
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setByte("SkeletonType", (byte)this.getSkeletonType());
        if (this.isChild())
        {
            p_70014_1_.setBoolean("IsBaby", true);
        }
    }

    /**
     * Sets the held item, or an armor slot. Slot 0 is held item. Slot 1-4 is armor. Params: Item, slot
     */
    public void setCurrentItemOrArmor(int p_70062_1_, ItemStack p_70062_2_)
    {
        super.setCurrentItemOrArmor(p_70062_1_, p_70062_2_);

        if (!this.worldObj.isRemote && p_70062_1_ == 0)
        {
            this.setCombatTask();
        }
    }

    /**
     * Returns the Y Offset of this entity.
     */
    public double getYOffset()
    {
    	if (this.isChild() == false)
    	{
    		return super.getYOffset() - 0.5D;
    	}
    	else
    	{
    		if (this.ridingEntity instanceof EntitySkeletonWolf)
    		{
    			return super.getYOffset() - 0.05D;
    		}
    		else
    		{
    			return super.getYOffset() - 0.2D;
    		}
    	}
    }
    
    public String getCommandSenderName()
    {
        if (this.hasCustomNameTag())
        {
            return this.getCustomNameTag();
        }
        else
        {
        	return StatCollector.translateToLocal("entity.Skeleton.name");
        }
    }

    public float getEyeHeight()
    {
    	if (this.isChild() == false)
    	{
    		return this.height * 0.85F;
    	}
    	else
    	{
    		return this.height * 0.425F;
    	}
    }
}