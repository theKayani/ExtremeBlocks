package main.extremeblocks.client.guis;

import main.extremeblocks.Init;
import main.extremeblocks.client.containers.ContainerEBIdentifier;
import main.extremeblocks.tileentities.TileEntityEBIdentifier;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiEBIdentifier extends GuiInventory
{
	private final TileEntityEBIdentifier tile;

	public GuiEBIdentifier(InventoryPlayer inv, TileEntityEBIdentifier tile)
	{
		super(new ContainerEBIdentifier(inv, tile));
		ySize = 207;
		this.tile = tile;
		guiTexture = new ResourceLocation(Init.MODID + ":textures/gui/eb_identifier.png");
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int a, int b)
	{
		fontRendererObj.drawSplitString(tile.getItemsInfo(), 9, 10, 155, 4210752);
	}
}
