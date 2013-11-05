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

public class BlockXBox360 extends Block {
	public BlockXBox360(int par1, Material par2) {
		super(par1, par2);
		this.setHardness(0.7F);
		this.setUnlocalizedName("XBox360");
		this.setCreativeTab(ExtremeBlocksMain.EBMiscTab);
		this.setStepSound(soundStoneFootstep);
		this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.4F, 0.9F);
	}

	public int idDropped(int par1, Random par2Random, int par3) {
		return this.blockID;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid
				+ ":" + (this.getUnlocalizedName().substring(5)));
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		par5EntityPlayer
				.addChatMessage("Will Be Updated for you to actually do something with it!");
		return true;
	}
}
