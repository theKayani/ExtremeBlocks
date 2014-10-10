package main.extremeblocks.blocks;

import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.Init;
import main.extremeblocks.tileentities.TileEntityMedal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMedal extends BlockCustom implements ITileEntityProvider
{
	public final MedalType type;

	public BlockMedal(MedalType type)
	{
		super(Material.wood, type.name + " Medal");
		setHardness(0.5F);
		float f = 0.325F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
		setCreativeTab(Init.tab_mainBlocks);
		switch (type)
		{
			case IRON:
				setBlockTextureName("iron_block");
				break;
			case BRONZE:
				setBlockTextureName(Init.MODID + ":Bronze Block");
				break;
			case DIAMOND:
				setBlockTextureName("diamond_block");
				break;
			case GOLD:
				setBlockTextureName("gold_block");
				break;
			case SILVER:
				setBlockTextureName(Init.MODID + ":Silver Block");
				break;
			case TRINQUANTIUM:
				setBlockTextureName(Init.MODID + ":Trinquantium Block");
				break;
			default:
				break;

		}
		this.type = type;
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
	public TileEntity createNewTileEntity(World world, int p_149915_2_)
	{
		return new TileEntityMedal();
	}

	public static enum MedalType
	{
		GOLD, TRINQUANTIUM, BRONZE, SILVER, DIAMOND, IRON;

		public final String name;

		private MedalType()
		{
			name = name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
		}
	}
}
