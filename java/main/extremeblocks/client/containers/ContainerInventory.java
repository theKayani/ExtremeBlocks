package main.extremeblocks.client.containers;

import java.util.List;
import main.extremeblocks.tileentities.TileEntityInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class ContainerInventory extends ContainerEB
{
	public final TileEntityInventory tile;

	public ContainerInventory(InventoryPlayer inventory, TileEntityInventory tile)
	{
		this.tile = tile;
		List<Slot> slots = tile.getSlots();
		for (int i = 0; i < slots.size(); i++)
		{
			addSlotToContainer(slots.get(i));
		}
		addPlayerInventory(inventory, getPlayerInvX(), getPlayerInvY());
	}

	public abstract int getPlayerInvX();

	public abstract int getPlayerInvY();

	public abstract TransferResult transferStackInSlot(int slot, ItemStack stack, Item item, int invStart, int hotbarStart, int invEnd, EntityPlayer player);

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return tile.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int s)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(s);
		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			TransferResult stuff = transferStackInSlot(s, itemstack1, itemstack1.getItem(), tile.getSizeInventory(), tile.getSizeInventory() + 27, tile.getSizeInventory() + 36, player);

			if (stuff != null)
			{
				if (!mergeItemStack(itemstack1, stuff.start, stuff.end, stuff.reverse)) return null;
			}

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

	public static class TransferResult
	{
		public int start, end;
		public boolean reverse;

		public TransferResult(int start, int end)
		{
			this(start, end, false);
		}

		public TransferResult(int start, int end, boolean reverse)
		{
			this.start = start;
			this.end = end;
			this.reverse = reverse;
		}
	}
}
