package ExtremeBlocks.Blocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import ExtremeBlocks.ExtremeBlocksMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTrinquantiumOre extends Block {
	public BlockTrinquantiumOre(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setHardness(5.0F);
		this.setUnlocalizedName("TrinquantiumOre");
		this.setCreativeTab(ExtremeBlocksMain.EBOresTab);
		this.setStepSound(soundStoneFootstep);
	}

	public int idDropped(int par1, Random par2Random, int par3) {
		return this.blockID;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid
				+ ":" + (this.getUnlocalizedName().substring(5)));
	}
}
