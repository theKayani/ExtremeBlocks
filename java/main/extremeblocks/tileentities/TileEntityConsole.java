package main.extremeblocks.tileentities;

import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.util.IPlayerMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityConsole extends TileEntity implements IPlayerMessage
{
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		// Here
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		// Here
	}

	@Override
	public boolean onClickedOn(EntityPlayer player)
	{
		MPUtil.sendMessage("Does not work yet, SORRY!", player);
		return true;
	}
}
