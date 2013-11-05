package ExtremeBlocks.Items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ExtremeBlocks.ExtremeBlocksMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCellphone extends Item 
{
	 
	public ItemCellphone(int par1) 
	{
		super(par1);
		this.setCreativeTab(ExtremeBlocksMain.EBBasicItemsTab);
		this.maxStackSize = 1;
		this.setUnlocalizedName("Cellphone");
	}
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
    }
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add(this.getTheInfo(par1ItemStack, par2EntityPlayer, par3List));
		par3List.add(this.getTheInfo2(par1ItemStack, par2EntityPlayer, par3List));
	}
	public String getTheInfo(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List)
	{	
		String Username = par2EntityPlayer.username;
		int Distance = (int) par2EntityPlayer.distanceWalkedModified;
		
		return "Welcome " + Username + "! You Have Traveled " + Distance + " Feet.";
	}
	public String getTheInfo2(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List)
	{	
		int Oldness = par2EntityPlayer.getAge() / 1350;
		int Health = (int) par2EntityPlayer.getHealth() / 2;
		
		return "You are " + Oldness + " Minutes Old. Your Health is " + Health + ".";
	}	
}
