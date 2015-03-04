package main.extremeblocks;

import main.extremeblocks.blocks.BlockEBTable;
import main.extremeblocks.blocks.abstracts.BlockGui;
import main.extremeblocks.client.containers.ContainerEBTable;
import main.extremeblocks.client.containers.ContainerEnchantmentExtractor;
import main.extremeblocks.client.containers.ContainerRevertingStation;
import main.extremeblocks.client.containers.ContainerRobot;
import main.extremeblocks.client.containers.ContainerRobotCommands;
import main.extremeblocks.client.containers.ContainerRobotInv;
import main.extremeblocks.client.containers.ContainerStorage;
import main.extremeblocks.client.containers.ContainerTrash;
import main.extremeblocks.client.guis.GuiEBTable;
import main.extremeblocks.client.guis.GuiEnchantmentExtractor;
import main.extremeblocks.client.guis.GuiRevertingStation;
import main.extremeblocks.client.guis.GuiRobot;
import main.extremeblocks.client.guis.GuiRobotCommands;
import main.extremeblocks.client.guis.GuiRobotInv;
import main.extremeblocks.client.guis.GuiStorage;
import main.extremeblocks.client.guis.GuiTrash;
import main.extremeblocks.entities.mobs.EntityRobot;
import main.extremeblocks.tileentities.TileEntityEnchantmentExtractor;
import main.extremeblocks.tileentities.TileEntityRevertingStation;
import main.extremeblocks.tileentities.TileEntityStorage;
import main.extremeblocks.tileentities.TileEntityTrash;
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
		if (block instanceof BlockGui) return ((BlockGui) block).getContainer(player.inventory, tileEntity);
		switch (ID)
		{
			case TILE_STORAGE:
				return tileEntity instanceof TileEntityStorage ? new ContainerStorage(player.inventory, (TileEntityStorage) tileEntity) : null;
			case BLOCK_EBTABLE:
				return block instanceof BlockEBTable ? new ContainerEBTable(player.inventory, world, x, y, z) : null;
			case GUI_ROBOT:
				return new ContainerRobot((EntityRobot) world.getEntityByID(entityID));
			case GUI_ROBOT_INV:
				return new ContainerRobotInv(player.inventory, (EntityRobot) world.getEntityByID(entityID));
			case GUI_ROBOT_COMMANDS:
				return new ContainerRobotCommands((EntityRobot) world.getEntityByID(entityID));
			case TILE_REVERTING_STATION:
				return tileEntity instanceof TileEntityRevertingStation ? new ContainerRevertingStation(player.inventory, (TileEntityRevertingStation) tileEntity) : null;
			case TILE_ENTITY_TRASH:
				return tileEntity instanceof TileEntityTrash ? new ContainerTrash(player.inventory, (TileEntityTrash) tileEntity) : null;
			case TILE_ENCHANTMENT_EXTRACTOR:
				return tileEntity instanceof TileEntityEnchantmentExtractor ? new ContainerEnchantmentExtractor(player.inventory, (TileEntityEnchantmentExtractor) tileEntity) : null;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (block instanceof BlockGui) return ((BlockGui) block).getGui(player.inventory, tileEntity);
		switch (ID)
		{
			case TILE_STORAGE:
				return tileEntity instanceof TileEntityStorage ? new GuiStorage(player.inventory, (TileEntityStorage) tileEntity) : null;
			case BLOCK_EBTABLE:
				return block instanceof BlockEBTable ? new GuiEBTable(player.inventory, world, x, y, z) : null;
			case GUI_ROBOT:
				return new GuiRobot(player.inventory, world, x, y, z, (EntityRobot) world.getEntityByID(entityID));
			case GUI_ROBOT_INV:
				return new GuiRobotInv(player.inventory, world, x, y, z, (EntityRobot) world.getEntityByID(entityID));
			case GUI_ROBOT_COMMANDS:
				return new GuiRobotCommands(player.inventory, world, x, y, z, (EntityRobot) world.getEntityByID(entityID));
			case TILE_REVERTING_STATION:
				return tileEntity instanceof TileEntityRevertingStation ? new GuiRevertingStation(player.inventory, (TileEntityRevertingStation) tileEntity) : null;
			case TILE_ENTITY_TRASH:
				return tileEntity instanceof TileEntityTrash ? new GuiTrash(player.inventory, (TileEntityTrash) tileEntity) : null;
			case TILE_ENCHANTMENT_EXTRACTOR:
				return tileEntity instanceof TileEntityEnchantmentExtractor ? new GuiEnchantmentExtractor(player.inventory, (TileEntityEnchantmentExtractor) tileEntity) : null;
		}
		return null;
	}
}
