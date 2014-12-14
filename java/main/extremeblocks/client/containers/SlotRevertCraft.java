package main.extremeblocks.client.containers;

import main.extremeblocks.tileentities.TileEntityRevertingStation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotRevertCraft extends Slot
{
	private final TileEntityRevertingStation inv;

	public SlotRevertCraft(TileEntityRevertingStation inv, int index, int x, int y)
	{
		super(inv, index, x, y);
		this.inv = inv;
	}

	@Override
	public void onPickupFromSlot(EntityPlayer player, ItemStack stack)
	{
		super.onPickupFromSlot(player, stack);
		inv.slotTakenFrom();
	}

	@Override
	public boolean isItemValid(ItemStack p_75214_1_)
	{
		return false;
	}

	@Override
	public boolean canTakeStack(EntityPlayer p_82869_1_)
	{
		return true;
	}
}
