package main.extremeblocks.tileentities;

import net.minecraft.item.ItemStack;

public class TileEntityTrash extends TileEntityInventory
{
	public TileEntityTrash()
	{
		super("Trash Can");
		inventory = new ItemStack[9];
	}

	@Override
	public boolean update()
	{
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
	{
		return true;
	}

	public boolean isPowered()
	{
		return worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
	}
}
