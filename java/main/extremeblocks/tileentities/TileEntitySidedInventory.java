package main.extremeblocks.tileentities;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileEntitySidedInventory extends TileEntityInventory implements ISidedInventory
{
	public TileEntitySidedInventory(String name)
	{
		super(name);
	}

	@Override
	/**
	 * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
	 * block.
	 */
	public int[] getAccessibleSlotsFromSide(int side)
	{
		return getSlotsFor(ForgeDirection.getOrientation(side));
	}

	@Override
	/**
	 * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
	 * side
	 */
	public boolean canInsertItem(int slot, ItemStack stack, int side)
	{
		return isItemValidForSlot(slot, stack);
	}

	@Override
	/**
	 * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
	 * side
	 */
	public abstract boolean canExtractItem(int slot, ItemStack stack, int side);

	/**
	 * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
	 * block. Should have a -1 key to show any other sides that aren't added to the map
	 */
	protected abstract int[] getSlotsFor(ForgeDirection side);
}
