package main.extremeblocks.blocks;

import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;
import java.util.ArrayList;
import java.util.Random;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.Init;
import main.extremeblocks.blocks.abstractblocks.BlockStorage;
import main.extremeblocks.tileentities.TileEntityStorage;
import main.extremeblocks.util.BlockType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.Slot;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockCabinet extends BlockStorage
{
	public BlockCabinet()
	{
		super("Cabinet", Material.wood, Init.tab_mainBlocks, 6, "Cabinet", Init.MODID + ":textures/gui/cabinet.png", BlockType.CABINET);
		this.setHardness(1.0F);
		this.setTickRandomly(true);
	}

	private void notifyBlocks(World world, int x, int y, int z, int flag)
	{
		world.notifyBlocksOfNeighborChange(x, y, z, this);
		if (flag == 1)
		{
			world.notifyBlocksOfNeighborChange(x - 1, y, z, this);
		}
		else if (flag == 2)
		{
			world.notifyBlocksOfNeighborChange(x + 1, y, z, this);
		}
		else if (flag == 3)
		{
			world.notifyBlocksOfNeighborChange(x, y, z - 1, this);
		}
		else if (flag == 4)
		{
			world.notifyBlocksOfNeighborChange(x, y, z + 1, this);
		}
		else
		{
			world.notifyBlocksOfNeighborChange(x, y - 1, z, this);
		}
	}

	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if (MPUtil.isServerSide())
		{
			int l = world.getBlockMetadata(x, y, z);
			if ((l & 8) != 0)
			{
				world.setBlockMetadataWithNotify(x, y, z, l & 7, 3);
				int i1 = l & 7;
				this.notifyBlocks(world, x, y, z, i1);
				world.playSoundEffect((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "random.click", 0.3F, 0.5F);
				world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			}
		}
	}

	@Override
	public Slot[] addSlotsToContainer(TileEntityStorage te)
	{
		ArrayList<Slot> slots = JavaHelp.newArrayList();
		for (int i = 0; i < 6; ++i)
		{
			slots.add(new Slot(te, i, 34 + i * 18, 32));
		}
		return slots.toArray(new Slot[0]);
	}

	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return canPlaceAt(world, x, y, z);
	}

	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int i)
	{
		ForgeDirection dir = ForgeDirection.getOrientation(i);
		return (dir == NORTH && world.isSideSolid(x, y, z + 1, NORTH)) || (dir == SOUTH && world.isSideSolid(x, y, z - 1, SOUTH)) || (dir == WEST && world.isSideSolid(x + 1, y, z, WEST)) || (dir == EAST && world.isSideSolid(x - 1, y, z, EAST));
	}

	private boolean canPlaceAt(World world, int x, int y, int z)
	{
		return (world.isSideSolid(x - 1, y, z, EAST)) || (world.isSideSolid(x + 1, y, z, WEST)) || (world.isSideSolid(x, y, z - 1, SOUTH)) || (world.isSideSolid(x, y, z + 1, NORTH));
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		if (this.shouldDropItem(world, x, y, z))
		{
			int l = world.getBlockMetadata(x, y, z) & 7;
			boolean flag = false;
			if (!world.isSideSolid(x - 1, y, z, EAST) && l == 1)
			{
				flag = true;
			}
			if (!world.isSideSolid(x + 1, y, z, WEST) && l == 2)
			{
				flag = true;
			}
			if (!world.isSideSolid(x, y, z - 1, SOUTH) && l == 3)
			{
				flag = true;
			}
			if (!world.isSideSolid(x, y, z + 1, NORTH) && l == 4)
			{
				flag = true;
			}
			if (flag)
			{
				this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
				world.setBlockToAir(x, y, z);
			}
		}
	}

	private boolean shouldDropItem(World world, int x, int y, int z)
	{
		if (!this.canPlaceBlockAt(world, x, y, z))
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

	public void setBlockBoundsForItemRender()
	{
		this.setBlockBounds(0.1F, 0.2F, 0.0F, 0.9F, 0.8F, 0.2F);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		int l = world.getBlockMetadata(x, y, z);
		this.checker(l);
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	private void checker(int check)
	{
		int j = check & 7;
		boolean flag = (check & 8) > 0;
		float f = 0.375F;
		float f1 = 0.625F;
		float f2 = 0.1875F;
		float f3 = 0.125F;
		if (flag)
		{
			f3 = 0.0625F;
		}
		if (j == 1) // WEST
		{
			this.setBlockBounds(0.0F, 0.2F, 0.1F, 0.2F, 0.8F, 0.9F);
		}
		else if (j == 2)// EAST
		{
			this.setBlockBounds(0.8F, 0.2F, 0.1F, 1.0F, 0.8F, 0.9F);
		}
		else if (j == 3)// NORTH
		{
			this.setBlockBounds(0.1F, 0.2F, 0.0F, 0.9F, 0.8F, 0.2F);
		}
		else if (j == 4)// SOUTH
		{
			this.setBlockBounds(0.1F, 0.2F, 0.8F, 0.9F, 0.8F, 1.0F);
		}
	}

	public void breakBlock(World world, int x, int y, int z, Block block, int i)
	{
		if ((i & 8) > 0)
		{
			int i1 = i & 7;
			this.notifyBlocks(world, x, y, z, i1);
		}
		super.breakBlock(world, x, y, z, block, i);
	}

	public int onBlockPlaced(World world, int x, int y, int z, int flag, float lolX, float lolY, float lolZ, int idk)
	{
		int j1 = world.getBlockMetadata(x, y, z);
		int k1 = j1 & 8;
		j1 &= 7;
		ForgeDirection dir = ForgeDirection.getOrientation(flag);
		if (dir == NORTH && world.isSideSolid(x, y, z + 1, NORTH))
		{
			j1 = 4;
		}
		else if (dir == SOUTH && world.isSideSolid(x, y, z - 1, SOUTH))
		{
			j1 = 3;
		}
		else if (dir == WEST && world.isSideSolid(x + 1, y, z, WEST))
		{
			j1 = 2;
		}
		else if (dir == EAST && world.isSideSolid(x - 1, y, z, EAST))
		{
			j1 = 1;
		}
		else
		{
			j1 = this.iHaveNoIdeaWhatThisDoes(world, x, y, z);
		}
		return j1 + k1;
	}

	private int iHaveNoIdeaWhatThisDoes(World world, int x, int y, int z)
	{
		if (world.isSideSolid(x - 1, y, z, EAST)) return 1;
		if (world.isSideSolid(x + 1, y, z, WEST)) return 2;
		if (world.isSideSolid(x, y, z - 1, SOUTH)) return 3;
		if (world.isSideSolid(x, y, z + 1, NORTH)) return 4;
		return 1;
	}
}
