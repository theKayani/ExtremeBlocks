package main.extremeblocks.blocks.tileentities.containers;

import main.extremeblocks.blocks.tileentities.TileEntityPowerEmitter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerPowerEmitter extends Container
{
	private TileEntityPowerEmitter powerEmitter;
	private int lastBurnTime, lastItemBurnTime;

	public ContainerPowerEmitter(IInventory par1IInventory, TileEntityPowerEmitter par2TileEntityTiny)
	{
		powerEmitter = par2TileEntityTiny;

		addSlotToContainer(new Slot(par2TileEntityTiny, 0, 17, 23));

		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				addSlotToContainer(new Slot(par1IInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i)
		{
			addSlotToContainer(new Slot(par1IInventory, i, 8 + i * 18, 142));
		}
	}

	public void addCraftingToCrafters(ICrafting par1ICrafting)
	{
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 1, powerEmitter.furnaceBurnTime);
		par1ICrafting.sendProgressBarUpdate(this, 2, powerEmitter.currentItemBurnTime);
	}

	/**
	 * Looks for changes made in the container, sends them to every listener.
	 */
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (int i = 0; i < crafters.size(); ++i)
		{
			ICrafting icrafting = (ICrafting) crafters.get(i);

			if (lastBurnTime != powerEmitter.furnaceBurnTime)
			{
				icrafting.sendProgressBarUpdate(this, 1, powerEmitter.furnaceBurnTime);
			}

			if (lastItemBurnTime != powerEmitter.currentItemBurnTime)
			{
				icrafting.sendProgressBarUpdate(this, 2, powerEmitter.currentItemBurnTime);
			}
		}

		lastBurnTime = powerEmitter.furnaceBurnTime;
		lastItemBurnTime = powerEmitter.currentItemBurnTime;
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2)
	{
		if (par1 == 1)
		{
			powerEmitter.furnaceBurnTime = par2;
		}

		if (par1 == 2)
		{
			powerEmitter.currentItemBurnTime = par2;
		}
	}

	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return powerEmitter.isUseableByPlayer(par1EntityPlayer);
	}

	public ItemStack transferStackInSlot(EntityPlayer player, int par2)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(par2);

		if (slot != null && slot.getHasStack())
		{
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 0)
            {
                if (!this.mergeItemStack(itemstack1, 1, 37, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (itemstack1.stackSize > 0 && TileEntityFurnace.isItemFuel(itemstack1))
            {
                if (!this.mergeItemStack(itemstack1, 0, 1, false))
                {
                    return null;
                }
            }
            else if (par2 >= 1 && par2 < 28)
            {
                if (!this.mergeItemStack(itemstack1, 28, 37, false))
                {
                    return null;
                }
            }
            else if (par2 >= 28 && par2 < 37)
            {
                if (!this.mergeItemStack(itemstack1, 1, 28, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 1, 37, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
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
