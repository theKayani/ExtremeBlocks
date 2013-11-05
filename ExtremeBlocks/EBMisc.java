package ExtremeBlocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class EBMisc extends CreativeTabs {

	public EBMisc(int par1, String par2Str) {
		super(par1, par2Str);
	}

	@SideOnly(Side.CLIENT)
	/**
	 * the itemID for the item to be displayed on the tab
	 */
	public int getTabIconItemIndex() {
		return ExtremeBlocksMain.FireHydrant.blockID;
	}

	public String getTranslatedTabLabel() {
		return "EB Miscellaneous";
	}
}
