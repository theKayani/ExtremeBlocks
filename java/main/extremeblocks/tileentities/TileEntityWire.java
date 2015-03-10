package main.extremeblocks.tileentities;

import java.util.List;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.Vector3I;
import main.com.hk.eb.util.WorldUtil;
import main.extremeblocks.tileentities.energy.PowerHelper;
import main.extremeblocks.tileentities.energy.WireList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyHandler;

public class TileEntityWire extends TileEntity implements IEnergyHandler
{
	public WireList map;

	@Override
	public void updateEntity()
	{
		getMap().sendEnergy();
	}

	public void updateNeighbors()
	{
		List<TileEntityWire> pipes = JavaHelp.newArrayList();
		for (ForgeDirection d : ForgeDirection.VALID_DIRECTIONS)
		{
			TileEntity t = worldObj.getTileEntity(xCoord + d.offsetX, yCoord + d.offsetY, zCoord + d.offsetZ);
			if (t instanceof TileEntityWire)
			{
				((TileEntityWire) t).getPipeNeighbors(pipes);
			}
		}
		int power = map == null ? 0 : map.getEnergyStored();
		map = new WireList(worldObj);
		map.storage.modifyEnergyStored(power);
		for (TileEntityWire p : pipes)
		{
			p.map = map;
			map.add(p);
		}
	}

	public List<TileEntityWire> getPipeNeighbors(List<TileEntityWire> pipes)
	{
		TileEntity[] tiles = WorldUtil.getNeighborTiles(worldObj, new Vector3I(this));
		for (TileEntity e : tiles)
		{
			if (e instanceof TileEntityWire)
			{
				if (!pipes.contains(e))
				{
					pipes.add((TileEntityWire) e);
					((TileEntityWire) e).getPipeNeighbors(pipes);
				}
			}
		}
		return pipes;
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	public boolean canConnectTo(int x, int y, int z)
	{
		TileEntity tile = WorldUtil.getTile(worldObj, x, y, z);
		return tile instanceof IEnergyConnection && ((IEnergyConnection) tile).canConnectEnergy(PowerHelper.getSideAt(worldObj, tile, this));
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		return getMap().storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		return getMap().storage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return getMap().getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return getMap().getMaxEnergyStored();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		getMap().storage.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		getMap().storage.writeToNBT(nbt);
	}

	private WireList getMap()
	{
		if (map == null)
		{
			updateNeighbors();
		}
		return map;
	}

	@Override
	public boolean equals(Object o)
	{
		return o instanceof TileEntityWire ? new Vector3I(this).equals(new Vector3I((TileEntityWire) o)) : false;
	}

	@Override
	public int hashCode()
	{
		return new Vector3I(this).hashCode();
	}

	@Override
	public String toString()
	{
		return new Vector3I(this).toString();
	}
}
