package main.extremeblocks.items;

import main.com.hk.testing.util.ItemCustom;
import main.extremeblocks.Init;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemExtractor extends ItemCustom
{
	public ItemExtractor()
	{
		super("Extractor", Init.tab_mainItems);
		this.setMaxDamage(59);
		this.setTextureName(Init.MODID + ":extractor");
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		Block block = par3World.getBlock(par4, par5, par6);
		if (block == Blocks.log)
		{
			par1ItemStack.damageItem(1, par2EntityPlayer);
			par2EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Init.sap));
			par3World.setBlock(par4, par5, par6, Init.emptied_log);
		}
		return true;
	}
}
