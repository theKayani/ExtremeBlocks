package main.extremeblocks.blocks.tileentities.pipe;

import static main.extremeblocks.blocks.tileentities.pipe.TransferType.NONE;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class PipeLogic<E extends TileEntityAbstractPipe, R, P, G>
{
	protected final E pipe;
	protected final ForgeDirection side;
	protected final int posX, posY, posZ;
	private TransferType type = NONE;
	private boolean isFake;

	public PipeLogic(E pipe, ForgeDirection side)
	{
		this.pipe = pipe;
		this.side = side;
		this.posX = pipe.xCoord + side.offsetX;
		this.posY = pipe.yCoord + side.offsetY;
		this.posZ = pipe.zCoord + side.offsetZ;

		if (pipe == null || side == null)
		{
			isFake = true;
		}
	}

	public final P getTransferer()
	{
		if (isFake()) return null;
		if (isTransferer()) return (P) tileAtSpot();
		return null;
	}

	public final G getEmitter()
	{
		if (isFake()) return null;
		if (isEmitter()) return (G) tileAtSpot();
		return null;
	}

	public final R getReceiver()
	{
		if (isFake()) return null;
		if (isReceiver()) return (R) tileAtSpot();
		return null;
	}

	public abstract boolean isTransferer();

	public abstract boolean isEmitter();

	public abstract boolean isReceiver();

	public final boolean isNull()
	{
		return tileAtSpot() == null || isFake();
	}

	public final TileEntity tileAtSpot()
	{
		if (isFake()) return null;
		return tileEntity(posX, posY, posZ);
	}

	public final TileEntity tileEntity(int x, int y, int z)
	{
		if (isFake()) return null;
		return pipe.getWorldObj().getTileEntity(x, y, z);
	}

	public final ForgeDirection getSide()
	{
		if (isFake()) return null;
		return side;
	}

	public final TransferType getType()
	{
		if (isFake()) return null;
		return type;
	}

	public final void setType(TransferType type)
	{
		if (isFake()) return;
		this.type = type;
	}

	private final boolean isFake()
	{
		return isFake;
	}
}
