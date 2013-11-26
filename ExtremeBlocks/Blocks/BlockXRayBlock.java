package ExtremeBlocks.Blocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import ExtremeBlocks.ExtremeBlocksMain;
import ExtremeBlocks.Vars;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockXRayBlock extends Block 
{
	public BlockXRayBlock(int par1, Material par2Material) 
	{
		super(par1, par2Material);
		this.setUnlocalizedName("XRayBlock");
		this.setCreativeTab(ExtremeBlocksMain.EBBasicBlocksTab);
		this.setLightValue(2.0F);
		this.setStepSound(soundGlassFootstep);
		
	}

	public int idDropped(int par1, Random par2Random, int par3) 
	{
		return this.blockID;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) 
	{
		this.blockIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}
	
	public boolean isOpaqueCube() 
	{
		if(Vars.CFXrayBlockOn)
		{
			return false;
		}
		if(!Vars.CFXrayBlockOn)
		{
			return true;
		}
		return Vars.CFXrayBlockOn;
	}

	public boolean renderAsNormalBlock() 
	{
		if(Vars.CFXrayBlockOn)
		{
			return false;
		}
		if(!Vars.CFXrayBlockOn)
		{
			return true;
		}
		return Vars.CFXrayBlockOn;
	}
}
