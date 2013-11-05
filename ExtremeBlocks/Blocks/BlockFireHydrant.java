package ExtremeBlocks.Blocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ExtremeBlocks.ExtremeBlocksMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFireHydrant extends Block 
{
	public BlockFireHydrant(int par1, Material par2Material) 
	{
		super(par1, par2Material);
		this.setHardness(2.0F);
		this.setUnlocalizedName("FireHydrant");
		this.setCreativeTab(ExtremeBlocksMain.EBMiscTab);
		this.setStepSound(soundMetalFootstep);
		this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.8F, 0.7F);
	}

	public int idDropped(int par1, Random par2Random, int par3) 
	{
		return this.blockID;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) 
	{
		this.blockIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid+ ":" + (this.getUnlocalizedName().substring(5)));
	}

	public boolean onBlockActivated(World par1World, int par2, int par3,int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) 
	{
		if (par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().itemID == Item.bucketEmpty.itemID) 
		{
			par5EntityPlayer.inventory.consumeInventoryItem(Item.bucketEmpty.itemID);
			par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.getFirstEmptyStack(), new ItemStack(Item.bucketWater));
			return true;
		}
		if (par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().itemID == ExtremeBlocksMain.Wrench.itemID) 
		{
			if (par1World.isAirBlock(par2, par3 + 1, par4))
			{
				par1World.setBlock(par2, par3 + 1, par4, Block.waterMoving.blockID);
				return true;
			}
			else
			{
				par5EntityPlayer.addChatMessage("You Need one Block Space on top of this Block to get water.");
			}
		}
		else if(par5EntityPlayer.getCurrentEquippedItem() != null)
		{
			if(par5EntityPlayer.getCurrentEquippedItem().itemID != ExtremeBlocksMain.Wrench.itemID || par5EntityPlayer.getCurrentEquippedItem().itemID != Item.bucketEmpty.itemID)
			{
				//Negatives on the X Axis
				par1World.extinguishFire(par5EntityPlayer, par2 - 1, par3, par4, 6);
				par1World.extinguishFire(par5EntityPlayer, par2 - 2, par3, par4, 6);
				par1World.extinguishFire(par5EntityPlayer, par2 - 3, par3, par4, 6);
				par1World.extinguishFire(par5EntityPlayer, par2 - 4, par3, par4, 6);

				//Positives on the X Axis
				par1World.extinguishFire(par5EntityPlayer, par2 + 1, par3, par4, 6);
				par1World.extinguishFire(par5EntityPlayer, par2 + 2, par3, par4, 6);
				par1World.extinguishFire(par5EntityPlayer, par2 + 3, par3, par4, 6);
				par1World.extinguishFire(par5EntityPlayer, par2 + 4, par3, par4, 6);

				//Negatives on the Z Axis
				par1World.extinguishFire(par5EntityPlayer, par2, par3, par4 - 1, 6);
				par1World.extinguishFire(par5EntityPlayer, par2, par3, par4 - 2, 6);
				par1World.extinguishFire(par5EntityPlayer, par2, par3, par4 - 3, 6);
				par1World.extinguishFire(par5EntityPlayer, par2, par3, par4 - 4, 6);

				//Positives on the Z Axis
				par1World.extinguishFire(par5EntityPlayer, par2, par3, par4 + 1, 6);
				par1World.extinguishFire(par5EntityPlayer, par2, par3, par4 + 2, 6);
				par1World.extinguishFire(par5EntityPlayer, par2, par3, par4 + 3, 6);
				par1World.extinguishFire(par5EntityPlayer, par2, par3, par4 + 4, 6);

				//Bottom Left of Circle
				par1World.extinguishFire(par5EntityPlayer, par2 - 1, par3, par4 - 1, 6);
				par1World.extinguishFire(par5EntityPlayer, par2 - 2, par3, par4 - 1, 6);
				par1World.extinguishFire(par5EntityPlayer, par2 - 3, par3, par4 - 1, 6);
				par1World.extinguishFire(par5EntityPlayer, par2 - 1, par3, par4 - 2, 6);
				par1World.extinguishFire(par5EntityPlayer, par2 - 2, par3, par4 - 2, 6);	
				par1World.extinguishFire(par5EntityPlayer, par2 - 1, par3, par4 - 3, 6);

				//Bottom Right of Circle
				par1World.extinguishFire(par5EntityPlayer, par2 - 1, par3, par4 + 1, 6);
				par1World.extinguishFire(par5EntityPlayer, par2 - 2, par3, par4 + 1, 6);
				par1World.extinguishFire(par5EntityPlayer, par2 - 3, par3, par4 + 1, 6);	
				par1World.extinguishFire(par5EntityPlayer, par2 - 1, par3, par4 + 2, 6);
				par1World.extinguishFire(par5EntityPlayer, par2 - 2, par3, par4 + 2, 6);		
				par1World.extinguishFire(par5EntityPlayer, par2 - 1, par3, par4 + 3, 6);

				//Top Right of Circle
				par1World.extinguishFire(par5EntityPlayer, par2 + 1, par3, par4 + 1, 6);
				par1World.extinguishFire(par5EntityPlayer, par2 + 2, par3, par4 + 1, 6);
				par1World.extinguishFire(par5EntityPlayer, par2 + 3, par3, par4 + 1, 6);	
				par1World.extinguishFire(par5EntityPlayer, par2 + 1, par3, par4 + 2, 6);
				par1World.extinguishFire(par5EntityPlayer, par2 + 2, par3, par4 + 2, 6);		
				par1World.extinguishFire(par5EntityPlayer, par2 + 1, par3, par4 + 3, 6);

				//Top Left of Circle
				par1World.extinguishFire(par5EntityPlayer, par2 + 1, par3, par4 - 1, 6);
				par1World.extinguishFire(par5EntityPlayer, par2 + 2, par3, par4 - 1, 6);
				par1World.extinguishFire(par5EntityPlayer, par2 + 3, par3, par4 - 1, 6);	
				par1World.extinguishFire(par5EntityPlayer, par2 + 1, par3, par4 - 2, 6);
				par1World.extinguishFire(par5EntityPlayer, par2 + 2, par3, par4 - 2, 6);		
				par1World.extinguishFire(par5EntityPlayer, par2 + 1, par3, par4 - 3, 6);
			}
		}
		return super.onBlockActivated(par1World, par2, par3, par4,par5EntityPlayer, par6, par7, par8, par9);
	}

	public boolean isOpaqueCube() 
	{
		return false;
	}

	public boolean renderAsNormalBlock() 
	{
		return false;
	}
}