package main.extremeblocks.client.containers;

import main.extremeblocks.tileentities.TileEntityTrash;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTrash extends Container
{
	private final TileEntityTrash tiny;

	public ContainerTrash(IInventory par1IInventory, TileEntityTrash par2TileEntityTiny)
	{
		tiny = par2TileEntityTiny;
		int i;
		int j;
		for (i = 0; i < 3; ++i)
		{
			for (j = 0; j < 3; ++j)
			{
				addSlotToContainer(new Slot(par2TileEntityTiny, j + i * 3, 92 + j * 18, 17 + i * 18));
			}
		}
		for (i = 0; i < 3; ++i)
		{
			for (j = 0; j < 9; ++j)
			{
				addSlotToContainer(new Slot(par1IInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		for (i = 0; i < 9; ++i)
		{
			addSlotToContainer(new Slot(par1IInventory, i, 8 + i * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return tiny.isUseableByPlayer(par1EntityPlayer);
	}

	/**
	 * par2 = The Slot that is clicked!
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int par2)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(par2);
		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (par2 < tiny.getSizeInventory())
			{
				if (!mergeItemStack(itemstack1, tiny.getSizeInventory(), inventorySlots.size(), true)) return null;
			}
			else if (!mergeItemStack(itemstack1, 0, tiny.getSizeInventory(), false)) return null;
			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack) null);
			}
			else
			{
				slot.onSlotChanged();
			}
			if (itemstack1.stackSize == itemstack.stackSize) return null;
			slot.onPickupFromSlot(player, itemstack1);
		}
		return itemstack;
	}
}
