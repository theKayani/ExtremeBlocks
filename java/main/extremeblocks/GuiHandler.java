package main.extremeblocks;

import main.extremeblocks.blocks.abstracts.BlockGui;
import main.extremeblocks.client.containers.ContainerRobot;
import main.extremeblocks.client.containers.ContainerRobotCommands;
import main.extremeblocks.client.containers.ContainerRobotInv;
import main.extremeblocks.client.guis.GuiRobot;
import main.extremeblocks.client.guis.GuiRobotCommands;
import main.extremeblocks.client.guis.GuiRobotInv;
import main.extremeblocks.entities.mobs.EntityRobot;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler, GuiIDs
{
	public static int entityID;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (block instanceof BlockGui) return ((BlockGui) block).getContainer(player.inventory, world, x, y, z, tileEntity);
		switch (ID)
		{
			case GUI_ROBOT:
				return new ContainerRobot((EntityRobot) world.getEntityByID(entityID));
			case GUI_ROBOT_INV:
				return new ContainerRobotInv(player.inventory, (EntityRobot) world.getEntityByID(entityID));
			case GUI_ROBOT_COMMANDS:
				return new ContainerRobotCommands((EntityRobot) world.getEntityByID(entityID));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (block instanceof BlockGui) return ((BlockGui) block).getGui(player.inventory, world, x, y, z, tileEntity);
		switch (ID)
		{
			case GUI_ROBOT:
				return new GuiRobot(player.inventory, world, x, y, z, (EntityRobot) world.getEntityByID(entityID));
			case GUI_ROBOT_INV:
				return new GuiRobotInv(player.inventory, world, x, y, z, (EntityRobot) world.getEntityByID(entityID));
			case GUI_ROBOT_COMMANDS:
				return new GuiRobotCommands(player.inventory, world, x, y, z, (EntityRobot) world.getEntityByID(entityID));
		}
		return null;
	}
}
