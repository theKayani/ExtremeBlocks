package ExtremeBlocks.Items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ExtremeBlocks.ExtremeBlocksMain;
import ExtremeBlocks.Vars;
import ExtremeBlocks.Blocks.BlockConsole;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNotes extends Item 
{
	public int pageNumber;
	
	public ItemNotes(int par1, int par2) 
	{
		super(par1);
		
		pageNumber = par2;
		
		this.setUnlocalizedName("Notes");
		this.maxStackSize = 1;
		this.setCreativeTab(ExtremeBlocksMain.EBBasicItemsTab);
	}
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) 
	{
		this.itemIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + "Notes");
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		if(pageNumber == 0)
		{
			par3List.add("Finders Keepers!");
			par3List.add("To Play this Game ");
			par3List.add("you have to Find the ");
			par3List.add("Reward Block!");
			par3List.add("it is somewhere in ");
			par3List.add("the sky! Use the four ");
			par3List.add("blocks around the");
			par3List.add("platform to get to ");
		}
		if(pageNumber == 1)	
		{
			par3List.add("it! The blocks spread");
			par3List.add("in to  four directions!");
			par3List.add("Be careful, the blocks ");
			par3List.add("could dissapear with");
			par3List.add("any kind of contact! Once");
			par3List.add("to the Block, click it ");
			par3List.add("and you will recieve");
			par3List.add("you're reward!");
		}
	}
}
