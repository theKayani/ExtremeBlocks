package main.extremeblocks.client.guis;

import main.extremeblocks.Init;
import main.extremeblocks.client.containers.ContainerRobotCommands;
import main.extremeblocks.entities.mobs.EntityRobot;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiRobotCommands extends GuiContainer
{
	private final InventoryPlayer inv;
	private final World world;
	private final int x, y, z;
	private final EntityRobot robot;

	public GuiRobotCommands(InventoryPlayer inv, World world, int x, int y, int z, EntityRobot robot)
	{
		super(new ContainerRobotCommands(inv, world, x, y, z, robot));
		this.inv = inv;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.robot = robot;
	}

	@Override
	public void onGuiClosed()
	{
		this.robot.stayStill = false;
		super.onGuiClosed();
	}

	@Override
	public void initGui()
	{
		super.initGui();
		this.buttonList.add(new GuiButton(0, width / 2 - 45, 50, 90, 20, "Set This Home"));
		this.buttonList.add(new GuiButton(1, width / 2 - 45, 80, 90, 20, "Remove Home"));
		this.buttonList.add(new GuiButton(2, width / 2 - 45, 110, 90, 20, "Stay Still"));
		this.buttonList.add(new GuiButton(3, width / 2 - 45, 140, 90, 20, "Don't Stay Still"));
	}

	@Override
	public void actionPerformed(GuiButton b)
	{
		switch (b.id)
		{
			case 0:
			{
				robot.hasHome = true;
				robot.homePosition = new int[] { x, y, z };
				break;
			}
			case 1:
			{
				robot.hasHome = false;
				robot.homePosition = new int[] { 0, 0, 0 };
				break;
			}
			case 2:
			{
				robot.stayStill = true;
				break;
			}
			case 3:
			{
				robot.stayStill = false;
				Vec3 vec = RandomPositionGenerator.findRandomTarget(robot, 10, 3);
				robot.getNavigator().tryMoveToXYZ(vec.xCoord, vec.yCoord, vec.zCoord, 1.0F);
				break;
			}
		}
		robot.syncServerAndClient(false);
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(new ResourceLocation(Init.MODID + ":textures/gui/power_receiver.png"));
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}
}
