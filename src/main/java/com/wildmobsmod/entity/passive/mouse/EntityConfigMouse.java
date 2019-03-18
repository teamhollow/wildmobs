package com.wildmobsmod.entity.passive.mouse;

import com.wildmobsmod.entity.EntityConfig;

import net.minecraftforge.common.config.Configuration;

public class EntityConfigMouse extends EntityConfig
{
	/**
	 * Adaptation of the {@link EntityConfig#construct(String, int, int, int, boolean) EntityConfig.construct()} factory method.
	 */
	public static EntityConfigMouse construct(String id, int spawnrate, int minPack, int maxPack, boolean enabled, boolean enableDiseasedMouse)
	{
		EntityConfigMouse cfg = new EntityConfigMouse(id, spawnrate, minPack, maxPack, enabled, enableDiseasedMouse);
		registerEntityConfig(cfg);
		return cfg;
	}
	
	protected final boolean defaultEnableDiseasedMouse;
	protected boolean enableDiseasedMouse;
	
	protected EntityConfigMouse(String id, int defaultSpawnrate, int defaultMinPack, int defaultMaxPack, boolean defaultEnabled, boolean defaultEnableDiseasedMouse)
	{
		super(id, defaultSpawnrate, defaultMinPack, defaultMaxPack, defaultEnabled);
		this.defaultEnableDiseasedMouse = defaultEnableDiseasedMouse;
		enableDiseasedMouse = defaultEnableDiseasedMouse;
	}

	@Override
	protected void sync(Configuration config)
	{
		super.sync(config);
		enableDiseasedMouse = config.getBoolean("enableDiseasedMouse", getConfigCategory(), defaultEnableDiseasedMouse, "Enable diseased/ill mice?");
	}

	public boolean getEnableDiseasedMouse()
	{
		return enableDiseasedMouse;
	}
}
