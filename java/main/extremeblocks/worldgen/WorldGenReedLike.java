package main.extremeblocks.worldgen;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenReedLike extends WorldGenerator
{
	public final Block reed;
	public final int maxHeight;

	public WorldGenReedLike(Block reed, int maxHeight)
	{
		this.reed = reed;
		this.maxHeight = maxHeight;
	}

	public WorldGenReedLike(Block reed)
	{
		this(reed, 3);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		for (int l = 0; l < 20; ++l)
		{
			int i1 = x + rand.nextInt(4) - rand.nextInt(4);
			int j1 = y;
			int k1 = z + rand.nextInt(4) - rand.nextInt(4);

			if (world.isAirBlock(i1, y, k1) && (world.getBlock(i1 - 1, y - 1, k1).getMaterial() == Material.water || world.getBlock(i1 + 1, y - 1, k1).getMaterial() == Material.water || world.getBlock(i1, y - 1, k1 - 1).getMaterial() == Material.water || world.getBlock(i1, y - 1, k1 + 1).getMaterial() == Material.water))
			{
				int l1 = 2 + rand.nextInt(rand.nextInt(maxHeight) + 1);

				for (int i2 = 0; i2 < l1; ++i2)
				{
					if (reed.canBlockStay(world, i1, j1 + i2, k1))
					{
						world.setBlock(i1, j1 + i2, k1, reed, 0, 2);
					}
				}
			}
		}

		return true;
	}
}