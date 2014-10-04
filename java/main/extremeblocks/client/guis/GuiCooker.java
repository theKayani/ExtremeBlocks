package main.extremeblocks.client.guis;

import main.extremeblocks.Init;
import main.extremeblocks.client.containers.ContainerCooker;
import main.extremeblocks.tileentities.TileEntityCooker;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiCooker extends GuiContainer
{
	public final TileEntityCooker tile;

	public GuiCooker(InventoryPlayer inv, TileEntityCooker tile)
	{
		super(new ContainerCooker(inv, tile));
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2)
	{
		this.fontRendererObj.drawString(tile.getInventoryName(), 6, 6, 4210752);
		this.fontRendererObj.drawString("Power: " + tile.overallPower + " Watts", 45, 10, 4210752);
		this.fontRendererObj.drawString("Upgrades: " + tile.upgradeCount, 45, 21, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(new ResourceLocation(Init.MODID + ":textures/gui/cooker.png"));
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);

		if (this.tile.canUsePower())
		{
			int i1 = this.tile.getCookProgressScaled(24);
			this.drawTexturedModalRect(k + 64, l + 40, 176, 0, i1 + 1, 16);
		}
	}
}
