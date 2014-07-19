package main.extremeblocks.items;

import main.com.hk.testing.util.ItemCustom;
import main.extremeblocks.Init;

public class ItemReturner extends ItemCustom
{
	public ItemReturner()
	{
		super("Returner", Init.tab_mainItems);
		this.setTextureName(Init.MODID + ":returner");
	}
}
