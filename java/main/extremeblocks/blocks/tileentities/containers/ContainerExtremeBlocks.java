package main.extremeblocks.blocks.tileentities.containers;

import main.extremeblocks.blocks.tileentities.guis.IGuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerExtremeBlocks<E extends IGuiContainer> extends Container
{
	private E tileEntity;

	public ContainerExtremeBlocks(IInventory par1IInventory, E par2TileEntity)
	{
		tileEntity = par2TileEntity;
		Slot[] slots = tileEntity.addSlots(this);
		for (int i = 0; i < slots.length; i++)
		{
			if (slots[i] != null) addSlotToContainer(slots[i]);
		}
	}

	public void addCraftingToCrafters(ICrafting par1ICrafting)
	{
		super.addCraftingToCrafters(par1ICrafting);
		tileEntity.addCraftingToCrafters(this, par1ICrafting);
	}

	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		tileEntity.detectAndSendChanges(this);
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2)
	{
		tileEntity.updateProgressBar(this, par1, par2);
	}

	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return tileEntity.canInteractWith(this, par1EntityPlayer);
	}

	public ItemStack transferStackInSlot(EntityPlayer player, int par2)
	{
		return tileEntity.transferStackInSlot(this, player, par2);
	}
}
