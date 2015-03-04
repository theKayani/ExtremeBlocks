package main.extremeblocks.util;

import java.util.List;
import main.com.hk.eb.util.JavaHelp;
import net.minecraft.world.biome.BiomeGenBase;

public class SpawnDetail
{
	public final BiomeGenBase biome;
	public final int chanceToSpawn;
	public final int minAmountToSpawn;
	public final int maxAmountToSpawn;

	public SpawnDetail(BiomeGenBase biome, int chanceToSpawn, int minAmountToSpawn, int maxAmountToSpawn)
	{
		this.biome = biome;
		this.chanceToSpawn = chanceToSpawn;
		this.minAmountToSpawn = minAmountToSpawn;
		this.maxAmountToSpawn = maxAmountToSpawn;
	}

	public static SpawnDetail[] getForBiomes(int chanceToSpawn, int minAmountToSpawn, int maxAmountToSpawn, BiomeGenBase... biomes)
	{
		List<SpawnDetail> spawns = JavaHelp.newArrayList();

		for (BiomeGenBase biome : biomes)
		{
			if (biome != null)
			{
				spawns.add(new SpawnDetail(biome, chanceToSpawn, minAmountToSpawn, maxAmountToSpawn));
			}
		}
		return spawns.toArray(new SpawnDetail[0]);
	}

	public static SpawnDetail[] getForAllBiomes(int chanceToSpawn, int minAmountToSpawn, int maxAmountToSpawn)
	{
		return getForBiomes(chanceToSpawn, minAmountToSpawn, maxAmountToSpawn, BiomeGenBase.getBiomeGenArray());
	}
}
