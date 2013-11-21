package ExtremeBlocks.Blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ExtremeBlocks.ExtremeBlocksMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSpreadGameBlock extends Block 
{
	public BlockSpreadGameBlock(int par1, Material par2Material) 
	{
		super(par1, par2Material);
		this.setHardness(1.0F);
		this.setUnlocalizedName("SpreadGameBlock");
		this.setStepSound(soundStoneFootstep);
	}

	public int idDropped(int par1, Random par2Random, int par3) 
	{
		return this.blockID;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) 
	{
		this.blockIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + "LightedObsidian");
	}

	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if(par1World.isAirBlock(par2 + 1, par3, par4))
		{	
			par1World.setBlock(par2 + 1, par3, par4, blockID);
		}
		
		if(par1World.isAirBlock(par2 - 1, par3, par4))
		{
			par1World.setBlock(par2 - 1, par3, par4, blockID);
		}

		if(par1World.isAirBlock(par2, par3, par4 + 1))
		{
			par1World.setBlock(par2, par3, par4 + 1, blockID);
		}

		if(par1World.isAirBlock(par2, par3, par4 - 1))
		{
			par1World.setBlock(par2, par3, par4 - 1, blockID);
		}
	}
}
