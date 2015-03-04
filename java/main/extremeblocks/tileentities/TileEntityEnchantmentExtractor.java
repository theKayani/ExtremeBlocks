package main.extremeblocks.tileentities;

import java.util.Map;
import java.util.Map.Entry;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.StackHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class TileEntityEnchantmentExtractor extends TileEntityInventory
{
	public TileEntityEnchantmentExtractor()
	{
		super("Enchantment Extractor");
		inventory = new ItemStack[3];
	}

	@Override
	public boolean update()
	{
		if (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord) && hasBook() && hasValidTool() && canProcess())
		{
			ItemStack tool = inventory[0];
			inventory[1] = new ItemStack(Items.enchanted_book);
			ItemStack book = inventory[2];
			Map<Integer, Integer> firstE = getFirstEnchantment(tool);
			Map<Integer, Integer> restE = getAllButFirstEnchantment(tool);
			setEnchantment(firstE, inventory[1]);
			setEnchantment(restE, tool);
			inventory[0] = StackHelper.consumeItem(book);
		}
		return false;
	}

	public boolean hasBook()
	{
		return inventory[2] != null && inventory[2].getItem() == Items.book;
	}

	public boolean hasValidTool()
	{
		return inventory[0] != null && inventory[0].isItemEnchanted();
	}

	public boolean canProcess()
	{
		return inventory[1] == null;
	}

	@SuppressWarnings("unchecked")
	public Map<Integer, Integer> getFirstEnchantment(ItemStack stack)
	{
		if (stack.isItemEnchanted())
		{
			Map<Integer, Integer> enchs = EnchantmentHelper.getEnchantments(stack);
			Map<Integer, Integer> map = JavaHelp.newHashMap();
			Entry<Integer, Integer> e = enchs.entrySet().toArray(new Entry[0])[0];
			map.put(e.getKey(), e.getValue());
			return map;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Map<Integer, Integer> getAllButFirstEnchantment(ItemStack stack)
	{
		if (stack.isItemEnchanted())
		{
			Map<Integer, Integer> map = EnchantmentHelper.getEnchantments(stack);
			Entry<Integer, Integer> e = map.entrySet().toArray(new Entry[0])[0];
			map.remove(e.getKey());
			return map;
		}
		return null;
	}

	public void setEnchantment(Map<Integer, Integer> map, ItemStack stack)
	{
		EnchantmentHelper.setEnchantments(map, stack);
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		return true;
	}
}
