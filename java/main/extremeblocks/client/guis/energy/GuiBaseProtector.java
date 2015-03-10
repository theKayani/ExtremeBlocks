package main.extremeblocks.client.guis.energy;

import main.extremeblocks.Init;
import main.extremeblocks.blocks.energy.BlockBaseProtector.ProtectorUpgrade;
import main.extremeblocks.client.containers.energy.ContainerBaseProtector;
import main.extremeblocks.tileentities.energy.TileEntityBaseProtector;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiBaseProtector extends GuiRFHolder
{
	private final TileEntityBaseProtector tile;

	public GuiBaseProtector(InventoryPlayer inv, TileEntityBaseProtector tile)
	{
		super(new ContainerBaseProtector(inv, tile));
		this.tile = tile;
		powerX = 158;
		powerY = 7;
		powerU = 176;
		powerV = 0;
		guiTexture = new ResourceLocation(Init.MODID + ":textures/gui/base_protector.png");
		addPowerTooltip();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int a, int b)
	{
		super.drawGuiContainerForegroundLayer(a, b);
		for (int i = 0; i < ProtectorUpgrade.values().length; i++)
		{
			drawString(tile.getModifier(ProtectorUpgrade.values()[i]), 21 + i * 29, 54, true);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float eye, int dont, int know)
	{
		super.drawGuiContainerBackgroundLayer(eye, dont, know);
		renderPowerBar();
	}
}
