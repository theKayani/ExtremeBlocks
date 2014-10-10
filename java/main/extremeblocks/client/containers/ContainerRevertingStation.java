package main.extremeblocks.client.containers;

import main.extremeblocks.tileentities.TileEntityRevertingStation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerRevertingStation extends Container
{
	private final TileEntityRevertingStation tile;
	public static final int RESULT = 0;
	public static final int CRAFTING_START = 1;
	public static final int CRAFTING_END = 9;
	public static final int INV_START = 9;
	public static final int INV_END = 37;
	public static final int HOTBAR_START = 37;
	public static final int HOTBAR_END = 46;

	public ContainerRevertingStation(InventoryPlayer inventory, TileEntityRevertingStation tileEntity)
	{
		tile = tileEntity;
		addSlotToContainer(new Slot(tileEntity, 0, 34, 35));

		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				addSlotToContainer(new SlotRevertCraft(tileEntity, i + j * 3 + 1, 92 + i * 18, 17 + j * 18));
			}
		}

		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i)
		{
			addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entity)
	{
		return tile.isUseableByPlayer(entity);
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

			if (par2 <= CRAFTING_END)
			{
				if (!mergeItemStack(itemstack1, INV_START, HOTBAR_END, true)) return null;

				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (par2 > INV_START && par2 < INV_END)
			{
				if (!mergeItemStack(itemstack1, HOTBAR_START, HOTBAR_END, false)) return null;
			}
			else if (par2 >= HOTBAR_START && par2 < HOTBAR_END)
			{
				if (!mergeItemStack(itemstack1, RESULT, CRAFTING_START, false)) return null;
			}
			else if (!mergeItemStack(itemstack1, CRAFTING_START, HOTBAR_END, false)) return null;

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
