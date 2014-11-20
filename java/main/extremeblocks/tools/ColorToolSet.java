package main.extremeblocks.tools;

import main.com.hk.eb.util.MPUtil;
import main.com.hk.eb.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.EnumHelper;

public class ColorToolSet
{
	public final ToolMaterial toolMaterial;
	public final ArmorMaterial armorMaterial;
	public final int color;
	public final Item[] tools;
	public final Item[] armors;
	public final Object resource;

	public ColorToolSet(Object resource, ToolMaterial toolMaterial, int color)
	{
		this(resource, toolMaterial, getArmorMaterial(toolMaterial), color);
	}

	public ColorToolSet(Object resource, ToolMaterial toolMaterial, ArmorMaterial armorMaterial, int color)
	{
		this.toolMaterial = toolMaterial;
		this.armorMaterial = armorMaterial;
		this.color = color;
		this.resource = resource;
		toolMaterial.customCraftingMaterial = resource instanceof Block ? Item.getItemFromBlock((Block) resource) : (Item) resource;
		tools = new Item[5];
		tools[0] = new ItemPickaxeOverlay(toolMaterial, color);
		tools[1] = new ItemShovelOverlay(toolMaterial, color);
		tools[2] = new ItemAxeOverlay(toolMaterial, color);
		tools[3] = new ItemHoeOverlay(toolMaterial, color);
		tools[4] = new ItemSwordOverlay(toolMaterial, color);

		for (Item item : tools)
		{
			RegistryHelper.register(item);
		}
		MPUtil.addToolSetRecipe(resource, tools);

		armors = new Item[4];
		armors[0] = new ItemArmorOverlay(armorMaterial, 0, color);
		armors[1] = new ItemArmorOverlay(armorMaterial, 1, color);
		armors[2] = new ItemArmorOverlay(armorMaterial, 2, color);
		armors[3] = new ItemArmorOverlay(armorMaterial, 3, color);

		for (Item item : armors)
		{
			RegistryHelper.register(item);
		}
		MPUtil.addArmorSetRecipe(resource, armors);
	}

	private static ArmorMaterial getArmorMaterial(ToolMaterial copy)
	{
		int x = MathHelper.floor_float(copy.getEfficiencyOnProperMaterial());
		int a = (x - 2) / 2;
		int c = x - 2;
		int[] use = { Math.max(a, 1), Math.max(x, 1), Math.max(c, 1), Math.max(a, 1) };
		return EnumHelper.addArmorMaterial(copy.name(), copy.getMaxUses() / 50, use, copy.getEnchantability());
	}

	private static ArmorMaterial getArmorMaterial(String name, int durability, int enchantability, int... ints)
	{
		return EnumHelper.addArmorMaterial(name, durability, ints, enchantability);
	}

	private static ToolMaterial getToolMaterial(String name, int harvestLevel, int durability, int enchantability, int efficiency, int damage)
	{
		return EnumHelper.addToolMaterial(name, harvestLevel, durability, efficiency, damage, enchantability);
	}
}
