package main.extremeblocks.blocks;

import main.extremeblocks.Init;
import main.extremeblocks.Vars;
import main.extremeblocks.blocks.abstracts.BlockGui;
import main.extremeblocks.client.containers.ContainerTrash;
import main.extremeblocks.client.guis.GuiTrash;
import main.extremeblocks.tileentities.TileEntityInventory;
import main.extremeblocks.tileentities.TileEntityTrash;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTrash extends BlockGui
{
	public BlockTrash()
	{
		super("Trash", Material.iron);
		setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.8F, 0.8F);
		setBlockTextureName(Init.MODID + ":trash");
		setCreativeTab(Init.tab_mainBlocks);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int flag)
	{
		TileEntityInventory.dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, block, flag);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (Vars.traditionalTrash)
		{
			if (entity instanceof EntityItem)
			{
				((EntityItem) entity).setDead();
			}
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float sideX, float sideY, float sideZ)
	{
		if (!Vars.traditionalTrash) return false;
		return super.onBlockActivated(world, x, y, z, player, side, sideX, sideY, sideZ);
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public Class<? extends TileEntityInventory> getTileClass()
	{
		return TileEntityTrash.class;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getGui(InventoryPlayer inventory, World world, int x, int y, int z, TileEntity tile)
	{
		return new GuiTrash(inventory, (TileEntityTrash) tile);
	}

	@Override
	public Container getContainer(InventoryPlayer inventory, World world, int x, int y, int z, TileEntity tile)
	{
		return new ContainerTrash(inventory, (TileEntityTrash) tile);
	}
}
