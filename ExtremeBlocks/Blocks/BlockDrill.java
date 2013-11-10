package ExtremeBlocks.Blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ExtremeBlocks.ExtremeBlocksMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDrill extends Block
{
	public static int PLP = 0;

	public BlockDrill(int par1, Material par2Material) 
	{
		super(par1, par2Material);
		this.setHardness(1.0F);
		this.setUnlocalizedName("Drill");
		this.setCreativeTab(ExtremeBlocksMain.EBBasicBlocksTab);
		this.setStepSound(soundMetalFootstep);
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
			par1World.setBlock(par2, par3 - 1, par4, ExtremeBlocksMain.DrillPole.blockID);
		}
		if(par1World.getBlockId(par2, par3 - 1, par4) == ExtremeBlocksMain.DrillPole.blockID)
		{
			par1World.setBlock(par2, par3, par4, ExtremeBlocksMain.DrillPole.blockID);
		}
	}
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
	{
		if(par1World.getBlockId(par2, par3 - 1, par4) == ExtremeBlocksMain.DrillPole.blockID)
		{
			par1World.setBlock(par2, par3, par4, ExtremeBlocksMain.DrillPole.blockID);
		}
		if(par1World.getBlockId(par2, par3 - 1, par4) == ExtremeBlocksMain.Drill.blockID)
		{
			par1World.setBlock(par2, par3, par4, ExtremeBlocksMain.DrillPole.blockID);
		}
	}
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if(PLP > 0)
		{
			par5EntityPlayer.addChatMessage("You have collected " + PLP + " Stone blocks!");
			par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Block.stone, PLP));
			PLP = 0;
		}
		else if(PLP == 0)
		{
			par5EntityPlayer.addChatMessage("You have not collected any Stone blocks, Start Drilling!");
		}
		if(PLP < 0)
		{
			PLP = 0;
		}
		return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
	}
}
