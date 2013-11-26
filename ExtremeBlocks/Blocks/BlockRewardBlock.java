package ExtremeBlocks.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ExtremeBlocks.ExtremeBlocksMain;
import ExtremeBlocks.Vars;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockRewardBlock extends Block 
{	
	public BlockRewardBlock(int par1, Material par2Material) 
	{
		super(par1, par2Material);
		this.setHardness(2.0F);
		this.setUnlocalizedName("RewardBlock");
		this.setCreativeTab(ExtremeBlocksMain.EBMiscTab);
		this.setStepSound(soundStoneFootstep);
	}

	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		int times = Vars.timesTried;
		
		if(Vars.gotReward)
		{
			par5EntityPlayer.addChatMessage("You have recieved you're Reward already!");
		}
		else if(!Vars.gotReward || Vars.isCheated)
		{	
			if(Vars.isAllowed(times, 0, 3))
			{
			par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(ExtremeBlocksMain.TrinquantiumMedal, 1));
			par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Block.blockDiamond, 3));	
		}
			if(Vars.isAllowed(times, 4, 7))
			{
				par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(ExtremeBlocksMain.GoldMedal, 1));
				par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Block.blockDiamond, 2));
			}
			if(Vars.isAllowed(times, 8, 12))
			{
				par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(ExtremeBlocksMain.SilverMedal, 1));
				par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Block.blockDiamond, 1));
			}
			if(times >= 13)
			{
				par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(ExtremeBlocksMain.BronzeMedal, 1));
				par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Item.coal, 20));
			}
			par5EntityPlayer.addChatMessage("Congragulations, " + par5EntityPlayer.username + ", You have Completed the Game!");
			par5EntityPlayer.addChatMessage("You may restart the Game in the Config file and do it all over!");
			Vars.gotReward = true;
		}

		return true;
	}
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) 
	{
		this.blockIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}
}