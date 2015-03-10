package main.extremeblocks.tools;

import java.util.List;
import main.com.hk.eb.util.JavaHelp;
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
	public final Item resource;
	private static final List<Item[]> allTools = JavaHelp.newArrayList();

	public ColorToolSet(Object resource, ToolMaterial toolMaterial, int color)
	{
		this.toolMaterial = toolMaterial;
		this.color = color;
		this.resource = resource instanceof Block ? Item.getItemFromBlock((Block) resource) : (Item) resource;
		toolMaterial.customCraftingMaterial = this.resource;
		tools = new Item[6];
		tools[0] = this.resource;
		tools[1] = new ItemPickaxeOverlay(toolMaterial, color);
		tools[2] = new ItemShovelOverlay(toolMaterial, color);
		tools[3] = new ItemAxeOverlay(toolMaterial, color);
		tools[4] = new ItemHoeOverlay(toolMaterial, color);
		tools[5] = new ItemSwordOverlay(toolMaterial, color);
		allTools.add(tools);
	}

	public static void registerItems()
	{
		for (Item[] tools : allTools)
		{
			Item resource = tools[0];
			RegistryHelper.register(tools[1]);
			RegistryHelper.register(tools[2]);
			RegistryHelper.register(tools[3]);
			RegistryHelper.register(tools[4]);
			RegistryHelper.register(tools[5]);
			MPUtil.addToolSetRecipe(resource, tools);
		}
	}
}
