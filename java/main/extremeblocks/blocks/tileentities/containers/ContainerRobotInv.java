package main.extremeblocks.blocks.tileentities.containers;

import main.extremeblocks.entities.mobs.EntityRobot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ContainerRobotInv extends Container
{
	private final EntityRobot robot;
	private final int x, y, z;
	private final World world;
	private final InventoryPlayer inv;
	private static final int HELD_ITEM = 0;
	private static final int ARMOR_START = 1;
	private static final int ARMOR_END = 5;
	private static final int ROBOT_INV_START = 5;
	private static final int ROBOT_INV_END = 32;
	private static final int PLAYER_INV_START = 32;
	private static final int PLAYER_INV_END = 59;
	private static final int HOTBAR_START = 59;
	private static final int HOTBAR_END = 68;

	public ContainerRobotInv(InventoryPlayer inv, World world, int x, int y, int z, final EntityRobot robot)
	{
		this.inv = inv;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
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
					return stack.getItem().isValidArmor(stack, k, robot);
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
		return player.getDistance(this.x + 0.5D, this.y + 0.5D, this.z + 0.5D) <= 8.0D;
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
				if (!this.mergeItemStack(itemstack1, PLAYER_INV_START, HOTBAR_END, true))
				{
					return null;
				}
			}
			else if (itemstack.getItem() instanceof ItemArmor && !((Slot) this.inventorySlots.get(1 + ((ItemArmor) itemstack.getItem()).armorType)).getHasStack())
			{
				int j = 1 + ((ItemArmor) itemstack.getItem()).armorType;

				if (!this.mergeItemStack(itemstack1, j, j + 1, false))
				{
					return null;
				}
			}
			else if (par2 >= ARMOR_START && par2 < ARMOR_END)
			{
				if (!this.mergeItemStack(itemstack1, PLAYER_INV_START, HOTBAR_END, true))
				{
					return null;
				}
			}
			else if (par2 >= ROBOT_INV_START && par2 < ROBOT_INV_END)
			{
				if (!this.mergeItemStack(itemstack1, PLAYER_INV_START, HOTBAR_END, true))
				{
					return null;
				}
			}
			else if (par2 >= PLAYER_INV_START && par2 < HOTBAR_END)
			{
				if (!this.mergeItemStack(itemstack1, ROBOT_INV_START, ROBOT_INV_END, false))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(itemstack1, PLAYER_INV_START, HOTBAR_END, false))
			{
				return null;
			}

			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack) null);
			}
			else
			{
				slot.onSlotChanged();
			}
			if (itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}
			slot.onPickupFromSlot(player, itemstack1);
		}
		return itemstack;
	}
}
