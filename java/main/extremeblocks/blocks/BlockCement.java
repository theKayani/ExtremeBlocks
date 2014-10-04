package main.extremeblocks.blocks;

import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.Init;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockCement extends BlockCustom
{
	private final boolean isCement;

	public BlockCement(boolean isCement)
	{
		super(Material.rock, (isCement ? "Cement" : "Plaster") + " Block");
		this.setHardness(1.5F);
		this.setCreativeTab(Init.tab_mainBlocks);
		this.setBlockTextureName(Init.MODID + ":" + (isCement ? "cement" : "plaster") + "block");
		this.isCement = isCement;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return isCement;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return super.canPlaceBlockAt(world, x, y, z) && this.canBlockStay(world, x, y, z);
	}
}
