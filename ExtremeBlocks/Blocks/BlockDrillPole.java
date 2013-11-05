package ExtremeBlocks.Blocks;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import ExtremeBlocks.ExtremeBlocksMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDrillPole extends Block
{
	public BlockDrillPole(int par1, Material par2Material) 
	{
		super(par1, par2Material);
		this.setHardness(0.1F);
		this.setUnlocalizedName("DrillPole");
		this.setStepSound(soundMetalFootstep);
	}

	public int idPicked(World par1World, int par2, int par3, int par4)
	{
		return ExtremeBlocksMain.Drill.blockID;	
	}

	public int idDropped(int par1, Random par2Random, int par3) 
	{
		return ExtremeBlocksMain.CrushedStone.itemID;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) 
	{
		this.blockIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}
	public int getRenderType()
	{
		return 2;	
	}

	public boolean isOpaqueCube() 
	{
		return false;
	}

	public boolean renderAsNormalBlock() 
	{
		return false;
	}

	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		if(par1World.getBlockId(par2, par3 - 1, par4) != Block.bedrock.blockID && !par1World.isAirBlock(par2, par3 - 1, par4))
		{
			par1World.setBlock(par2, par3 - 1, par4, this.blockID);
		}
		if(par1World.getBlockId(par2, par3 - 1, par4) == Block.bedrock.blockID || par1World.isAirBlock(par2, par3 - 1, par4))
		{
			par1World.setBlock(par2, par3, par4, ExtremeBlocksMain.Drill.blockID);
		}		
		BlockDrill.PLP++;	
	}
}
