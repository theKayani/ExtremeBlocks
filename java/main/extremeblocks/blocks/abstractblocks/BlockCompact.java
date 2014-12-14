package main.extremeblocks.blocks.abstractblocks;

import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.Init;
import net.minecraft.block.material.Material;

public class BlockCompact extends BlockCustom
{
	public BlockCompact(String name)
	{
		super(Material.iron, name);
		setHardness(3.0F);
		setBlockTextureName(Init.MODID + ":" + name);
		setCreativeTab(Init.tab_mainBlocks);
		setInfo("Simple storage block. Can be crafted with 9 of the corresponding items. Can also be reversed");
	}
}
