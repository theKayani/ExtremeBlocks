package main.extremeblocks.blocks.abstractblocks;

import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.Init;
import net.minecraft.block.material.Material;

public class BlockCompact extends BlockCustom
{
	public BlockCompact(String name)
	{
		super(Material.iron, name);
		this.setHardness(3.0F);
		this.setBlockTextureName(Init.MODID + ":" + name);
		this.setCreativeTab(Init.tab_mainBlocks);
	}
}
