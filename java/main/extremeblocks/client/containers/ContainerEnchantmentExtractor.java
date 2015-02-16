package main.extremeblocks.client.containers;

import main.extremeblocks.tileentities.TileEntityEnchantmentExtractor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerEnchantmentExtractor extends Container
{
	private final TileEntityEnchantmentExtractor tile;

	public ContainerEnchantmentExtractor(InventoryPlayer inv, TileEntityEnchantmentExtractor tile)
	{
		this.tile = tile;
		addSlotToContainer(new Slot(tile, 0, 31, 36));
		addSlotToContainer(new Slot(tile, 1, 81, 16));
		addSlotToContainer(new Slot(tile, 2, 129, 36));

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

			if (par2 >= 3)
			{
				if (itemstack1.getItem() == Items.book)
				{
					if (!mergeItemStack(itemstack1, 2, 3, false)) return null;
				}
				else if (itemstack1.isItemEnchanted())
				{
					if (!mergeItemStack(itemstack1, 0, 1, false)) return null;
				}
				else if (par2 >= 30)
				{
					if (!mergeItemStack(itemstack1, 3, 30, false)) return null;
				}
				else if (par2 < 30)
				{
					if (!mergeItemStack(itemstack1, 30, 39, true)) return null;
				}
			}
			else if (!mergeItemStack(itemstack1, 3, inventorySlots.size(), true)) return null;

			if (itemstack1.stackSize == 0)
			{
				slot.putStack(null);
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
