package main.extremeblocks.worldgen;

import java.util.Random;
import main.com.hk.eb.util.Builder;
import main.com.hk.eb.util.Rand;
import main.extremeblocks.Vars;
import main.extremeblocks.builder.Constructor;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenHouse extends WorldGenerator
{
	public WorldGenHouse()
	{
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		if (!Vars.genHouse || Rand.nextInt(30) != 0) return false;
		if (!new Builder(world, x, y, z).isAllAir(0, 0, 0, 3, 5, 3)) return false;
		Constructor c = new Constructor(world, x, y - 1, z);
		for (int i = 0; i < Constructor.getMaxStages(3) - 1; i++)
		{
			c.buildHouse(i);
		}
		return true;
	}
}
