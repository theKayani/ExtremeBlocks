package main.extremeblocks.util;

import main.extremeblocks.tileentities.TileEntityWire;
import main.extremeblocks.tileentities.pipe.WireLogic;
import main.extremeblocks.tileentities.pipe.WireLogic.TransferType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class PowerHelper
{
	public static final float MAX_WIRE_POWER = 10000.0F;

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

	public static boolean sendPowerTo(TileEntityWire source, TileEntityWire destination)
	{
		ForgeDirection sent = PowerHelper.getSideAt(source.getWorldObj(), source, destination);
		if (source.overallPower - 1.0F > 0.0F && destination.overallPower + 1.0F <= MAX_WIRE_POWER)
		{
			WireLogic log = WireLogic.getLogicForSide(source, sent);
			WireLogic dest = WireLogic.getLogicForSide(destination, sent.getOpposite());
			if (log != null)
			{
				log.setType(TransferType.SENT);
			}
			if (dest != null)
			{
				dest.setType(TransferType.RECEIVED);
			}
			source.overallPower -= 1.0F;
			destination.overallPower += 1.0F;
			source.sent.put(sent, true);
			destination.received.put(sent.getOpposite(), true);
			source.render = destination.render = true;
			return true;
		}
		source.render = destination.render = false;
		return false;
	}

	public static int calculatePowerFromInventory(IInventory inv, int amountToTake, int... slots)
	{
		for (int slot : slots)
		{
			ItemStack stack = inv.getStackInSlot(slot);

			if (stack != null && stack.getItem() instanceof IBattery)
			{
				int input = ((IBattery) stack.getItem()).getMaxHeldPower() - stack.getItemDamage();
				if (input > 0 && ((IBattery) stack.getItem()).removePower(stack, amountToTake)) return amountToTake;
			}
		}
		return 0;
	}
}
