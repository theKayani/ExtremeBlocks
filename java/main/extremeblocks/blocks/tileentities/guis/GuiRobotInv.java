package main.extremeblocks.blocks.tileentities.guis;

import main.extremeblocks.Init;
import main.extremeblocks.blocks.tileentities.containers.ContainerRobotInv;
import main.extremeblocks.entities.mobs.EntityRobot;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiRobotInv extends GuiContainer
{
	private final EntityRobot robot;

	public GuiRobotInv(InventoryPlayer inventoryPlayer, World world, int x, int y, int z, EntityRobot robot)
	{
		super(new ContainerRobotInv(inventoryPlayer, world, x, y, z, robot));
		this.robot = robot;
		this.robot.stayStill = true;
	}

	@Override
	public void onGuiClosed()
	{
		this.robot.stayStill = false;
		super.onGuiClosed();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(new ResourceLocation(Init.MODID + ":textures/gui/robot_inv.png"));
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
		int i1;
	}
}
