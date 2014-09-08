package main.extremeblocks.blocks.abstractblocks;

import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.Init;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSided extends BlockRotatedPillar
{
	private IIcon topIcon;

	public BlockSided(Material mat, String blockName, String textureName)
	{
		super(mat);
		this.setBlockName(blockName);
		this.setBlockTextureName(Init.MODID + ":" + textureName + "_");
		this.setCreativeTab(Init.tab_mainBlocks);
		ExtremeBlocks.blocks.add(this);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.blockIcon = ir.registerIcon(getTextureName() + "side");
		this.topIcon = ir.registerIcon(getTextureName() + "top");
	}

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
}
