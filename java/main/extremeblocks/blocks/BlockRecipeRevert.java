package main.extremeblocks.blocks;

import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.Init;
import main.extremeblocks.blocks.abstracts.BlockGui;
import main.extremeblocks.client.containers.ContainerRevertingStation;
import main.extremeblocks.client.guis.GuiRevertingStation;
import main.extremeblocks.tileentities.TileEntityInventory;
import main.extremeblocks.tileentities.TileEntityRevertingStation;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockRecipeRevert extends BlockGui
{
	public BlockRecipeRevert()
	{
		super("Recipe Reverting Station", Material.wood);
		setCreativeTab(Init.tab_mainBlocks);
		setBlockTextureName(Init.MODID + ":recipe_reverter");
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
	{
		MPUtil.dropItemAsEntity(world, x, y, z, true, ((IInventory) world.getTileEntity(x, y, z)).getStackInSlot(0));
		super.breakBlock(world, x, y, z, par5, par6);
	}

	@Override
	public Class<? extends TileEntityInventory> getTileClass()
	{
		return TileEntityRevertingStation.class;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getGui(InventoryPlayer inventory, World world, int x, int y, int z, TileEntity tile)
	{
		return new GuiRevertingStation(inventory, (TileEntityRevertingStation) tile);
	}

	@Override
	public Container getContainer(InventoryPlayer inventory, World world, int x, int y, int z, TileEntity tile)
	{
		return new ContainerRevertingStation(inventory, (TileEntityRevertingStation) tile);
	}
}
