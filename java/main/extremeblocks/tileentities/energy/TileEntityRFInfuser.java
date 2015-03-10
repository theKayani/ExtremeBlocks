package main.extremeblocks.tileentities.energy;

import java.text.DecimalFormat;
import java.util.List;
import main.extremeblocks.client.containers.Slots;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyContainerItem;

public class TileEntityRFInfuser extends TileEntityMachine
{
	public TileEntityRFInfuser()
	{
		super("Redstone Flux Infuser");
		inventory = new ItemStack[12];
	}

	@Override
	public boolean update()
	{
		if (isRedstonePowered())
		{
			for (int i = 0; i < 12; i++)
			{
				ItemStack stack = inventory[i];
				if (stack != null && stack.getItem() instanceof IEnergyContainerItem)
				{
					IEnergyContainerItem batt = (IEnergyContainerItem) stack.getItem();
					int energy = storage.extractEnergy(Integer.MAX_VALUE, true);
					energy = batt.receiveEnergy(stack, energy, false);
					storage.extractEnergy(energy, false);
				}
			}
		}
		return false;
	}

	@Override
	public int[] getBatterySlots()
	{
		//99,264
		return new int[0];
	}

	@Override
	public List<Slot> getSlots()
	{
		return new Slots(this, 11, 29, 6, 2);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side)
	{
		return stack.getItem() instanceof IEnergyContainerItem ? ((IEnergyContainerItem) stack.getItem()).getEnergyStored(stack) == ((IEnergyContainerItem) stack.getItem()).getMaxEnergyStored(stack) : false;
	}

	@Override
	protected int[] getSlotsFor(ForgeDirection side)
	{
		return getSlotsBetween(0, 12);
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		return stack != null && stack.getItem() instanceof IEnergyContainerItem;
	}

	@Override
	public List<String> getTips(List<String> currentList)
	{
		DecimalFormat f = new DecimalFormat("#,###");
		String stored = f.format(storage.getEnergyStored());
		String max = f.format(storage.getMaxEnergyStored());
		currentList.add(stored + "/" + max + EnumChatFormatting.RED + " RF " + EnumChatFormatting.RESET + "Held");
		String s1 = "";
		String s2 = "";
		for (int i = 0; i < 6; i++)
		{
			ItemStack stack1 = inventory[i];
			ItemStack stack2 = inventory[i + 6];
			if (stack1 == null || !(stack1.getItem() instanceof IEnergyContainerItem))
			{
				s1 += "[" + EnumChatFormatting.RED + "X" + EnumChatFormatting.RESET + "]";
				continue;
			}
			if (stack2 == null || !(stack2.getItem() instanceof IEnergyContainerItem))
			{
				s2 += "[" + EnumChatFormatting.RED + "X" + EnumChatFormatting.RESET + "]";
				continue;
			}
			IEnergyContainerItem cont1 = (IEnergyContainerItem) stack1.getItem();
			IEnergyContainerItem cont2 = (IEnergyContainerItem) stack2.getItem();
			if (cont1.getEnergyStored(stack1) < cont1.getMaxEnergyStored(stack1))
			{
				s1 += "[" + EnumChatFormatting.YELLOW + "W" + EnumChatFormatting.RESET + "]";
			}
			if (cont2.getEnergyStored(stack2) < cont2.getMaxEnergyStored(stack2))
			{
				s2 += "[" + EnumChatFormatting.YELLOW + "W" + EnumChatFormatting.RESET + "]";
			}
			if (cont1.getEnergyStored(stack1) == cont1.getMaxEnergyStored(stack1))
			{
				s1 += "[" + EnumChatFormatting.GREEN + "D" + EnumChatFormatting.RESET + "]";
			}
			if (cont2.getEnergyStored(stack2) == cont2.getMaxEnergyStored(stack2))
			{
				s2 += "[" + EnumChatFormatting.GREEN + "D" + EnumChatFormatting.RESET + "]";
			}
		}
		currentList.add(s1);
		currentList.add(s2);
		return currentList;
	}

	@Override
	public int getMaxEnergyStored()
	{
		return 10000000;
	}

	@Override
	public boolean shouldAddTip()
	{
		return true;
	}
}
