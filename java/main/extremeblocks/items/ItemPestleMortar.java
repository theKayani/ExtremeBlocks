package main.extremeblocks.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import main.com.hk.testing.util.ItemCustom;
import main.com.hk.testing.util.Rand;
import main.extremeblocks.Init;

public class ItemPestleMortar extends ItemCustom
{
	public ItemPestleMortar()
	{
		super("Pestle And Mortar", Init.tab_mainItems);
		this.setTextureName(Init.MODID + ":pestle_mortar");
		this.setMaxDamage(60);
		this.setMaxStackSize(1);
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {	
		if(player.inventory.consumeInventoryItem(Item.getItemFromBlock(Init.hemp)))
		{
			stack.damageItem(1, player);
			
			if(Rand.nextInt(3) == 1)
			{
				player.inventory.addItemStackToInventory(new ItemStack(Init.hemp_leaves));
			}
		}
		if(player.inventory.consumeInventoryItem(Init.hemp_leaves))
		{
			stack.damageItem(1, player);
			
			if(Rand.nextInt(5) == 1)
			{
				player.inventory.addItemStackToInventory(new ItemStack(Init.mellow_weed));
			}
		}
		else if(player.inventory.consumeInventoryItem(Init.mellow_weed))
		{
			stack.damageItem(1, player);
			
			if(Rand.nextInt(10) == 1)
			{
				player.inventory.addItemStackToInventory(new ItemStack(Init.normal_weed));
			}
		}
        return stack;
    }
}
