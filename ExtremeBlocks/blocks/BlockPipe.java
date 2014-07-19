package main.extremeblocks.blocks;

import javax.swing.Icon;
import main.com.hk.testing.util.BlockCustom;
import main.com.hk.testing.util.MPUtil;
import main.extremeblocks.Init;
import main.extremeblocks.blocks.tileentities.TileEntityPowerPipe;
import main.extremeblocks.blocks.tileentities.TileEntityTransportPipe;
import main.extremeblocks.blocks.tileentities.pipe.PipeType;
import main.extremeblocks.blocks.tileentities.pipe.TileEntityAbstractPipe;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPipe extends BlockCustom implements ITileEntityProvider
{
	private final PipeType type;
	private Icon itemIcon;

	public BlockPipe(PipeType type)
	{
		super(Material.iron, type + " Pipe");
		this.setCreativeTab(Init.tab_misc);
		this.setHardness(2.5F);
		this.setBlockTextureName("iron_block");
		this.setResistance(120.0F);
		this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.8F, 0.8F);

		this.type = type;
	}

	public void breakBlock(World world, int x, int y, int z, Block block, int chance)
	{
		TileEntityAbstractPipe te = (TileEntityAbstractPipe) world.getTileEntity(x, y, z);

		if (MPUtil.isServerSide())
		{
			te.destroyedBlock(x, y, z, chance);
		}

		super.breakBlock(world, x, y, z, block, chance);
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		switch (type)
		{
			case POWER:
				return new TileEntityPowerPipe();
			case TRANSPORT:
				return new TileEntityTransportPipe();
			default:
				return null;

		}
	}
}
