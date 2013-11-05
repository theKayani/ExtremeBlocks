package ExtremeBlocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class EBTools extends CreativeTabs {

	public EBTools(int par1, String par2Str) {
		super(par1, par2Str);
	}

	@SideOnly(Side.CLIENT)
	/**
	 * the itemID for the item to be displayed on the tab
	 */
	public int getTabIconItemIndex() {
		return ExtremeBlocksMain.BronzeAxe.itemID;
	}

	public String getTranslatedTabLabel() {
		return "EB Tools";
	}
}
