package main.extremeblocks.tileentities.energy;

import java.util.List;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.WorldUtil;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;

public class PowerHelper
{
	private static boolean isAllValid(int a, int b, int c)
	{
		return a + b + c == 1 || a + b + c == -1;
	}

	public static ForgeDirection getSideAt(World world, TileEntity me, int xCoord, int yCoord, int zCoord)
	{
		return getSideAt(world, me.xCoord, me.yCoord, me.zCoord, xCoord, yCoord, zCoord);
	}

	public static ForgeDirection getSideAt(World world, int xCoord, int yCoord, int zCoord, TileEntity other)
	{
		return getSideAt(world, xCoord, yCoord, zCoord, other.xCoord, other.yCoord, other.zCoord);
	}

	public static ForgeDirection getSideAt(World world, TileEntity me, TileEntity other)
	{
		return getSideAt(world, me.xCoord, me.yCoord, me.zCoord, other.xCoord, other.yCoord, other.zCoord);
	}

	public static ForgeDirection getSideAt(World world, int x, int y, int z, int x1, int y1, int z1)
	{
		int x2 = x1 - x;
		int y2 = y1 - y;
		int z2 = z1 - z;
		if (!isAllValid(x2, y2, z2)) return ForgeDirection.UNKNOWN;
		switch (x2)
		{
			case -1:
				return ForgeDirection.WEST;
			case 1:
				return ForgeDirection.EAST;
		}
		switch (y2)
		{
			case -1:
				return ForgeDirection.DOWN;
			case 1:
				return ForgeDirection.UP;
		}
		switch (z2)
		{
			case -1:
				return ForgeDirection.NORTH;
			case 1:
				return ForgeDirection.SOUTH;
		}
		return ForgeDirection.UNKNOWN;
	}

	public static void calculatePowerFromInventory(IInventory inv, EnergyStorage storage, int... slots)
	{
		for (int slot : slots)
		{
			ItemStack stack = inv.getStackInSlot(slot);
			if (stack != null && stack.getItem() instanceof IEnergyContainerItem)
			{
				IEnergyContainerItem batt = (IEnergyContainerItem) stack.getItem();
				int i = batt.extractEnergy(stack, Integer.MAX_VALUE, true);
				i = storage.receiveEnergy(i, false);
				batt.extractEnergy(stack, i, false);
				inv.setInventorySlotContents(slot, stack);
			}
		}
	}

	public static void sendNearby(IEnergyProvider provider)
	{
		TileEntity e = (TileEntity) provider;
		TileEntity[] tiles = WorldUtil.getNeighborTiles(e.getWorldObj(), e.xCoord, e.yCoord, e.zCoord);
		List<IEnergyReceiver> recs = JavaHelp.newArrayList();
		for (TileEntity tile : tiles)
		{
			if (tile != null)
			{
				ForgeDirection d = PowerHelper.getSideAt(e.getWorldObj(), tile, e);
				if (tile instanceof IEnergyReceiver && ((IEnergyReceiver) tile).canConnectEnergy(d) && provider.canConnectEnergy(d.getOpposite()))
				{
					recs.add((IEnergyReceiver) tile);
				}
			}
		}
		int allEnergy = provider.getEnergyStored(ForgeDirection.UNKNOWN);
		for (IEnergyReceiver rec : recs)
		{
			if (allEnergy > 0)
			{
				ForgeDirection d = PowerHelper.getSideAt(e.getWorldObj(), (TileEntity) rec, e);
				int a = rec.receiveEnergy(d, allEnergy / recs.size(), false);
				allEnergy -= a;
				provider.extractEnergy(d.getOpposite(), a, false);
			}
		}
	}

	public int sendEnergyTo(int transfer, EnergyStorage from, EnergyStorage to)
	{
		int i = from.extractEnergy(transfer, true);
		i = to.receiveEnergy(i, false);
		i = from.extractEnergy(i, false);
		return i;
	}
}
