package ExtremeBlocks.Blocks;

import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import ExtremeBlocks.ExtremeBlocksMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAesthetic extends BlockSand
{
	public boolean isStable = false;
	
	public BlockAesthetic(int par1, Material par2Material) 
	{
		super(par1, par2Material);
		this.setHardness(0.0F);
		this.setUnlocalizedName("Aesthetic");
		this.setCreativeTab(ExtremeBlocksMain.EBBasicBlocksTab);
		this.setStepSound(soundSandFootstep);
	}
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) 
	{
		this.blockIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}
}