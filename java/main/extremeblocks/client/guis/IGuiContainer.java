package main.extremeblocks.client.guis;

import main.extremeblocks.client.containers.ContainerExtremeBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public interface IGuiContainer extends IInventory
{
	/* Gui Functions! */
	public void drawGuiContainerForegroundLayer(GuiExtremeBlocks<?> gui, int par1, int par2);

	public void drawGuiContainerBackgroundLayer(GuiExtremeBlocks<?> gui, float par1, int par2, int par3);

	public void updateScreen(GuiExtremeBlocks<?> gui);

	/* Container Functions! */
	public Slot[] addSlots(ContainerExtremeBlocks<?> container);

	public void addCraftingToCrafters(ContainerExtremeBlocks<?> container, ICrafting crafter);

	public void detectAndSendChanges(ContainerExtremeBlocks<?> container);

	public void updateProgressBar(ContainerExtremeBlocks<?> container, int par2, int par3);

	public boolean canInteractWith(ContainerExtremeBlocks<?> container, EntityPlayer player);

	public ItemStack transferStackInSlot(ContainerExtremeBlocks<?> container, EntityPlayer player, int par2);
}
