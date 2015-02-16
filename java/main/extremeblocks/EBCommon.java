package main.extremeblocks;

import java.awt.Color;
import java.util.Map;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.Rand;
import main.extremeblocks.Vars.Mob;
import main.extremeblocks.util.SpawnDetail;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import cpw.mods.fml.common.registry.EntityRegistry;

public class EBCommon
{
	public void registerRenderThings()
	{
	}

	public void openEBGuide()
	{

	}

	public void registerSounds()
	{
	}

	public static void registerThrowable(Class<? extends Entity> entityClass, String mobName)
	{
		EntityRegistry.registerModEntity(entityClass, mobName, nextID(), ExtremeBlocks.instance, 80, 3, true);
	}

	public static void registerEntity(Class<? extends EntityLiving> entityClass, String mobName)
	{
		int id = nextID();
		EntityRegistry.registerGlobalEntityID(entityClass, mobName, id);
		EntityRegistry.registerModEntity(entityClass, mobName, id, ExtremeBlocks.instance, 80, 3, true);
		addMob(entityClass);
	}

	public static void registerEntity(Class<? extends EntityLiving> entityClass, String mobName, EnumCreatureType creature, Color foreground, Color background, SpawnDetail... spawns)
	{
		int id = nextID();
		EntityRegistry.registerGlobalEntityID(entityClass, mobName, id, foreground.getRGB(), background.getRGB());
		EntityRegistry.registerModEntity(entityClass, mobName, id, ExtremeBlocks.instance, 80, 3, true);
		if (spawns != null && spawns.length > 0)
		{
			for (SpawnDetail spawn : spawns)
			{
				if (spawn != null)
				{
					EntityRegistry.addSpawn(entityClass, spawn.chanceToSpawn, spawn.minAmountToSpawn, spawn.maxAmountToSpawn, creature, spawn.biome);
				}
			}
		}
		addMob(entityClass);
	}

	public static void registerEntity(Class<? extends EntityLiving> entityClass, String mobName, EnumCreatureType creature, SpawnDetail... spawns)
	{
		Color color = new Color(Rand.nextInt(256), Rand.nextInt(256), Rand.nextInt(256));
		switch (creature)
		{
			case ambient:
				EBCommon.registerEntity(entityClass, mobName, creature, color, color, spawns);
				break;
			case creature:
				EBCommon.registerEntity(entityClass, mobName, creature, Color.WHITE, Color.BLACK, spawns);
				break;
			case monster:
				EBCommon.registerEntity(entityClass, mobName, creature, Color.BLACK, Color.WHITE, spawns);
				break;
			case waterCreature:
				EBCommon.registerEntity(entityClass, mobName, creature, Color.BLUE.brighter(), Color.BLUE.darker(), spawns);
				break;
		}
	}

	private static int nextID()
	{
		int i = entityID++;
		if (EntityList.getStringFromID(i) != null) return nextID();
		return i;
	}

	private static void addMob(Class<? extends EntityLivingBase> clazz)
	{
		if (clazz.isAnnotationPresent(Mob.class))
		{
			removeMobs.put(clazz, new Boolean(false));
		}
		else throw new IllegalArgumentException(clazz.getSimpleName() + " does not have the Mob.class annotation");
	}

	public static Map<Class<?>, Boolean> removeMobs = JavaHelp.newHashMap();
	private static int entityID = 1;
}
