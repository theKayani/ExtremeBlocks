package ExtremeBlocks.Items;

import ExtremeBlocks.ExtremeBlocksMain;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBronzeMedal extends Item 
{
	public ItemBronzeMedal(int par1) 
	{
		super(par1);
		this.setCreativeTab(ExtremeBlocksMain.EBBasicItemsTab);
		this.maxStackSize = 3;
		this.setUnlocalizedName("BronzeMedal");
	}
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
    }
}
