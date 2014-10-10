package main.extremeblocks.blocks;

import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.Init;
import main.extremeblocks.tileentities.TileEntityRewardBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockRewardBlock extends BlockCustom implements ITileEntityProvider
{
	public BlockRewardBlock()
	{
		super(Material.iron, "Reward Block");
		setCreativeTab(Init.tab_mainBlocks);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityRewardBlock();
	}
}
