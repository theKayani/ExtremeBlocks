package ExtremeBlocks.Items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ExtremeBlocks.ExtremeBlocksMain;
import ExtremeBlocks.Vars;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemReturner extends Item 
{
	public ItemReturner(int par1) 
	{
		super(par1);
		this.setUnlocalizedName("Returner");
		this.setCreativeTab(ExtremeBlocksMain.EBBasicItemsTab);
	}
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) 
	{
		this.itemIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if(Vars.playing)
		{
			par3EntityPlayer.setPosition(Vars.Gx, Vars.Gy - 200, Vars.Gz);

			Vars.playing = false;
		}
		if(!Vars.playing)
		{
			par3EntityPlayer.addChatMessage("This is useless if you aren't playing the game!");
		}

		return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);	
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add("Returner for Xbox 360");
		par3List.add("" + Vars.playing + ", " + Vars.startedGame);
		par3List.add("" + Vars.Gx + ", " + Vars.Gy + ", " + Vars.Gz);
	}
}
