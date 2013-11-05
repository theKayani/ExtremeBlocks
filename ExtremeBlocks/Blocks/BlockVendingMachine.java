package ExtremeBlocks.Blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import ExtremeBlocks.ExtremeBlocksMain;

public class BlockVendingMachine extends Block 
{
	public BlockVendingMachine(int par1, Material par2Material) 
	{
		super(par1, par2Material);
		this.setHardness(1.0F);
		this.setUnlocalizedName("VendingMachine");
		this.setCreativeTab(ExtremeBlocksMain.EBMiscTab);
		this.setStepSound(soundMetalFootstep);
		this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 1.5F, 0.9F);
	}

	public int idDropped(int par1, Random par2Random, int par3) 
	{
		return this.blockID;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	private Item[] list = new Item[]
			{
				Item.appleGold,  	 	//1
				Item.helmetChain, 		//2
				Item.beefCooked,  	 	//3
				Item.recordFar, 	 	//4
				Item.legsLeather, 	 	//5
				Item.recordMellohi,  	//6
				Item.plateIron, 	 	//7
				Item.horseArmorGold, 	//8
				Item.helmetLeather,  	//9
				Item.ghastTear,  	 	//10
				Item.fishingRod,  	 	//11
				Item.bootsDiamond,   	//12
				Item.chickenRaw,  	 	//13
				Item.map,  			 	//14
				Item.goldenCarrot,   	//15
				Item.cake, 			 	//16
				Item.fireworkCharge, 	//17
				Item.flowerPot,  	 	//18
				Item.bakedPotato,  	 	//19
				Item.hoeIron,  		 	//20
				Item.leash,			 	//21
				Item.feather,			//22
				Item.bootsGold,		 	//23
				Item.pumpkinPie,	 	//24
				Item.ingotGold,		 	//25
				Item.appleRed,		 	//26
			};

	public boolean onBlockActivated(World par1World, int par2, int par3,int par4, EntityPlayer par5EntityPlayer, int par6, float par7,float par8, float par9) 
	{
		if (par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().itemID == ExtremeBlocksMain.GoldCoin.itemID) 
		{
			par5EntityPlayer.inventory.consumeInventoryItem(ExtremeBlocksMain.GoldCoin.itemID);
			par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(list[par1World.rand.nextInt(26)]));
			return true;
		}
		return super.onBlockActivated(par1World, par2, par3, par4,par5EntityPlayer, par6, par7, par8, par9);
	}

	@SideOnly(Side.CLIENT)
	private Icon[] icons;

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) 
	{
		icons = new Icon[3];

		for (int i = 0; i < icons.length; i++) 
		{
			icons[i] = par1IconRegister.registerIcon(ExtremeBlocksMain.modid+ ":" + (this.getUnlocalizedName().substring(5)) + i);
		}
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) 
	{
		switch (par1) 
		{
		case 0:
			return icons[1];
		case 1:
			return icons[2];
		default:
			return icons[0];
		}
	}
}
