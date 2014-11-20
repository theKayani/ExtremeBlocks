package main.extremeblocks.worldgen;

import java.util.Random;
import main.com.hk.eb.util.Builder;
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

	public abstract boolean canGenerateAt(Builder helper);

	public abstract boolean generateStructure(Builder helper);
}
