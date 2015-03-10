package main.extremeblocks.client.guis.energy;

import main.extremeblocks.Init;
import main.extremeblocks.client.containers.energy.ContainerFluxCooker;
import main.extremeblocks.tileentities.energy.TileEntityFluxCooker;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiFluxCooker extends GuiRFHolder
{
	private TileEntityFluxCooker tile;

	public GuiFluxCooker(InventoryPlayer inventory, TileEntityFluxCooker tile)
	{
		super(new ContainerFluxCooker(inventory, tile));
		this.tile = tile;
		guiTexture = new ResourceLocation(Init.MODID + ":textures/gui/flux_cooker.png");
		powerX = 158;
		powerY = 7;
		powerU = 176;
		powerV = 17;
		indX = 61;
		indY = 55;
		indU = 186;
		indV = 17;
		addPowerTooltip();
		addRedstoneTooltip();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float eye, int dont, int know)
	{
		super.drawGuiContainerBackgroundLayer(eye, dont, know);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		//GuiFurnace
		int i1 = tile.getCookProgressScaled(24);
		drawTexturedModalRect(k + 82, l + 18, 176, 0, i1 + 1, 16);

		renderPowerBar();
		if (tile.isRedstonePowered())
		{
			renderRedstoneIndicator();
		}
	}
}
