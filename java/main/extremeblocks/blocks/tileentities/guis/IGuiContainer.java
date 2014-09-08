package main.extremeblocks.blocks.tileentities.guis;

import main.extremeblocks.blocks.tileentities.containers.ContainerExtremeBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public interface IGuiContainer extends IInventory
{
	/* Gui Functions! */
	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	public void drawGuiContainerForegroundLayer(GuiExtremeBlocks gui, int par1, int par2);

	public void drawGuiContainerBackgroundLayer(GuiExtremeBlocks gui, float par1, int par2, int par3);

	/* Container Functions! */
	public Slot[] addSlots(ContainerExtremeBlocks container);

	public void addCraftingToCrafters(ContainerExtremeBlocks container, ICrafting crafter);

	/**
	 * Looks for changes made in the container, sends them to every listener.
	 */
	public void detectAndSendChanges(ContainerExtremeBlocks container);

	public void updateProgressBar(ContainerExtremeBlocks container, int par2, int par3);

	public boolean canInteractWith(ContainerExtremeBlocks container, EntityPlayer player);

	/**
	 * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	 */
	public ItemStack transferStackInSlot(ContainerExtremeBlocks container, EntityPlayer player, int par2);
}
