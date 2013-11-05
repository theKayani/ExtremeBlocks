package ExtremeBlocks.Items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ExtremeBlocks.ExtremeBlocksMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemExtractor extends Item 
{

	public ItemExtractor(int par1) 
	{
		super(par1);
		this.setCreativeTab(ExtremeBlocksMain.EBBasicItemsTab);
		this.maxStackSize = 3;
		this.setUnlocalizedName("Extractor");
	}
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if(par3World.getBlockId(par4, par5, par6) == Block.wood.blockID)
		{
			par3World.setBlock(par4, par5, par6, ExtremeBlocksMain.EmptiedLog.blockID);
			par2EntityPlayer.inventory.addItemStackToInventory(new ItemStack(ExtremeBlocksMain.Sap, par3World.rand.nextInt(3)));
			return true;
		}
		return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
	}
}
