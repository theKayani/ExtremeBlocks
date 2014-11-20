package main.extremeblocks.blocks;

import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.Init;
import main.extremeblocks.registry.Interfaces.IStoneOre;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockCement extends BlockCustom implements IStoneOre
{
	private final boolean isCement;

	public BlockCement(boolean isCement)
	{
		super(Material.rock, (isCement ? "Cement" : "Plaster") + " Block");
		setHardness(1.5F);
		setCreativeTab(Init.tab_mainBlocks);
		setBlockTextureName(Init.MODID + ":" + (isCement ? "cement" : "plaster") + "block");
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
		return super.canPlaceBlockAt(world, x, y, z) && canBlockStay(world, x, y, z);
	}
}
