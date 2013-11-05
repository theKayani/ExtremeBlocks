package ExtremeBlocks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ExtremeBlocksRegistry 
{
	/**Registers the Given Block to Forge.
	 * 
	 * @param block The Block being registered
	 * @param name1 the Name without any spaces (Not Shown in game)
	 * @param name2 The Ordinary Name (Shown in game)
	 */
	public static void RegisterBlock(Block block, String name1, String name2)
	{
		GameRegistry.registerBlock(block, name1);
		LanguageRegistry.addName(block, name2);
	}
	
	/** Registers the Given Item to Forge.
	 * 
	 * @param item The Item being registered
	 * @param name1 the Name without any spaces (Not Shown in game)
	 * @param name2 The Ordinary Name (Shown in game)
	 */
	public static void RegisterItem(Item item, String name1, String name2)
	{
		GameRegistry.registerItem(item, name1);
		LanguageRegistry.addName(item, name2);
	}	
}
