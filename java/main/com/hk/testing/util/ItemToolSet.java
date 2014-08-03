package main.com.hk.testing.util;

import java.util.ArrayList;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemToolSet
{
	public ArrayList<Item> toolSet = new ArrayList<Item>();
	private final ToolMaterial material;
	private final Item item;
	private final String namePrefix;
	private final String modid;
	private final CreativeTabs tab;

	public ItemToolSet(ToolMaterial material, Item item, String namePrefix, String modid, CreativeTabs tab)
	{
		this.material = material;
		this.item = item;
		this.namePrefix = namePrefix;
		this.modid = modid;
		this.tab = tab;

		this.addToolSet();
	}

	public ItemToolSet registerTools()
	{
		for (int i = 0; i < toolSet.size(); i++)
		{
			RegistryHelper.register(toolSet.get(i));
		}
		return this;
	}

	private void addToolSet()
	{
		Item[] tools = new Item[] {
		new ItemCPickaxe(material), new ItemCSpade(material), new ItemCAxe(material), new ItemCHoe(material), new ItemCSword(material)
		};

		tools[0].setUnlocalizedName(namePrefix + " Pickaxe");
		tools[1].setUnlocalizedName(namePrefix + " Shovel");
		tools[2].setUnlocalizedName(namePrefix + " Axe");
		tools[3].setUnlocalizedName(namePrefix + " Hoe");
		tools[4].setUnlocalizedName(namePrefix + " Sword");

		tools[0].setTextureName(modid + ":" + namePrefix.toLowerCase() + "_pickaxe");
		tools[1].setTextureName(modid + ":" + namePrefix.toLowerCase() + "_shovel");
		tools[2].setTextureName(modid + ":" + namePrefix.toLowerCase() + "_axe");
		tools[3].setTextureName(modid + ":" + namePrefix.toLowerCase() + "_hoe");
		tools[4].setTextureName(modid + ":" + namePrefix.toLowerCase() + "_sword");

		for (int i = 0; i < tools.length; i++)
		{
			tools[i].setCreativeTab(tab);
			toolSet.add(tools[i]);
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
