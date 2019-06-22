package com.wildmobsmod.entity.monster.dreath;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.Level;

import com.wildmobsmod.entity.EntityConfig;
import com.wildmobsmod.main.WildMobsMod;
import com.wildmobsmod.misc.WeightedRandomSelector;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;

public class EntityConfigDreathMired extends EntityConfig
{
	/**
	 * Adaptation of the {@link EntityConfig#construct(String, int, int, int, boolean) EntityConfig.construct()} factory method.
	 */
	public static EntityConfigDreathMired construct(String id, int spawnrate, int minPack, int maxPack, boolean enabled, boolean enableBabyDreath, boolean registerMiredEntities, double bottleDropChance, boolean enableMiredBottle, int miredDropCount, String[] miredLoot, boolean guaranteeDropCount)
	{
		EntityConfigDreathMired cfg = new EntityConfigDreathMired(id, spawnrate, minPack, maxPack, enabled, enableBabyDreath, registerMiredEntities, bottleDropChance, enableMiredBottle, miredDropCount, miredLoot, guaranteeDropCount);
		registerEntityConfig(cfg);
		return cfg;
	}
	
	protected final boolean defaultEnableBabyDreath, defaultRegisterMiredEntities, defaultEnableMiredBottle, defaultGuaranteeDropCount;
	protected boolean enableBabyDreath, registerMiredEntities, enableMiredBottle, guaranteeDropCount;
	protected final double defaultBottleDropChance;
	protected double bottleDropChance;
	protected final int defaultMiredDropCount;
	protected int miredDropCount;
	protected final String[] defaultMiredLoot;
	protected String[] miredLoot;
	
	protected WeightedRandomSelector<ItemStack> miredLootTable;
	
	protected EntityConfigDreathMired(String id, int defaultSpawnrate, int defaultMinPack, int defaultMaxPack, boolean defaultEnabled, boolean defaultEnableBabyDreath, boolean defaultRegisterMiredEntities, double defaultBottleDropChance, boolean defaultEnableMiredBottle, int defaultMiredDropCount, String[] defaultMiredLoot, boolean defaultGuaranteeDropCount)
	{
		super(id, defaultSpawnrate, defaultMinPack, defaultMaxPack, defaultEnabled);
		this.defaultEnableBabyDreath = defaultEnableBabyDreath;
		this.defaultRegisterMiredEntities = defaultRegisterMiredEntities;
		this.defaultBottleDropChance = defaultBottleDropChance;
		this.defaultEnableMiredBottle = defaultEnableMiredBottle;
		this.defaultMiredDropCount = defaultMiredDropCount;
		this.defaultMiredLoot = defaultMiredLoot;
		this.defaultGuaranteeDropCount = defaultGuaranteeDropCount;
		enableBabyDreath = defaultEnableBabyDreath;
		registerMiredEntities = defaultRegisterMiredEntities;
		bottleDropChance = defaultBottleDropChance;
		enableMiredBottle = defaultEnableMiredBottle;
		miredDropCount = defaultMiredDropCount;
		miredLoot = defaultMiredLoot;
		guaranteeDropCount = defaultGuaranteeDropCount;
	}

	@Override
	protected void sync(Configuration config)
	{
		super.sync(config);
		enableBabyDreath = config.getBoolean("enableBabyDreath", getConfigCategory(), defaultEnableBabyDreath, "Allow Baby Dreaths?");
		registerMiredEntities = config.getBoolean("registerMiredEntities", getMiredCategory(), defaultRegisterMiredEntities, "Register the Mired and MiredSummoner Entities?");
		bottleDropChance = config.get(getMiredCategory(), "bottleDropChance", defaultBottleDropChance, "Chance (in percent) of a mired dropping a mired bottle.", 0.0D, 1.0D).getDouble();
		enableMiredBottle = config.getBoolean("enableMiredBottle", getMiredCategory(), defaultEnableMiredBottle, "Allow MiredSummoners (and consequentially Mireds) to be spawned via Mired Bottles (which are dropped by Dreaths)? [Ignored if \"registerMiredEntities\" is false]");
		miredDropCount = config.getInt("miredDropCount", getMiredCategory(), defaultMiredDropCount, 1, 20, "Amount of Loot a Mired Bottle is meant to drop.");
		miredLoot = config.getStringList("miredLoot", getMiredCategory(), defaultMiredLoot, "Mired LootTable definition. " + PATTERN_WEIGHTEDRANDOMSTACKSELECTOR);
		miredLootTable = buildWeightedRandomStackSelector(miredLoot);
		guaranteeDropCount = config.getBoolean("guaranteeDropCount", getMiredCategory(), defaultGuaranteeDropCount, "If a MiredSummoners loot pool hasn't been exhausted during its lifetime, drop the leftovers on it's own death?");
	}
	
	protected String getMiredCategory() {
		return super.getConfigCategory() + ".mired";
	}
	
	public boolean getEnableBabyDreath()
	{
		return enableBabyDreath;
	}

	public boolean getRegisterMiredEntities()
	{
		return registerMiredEntities;
	}
	
	public double getBottleDropChance()
	{
		return bottleDropChance;
	}

	public boolean getEnableMiredBottle()
	{
		return registerMiredEntities && enableMiredBottle;
	}
	
	public int getMiredDropCount()
	{
		return miredDropCount;
	}
	
	/**
	 * May return null!
	 */
	public ItemStack getRandomLootStack()
	{
		return miredLootTable.next();
	}

	public boolean getGuaranteeDropCount()
	{
		return guaranteeDropCount;
	}

}
