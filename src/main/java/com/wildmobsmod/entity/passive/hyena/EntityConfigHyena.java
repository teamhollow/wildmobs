package com.wildmobsmod.entity.passive.hyena;

import com.wildmobsmod.entity.EntityConfig;

import net.minecraftforge.common.config.Configuration;

public class EntityConfigHyena extends EntityConfig
{
	/**
	 * Adaptation of the {@link EntityConfig#construct(String, int, int, int, boolean) EntityConfig.construct()} factory method.
	 */
	public static EntityConfigHyena construct(String id, int spawnrate, int minPack, int maxPack, boolean enabled, boolean attackPlayersAtNight)
	{
		EntityConfigHyena cfg = new EntityConfigHyena(id, spawnrate, minPack, maxPack, enabled, attackPlayersAtNight);
		registerEntityConfig(cfg);
		return cfg;
	}
	
	protected final boolean defaultAttackPlayersAtNight;
	protected boolean attackPlayersAtNight;
	
	protected EntityConfigHyena(String id, int defaultSpawnrate, int defaultMinPack, int defaultMaxPack, boolean defaultEnabled, boolean defaultAttackPlayersAtNight)
	{
		super(id, defaultSpawnrate, defaultMinPack, defaultMaxPack, defaultEnabled);
		this.defaultAttackPlayersAtNight = defaultAttackPlayersAtNight;
		attackPlayersAtNight = defaultAttackPlayersAtNight;
	}

	@Override
	protected void sync(Configuration config)
	{
		super.sync(config);
		attackPlayersAtNight = config.getBoolean("attackPlayersAtNight", getConfigCategory(), defaultAttackPlayersAtNight, "Allow Hyenas to attack nearby Players at night in non-peaceful worlds?");
	}

	public boolean getAttackPlayersAtNight()
	{
		return attackPlayersAtNight;
	}

}
