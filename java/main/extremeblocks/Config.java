package main.extremeblocks;

import java.io.File;
import main.com.hk.eb.util.JavaHelp;
import net.minecraftforge.common.config.Configuration;

public class Config
{
	public final Configuration config;
	public final File configFile;
	public final static String generalConfig = "general";
	public final static String oresConfig = "ores";
	public final static String mobConfig = "mobs";
	public final static String worldConfig = "world generation";

	public Config(Configuration config)
	{
		this.config = config;
		configFile = config.getConfigFile();
	}

	public int getSpawnRate(String ore, int defaultValue)
	{
		int min = Math.max(defaultValue - 4, 0);
		int max = Math.min(defaultValue + 4, 35);
		return config.getInt(ore + " Ore", oresConfig, defaultValue, min, max, "Adjust spawn rate for the " + ore + " Ore. From " + min + ", to " + max + ".");
	}

	public boolean allowMob(String mobName, String switchedWith)
	{
		return config.getBoolean("Allow the " + mobName, mobConfig, true, "Allow the " + mobName + " to spawn and function. If is off, the mob, whenever spawned in, is switched with a" + (JavaHelp.startsWithVowel(switchedWith) ? "n " : " ") + switchedWith + " in game.");
	}

	public boolean shouldGen(String structureName)
	{
		return config.getBoolean("Generate the " + structureName, worldConfig, true, "Allow the " + structureName + " to generate in your world in game.");
	}

	public boolean getBool(String elementName, boolean defaultValue, String comment)
	{
		return config.getBoolean(elementName, generalConfig, defaultValue, comment);
	}

	public int getInt(String elementName, int min, int defaultValue, int max, String comment)
	{
		return config.getInt(elementName, generalConfig, defaultValue, min, max, comment);
	}

	public int getInt(String elementName, int defaultValue, String comment)
	{
		return config.getInt(elementName, generalConfig, defaultValue, Integer.MIN_VALUE, Integer.MAX_VALUE, comment);
	}

	public double getDouble(String elementName, double min, double defaultValue, double max, String comment)
	{
		return config.get(generalConfig, elementName, defaultValue, comment, min, max).getDouble(defaultValue);
	}

	public double getDouble(String elementName, double defaultValue, String comment)
	{
		return config.get(generalConfig, elementName, defaultValue, comment).getDouble(defaultValue);
	}

	public float getFloat(String elementName, float min, float defaultValue, float max, String comment)
	{
		return config.getFloat(elementName, generalConfig, defaultValue, min, max, comment);
	}

	public float getFloat(String elementName, float defaultValue, String comment)
	{
		return config.getFloat(elementName, generalConfig, defaultValue, Float.MIN_VALUE, Float.MAX_VALUE, comment);
	}

	public String getString(String elementName, String defaultValue, String comment)
	{
		return config.getString(elementName, generalConfig, defaultValue, comment);
	}
}
