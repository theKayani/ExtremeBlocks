package main.extremeblocks.blocks;

import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.Init;
import main.extremeblocks.tileentities.TileEntityWire;
import main.extremeblocks.util.IConnector;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWire extends BlockCustom implements ITileEntityProvider
{
	public BlockWire()
	{
		super(Material.wood, "Power Wire");
		this.setHardness(0.3F);
		this.setCreativeTab(Init.tab_mainBlocks);
		this.setBlockTextureName("coal_block");
		float f = 0.3F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		boolean flag = this.canConnectTo(world, x, y, z - 1, x, y, z);
		boolean flag1 = this.canConnectTo(world, x, y, z + 1, x, y, z);
		boolean flag2 = this.canConnectTo(world, x - 1, y, z, x, y, z);
		boolean flag3 = this.canConnectTo(world, x + 1, y, z, x, y, z);
		float f = 0.2F;
		float f1 = 0.8F;
		float f2 = 0.2F;
		float f3 = 0.8F;

		if (flag)
		{
			f2 = 0.0F;
		}

		if (flag1)
		{
			f3 = 1.0F;
		}

		if (flag2)
		{
			f = 0.0F;
		}

		if (flag3)
		{
			f1 = 1.0F;
		}

		this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
	}

	public boolean canConnectTo(IBlockAccess world, int x, int y, int z, int x1, int y1, int z1)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof IConnector)
		{
			return ((IConnector) tile).canConnect((World) world, x1, y1, z1);
		}
		return false;
	}

	@Override
	public int getRenderType()
	{
		return -1;
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
	public void breakBlock(World world, int x, int y, int z, Block block, int flag)
	{
		((TileEntityWire) world.getTileEntity(x, y, z)).breakBlock();
		super.breakBlock(world, x, y, z, block, flag);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		((TileEntityWire) world.getTileEntity(x, y, z)).onBlockAdded();
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityWire();
	}
}
