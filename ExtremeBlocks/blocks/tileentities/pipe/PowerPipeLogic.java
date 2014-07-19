package main.extremeblocks.blocks.tileentities.pipe;

import static main.extremeblocks.blocks.tileentities.pipe.TransferType.IN;
import static main.extremeblocks.blocks.tileentities.pipe.TransferType.OUT;
import main.extremeblocks.blocks.tileentities.TileEntityPowerPipe;
import main.extremeblocks.util.IPower.IPowerEmitter;
import main.extremeblocks.util.IPower.IPowerReceiver;
import net.minecraftforge.common.util.ForgeDirection;

public class PowerPipeLogic extends PipeLogic<TileEntityPowerPipe, IPowerReceiver, TileEntityPowerPipe, IPowerEmitter>
{
	public PowerPipeLogic(TileEntityPowerPipe pipe, ForgeDirection side)
	{
		super(pipe, side);
	}

	public boolean isTransferer()
	{
		return !isNull() && tileAtSpot() instanceof TileEntityPowerPipe;
	}

	public boolean isEmitter()
	{
		return !isNull() && tileAtSpot() instanceof IPowerEmitter;
	}

	public boolean isReceiver()
	{
		return !isNull() && tileAtSpot() instanceof IPowerReceiver;
	}

	public void sendPowerToNextPipe()
	{
		TileEntityPowerPipe te = (TileEntityPowerPipe) getTransferer();

		if (getType() != OUT || te == null) return;

		te.power += pipe.power;
		pipe.power = 0;
		te.sideReceived = getSide().getOpposite();
		pipe.sideSent = getSide();
	}

	public void sendToPowerReceiver()
	{
		if (!isReceiver() || getType() != OUT) return;
		IPowerReceiver te = (IPowerReceiver) getReceiver();

		te.receivePower(pipe.sideSent, pipe.power);
		pipe.power = 0;
	}

	public void getFromEmitter()
	{
		if (!isEmitter() || getType() != IN) return;
		IPowerEmitter te = (IPowerEmitter) getEmitter();

		pipe.power = te.getPower(pipe.sideReceived);
	}
}
