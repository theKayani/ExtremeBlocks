package main.extremeblocks.crafting;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import main.com.hk.eb.util.JavaHelp;
import main.extremeblocks.Vars;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeBookCloning;
import net.minecraft.item.crafting.RecipeFireworks;
import net.minecraft.item.crafting.RecipesArmorDyes;
import net.minecraft.item.crafting.RecipesMapCloning;
import net.minecraft.item.crafting.RecipesMapExtending;
import net.minecraft.world.World;

public class EBCraftingManager
{
	public static final EBCraftingManager INSTANCE = new EBCraftingManager();
	public static final int WILDCARD_VALUE = Short.MAX_VALUE;
	private List<IRecipe> recipes = JavaHelp.newArrayList();

	private EBCraftingManager()
	{
		String[][] recipePatterns;
		Object[][] recipeItems;
		if (Vars.addVanillaRecipes)
		{
			recipePatterns = new String[][] { { "XXX", " # ", " # " }, { "X", "#", "#" }, { "XX", "X#", " #" }, { "XX", " #", " #" } };
			recipeItems = new Object[][] { { Blocks.planks, Blocks.cobblestone, Items.iron_ingot, Items.diamond, Items.gold_ingot }, { Items.wooden_pickaxe, Items.stone_pickaxe, Items.iron_pickaxe, Items.diamond_pickaxe, Items.golden_pickaxe }, { Items.wooden_shovel, Items.stone_shovel, Items.iron_shovel, Items.diamond_shovel, Items.golden_shovel }, { Items.wooden_axe, Items.stone_axe, Items.iron_axe, Items.diamond_axe, Items.golden_axe }, { Items.wooden_hoe, Items.stone_hoe, Items.iron_hoe, Items.diamond_hoe, Items.golden_hoe } };
			for (int i = 0; i < recipeItems[0].length; ++i)
			{
				Object object = recipeItems[0][i];
				for (int j = 0; j < recipeItems.length - 1; ++j)
				{
					Item item = (Item) recipeItems[j + 1][i];
					addRecipe(new ItemStack(item), recipePatterns[j], '#', Items.stick, 'X', object);
				}
			}
			addRecipe(new ItemStack(Items.shears), " #", "# ", '#', Items.iron_ingot);
			recipePatterns = new String[][] { { "X", "X", "#" } };
			recipeItems = new Object[][] { { Blocks.planks, Blocks.cobblestone, Items.iron_ingot, Items.diamond, Items.gold_ingot }, { Items.wooden_sword, Items.stone_sword, Items.iron_sword, Items.diamond_sword, Items.golden_sword } };
			for (int i = 0; i < recipeItems[0].length; ++i)
			{
				Object object = recipeItems[0][i];
				for (int j = 0; j < recipeItems.length - 1; ++j)
				{
					Item item = (Item) recipeItems[j + 1][i];
					addRecipe(new ItemStack(item), recipePatterns[j], '#', Items.stick, 'X', object);
				}
			}
			addRecipe(new ItemStack(Items.bow, 1), " #X", "# X", " #X", 'X', Items.string, '#', Items.stick);
			addRecipe(new ItemStack(Items.arrow, 4), "X", "#", "Y", 'Y', Items.feather, 'X', Items.flint, '#', Items.stick);
			recipeItems = new Object[][] { { Blocks.gold_block, new ItemStack(Items.gold_ingot, 9) }, { Blocks.iron_block, new ItemStack(Items.iron_ingot, 9) }, { Blocks.diamond_block, new ItemStack(Items.diamond, 9) }, { Blocks.emerald_block, new ItemStack(Items.emerald, 9) }, { Blocks.lapis_block, new ItemStack(Items.dye, 9, 4) }, { Blocks.redstone_block, new ItemStack(Items.redstone, 9) }, { Blocks.coal_block, new ItemStack(Items.coal, 9, 0) }, { Blocks.hay_block, new ItemStack(Items.wheat, 9) } };
			for (Object[] recipeItem : recipeItems)
			{
				Block block = (Block) recipeItem[0];
				ItemStack itemstack = (ItemStack) recipeItem[1];
				addRecipe(new ItemStack(block), "###", "###", "###", '#', itemstack);
				addRecipe(itemstack, "#", '#', block);
			}
			addRecipe(new ItemStack(Items.gold_ingot), "###", "###", "###", '#', Items.gold_nugget);
			addRecipe(new ItemStack(Items.gold_nugget, 9), "#", '#', Items.gold_ingot);
			addShapelessRecipe(new ItemStack(Items.mushroom_stew), Blocks.brown_mushroom, Blocks.red_mushroom, Items.bowl);
			addRecipe(new ItemStack(Items.cookie, 8), "#X#", 'X', new ItemStack(Items.dye, 1, 3), '#', Items.wheat);
			addRecipe(new ItemStack(Blocks.melon_block), "MMM", "MMM", "MMM", 'M', Items.melon);
			addRecipe(new ItemStack(Items.melon_seeds), "M", 'M', Items.melon);
			addRecipe(new ItemStack(Items.pumpkin_seeds, 4), "M", 'M', Blocks.pumpkin);
			addShapelessRecipe(new ItemStack(Items.pumpkin_pie), Blocks.pumpkin, Items.sugar, Items.egg);
			addShapelessRecipe(new ItemStack(Items.fermented_spider_eye), Items.spider_eye, Blocks.brown_mushroom, Items.sugar);
			addShapelessRecipe(new ItemStack(Items.blaze_powder, 2), Items.blaze_rod);
			addShapelessRecipe(new ItemStack(Items.magma_cream), Items.blaze_powder, Items.slime_ball);
			addRecipe(new ItemStack(Blocks.chest), "###", "# #", "###", '#', Blocks.planks);
			addRecipe(new ItemStack(Blocks.trapped_chest), "#-", '#', Blocks.chest, '-', Blocks.tripwire_hook);
			addRecipe(new ItemStack(Blocks.ender_chest), "###", "#E#", "###", '#', Blocks.obsidian, 'E', Items.ender_eye);
			addRecipe(new ItemStack(Blocks.furnace), "###", "# #", "###", '#', Blocks.cobblestone);
			addRecipe(new ItemStack(Blocks.crafting_table), "##", "##", '#', Blocks.planks);
			addRecipe(new ItemStack(Blocks.sandstone), "##", "##", '#', new ItemStack(Blocks.sand, 1, 0));
			addRecipe(new ItemStack(Blocks.sandstone, 4, 2), "##", "##", '#', Blocks.sandstone);
			addRecipe(new ItemStack(Blocks.sandstone, 1, 1), "#", "#", '#', new ItemStack(Blocks.stone_slab, 1, 1));
			addRecipe(new ItemStack(Blocks.quartz_block, 1, 1), "#", "#", '#', new ItemStack(Blocks.stone_slab, 1, 7));
			addRecipe(new ItemStack(Blocks.quartz_block, 2, 2), "#", "#", '#', new ItemStack(Blocks.quartz_block, 1, 0));
			addRecipe(new ItemStack(Blocks.stonebrick, 4), "##", "##", '#', Blocks.stone);
			addRecipe(new ItemStack(Blocks.iron_bars, 16), "###", "###", '#', Items.iron_ingot);
			addRecipe(new ItemStack(Blocks.glass_pane, 16), "###", "###", '#', Blocks.glass);
			addRecipe(new ItemStack(Blocks.redstone_lamp, 1), " R ", "RGR", " R ", 'R', Items.redstone, 'G', Blocks.glowstone);
			addRecipe(new ItemStack(Blocks.beacon, 1), "GGG", "GSG", "OOO", 'G', Blocks.glass, 'S', Items.nether_star, 'O', Blocks.obsidian);
			addRecipe(new ItemStack(Blocks.nether_brick, 1), "NN", "NN", 'N', Items.netherbrick);
			recipePatterns = new String[][] { { "XXX", "X X" }, { "X X", "XXX", "XXX" }, { "XXX", "X X", "X X" }, { "X X", "X X" } };
			recipeItems = new Object[][] { { Items.leather, Blocks.fire, Items.iron_ingot, Items.diamond, Items.gold_ingot }, { Items.leather_helmet, Items.chainmail_helmet, Items.iron_helmet, Items.diamond_helmet, Items.golden_helmet }, { Items.leather_chestplate, Items.chainmail_chestplate, Items.iron_chestplate, Items.diamond_chestplate, Items.golden_chestplate }, { Items.leather_leggings, Items.chainmail_leggings, Items.iron_leggings, Items.diamond_leggings, Items.golden_leggings }, { Items.leather_boots, Items.chainmail_boots, Items.iron_boots, Items.diamond_boots, Items.golden_boots } };
			for (int i = 0; i < recipeItems[0].length; ++i)
			{
				Object object = recipeItems[0][i];
				for (int j = 0; j < recipeItems.length - 1; ++j)
				{
					Item item = (Item) recipeItems[j + 1][i];
					addRecipe(new ItemStack(item), recipePatterns[j], 'X', object);
				}
			}
			int i;
			for (i = 0; i < 16; ++i)
			{
				addShapelessRecipe(new ItemStack(Blocks.wool, 1, BlockColored.func_150031_c(i)), new ItemStack(Items.dye, 1, i), new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 0));
				addRecipe(new ItemStack(Blocks.stained_hardened_clay, 8, BlockColored.func_150031_c(i)), "###", "#X#", "###", '#', new ItemStack(Blocks.hardened_clay), 'X', new ItemStack(Items.dye, 1, i));
				addRecipe(new ItemStack(Blocks.stained_glass, 8, BlockColored.func_150031_c(i)), "###", "#X#", "###", '#', new ItemStack(Blocks.glass), 'X', new ItemStack(Items.dye, 1, i));
				addRecipe(new ItemStack(Blocks.stained_glass_pane, 16, i), "###", "###", '#', new ItemStack(Blocks.stained_glass, 1, i));
			}
			addShapelessRecipe(new ItemStack(Items.dye, 1, 11), new ItemStack(Blocks.yellow_flower, 1, 0));
			addShapelessRecipe(new ItemStack(Items.dye, 1, 1), new ItemStack(Blocks.red_flower, 1, 0));
			addShapelessRecipe(new ItemStack(Items.dye, 3, 15), Items.bone);
			addShapelessRecipe(new ItemStack(Items.dye, 2, 9), new ItemStack(Items.dye, 1, 1), new ItemStack(Items.dye, 1, 15));
			addShapelessRecipe(new ItemStack(Items.dye, 2, 14), new ItemStack(Items.dye, 1, 1), new ItemStack(Items.dye, 1, 11));
			addShapelessRecipe(new ItemStack(Items.dye, 2, 10), new ItemStack(Items.dye, 1, 2), new ItemStack(Items.dye, 1, 15));
			addShapelessRecipe(new ItemStack(Items.dye, 2, 8), new ItemStack(Items.dye, 1, 0), new ItemStack(Items.dye, 1, 15));
			addShapelessRecipe(new ItemStack(Items.dye, 2, 7), new ItemStack(Items.dye, 1, 8), new ItemStack(Items.dye, 1, 15));
			addShapelessRecipe(new ItemStack(Items.dye, 3, 7), new ItemStack(Items.dye, 1, 0), new ItemStack(Items.dye, 1, 15), new ItemStack(Items.dye, 1, 15));
			addShapelessRecipe(new ItemStack(Items.dye, 2, 12), new ItemStack(Items.dye, 1, 4), new ItemStack(Items.dye, 1, 15));
			addShapelessRecipe(new ItemStack(Items.dye, 2, 6), new ItemStack(Items.dye, 1, 4), new ItemStack(Items.dye, 1, 2));
			addShapelessRecipe(new ItemStack(Items.dye, 2, 5), new ItemStack(Items.dye, 1, 4), new ItemStack(Items.dye, 1, 1));
			addShapelessRecipe(new ItemStack(Items.dye, 2, 13), new ItemStack(Items.dye, 1, 5), new ItemStack(Items.dye, 1, 9));
			addShapelessRecipe(new ItemStack(Items.dye, 3, 13), new ItemStack(Items.dye, 1, 4), new ItemStack(Items.dye, 1, 1), new ItemStack(Items.dye, 1, 9));
			addShapelessRecipe(new ItemStack(Items.dye, 4, 13), new ItemStack(Items.dye, 1, 4), new ItemStack(Items.dye, 1, 1), new ItemStack(Items.dye, 1, 1), new ItemStack(Items.dye, 1, 15));
			addShapelessRecipe(new ItemStack(Items.dye, 1, 12), new ItemStack(Blocks.red_flower, 1, 1));
			addShapelessRecipe(new ItemStack(Items.dye, 1, 13), new ItemStack(Blocks.red_flower, 1, 2));
			addShapelessRecipe(new ItemStack(Items.dye, 1, 7), new ItemStack(Blocks.red_flower, 1, 3));
			addShapelessRecipe(new ItemStack(Items.dye, 1, 1), new ItemStack(Blocks.red_flower, 1, 4));
			addShapelessRecipe(new ItemStack(Items.dye, 1, 14), new ItemStack(Blocks.red_flower, 1, 5));
			addShapelessRecipe(new ItemStack(Items.dye, 1, 7), new ItemStack(Blocks.red_flower, 1, 6));
			addShapelessRecipe(new ItemStack(Items.dye, 1, 9), new ItemStack(Blocks.red_flower, 1, 7));
			addShapelessRecipe(new ItemStack(Items.dye, 1, 7), new ItemStack(Blocks.red_flower, 1, 8));
			addShapelessRecipe(new ItemStack(Items.dye, 2, 11), new ItemStack(Blocks.double_plant, 1, 0));
			addShapelessRecipe(new ItemStack(Items.dye, 2, 13), new ItemStack(Blocks.double_plant, 1, 1));
			addShapelessRecipe(new ItemStack(Items.dye, 2, 1), new ItemStack(Blocks.double_plant, 1, 4));
			addShapelessRecipe(new ItemStack(Items.dye, 2, 9), new ItemStack(Blocks.double_plant, 1, 5));
			for (i = 0; i < 16; ++i)
			{
				addRecipe(new ItemStack(Blocks.carpet, 3, i), "##", '#', new ItemStack(Blocks.wool, 1, i));
			}
			recipes.add(new RecipesArmorDyes());
			recipes.add(new RecipeBookCloning());
			recipes.add(new RecipesMapCloning());
			recipes.add(new RecipesMapExtending());
			recipes.add(new RecipeFireworks());
			addRecipe(new ItemStack(Items.paper, 3), "###", '#', Items.reeds);
			addShapelessRecipe(new ItemStack(Items.book, 1), Items.paper, Items.paper, Items.paper, Items.leather);
			addShapelessRecipe(new ItemStack(Items.writable_book, 1), Items.book, new ItemStack(Items.dye, 1, 0), Items.feather);
			addRecipe(new ItemStack(Blocks.fence, 2), "###", "###", '#', Items.stick);
			addRecipe(new ItemStack(Blocks.cobblestone_wall, 6, 0), "###", "###", '#', Blocks.cobblestone);
			addRecipe(new ItemStack(Blocks.cobblestone_wall, 6, 1), "###", "###", '#', Blocks.mossy_cobblestone);
			addRecipe(new ItemStack(Blocks.nether_brick_fence, 6), "###", "###", '#', Blocks.nether_brick);
			addRecipe(new ItemStack(Blocks.fence_gate, 1), "#W#", "#W#", '#', Items.stick, 'W', Blocks.planks);
			addRecipe(new ItemStack(Blocks.jukebox, 1), "###", "#X#", "###", '#', Blocks.planks, 'X', Items.diamond);
			addRecipe(new ItemStack(Items.lead, 2), "~~ ", "~O ", "  ~", '~', Items.string, 'O', Items.slime_ball);
			addRecipe(new ItemStack(Blocks.noteblock, 1), "###", "#X#", "###", '#', Blocks.planks, 'X', Items.redstone);
			addRecipe(new ItemStack(Blocks.bookshelf, 1), "###", "XXX", "###", '#', Blocks.planks, 'X', Items.book);
			addRecipe(new ItemStack(Blocks.snow, 1), "##", "##", '#', Items.snowball);
			addRecipe(new ItemStack(Blocks.snow_layer, 6), "###", '#', Blocks.snow);
			addRecipe(new ItemStack(Blocks.clay, 1), "##", "##", '#', Items.clay_ball);
			addRecipe(new ItemStack(Blocks.brick_block, 1), "##", "##", '#', Items.brick);
			addRecipe(new ItemStack(Blocks.glowstone, 1), "##", "##", '#', Items.glowstone_dust);
			addRecipe(new ItemStack(Blocks.quartz_block, 1), "##", "##", '#', Items.quartz);
			addRecipe(new ItemStack(Blocks.wool, 1), "##", "##", '#', Items.string);
			addRecipe(new ItemStack(Blocks.tnt, 1), "X#X", "#X#", "X#X", 'X', Items.gunpowder, '#', Blocks.sand);
			addRecipe(new ItemStack(Blocks.stone_slab, 6, 3), "###", '#', Blocks.cobblestone);
			addRecipe(new ItemStack(Blocks.stone_slab, 6, 0), "###", '#', Blocks.stone);
			addRecipe(new ItemStack(Blocks.stone_slab, 6, 1), "###", '#', Blocks.sandstone);
			addRecipe(new ItemStack(Blocks.stone_slab, 6, 4), "###", '#', Blocks.brick_block);
			addRecipe(new ItemStack(Blocks.stone_slab, 6, 5), "###", '#', Blocks.stonebrick);
			addRecipe(new ItemStack(Blocks.stone_slab, 6, 6), "###", '#', Blocks.nether_brick);
			addRecipe(new ItemStack(Blocks.stone_slab, 6, 7), "###", '#', Blocks.quartz_block);
			addRecipe(new ItemStack(Blocks.wooden_slab, 6, 0), "###", '#', new ItemStack(Blocks.planks, 1, 0));
			addRecipe(new ItemStack(Blocks.wooden_slab, 6, 2), "###", '#', new ItemStack(Blocks.planks, 1, 2));
			addRecipe(new ItemStack(Blocks.wooden_slab, 6, 1), "###", '#', new ItemStack(Blocks.planks, 1, 1));
			addRecipe(new ItemStack(Blocks.wooden_slab, 6, 3), "###", '#', new ItemStack(Blocks.planks, 1, 3));
			addRecipe(new ItemStack(Blocks.wooden_slab, 6, 4), "###", '#', new ItemStack(Blocks.planks, 1, 4));
			addRecipe(new ItemStack(Blocks.wooden_slab, 6, 5), "###", '#', new ItemStack(Blocks.planks, 1, 5));
			addRecipe(new ItemStack(Blocks.ladder, 3), "# #", "###", "# #", '#', Items.stick);
			addRecipe(new ItemStack(Items.wooden_door, 1), "##", "##", "##", '#', Blocks.planks);
			addRecipe(new ItemStack(Blocks.trapdoor, 2), "###", "###", '#', Blocks.planks);
			addRecipe(new ItemStack(Items.iron_door, 1), "##", "##", "##", '#', Items.iron_ingot);
			addRecipe(new ItemStack(Items.sign, 3), "###", "###", " X ", '#', Blocks.planks, 'X', Items.stick);
			addRecipe(new ItemStack(Items.cake, 1), "AAA", "BEB", "CCC", 'A', Items.milk_bucket, 'B', Items.sugar, 'C', Items.wheat, 'E', Items.egg);
			addRecipe(new ItemStack(Items.sugar, 1), "#", '#', Items.reeds);
			addRecipe(new ItemStack(Blocks.planks, 4, 0), "#", '#', new ItemStack(Blocks.log, 1, 0));
			addRecipe(new ItemStack(Blocks.planks, 4, 1), "#", '#', new ItemStack(Blocks.log, 1, 1));
			addRecipe(new ItemStack(Blocks.planks, 4, 2), "#", '#', new ItemStack(Blocks.log, 1, 2));
			addRecipe(new ItemStack(Blocks.planks, 4, 3), "#", '#', new ItemStack(Blocks.log, 1, 3));
			addRecipe(new ItemStack(Blocks.planks, 4, 4), "#", '#', new ItemStack(Blocks.log2, 1, 0));
			addRecipe(new ItemStack(Blocks.planks, 4, 5), "#", '#', new ItemStack(Blocks.log2, 1, 1));
			addRecipe(new ItemStack(Items.stick, 4), "#", "#", '#', Blocks.planks);
			addRecipe(new ItemStack(Blocks.torch, 4), "X", "#", 'X', Items.coal, '#', Items.stick);
			addRecipe(new ItemStack(Blocks.torch, 4), "X", "#", 'X', new ItemStack(Items.coal, 1, 1), '#', Items.stick);
			addRecipe(new ItemStack(Items.bowl, 4), "# #", " # ", '#', Blocks.planks);
			addRecipe(new ItemStack(Items.glass_bottle, 3), "# #", " # ", '#', Blocks.glass);
			addRecipe(new ItemStack(Blocks.rail, 16), "X X", "X#X", "X X", 'X', Items.iron_ingot, '#', Items.stick);
			addRecipe(new ItemStack(Blocks.golden_rail, 6), "X X", "X#X", "XRX", 'X', Items.gold_ingot, 'R', Items.redstone, '#', Items.stick);
			addRecipe(new ItemStack(Blocks.activator_rail, 6), "XSX", "X#X", "XSX", 'X', Items.iron_ingot, '#', Blocks.redstone_torch, 'S', Items.stick);
			addRecipe(new ItemStack(Blocks.detector_rail, 6), "X X", "X#X", "XRX", 'X', Items.iron_ingot, 'R', Items.redstone, '#', Blocks.stone_pressure_plate);
			addRecipe(new ItemStack(Items.minecart, 1), "# #", "###", '#', Items.iron_ingot);
			addRecipe(new ItemStack(Items.cauldron, 1), "# #", "# #", "###", '#', Items.iron_ingot);
			addRecipe(new ItemStack(Items.brewing_stand, 1), " B ", "###", '#', Blocks.cobblestone, 'B', Items.blaze_rod);
			addRecipe(new ItemStack(Blocks.lit_pumpkin, 1), "A", "B", 'A', Blocks.pumpkin, 'B', Blocks.torch);
			addRecipe(new ItemStack(Items.chest_minecart, 1), "A", "B", 'A', Blocks.chest, 'B', Items.minecart);
			addRecipe(new ItemStack(Items.furnace_minecart, 1), "A", "B", 'A', Blocks.furnace, 'B', Items.minecart);
			addRecipe(new ItemStack(Items.tnt_minecart, 1), "A", "B", 'A', Blocks.tnt, 'B', Items.minecart);
			addRecipe(new ItemStack(Items.hopper_minecart, 1), "A", "B", 'A', Blocks.hopper, 'B', Items.minecart);
			addRecipe(new ItemStack(Items.boat, 1), "# #", "###", '#', Blocks.planks);
			addRecipe(new ItemStack(Items.bucket, 1), "# #", " # ", '#', Items.iron_ingot);
			addRecipe(new ItemStack(Items.flower_pot, 1), "# #", " # ", '#', Items.brick);
			addShapelessRecipe(new ItemStack(Items.flint_and_steel, 1), new ItemStack(Items.iron_ingot, 1), new ItemStack(Items.flint, 1));
			addRecipe(new ItemStack(Items.bread, 1), "###", '#', Items.wheat);
			addRecipe(new ItemStack(Blocks.oak_stairs, 4), "#  ", "## ", "###", '#', new ItemStack(Blocks.planks, 1, 0));
			addRecipe(new ItemStack(Blocks.birch_stairs, 4), "#  ", "## ", "###", '#', new ItemStack(Blocks.planks, 1, 2));
			addRecipe(new ItemStack(Blocks.spruce_stairs, 4), "#  ", "## ", "###", '#', new ItemStack(Blocks.planks, 1, 1));
			addRecipe(new ItemStack(Blocks.jungle_stairs, 4), "#  ", "## ", "###", '#', new ItemStack(Blocks.planks, 1, 3));
			addRecipe(new ItemStack(Blocks.acacia_stairs, 4), "#  ", "## ", "###", '#', new ItemStack(Blocks.planks, 1, 4));
			addRecipe(new ItemStack(Blocks.dark_oak_stairs, 4), "#  ", "## ", "###", '#', new ItemStack(Blocks.planks, 1, 5));
			addRecipe(new ItemStack(Items.fishing_rod, 1), "  #", " #X", "# X", '#', Items.stick, 'X', Items.string);
			addRecipe(new ItemStack(Blocks.stone_stairs, 4), "#  ", "## ", "###", '#', Blocks.cobblestone);
			addRecipe(new ItemStack(Blocks.brick_stairs, 4), "#  ", "## ", "###", '#', Blocks.brick_block);
			addRecipe(new ItemStack(Blocks.stone_brick_stairs, 4), "#  ", "## ", "###", '#', Blocks.stonebrick);
			addRecipe(new ItemStack(Blocks.nether_brick_stairs, 4), "#  ", "## ", "###", '#', Blocks.nether_brick);
			addRecipe(new ItemStack(Blocks.sandstone_stairs, 4), "#  ", "## ", "###", '#', Blocks.sandstone);
			addRecipe(new ItemStack(Blocks.quartz_stairs, 4), "#  ", "## ", "###", '#', Blocks.quartz_block);
			addRecipe(new ItemStack(Items.painting, 1), "###", "#X#", "###", '#', Items.stick, 'X', Blocks.wool);
			addRecipe(new ItemStack(Items.item_frame, 1), "###", "#X#", "###", '#', Items.stick, 'X', Items.leather);
			addRecipe(new ItemStack(Items.golden_apple, 1, 0), "###", "#X#", "###", '#', Items.gold_ingot, 'X', Items.apple);
			addRecipe(new ItemStack(Items.golden_apple, 1, 1), "###", "#X#", "###", '#', Blocks.gold_block, 'X', Items.apple);
			addRecipe(new ItemStack(Items.golden_carrot, 1, 0), "###", "#X#", "###", '#', Items.gold_nugget, 'X', Items.carrot);
			addRecipe(new ItemStack(Items.speckled_melon, 1), "###", "#X#", "###", '#', Items.gold_nugget, 'X', Items.melon);
			addRecipe(new ItemStack(Blocks.lever, 1), "X", "#", '#', Blocks.cobblestone, 'X', Items.stick);
			addRecipe(new ItemStack(Blocks.tripwire_hook, 2), "I", "S", "#", '#', Blocks.planks, 'S', Items.stick, 'I', Items.iron_ingot);
			addRecipe(new ItemStack(Blocks.redstone_torch, 1), "X", "#", '#', Items.stick, 'X', Items.redstone);
			addRecipe(new ItemStack(Items.repeater, 1), "#X#", "III", '#', Blocks.redstone_torch, 'X', Items.redstone, 'I', Blocks.stone);
			addRecipe(new ItemStack(Items.comparator, 1), " # ", "#X#", "III", '#', Blocks.redstone_torch, 'X', Items.quartz, 'I', Blocks.stone);
			addRecipe(new ItemStack(Items.clock, 1), " # ", "#X#", " # ", '#', Items.gold_ingot, 'X', Items.redstone);
			addRecipe(new ItemStack(Items.compass, 1), " # ", "#X#", " # ", '#', Items.iron_ingot, 'X', Items.redstone);
			addRecipe(new ItemStack(Items.map, 1), "###", "#X#", "###", '#', Items.paper, 'X', Items.compass);
			addRecipe(new ItemStack(Blocks.stone_button, 1), "#", '#', Blocks.stone);
			addRecipe(new ItemStack(Blocks.wooden_button, 1), "#", '#', Blocks.planks);
			addRecipe(new ItemStack(Blocks.stone_pressure_plate, 1), "##", '#', Blocks.stone);
			addRecipe(new ItemStack(Blocks.wooden_pressure_plate, 1), "##", '#', Blocks.planks);
			addRecipe(new ItemStack(Blocks.heavy_weighted_pressure_plate, 1), "##", '#', Items.iron_ingot);
			addRecipe(new ItemStack(Blocks.light_weighted_pressure_plate, 1), "##", '#', Items.gold_ingot);
			addRecipe(new ItemStack(Blocks.dispenser, 1), "###", "#X#", "#R#", '#', Blocks.cobblestone, 'X', Items.bow, 'R', Items.redstone);
			addRecipe(new ItemStack(Blocks.dropper, 1), "###", "# #", "#R#", '#', Blocks.cobblestone, 'R', Items.redstone);
			addRecipe(new ItemStack(Blocks.piston, 1), "TTT", "#X#", "#R#", '#', Blocks.cobblestone, 'X', Items.iron_ingot, 'R', Items.redstone, 'T', Blocks.planks);
			addRecipe(new ItemStack(Blocks.sticky_piston, 1), "S", "P", 'S', Items.slime_ball, 'P', Blocks.piston);
			addRecipe(new ItemStack(Items.bed, 1), "###", "XXX", '#', Blocks.wool, 'X', Blocks.planks);
			addRecipe(new ItemStack(Blocks.enchanting_table, 1), " B ", "D#D", "###", '#', Blocks.obsidian, 'B', Items.book, 'D', Items.diamond);
			addRecipe(new ItemStack(Blocks.anvil, 1), "III", " i ", "iii", 'I', Blocks.iron_block, 'i', Items.iron_ingot);
			addShapelessRecipe(new ItemStack(Items.ender_eye, 1), Items.ender_pearl, Items.blaze_powder);
			addShapelessRecipe(new ItemStack(Items.fire_charge, 3), Items.gunpowder, Items.blaze_powder, Items.coal);
			addShapelessRecipe(new ItemStack(Items.fire_charge, 3), Items.gunpowder, Items.blaze_powder, new ItemStack(Items.coal, 1, 1));
			addRecipe(new ItemStack(Blocks.daylight_detector), "GGG", "QQQ", "WWW", 'G', Blocks.glass, 'Q', Items.quartz, 'W', Blocks.wooden_slab);
			addRecipe(new ItemStack(Blocks.hopper), "I I", "ICI", " I ", 'I', Items.iron_ingot, 'C', Blocks.chest);
		}
		recipes.addAll(RecipeManager.PIES);
		Collections.sort(recipes, new Comparator<IRecipe>()
		{
			@Override
			public int compare(IRecipe p_compare_1_, IRecipe p_compare_2_)
			{
				return p_compare_1_ instanceof EBShapelessRecipes && p_compare_2_ instanceof EBShapedRecipes ? 1 : p_compare_2_ instanceof EBShapelessRecipes && p_compare_1_ instanceof EBShapedRecipes ? -1 : p_compare_2_.getRecipeSize() < p_compare_1_.getRecipeSize() ? -1 : p_compare_2_.getRecipeSize() > p_compare_1_.getRecipeSize() ? 1 : 0;
			}
		});
		System.err.println(recipes.size() + " recipes added to EB table");
	}

	public EBShapedRecipes addRecipe(ItemStack stack, Object... obs)
	{
		String s = "";
		int i = 0;
		int j = 0;
		int k = 0;
		if (obs[i] instanceof String[])
		{
			String[] astring = (String[]) obs[i++];
			for (String s1 : astring)
			{
				++k;
				j = s1.length();
				s = s + s1;
			}
		}
		else
		{
			while (obs[i] instanceof String)
			{
				String s2 = (String) obs[i++];
				++k;
				j = s2.length();
				s = s + s2;
			}
		}
		Map<Character, Object> hashmap;
		for (hashmap = JavaHelp.newHashMap(); i < obs.length; i += 2)
		{
			Character character = (Character) obs[i];
			ItemStack itemstack1 = null;
			if (obs[i + 1] instanceof Item)
			{
				itemstack1 = new ItemStack((Item) obs[i + 1]);
			}
			else if (obs[i + 1] instanceof Block)
			{
				itemstack1 = new ItemStack((Block) obs[i + 1], 1, 32767);
			}
			else if (obs[i + 1] instanceof ItemStack)
			{
				itemstack1 = (ItemStack) obs[i + 1];
			}
			hashmap.put(character, itemstack1);
		}
		ItemStack[] aitemstack = new ItemStack[j * k];
		for (int i1 = 0; i1 < j * k; ++i1)
		{
			char c0 = s.charAt(i1);
			if (hashmap.containsKey(Character.valueOf(c0)))
			{
				aitemstack[i1] = ((ItemStack) hashmap.get(Character.valueOf(c0))).copy();
			}
			else
			{
				aitemstack[i1] = null;
			}
		}
		EBShapedRecipes shapedrecipes = new EBShapedRecipes(j, k, aitemstack, stack);
		recipes.add(shapedrecipes);
		return shapedrecipes;
	}

	public void addShapelessRecipe(ItemStack stack, Object... obs)
	{
		List<ItemStack> arraylist = JavaHelp.newArrayList();
		Object[] aobject = obs;
		int i = obs.length;
		for (int j = 0; j < i; ++j)
		{
			Object object1 = aobject[j];
			if (object1 instanceof ItemStack)
			{
				arraylist.add(((ItemStack) object1).copy());
			}
			else if (object1 instanceof Item)
			{
				arraylist.add(new ItemStack((Item) object1));
			}
			else
			{
				if (!(object1 instanceof Block)) throw new RuntimeException("Invalid shapeless recipy!");
				arraylist.add(new ItemStack((Block) object1));
			}
		}
		recipes.add(new EBShapelessRecipes(stack, arraylist));
	}

	public ItemStack findMatchingRecipe(InventoryCrafting p_82787_1_, World p_82787_2_)
	{
		int i = 0;
		ItemStack itemstack = null;
		ItemStack itemstack1 = null;
		int j;
		for (j = 0; j < p_82787_1_.getSizeInventory(); ++j)
		{
			ItemStack itemstack2 = p_82787_1_.getStackInSlot(j);
			if (itemstack2 != null)
			{
				if (i == 0)
				{
					itemstack = itemstack2;
				}
				if (i == 1)
				{
					itemstack1 = itemstack2;
				}
				++i;
			}
		}
		if (i == 2 && itemstack.getItem() == itemstack1.getItem() && itemstack.stackSize == 1 && itemstack1.stackSize == 1 && itemstack.getItem().isRepairable())
		{
			Item item = itemstack.getItem();
			int j1 = item.getMaxDamage() - itemstack.getItemDamageForDisplay();
			int k = item.getMaxDamage() - itemstack1.getItemDamageForDisplay();
			int l = j1 + k + item.getMaxDamage() * 5 / 100;
			int i1 = item.getMaxDamage() - l;
			if (i1 < 0)
			{
				i1 = 0;
			}
			return new ItemStack(itemstack.getItem(), 1, i1);
		}
		else
		{
			for (j = 0; j < recipes.size(); ++j)
			{
				IRecipe irecipe = recipes.get(j);
				if (irecipe.matches(p_82787_1_, p_82787_2_)) return irecipe.getCraftingResult(p_82787_1_);
			}
			return null;
		}
	}

	public void addCompactRecipe(ItemStack item, ItemStack obj)
	{
		addRecipe(item, "###", "###", "###", '#', obj);
	}

	public void addReversedRecipe(ItemStack item, ItemStack obj)
	{
		item.stackSize = 9;
		addRecipe(item, "#", '#', obj);
	}

	public void addCompactAndReversedRecipe(ItemStack item, ItemStack obj)
	{
		addCompactRecipe(item, obj);
		addReversedRecipe(obj, item);
	}

	public List<IRecipe> getRecipeList()
	{
		return recipes;
	}
}