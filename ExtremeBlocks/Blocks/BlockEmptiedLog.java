package ExtremeBlocks.Blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockReed;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import ExtremeBlocks.ExtremeBlocksMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEmptiedLog extends Block 
{
	public BlockEmptiedLog(int par1, Material par2Material) 
	{
		super(par1, par2Material);
		this.setCreativeTab(ExtremeBlocksMain.EBBasicBlocksTab);
		this.setUnlocalizedName("EmptiedLog");
	}

	@SideOnly(Side.CLIENT)
	private Icon[] icons;

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) 
	{
		icons = new Icon[3];

		for (int i = 0; i < icons.length; i++) 
		{
			icons[i] = par1IconRegister.registerIcon(ExtremeBlocksMain.modid+ ":" + (this.getUnlocalizedName().substring(5)) + i);
		}
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		switch (par1) {
		case 0:
			return icons[1];
		case 1:
			return icons[2];
		default:
			return icons[0];
		}
	}
}
