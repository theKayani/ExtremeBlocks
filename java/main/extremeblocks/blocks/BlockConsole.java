package main.extremeblocks.blocks;

import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.Init;
import main.extremeblocks.tileentities.TileEntityConsole;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockConsole extends BlockCustom implements ITileEntityProvider
{
	public BlockConsole()
	{
		super(Material.wood, "Console");
		setBlockTextureName(Init.MODID + ":Console");
		setCreativeTab(Init.tab_mainBlocks);
		setHardness(2.0F);
		setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.4F, 0.9F);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityConsole();
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
}
