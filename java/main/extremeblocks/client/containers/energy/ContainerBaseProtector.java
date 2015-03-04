package main.extremeblocks.client.containers.energy;

import main.extremeblocks.blocks.energy.BlockBaseProtector.ProtectorUpgrade;
import main.extremeblocks.client.containers.ContainerInventory;
import main.extremeblocks.tileentities.TileEntityInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cofh.api.energy.IEnergyContainerItem;

public class ContainerBaseProtector extends ContainerInventory
{
	public ContainerBaseProtector(InventoryPlayer inventory, TileEntityInventory tile)
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
		return 84;
	}

	@Override
	public TransferResult transferStackInSlot(int slot, ItemStack stack, Item item, int invStart, int hotbarStart, int invEnd, EntityPlayer player)
	{
		if (slot >= 0 && slot < invStart) return new TransferResult(invStart, invEnd, true);
		else
		{
			for (int i = 0; i < ProtectorUpgrade.values().length; i++)
			{
				if (ProtectorUpgrade.values()[i].isValid(stack)) return new TransferResult(i, i + 1);
			}
			if (item instanceof IEnergyContainerItem) return new TransferResult(5, 9);
			else if (slot >= invStart && slot < hotbarStart) return new TransferResult(hotbarStart, invEnd, true);
			else if (slot >= hotbarStart) return new TransferResult(invStart, hotbarStart, false);
		}
		return null;
	}
}
