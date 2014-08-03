package main.extremeblocks;

import java.util.ArrayList;
import main.com.hk.testing.util.ItemToolSet;
import main.com.hk.testing.util.RegistryHelper;
import main.extremeblocks.blocks.abstractblocks.BlockFakeFloor;
import main.extremeblocks.blocks.abstractblocks.BlockLightedBlock;
import main.extremeblocks.entities.EntityGrenade;
import main.extremeblocks.network.PacketHandlerEB;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Init.MODID, name = "Extreme Blocks", version = "6.0", guiFactory = "main.extremeblocks.EBGuiFactory")
public class ExtremeBlocks
{
	@SidedProxy(clientSide = "main.extremeblocks.EBClient", serverSide = "main.extremeblocks.EBCommon")
	public static EBCommon proxy;
	@Instance(Init.MODID)
	public static ExtremeBlocks instance;
	public static Configuration configFile;
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	public static ArrayList<Item> items = new ArrayList<Item>();
	public static final PacketHandlerEB packetPipeline = new PacketHandlerEB();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new EBEventHandler());
		MinecraftForge.ORE_GEN_BUS.register(new EBEventHandler());
		MinecraftForge.TERRAIN_GEN_BUS.register(new EBEventHandler());
		FMLCommonHandler.instance().bus().register(new EBEventHandler());
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		configFile = new Configuration(event.getSuggestedConfigurationFile());
		configFile.load();
		Init.handleConfig();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		packetPipeline.initialise();
		new Init();

		GameRegistry.registerWorldGenerator(new WorldManager(), 1);

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
			RegistryHelper.register(blocks.get(i));
		}
		for (int i = 0; i < items.size(); i++)
		{
			RegistryHelper.register(items.get(i));
		}

		Init.addRecipes();

		EntityRegistry.registerModEntity(EntityGrenade.class, "Grenade", 1, this, 80, 3, true);

		proxy.registerRenderThings();
		proxy.registerSounds();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		packetPipeline.postInitialise();
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

		for (int i = 0; i < ToolMaterial.values().length; i++)
		{
			ToolMaterial mat = ToolMaterial.values()[i];

			harvestLevel += mat.getHarvestLevel();
			maxUses += mat.getMaxUses();
			efficiency += mat.getEfficiencyOnProperMaterial();
			damage += mat.getDamageVsEntity();
			enchantability += mat.getEnchantability();
		}

		// ToolMaterial hardcore = EnumHelper.addToolMaterial("Hardcore",
		// harvestLevel, maxUses, efficiency, damage, enchantability);

		// new ItemToolSet(hardcore, Items.boat, "Hardcore", Init.MODID,
		// Init.tab_tools).registerTools();
	}
}