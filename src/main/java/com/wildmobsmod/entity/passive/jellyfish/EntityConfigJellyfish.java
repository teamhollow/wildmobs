package com.wildmobsmod.entity.passive.jellyfish;

import com.wildmobsmod.entity.EntityConfig;

import net.minecraftforge.common.config.Configuration;

public class EntityConfigJellyfish extends EntityConfig
{
	/**
	 * Adaptation of the {@link EntityConfig#construct(String, int, int, int, boolean) EntityConfig.construct()} factory method.
	 */
	public static EntityConfigJellyfish construct(String id, int spawnrate, int minPack, int maxPack, boolean enabled, int netherSpawnrate)
	{
		EntityConfigJellyfish cfg = new EntityConfigJellyfish(id, spawnrate, minPack, maxPack, enabled, netherSpawnrate);
		registerEntityConfig(cfg);
		return cfg;
	}
	
	protected final int defaultNetherSpawnrate;
	protected int netherSpawnrate;
	
	protected EntityConfigJellyfish(String id, int defaultSpawnrate, int defaultMinPack, int defaultMaxPack, boolean defaultEnabled, int defaultNetherSpawnrate)
	{
		super(id, defaultSpawnrate, defaultMinPack, defaultMaxPack, defaultEnabled);
		this.defaultNetherSpawnrate = defaultNetherSpawnrate;
		netherSpawnrate = defaultNetherSpawnrate;
	}

	@Override
	protected void sync(Configuration config)
	{
		super.sync(config);
		netherSpawnrate = config.getInt("netherSpawnrate", getConfigCategory(), defaultNetherSpawnrate, 0, 100, "Nether Medusae (nether variant of the Jellyfish) spawnrate. (Note that pack size will always be 1-2)");
	}

	public int getNetherSpawnrate()
	{
		return netherSpawnrate;
	}
	
	public int getNetherMinPackSize()
	{
		return Math.max(1, getMinPackSize() / 2);
	}

	public int getNetherMaxPackSize()
	{
		return Math.max(1, getMaxPackSize() / 2);
	}
}
