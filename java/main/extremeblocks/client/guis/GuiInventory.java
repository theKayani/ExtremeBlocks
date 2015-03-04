package main.extremeblocks.client.guis;

import main.extremeblocks.client.containers.ContainerInventory;
import main.extremeblocks.tileentities.TileEntityInventory;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiInventory extends GuiEB
{
	protected ResourceLocation guiTexture;
	public final TileEntityInventory tile;

	public GuiInventory(ContainerInventory container)
	{
		super(container);
		tile = container.tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int a, int b)
	{
		String s = tile.hasCustomInventoryName() ? tile.getInventoryName() : I18n.format(tile.getInventoryName(), new Object[0]);
		fontRendererObj.drawString(s, 6, 6, 4210752);
		fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float eye, int dont, int know)
	{
		if (guiTexture != null)
		{
			renderFullTexture(guiTexture);
		}
	}
}
