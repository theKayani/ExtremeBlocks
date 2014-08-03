package main.extremeblocks.blocks.tileentities.pipe;

import static main.extremeblocks.blocks.tileentities.pipe.TransferType.NONE;
import static net.minecraftforge.common.util.ForgeDirection.DOWN;
import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.UNKNOWN;
import static net.minecraftforge.common.util.ForgeDirection.UP;
import static net.minecraftforge.common.util.ForgeDirection.WEST;
import main.com.hk.testing.util.MPUtil;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.blocks.tileentities.TileEntityTransportPipe;
import main.extremeblocks.network.PacketPipe;
import main.extremeblocks.util.IConnector;
import main.extremeblocks.util.IPlayerMessage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileEntityAbstractPipe<E extends PipeLogic, P extends PacketPipe> extends TileEntity implements IConnector, IPlayerMessage
{
	public boolean hasSource, hasDir;
	public int sourceCount, dirCount, counter;
	public ForgeDirection sideReceived = UNKNOWN, sideSent = UNKNOWN;
	public E[] sides;

	public abstract P getPacket();

	public abstract void receivedPacket(P packet);

	public abstract void nearEmitter(E side);

	public abstract void nearReceiver(E side);

	public abstract void nearOutPipe(E side);

	public abstract void nearInPipe(E side);

	public abstract void nearUnknownPipe(E side);

	public abstract E[] getLogics();

	public abstract void update();

	public abstract void destroyedBlock(int x, int y, int z, int chance);

	public final void updateEntity()
	{
		if (MPUtil.isServerSide())
		{
			if (counter++ >= 50) MPUtil.sendToAll(getPacket());

			update();

			if (getLogics().length != 6)
			{
				System.err.println("~~~~~~~~~~~~~~~~~~~");
				System.err.println("~~~~~~~~~~~~~~~~~~~");
				System.err.println("~~~~~~~~~~~~~~~~~~~");
				System.err.println("Logics Size Isn't 6!");
				System.err.println("~~~~~~~~~~~~~~~~~~~");
				System.err.println("~~~~~~~~~~~~~~~~~~~");
				System.err.println("~~~~~~~~~~~~~~~~~~~");
				return;
			}

			sides = getLogics();

			for (int i = 0; i < sides.length; i++)
			{
				sides[i].setType(NONE);

				if (sides[i].isEmitter())
				{
					sourceCount++;
					nearEmitter(sides[i]);
				}
				if (sides[i].isReceiver())
				{
					dirCount++;
					nearReceiver(sides[i]);
				}
				if (sides[i].isTransferer())
				{
					if (sideSent == sides[i].getSide())
					{
						nearOutPipe(sides[i]);
					}
					else if (sideReceived == sides[i].getSide())
					{
						nearInPipe(sides[i]);
					}
					else
					{
						nearUnknownPipe(sides[i]);
					}
				}
			}
		}

		if (sourceCount > 0) hasSource = true;
		else hasSource = false;

		if (dirCount > 0) hasDir = true;
		else hasDir = false;
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);

		nbt.setString("Side Sent", getSymbol(sideSent));
		nbt.setString("Side Received", getSymbol(sideReceived));
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);

		this.sideSent = TileEntityTransportPipe.getDirection(nbt.getString("Side Sent"));
		this.sideReceived = TileEntityTransportPipe.getDirection(nbt.getString("Side Received"));
	}

	public static String getSymbol(ForgeDirection direction)
	{
		switch (direction)
		{
			case EAST:
				return "E";
			case WEST:
				return "W";
			case NORTH:
				return "N";
			case SOUTH:
				return "S";
			case UP:
				return "U";
			case DOWN:
				return "D";
			default:
				return "?";
		}
	}

	public static ForgeDirection getDirection(String symbol)
	{
		char sym = symbol.charAt(0);

		switch (sym)
		{
			case 'E':
				return EAST;
			case 'W':
				return WEST;
			case 'N':
				return NORTH;
			case 'S':
				return SOUTH;
			case 'U':
				return UP;
			case 'D':
				return DOWN;
			default:
				return UNKNOWN;
		}
	}
}
