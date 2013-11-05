package ExtremeBlocks.Blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ExtremeBlocks.ExtremeBlocksMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDriedSapling extends Block {
	public BlockDriedSapling(int par1, Material par2) {
		super(par1, par2);
		this.setUnlocalizedName("DriedSapling");
		this.setCreativeTab(ExtremeBlocksMain.EBMiscTab);
		this.setStepSound(soundGrassFootstep);
	}

	public int idDropped(int par1, Random par2Random, int par3) {
		return this.blockID;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid
				+ ":" + (this.getUnlocalizedName().substring(5)));
	}

	public int getRenderType() {
		return 2;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if (par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().itemID == new ItemStack(Item.dyePowder, 1, 16).itemID) 
		{
			par1World.setBlock(par2, par3, par4, ExtremeBlocksMain.EmptiedLog.blockID);
			par1World.setBlock(par2, par3 + 1, par4,ExtremeBlocksMain.EmptiedLog.blockID);
			par1World.setBlock(par2, par3 + 2, par4,ExtremeBlocksMain.EmptiedLog.blockID);
			par1World.setBlock(par2, par3 + 3, par4,ExtremeBlocksMain.EmptiedLog.blockID);
			par1World.setBlock(par2 + 1, par3 + 3, par4,ExtremeBlocksMain.EmptiedLog.blockID);
			par1World.setBlock(par2 + 2, par3 + 4, par4,ExtremeBlocksMain.EmptiedLog.blockID);
			par1World.setBlock(par2 + 3, par3 + 5, par4,ExtremeBlocksMain.EmptiedLog.blockID);
			par1World.setBlock(par2 - 1, par3 + 3, par4,ExtremeBlocksMain.EmptiedLog.blockID);
			par1World.setBlock(par2, par3 + 2, par4 - 1,ExtremeBlocksMain.EmptiedLog.blockID);
			par1World.setBlock(par2, par3 + 3, par4 - 1,ExtremeBlocksMain.EmptiedLog.blockID);
			par1World.setBlock(par2, par3 + 3, par4 - 3,ExtremeBlocksMain.EmptiedLog.blockID);
			par1World.setBlock(par2, par3 + 4, par4 - 2,ExtremeBlocksMain.EmptiedLog.blockID);
			par1World.setBlock(par2, par3 + 4, par4,ExtremeBlocksMain.EmptiedLog.blockID);
			par1World.setBlock(par2, par3 + 5, par4 + 1,ExtremeBlocksMain.EmptiedLog.blockID);
			par1World.setBlock(par2, par3 + 4, par4 + 2,ExtremeBlocksMain.EmptiedLog.blockID);
			return true;
		}
		return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
	}
}
