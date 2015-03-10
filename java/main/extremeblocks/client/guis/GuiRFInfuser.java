package main.extremeblocks.client.guis;

import main.extremeblocks.Init;
import main.extremeblocks.client.containers.energy.ContainerRFInfuser;
import main.extremeblocks.client.guis.energy.GuiRFHolder;
import main.extremeblocks.tileentities.energy.TileEntityRFInfuser;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiRFInfuser extends GuiRFHolder
{
	public GuiRFInfuser(InventoryPlayer inventory, TileEntityRFInfuser tile)
	{
		super(new ContainerRFInfuser(inventory, tile));
		guiTexture = new ResourceLocation(Init.MODID + ":textures/gui/rf_infuser.png");
		powerX = 157;
		powerY = 8;
		powerU = 176;
		powerV = 0;
		indX = 131;
		indY = 24;
		indU = 186;
		indV = 0;
		addPowerTooltip();
		addRedstoneTooltip();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float eye, int dont, int know)
	{
		super.drawGuiContainerBackgroundLayer(eye, dont, know);
		renderPowerBar();
		if (tile.isRedstonePowered())
		{
			renderRedstoneIndicator();
		}
	}
}
