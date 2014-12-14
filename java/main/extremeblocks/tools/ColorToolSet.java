package main.extremeblocks.tools;

import main.com.hk.eb.util.MPUtil;
import main.com.hk.eb.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public class ColorToolSet
{
	public final ToolMaterial toolMaterial;
	public final int color;
	public final Item[] tools;
	public final Object resource;

	public ColorToolSet(Object resource, ToolMaterial toolMaterial, int color)
	{
		this.toolMaterial = toolMaterial;
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
	}
}
