package main.extremeblocks.blocks.tileentities.containers;

import main.extremeblocks.blocks.tileentities.TileEntityStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerStorage extends Container
{
	private TileEntityStorage tiny;

	public ContainerStorage(IInventory par1IInventory, TileEntityStorage par2TileEntityTiny)
	{
		this.tiny = par2TileEntityTiny;
		int i;
		int j;

		for (i = 0; i < this.tiny.addSlotsToContainer().length; i++)
		{
			this.addSlotToContainer(this.tiny.addSlotsToContainer()[i]);
		}

		for (i = 0; i < 3; ++i)
		{
			for (j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(par1IInventory, j + i * 9 + 9, (tiny.getXSize() - 176) + 8 + j * 18, (tiny.getYSize() - 166) + 84 + i * 18));
			}
		}

		for (i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(par1IInventory, i, (tiny.getXSize() - 176) + 8 + i * 18, (tiny.getYSize() - 166) + 142));
		}
	}

	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return this.tiny.isUseableByPlayer(par1EntityPlayer);
	}

	/**
	 * par2 = The Slot that is clicked!
	 */
	public ItemStack transferStackInSlot(EntityPlayer player, int par2)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 < this.tiny.getSizeInventory())
			{
				if (!this.mergeItemStack(itemstack1, this.tiny.getSizeInventory(), this.inventorySlots.size(), true))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 0, this.tiny.getSizeInventory(), false))
			{
				return null;
			}

			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack) null);
			}
			else
			{
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}
}
