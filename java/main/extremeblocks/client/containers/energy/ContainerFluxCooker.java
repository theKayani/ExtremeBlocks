package main.extremeblocks.client.containers.energy;

import main.extremeblocks.client.containers.ContainerInventory;
import main.extremeblocks.tileentities.energy.TileEntityFluxCooker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ContainerFluxCooker extends ContainerInventory
{
	public ContainerFluxCooker(InventoryPlayer inventory, TileEntityFluxCooker tile)
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
		if (slot == 2) return new TransferResult(3, 39, true);
		else if (slot != 0 || slot != 1)
		{
			if (FurnaceRecipes.smelting().getSmeltingResult(stack) != null) return new TransferResult(0);
			else if (slot >= 3 && slot < 30) return new TransferResult(30, 39);
			else if (slot >= 30 && slot < 39) return new TransferResult(3, 30);
		}
		else return new TransferResult(3, 39);
		return null;
	}
}
