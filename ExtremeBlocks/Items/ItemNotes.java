package extremeblocks.items;

import com.hk.testing.util.ItemCustom;
import extremeblocks.Init;

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
