package extremeblocks.blocks.abstractblocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import com.hk.testing.util.BlockCustom;
import extremeblocks.Init;

public class BlockOre extends BlockCustom
{
	public BlockOre(String name)
	{
		super(Material.rock, name);
		this.setHardness(3.0F);
		this.setStepSound(Block.soundTypeStone);
		this.setBlockTextureName(Init.MODID + ":" + name);
		this.setCreativeTab(Init.tab_ebOres);
	}
}
