package main.extremeblocks.items;

import main.com.hk.eb.util.ItemCustom;
import main.extremeblocks.Init;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBackpack extends ItemCustom
{
	public ItemBackpack()
	{
		super("Ender Backpack", Init.tab_mainItems);
		setTextureName(Init.MODID + ":backpack");
		setInfo("Simply. A Portable Ender Chest. Quite OP really.");
		setShowRecipe();
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.displayGUIChest(par3EntityPlayer.getInventoryEnderChest());
		return par1ItemStack;
	}
}
