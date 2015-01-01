package main.extremeblocks.client.guis;

import main.com.hk.eb.util.StackHelper;
import main.extremeblocks.GuiIDs;
import main.extremeblocks.Init;
import main.extremeblocks.client.containers.ContainerRobot;
import main.extremeblocks.entities.mobs.EntityRobot;
import main.extremeblocks.network.packets.PacketAddToPlayer;
import main.extremeblocks.network.packets.PacketOpenGui;
import main.extremeblocks.network.packets.PacketRemoveEntity;
import main.extremeblocks.network.packets.PacketStartTask;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiRobot extends GuiContainer implements GuiIDs
{
	private final int x, y, z;
	private final EntityRobot robot;

	public GuiRobot(InventoryPlayer inv, World world, int x, int y, int z, EntityRobot robot)
	{
		super(new ContainerRobot(robot));
		this.x = x;
		this.y = y;
		this.z = z;
		this.robot = robot;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui()
	{
		super.initGui();
		buttonList.add(new GuiButton(0, width / 2 - 35, 50, 70, 20, "Inventory"));
		buttonList.add(new GuiButton(1, width / 2 - 35, 80, 70, 20, "Begin Task"));
		buttonList.add(new GuiButton(2, width / 2 - 35, 110, 70, 20, "End Task"));
		buttonList.add(new GuiButton(3, width / 2 - 35, 140, 70, 20, "Kill"));
		buttonList.add(new GuiButton(4, width / 2 - 35, 170, 70, 20, "Commands"));
	}

	@Override
	public void actionPerformed(GuiButton b)
	{
		switch (b.id)
		{
			case 0:
			{
				new PacketOpenGui(GUI_ROBOT_INV, x, y, z).sendToServer();
				break;
			}
			case 1:
			{
				new PacketStartTask(robot, true).sendToServer();
				robot.setOnTask(true);
				mc.thePlayer.closeScreen();
				break;
			}
			case 2:
			{
				new PacketStartTask(robot, false).sendToServer();
				robot.setOnTask(false);
				mc.thePlayer.closeScreen();
				break;
			}
			case 3:
			{
				ItemStack[] stacks = StackHelper.asItemStacks(Init.robot_torso, Init.robot_leg, Init.robot_leg, Init.robot_arm, Init.robot_arm, Init.robot_head);
				new PacketAddToPlayer(stacks).sendToServer();
				new PacketRemoveEntity(robot, true).sendToServer();
				mc.thePlayer.closeScreen();
				break;
			}
			case 4:
			{
				new PacketOpenGui(GUI_ROBOT_COMMANDS, x, y, z).sendToServer();
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
