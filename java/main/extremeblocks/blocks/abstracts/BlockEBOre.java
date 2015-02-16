package main.extremeblocks.blocks.abstracts;

import java.util.Random;
import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.Init;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

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
	public int getExpDrop(IBlockAccess p_149690_1_, int p_149690_5_, int p_149690_7_)
	{
		Random rand = new Random();
		if (getItemDropped(p_149690_5_, rand, p_149690_7_) != Item.getItemFromBlock(this)) return MathHelper.getRandomIntegerInRange(rand, 2, 5);
		return 0;
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
