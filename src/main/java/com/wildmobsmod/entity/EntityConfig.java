package com.wildmobsmod.entity;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.logging.log4j.Level;

import com.wildmobsmod.main.WildMobsMod;
import com.wildmobsmod.misc.WeightedRandomSelector;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;

public class EntityConfig
{	
	private static final Set<EntityConfig> ALL_CONFIGS = new LinkedHashSet<>();

	protected static void registerEntityConfig(EntityConfig entityConfig)
	{
		ALL_CONFIGS.add(entityConfig);
	}
	
	/**
	 * Factory method: Constructs a new EntityConfig with the given default settings and registers it to the {@link EntityConfig#syncAll(Configuration) syncAll()} system.
	 */
	public static EntityConfig construct(String id, int spawnrate, int minPack, int maxPack, boolean enabled)
	{
		EntityConfig cfg = new EntityConfig(id, spawnrate, minPack, maxPack, enabled);
		registerEntityConfig(cfg);
		return cfg;
	}
	
	public static void syncAll(Configuration config)
	{
		ALL_CONFIGS.forEach(eCfg -> eCfg.sync(config));
	}
	
	protected final String id;
	protected final int defaultSpawnrate, defaultMinPack, defaultMaxPack;
	protected final boolean defaultEnabled;
	protected int spawnrate, minPack, maxPack;
	protected boolean enabled;
	
	protected EntityConfig(String id, int defaultSpawnrate, int defaultMinPack, int defaultMaxPack, boolean defaultEnabled)
	{
		this.id = id;
		this.defaultSpawnrate = defaultSpawnrate;
		this.defaultMinPack = defaultMinPack;
		this.defaultMaxPack = defaultMaxPack;
		this.defaultEnabled = defaultEnabled;
		spawnrate = defaultSpawnrate;
		minPack = defaultMinPack;
		maxPack = defaultMaxPack;
		enabled = defaultEnabled;
	}
	
	protected void sync(Configuration config)
	{
		enabled = config.getBoolean("register", getConfigCategory(), defaultEnabled, String.format("Register the %s Entity to the Game Registry? (Setting this to false will completely disable it.)", id));
		spawnrate = config.getInt("spawnrate", getConfigCategory(), defaultSpawnrate, 0, 100, String.format("The spawnrate of the %s Entity - higher means more frequent spawns. Set to 0 to disable natural spawning (it will still be registered).", id));
		minPack = config.getInt("minPackSize", getConfigCategory(), defaultMinPack, 1, 20, String.format("The minimum number of %s Entities to spawn per pack - has to be lower than maxPackSize (if it's larger, all packs will spawn with a size of minPackSize).", id));
		maxPack = Math.max(minPack, config.getInt("maxPackSize", getConfigCategory(), defaultMaxPack, 1, 20, String.format("The maximum number of %s Entities to spawn per pack - has to be larger than minPackSize (if it's lower, all packs will spawn with a size of minPackSize).", id)));
	}
	
	protected String getConfigCategory() {
		return WildMobsMod.CONFIG_CATEGORY_ENTITIES + "." + id;
	}

	public int getSpawnrate()
	{
		return spawnrate;
	}
	
	public int getMinPackSize()
	{
		return minPack;
	}
	
	public int getMaxPackSize()
	{
		return maxPack;
	}

	public boolean isEnabled()
	{
		return enabled;
	}
	
	/*
	 * =<>=<>=<>=<>= CONFIG PARSING UTILITY METHODS =<>=<>=<>=<>=
	 */
	
	protected static final String PATTERN_WEIGHTEDRANDOMSTACKSELECTOR = "[Pattern: <modid>:<itemid>:<meta>, <amount>, <weight>]";

	protected static WeightedRandomSelector<ItemStack> buildWeightedRandomStackSelector(String... stackList) {
		final int size = stackList.length;
		if(size < 1) {
			return new WeightedRandomSelector<>(new ItemStack[]{null}, new double[]{1.0D});
		}
		final ItemStack[] stacks = new ItemStack[size];
		final int[] weights = new int[size];
		int totalWeight = 0;
		for(int i = 0; i < size; i++) {
			String def = stackList[i];
			ItemStack stack;
			int weight;
			try {
				int pos = def.lastIndexOf(',');
				weight = Integer.parseInt(def.substring(pos+1).trim());
				def = def.substring(0, pos);
				pos = def.lastIndexOf(',');
				int stacksize = Integer.parseInt(def.substring(pos+1).trim());
				def = def.substring(0, pos).trim();
				pos = def.indexOf(':');
				String modid = def.substring(0, pos);
				def = def.substring(pos+1);
				pos = def.lastIndexOf(':');
				int meta;
				String itemid;
				if(pos < 0) {
					meta = 0;
					itemid = def;
				} else {
					itemid = def.substring(0, pos);
					meta = Integer.parseInt(def.substring(pos+1).trim());
				}
				String key = modid + ":" + itemid;
				Item item = (Item) Item.itemRegistry.getObject(key);
				if(item == null) {
					throw new NoSuchElementException(key);
				}
				stack = new ItemStack(item, stacksize, meta);
			} catch(IndexOutOfBoundsException | NumberFormatException | NoSuchElementException e) {
				FMLLog.log(WildMobsMod.MODID, Level.ERROR, e, "Invalid config entry: \"%s\" (index:%d)", stackList[i], i);
				stack = null;
				weight = 0;
			}
			stacks[i] = stack;
			weights[i] = weight;
			totalWeight += weight;
		}
		final double[] probabilities = new double[size];
		if(totalWeight > 0) {
			final double totalWeight_d = totalWeight;
			for(int i = 0; i < size; i++) {
				probabilities[i] = weights[i] / totalWeight_d;
			}
		} else {
			Arrays.fill(probabilities, 1.0D / size);
		}
		return new WeightedRandomSelector<>(stacks, probabilities);
	}

	protected static final String PATTERN_STACKLIST = "[Pattern: <modid>:<itemid>:<meta>, <amount>]";

	protected static ItemStack[] parseItemStackArray(String... stackArray) {
		final int size = stackArray.length;
		final ItemStack[] stacks = new ItemStack[size];
		for(int i = 0; i < size; i++) {
			String def = stackArray[i];
			ItemStack stack;
			try {
				int pos = def.lastIndexOf(',');
				int stacksize = Integer.parseInt(def.substring(pos+1).trim());
				def = def.substring(0, pos).trim();
				pos = def.indexOf(':');
				String modid = def.substring(0, pos);
				def = def.substring(pos+1);
				pos = def.lastIndexOf(':');
				int meta;
				String itemid;
				if(pos < 0) {
					meta = 0;
					itemid = def;
				} else {
					itemid = def.substring(0, pos);
					meta = Integer.parseInt(def.substring(pos+1).trim());
				}
				String key = modid + ":" + itemid;
				Item item = (Item) Item.itemRegistry.getObject(key);
				if(item == null) {
					throw new NoSuchElementException(key);
				}
				stack = new ItemStack(item, stacksize, meta);
			} catch(IndexOutOfBoundsException | NumberFormatException | NoSuchElementException e) {
				FMLLog.log(WildMobsMod.MODID, Level.ERROR, e, "Invalid config entry: \"%s\" (index:%d)", stackArray[i], i);
				stack = null;
			}
			stacks[i] = stack;
		}
		return stacks;
	}
}
