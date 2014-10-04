package main.extremeblocks.tileentities;

import static main.extremeblocks.tileentities.pipe.WireLogic.TransferType.*;
import java.util.ArrayList;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.tileentities.pipe.WireLogic;
import main.extremeblocks.util.IConnector;
import main.extremeblocks.util.IPlayerMessage;
import main.extremeblocks.util.Power.IPowerEmitter;
import main.extremeblocks.util.Power.IPowerReceiver;
import main.extremeblocks.util.PowerHelper;
import main.extremeblocks.util.PowerMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityWire extends TileEntity implements IConnector, IPlayerMessage
{
	public static final int DIR_LENGTH = ForgeDirection.VALID_DIRECTIONS.length;
	public int id;
	public float overallPower;
	public static int ID;
	public boolean isIllegal, isValid, render;
	public int counter;
	public PowerMap received;
	public PowerMap sent;
	public WireLogic[] logics;

	public TileEntityWire()
	{
		id = ID++;
		received = new PowerMap("Received");
		sent = new PowerMap("Sent");
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		isIllegal = tag.getBoolean("Is Illegal");
		overallPower = tag.getFloat("Overall Power");

		logics = new WireLogic[DIR_LENGTH];
		for (int i = 0; i < DIR_LENGTH; i++)
		{
			logics[i] = new WireLogic(this, ForgeDirection.VALID_DIRECTIONS[i]);
		}

		for (int i = 0; i < DIR_LENGTH; i++)
		{
			logics[i].readFromNBT(tag);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setBoolean("Is Illegal", isIllegal);
		tag.setFloat("Overall Power", overallPower);

		for (int i = 0; i < DIR_LENGTH; i++)
		{
			logics[i].writeToNBT(tag);
		}
	}

	@Override
	public void updateEntity()
	{
		TileEntity[] tiles = MPUtil.getNeighborTiles(worldObj, xCoord, yCoord, zCoord);
		ArrayList<IPowerReceiver> receivers = JavaHelp.newArrayList();
		boolean remove = false;
		int rec = 0;

		if (logics == null)
		{
			logics = new WireLogic[DIR_LENGTH];
			for (int i = 0; i < DIR_LENGTH; i++)
			{
				logics[i] = new WireLogic(this, ForgeDirection.VALID_DIRECTIONS[i]);
			}
		}

		for (int i = 0; i < logics.length; i++)
		{
			WireLogic logic = logics[i];
			logic.setModified(false);

			if (logic.isEmitter() && logic.getEmitter().canSendPowerThrough(logic.getSide().getOpposite()))
			{
				IPowerEmitter te = logic.getEmitter();
				float pw = te.getPower();
				if (pw + overallPower < PowerHelper.MAX_WIRE_POWER)
				{
					overallPower += pw;
					logic.setType(RECEIVED);
					received.put(logic.getSide(), true);
					te.takenPowerFrom(pw);
				}
				logic.setModified(true);
			}
			else if (logic.isWire())
			{
				TileEntityWire wire = logic.getWire();

				if (wire.isSource() || logic.getType() == RECEIVED || wire.sent.get(logic.getSide().getOpposite()))
				{
					WireLogic log = WireLogic.getLogicForSide(wire, logic.getSide().getOpposite());
					if (log != null) log.sendPowerTo();
					logic.setModified(true);
				}
				if (isSource() || logic.getType() == SENT || wire.received.get(logic.getSide().getOpposite()))
				{
					logic.sendPowerTo();
					logic.setModified(true);
				}
				if (logic.getType() == UNKNOWN || (!received.isAllFalse() && wire.received.isAllFalse()))
				{
					logic.sendPowerTo();
					logic.setModified(true);
				}
			}
			else if (logic.isReceiver())
			{
				logic.setType(SENT);
				receivers.add(logic.getReceiver());
				rec++;
				logic.setModified(true);
			}
			if (!logic.isModified())
			{
				logic.setType(UNKNOWN);
				sent.put(logic.getSide(), false);
				received.put(logic.getSide(), false);
			}
		}
		for (int i = 0; rec > 0 && i < receivers.size(); i++)
		{
			receivers.get(i).receivePower(PowerHelper.getSideAt(worldObj, this, (TileEntity) receivers.get(i)), overallPower / rec);
			remove = true;
		}
		if (remove)
		{
			overallPower = 0;
		}
	}

	private boolean isSource()
	{
		TileEntity[] tiles = MPUtil.getNeighborTiles(worldObj, xCoord, yCoord, zCoord);
		for (int i = 0; i < tiles.length; i++)
		{
			if (tiles[i] instanceof IPowerEmitter)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof TileEntityWire && o.hashCode() == this.hashCode())
		{
			return true;
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		return id;
	}

	public void breakBlock()
	{

	}

	public void onBlockAdded()
	{

	}

	@Override
	public boolean onClickedOn(EntityPlayer player)
	{
		if (MPUtil.isServerSide())
		{
			System.out.println("Overall Power: " + overallPower);
		}
		return false;
	}

	@Override
	public boolean canConnect(World world, int x, int y, int z)
	{
		return true;
	}
}
