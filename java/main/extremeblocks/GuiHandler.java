package main.extremeblocks;

import main.extremeblocks.blocks.BlockEBTable;
import main.extremeblocks.blocks.tileentities.TileEntityStorage;
import main.extremeblocks.blocks.tileentities.containers.ContainerEBTable;
import main.extremeblocks.blocks.tileentities.containers.ContainerRobot;
import main.extremeblocks.blocks.tileentities.containers.ContainerRobotCommands;
import main.extremeblocks.blocks.tileentities.containers.ContainerRobotInv;
import main.extremeblocks.blocks.tileentities.containers.ContainerStorage;
import main.extremeblocks.blocks.tileentities.guis.GuiEBTable;
import main.extremeblocks.blocks.tileentities.guis.GuiRobot;
import main.extremeblocks.blocks.tileentities.guis.GuiRobotCommands;
import main.extremeblocks.blocks.tileentities.guis.GuiRobotInv;
import main.extremeblocks.blocks.tileentities.guis.GuiStorage;
import main.extremeblocks.entities.mobs.EntityRobot;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	public static int entityID;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		switch (ID)
		{
			case 1:
				return tileEntity instanceof TileEntityStorage ? new ContainerStorage(player.inventory, (TileEntityStorage) tileEntity) : null;
			case 4:
				return block instanceof BlockEBTable ? new ContainerEBTable(player.inventory, world, x, y, z) : null;
			case 5:
				return new ContainerRobot(player.inventory, world, x, y, z, (EntityRobot) world.getEntityByID(entityID));
			case 6:
				return new ContainerRobotInv(player.inventory, world, x, y, z, (EntityRobot) world.getEntityByID(entityID));
			case 7:
				return new ContainerRobotCommands(player.inventory, world, x, y, z, (EntityRobot) world.getEntityByID(entityID));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		switch (ID)
		{
			case 1:
				return tileEntity instanceof TileEntityStorage ? new GuiStorage(player.inventory, (TileEntityStorage) tileEntity) : null;
			case 4:
				return block instanceof BlockEBTable ? new GuiEBTable(player.inventory, world, x, y, z) : null;
			case 5:
				return new GuiRobot(player.inventory, world, x, y, z, (EntityRobot) world.getEntityByID(entityID));
			case 6:
				return new GuiRobotInv(player.inventory, world, x, y, z, (EntityRobot) world.getEntityByID(entityID));
			case 7:
				return new GuiRobotCommands(player.inventory, world, x, y, z, (EntityRobot) world.getEntityByID(entityID));
		}
		return null;
	}
}
