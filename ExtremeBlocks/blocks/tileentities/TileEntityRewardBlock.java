package main.extremeblocks.blocks.tileentities;

import main.com.hk.testing.util.MPUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class TileEntityRewardBlock extends TileEntity
{
	private int amount;

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);

		amount = nbt.getInteger("Amount");
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);

		nbt.setInteger("Amount", amount);
	}

	public boolean blockClicked(World world, EntityPlayer player)
	{
		if (MPUtil.isServerSide())
		{
			player.addChatComponentMessage(new ChatComponentTranslation("Reward Block Activated! Amount: " + amount++));
		}

		return true;
	}
}
