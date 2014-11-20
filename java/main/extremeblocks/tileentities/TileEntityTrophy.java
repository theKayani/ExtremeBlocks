package main.extremeblocks.tileentities;

import main.extremeblocks.blocks.BlockTrophy;
import main.extremeblocks.blocks.BlockTrophy.TrophyType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTrophy extends TileEntity
{
	public TrophyType type;

	@Override
	public void updateEntity()
	{
		if (type == null)
		{
			type = ((BlockTrophy) worldObj.getBlock(xCoord, yCoord, zCoord)).type;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		type = TrophyType.values()[nbt.getInteger("Type")];
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("Type", type.ordinal());
	}
}
