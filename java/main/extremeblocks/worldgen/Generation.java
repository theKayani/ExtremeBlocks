package main.extremeblocks.worldgen;

import java.util.Random;
import main.com.hk.eb.util.Builder;
import main.com.hk.eb.util.Vector3I;
import main.extremeblocks.worldgen.GenManager.Gen;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class Generation extends WorldGenerator
{
	public final int chanceToSpawn;
	public final String name;

	public Generation()
	{
		chanceToSpawn = this.getClass().getAnnotation(Gen.class).chance();
		name = this.getClass().getAnnotation(Gen.class).name();
	}

	@Override
	public final boolean generate(World world, Random rand, int x, int y, int z)
	{
		Builder b = new Builder(world, x, y, z);
		return canGenerateAt(b) ? generateStructure(b) : false;
	}

	public Vector3I getSpawnPosition(World world, Random random, int chunkX, int chunkZ)
	{
		int x = chunkX + random.nextInt(16);
		int z = chunkZ + random.nextInt(16);
		int y = world.getTopSolidOrLiquidBlock(x, z);
		return new Vector3I(x, y, z);
	}

	public abstract boolean canGenerateAt(Builder helper);

	public abstract boolean generateStructure(Builder helper);
}
