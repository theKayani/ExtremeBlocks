package main.extremeblocks.blocks.tileentities.pipe;

import static main.extremeblocks.blocks.tileentities.pipe.TransferType.IN;
import static main.extremeblocks.blocks.tileentities.pipe.TransferType.OUT;
import main.com.hk.testing.util.StackHelper;
import main.extremeblocks.blocks.tileentities.TileEntityStorage;
import main.extremeblocks.blocks.tileentities.TileEntityTransportPipe;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraftforge.common.util.ForgeDirection;

public class TransportPipeLogic extends PipeLogic<TileEntityTransportPipe, TileEntityStorage, TileEntityTransportPipe, TileEntityChest>
{
	public TransportPipeLogic(TileEntityTransportPipe pipe, ForgeDirection side)
	{
		super(pipe, side);
	}

	public boolean isTransferer()
	{
		return !isNull() && tileAtSpot() instanceof TileEntityTransportPipe;
	}

	public boolean isEmitter()
	{
		return !isNull() && tileAtSpot() instanceof TileEntityChest;
	}

	public boolean isReceiver()
	{
		return !isNull() && !(tileAtSpot() instanceof TileEntityChest) && tileAtSpot() instanceof IInventory;
	}

	public void transferItemsToNextPipe()
	{
		TileEntityTransportPipe te = (TileEntityTransportPipe) getTransferer();

		if (getType() != OUT || te == null) return;

		te.items.addAll(pipe.items);
		pipe.items.clear();
		te.sideReceived = getSide().getOpposite();
		pipe.sideSent = getSide();
	}

	public void sendToStorage()
	{
		if (!isReceiver() || getType() != OUT) return;
		StackHelper.addToInv(getReceiver(), pipe.items.toArray(new ItemStack[0]));
	}

	public void getFromChest()
	{
		if (!isEmitter() || getType() != IN) return;
		IInventory inv = (IInventory) getEmitter();

		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			if (inv.getStackInSlot(i) != null)
			{
				pipe.items.add(inv.getStackInSlot(i));
				inv.setInventorySlotContents(i, null);
			}
		}
	}
}
