package main.extremeblocks.blocks.tileentities.guis;

import main.extremeblocks.Init;
import main.extremeblocks.blocks.tileentities.containers.ContainerExtremeBlocks;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

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