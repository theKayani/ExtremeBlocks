package extremeblocks;

import java.io.File;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import com.hk.testing.util.Config;
import com.hk.testing.util.ItemToolSet;
import com.hk.testing.util.RegistryHelper;
import com.hk.testing.util.StringLocator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import extremeblocks.blocks.abstractblocks.BlockFakeFloor;
import extremeblocks.blocks.abstractblocks.BlockLightedBlock;
import extremeblocks.blocks.tileentities.TileEntityConsole;
import extremeblocks.blocks.tileentities.TileEntityFuse;
import extremeblocks.blocks.tileentities.TileEntityRewardBlock;

@Mod(modid = Init.MODID, name = "Extreme Blocks", version = "5.0")
public class ExtremeBlocks
{
	@Instance(Init.MODID)
	public static ExtremeBlocks instance;
	public static Config config;
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	public static ArrayList<Item> items = new ArrayList<Item>();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		config = new Config(event);

		config.add("Spawn Amount", 3440, "How many Time to Spawn it!");
		config.add("Can Spawn", false, "Can it Spawn or Not?");
		config.add("Name", "Boom", "What to Name it");
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		StringLocator locator = new StringLocator(new File("/Users/owner/Desktop/MCP/1.1060/src/main/resources/assets/extremeblocks/lang/en_US.lang"));
		new Init();
		
		GameRegistry.registerWorldGenerator(new WorldManager(), 5);

		Init.TRINQUANTIUM.customCraftingMaterial = Init.trinquantium_ingot;
		Init.BRONZE.customCraftingMaterial = Init.bronze_ingot;
		Init.DELVLISH.customCraftingMaterial = Init.delvlish_crystal;
		Init.SILVER.customCraftingMaterial = Init.silver_ingot;
		Init.GLESTER.customCraftingMaterial = Init.glester_rock;
		Init.METEORITE.customCraftingMaterial = Init.meteor;
		Init.SAPPHIRE.customCraftingMaterial = Init.sapphire;
		Init.ONYX.customCraftingMaterial = Init.onyx;
		Init.DIAMOND.customCraftingMaterial = Items.emerald;
		Init.FLUORITE.customCraftingMaterial = Init.fluorite;

		new ItemToolSet(Init.TRINQUANTIUM, Init.trinquantium_ingot, "Trinquantium", Init.MODID, Init.tab_tools).registerTools();
		new ItemToolSet(Init.BRONZE, Init.bronze_ingot, "Bronze", Init.MODID, Init.tab_tools).registerTools();
		new ItemToolSet(Init.DELVLISH, Init.delvlish_crystal, "Delvlish", Init.MODID, Init.tab_tools).registerTools();
		new ItemToolSet(Init.SILVER, Init.silver_ingot, "Silver", Init.MODID, Init.tab_tools).registerTools();
		new ItemToolSet(Init.GLESTER, Init.glester_rock, "Glester", Init.MODID, Init.tab_tools).registerTools();
		new ItemToolSet(Init.METEORITE, Init.meteor, "Meteorite", Init.MODID, Init.tab_tools).registerTools();
		new ItemToolSet(Init.SAPPHIRE, Init.sapphire, "Sapphire", Init.MODID, Init.tab_tools).registerTools();
		new ItemToolSet(Init.ONYX, Init.onyx, "Onyx", Init.MODID, Init.tab_tools).registerTools();
		new ItemToolSet(Init.DIAMOND, Items.emerald, "Emerald", Init.MODID, Init.tab_tools).registerTools();
		new ItemToolSet(Init.FLUORITE, Init.fluorite, "Fluorite", Init.MODID, Init.tab_tools).registerTools();

		for (int i = 0; i < blocks.size(); i++)
		{
			locator.addLocalization(blocks.get(i), blocks.get(i).getLocalizedName().replaceAll("tile.", "").replaceAll(".name", ""));
			RegistryHelper.register(blocks.get(i));
		}
		for (int i = 0; i < items.size(); i++)
		{
			RegistryHelper.register(items.get(i));
		}

		GameRegistry.registerTileEntity(TileEntityConsole.class, "Console");
		GameRegistry.registerTileEntity(TileEntityRewardBlock.class, "RewardBlock");
		GameRegistry.registerTileEntity(TileEntityFuse.class, "Fuse");

		Init.addRecipes();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		ArrayList<Block> allBlocks = new ArrayList<Block>();

		for (int i = 0; i < RegistryHelper.blocksList.length; i++)
		{
			Block block = Block.getBlockById(i);

			if (block != null && block != Blocks.air)
			{
				RegistryHelper.blocksList[i] = block;
				allBlocks.add(block);
			}
		}
		for (int i = 0; i < RegistryHelper.itemsList.length; i++)
		{
			Item item = Item.getItemById(i);

			if (item != null)
			{
				RegistryHelper.itemsList[i] = item;
			}
		}

		for (int i = 0; i < allBlocks.size(); i++)
		{
			if (allBlocks.get(i).isNormalCube() && allBlocks.get(i).isOpaqueCube())
			{
				BlockLightedBlock.createPair(allBlocks.get(i));
				RegistryHelper.register(new BlockFakeFloor(allBlocks.get(i)));
			}
		}
		
		int harvestLevel = 0;
		int maxUses = 0;
		int efficiency = 0;
		int damage = 0;
		int enchantability = 0;
		
		for(int i = 0; i < ToolMaterial.values().length; i++)
		{
			harvestLevel += ToolMaterial.values()[i].getHarvestLevel();
			maxUses += ToolMaterial.values()[i].getMaxUses();
			efficiency += ToolMaterial.values()[i].getEfficiencyOnProperMaterial();
			damage += ToolMaterial.values()[i].getDamageVsEntity();
			enchantability += ToolMaterial.values()[i].getEnchantability();
		}

		//ToolMaterial hardcore = EnumHelper.addToolMaterial("Hardcore", harvestLevel, maxUses, efficiency, damage, enchantability);
		
		//new ItemToolSet(hardcore, Items.boat, "Hardcore", Init.MODID, Init.tab_tools).registerTools();
	}
}