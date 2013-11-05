package ExtremeBlocks.Items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import ExtremeBlocks.ExtremeBlocksMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPipes extends Item 
{
	public ItemPipes(int par1) 
	{
		super(par1);
		this.setUnlocalizedName("Pipes");
		this.setCreativeTab(ExtremeBlocksMain.EBBasicItemsTab);
	}
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
    }
}
