package main.extremeblocks.tileentities.energy;

import java.text.DecimalFormat;
import java.util.List;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.StackHelper;
import main.extremeblocks.client.containers.Slots;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityFuelGenerator extends TileEntityGenerator
{
	private int[] furnaceBurnTimes;
	private int[] itemBurnTimes;

	public TileEntityFuelGenerator()
	{
		super("Fuel Generator");
		inventory = new ItemStack[14];
		furnaceBurnTimes = new int[10];
		itemBurnTimes = new int[10];
	}

	@Override
	public int[] getBatterySlots()
	{
		return new int[] { 10, 11, 12, 13 };
	}

	@Override
	public List<Slot> getSlots()
	{
		List<Slot> list = JavaHelp.newArrayList();
		list.addAll(new Slots(this, 10, 20, 5, 2).setApart(20, 37));
		list.addAll(new Slots(this, 120, 10, 2, 2).setStartID(10));
		return list;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side)
	{
		return stack.getItem() instanceof IEnergyContainerItem ? ((IEnergyContainerItem) stack.getItem()).getEnergyStored(stack) == 0 : false;
	}

	@Override
	protected int[] getSlotsFor(ForgeDirection side)
	{
		return getBatterySlots();
	}

	@Override
	public boolean update()
	{
		int stored = getEnergyStored();
		calculatePowerFromInventory();
		if (isRedstonePowered())
		{
			for (int i = 0; i < 10; i++)
			{
				ItemStack stack = inventory[i];
				if (furnaceBurnTimes[i] > 0)
				{
					int energyReceived = storage.receiveEnergy(80, true);
					if (energyReceived > 0)
					{
						storage.receiveEnergy(80, false);
						furnaceBurnTimes[i]--;
					}
				}
				if (furnaceBurnTimes[i] == 0 && canSmelt(stack))
				{
					itemBurnTimes[i] = furnaceBurnTimes[i] = TileEntityFurnace.getItemBurnTime(stack);

					if (furnaceBurnTimes[i] > 0)
					{
						inventory[i] = StackHelper.consumeItem(stack);
					}
				}
				//TileEntityFurnace
			}
		}
		sendNearby();
		return stored != getEnergyStored();
	}

	public boolean canSmelt(ItemStack stack)
	{
		return TileEntityFurnace.isItemFuel(stack);
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int i, int slot)
	{
		if (itemBurnTimes[slot] == 0)
		{
			itemBurnTimes[slot] = 200;
		}

		return furnaceBurnTimes[slot] * i / itemBurnTimes[slot];
	}

	public boolean isBurning(int i)
	{
		return furnaceBurnTimes[i] > 0;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		return slot >= 0 && slot < 10 ? canSmelt(stack) : stack.getItem() instanceof IEnergyContainerItem;
	}

	@Override
	public void writeTo(NBTTagCompound nbt)
	{
		super.writeTo(nbt);
		nbt.setIntArray("Furnace Burn Times", furnaceBurnTimes);
		nbt.setIntArray("item Burn Times", itemBurnTimes);
	}

	@Override
	public void readFrom(NBTTagCompound nbt)
	{
		super.readFrom(nbt);
		if (nbt.hasKey("Furnace Burn Times"))
		{
			furnaceBurnTimes = nbt.getIntArray("Furnace Burn Times");
		}
		if (nbt.hasKey("Item Burn Times"))
		{
			itemBurnTimes = nbt.getIntArray("Item Burn Times");
		}
	}

	@Override
	public List<String> getTips(List<String> currentList)
	{
		DecimalFormat f = new DecimalFormat("#,###");
		String stored = f.format(storage.getEnergyStored());
		String max = f.format(storage.getMaxEnergyStored());
		currentList.add(stored + "/" + max + EnumChatFormatting.RED + " RF " + EnumChatFormatting.RESET + "Held");

		String s = "";
		String s1 = "";
		for (int i = 0; i < 5; i++)
		{
			s += "[" + getSizeForStack(inventory[i]) + "]" + (i == 4 ? "" : " ");
			s1 += "[" + getSizeForStack(inventory[i + 5]) + "]" + (i == 4 ? "" : " ");
		}
		currentList.add(s);
		currentList.add(s1);
		return currentList;
	}

	public String getSizeForStack(ItemStack stack)
	{
		int stackSize = stack == null ? 0 : stack.stackSize;
		String s = String.valueOf(stackSize);
		if (stackSize == 0) return EnumChatFormatting.RED + s + EnumChatFormatting.RESET;
		else if (stackSize > stack.getMaxStackSize() / 2) return EnumChatFormatting.GREEN + s + EnumChatFormatting.RESET;
		else if (stackSize <= stack.getMaxStackSize() / 2) return EnumChatFormatting.YELLOW + s + EnumChatFormatting.RESET;
		return s;
	}

	@Override
	public boolean shouldAddTip()
	{
		return true;
	}
}
