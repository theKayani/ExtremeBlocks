package main.extremeblocks.blocks;

import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.GuiIDs;
import main.extremeblocks.Init;
import main.extremeblocks.Vars;
import main.extremeblocks.tileentities.TileEntityInventory;
import main.extremeblocks.tileentities.TileEntityTrash;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTrash extends BlockCustom implements GuiIDs, ITileEntityProvider
{
	public BlockTrash()
	{
		super(Material.iron, "Trash");
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		if (!Vars.traditionalTrash && !player.isSneaking())
		{
			player.openGui(ExtremeBlocks.instance, TILE_ENTITY_TRASH, world, x, y, z);
			return true;
		}
		return false;
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
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityTrash();
	}
}
