package main.extremeblocks.blocks.tileentities.guis;

import main.extremeblocks.Init;
import main.extremeblocks.blocks.tileentities.TileEntityPowerEmitter;
import main.extremeblocks.blocks.tileentities.containers.ContainerPowerEmitter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiPowerEmitter extends GuiContainer
{
	private TileEntityPowerEmitter powerEmitter;

	public GuiPowerEmitter(InventoryPlayer inventoryPlayer, TileEntityPowerEmitter tileEntity)
	{
		super(new ContainerPowerEmitter(inventoryPlayer, tileEntity));

		powerEmitter = tileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2)
	{
		fontRendererObj.drawString(powerEmitter.getInventoryName(), 6, 6, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);

		fontRendererObj.drawString("Power: " + powerEmitter.getPower(), 76 + fontRendererObj.FONT_HEIGHT, 18, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(new ResourceLocation(Init.MODID + ":textures/gui/power_emitter.png"));
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
		int i1;

		if (powerEmitter.isBurning())
		{
			i1 = powerEmitter.getBurnTimeRemainingScaled(12);
			drawTexturedModalRect(k + 17, l + 43 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
		}
	}
}