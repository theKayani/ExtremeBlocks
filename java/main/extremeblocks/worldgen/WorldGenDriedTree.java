package main.extremeblocks.worldgen;

import java.util.Random;
import main.com.hk.eb.util.Builder;
import main.com.hk.eb.util.Rand;
import main.extremeblocks.Init;
import main.extremeblocks.Vars;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDriedTree extends WorldGenerator
{
	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		if (!Vars.genDriedTree) return false;
		Builder helper = new Builder(world, x, y, z);
		if (helper.getBiomeAtSpot().temperature < 1.0D) return false;
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
		for (int l = 0; l < 5; ++l)
		{
			helper.setBlock(Init.emptied_log, 0, l, 0, Rand.getRandomMetadataOf(Item.getItemFromBlock(Init.emptied_log)));
		}
		return true;
	}
}