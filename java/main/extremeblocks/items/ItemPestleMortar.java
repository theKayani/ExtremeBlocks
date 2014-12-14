package main.extremeblocks.items;

import main.com.hk.eb.util.ItemCustom;
import main.com.hk.eb.util.Rand;
import main.extremeblocks.Init;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPestleMortar extends ItemCustom
{
	public ItemPestleMortar()
	{
		super("Pestle And Mortar", Init.tab_mainItems);
		setTextureName(Init.MODID + ":pestle_mortar");
		setMaxDamage(60);
		setMaxStackSize(1);
		setShowRecipe();
		setInfo("When right clicked:\nWith Cannabis Plant in your inventory, 33% chance to get Hemp Leaves\nWith Hemp Leaves in your inventory, 20% chance to get Mellow Weed\nWith Mellow Weed in your inventory, 10% chance to give you Weed.");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (player.inventory.consumeInventoryItem(Item.getItemFromBlock(Init.cannabis_plant)))
		{
			stack.damageItem(1, player);
			if (Rand.nextInt(3) == 0)
			{
				player.inventory.addItemStackToInventory(new ItemStack(Init.hemp_leaves));
			}
		}
		else if (player.inventory.consumeInventoryItem(Init.hemp_leaves))
		{
			stack.damageItem(1, player);
			if (Rand.nextInt(5) == 0)
			{
				player.inventory.addItemStackToInventory(new ItemStack(Init.mellow_weed));
			}
		}
		else if (player.inventory.consumeInventoryItem(Init.mellow_weed))
		{
			stack.damageItem(1, player);
			if (Rand.nextInt(10) == 0)
			{
				player.inventory.addItemStackToInventory(new ItemStack(Init.normal_weed));
			}
		}
		return stack;
	}
}
