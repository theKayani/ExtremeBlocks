package main.extremeblocks.blocks.tileentities.guis;

import main.extremeblocks.blocks.tileentities.TileEntityStorage;
import main.extremeblocks.blocks.tileentities.containers.ContainerStorage;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiStorage extends GuiContainer
{
	private TileEntityStorage te;

	public GuiStorage(InventoryPlayer inventoryPlayer, TileEntityStorage tileEntity)
	{
		super(new ContainerStorage(inventoryPlayer, tileEntity));
		te = tileEntity;
		this.xSize = te.getXSize();
		this.ySize = te.getYSize();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2)
	{
		this.fontRendererObj.drawString(te.getInventoryName(), 6, 6, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		ResourceLocation texture = te.getTexture();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(texture);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}