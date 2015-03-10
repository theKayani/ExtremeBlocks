package main.extremeblocks.tileentities.energy;

import java.text.DecimalFormat;
import java.util.List;
import main.com.hk.eb.util.StackHelper;
import main.extremeblocks.client.containers.Slots;
import main.extremeblocks.items.ItemFurnaceUpgrade;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityFluxCooker extends TileEntityMachine
{
	public int itemCookTime;

	public TileEntityFluxCooker()
	{
		super("Flux Cooker");
		inventory = new ItemStack[7];
	}

	@Override
	public boolean update()
	{
		int energyStored = getEnergyStored();
		calculatePowerFromInventory();
		if (canSmelt())
		{
			if (hasEnoughEnergy())
			{
				itemCookTime += inventory[2] == null ? 1 : inventory[2].stackSize;
				if (itemCookTime == 200)
				{
					itemCookTime = 0;
					smeltItem();
					storage.extractEnergy(1024, false);
				}
			}
		}
		else
		{
			itemCookTime = 0;
		}
		return energyStored != this.getEnergyStored();
	}

	public boolean canSmelt()
	{
		if (!isRedstonePowered()) return false;
		if (getSmeltee() == null) return false;
		ItemStack stack = getResultFor(getSmeltee());
		if (stack == null) return false;
		if (getResult() == null) return true;
		if (!getResult().isItemEqual(stack)) return false;
		int result = getResult().stackSize + stack.stackSize;
		return result <= getInventoryStackLimit() && result <= getResult().getMaxStackSize();
	}

	public void smeltItem()
	{
		if (canSmelt())
		{
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(getSmeltee());

			if (getResult() == null)
			{
				inventory[1] = itemstack.copy();
			}
			else if (getResult().getItem() == itemstack.getItem())
			{
				getResult().stackSize += itemstack.stackSize;
			}

			inventory[0] = StackHelper.consumeItem(getSmeltee());
		}
	}

	public boolean hasEnoughEnergy()
	{
		return getEnergyStored() > 1024;
	}

	public ItemStack getResultFor(ItemStack stack)
	{
		return FurnaceRecipes.smelting().getSmeltingResult(stack);
	}

	public ItemStack getResult()
	{
		return inventory[1];
	}

	public ItemStack getSmeltee()
	{
		return inventory[0];
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int scale)
	{
		return itemCookTime * scale / 200;
	}

	@Override
	public int[] getBatterySlots()
	{
		return new int[] { 3, 4, 5, 6 };
	}

	@Override
	public List<Slot> getSlots()
	{
		List<Slot> list = new Slots(this, 15, 6, 1, 4).setStartID(3);
		list.add(new Slot(this, 0, 58, 18));
		list.add(new Slot(this, 1, 117, 17));
		list.add(new Slot(this, 2, 105, 52));
		return list;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		if (stack == null) return false;
		switch (slot)
		{
			case 0:
				return FurnaceRecipes.smelting().getSmeltingResult(stack) != null;
			case 1:
				return false;
			case 2:
				return stack.getItem() instanceof ItemFurnaceUpgrade;
			case 3:
			case 4:
			case 5:
			case 6:
				return stack.getItem() instanceof IEnergyContainerItem;
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side)
	{
		return slot == 2 ? true : stack.getItem() instanceof IEnergyContainerItem ? ((IEnergyContainerItem) stack.getItem()).getEnergyStored(stack) == 0 : false;
	}

	@Override
	protected int[] getSlotsFor(ForgeDirection side)
	{
		return side == ForgeDirection.DOWN ? new int[] { 1 } : getBatterySlots();
	}

	@Override
	public List<String> getTips(List<String> currentList)
	{
		DecimalFormat f = new DecimalFormat("#,###");
		String stored = f.format(storage.getEnergyStored());
		String max = f.format(storage.getMaxEnergyStored());
		currentList.add(stored + "/" + max + EnumChatFormatting.RED + " RF " + EnumChatFormatting.RESET + "Held");
		currentList.add("Can Smelt: " + (canSmelt() ? EnumChatFormatting.GREEN + "YES" : EnumChatFormatting.RED + "NO") + EnumChatFormatting.RESET);
		return currentList;
	}

	@Override
	public boolean shouldAddTip()
	{
		return true;
	}

	@Override
	public void writeTo(NBTTagCompound nbt)
	{
		super.writeTo(nbt);
		nbt.setInteger("Item Cook Time", itemCookTime);
	}

	@Override
	public void readFrom(NBTTagCompound nbt)
	{
		super.readFrom(nbt);
	}
}
