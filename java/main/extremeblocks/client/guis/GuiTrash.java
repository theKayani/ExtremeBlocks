package main.extremeblocks.client.guis;

import main.extremeblocks.Init;
import main.extremeblocks.client.containers.ContainerTrash;
import main.extremeblocks.network.packets.PacketClearInv;
import main.extremeblocks.tileentities.TileEntityTrash;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiTrash extends GuiContainer
{
	private GuiButton button;
	private TileEntityTrash te;

	public GuiTrash(InventoryPlayer inventoryPlayer, TileEntityTrash tileEntity)
	{
		super(new ContainerTrash(inventoryPlayer, tileEntity));
		te = tileEntity;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		buttonList.add(button = new GuiButton(0, width / 2 - 65, 70, 50, 20, "Empty"));
	}

	@Override
	public void updateScreen()
	{
		super.updateScreen();
		button.enabled = te.isPowered();
	}

	@Override
	protected void actionPerformed(GuiButton b)
	{
		if (te.isPowered() && b.id == 0)
		{
			new PacketClearInv(te.xCoord, te.yCoord, te.zCoord).sendToServer();
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2)
	{
		fontRendererObj.drawString(te.getInventoryName(), 6, 6, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		ResourceLocation texture = new ResourceLocation(Init.MODID + ":textures/gui/trash_can.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(texture);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}