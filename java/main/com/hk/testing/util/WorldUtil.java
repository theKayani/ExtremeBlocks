package main.com.hk.testing.util;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WorldUtil
{
	public static void createBox(World worldObj, int x, int y, int z, Block block, int length, int width, int height, boolean isAir, boolean shouldStay)
	{
		if (MPUtil.isClientSide()) return;

		for (int i = 0; i < height + 1; i++)
		{
			createLayer(worldObj, x, y, z, block, length, width, isAir, shouldStay);
		}
	}

	public static void fillWithBlocks(World worldObj, int x, int y, int z, Block block, boolean isAir, boolean shouldStay, int radius)
	{
		if (MPUtil.isClientSide()) return;
		int i, j, k;

		for (i = -radius; i < radius + 1; i++)
		{
			for (j = -radius; j < radius + 1; j++)
			{
				for (k = -radius; k < radius + 1; k++)
				{
					if (shouldStay)
					{
						if (block.canBlockStay(worldObj, x + i, y + j, z + k)) WorldUtil.setBlock(worldObj, x + i, y + j, z + k, block);
						else continue;
					}

					if (isAir)
					{
						if (worldObj.isAirBlock(x + i, y + j, z + k)) WorldUtil.setBlock(worldObj, x + i, y + j, z + k, block);
						else continue;
					}

					if (!isAir && !shouldStay)
					{
						WorldUtil.setBlock(worldObj, x + i, y + j, z + k, block);
					}
				}
			}
		}
	}

	public static void createLayer(World worldObj, int x, int y, int z, Block block, int length, int width, boolean isAir, boolean shouldStay)
	{
		if (MPUtil.isClientSide()) return;

		if (MathHelp.isOdd(length)) length++;
		if (MathHelp.isOdd(width)) width++;

		length = length / 2;
		width = width / 2;
		int i, j, k;

		for (i = -length; i < length + 1; i++)
		{
			for (j = -width; j < width + 1; j++)
			{
				if (shouldStay)
				{
					if (block.canBlockStay(worldObj, x + i, y, z + j)) WorldUtil.setBlock(worldObj, x + i, y, z + j, block);
					else continue;
				}

				if (isAir)
				{
					if (worldObj.isAirBlock(x + i, y, z + j)) WorldUtil.setBlock(worldObj, x + i, y, z + j, block);
					else continue;
				}

				if (!isAir && !shouldStay)
				{
					worldObj.setBlock(x + i, y, z + j, block);
				}
			}
		}
	}

	public static void setBlockWithItemStack(World worldObj, int x, int y, int z, Block block, ItemStack... items)
	{
		if (MPUtil.isClientSide()) return;

		setBlock(worldObj, x, y, z, block);

		if (worldObj.getTileEntity(x, y, z) instanceof IInventory) StackHelper.addToInv((IInventory) worldObj.getTileEntity(x, y, z), items);
	}

	public static void setBlockWithItemStackInSlot(World worldObj, int x, int y, int z, Block block, ItemStack item, int slot)
	{
		if (MPUtil.isClientSide()) return;

		setBlock(worldObj, x, y, z, block);

		if (worldObj.getTileEntity(x, y, z) instanceof IInventory) StackHelper.addToInvSlot((IInventory) worldObj.getTileEntity(x, y, z), item, slot);
	}

	public static void setBlock(World worldObj, int x, int y, int z, Block block)
	{
		if (MPUtil.isClientSide()) return;

		worldObj.setBlock(x, y, z, block);
	}

	public static void setBlock(World worldObj, int x, int y, int z, Block block, int metadata)
	{
		if (MPUtil.isClientSide()) return;

		worldObj.setBlock(x, y, z, block, metadata, 2);
	}

	public static void setBlock(World worldObj, int x, int y, int z, Block block, int metadata, int flag)
	{
		if (MPUtil.isClientSide()) return;

		worldObj.setBlock(x, y, z, block, metadata, flag);
	}
}
