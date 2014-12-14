package main.extremeblocks.worldgen;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenOneTree extends WorldGenerator
{
	public static boolean spawned = false;

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		int l = par2Random.nextInt(3) + 5 + par2Random.nextInt(7);
		boolean flag = true;
		if (par4 >= 1 && par4 + l + 1 <= 256)
		{
			int j1;
			int k1;
			for (int i1 = par4; i1 <= par4 + 1 + l; ++i1)
			{
				byte b0 = 1;
				if (i1 == par4)
				{
					b0 = 0;
				}
				if (i1 >= par4 + 1 + l - 2)
				{
					b0 = 2;
				}
				for (j1 = par3 - b0; j1 <= par3 + b0 && flag; ++j1)
				{
					for (k1 = par5 - b0; k1 <= par5 + b0 && flag; ++k1)
					{
						if (i1 >= 0 && i1 < 256)
						{
							if (!isReplaceable(par1World, j1, i1, k1))
							{
								flag = false;
							}
						}
						else
						{
							flag = false;
						}
					}
				}
			}
			if (!flag) return false;
			else
			{
				Block block2 = par1World.getBlock(par3, par4 - 1, par5);
				boolean isSoil = block2.canSustainPlant(par1World, par3, par4 - 1, par5, ForgeDirection.UP, (BlockSapling) Blocks.sapling);
				if (isSoil && par4 < 256 - l - 1)
				{
					block2.onPlantGrow(par1World, par3, par4 - 1, par5, par3, par4, par5);
					int k2;
					for (k2 = par4 - 3 + l; k2 <= par4 + l; ++k2)
					{
						j1 = k2 - (par4 + l);
						k1 = 1 - j1 / 2;
						for (int l2 = par3 - k1; l2 <= par3 + k1; ++l2)
						{
							int l1 = l2 - par3;
							for (int i2 = par5 - k1; i2 <= par5 + k1; ++i2)
							{
								int j2 = i2 - par5;
								if (Math.abs(l1) != k1 || Math.abs(j2) != k1 || par2Random.nextInt(2) != 0 && j1 != 0)
								{
									Block block1 = par1World.getBlock(l2, k2, i2);
									if (block1.isAir(par1World, l2, k2, i2) || block1.isLeaves(par1World, l2, k2, i2))
									{
										setBlockAndNotifyAdequately(par1World, l2, k2, i2, Blocks.leaves, 0);
									}
								}
							}
						}
					}
					for (k2 = 0; k2 < l; ++k2)
					{
						Block block3 = par1World.getBlock(par3, par4 + k2, par5);
						if (block3.isAir(par1World, par3, par4 + k2, par5) || block3.isLeaves(par1World, par3, par4 + k2, par5))
						{
							setBlockAndNotifyAdequately(par1World, par3, par4 + k2, par5, Blocks.log, 0);
						}
					}
					return true;
				}
				else return false;
			}
		}
		else return false;
	}

	protected boolean isReplaceable(World world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		return block.isAir(world, x, y, z) || block.isLeaves(world, x, y, z) || block.isWood(world, x, y, z) || canReplace(block);
	}

	protected boolean canReplace(Block p_150523_1_)
	{
		return p_150523_1_.getMaterial() == Material.air || p_150523_1_.getMaterial() == Material.leaves || p_150523_1_ == Blocks.grass || p_150523_1_ == Blocks.dirt || p_150523_1_ == Blocks.log || p_150523_1_ == Blocks.log2 || p_150523_1_ == Blocks.sapling || p_150523_1_ == Blocks.vine;
	}
}