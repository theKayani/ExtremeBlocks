package main.extremeblocks.tileentities;

import main.extremeblocks.blocks.BlockMedal;
import main.extremeblocks.blocks.BlockMedal.MedalType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMedal extends TileEntity
{
	public MedalType type;

	@Override
	public void updateEntity()
	{
		if (type == null)
		{
			type = ((BlockMedal) worldObj.getBlock(xCoord, yCoord, zCoord)).type;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		type = MedalType.values()[nbt.getInteger("Type")];
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("Type", type.ordinal());
	}
}
