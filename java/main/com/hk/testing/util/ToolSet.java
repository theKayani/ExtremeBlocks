package main.com.hk.testing.util;

import java.util.ArrayList;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.Init;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

public class ToolSet
{
	private final ToolMaterial material;
	private final Item item;
	private final String namePrefix;
	private final String modid = Init.MODID;
	private final CreativeTabs tab = Init.tab_tools;

	public ToolSet(ToolMaterial material, Item item, String namePrefix)
	{
		this.material = material;
		this.item = item;
		this.namePrefix = namePrefix;
		createSetAndRegisterThenAddRecipes();
	}

	private void createSetAndRegisterThenAddRecipes()
	{
		Item[] tools = new Item[] { new ItemCPickaxe(material), new ItemCSpade(material), new ItemCAxe(material), new ItemCHoe(material), new ItemCSword(material) };
		tools[0].setUnlocalizedName(namePrefix + " Pickaxe").setTextureName(modid + ":" + namePrefix.toLowerCase() + "_pickaxe").setCreativeTab(tab);
		tools[1].setUnlocalizedName(namePrefix + " Shovel").setTextureName(modid + ":" + namePrefix.toLowerCase() + "_shovel").setCreativeTab(tab);
		tools[2].setUnlocalizedName(namePrefix + " Axe").setTextureName(modid + ":" + namePrefix.toLowerCase() + "_axe").setCreativeTab(tab);
		tools[3].setUnlocalizedName(namePrefix + " Hoe").setTextureName(modid + ":" + namePrefix.toLowerCase() + "_hoe").setCreativeTab(tab);
		tools[4].setUnlocalizedName(namePrefix + " Sword").setTextureName(modid + ":" + namePrefix.toLowerCase() + "_sword").setCreativeTab(tab);
		for (Item tool : tools)
		{
			RegistryHelper.register(tool);
		}
		MPUtil.addToolSetRecipe(item, tools);
	}

	public static class ItemCAxe extends ItemAxe
	{
		public ItemCAxe(ToolMaterial mat)
		{
			super(mat);
			this.setTextureName(getUnlocalizedName());
		}

		public String getUnlocalizedName()
		{
			return super.getUnlocalizedName().replaceAll("item.", "");
		}

		public String getUnlocalizedName(ItemStack par1ItemStack)
		{
			return this.getUnlocalizedName();
		}

		public String getItemStackDisplayName(ItemStack par1ItemStack)
		{
			return this.getUnlocalizedNameInefficiently(par1ItemStack).trim();
		}
	}

	public static class ItemCPickaxe extends ItemPickaxe
	{
		public ItemCPickaxe(ToolMaterial mat)
		{
			super(mat);
		}

		public String getUnlocalizedName()
		{
			return super.getUnlocalizedName().replaceAll("item.", "");
		}

		public String getUnlocalizedName(ItemStack par1ItemStack)
		{
			return this.getUnlocalizedName();
		}

		public String getItemStackDisplayName(ItemStack par1ItemStack)
		{
			return this.getUnlocalizedNameInefficiently(par1ItemStack).trim();
		}
	}

	public static class ItemCHoe extends ItemHoe
	{
		public ItemCHoe(ToolMaterial mat)
		{
			super(mat);
		}

		public String getUnlocalizedName()
		{
			return super.getUnlocalizedName().replaceAll("item.", "");
		}

		public String getUnlocalizedName(ItemStack par1ItemStack)
		{
			return this.getUnlocalizedName();
		}

		public String getItemStackDisplayName(ItemStack par1ItemStack)
		{
			return this.getUnlocalizedNameInefficiently(par1ItemStack).trim();
		}
	}

	public static class ItemCSword extends ItemSword
	{
		public ItemCSword(ToolMaterial mat)
		{
			super(mat);
		}

		public String getUnlocalizedName()
		{
			return super.getUnlocalizedName().replaceAll("item.", "");
		}

		public String getUnlocalizedName(ItemStack par1ItemStack)
		{
			return this.getUnlocalizedName();
		}

		public String getItemStackDisplayName(ItemStack par1ItemStack)
		{
			return this.getUnlocalizedNameInefficiently(par1ItemStack).trim();
		}
	}

	public static class ItemCSpade extends ItemSpade
	{
		public ItemCSpade(ToolMaterial mat)
		{
			super(mat);
		}

		public String getUnlocalizedName()
		{
			return super.getUnlocalizedName().replaceAll("item.", "");
		}

		public String getUnlocalizedName(ItemStack par1ItemStack)
		{
			return this.getUnlocalizedName();
		}

		public String getItemStackDisplayName(ItemStack par1ItemStack)
		{
			return this.getUnlocalizedNameInefficiently(par1ItemStack).trim();
		}
	}
}
