package main.extremeblocks.blocks;

import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.Init;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockStonePillar extends BlockCustom
{
	@SideOnly(Side.CLIENT)
	private IIcon field_149936_O;
	@SideOnly(Side.CLIENT)
	private IIcon field_149935_N;

	public BlockStonePillar()
	{
		super(Material.rock, "VendingMachine");
		this.setCreativeTab(Init.tab_mainBlocks);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return p_149691_1_ == 1 ? this.field_149935_N : (p_149691_1_ == 0 ? this.field_149935_N : (p_149691_1_ != p_149691_2_ ? this.blockIcon : this.field_149936_O));
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
		this.blockIcon = p_149651_1_.registerIcon("furnace_side");
		this.field_149936_O = p_149651_1_.registerIcon("furnace_front_on");
		this.field_149935_N = p_149651_1_.registerIcon("furnace_top");
	}
}
