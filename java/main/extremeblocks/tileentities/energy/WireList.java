package main.extremeblocks.tileentities.energy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.Vector3I;
import main.com.hk.eb.util.WorldUtil;
import main.extremeblocks.tileentities.TileEntityWire;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;

public class WireList extends ArrayList<TileEntityWire>
{
	public final List<WirePath> paths;
	public int power;
	public final World world;

	public WireList(World world)
	{
		this.world = world;
		paths = JavaHelp.newArrayList();
	}

	@Override
	public boolean add(TileEntityWire p)
	{
		if (contains(p)) return false;
		else return super.add(p);
	}

	@Override
	public boolean addAll(Collection<? extends TileEntityWire> coll)
	{
		boolean done = true;
		for (TileEntityWire p : coll)
		{
			if (!contains(p))
			{
				done = done && add(p);
			}
		}
		return done;
	}

	@Override
	public boolean contains(Object o)
	{
		if (o == null || !(o instanceof TileEntityWire)) return false;
		TileEntityWire p = (TileEntityWire) o;
		for (TileEntityWire p1 : this)
		{
			if (p1 != null)
			{
				if (new Vector3I(p).equals(new Vector3I(p1))) return true;
			}
		}
		return false;
	}

	public void sendEnergy()
	{
		updateConnections();
		List<IEnergyReceiver> red = getReceivers();
		for (int i = 0; i < red.size(); i++)
		{
			int shouldTake = power / red.size();
			IEnergyReceiver r = red.get(i);
			TileEntity rec = (TileEntity) r;
			TileEntity[] tiles = WorldUtil.getNeighborTiles(world, new Vector3I(rec));
			for (TileEntity e : tiles)
			{
				if (e != null)
				{
					ForgeDirection a = PowerHelper.getSideAt(world, rec, e);
					if (power > 0 && e instanceof TileEntityWire && contains(e) && r.canConnectEnergy(a))
					{
						if (shouldTake > 1024)
						{
							shouldTake = 1024;
						}
						power -= r.receiveEnergy(a, shouldTake, false);
					}
				}
			}
		}
	}

	public WirePath getShortestPath(Vector3I start, Vector3I end)
	{
		List<Vector3I> vecs = JavaHelp.newArrayList();
		WirePath path = null;
		int m = Integer.MAX_VALUE;
		for (TileEntityWire p : this)
		{
			vecs.add(new Vector3I(p));
		}
		for (long i = 1; i < 7; i++)
		{
			WirePath p = new WirePath(i * 287652356L, world, start, end, vecs.toArray(new Vector3I[0]));
			if (p.getPath().size() < m)
			{
				path = p;
				m = p.getPath().size();
			}
		}
		return path;
	}

	public void updateConnections()
	{
		for (TileEntityWire p : this)
		{
			power += p.removeAllEnergy();
		}
	}

	public List<TileEntity> getNeighborMachines()
	{
		List<TileEntity> rec = JavaHelp.newArrayList();
		for (TileEntityWire p : this)
		{
			TileEntity[] tiles = WorldUtil.getNeighborTiles(world, new Vector3I(p));
			for (TileEntity t : tiles)
			{
				if ((t instanceof IEnergyProvider || t instanceof IEnergyReceiver) && !(t instanceof TileEntityWire) && !rec.contains(t))
				{
					rec.add(t);
				}
			}
		}
		return rec;
	}

	public List<IEnergyReceiver> getReceivers()
	{
		List<IEnergyReceiver> rec = JavaHelp.newArrayList();
		for (TileEntityWire p : this)
		{
			TileEntity[] tiles = WorldUtil.getNeighborTiles(world, new Vector3I(p));
			for (TileEntity t : tiles)
			{
				if (t instanceof IEnergyReceiver && !(t instanceof TileEntityWire) && !rec.contains(t))
				{
					rec.add((IEnergyReceiver) t);
				}
			}
		}
		return rec;
	}

	public List<IEnergyProvider> getProviders()
	{
		List<IEnergyProvider> pros = JavaHelp.newArrayList();
		for (TileEntityWire p : this)
		{
			TileEntity[] tiles = WorldUtil.getNeighborTiles(world, new Vector3I(p));
			for (TileEntity t : tiles)
			{
				if (t instanceof IEnergyProvider && !(t instanceof TileEntityWire) && !pros.contains(t))
				{
					pros.add((IEnergyProvider) t);
				}
			}
		}
		return pros;
	}

	@Override
	public String toString()
	{
		return String.valueOf(size() + (size() > 0 ? new Vector3I(get(0)).toString() : ""));
	}

	private static final long serialVersionUID = 1L;
}
