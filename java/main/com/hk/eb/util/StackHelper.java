package main.com.hk.eb.util;

import java.util.ArrayList;
import main.extremeblocks.misc.StackInv;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.oredict.OreDictionary;

public class StackHelper
{
	public static int getMaxMetadataOf(Item item)
	{
		if (item == null) return 0;
		ArrayList<ItemStack> list = JavaHelp.newArrayList();
		item.getSubItems(item, item.getCreativeTab(), list);
		return list.size();
	}

	public static boolean isBlockOrItem(Object obj)
	{
		return obj instanceof Block || obj instanceof Item;
	}

	public static boolean isEmpty(ItemStack... stacks)
	{
		if (stacks != null)
		{
			for (ItemStack stack : stacks)
			{
				if (stack != null) return false;
			}
		}
		return true;
	}

	public static void getRidOfNulls(ItemStack... stacks)
	{
		for (int i = 0; i < stacks.length; i++)
		{
			if (stacks[i] != null && stacks[i].stackSize <= 0)
			{
				stacks[i] = null;
			}
		}
	}

	public static boolean canStacksMerge(ItemStack stack1, ItemStack stack2)
	{
		return ItemStack.areItemStacksEqual(stack1, stack2);
	}

	public static int mergeStacks(ItemStack mergeSource, ItemStack mergeTarget, boolean doMerge)
	{
		if (!canStacksMerge(mergeSource, mergeTarget)) return 0;
		int mergeCount = Math.min(mergeTarget.getMaxStackSize() - mergeTarget.stackSize, mergeSource.stackSize);
		if (mergeCount < 1) return 0;
		if (doMerge)
		{
			mergeTarget.stackSize += mergeCount;
			mergeSource.stackSize -= mergeCount;
			if (mergeSource.stackSize < 0)
			{
				mergeSource = null;
			}
		}
		return mergeCount;
	}

	public static boolean[] addToInv(IInventory inv, ItemStack... itemstacks)
	{
		boolean[] val = new boolean[itemstacks.length];
		for (int i = 0; i < itemstacks.length; i++)
		{
			val[i] = addToInv(inv, itemstacks[i]);
		}
		return val;
	}

	public static boolean addToInv(IInventory inv, ItemStack par1ItemStack)
	{
		ArrayList<ItemStack> inventorySlots = JavaHelp.newArrayList();
		boolean flag1 = false;
		int k = 0;
		ItemStack itemstack1 = null;
		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			inventorySlots.add(inv.getStackInSlot(i));
		}
		if (par1ItemStack == null) return false;
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
		if (itemstack1 != null && itemstack1.stackSize == 0)
		{
			itemstack1 = null;
		}
		if (par1ItemStack != null && par1ItemStack.stackSize == 0)
		{
			par1ItemStack = null;
		}
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
		if (itemstack1 != null && itemstack1.stackSize == 0)
		{
			itemstack1 = null;
		}
		if (par1ItemStack != null && par1ItemStack.stackSize == 0)
		{
			par1ItemStack = null;
		}
		return flag1;
	}

	public static ItemStack[] combineLikeStacks(ItemStack[] stack)
	{
		StackInv inv = new StackInv(new ItemStack[stack.length]);
		addToInv(inv, stack);
		return inv.inventory;
	}

	public static ItemStack consumeItem(ItemStack stack, int amount)
	{
		if (stack != null && stack.stackSize <= amount) return null;
		for (int i = 0; i < amount; i++)
		{
			stack = consumeItem(stack);
		}
		return stack;
	}

	public static ItemStack consumeItem(ItemStack stack)
	{
		if (stack == null || stack.stackSize <= 0) return null;
		if (stack.stackSize == 1)
		{
			if (stack.getItem().hasContainerItem(stack)) return stack.getItem().getContainerItem(stack);
			else return null;
		}
		else
		{
			stack.splitStack(1);
			return stack;
		}
	}

	public static NBTTagCompound saveToNBT(ItemStack[] stacks)
	{
		NBTTagCompound compound = new NBTTagCompound();
		NBTTagList items = new NBTTagList();
		for (int i = 0; i < stacks.length; ++i)
		{
			if (stacks[i] != null)
			{
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte) i);
				stacks[i].writeToNBT(item);
				items.appendTag(item);
			}
		}
		compound.setTag("Items", items);
		return compound;
	}

	public static ItemStack[] loadFromNBT(int size, NBTTagCompound compound)
	{
		ItemStack[] inventory = new ItemStack[size];
		NBTTagList items = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < items.tagCount(); ++i)
		{
			NBTTagCompound item = items.getCompoundTagAt(i);
			byte slot = item.getByte("Slot");
			if (slot >= 0 && slot < inventory.length)
			{
				inventory[slot] = ItemStack.loadItemStackFromNBT(item);
			}
		}
		return inventory;
	}

	public static ItemStack[] asItemStacks(Item... items)
	{
		if (items.length <= 0) return new ItemStack[0];
		ItemStack[] stacks = new ItemStack[items.length];
		for (int i = 0; i < stacks.length; i++)
		{
			stacks[i] = new ItemStack(items[i]);
		}
		return stacks;
	}

	public static boolean areStacksSameTypeCrafting(ItemStack stack1, ItemStack stack2)
	{
		return OreDictionary.itemMatches(stack1, stack2, false);
	}

	public static boolean hasItem(IInventory tile, Item item)
	{
		return indexOfItem(tile, item) >= 0;
	}

	public static boolean consumeItemFrom(IInventory tile, Item item)
	{
		if (hasItem(tile, item))
		{
			int i = indexOfItem(tile, item);
			tile.setInventorySlotContents(i, consumeItem(tile.getStackInSlot(i)));
			return true;
		}
		return false;
	}

	public static void clearInv(IInventory tile)
	{
		for (int i = 0; i < tile.getSizeInventory(); i++)
		{
			tile.setInventorySlotContents(i, null);
		}
	}

	public static int indexOfItem(IInventory tile, Item item)
	{
		for (int i = 0; i < tile.getSizeInventory(); i++)
		{
			if (tile.getStackInSlot(i) != null && tile.getStackInSlot(i).getItem() == item) return i;
		}
		return -1;
	}
}