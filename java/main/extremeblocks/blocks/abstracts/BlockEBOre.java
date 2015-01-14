package main.extremeblocks.blocks.abstracts;

import java.util.Random;
import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.Init;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockEBOre extends BlockCustom
{
	public BlockEBOre(String name)
	{
		super(Material.rock, name);
		setHardness(3.0F);
		setStepSound(Block.soundTypeStone);
		setBlockTextureName(Init.MODID + ":" + name);
		setCreativeTab(Init.tab_mainBlocks);
	}

	@Override
	public String getInfo()
	{
		int ad = quantityDropped(new Random());
		return "An ore for " + getName().replaceAll(" Ore", "") + ". If you received the actual block, you have to smelt it to get the corresponding ingot." + (ad > 1 ? " Also, this ore drops " + ad + "." : "");
	}

	@Override
	public Elements getElements()
	{
		return new Elements(true, false);
	}
}
