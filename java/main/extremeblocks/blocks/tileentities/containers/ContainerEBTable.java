package main.extremeblocks.blocks.tileentities.containers;

import main.extremeblocks.Init;
import main.extremeblocks.blocks.BlockEBTable;
import main.extremeblocks.util.EBCraftingManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerEBTable extends Container
{
	public InventoryCrafting craftMatrix = new InventoryCrafting(this, BlockEBTable.CRAFTING_SLOTS, BlockEBTable.CRAFTING_SLOTS);
	public IInventory craftResult = new InventoryCraftResult();
	private World worldObj;
	private int posX;
	private int posY;
	private int posZ;

	public ContainerEBTable(InventoryPlayer inventoryplayer, World world, int i, int j, int k)
	{
		worldObj = world;
		posX = i;
		posY = j;
		posZ = k;
		this.addSlotToContainer(new SlotEBCrafting(this, inventoryplayer.player, craftMatrix, craftResult, 0, 131, 36));
		for (int l = 0; l < BlockEBTable.CRAFTING_SLOTS; l++)
		{
			for (int k1 = 0; k1 < BlockEBTable.CRAFTING_SLOTS; k1++)
			{
				this.addSlotToContainer(new Slot(craftMatrix, k1 + l * BlockEBTable.CRAFTING_SLOTS, 13 + k1 * 18, 12 + l * 18));
			}
		}
		for (int i1 = 0; i1 < 3; i1++)
		{
			for (int l1 = 0; l1 < 9; l1++)
			{
				this.addSlotToContainer(new Slot(inventoryplayer, l1 + i1 * 9 + 9, 8 + l1 * 18, 94 + i1 * 18));
			}
		}
		for (int j1 = 0; j1 < 9; j1++)
		{
			this.addSlotToContainer(new Slot(inventoryplayer, j1, 8 + j1 * 18, 152));
		}
		onCraftMatrixChanged(craftMatrix);
	}

	public void onCraftMatrixChanged(IInventory iinventory)
	{
		craftResult.setInventorySlotContents(0, EBCraftingManager.INSTANCE.findMatchingRecipe(craftMatrix, worldObj));
	}

	public void onContainerClosed(EntityPlayer entityplayer)
	{
		super.onContainerClosed(entityplayer);
		if (worldObj.isRemote)
		{
			return;
		}
		for (int i = 0; i < 16; i++)
		{
			ItemStack itemstack = craftMatrix.getStackInSlot(i);
			if (itemstack != null)
			{
				entityplayer.dropPlayerItemWithRandomChoice(itemstack, false);
			}
		}
	}

	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		if (worldObj.getBlock(posX, posY, posZ) != Init.eb_table) return false;
		else return entityplayer.getDistanceSq((double) posX + 0.5D, (double) posY + 0.5D, (double) posZ + 0.5D) <= 64D;
	}

	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotID)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(slotID);
		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (slotID == 0)
			{
				if (!this.mergeItemStack(itemstack1, 17, 52, true))
				{
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (slotID >= 17 && slotID < 44)
			{
				if (!this.mergeItemStack(itemstack1, 44, 52, false))
				{
					return null;
				}
			}
			else if (slotID >= 44 && slotID < 52)
			{
				if (!this.mergeItemStack(itemstack1, 17, 44, false))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 17, 52, false))
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
			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
			craftResult.setInventorySlotContents(0, EBCraftingManager.INSTANCE.findMatchingRecipe(craftMatrix, worldObj));
		}
		return itemstack;
	}

	public boolean func_94530_a(ItemStack p_94530_1_, Slot p_94530_2_)
	{
		return p_94530_2_.inventory != this.craftResult && super.func_94530_a(p_94530_1_, p_94530_2_);
	}
}