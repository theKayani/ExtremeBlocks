package ExtremeBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class EBTools extends CreativeTabs 
{
	public EBTools(int id, String name) 
	{
		super(id, name);
	}

	@Override
	public int getTabIconItemIndex() 
	{
		return ExtremeBlocksMain.BronzeAxe.itemID;
	}
	
	public String getTranslatedTabLabel()
	{
		return "EB Tools";
	}
}
