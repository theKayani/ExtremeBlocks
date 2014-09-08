package main.com.hk.testing.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class RegistryHelper
{
	public static final Block[] blocksList = new Block[4095];
	public static final Item[] itemsList = new Item[31999];

	public static void register(Object obj)
	{
		if (obj instanceof Block) GameRegistry.registerBlock(((Block) obj), ((Block) obj).getUnlocalizedName());
		else if (obj instanceof Item) GameRegistry.registerItem(((Item) obj), ((Item) obj).getUnlocalizedName());
		else throw new IllegalArgumentException("Object isn't an Item or a Block... This is BAD! Object Class: " + obj.getClass().getName());
	}
}
