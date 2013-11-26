package ExtremeBlocks.Items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import ExtremeBlocks.ExtremeBlocksMain;
import ExtremeBlocks.Vars;
import ExtremeBlocks.Blocks.BlockConsole;
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

	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if(Vars.playing)
		{
			par2EntityPlayer.setPosition(Vars.x, Vars.y + 2, Vars.z);

			Vars.playing = false;
		}
		return true;
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add(EnumChatFormatting.GOLD + "[|][|]Finders Keepers[|][|]");
		if(Vars.timesTried > 1)
		{
			par3List.add("You have played " + Vars.timesTried + " times.");
		}
		else if(Vars.timesTried == 1)
		{
			par3List.add("You have played " + Vars.timesTried + " time.");
		}
		else if(Vars.timesTried == 0)
		{
			par3List.add(EnumChatFormatting.RED + "You have not played the Game yet!");
		}
	}
}
