package main.extremeblocks.client.containers;

import main.extremeblocks.misc.IBattery;
import main.extremeblocks.tileentities.TileEntityCharger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCharger extends Container
{
	public final TileEntityCharger tile;

	public ContainerCharger(InventoryPlayer inv, TileEntityCharger tile)
	{
		this.tile = tile;

		for (int i = 0; i < 3; ++i)
		{
			addSlotToContainer(new Slot(tile, i, 62 + i * 18, 36));
		}

		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				addSlotToContainer(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		for (int i = 0; i < 9; ++i)
		{
			addSlotToContainer(new Slot(inv, i, 8 + i * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return tile.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int par2)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(par2);
		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 < 4)
			{
				if (!this.mergeItemStack(itemstack1, 4, 39, true))
				{
					return null;
				}
			}
			else if (itemstack.getItem() instanceof IBattery)
			{
				if (!this.mergeItemStack(itemstack1, 0, 4, false))
				{
					return null;
				}
			}

			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack) null);
			}
			else
			{
				slot.onSlotChanged();
			}
			if (itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}
			slot.onPickupFromSlot(player, itemstack1);
		}
		return itemstack;
	}
}
