package main.extremeblocks.blocks;

import java.util.List;
import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.Init;
import main.extremeblocks.tileentities.TileEntityWire;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWire extends BlockCustom implements ITileEntityProvider
{
	public BlockWire()
	{
		super(Material.wood, "Wire");
		setCreativeTab(Init.tab_mainBlocks);
		setHardness(0.3F);
		float f = 0.3F;
		setBlockBounds(0.5F - f, 0.5F - f, 0.5F - f, 0.5F + f, 0.5F + f, 0.5F + f);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void addCollisionBoxesToList(World wrd, int x, int y, int z, AxisAlignedBB mask, List list, Entity ent)
	{
		setBlockBoundsBasedOnState(wrd, x, y, z);
		super.addCollisionBoxesToList(wrd, x, y, z, mask, list, ent);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		boolean flag = canConnectTo(world, x, y, z - 1, x, y, z);
		boolean flag1 = canConnectTo(world, x, y, z + 1, x, y, z);
		boolean flag2 = canConnectTo(world, x - 1, y, z, x, y, z);
		boolean flag3 = canConnectTo(world, x + 1, y, z, x, y, z);
		float f = flag2 ? 0.0F : 0.2F;
		float f1 = flag3 ? 1.0F : 0.8F;
		float f2 = flag ? 0.0F : 0.2F;
		float f3 = flag1 ? 1.0F : 0.8F;
		setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
	}

	public boolean canConnectTo(IBlockAccess world, int x, int y, int z, int x1, int y1, int z1)
	{
		return world.getTileEntity(x, y, z) instanceof TileEntityWire ? ((TileEntityWire) world.getTileEntity(x, y, z)).canConnectTo(x1, y1, z1) : false;
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
	public int getRenderType()
	{
		return -1;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block b)
	{
		((TileEntityWire) world.getTileEntity(x, y, z)).updateNeighbors();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r)
	{
	}

	@Override
	public TileEntity createNewTileEntity(World world, int idk)
	{
		return new TileEntityWire();
	}
}
