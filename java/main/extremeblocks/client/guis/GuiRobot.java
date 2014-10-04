package main.extremeblocks.client.guis;

import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.Init;
import main.extremeblocks.client.containers.ContainerRobot;
import main.extremeblocks.entities.mobs.EntityRobot;
import main.extremeblocks.network.packets.PacketOpenGui;
import main.extremeblocks.network.packets.PacketRemoveEntity;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiRobot extends GuiContainer
{
	private final InventoryPlayer inv;
	private final World world;
	private final int x, y, z;
	private final EntityRobot robot;

	public GuiRobot(InventoryPlayer inv, World world, int x, int y, int z, EntityRobot robot)
	{
		super(new ContainerRobot(inv, world, x, y, z, robot));
		this.inv = inv;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.robot = robot;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		this.buttonList.add(new GuiButton(0, width / 2 - 35, 50, 70, 20, "Inventory"));
		this.buttonList.add(new GuiButton(1, width / 2 - 35, 80, 70, 20, "Begin Task"));
		this.buttonList.add(new GuiButton(2, width / 2 - 35, 110, 70, 20, "End Task"));
		this.buttonList.add(new GuiButton(3, width / 2 - 35, 140, 70, 20, "Kill"));
		this.buttonList.add(new GuiButton(4, width / 2 - 35, 170, 70, 20, "Commands"));
	}

	@Override
	public void actionPerformed(GuiButton b)
	{
		switch (b.id)
		{
			case 0:
			{
				MPUtil.sendToServer(new PacketOpenGui(6, x, y, z));
				break;
			}
			case 1:
			{
				robot.syncServerAndClient(true);
				this.mc.thePlayer.closeScreen();
				break;
			}
			case 2:
			{
				robot.endTask();
				robot.syncServerAndClient(false);
				this.mc.thePlayer.closeScreen();
				break;
			}
			case 3:
			{
				MPUtil.sendToServer(new PacketRemoveEntity(robot, true));
				this.mc.thePlayer.closeScreen();
				break;
			}
			case 4:
			{
				MPUtil.sendToServer(new PacketOpenGui(7, x, y, z));
				break;
			}
		}
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
