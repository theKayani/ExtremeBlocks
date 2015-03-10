package main.extremeblocks.blocks;

import main.extremeblocks.Init;
import main.extremeblocks.blocks.abstracts.BlockGui;
import main.extremeblocks.client.containers.ContainerEBTable;
import main.extremeblocks.client.guis.GuiEBTable;
import main.extremeblocks.tileentities.TileEntityInventory;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEBTable extends BlockGui
{
	public static final int CRAFTING_SLOTS = 4;
	@SideOnly(Side.CLIENT)
	private IIcon topIcon;
	@SideOnly(Side.CLIENT)
	private IIcon frontIcon;

	public BlockEBTable()
	{
		super("EB Crafting Table", Material.wood);
		setHardness(2.5F);
		setCreativeTab(Init.tab_mainBlocks);
		setBlockTextureName(Init.MODID + ":crafting_table");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return side == 1 ? topIcon : side == 0 ? Blocks.planks.getIcon(side, 0) : side != 2 && side != 4 ? blockIcon : frontIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
		blockIcon = p_149651_1_.registerIcon(getTextureName() + "_side");
		topIcon = p_149651_1_.registerIcon(getTextureName() + "_top");
		frontIcon = p_149651_1_.registerIcon(getTextureName() + "_front");
	}

	@Override
	public boolean hasTile()
	{
		return false;
	}

	@Override
	public Class<? extends TileEntityInventory> getTileClass()
	{
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getGui(InventoryPlayer inventory, World world, int x, int y, int z, TileEntity tile)
	{
		return new GuiEBTable(inventory, world, x, y, z);
	}

	@Override
	public Container getContainer(InventoryPlayer inventory, World world, int x, int y, int z, TileEntity tile)
	{
		return new ContainerEBTable(inventory, world, x, y, z);
	}
}
