package main.extremeblocks.client.containers;

import main.extremeblocks.entities.mobs.EntityRobot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ContainerRobotInv extends Container
{
	private final EntityRobot robot;
	private static final int HELD_ITEM = 0;
	private static final int ARMOR_START = 1;
	private static final int ARMOR_END = 5;
	private static final int ROBOT_INV_START = 5;
	private static final int ROBOT_INV_END = 32;
	private static final int PLAYER_INV_START = 32;
	private static final int HOTBAR_END = 68;

	public ContainerRobotInv(InventoryPlayer inv, EntityRobot robot)
	{
		this.robot = robot;
		addSlotToContainer(new Slot(robot.inv, 0, 8, 64));
		for (int i = 0; i < 4; i++)
		{
			final int k = i;
			addSlotToContainer(new Slot(robot.inv, i + 1, i * 18 + 98, 64)
			{
				@Override
				public int getSlotStackLimit()
				{
					return 1;
				}

				@Override
				public boolean isItemValid(ItemStack stack)
				{
					if (stack == null) return false;
					return stack.getItem().isValidArmor(stack, k, ContainerRobotInv.this.robot);
				}

				@Override
				public IIcon getBackgroundIconIndex()
				{
					return ItemArmor.func_94602_b(k);
				}
			});
		}
		for (int j = 0; j < 3; ++j)
		{
			for (int k = 0; k < 9; ++k)
			{
				addSlotToContainer(new Slot(robot.inv, k + j * 9 + 5, 8 + k * 18, 8 + j * 18));
			}
		}
		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				addSlotToContainer(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		for (int i = 0; i < 9; ++i)
		{
			addSlotToContainer(new Slot(inv, i, 8 + i * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return player.getDistanceToEntity(robot) <= 8.0D;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int par2)
	{
		System.out.println("Slot ID: " + par2);
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(par2);
		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (par2 == HELD_ITEM)
			{
				if (!mergeItemStack(itemstack1, PLAYER_INV_START, HOTBAR_END, true)) return null;
			}
			else if (itemstack.getItem() instanceof ItemArmor && !((Slot) inventorySlots.get(1 + ((ItemArmor) itemstack.getItem()).armorType)).getHasStack())
			{
				int j = 1 + ((ItemArmor) itemstack.getItem()).armorType;

				if (!mergeItemStack(itemstack1, j, j + 1, false)) return null;
			}
			else if (par2 >= ARMOR_START && par2 < ARMOR_END)
			{
				if (!mergeItemStack(itemstack1, PLAYER_INV_START, HOTBAR_END, true)) return null;
			}
			else if (par2 >= ROBOT_INV_START && par2 < ROBOT_INV_END)
			{
				if (!mergeItemStack(itemstack1, PLAYER_INV_START, HOTBAR_END, true)) return null;
			}
			else if (par2 >= PLAYER_INV_START && par2 < HOTBAR_END)
			{
				if (!mergeItemStack(itemstack1, ROBOT_INV_START, ROBOT_INV_END, false)) return null;
			}
			else if (!mergeItemStack(itemstack1, PLAYER_INV_START, HOTBAR_END, false)) return null;

			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack) null);
			}
			else
			{
				slot.onSlotChanged();
			}
			if (itemstack1.stackSize == itemstack.stackSize) return null;
			slot.onPickupFromSlot(player, itemstack1);
		}
		return itemstack;
	}
}
