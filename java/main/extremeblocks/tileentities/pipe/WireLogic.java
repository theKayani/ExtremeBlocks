package main.extremeblocks.tileentities.pipe;

import static main.extremeblocks.tileentities.pipe.WireLogic.TransferType.UNKNOWN;
import main.extremeblocks.misc.PowerHelper;
import main.extremeblocks.misc.Power.IPowerEmitter;
import main.extremeblocks.misc.Power.IPowerReceiver;
import main.extremeblocks.tileentities.TileEntityWire;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class WireLogic
{
	private final TileEntityWire wire;
	private final ForgeDirection side;
	private int posX, posY, posZ;
	private boolean isFake, modified;
	private TransferType type;

	public WireLogic(TileEntityWire wire, ForgeDirection side)
	{
		this.wire = wire;
		this.side = side;
		this.posX = wire.xCoord + side.offsetX;
		this.posY = wire.yCoord + side.offsetY;
		this.posZ = wire.zCoord + side.offsetZ;

		if (wire == null || side == null)
		{
			isFake = true;
		}
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		setType(TransferType.values()[nbt.getInteger(side.ordinal() + "-Type")]);
		posX = nbt.getInteger(side.ordinal() + "-X Pos") + getSide().offsetX;
		posY = nbt.getInteger(side.ordinal() + "-Y Pos") + getSide().offsetY;
		posZ = nbt.getInteger(side.ordinal() + "-Z Pos") + getSide().offsetZ;
		isFake = nbt.getBoolean(side.ordinal() + "-Is Fake");
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		nbt.setInteger(side.ordinal() + "-Type", getType().ordinal());
		nbt.setInteger(side.ordinal() + "-X Pos", wire.xCoord);
		nbt.setInteger(side.ordinal() + "-Y Pos", wire.yCoord);
		nbt.setInteger(side.ordinal() + "-Z Pos", wire.zCoord);
		nbt.setBoolean(side.ordinal() + "-Is Fake", isFake);
	}

	public final IPowerEmitter getEmitter()
	{
		if (isFake() || !isEmitter()) return null;
		return (IPowerEmitter) tileEntityAtSpot();
	}

	public final IPowerReceiver getReceiver()
	{
		if (isFake() || !isReceiver()) return null;
		return (IPowerReceiver) tileEntityAtSpot();
	}

	public final TileEntityWire getWire()
	{
		if (isFake() || !isWire()) return null;
		return (TileEntityWire) tileEntityAtSpot();
	}

	public void sendPowerTo()
	{
		if (isFake()) return;
		if (isWire())
		{
			PowerHelper.sendPowerTo(wire, (TileEntityWire) tileEntityAtSpot());
		}
	}

	public final TransferType getType()
	{
		if (type == null)
		{
			type = TransferType.UNKNOWN;
		}
		return type;
	}

	public final void setType(TransferType type)
	{
		type = type == null ? UNKNOWN : type;
		this.type = type;
	}

	public final boolean isEmitter()
	{
		return tileEntityAtSpot() instanceof IPowerEmitter;
	}

	public final boolean isWire()
	{
		return tileEntityAtSpot() instanceof TileEntityWire;
	}

	public final boolean isReceiver()
	{
		return tileEntityAtSpot() instanceof IPowerReceiver;
	}

	public final boolean isNull()
	{
		return tileEntityAtSpot() == null || isFake();
	}

	public final TileEntity tileEntityAtSpot()
	{
		if (isFake()) return null;
		return tileAt(posX, posY, posZ);
	}

	public final TileEntity tileAt(int x, int y, int z)
	{
		if (isFake()) return null;
		return wire.getWorldObj().getTileEntity(x, y, z);
	}

	public final ForgeDirection getSide()
	{
		if (isFake()) return null;
		return side;
	}

	public final boolean isModified()
	{
		return modified;
	}

	public final void setModified(boolean modified)
	{
		this.modified = modified;
	}

	private final boolean isFake()
	{
		return isFake;
	}

	public static WireLogic getLogicForSide(TileEntityWire wire, ForgeDirection side)
	{
		if (wire == null || wire.logics == null) return null;
		for (int i = 0; i < wire.logics.length; i++)
		{
			if (wire.logics[i].side == side)
			{
				return wire.logics[i];
			}
		}
		return null;
	}

	public static enum TransferType
	{
		RECEIVED, SENT, UNKNOWN;
	}
}
