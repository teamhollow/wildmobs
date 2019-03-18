package com.wildmobsmod.entity.passive.wolf;

import com.wildmobsmod.entity.EntityConfig;

import net.minecraftforge.common.config.Configuration;

public class EntityConfigWolf extends EntityConfig
{
	/**
	 * Adaptation of the {@link EntityConfig#construct(String, int, int, int, boolean) EntityConfig.construct()} factory method.
	 */
	public static EntityConfigWolf construct(String id, int spawnrate, int minPack, int maxPack, boolean enabled, int overrideChance)
	{
		EntityConfigWolf cfg = new EntityConfigWolf(id, spawnrate, minPack, maxPack, enabled, overrideChance);
		registerEntityConfig(cfg);
		return cfg;
	}
	
	protected final int defaultOverrideChance;
	protected int overrideChance;
	
	protected EntityConfigWolf(String id, int defaultSpawnrate, int defaultMinPack, int defaultMaxPack, boolean defaultEnabled, int defaultOverrideChance)
	{
		super(id, defaultSpawnrate, defaultMinPack, defaultMaxPack, defaultEnabled);
		this.defaultOverrideChance = defaultOverrideChance;
		overrideChance = defaultOverrideChance;
	}

	@Override
	protected void sync(Configuration config)
	{
		super.sync(config);
		overrideChance = config.getInt("overrideChance", getConfigCategory(), defaultOverrideChance, 0, 100, "Chance (in Percent) to replace a vanilla Wolf with a WildMobs one on spawn (this can impact mod compatibility).");
	}

	public int getOverrideChance()
	{
		return overrideChance;
	}
}
