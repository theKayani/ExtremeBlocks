package main.extremeblocks.client.guis;

import main.extremeblocks.client.containers.ContainerExtremeBlocks;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiExtremeBlocks<E extends IGuiContainer> extends GuiContainer
{
	private E tileEntity;

	public GuiExtremeBlocks(InventoryPlayer inventoryPlayer, E tileEntity)
	{
		super(new ContainerExtremeBlocks<E>(inventoryPlayer, tileEntity));
		this.tileEntity = tileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		tileEntity.drawGuiContainerForegroundLayer(this, par1, par2);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		tileEntity.drawGuiContainerBackgroundLayer(this, par1, par2, par3);
	}
}