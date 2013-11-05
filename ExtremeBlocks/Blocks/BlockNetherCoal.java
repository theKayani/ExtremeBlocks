package ExtremeBlocks.Blocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import ExtremeBlocks.ExtremeBlocksMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockNetherCoal extends Block {
	public BlockNetherCoal(int par1, Material par2) {
		super(par1, par2);
		this.setHardness(1.0F);
		this.setUnlocalizedName("NetherCoal");
		this.setCreativeTab(ExtremeBlocksMain.EBOresTab);
		this.setStepSound(soundStoneFootstep);
	}

	public int idDropped(int par1, Random par2Random, int par3) {
		return Item.coal.itemID;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid
				+ ":" + (this.getUnlocalizedName().substring(5)));
	}
}
