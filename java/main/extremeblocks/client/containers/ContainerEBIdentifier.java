package main.extremeblocks.client.containers;

import main.com.hk.eb.util.IInfo;
import main.extremeblocks.tileentities.TileEntityEBIdentifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ContainerEBIdentifier extends ContainerInventory
{
	public ContainerEBIdentifier(InventoryPlayer inv, TileEntityEBIdentifier tile)
	{
		super(inv, tile);
	}

	@Override
	public int getPlayerInvX()
	{
		return 8;
	}

	@Override
	public int getPlayerInvY()
	{
		return 125;
	}

	@Override
	public TransferResult transferStackInSlot(int slot, ItemStack stack, Item item, int invStart, int hotbarStart, int invEnd, EntityPlayer player)
	{
		if (slot == 0) return new TransferResult(invStart, invEnd, true);
		else if (item instanceof IInfo || item instanceof ItemBlock && ((ItemBlock) item).field_150939_a instanceof IInfo) return new TransferResult(0);
		return null;
	}
}
