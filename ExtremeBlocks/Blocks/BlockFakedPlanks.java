package ExtremeBlocks.Blocks;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import ExtremeBlocks.ExtremeBlocksMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFakedPlanks extends Block 
{
	public BlockFakedPlanks(int par1, Material par2) 
	{
		super(par1, par2);
		this.setUnlocalizedName("FakedPlanks");
		this.setCreativeTab(ExtremeBlocksMain.EBBasicBlocksTab);
		this.setStepSound(soundWoodFootstep);
	}

	public int idDropped(int par1, Random par2Random, int par3) 
	{
		return this.blockID;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) 
	{
		String PLP = "LightedPlank";
		this.blockIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + PLP);
	}

	public boolean isOpaqueCube() 
	{
		return false;
	}

	public boolean renderAsNormalBlock() 
	{
		return false;
	}
	
	public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
	{
		
	}
}
