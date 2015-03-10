package main.extremeblocks.client.containers.energy;

import main.extremeblocks.client.containers.ContainerInventory;
import main.extremeblocks.tileentities.energy.TileEntityRFInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cofh.api.energy.IEnergyContainerItem;

public class ContainerRFInfuser extends ContainerInventory
{
	public ContainerRFInfuser(InventoryPlayer inventory, TileEntityRFInfuser tile)
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
		if (slot >= 0 && slot < 12) return new TransferResult(invStart, invEnd, true);
		else if (item instanceof IEnergyContainerItem) return new TransferResult(0, invStart);
		else if (slot >= 12 && slot < hotbarStart) return new TransferResult(hotbarStart, invEnd);
		else if (slot >= hotbarStart && slot < invEnd) return new TransferResult(invStart, hotbarStart);
		return null;
	}
}
