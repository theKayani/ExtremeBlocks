package main.extremeblocks;

import main.extremeblocks.blocks.BlockEBTable;
import main.extremeblocks.blocks.tileentities.TileEntityPowerEmitter;
import main.extremeblocks.blocks.tileentities.TileEntityPowerReceiver;
import main.extremeblocks.blocks.tileentities.TileEntityStorage;
import main.extremeblocks.blocks.tileentities.containers.ContainerEBTable;
import main.extremeblocks.blocks.tileentities.containers.ContainerPowerEmitter;
import main.extremeblocks.blocks.tileentities.containers.ContainerPowerReceiver;
import main.extremeblocks.blocks.tileentities.containers.ContainerStorage;
import main.extremeblocks.blocks.tileentities.guis.GuiEBTable;
import main.extremeblocks.blocks.tileentities.guis.GuiPowerEmitter;
import main.extremeblocks.blocks.tileentities.guis.GuiPowerReceiver;
import main.extremeblocks.blocks.tileentities.guis.GuiStorage;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
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
				return tileEntity instanceof TileEntityPowerEmitter ? new ContainerPowerEmitter(player.inventory, (TileEntityPowerEmitter) tileEntity) : null;
			case 3:
				return tileEntity instanceof TileEntityPowerReceiver ? new ContainerPowerReceiver((TileEntityPowerReceiver) tileEntity) : null;
			case 4:
				return block instanceof BlockEBTable ? new ContainerEBTable(player.inventory, world, x, y, z) : null;
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
				return tileEntity instanceof TileEntityPowerEmitter ? new GuiPowerEmitter(player.inventory, (TileEntityPowerEmitter) tileEntity) : null;
			case 3:
				return tileEntity instanceof TileEntityPowerReceiver ? new GuiPowerReceiver((TileEntityPowerReceiver) tileEntity) : null;
			case 4:
				return block instanceof BlockEBTable ? new GuiEBTable(player.inventory, world, x, y, z) : null;
		}
		return null;
	}
}
