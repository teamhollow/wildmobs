package com.wildmobsmod.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Sets;
import com.wildmobsmod.main.WildMobsMod;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.config.Configuration;

public class EntityRegistrar
{
	private static final Set<EntityRegistrar> ALL_REGISTRARS = new LinkedHashSet<>();

	protected static void registerRegistrar(EntityRegistrar entityRegistrar)
	{
		ALL_REGISTRARS.add(entityRegistrar);
	}
	
	static void registerAllEntities()
	{
		ALL_REGISTRARS.forEach(reg -> reg.registerEntity());
	}

	static void registerAllSpawns()
	{
		ALL_REGISTRARS.forEach(reg -> reg.registerSpawns());
	}
	
	public static EntityRegistrar constructDisjunct(Class entityClass, EntityConfig config, String entityName, int entityId, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, EnumCreatureType creatureType, BiomeDictionary.Type... biomeTypes)
	{
		return construct(entityClass, config, entityName, entityId, trackingRange, updateFrequency, sendsVelocityUpdates, creatureType, getBiomesFromTypesDisjunct(biomeTypes));
	}
	
	public static EntityRegistrar constructConjunct(Class entityClass, EntityConfig config, String entityName, int entityId, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, EnumCreatureType creatureType, BiomeDictionary.Type... biomeTypes)
	{
		return construct(entityClass, config, entityName, entityId, trackingRange, updateFrequency, sendsVelocityUpdates, creatureType, getBiomesFromTypesConjunct(biomeTypes));
	}

	public static EntityRegistrar construct(Class entityClass, EntityConfig config, String entityName, int entityId, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, EnumCreatureType creatureType, BiomeDictionary.Type biomeType)
	{
		return construct(entityClass, config, entityName, entityId, trackingRange, updateFrequency, sendsVelocityUpdates, creatureType, BiomeDictionary.getBiomesForType(biomeType));
	}
	
	public static EntityRegistrar construct(Class entityClass, EntityConfig config, String entityName, int entityId, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, EnumCreatureType creatureType, BiomeGenBase[] biomes)
	{
		EntityRegistrar registrar = new EntityRegistrar(entityClass, config, entityName, entityId, trackingRange, updateFrequency, sendsVelocityUpdates, creatureType, biomes);
		registerRegistrar(registrar);
		return registrar;
	}
	
	public static BiomeGenBase[] getBiomesFromTypesDisjunct(BiomeDictionary.Type... biomeTypes)
	{
		Map<Integer, BiomeGenBase> biomes = new HashMap<>();
		for(BiomeDictionary.Type type : biomeTypes)
		{
			for(BiomeGenBase biome : BiomeDictionary.getBiomesForType(type))
			{
				biomes.putIfAbsent(biome.biomeID, biome);
			}
		}
		return biomes.values().toArray(new BiomeGenBase[0]);
	}
	
	public static BiomeGenBase[] getBiomesFromTypesConjunct(BiomeDictionary.Type... biomeTypes)
	{
		if(biomeTypes.length < 1) return new BiomeGenBase[0];
		Set<BiomeGenBase> biomes = Sets.newHashSet(BiomeDictionary.getBiomesForType(biomeTypes[0]));
		for(int i = 1; i < biomeTypes.length; i++)
		{
			Set<BiomeGenBase> typeBiomes = Sets.newHashSet(BiomeDictionary.getBiomesForType(biomeTypes[0]));
			biomes.retainAll(typeBiomes);
		}
		return biomes.toArray(new BiomeGenBase[0]);
	}
	
	public static BiomeGenBase[] excludeBiomes(BiomeGenBase[] biomes, BiomeGenBase... blacklist)
	{
		Set<BiomeGenBase> result = Sets.newHashSet(biomes);
		result.removeAll(Arrays.asList(blacklist));
		return result.toArray(new BiomeGenBase[0]);
	}
	
	protected final Class entityClass;
	protected final String entityName;
	protected final EntityConfig config;
	protected final int entityId, trackingRange, updateFrequency;
	protected final boolean sendsVelocityUpdates;
	protected final EnumCreatureType creatureType;
	protected final BiomeGenBase[] biomes;
	
	protected EntityRegistrar(Class entityClass, EntityConfig config, String entityName, int entityId, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates,EnumCreatureType creatureType, BiomeGenBase[] biomes)
	{
		this.entityClass = entityClass;
		this.config = config;
		this.entityName = entityName;
		this.entityId = entityId;
		this.trackingRange = trackingRange;
		this.updateFrequency = updateFrequency;
		this.sendsVelocityUpdates = sendsVelocityUpdates;
		this.creatureType = creatureType;
		this.biomes = biomes;
	}
	
	protected void registerEntity()
	{
		if(config.isEnabled())
		{
			EntityRegistry.registerModEntity(entityClass, entityName, entityId, WildMobsMod.modInstance, trackingRange, updateFrequency, sendsVelocityUpdates);
		}
	}
	
	protected void registerSpawns()
	{
		int spawnrate = config.getSpawnrate();
		if(spawnrate > 0 && biomes != null && biomes.length > 0)
		{
			EntityRegistry.addSpawn(entityClass, spawnrate, config.getMinPackSize(), config.getMaxPackSize(), creatureType, biomes);
		}
	}
}
