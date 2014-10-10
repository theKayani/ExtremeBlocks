package main.extremeblocks.tileentities;

import main.extremeblocks.util.IPlayerMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRewardBlock extends TileEntity implements IPlayerMessage
{
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
	}

	@Override
	public boolean onClickedOn(EntityPlayer player)
	{
		return true;
	}
}
