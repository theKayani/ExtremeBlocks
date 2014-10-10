package main.extremeblocks.blocks;

import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.GuiIDs;
import main.extremeblocks.Init;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEBTable extends BlockCustom implements GuiIDs
{
	@SideOnly(Side.CLIENT)
	private IIcon topIcon;
	@SideOnly(Side.CLIENT)
	private IIcon frontIcon;

	public BlockEBTable()
	{
		super(Material.wood, "EB Crafting Table");
		setHardness(2.5F);
		setCreativeTab(Init.tab_mainBlocks);
		setBlockTextureName(Init.MODID + ":crafting_table");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return side == 1 ? topIcon : side == 0 ? Blocks.planks.getIcon(side, 0) : side != 2 && side != 4 ? blockIcon : frontIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
		blockIcon = p_149651_1_.registerIcon(getTextureName() + "_side");
		topIcon = p_149651_1_.registerIcon(getTextureName() + "_top");
		frontIcon = p_149651_1_.registerIcon(getTextureName() + "_front");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		if (!player.isSneaking())
		{
			player.openGui(ExtremeBlocks.instance, BLOCK_EBTABLE, world, x, y, z);
			return true;
		}
		return false;
	}

	public static final int CRAFTING_SLOTS = 4;
}
