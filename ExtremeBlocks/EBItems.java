package ExtremeBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class EBItems extends CreativeTabs 
{
	public EBItems(int id, String name) 
	{
		super(id, name);
		this.getTranslatedTabLabel();
	}

	@Override
	public int getTabIconItemIndex() 
	{
		return ExtremeBlocksMain.GlesterRock.itemID;
	}
	
	public String getTranslatedTabLabel()
	{
		return "EB Basic Items";
	}
}