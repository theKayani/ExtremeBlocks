package main.extremeblocks.blocks.tileentities;

import main.com.hk.testing.util.MPUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class TileEntityConsole extends TileEntity
{

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);

		// Here
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);

		// Here
	}

	public boolean blockClicked(World world, EntityPlayer player)
	{
		if (MPUtil.isServerSide())
		{
			MPUtil.sendMessage("Does not Work SORRY!", player);
		}
		return true;
	}
}
