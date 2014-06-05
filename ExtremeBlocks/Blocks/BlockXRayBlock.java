package extremeblocks.blocks;

import net.minecraft.block.material.Material;
import com.hk.testing.util.BlockCustom;
import extremeblocks.Init;

public class BlockXrayBlock extends BlockCustom
{
	private final boolean isOn;

	public BlockXrayBlock(boolean isOn)
	{
		super(Material.glass, (isOn ? "Unf" : "F") + "inished Xray Block");
		this.setBlockTextureName(Init.MODID + ":xrayblock");
		this.setCreativeTab(Init.tab_mainBlocks);

		this.isOn = isOn;
	}

	public boolean renderAsNormalBlock()
	{
		return !isOn;
	}

	public boolean isOpaqueCube()
	{
		return !isOn;
	}
}
