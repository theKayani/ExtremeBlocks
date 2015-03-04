package main.extremeblocks.blocks.abstracts;

import main.com.hk.eb.util.IInfo;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.Init;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSided extends BlockRotatedPillar implements IInfo
{
	private boolean showRecipe;
	private IIcon topIcon;

	public BlockSided(Material mat, String blockName, String textureName)
	{
		super(mat);
		setBlockName(blockName);
		setBlockTextureName(Init.MODID + ":" + textureName + "_");
		setCreativeTab(Init.tab_mainBlocks);
		ExtremeBlocks.blocks.add(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		blockIcon = ir.registerIcon(getTextureName() + "side");
		topIcon = ir.registerIcon(getTextureName() + "top");
	}

	public BlockSided setShowRecipe()
	{
		showRecipe = true;
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getTopIcon(int p_150161_1_)
	{
		return topIcon;
	}

	@Override
	protected IIcon getSideIcon(int var1)
	{
		return blockIcon;
	}

	@Override
	public String getInfo()
	{
		return "This block, like logs, can be placed sideways.";
	}

	@Override
	public Elements getElements()
	{
		return new Elements(true, showRecipe);
	}
}
