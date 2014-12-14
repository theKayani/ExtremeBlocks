package main.extremeblocks.client.containers;

import main.extremeblocks.items.ItemEBGuide;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

public class ContainerEBGuide extends Container
{

	public ContainerEBGuide(InventoryPlayer inventory, World world, int x, int y, int z)
	{
	}

	@Override
	public boolean canInteractWith(EntityPlayer p)
	{
		return p.getHeldItem() != null && p.getHeldItem().getItem() instanceof ItemEBGuide;
	}
}
