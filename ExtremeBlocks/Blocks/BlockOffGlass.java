package ExtremeBlocks.Blocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ExtremeBlocks.ExtremeBlocksMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOffGlass extends Block {
	public BlockOffGlass(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setUnlocalizedName("OffGlass");
		this.setStepSound(soundGlassFootstep);
	}

	public int idDropped(int par1, Random par2Random, int par3) {
		return ExtremeBlocksMain.LightedGlass.blockID;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid
				+ ":" + (this.getUnlocalizedName().substring(5)));
	}

	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		breakBlock(par1World, par2, par3, par4, par3, par2);
		par1World.setBlock(par2, par3, par4,
				ExtremeBlocksMain.LightedGlass.blockID);
		return true;
	}

	public int idPicked(World par1World, int par2, int par3, int par4) {
		return ExtremeBlocksMain.LightedGlass.blockID;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
}
