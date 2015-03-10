package main.extremeblocks.client.guis.energy;

import main.extremeblocks.Init;
import main.extremeblocks.client.containers.energy.ContainerFuelGenerator;
import main.extremeblocks.tileentities.energy.TileEntityFuelGenerator;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiFuelGenerator extends GuiRFHolder
{
	private final TileEntityFuelGenerator tile;

	public GuiFuelGenerator(InventoryPlayer inventory, TileEntityFuelGenerator tile)
	{
		super(new ContainerFuelGenerator(inventory, tile));
		guiTexture = new ResourceLocation(Init.MODID + ":textures/gui/fuel_generator.png");
		ySize = 177;
		this.tile = tile;
		powerX = 157;
		powerY = 10;
		powerU = 176;
		powerV = 13;
		indX = 127;
		indY = 60;
		indU = 190;
		indV = 0;
		addPowerTooltip();
		addRedstoneTooltip();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int a, int b)
	{
		String s = tile.hasCustomInventoryName() ? tile.getInventoryName() : I18n.format(tile.getInventoryName(), new Object[0]);
		fontRendererObj.drawString(s, 6, 6, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float eye, int dont, int know)
	{
		super.drawGuiContainerBackgroundLayer(eye, dont, know);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;

		for (int i = 0; i < 10; i++)
		{
			if (tile.isBurning(i))
			{
				int i1 = tile.getBurnTimeRemainingScaled(13, i);
				drawTexturedModalRect((i >= 5 ? (i - 5) * 20 : i * 20) + k + 11, (i >= 5 ? 37 : 0) + l + 53 - i1, 176, 12 - i1, 14, i1 + 1);
			}
		}
		renderPowerBar();
		if (tile.isRedstonePowered())
		{
			renderRedstoneIndicator();
		}
	}
}
