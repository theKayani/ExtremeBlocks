package extremeblocks.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import com.hk.testing.util.ItemCustom;
import extremeblocks.ExtremeBlocks;
import extremeblocks.Init;

public class ItemBackpack extends ItemCustom
{
	public ItemBackpack()
	{
		super("Ender Backpack", Init.tab_mainItems);
		this.setTextureName(Init.MODID + ":backpack");
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.displayGUIChest(par3EntityPlayer.getInventoryEnderChest());
		return par1ItemStack;
	}
}
