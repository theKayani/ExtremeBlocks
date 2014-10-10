package main.extremeblocks.client.guis;

import main.extremeblocks.Init;
import main.extremeblocks.client.containers.ContainerRevertingStation;
import main.extremeblocks.tileentities.TileEntityRevertingStation;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiRevertingStation extends GuiContainer
{
	private final TileEntityRevertingStation tile;

	public GuiRevertingStation(InventoryPlayer inventory, TileEntityRevertingStation tileEntity)
	{
		super(new ContainerRevertingStation(inventory, tileEntity));
		tile = tileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2)
	{
		fontRendererObj.drawString(tile.getInventoryName(), 6, 6, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(new ResourceLocation(Init.MODID + ":textures/gui/reverting_station.png"));
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}
}
