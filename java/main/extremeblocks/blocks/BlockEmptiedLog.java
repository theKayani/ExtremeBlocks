package main.extremeblocks.blocks;

import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.Init;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEmptiedLog extends BlockRotatedPillar
{
	@SideOnly(Side.CLIENT)
	private IIcon topIcon;

	public BlockEmptiedLog()
	{
		super(Material.wood);
		this.setBlockName("Emptied Log");
		this.setBlockTextureName(Init.MODID + ":emptiedlog_");
		this.setCreativeTab(Init.tab_mainBlocks);
		ExtremeBlocks.blocks.add(this);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getTopIcon(int p_150161_1_)
	{
		return this.topIcon;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.blockIcon = ir.registerIcon(getTextureName() + "side");
		this.topIcon = ir.registerIcon(getTextureName() + "top");
	}

	@Override
	protected IIcon getSideIcon(int var1)
	{
		return this.blockIcon;
	}
}
