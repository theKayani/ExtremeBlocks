package extremeblocks.items;

import com.hk.testing.util.ItemCustom;
import extremeblocks.ExtremeBlocks;
import extremeblocks.Init;

public class ItemReturner extends ItemCustom
{
	public ItemReturner()
	{
		super("Returner", Init.tab_mainItems);
		this.setTextureName(Init.MODID + ":returner");
	}
}
