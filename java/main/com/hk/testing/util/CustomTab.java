package main.com.hk.testing.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CustomTab extends CreativeTabs
{
	public CustomTab(String label)
	{
		super(label);
	}

	@Override
	public Item getTabIconItem()
	{
		return MPUtil.getRandomEBItem();
	}

	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "EB " + this.getTabLabel();
	}
}
