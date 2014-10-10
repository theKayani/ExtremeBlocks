package main.extremeblocks.blocks;

import java.util.ArrayList;
import java.util.Random;
import main.com.hk.eb.util.BlockCustom;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.Init;
import main.extremeblocks.tileentities.TileEntityDrill;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDrill extends BlockCustom implements ITileEntityProvider
{
	public final boolean isHead;
	private static ArrayList<ItemStack> loot = JavaHelp.newArrayList();

	public BlockDrill(boolean isHead)
	{
		super(Material.iron, "Drill" + (isHead ? " Head" : ""));
		setBlockTextureName(Init.MODID + ":drill" + (isHead ? "_head" : ""));
		this.isHead = isHead;

		if (isHead)
		{
			setCreativeTab(Init.tab_mainBlocks);
			setHardness(3.0F);
		}
		else
		{
			setHardness(0.2F);
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityDrill)
		{
			MPUtil.dropItemsAsEntities(world, x, y, z, false, ((TileEntityDrill) te).getStack().toArray(new ItemStack[0]));
		}
		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return isHead ? true : world.getBlock(x, y - 1, z) instanceof BlockDrill;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		if (!canBlockStay(world, x, y, z))
		{
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return canBlockStay(world, x, y, z);
	}

	@Override
	public int getRenderType()
	{
		return 2;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return isHead ? Item.getItemFromBlock(Init.drill) : Init.crushed_stone;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World par1World, int par2, int par3, int par4)
	{
		return Item.getItemFromBlock(Init.drill);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int idk)
	{
		return isHead ? new TileEntityDrill() : null;
	}
}
