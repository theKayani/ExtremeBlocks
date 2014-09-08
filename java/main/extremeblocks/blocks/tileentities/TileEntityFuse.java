package main.extremeblocks.blocks.tileentities;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFuse extends TileEntity
{
	public int timer;

	public void updateEntity()
	{
		for (int i = -1; i < 2; i++)
		{
			for (int j = -1; j < 2; j++)
			{
				for (int k = -1; k < 2; k++)
				{
					Block block = worldObj.getBlock(xCoord + i, yCoord + j, zCoord + k);
					if (block == Blocks.fire)
					{
						timer++;
						if (timer >= 10)
						{
							worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.fire);
						}
					}
				}
			}
		}
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		timer = nbt.getInteger("Timer");
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("Timer", timer);
	}
}
