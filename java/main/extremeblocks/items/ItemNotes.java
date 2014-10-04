package main.extremeblocks.items;

import main.com.hk.eb.util.ItemCustom;
import main.extremeblocks.Init;

public class ItemNotes extends ItemCustom
{
	private final boolean first;

	public ItemNotes(boolean first)
	{
		super("Notes[" + (first ? "1" : "0") + "]", Init.tab_mainItems);
		this.setTextureName("paper");
		this.first = first;
	}
}
