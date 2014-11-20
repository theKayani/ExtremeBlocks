package main.extremeblocks.client.containers;

import main.extremeblocks.items.ItemFurnaceUpgrade;
import main.extremeblocks.misc.IBattery;
import main.extremeblocks.tileentities.TileEntityCooker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerCooker extends Container
{
	public final TileEntityCooker tile;
	private int lastCookDelay, lastOverallPower, lastUpgradeCount;

	public ContainerCooker(InventoryPlayer inv, TileEntityCooker tile)
	{
		this.tile = tile;

		addSlotToContainer(new Slot(tile, 0, 10, 37));
		addSlotToContainer(new Slot(tile, 1, 10, 55));
		addSlotToContainer(new Slot(tile, 2, 37, 39));
		addSlotToContainer(new SlotFurnace(inv.player, tile, 3, 98, 40));
		addSlotToContainer(new Slot(tile, 4, 140, 50));

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
	public void addCraftingToCrafters(ICrafting c)
	{
		super.addCraftingToCrafters(c);
		c.sendProgressBarUpdate(this, 0, this.tile.cookDelay);
		c.sendProgressBarUpdate(this, 1, this.tile.overallPower);
		c.sendProgressBarUpdate(this, 2, this.tile.upgradeCount);
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i)
		{
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if (this.lastCookDelay != this.tile.cookDelay)
			{
				icrafting.sendProgressBarUpdate(this, 0, this.tile.cookDelay);
			}
			if (this.lastOverallPower != this.tile.overallPower)
			{
				icrafting.sendProgressBarUpdate(this, 1, this.tile.overallPower);
			}
			if (this.lastUpgradeCount != this.tile.upgradeCount)
			{
				icrafting.sendProgressBarUpdate(this, 2, this.tile.upgradeCount);
			}
		}

		this.lastCookDelay = this.tile.cookDelay;
		this.lastOverallPower = this.tile.overallPower;
		this.lastUpgradeCount = this.tile.upgradeCount;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int p_75137_1_, int p_75137_2_)
	{
		if (p_75137_1_ == 0)
		{
			this.tile.cookDelay = p_75137_2_;
		}
		if (p_75137_1_ == 1)
		{
			this.tile.overallPower = p_75137_2_;
		}
		if (p_75137_1_ == 2)
		{
			this.tile.upgradeCount = p_75137_2_;
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return tile.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int par2)
	{
		System.out.println("par2 == " + par2);
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(par2);
		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 < 5)
			{
				if (!this.mergeItemStack(itemstack1, 5, 41, true))
				{
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (par2 >= 5)
			{
				if (itemstack1.getItem() instanceof IBattery)
				{
					if (!this.mergeItemStack(itemstack1, 0, 2, false))
					{
						return null;
					}
				}
				else if (itemstack1.getItem() instanceof ItemFurnaceUpgrade)
				{
					if (!this.mergeItemStack(itemstack1, 4, 5, false))
					{
						return null;
					}
				}
				else if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null)
				{
					if (!this.mergeItemStack(itemstack1, 2, 3, false))
					{
						return null;
					}
				}
				else if (par2 >= 5 && par2 < 32)
				{
					if (!this.mergeItemStack(itemstack1, 33, 41, true))
					{
						return null;
					}
				}
				else if (par2 >= 32)
				{
					if (!this.mergeItemStack(itemstack1, 6, 33, false))
					{
						return null;
					}
				}
			}
			else if (!this.mergeItemStack(itemstack1, 5, 41, false))
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
