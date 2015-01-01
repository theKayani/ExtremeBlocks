package main.extremeblocks.items;

import main.com.hk.eb.util.ItemCustom;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.Init;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEBGuide extends ItemCustom
{
	public ItemEBGuide()
	{
		super("The EB Guide", Init.tab_mainItems);
		setTextureName(Init.MODID + ":eb_guide");
		setShowRecipe();
		setInfo("The EB Guide to everything. You're reading it...");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player)
	{
		ExtremeBlocks.proxy.openEBGuide();
		return par1ItemStack;
	}
}
