package ExtremeBlocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDriedTree extends WorldGenerator
{
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		for (int l = 0; l < 10; ++l)
		{
			int i1 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
			int j1 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
			int k1 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

			if (par1World.isAirBlock(i1, j1, k1))
			{
				int l1 = 1 + par2Random.nextInt(par2Random.nextInt(3) + 1);

				for (int i2 = 0; i2 < l1; ++i2)
				{
					if(par1World.getBiomeGenForCoords(i1, k1) == BiomeGenBase.desert)
					{
						par1World.setBlock(par3, par4, par5, ExtremeBlocksMain.EmptiedLog.blockID, 0, 2);
						par1World.setBlock(par3, par4 + 1, par5,ExtremeBlocksMain.EmptiedLog.blockID, 0, 2);
						par1World.setBlock(par3, par4 + 2, par5,ExtremeBlocksMain.EmptiedLog.blockID, 0, 2);
						par1World.setBlock(par3, par4 + 3, par5,ExtremeBlocksMain.EmptiedLog.blockID, 0, 2);
						par1World.setBlock(par3 + 1, par4 + 3, par5,ExtremeBlocksMain.EmptiedLog.blockID, 0, 2);
						par1World.setBlock(par3 + 2, par4 + 4, par5,ExtremeBlocksMain.EmptiedLog.blockID, 0, 2);
						par1World.setBlock(par3 + 3, par4 + 5, par5,ExtremeBlocksMain.EmptiedLog.blockID, 0, 2);
						par1World.setBlock(par3 - 1, par4 + 3, par5,ExtremeBlocksMain.EmptiedLog.blockID, 0, 2);
						par1World.setBlock(par3, par4 + 2, par5 - 1,ExtremeBlocksMain.EmptiedLog.blockID, 0, 2);
						par1World.setBlock(par3, par4 + 3, par5 - 1,ExtremeBlocksMain.EmptiedLog.blockID, 0, 2);
						par1World.setBlock(par3, par4 + 3, par5 - 3,ExtremeBlocksMain.EmptiedLog.blockID, 0, 2);
						par1World.setBlock(par3, par4 + 4, par5 - 2,ExtremeBlocksMain.EmptiedLog.blockID, 0, 2);
						par1World.setBlock(par3, par4 + 4, par5,ExtremeBlocksMain.EmptiedLog.blockID, 0, 2);
						par1World.setBlock(par3, par4 + 5, par5 + 1,ExtremeBlocksMain.EmptiedLog.blockID, 0, 2);
						par1World.setBlock(par3, par4 + 4, par5 + 2,ExtremeBlocksMain.EmptiedLog.blockID, 0, 2);
					}
				}
			}
		}

		return true;
	}
}
