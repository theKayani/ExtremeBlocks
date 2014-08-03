package main.extremeblocks.worldgen;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBlock extends WorldGenerator
{
	private Block blockToSpawn;
	private Block blockUnder;

	public WorldGenBlock(Block blockToSpawn)
	{
		this.blockToSpawn = blockToSpawn;
		this.blockUnder = Blocks.grass;
	}

	public WorldGenBlock(Block blockToSpawn, Block blockUnder)
	{
		this.blockToSpawn = blockToSpawn;
		this.blockUnder = blockUnder;
	}

	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		for (int l = 0; l < 64; ++l)
		{
			int i1 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
			int j1 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
			int k1 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

			if (par1World.isAirBlock(i1, j1, k1) && par1World.getBlock(i1, j1 - 1, k1) == blockUnder && blockToSpawn.canPlaceBlockAt(par1World, i1, j1, k1))
			{
				par1World.setBlock(i1, j1, k1, blockToSpawn, 0, 2);
				return true;
			}
		}

		return false;
	}
}