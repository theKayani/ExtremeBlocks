package main.extremeblocks.worldgen;

import java.util.List;
import java.util.Random;
import main.com.hk.testing.util.Builder;
import main.com.hk.testing.util.JavaHelp;
import main.com.hk.testing.util.Rand;
import main.extremeblocks.Init;
import main.extremeblocks.Vars;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDriedTree extends WorldGenerator
{
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
					if (!helper.isBlockReplaceable(i, j, k))
					{
						return false;
					}
				}
			}
		}
		for (int l = 0; l < 5; ++l)
		{
			List<ItemStack> list = JavaHelp.newArrayList();
			Init.emptied_log.getSubBlocks(Item.getItemFromBlock(Init.emptied_log), Init.emptied_log.getCreativeTabToDisplayOn(), list);
			world.setBlock(x, y + l, z, Init.emptied_log, Rand.nextInt(list.size()), 2);
		}
		return true;
	}
}