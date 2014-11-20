package main.extremeblocks.blocks;

import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.Init;
import main.extremeblocks.registry.Interfaces.ILogOre;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEmptiedLog extends BlockRotatedPillar implements ILogOre
{
	@SideOnly(Side.CLIENT)
	private IIcon topIcon;

	public BlockEmptiedLog()
	{
		super(Material.wood);
		setBlockName("Emptied Log");
		setBlockTextureName(Init.MODID + ":emptiedlog_");
		setCreativeTab(Init.tab_mainBlocks);
		ExtremeBlocks.blocks.add(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getTopIcon(int p_150161_1_)
	{
		return topIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		blockIcon = ir.registerIcon(getTextureName() + "side");
		topIcon = ir.registerIcon(getTextureName() + "top");
	}

	@Override
	protected IIcon getSideIcon(int var1)
	{
		return blockIcon;
	}
}
