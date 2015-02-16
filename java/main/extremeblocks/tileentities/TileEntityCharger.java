package main.extremeblocks.tileentities;

import main.extremeblocks.util.IBattery;
import main.extremeblocks.util.IConnector;
import main.extremeblocks.util.Power.IPowerReceiver;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCharger extends TileEntityInventory implements IConnector, IPowerReceiver
{
	public float overallPower;
	public int[] powerDelay;

	public TileEntityCharger()
	{
		super("Charger");
		inventory = new ItemStack[3];
		powerDelay = new int[3];
	}

	@Override
	public void updateEntity()
	{
		if (overallPower <= 0) return;
		for (ItemStack element : inventory)
		{
			if (element != null && element.getItem() instanceof IBattery)
			{
				if (((IBattery) element.getItem()).receivePower(element, 1))
				{
					overallPower--;
					break;
				}
			}
		}
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		return true;
	}

	@Override
	public void receivePower(ForgeDirection sideSent, float power)
	{
		overallPower += power;
	}

	@Override
	public boolean canConnect(World world, int x, int y, int z)
	{
		return true;
	}
}
