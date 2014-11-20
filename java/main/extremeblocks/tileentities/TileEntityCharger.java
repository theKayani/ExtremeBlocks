package main.extremeblocks.tileentities;

import main.extremeblocks.misc.IBattery;
import main.extremeblocks.misc.IConnector;
import main.extremeblocks.misc.Power.IPowerReceiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCharger extends TileEntityInventory implements IConnector, IPowerReceiver
{
	public float overallPower;
	public int[] powerDelay;
	public String customName;

	public TileEntityCharger()
	{
		inventory = new ItemStack[3];
		powerDelay = new int[3];
	}

	@Override
	public void updateEntity()
	{
		if (overallPower <= 0) return;
		for (int i = 0; i < inventory.length; i++)
		{
			if (inventory[i] != null && inventory[i].getItem() instanceof IBattery)
			{
				if (((IBattery) inventory[i].getItem()).receivePower(inventory[i], 1))
				{
					overallPower--;
					break;
				}
			}
		}
	}

	@Override
	public String getInventoryName()
	{
		return hasCustomInventoryName() ? customName : "Charger";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return customName != null;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
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
