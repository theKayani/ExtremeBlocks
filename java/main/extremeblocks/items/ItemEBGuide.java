package main.extremeblocks.items;

import main.com.hk.eb.util.Info;
import main.com.hk.eb.util.ItemCustom;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.GuiIDs;
import main.extremeblocks.Init;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEBGuide extends ItemCustom implements GuiIDs, Info
{
	public ItemEBGuide()
	{
		super("The EB Guide", Init.tab_mainItems);
		setTextureName(Init.MODID + ":eb_guide");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player)
	{
		ExtremeBlocks.proxy.openEBGuide();
		return par1ItemStack;
	}

	@Override
	public String getInfo()
	{
		return "The EB Guide to everything. You're reading it...";
	}

	@Override
	public Elements getElements()
	{
		return new Elements(true, true);
	}
}
