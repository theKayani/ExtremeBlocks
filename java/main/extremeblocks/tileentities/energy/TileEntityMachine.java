package main.extremeblocks.tileentities.energy;

import java.util.List;
import main.com.hk.eb.util.IEnergyHolder;
import main.com.hk.eb.util.IWailaInfo;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.MathHelp;
import main.extremeblocks.tileentities.TileEntitySidedInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyReceiver;

public abstract class TileEntityMachine extends TileEntitySidedInventory implements IEnergyReceiver, IEnergyHolder, IWailaInfo
{
	public EnergyStorage storage;

	public TileEntityMachine(String name)
	{
		super(name);
		storage = new EnergyStorage(getMaxEnergyStored(), getMaxReceive(), getMaxExtract());
	}

	public abstract int[] getBatterySlots();

	@Override
	public abstract List<Slot> getSlots();

	public void calculatePowerFromInventory()
	{
		PowerHelper.calculatePowerFromInventory(this, storage, getBatterySlots());
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		List<Integer> ints = JavaHelp.newArrayList();
		for (int i = 0; i < getBatterySlots().length; i++)
		{
			ints.add(getBatterySlots()[i]);
		}
		return ints.contains(ints) ? stack != null && stack.getItem() instanceof IEnergyContainerItem : false;
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		int i = storage.receiveEnergy(maxReceive, simulate);
		if (!simulate)
		{
			markDirty();
		}
		return i;
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return storage.getMaxEnergyStored();
	}

	@Override
	public boolean canWrench()
	{
		return true;
	}

	@Override
	public int getEnergyStoredScaled(int scale)
	{
		return MathHelp.clamp(Math.round(scale * ((float) storage.getEnergyStored() / storage.getMaxEnergyStored())), 0, scale);
	}

	@Override
	public int getMaxEnergyStored()
	{
		return 120000;
	}

	@Override
	public int getEnergyStored()
	{
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxExtract()
	{
		return 1024;
	}

	@Override
	public int getMaxReceive()
	{
		return 1024;
	}

	@Override
	public void readFrom(NBTTagCompound nbt)
	{
		super.readFrom(nbt);
		storage.readFromNBT(nbt);
	}

	@Override
	public void writeTo(NBTTagCompound nbt)
	{
		super.writeTo(nbt);
		storage.writeToNBT(nbt);
	}
}
