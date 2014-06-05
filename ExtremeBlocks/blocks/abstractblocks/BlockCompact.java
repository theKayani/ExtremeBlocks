package extremeblocks.blocks.abstractblocks;

import net.minecraft.block.material.Material;
import com.hk.testing.util.BlockCustom;
import extremeblocks.Init;

public class BlockCompact extends BlockCustom
{
	public BlockCompact(String name)
	{
		super(Material.iron, name);
		this.setBlockTextureName(Init.MODID + ":" + name);
		this.setCreativeTab(Init.tab_mainBlocks);
	}
}
