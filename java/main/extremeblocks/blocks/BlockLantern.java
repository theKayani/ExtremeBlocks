package main.extremeblocks.blocks;

import main.com.hk.testing.util.BlockCustom;
import main.extremeblocks.Init;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockLantern extends BlockCustom
{
	public BlockLantern()
	{
		super(Material.glass, "Lantern");
		this.setCreativeTab(Init.tab_mainBlocks);
		this.setBlockTextureName(Init.MODID + ":lantern");
		this.setLightLevel(0.4F);
		this.setBlockBounds(0.3F, 0.3F, 0.3F, 0.7F, 1.0F, 0.7F);
	}

	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return world.isSideSolid(x, y + 1, z, ForgeDirection.SOUTH);
	}

	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return this.canBlockStay(world, x, y, z);
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		if (!canBlockStay(world, x, y, z))
		{
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}
}
