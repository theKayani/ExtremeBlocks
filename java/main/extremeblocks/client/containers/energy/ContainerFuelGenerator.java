package main.extremeblocks.client.containers.energy;

import main.extremeblocks.client.containers.ContainerInventory;
import main.extremeblocks.tileentities.energy.TileEntityFuelGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import cofh.api.energy.IEnergyContainerItem;

public class ContainerFuelGenerator extends ContainerInventory
{
	public ContainerFuelGenerator(InventoryPlayer inventory, TileEntityFuelGenerator tile)
	{
		super(inventory, tile);
	}

	@Override
	public int getPlayerInvX()
	{
		return 8;
	}

	@Override
	public int getPlayerInvY()
	{
		return 95;
	}

	@Override
	public TransferResult transferStackInSlot(int slot, ItemStack stack, Item item, int invStart, int hotbarStart, int invEnd, EntityPlayer player)
	{
		if (slot >= 0 && slot < 10) return new TransferResult(invStart, invEnd, true);
		else if (slot >= invStart && slot < invEnd)
		{
			if (item instanceof IEnergyContainerItem) return new TransferResult(10, 14);
			else if (TileEntityFurnace.isItemFuel(stack)) return new TransferResult(0, 9);
			else if (slot < hotbarStart) return new TransferResult(hotbarStart, invEnd, true);
			else if (slot >= hotbarStart) return new TransferResult(invStart, hotbarStart, false);
		}
		return null;
	}
}
