package main.extremeblocks;

import main.extremeblocks.blocks.BlockEBTable;
import main.extremeblocks.client.containers.ContainerCharger;
import main.extremeblocks.client.containers.ContainerEBTable;
import main.extremeblocks.client.containers.ContainerGenerator;
import main.extremeblocks.client.containers.ContainerCooker;
import main.extremeblocks.client.containers.ContainerRobot;
import main.extremeblocks.client.containers.ContainerRobotCommands;
import main.extremeblocks.client.containers.ContainerRobotInv;
import main.extremeblocks.client.containers.ContainerStorage;
import main.extremeblocks.client.guis.GuiCharger;
import main.extremeblocks.client.guis.GuiEBTable;
import main.extremeblocks.client.guis.GuiGenerator;
import main.extremeblocks.client.guis.GuiCooker;
import main.extremeblocks.client.guis.GuiRobot;
import main.extremeblocks.client.guis.GuiRobotCommands;
import main.extremeblocks.client.guis.GuiRobotInv;
import main.extremeblocks.client.guis.GuiStorage;
import main.extremeblocks.entities.mobs.EntityRobot;
import main.extremeblocks.tileentities.TileEntityCharger;
import main.extremeblocks.tileentities.TileEntityGenerator;
import main.extremeblocks.tileentities.TileEntityCooker;
import main.extremeblocks.tileentities.TileEntityStorage;
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
			case 2:
				return tileEntity instanceof TileEntityCharger ? new ContainerCharger(player.inventory, (TileEntityCharger) tileEntity) : null;
			case 3:
				return tileEntity instanceof TileEntityCooker ? new ContainerCooker(player.inventory, (TileEntityCooker) tileEntity) : null;
			case 4:
				return block instanceof BlockEBTable ? new ContainerEBTable(player.inventory, world, x, y, z) : null;
			case 5:
				return new ContainerRobot(player.inventory, world, x, y, z, (EntityRobot) world.getEntityByID(entityID));
			case 6:
				return new ContainerRobotInv(player.inventory, world, x, y, z, (EntityRobot) world.getEntityByID(entityID));
			case 7:
				return new ContainerRobotCommands(player.inventory, world, x, y, z, (EntityRobot) world.getEntityByID(entityID));
			case 8:
				return tileEntity instanceof TileEntityGenerator ? new ContainerGenerator(player.inventory, (TileEntityGenerator) tileEntity) : null;
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
			case 2:
				return tileEntity instanceof TileEntityCharger ? new GuiCharger(player.inventory, (TileEntityCharger) tileEntity) : null;
			case 3:
				return tileEntity instanceof TileEntityCooker ? new GuiCooker(player.inventory, (TileEntityCooker) tileEntity) : null;
			case 4:
				return block instanceof BlockEBTable ? new GuiEBTable(player.inventory, world, x, y, z) : null;
			case 5:
				return new GuiRobot(player.inventory, world, x, y, z, (EntityRobot) world.getEntityByID(entityID));
			case 6:
				return new GuiRobotInv(player.inventory, world, x, y, z, (EntityRobot) world.getEntityByID(entityID));
			case 7:
				return new GuiRobotCommands(player.inventory, world, x, y, z, (EntityRobot) world.getEntityByID(entityID));
			case 8:
				return tileEntity instanceof TileEntityGenerator ? new GuiGenerator(player.inventory, (TileEntityGenerator) tileEntity) : null;
		}
		return null;
	}
}
