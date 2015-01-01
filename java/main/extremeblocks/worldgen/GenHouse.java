package main.extremeblocks.worldgen;

import main.com.hk.eb.util.Builder;
import main.com.hk.eb.util.Rand;
import main.extremeblocks.builder.Constructor;
import main.extremeblocks.worldgen.GenManager.Gen;

@Gen(chance = 1, name = "House")
public class GenHouse extends Generation
{
	@Override
	public boolean canGenerateAt(Builder helper)
	{
		if (Rand.nextInt(30) != 0) return false;
		if (!helper.isAllAir(0, 0, 0, 3, 5, 3)) return false;
		return true;
	}

	@Override
	public boolean generateStructure(Builder helper)
	{
		Constructor c = new Constructor(helper.world, helper.baseX, helper.baseY - 1, helper.baseZ);
		for (int i = 0; i < Constructor.getMaxStages(3) - 1; i++)
		{
			c.buildHouse(i);
		}
		return true;
	}
}
