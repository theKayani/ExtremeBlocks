package ExtremeBlocks.Blocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ExtremeBlocks.ExtremeBlocksMain;
import ExtremeBlocks.Vars;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockYellowGameFloor extends Block 
{
	public BlockYellowGameFloor(int par1, Material par2Material) 
	{
		super(par1, par2Material);
		this.setHardness(1.0F);
		this.setUnlocalizedName("YellowGameFloor");
		this.setStepSound(soundStoneFootstep);
	}

	public int idDropped(int par1, Random par2Random, int par3) 
	{
		return ExtremeBlocksMain.RedGameFloor.blockID;
	}

	public int idPicked(World par1World, int par2, int par3, int par4)
	{
		return ExtremeBlocksMain.RedGameFloor.blockID;	
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) 
	{
		this.blockIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}

	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if(par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().itemID == ExtremeBlocksMain.GameRemote.itemID)
		{
			par5EntityPlayer.addChatMessage("Marked Block- Red");
			par1World.setBlock(par2, par3, par4, ExtremeBlocksMain.RedGameFloor.blockID);
		}
		return false;
	}
}
