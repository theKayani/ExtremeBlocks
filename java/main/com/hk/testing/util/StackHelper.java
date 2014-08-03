package main.com.hk.testing.util;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class StackHelper
{
	public static boolean isBlockOrItem(Object obj)
	{
		return obj instanceof Block || obj instanceof Item;
	}

	public static boolean canStacksMerge(ItemStack stack1, ItemStack stack2)
	{
		return ItemStack.areItemStacksEqual(stack1, stack2);
	}

	public static int mergeStacks(ItemStack mergeSource, ItemStack mergeTarget, boolean doMerge)
	{
		if (!canStacksMerge(mergeSource, mergeTarget))
		{
			return 0;
		}
		int mergeCount = Math.min(mergeTarget.getMaxStackSize() - mergeTarget.stackSize, mergeSource.stackSize);

		if (mergeCount < 1)
		{
			return 0;
		}
		if (doMerge)
		{
			mergeTarget.stackSize += mergeCount;
			mergeSource.stackSize -= mergeCount;
		}
		return mergeCount;
	}

	public static boolean[] addToInv(IInventory inv, ItemStack... itemstacks)
	{
		if (itemstacks == null)
		{
			new IllegalArgumentException("Itemstack Cannot be NULL!").printStackTrace();
			return null;
		}

		boolean[] val = new boolean[itemstacks.length];

		for (int i = 0; i < itemstacks.length; i++)
		{
			val[i] = addToInv(inv, itemstacks[i]);
		}

		return val;
	}

	public static boolean addToInv(IInventory inv, ItemStack par1ItemStack)
	{
		ArrayList<ItemStack> inventorySlots = new ArrayList<ItemStack>();
		boolean flag1 = false;
		int k = 0;
		ItemStack itemstack1 = null;

		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			inventorySlots.add(inv.getStackInSlot(i));
		}

		if (par1ItemStack.isStackable())
		{
			while (par1ItemStack.stackSize > 0 && k < inv.getSizeInventory())
			{
				itemstack1 = inventorySlots.get(k);

				if (itemstack1 != null && itemstack1.getItem() == par1ItemStack.getItem() && (!par1ItemStack.getHasSubtypes() || par1ItemStack.getItemDamage() == itemstack1.getItemDamage()) && ItemStack.areItemStackTagsEqual(par1ItemStack, itemstack1))
				{
					int l = itemstack1.stackSize + par1ItemStack.stackSize;

					if (l <= par1ItemStack.getMaxStackSize())
					{
						par1ItemStack.stackSize = 0;
						itemstack1.stackSize = l;
						inv.markDirty();
						flag1 = true;
					}
					else if (itemstack1.stackSize < par1ItemStack.getMaxStackSize())
					{
						par1ItemStack.stackSize -= par1ItemStack.getMaxStackSize() - itemstack1.stackSize;
						itemstack1.stackSize = par1ItemStack.getMaxStackSize();
						inv.markDirty();
						flag1 = true;
					}
				}
				++k;

			}
		}

		if (par1ItemStack.stackSize > 0)
		{
			k = 0;

			while (k < inv.getSizeInventory())
			{
				itemstack1 = inventorySlots.get(k);

				if (itemstack1 == null)
				{
					inv.setInventorySlotContents(k, par1ItemStack.copy());
					inv.markDirty();
					par1ItemStack.stackSize = 0;
					flag1 = true;
					break;
				}
				++k;
			}
		}

		if (itemstack1 != null && itemstack1.stackSize == 0) itemstack1 = null;
		if (par1ItemStack != null && par1ItemStack.stackSize == 0) par1ItemStack = null;

		return flag1;
	}

	public static boolean addToInvSlot(IInventory inv, ItemStack par1ItemStack, int slot)
	{
		if (par1ItemStack == null || slot < 0 || slot >= inv.getSizeInventory()) return false;
		boolean flag1 = false;
		ItemStack itemstack1 = inv.getStackInSlot(slot);

		if (par1ItemStack.isStackable())
		{
			if (itemstack1 != null && itemstack1.getItem() == par1ItemStack.getItem() && (!par1ItemStack.getHasSubtypes() || par1ItemStack.getItemDamage() == itemstack1.getItemDamage()) && ItemStack.areItemStackTagsEqual(par1ItemStack, itemstack1))
			{
				int l = itemstack1.stackSize + par1ItemStack.stackSize;

				if (l <= par1ItemStack.getMaxStackSize())
				{
					par1ItemStack.stackSize = 0;
					itemstack1.stackSize = l;
					inv.markDirty();
					flag1 = true;
				}
				else if (itemstack1.stackSize < par1ItemStack.getMaxStackSize())
				{
					par1ItemStack.stackSize -= par1ItemStack.getMaxStackSize() - itemstack1.stackSize;
					itemstack1.stackSize = par1ItemStack.getMaxStackSize();
					inv.markDirty();
					flag1 = true;
				}
			}
		}

		if (par1ItemStack.stackSize > 0)
		{
			itemstack1 = inv.getStackInSlot(slot);

			if (itemstack1 == null)
			{
				inv.setInventorySlotContents(slot, par1ItemStack.copy());
				inv.markDirty();
				par1ItemStack.stackSize = 0;
				flag1 = true;
			}
		}

		if (itemstack1 != null && itemstack1.stackSize == 0) itemstack1 = null;
		if (par1ItemStack != null && par1ItemStack.stackSize == 0) par1ItemStack = null;

		return flag1;
	}
}