package main.extremeblocks.blocks;

import main.com.hk.testing.util.BlockCustom;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.Init;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEBTable extends BlockCustom
{
	@SideOnly(Side.CLIENT)
	private IIcon field_150035_a;
	@SideOnly(Side.CLIENT)
	private IIcon field_150034_b;

	public BlockEBTable()
	{
		super(Material.wood, "EB Crafting Table");
		this.setHardness(2.5F);
		this.setCreativeTab(Init.tab_mainBlocks);
		this.setBlockTextureName(Init.MODID + ":crafting_table");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return p_149691_1_ == 1 ? this.field_150035_a : (p_149691_1_ == 0 ? Blocks.planks.getIcon(p_149691_1_, 2) : (p_149691_1_ != 2 && p_149691_1_ != 4 ? this.blockIcon : this.field_150034_b));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
		this.blockIcon = p_149651_1_.registerIcon(this.getTextureName() + "_side");
		this.field_150035_a = p_149651_1_.registerIcon(this.getTextureName() + "_top");
		this.field_150034_b = p_149651_1_.registerIcon(this.getTextureName() + "_front");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		if (!player.isSneaking())
		{
			player.openGui(ExtremeBlocks.instance, 4, world, x, y, z);
			return true;
		}
		return false;
	}

	public static final int CRAFTING_SLOTS = 4;
}
