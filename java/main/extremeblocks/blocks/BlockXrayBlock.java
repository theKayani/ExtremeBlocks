package main.extremeblocks.blocks;

import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.Init;
import main.extremeblocks.Vars;
import net.minecraft.block.material.Material;

public class BlockXrayBlock extends BlockCustom
{
	private final boolean finished;

	public BlockXrayBlock(boolean finished)
	{
		super(Material.glass, (finished ? "F" : "Unf") + "inished Xray Block");
		setHardness(0.6F);
		setBlockTextureName(Init.MODID + ":xrayblock");
		setCreativeTab(Init.tab_mainBlocks);
		this.finished = finished && !Vars.xrayBlockWork;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return finished;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return finished;
	}
}
