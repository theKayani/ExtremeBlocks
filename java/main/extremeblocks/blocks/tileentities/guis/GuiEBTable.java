package main.extremeblocks.blocks.tileentities.guis;

import main.extremeblocks.Init;
import main.extremeblocks.blocks.tileentities.containers.ContainerEBTable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiEBTable extends GuiContainer
{	
	public GuiEBTable(InventoryPlayer inventoryplayer, World world, int i, int j, int k)
	{
		super(new ContainerEBTable(inventoryplayer, world, i, j, k));
		xSize = 175;
		ySize = 175;
	}

	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRendererObj.drawString(StatCollector.translateToLocal("EB Table"), 90, 5, 4210752);
		//this.fontRendererObj.drawString(StatCollector.translateToLocal("Crafting Table"), 116, 20, 4210752);
		//this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 - 14, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(new ResourceLocation(Init.MODID + ":textures/gui/crafting_table.png"));
		int l = (width - xSize) / 2;
		int i1 = (height - ySize) / 2;
		drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
	}
}