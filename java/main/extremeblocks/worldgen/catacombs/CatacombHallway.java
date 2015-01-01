package main.extremeblocks.worldgen.catacombs;

import main.com.hk.eb.util.Builder;
import main.com.hk.eb.util.Rand;
import main.com.hk.eb.util.Vector3I;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class CatacombHallway
{
	private Builder helper;

	public CatacombHallway(Builder helper)
	{
		this.helper = helper;
	}

	public void create(boolean isUp, int x, int z)
	{
		helper.setOffset(new Vector3I(x * 9, 0, z * 9));
		CatacombPiece.clearArea(helper);
		if (isUp)
		{
			helper.setBlock(getWall(), 0, 0, 0);
			helper.setBlock(getWall(), 0, 0, 1);
			helper.setBlock(getWall(), 0, 0, 2);
			helper.setBlock(getWall(), 0, 0, 3);
			helper.setBlock(getWall(), 0, 0, 4);
			helper.setBlock(getWall(), 0, 0, 5);
			helper.setBlock(getWall(), 0, 0, 6);
			helper.setBlock(getWall(), 0, 0, 7);
			helper.setBlock(getWall(), 0, 0, 8);
			helper.setBlock(getWall(), 0, 1, 0);
			helper.setBlock(getWall(), 0, 1, 1);
			helper.setBlock(getWall(), 0, 1, 2);
			helper.setBlock(getWall(), 0, 1, 3);
			helper.setBlock(getWall(), 0, 1, 4);
			helper.setBlock(getWall(), 0, 1, 5);
			helper.setBlock(getWall(), 0, 1, 6);
			helper.setBlock(getWall(), 0, 1, 7);
			helper.setBlock(getWall(), 0, 1, 8);
			helper.setBlock(getWall(), 0, 2, 0);
			helper.setBlock(getWall(), 0, 2, 1);
			helper.setBlock(getWall(), 0, 2, 2);
			helper.setBlock(getWall(), 0, 2, 3);
			helper.setBlock(getWall(), 0, 2, 4);
			helper.setBlock(getWall(), 0, 2, 5);
			helper.setBlock(getWall(), 0, 2, 6);
			helper.setBlock(getWall(), 0, 2, 7);
			helper.setBlock(getWall(), 0, 2, 8);
			helper.setBlock(getWall(), 0, 3, 0);
			helper.setBlock(getWall(), 0, 3, 1);
			helper.setBlock(getWall(), 0, 3, 2);
			helper.setBlock(getWall(), 0, 3, 3);
			helper.setBlock(getWall(), 0, 3, 4);
			helper.setBlock(getWall(), 0, 3, 5);
			helper.setBlock(getWall(), 0, 3, 6);
			helper.setBlock(getWall(), 0, 3, 7);
			helper.setBlock(getWall(), 0, 3, 8);
			helper.setBlock(getWall(), 0, 4, 0);
			helper.setBlock(getWall(), 0, 4, 1);
			helper.setBlock(getWall(), 0, 4, 2);
			helper.setBlock(getWall(), 0, 4, 3);
			helper.setBlock(getWall(), 0, 4, 4);
			helper.setBlock(getWall(), 0, 4, 5);
			helper.setBlock(getWall(), 0, 4, 6);
			helper.setBlock(getWall(), 0, 4, 7);
			helper.setBlock(getWall(), 0, 4, 8);
			helper.setBlock(getWall(), 0, 5, 0);
			helper.setBlock(getWall(), 0, 5, 1);
			helper.setBlock(getWall(), 0, 5, 2);
			helper.setBlock(getWall(), 0, 5, 3);
			helper.setBlock(getWall(), 0, 5, 4);
			helper.setBlock(getWall(), 0, 5, 5);
			helper.setBlock(getWall(), 0, 5, 6);
			helper.setBlock(getWall(), 0, 5, 7);
			helper.setBlock(getWall(), 0, 5, 8);
			helper.setBlock(getWall(), 1, 0, 0);
			helper.setBlock(getWall(), 1, 0, 1);
			helper.setBlock(getWall(), 1, 0, 2);
			helper.setBlock(getWall(), 1, 0, 3);
			helper.setBlock(getWall(), 1, 0, 4);
			helper.setBlock(getWall(), 1, 0, 5);
			helper.setBlock(getWall(), 1, 0, 6);
			helper.setBlock(getWall(), 1, 0, 7);
			helper.setBlock(getWall(), 1, 0, 8);
			helper.setBlock(getWall(), 1, 1, 4);
			helper.setBlock(getWall(), 1, 2, 4);
			helper.setBlock(getWall(), 1, 3, 4);
			helper.setBlock(getWall(), 1, 4, 4);
			helper.setBlock(getWall(), 1, 5, 0);
			helper.setBlock(getWall(), 1, 5, 1);
			helper.setBlock(getWall(), 1, 5, 2);
			helper.setBlock(getWall(), 1, 5, 3);
			helper.setBlock(getWall(), 1, 5, 4);
			helper.setBlock(getWall(), 1, 5, 5);
			helper.setBlock(getWall(), 1, 5, 6);
			helper.setBlock(getWall(), 1, 5, 7);
			helper.setBlock(getWall(), 1, 5, 8);
			helper.setBlock(getWall(), 2, 0, 0);
			helper.setBlock(getWall(), 2, 0, 1);
			helper.setBlock(getWall(), 2, 0, 2);
			helper.setBlock(getWall(), 2, 0, 3);
			helper.setBlock(getWall(), 2, 0, 4);
			helper.setBlock(getWall(), 2, 0, 5);
			helper.setBlock(getWall(), 2, 0, 6);
			helper.setBlock(getWall(), 2, 0, 7);
			helper.setBlock(getWall(), 2, 0, 8);
			helper.setBlock(getWall(), 2, 5, 0);
			helper.setBlock(getWall(), 2, 5, 1);
			helper.setBlock(getWall(), 2, 5, 2);
			helper.setBlock(getWall(), 2, 5, 3);
			helper.setBlock(getWall(), 2, 5, 4);
			helper.setBlock(getWall(), 2, 5, 5);
			helper.setBlock(getWall(), 2, 5, 6);
			helper.setBlock(getWall(), 2, 5, 7);
			helper.setBlock(getWall(), 2, 5, 8);
			helper.setBlock(getWall(), 3, 0, 0);
			helper.setBlock(getWall(), 3, 0, 1);
			helper.setBlock(getWall(), 3, 0, 2);
			helper.setBlock(getWall(), 3, 0, 3);
			helper.setBlock(getWall(), 3, 0, 4);
			helper.setBlock(getWall(), 3, 0, 5);
			helper.setBlock(getWall(), 3, 0, 6);
			helper.setBlock(getWall(), 3, 0, 7);
			helper.setBlock(getWall(), 3, 0, 8);
			helper.setBlock(getWall(), 3, 5, 0);
			helper.setBlock(getWall(), 3, 5, 1);
			helper.setBlock(getWall(), 3, 5, 2);
			helper.setBlock(getWall(), 3, 5, 3);
			helper.setBlock(getWall(), 3, 5, 4);
			helper.setBlock(getWall(), 3, 5, 5);
			helper.setBlock(getWall(), 3, 5, 6);
			helper.setBlock(getWall(), 3, 5, 7);
			helper.setBlock(getWall(), 3, 5, 8);
			helper.setBlock(getWall(), 4, 0, 0);
			helper.setBlock(getWall(), 4, 0, 1);
			helper.setBlock(getWall(), 4, 0, 2);
			helper.setBlock(getWall(), 4, 0, 3);
			helper.setBlock(getWall(), 4, 0, 4);
			helper.setBlock(getWall(), 4, 0, 5);
			helper.setBlock(getWall(), 4, 0, 6);
			helper.setBlock(getWall(), 4, 0, 7);
			helper.setBlock(getWall(), 4, 0, 8);
			helper.setBlock(getWall(), 4, 5, 0);
			helper.setBlock(getWall(), 4, 5, 1);
			helper.setBlock(getWall(), 4, 5, 2);
			helper.setBlock(getWall(), 4, 5, 3);
			helper.setBlock(getWall(), 4, 5, 4);
			helper.setBlock(getWall(), 4, 5, 5);
			helper.setBlock(getWall(), 4, 5, 6);
			helper.setBlock(getWall(), 4, 5, 7);
			helper.setBlock(getWall(), 4, 5, 8);
			helper.setBlock(getWall(), 5, 0, 0);
			helper.setBlock(getWall(), 5, 0, 1);
			helper.setBlock(getWall(), 5, 0, 2);
			helper.setBlock(getWall(), 5, 0, 3);
			helper.setBlock(getWall(), 5, 0, 4);
			helper.setBlock(getWall(), 5, 0, 5);
			helper.setBlock(getWall(), 5, 0, 6);
			helper.setBlock(getWall(), 5, 0, 7);
			helper.setBlock(getWall(), 5, 0, 8);
			helper.setBlock(getWall(), 5, 5, 0);
			helper.setBlock(getWall(), 5, 5, 1);
			helper.setBlock(getWall(), 5, 5, 2);
			helper.setBlock(getWall(), 5, 5, 3);
			helper.setBlock(getWall(), 5, 5, 4);
			helper.setBlock(getWall(), 5, 5, 5);
			helper.setBlock(getWall(), 5, 5, 6);
			helper.setBlock(getWall(), 5, 5, 7);
			helper.setBlock(getWall(), 5, 5, 8);
			helper.setBlock(getWall(), 6, 0, 0);
			helper.setBlock(getWall(), 6, 0, 1);
			helper.setBlock(getWall(), 6, 0, 2);
			helper.setBlock(getWall(), 6, 0, 3);
			helper.setBlock(getWall(), 6, 0, 4);
			helper.setBlock(getWall(), 6, 0, 5);
			helper.setBlock(getWall(), 6, 0, 6);
			helper.setBlock(getWall(), 6, 0, 7);
			helper.setBlock(getWall(), 6, 0, 8);
			helper.setBlock(getWall(), 6, 5, 0);
			helper.setBlock(getWall(), 6, 5, 1);
			helper.setBlock(getWall(), 6, 5, 2);
			helper.setBlock(getWall(), 6, 5, 3);
			helper.setBlock(getWall(), 6, 5, 4);
			helper.setBlock(getWall(), 6, 5, 5);
			helper.setBlock(getWall(), 6, 5, 6);
			helper.setBlock(getWall(), 6, 5, 7);
			helper.setBlock(getWall(), 6, 5, 8);
			helper.setBlock(getWall(), 7, 0, 0);
			helper.setBlock(getWall(), 7, 0, 1);
			helper.setBlock(getWall(), 7, 0, 2);
			helper.setBlock(getWall(), 7, 0, 3);
			helper.setBlock(getWall(), 7, 0, 4);
			helper.setBlock(getWall(), 7, 0, 5);
			helper.setBlock(getWall(), 7, 0, 6);
			helper.setBlock(getWall(), 7, 0, 7);
			helper.setBlock(getWall(), 7, 0, 8);
			helper.setBlock(getWall(), 7, 1, 4);
			helper.setBlock(getWall(), 7, 2, 4);
			helper.setBlock(getWall(), 7, 3, 4);
			helper.setBlock(getWall(), 7, 4, 4);
			helper.setBlock(getWall(), 7, 5, 0);
			helper.setBlock(getWall(), 7, 5, 1);
			helper.setBlock(getWall(), 7, 5, 2);
			helper.setBlock(getWall(), 7, 5, 3);
			helper.setBlock(getWall(), 7, 5, 4);
			helper.setBlock(getWall(), 7, 5, 5);
			helper.setBlock(getWall(), 7, 5, 6);
			helper.setBlock(getWall(), 7, 5, 7);
			helper.setBlock(getWall(), 7, 5, 8);
			helper.setBlock(getWall(), 8, 0, 0);
			helper.setBlock(getWall(), 8, 0, 1);
			helper.setBlock(getWall(), 8, 0, 2);
			helper.setBlock(getWall(), 8, 0, 3);
			helper.setBlock(getWall(), 8, 0, 4);
			helper.setBlock(getWall(), 8, 0, 5);
			helper.setBlock(getWall(), 8, 0, 6);
			helper.setBlock(getWall(), 8, 0, 7);
			helper.setBlock(getWall(), 8, 0, 8);
			helper.setBlock(getWall(), 8, 1, 0);
			helper.setBlock(getWall(), 8, 1, 1);
			helper.setBlock(getWall(), 8, 1, 2);
			helper.setBlock(getWall(), 8, 1, 3);
			helper.setBlock(getWall(), 8, 1, 4);
			helper.setBlock(getWall(), 8, 1, 5);
			helper.setBlock(getWall(), 8, 1, 6);
			helper.setBlock(getWall(), 8, 1, 7);
			helper.setBlock(getWall(), 8, 1, 8);
			helper.setBlock(getWall(), 8, 2, 0);
			helper.setBlock(getWall(), 8, 2, 1);
			helper.setBlock(getWall(), 8, 2, 2);
			helper.setBlock(getWall(), 8, 2, 3);
			helper.setBlock(getWall(), 8, 2, 4);
			helper.setBlock(getWall(), 8, 2, 5);
			helper.setBlock(getWall(), 8, 2, 6);
			helper.setBlock(getWall(), 8, 2, 7);
			helper.setBlock(getWall(), 8, 2, 8);
			helper.setBlock(getWall(), 8, 3, 0);
			helper.setBlock(getWall(), 8, 3, 1);
			helper.setBlock(getWall(), 8, 3, 2);
			helper.setBlock(getWall(), 8, 3, 3);
			helper.setBlock(getWall(), 8, 3, 4);
			helper.setBlock(getWall(), 8, 3, 5);
			helper.setBlock(getWall(), 8, 3, 6);
			helper.setBlock(getWall(), 8, 3, 7);
			helper.setBlock(getWall(), 8, 3, 8);
			helper.setBlock(getWall(), 8, 4, 0);
			helper.setBlock(getWall(), 8, 4, 1);
			helper.setBlock(getWall(), 8, 4, 2);
			helper.setBlock(getWall(), 8, 4, 3);
			helper.setBlock(getWall(), 8, 4, 4);
			helper.setBlock(getWall(), 8, 4, 5);
			helper.setBlock(getWall(), 8, 4, 6);
			helper.setBlock(getWall(), 8, 4, 7);
			helper.setBlock(getWall(), 8, 4, 8);
			helper.setBlock(getWall(), 8, 5, 0);
			helper.setBlock(getWall(), 8, 5, 1);
			helper.setBlock(getWall(), 8, 5, 2);
			helper.setBlock(getWall(), 8, 5, 3);
			helper.setBlock(getWall(), 8, 5, 4);
			helper.setBlock(getWall(), 8, 5, 5);
			helper.setBlock(getWall(), 8, 5, 6);
			helper.setBlock(getWall(), 8, 5, 7);
			helper.setBlock(getWall(), 8, 5, 8);
			helper.setBlock(Blocks.stone_stairs, 1, 1, 3, 2);
			helper.setBlock(Blocks.stone_stairs, 1, 1, 5, 3);
			helper.setBlock(Blocks.stone_stairs, 1, 4, 3, 6);
			helper.setBlock(Blocks.stone_stairs, 1, 4, 5, 7);
			helper.setBlock(Blocks.stone_stairs, 2, 1, 4, 1);
			helper.setBlock(Blocks.stone_stairs, 2, 4, 4, 5);
			helper.setBlock(Blocks.stone_stairs, 6, 1, 4);
			helper.setBlock(Blocks.stone_stairs, 6, 4, 4, 4);
			helper.setBlock(Blocks.stone_stairs, 7, 1, 3, 2);
			helper.setBlock(Blocks.stone_stairs, 7, 1, 5, 3);
			helper.setBlock(Blocks.stone_stairs, 7, 4, 3, 6);
			helper.setBlock(Blocks.stone_stairs, 7, 4, 5, 7);
			if (Rand.nextInt(4) == 0)
			{
				helper.setBlock(Blocks.torch, 1, 3, 3, 4);
			}
			if (Rand.nextInt(4) == 0)
			{
				helper.setBlock(Blocks.torch, 1, 3, 5, 3);
			}
			if (Rand.nextInt(4) == 0)
			{
				helper.setBlock(Blocks.torch, 7, 3, 3, 4);
			}
			if (Rand.nextInt(4) == 0)
			{
				helper.setBlock(Blocks.torch, 7, 3, 5, 3);
			}
		}
		else
		{
			helper.setBlock(getWall(), 0, 0, 0);
			helper.setBlock(getWall(), 0, 0, 1);
			helper.setBlock(getWall(), 0, 0, 2);
			helper.setBlock(getWall(), 0, 0, 3);
			helper.setBlock(getWall(), 0, 0, 4);
			helper.setBlock(getWall(), 0, 0, 5);
			helper.setBlock(getWall(), 0, 0, 6);
			helper.setBlock(getWall(), 0, 0, 7);
			helper.setBlock(getWall(), 0, 0, 8);
			helper.setBlock(getWall(), 0, 1, 0);
			helper.setBlock(getWall(), 0, 1, 8);
			helper.setBlock(getWall(), 0, 2, 0);
			helper.setBlock(getWall(), 0, 2, 8);
			helper.setBlock(getWall(), 0, 3, 0);
			helper.setBlock(getWall(), 0, 3, 8);
			helper.setBlock(getWall(), 0, 4, 0);
			helper.setBlock(getWall(), 0, 4, 8);
			helper.setBlock(getWall(), 0, 5, 0);
			helper.setBlock(getWall(), 0, 5, 1);
			helper.setBlock(getWall(), 0, 5, 2);
			helper.setBlock(getWall(), 0, 5, 3);
			helper.setBlock(getWall(), 0, 5, 4);
			helper.setBlock(getWall(), 0, 5, 5);
			helper.setBlock(getWall(), 0, 5, 6);
			helper.setBlock(getWall(), 0, 5, 7);
			helper.setBlock(getWall(), 0, 5, 8);
			helper.setBlock(getWall(), 1, 0, 0);
			helper.setBlock(getWall(), 1, 0, 1);
			helper.setBlock(getWall(), 1, 0, 2);
			helper.setBlock(getWall(), 1, 0, 3);
			helper.setBlock(getWall(), 1, 0, 4);
			helper.setBlock(getWall(), 1, 0, 5);
			helper.setBlock(getWall(), 1, 0, 6);
			helper.setBlock(getWall(), 1, 0, 7);
			helper.setBlock(getWall(), 1, 0, 8);
			helper.setBlock(getWall(), 1, 1, 0);
			helper.setBlock(getWall(), 1, 1, 8);
			helper.setBlock(getWall(), 1, 2, 0);
			helper.setBlock(getWall(), 1, 2, 8);
			helper.setBlock(getWall(), 1, 3, 0);
			helper.setBlock(getWall(), 1, 3, 8);
			helper.setBlock(getWall(), 1, 4, 0);
			helper.setBlock(getWall(), 1, 4, 8);
			helper.setBlock(getWall(), 1, 5, 0);
			helper.setBlock(getWall(), 1, 5, 1);
			helper.setBlock(getWall(), 1, 5, 2);
			helper.setBlock(getWall(), 1, 5, 3);
			helper.setBlock(getWall(), 1, 5, 4);
			helper.setBlock(getWall(), 1, 5, 5);
			helper.setBlock(getWall(), 1, 5, 6);
			helper.setBlock(getWall(), 1, 5, 7);
			helper.setBlock(getWall(), 1, 5, 8);
			helper.setBlock(getWall(), 2, 0, 0);
			helper.setBlock(getWall(), 2, 0, 1);
			helper.setBlock(getWall(), 2, 0, 2);
			helper.setBlock(getWall(), 2, 0, 3);
			helper.setBlock(getWall(), 2, 0, 4);
			helper.setBlock(getWall(), 2, 0, 5);
			helper.setBlock(getWall(), 2, 0, 6);
			helper.setBlock(getWall(), 2, 0, 7);
			helper.setBlock(getWall(), 2, 0, 8);
			helper.setBlock(getWall(), 2, 1, 0);
			helper.setBlock(getWall(), 2, 1, 8);
			helper.setBlock(getWall(), 2, 2, 0);
			helper.setBlock(getWall(), 2, 2, 8);
			helper.setBlock(getWall(), 2, 3, 0);
			helper.setBlock(getWall(), 2, 3, 8);
			helper.setBlock(getWall(), 2, 4, 0);
			helper.setBlock(getWall(), 2, 4, 8);
			helper.setBlock(getWall(), 2, 5, 0);
			helper.setBlock(getWall(), 2, 5, 1);
			helper.setBlock(getWall(), 2, 5, 2);
			helper.setBlock(getWall(), 2, 5, 3);
			helper.setBlock(getWall(), 2, 5, 4);
			helper.setBlock(getWall(), 2, 5, 5);
			helper.setBlock(getWall(), 2, 5, 6);
			helper.setBlock(getWall(), 2, 5, 7);
			helper.setBlock(getWall(), 2, 5, 8);
			helper.setBlock(getWall(), 3, 0, 0);
			helper.setBlock(getWall(), 3, 0, 1);
			helper.setBlock(getWall(), 3, 0, 2);
			helper.setBlock(getWall(), 3, 0, 3);
			helper.setBlock(getWall(), 3, 0, 4);
			helper.setBlock(getWall(), 3, 0, 5);
			helper.setBlock(getWall(), 3, 0, 6);
			helper.setBlock(getWall(), 3, 0, 7);
			helper.setBlock(getWall(), 3, 0, 8);
			helper.setBlock(getWall(), 3, 1, 0);
			helper.setBlock(getWall(), 3, 1, 8);
			helper.setBlock(getWall(), 3, 2, 0);
			helper.setBlock(getWall(), 3, 2, 8);
			helper.setBlock(getWall(), 3, 3, 0);
			helper.setBlock(getWall(), 3, 3, 8);
			helper.setBlock(getWall(), 3, 4, 0);
			helper.setBlock(getWall(), 3, 4, 8);
			helper.setBlock(getWall(), 3, 5, 0);
			helper.setBlock(getWall(), 3, 5, 1);
			helper.setBlock(getWall(), 3, 5, 2);
			helper.setBlock(getWall(), 3, 5, 3);
			helper.setBlock(getWall(), 3, 5, 4);
			helper.setBlock(getWall(), 3, 5, 5);
			helper.setBlock(getWall(), 3, 5, 6);
			helper.setBlock(getWall(), 3, 5, 7);
			helper.setBlock(getWall(), 3, 5, 8);
			helper.setBlock(getWall(), 4, 0, 0);
			helper.setBlock(getWall(), 4, 0, 1);
			helper.setBlock(getWall(), 4, 0, 2);
			helper.setBlock(getWall(), 4, 0, 3);
			helper.setBlock(getWall(), 4, 0, 4);
			helper.setBlock(getWall(), 4, 0, 5);
			helper.setBlock(getWall(), 4, 0, 6);
			helper.setBlock(getWall(), 4, 0, 7);
			helper.setBlock(getWall(), 4, 0, 8);
			helper.setBlock(getWall(), 4, 1, 0);
			helper.setBlock(getWall(), 4, 1, 1);
			helper.setBlock(getWall(), 4, 1, 7);
			helper.setBlock(getWall(), 4, 1, 8);
			helper.setBlock(getWall(), 4, 2, 0);
			helper.setBlock(getWall(), 4, 2, 1);
			helper.setBlock(getWall(), 4, 2, 7);
			helper.setBlock(getWall(), 4, 2, 8);
			helper.setBlock(getWall(), 4, 3, 0);
			helper.setBlock(getWall(), 4, 3, 1);
			helper.setBlock(getWall(), 4, 3, 7);
			helper.setBlock(getWall(), 4, 3, 8);
			helper.setBlock(getWall(), 4, 4, 0);
			helper.setBlock(getWall(), 4, 4, 1);
			helper.setBlock(getWall(), 4, 4, 7);
			helper.setBlock(getWall(), 4, 4, 8);
			helper.setBlock(getWall(), 4, 5, 0);
			helper.setBlock(getWall(), 4, 5, 1);
			helper.setBlock(getWall(), 4, 5, 2);
			helper.setBlock(getWall(), 4, 5, 3);
			helper.setBlock(getWall(), 4, 5, 4);
			helper.setBlock(getWall(), 4, 5, 5);
			helper.setBlock(getWall(), 4, 5, 6);
			helper.setBlock(getWall(), 4, 5, 7);
			helper.setBlock(getWall(), 4, 5, 8);
			helper.setBlock(getWall(), 5, 0, 0);
			helper.setBlock(getWall(), 5, 0, 1);
			helper.setBlock(getWall(), 5, 0, 2);
			helper.setBlock(getWall(), 5, 0, 3);
			helper.setBlock(getWall(), 5, 0, 4);
			helper.setBlock(getWall(), 5, 0, 5);
			helper.setBlock(getWall(), 5, 0, 6);
			helper.setBlock(getWall(), 5, 0, 7);
			helper.setBlock(getWall(), 5, 0, 8);
			helper.setBlock(getWall(), 5, 1, 0);
			helper.setBlock(getWall(), 5, 1, 8);
			helper.setBlock(getWall(), 5, 2, 0);
			helper.setBlock(getWall(), 5, 2, 8);
			helper.setBlock(getWall(), 5, 3, 0);
			helper.setBlock(getWall(), 5, 3, 8);
			helper.setBlock(getWall(), 5, 4, 0);
			helper.setBlock(getWall(), 5, 4, 8);
			helper.setBlock(getWall(), 5, 5, 0);
			helper.setBlock(getWall(), 5, 5, 1);
			helper.setBlock(getWall(), 5, 5, 2);
			helper.setBlock(getWall(), 5, 5, 3);
			helper.setBlock(getWall(), 5, 5, 4);
			helper.setBlock(getWall(), 5, 5, 5);
			helper.setBlock(getWall(), 5, 5, 6);
			helper.setBlock(getWall(), 5, 5, 7);
			helper.setBlock(getWall(), 5, 5, 8);
			helper.setBlock(getWall(), 6, 0, 0);
			helper.setBlock(getWall(), 6, 0, 1);
			helper.setBlock(getWall(), 6, 0, 2);
			helper.setBlock(getWall(), 6, 0, 3);
			helper.setBlock(getWall(), 6, 0, 4);
			helper.setBlock(getWall(), 6, 0, 5);
			helper.setBlock(getWall(), 6, 0, 6);
			helper.setBlock(getWall(), 6, 0, 7);
			helper.setBlock(getWall(), 6, 0, 8);
			helper.setBlock(getWall(), 6, 1, 0);
			helper.setBlock(getWall(), 6, 1, 8);
			helper.setBlock(getWall(), 6, 2, 0);
			helper.setBlock(getWall(), 6, 2, 8);
			helper.setBlock(getWall(), 6, 3, 0);
			helper.setBlock(getWall(), 6, 3, 8);
			helper.setBlock(getWall(), 6, 4, 0);
			helper.setBlock(getWall(), 6, 4, 8);
			helper.setBlock(getWall(), 6, 5, 0);
			helper.setBlock(getWall(), 6, 5, 1);
			helper.setBlock(getWall(), 6, 5, 2);
			helper.setBlock(getWall(), 6, 5, 3);
			helper.setBlock(getWall(), 6, 5, 4);
			helper.setBlock(getWall(), 6, 5, 5);
			helper.setBlock(getWall(), 6, 5, 6);
			helper.setBlock(getWall(), 6, 5, 7);
			helper.setBlock(getWall(), 6, 5, 8);
			helper.setBlock(getWall(), 7, 0, 0);
			helper.setBlock(getWall(), 7, 0, 1);
			helper.setBlock(getWall(), 7, 0, 2);
			helper.setBlock(getWall(), 7, 0, 3);
			helper.setBlock(getWall(), 7, 0, 4);
			helper.setBlock(getWall(), 7, 0, 5);
			helper.setBlock(getWall(), 7, 0, 6);
			helper.setBlock(getWall(), 7, 0, 7);
			helper.setBlock(getWall(), 7, 0, 8);
			helper.setBlock(getWall(), 7, 1, 0);
			helper.setBlock(getWall(), 7, 1, 8);
			helper.setBlock(getWall(), 7, 2, 0);
			helper.setBlock(getWall(), 7, 2, 8);
			helper.setBlock(getWall(), 7, 3, 0);
			helper.setBlock(getWall(), 7, 3, 8);
			helper.setBlock(getWall(), 7, 4, 0);
			helper.setBlock(getWall(), 7, 4, 8);
			helper.setBlock(getWall(), 7, 5, 0);
			helper.setBlock(getWall(), 7, 5, 1);
			helper.setBlock(getWall(), 7, 5, 2);
			helper.setBlock(getWall(), 7, 5, 3);
			helper.setBlock(getWall(), 7, 5, 4);
			helper.setBlock(getWall(), 7, 5, 5);
			helper.setBlock(getWall(), 7, 5, 6);
			helper.setBlock(getWall(), 7, 5, 7);
			helper.setBlock(getWall(), 7, 5, 8);
			helper.setBlock(getWall(), 8, 0, 0);
			helper.setBlock(getWall(), 8, 0, 1);
			helper.setBlock(getWall(), 8, 0, 2);
			helper.setBlock(getWall(), 8, 0, 3);
			helper.setBlock(getWall(), 8, 0, 4);
			helper.setBlock(getWall(), 8, 0, 5);
			helper.setBlock(getWall(), 8, 0, 6);
			helper.setBlock(getWall(), 8, 0, 7);
			helper.setBlock(getWall(), 8, 0, 8);
			helper.setBlock(getWall(), 8, 1, 0);
			helper.setBlock(getWall(), 8, 1, 8);
			helper.setBlock(getWall(), 8, 2, 0);
			helper.setBlock(getWall(), 8, 2, 8);
			helper.setBlock(getWall(), 8, 3, 0);
			helper.setBlock(getWall(), 8, 3, 8);
			helper.setBlock(getWall(), 8, 4, 0);
			helper.setBlock(getWall(), 8, 4, 8);
			helper.setBlock(getWall(), 8, 5, 0);
			helper.setBlock(getWall(), 8, 5, 1);
			helper.setBlock(getWall(), 8, 5, 2);
			helper.setBlock(getWall(), 8, 5, 3);
			helper.setBlock(getWall(), 8, 5, 4);
			helper.setBlock(getWall(), 8, 5, 5);
			helper.setBlock(getWall(), 8, 5, 6);
			helper.setBlock(getWall(), 8, 5, 7);
			helper.setBlock(getWall(), 8, 5, 8);
			helper.setBlock(Blocks.stone_stairs, 3, 1, 1);
			helper.setBlock(Blocks.stone_stairs, 3, 1, 7);
			helper.setBlock(Blocks.stone_stairs, 3, 4, 1, 4);
			helper.setBlock(Blocks.stone_stairs, 3, 4, 7, 4);
			helper.setBlock(Blocks.stone_stairs, 4, 1, 2, 3);
			helper.setBlock(Blocks.stone_stairs, 4, 1, 6, 2);
			helper.setBlock(Blocks.stone_stairs, 4, 4, 2, 7);
			helper.setBlock(Blocks.stone_stairs, 4, 4, 6, 6);
			helper.setBlock(Blocks.stone_stairs, 5, 1, 1, 1);
			helper.setBlock(Blocks.stone_stairs, 5, 1, 7, 1);
			helper.setBlock(Blocks.stone_stairs, 5, 4, 1, 5);
			helper.setBlock(Blocks.stone_stairs, 5, 4, 7, 5);
			if (Rand.nextInt(4) == 0)
			{
				helper.setBlock(Blocks.torch, 3, 3, 1, 2);
			}
			if (Rand.nextInt(4) == 0)
			{
				helper.setBlock(Blocks.torch, 3, 3, 7, 2);
			}
			if (Rand.nextInt(4) == 0)
			{
				helper.setBlock(Blocks.torch, 5, 3, 1, 1);
			}
			if (Rand.nextInt(4) == 0)
			{
				helper.setBlock(Blocks.torch, 5, 3, 7, 1);
			}
		}
		helper.removeOffset();
	}

	private Block getWall()
	{
		return Rand.nextBoolean() ? Blocks.cobblestone : Blocks.mossy_cobblestone;
	}
}
