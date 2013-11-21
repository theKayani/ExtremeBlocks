package ExtremeBlocks.Blocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ExtremeBlocks.ExtremeBlocksMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOffBlocks extends Block 
{
	public String name;
	
	public Block block; 
	
	public StepSound stepSound;
	
	public BlockOffBlocks(int par1, Material par2Material, String par3String, Block par4Block, StepSound par5StemSound) 
	{
		super(par1, par2Material);
		
		name = par3String;
		block = par4Block;
		stepSound = par5StemSound;
		
		this.setUnlocalizedName("Off" + name);
		this.setCreativeTab(ExtremeBlocksMain.EBLightedBlocksTab);
		this.setStepSound(stepSound);
	}

	public int idDropped(int par1, Random par2Random, int par3) 
	{
		return this.blockID;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) 
	{
		this.blockIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + "Lighted" + name);
	}

	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) 
	{
		breakBlock(par1World, par2, par3, par4, par3, par2);
		par1World.setBlock(par2, par3, par4, block.blockID);
		return true;
	}

	public int idPicked(World par1World, int par2, int par3, int par4) 
	{
		return block.blockID;
	}
}