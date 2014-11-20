package main.extremeblocks.worldgen;

import main.com.hk.eb.util.Builder;
import main.com.hk.eb.util.Rand;
import main.extremeblocks.Init;
import main.extremeblocks.Vars;
import main.extremeblocks.worldgen.GenManager.Gen;
import net.minecraft.item.Item;

@Gen(chance = 3, name = "Dried Tree")
public class WorldGenDriedTree extends Generation
{
	public WorldGenDriedTree()
	{
	}

	@Override
	public boolean canGenerateAt(Builder helper)
	{
		if (!Vars.genDriedTree || helper.getBiomeAtSpot().temperature < 1.0D) return false;
		for (int i = -4; i < 5; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				for (int k = -4; k < 5; k++)
				{
					if (!helper.isBlockReplaceable(i, j, k)) return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean generateStructure(Builder helper)
	{
		for (int l = 0; l < 5; ++l)
		{
			helper.setBlock(Init.emptied_log, 0, l, 0, Rand.getRandomMetadataOf(Item.getItemFromBlock(Init.emptied_log)));
		}
		return true;
	}
}