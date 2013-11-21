package ExtremeBlocks.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import ExtremeBlocks.ExtremeBlocksMain;
import ExtremeBlocks.Vars;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFakeSands extends BlockSand
{
	public String blockName;

	public String unLocName;
	
	public StepSound stepSound;

	public BlockFakeSands(int par1, Material par2Material, String par3String, StepSound par4StepSound) //, String par5String) 
	{
		super(par1, par2Material);
		
		blockName = par3String;
		stepSound = par4StepSound;
		//unLocName = par5String;

		this.setHardness(1.0F);
		this.setUnlocalizedName(blockName);
		this.setStepSound(stepSound);
		this.setCreativeTab(ExtremeBlocksMain.EBMiscTab);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) 
	{
		this.blockIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + "Lighted" + blockName);
	}
}
