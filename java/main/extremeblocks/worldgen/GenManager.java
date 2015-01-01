package main.extremeblocks.worldgen;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.MPUtil;
import main.com.hk.eb.util.Vector3I;
import main.extremeblocks.Init;
import main.extremeblocks.Vars;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class GenManager implements IWorldGenerator
{
	private static final ArrayList<Class<? extends Generation>> gens = JavaHelp.newArrayList();
	private World world;
	private Random random;
	private int chunkX;
	private int chunkZ;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (!Vars.alterWorld || MPUtil.isClientSide()) return;
		this.world = world;
		this.random = random;
		this.chunkX = chunkX * 16;
		this.chunkZ = chunkZ * 16;
		switch (world.provider.dimensionId)
		{
			case -1:
				generateInNether(world, random, this.chunkX, this.chunkZ);
				break;
			case 0:
				generateInOverworld(world, random, this.chunkX, this.chunkZ);
				break;
			case 1:
				generateInEnd(world, random, this.chunkX, this.chunkZ);
				break;
		}
	}

	private void generateInEnd(World world, Random random, int chunkX, int chunkZ)
	{
	}

	private void generateInNether(World world, Random random, int chunkX, int chunkZ)
	{
	}

	private void generateInOverworld(World world, Random random, int chunkX, int chunkZ)
	{
		addOreSpawn(Init.trinquantium_ore, 4, Vars.trinquantiumSR, 0, 25);
		addOreSpawn(Init.tin_ore, 8, Vars.tinSR, 10, 100);
		addOreSpawn(Init.copper_ore, 8, Vars.copperSR, 10, 100);
		addOreSpawn(Init.glester_ore, 6, Vars.glesterSR, 20, 50);
		addOreSpawn(Init.delvlish_ore, 6, Vars.delvlishSR, 20, 50);
		addOreSpawn(Init.silver_ore, 3, Vars.silverSR, 40, 80);
		addOreSpawn(Init.meteorite, 5, Vars.meteoriteSR, 0, 20);
		addOreSpawn(Init.onyx_ore, 10, Vars.onyxSR, 20, 120);
		addOreSpawn(Init.fluorite_ore, 16, Vars.fluoriteSR, 0, 256);
		addOreSpawn(Init.compact_stone, 3, Vars.compactStoneSR, 0, 256);
		addOreSpawn(Init.bone_dirt, 1, Vars.boneDirtSR, 20, 60);
		int j;
		int k, l, i1;

		for (int i = 0; i < gens.size(); i++)
		{
			try
			{
				Class<? extends Generation> gen = gens.get(i);
				boolean custom = gen.getAnnotation(Gen.class).custom();
				Generation g = null;
				if (custom)
				{
					g = (Generation) gen.getMethod("instance", World.class, Random.class, int.class).invoke(null, world, random, i);
				}
				generateStructure(custom ? g : gen.newInstance());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		for (j = 0; j < 12; ++j)
		{
			k = this.chunkX + this.random.nextInt(16) + 8;
			l = this.chunkZ + this.random.nextInt(16) + 8;
			i1 = this.world.getHeightValue(k, l) * 2 <= 1 ? 0 : random.nextInt(this.world.getHeightValue(k, l) * 2);
			new WorldGenReedLike(Init.cannabis_plant).generate(this.world, this.random, k, i1, l);
		}
	}

	/**
	 * Adds a Ore to generate into the World
	 * 
	 * @param block The block to generate
	 * @param meta The meta of the block to spawn
	 * @param maxVeinSize How many to spawn together
	 * @param chancesToSpawn 0-100 The chance it will spawn there
	 * @param minY The minimum Y to spawn at
	 * @param maxY The maximum Y to spawn at
	 */
	public void addOreSpawn(Block ore, int meta, int maxVeinSize, int chance, int minY, int maxY)
	{
		if (chance > 0)
		{
			for (int x = 0; x < chance; x++)
			{
				int posX = chunkX + random.nextInt(16);
				int posY = minY + random.nextInt(maxY - minY);
				int posZ = chunkZ + random.nextInt(16);
				new WorldGenMinable(ore, meta, maxVeinSize, Blocks.stone).generate(world, random, posX, posY, posZ);
			}
		}
	}

	/**
	 * Adds a Ore to generate into the World
	 * 
	 * @param block The block to generate
	 * @param maxVeinSize How many to spawn together
	 * @param chancesToSpawn 0-100 The chance it will spawn there
	 * @param minY The minimum Y to spawn at
	 * @param maxY The maximum Y to spawn at
	 */
	public void addOreSpawn(Block ore, int maxVeinSize, int chance, int minY, int maxY)
	{
		if (chance > 0)
		{
			this.addOreSpawn(ore, 0, maxVeinSize, chance, minY, maxY);
		}
	}

	public void generateStructure(Generation gen)
	{
		if (Vars.gens.get(gen.getClass()).booleanValue())
		{
			for (int i = 0; i < gen.chanceToSpawn; i++)
			{
				Vector3I v = gen.getSpawnPosition(world, random, chunkX, chunkZ);
				gen.generate(world, random, v.x, v.y, v.z);
			}
		}
	}

	public static void registerGeneration(Class<? extends Generation> clazz)
	{
		if (!gens.contains(clazz))
		{
			if (clazz.isAnnotationPresent(Gen.class))
			{
				gens.add(clazz);
				Vars.logger.info("Registered Generation: " + clazz.getSimpleName() + ", " + clazz.getAnnotation(Gen.class).name());
			}
			else throw new IllegalArgumentException(clazz.getName() + " doesn't have the Gen.class annotation!");
		}
	}

	public static List<Class<? extends Generation>> getGens()
	{
		return Collections.unmodifiableList(gens);
	}

	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	public static @interface Gen
	{
		String name();

		int chance() default 0;

		boolean custom() default false;
	}
}
