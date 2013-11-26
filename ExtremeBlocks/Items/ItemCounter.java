package ExtremeBlocks.Items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ExtremeBlocks.ExtremeBlocksMain;
import ExtremeBlocks.Vars;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCounter extends Item 
{
	public static int Count = 0;

	public ItemCounter(int par1) 
	{
		super(par1);
		this.setCreativeTab(ExtremeBlocksMain.EBBasicItemsTab);
		this.setUnlocalizedName("Counter");
	}
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		Count++;
		return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);	
	}

	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if(Count != 0)
		{
			Count = 0;
		}
		else if(Count == 0)
		{
			Count++;
		}
		return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);	
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		int Length = Vars.CFCounterMessage.length();

		par3List.add("Count is " + Count + ".");

		if(Length <= 10)
		{
			if(Vars.CFCounterMessage.contains("free"));
			{
				if(Length == 13)
				{
					Vars.isCheated = true;
					par2EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Block.blockDiamond, 64));
					par2EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Block.blockEmerald, 64));
					par2EntityPlayer.inventory.addItemStackToInventory(new ItemStack(ExtremeBlocksMain.TrinquantiumBlock, 64));
					par2EntityPlayer.inventory.addItemStackToInventory(new ItemStack(ExtremeBlocksMain.RewardBlock, 64));
				}
			}
			if(!Vars.CFCounterMessage.equals("GIVE ME STUFF"))
			{
				par3List.add(Vars.CFCounterMessage);
			}
		}

		else if(Length > 10 && Length <= 20)
		{		
			String firstHalf = Vars.CFCounterMessage.substring(0, 10);
			String secondHalf = Vars.CFCounterMessage.substring(10, Length);

			par3List.add(firstHalf);
			par3List.add(secondHalf);
		}

		else if(Length > 20 && Length <= 30)
		{
			String firstThird = Vars.CFCounterMessage.substring(0, 10);
			String secondThird = Vars.CFCounterMessage.substring(10, 20);
			String thirdThird = Vars.CFCounterMessage.substring(20, Length);

			par3List.add(firstThird);
			par3List.add(secondThird);
			par3List.add(thirdThird);
		}

		else if(Length > 30 && Length <= 40)
		{
			String firstQuarter = Vars.CFCounterMessage.substring(0, 10);
			String secondQuarter = Vars.CFCounterMessage.substring(10, 20);
			String thirdQuarter = Vars.CFCounterMessage.substring(20, 30);
			String fourthQuarter = Vars.CFCounterMessage.substring(30, Length);

			par3List.add(firstQuarter);
			par3List.add(secondQuarter);
			par3List.add(thirdQuarter);
			par3List.add(fourthQuarter);
		}

		else if(Length > 40)
		{
			par3List.add("Put a smaller");
			par3List.add("message, Please!");
		}
	}	
}
