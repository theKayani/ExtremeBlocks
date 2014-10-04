package main.extremeblocks.blocks.abstractblocks;

import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.Init;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockEBOre extends BlockCustom
{
	public BlockEBOre(String name)
	{
		super(Material.rock, name);
		this.setHardness(3.0F);
		this.setStepSound(Block.soundTypeStone);
		this.setBlockTextureName(Init.MODID + ":" + name);
		this.setCreativeTab(Init.tab_mainBlocks);
	}
}
