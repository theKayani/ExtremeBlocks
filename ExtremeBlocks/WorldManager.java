package extremeblocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldManager implements IWorldGenerator 
{
	private World world;
	private Random random;
	private int chunk_X;
	private int chunk_Z;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch(world.provider.dimensionId)
		{
			case -1:
				generateInNether(world, random, chunkX * 16, chunkZ * 16);
				break;
			case 0:
				generateInOverworld(world, random, chunkX * 16, chunkZ * 16);
				break;
			case 1:
				generateInEnd(world, random, chunkX * 16, chunkZ * 16);
				break;
		}
	}

	private void generateInEnd(World world, Random random, int chunkX, int chunkZ) {}

	private void generateInNether(World world, Random random, int chunkX, int chunkZ) {}

	private void generateInOverworld(World world, Random random, int chunkX, int chunkZ)
	{
		this.world = world;
		this.random = random;
		this.chunk_X = chunkX;
		this.chunk_Z = chunkZ;

		addOreSpawn(Init.trinquantium_ore	, 7	, 1	, 0	, 25);
		addOreSpawn(Init.tin_ore			, 16, 20, 10, 100);
		addOreSpawn(Init.copper_ore			, 16, 20, 10, 100);
		addOreSpawn(Init.glester_ore		, 12, 10, 20, 50);
		addOreSpawn(Init.delvlish_ore		, 12, 10, 20, 50);
		addOreSpawn(Init.silver_ore			, 5	, 2	, 40, 80);
		addOreSpawn(Init.meteorite			, 10, 5	, 0	, 20);
		addOreSpawn(Init.onyx_ore			, 20, 10, 20, 120);
		addOreSpawn(Init.fluorite_ore		, 32, 15, 0	, 256);
		addOreSpawn(Init.compact_stone		, 5	, 10, 0	, 256);
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
	public void addOreSpawn(Block block, int maxVeinSize, int chancesToSpawn, int minY, int maxY)
	{
		assert maxY > minY: "[ERR] (maxY > minY) != true";
		assert minY > 0: "[ERR] (minY > 0) != true";
		assert maxY <= 257 && maxY >= 0: "[ERR] (maxY < 256 && maxY > 0) != true";
		assert block != null: "[ERR] (block != null) != true";
		assert chancesToSpawn <= 100 && chancesToSpawn >= 0: "[ERR] (chancesToSpawn <= 100 && chancesToSpawn) != true";

		for(int x = 0; x < chancesToSpawn; x++)
		{
			int posX = chunk_X + random.nextInt(16);
			int posY = minY + random.nextInt(maxY - minY);
			int posZ = chunk_Z + random.nextInt(16);
			(new WorldGenMinable(block, maxVeinSize)).generate(world, random, posX, posY, posZ);
		}
	}
}
