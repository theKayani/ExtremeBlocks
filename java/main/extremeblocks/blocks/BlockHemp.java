package main.extremeblocks.blocks;

import java.util.Random;
import main.com.hk.eb.util.BlockCustom;
import main.com.hk.eb.util.Rand;
import main.extremeblocks.Init;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHemp extends BlockCustom
{
	public BlockHemp()
	{
		super(Material.plants, "Hemp");
		this.setBlockTextureName(Init.MODID + ":hemp");
		this.setCreativeTab(Init.tab_mainBlocks);
		this.setTickRandomly(true);
		this.setDrop(Init.hemp_leaves);
		this.setDroppedAmount(Rand.nextInt(3));
		this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 1.0F, 0.875F);
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		this.canStayAtSpot(world, x, y, z);
	}

	public final boolean canStayAtSpot(World world, int x, int y, int z)
	{
		if (!this.canBlockStay(world, x, y, z))
		{
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
			return false;
		}
		else
		{
			return true;
		}
	}

	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if (world.getBlock(x, y - 1, z) == Init.cannabis_plant || this.canStayAtSpot(world, x, y, z))
		{
			if (world.isAirBlock(x, y + 1, z))
			{
				int l;
				for (l = 1; world.getBlock(x, y - l, z) == this; ++l)
				{
					;
				}
				if (l < 5)
				{
					int i1 = world.getBlockMetadata(x, y, z);
					if (i1 == 15)
					{
						world.setBlock(x, y + 1, z, Init.cannabis_plant);
						world.setBlockMetadataWithNotify(x, y, z, 0, 4);
					}
					else
					{
						world.setBlockMetadataWithNotify(x, y, z, i1 + 1, 4);
					}
				}
			}
		}
	}

	public int getRenderType()
	{
		return 1;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z)
	{
		return world.getBiomeGenForCoords(x, z).getBiomeGrassColor(x, y, z);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}

	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return canPlaceBlockAt(world, x, y, z);
	}

	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y - 1, z);
		boolean hasWater = world.getBlock(x - 1, y - 1, z).getMaterial() == Material.water || world.getBlock(x + 1, y - 1, z).getMaterial() == Material.water || world.getBlock(x, y - 1, z - 1).getMaterial() == Material.water || world.getBlock(x, y - 1, z + 1).getMaterial() == Material.water;
		return block == Init.cannabis_plant || ((block == Blocks.grass || block == Blocks.dirt || block == Blocks.sand) && hasWater);
	}
}
