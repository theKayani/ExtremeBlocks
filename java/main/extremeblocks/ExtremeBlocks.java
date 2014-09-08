package main.extremeblocks;

import java.util.ArrayList;
import main.com.hk.testing.util.JavaHelp;
import main.com.hk.testing.util.RegistryHelper;
import main.com.hk.testing.util.ToolSet;
import main.extremeblocks.blocks.abstractblocks.BlockFakeFloor;
import main.extremeblocks.blocks.abstractblocks.BlockLightedBlock;
import main.extremeblocks.entities.EntityGrenade;
import main.extremeblocks.entities.EntityMolotov;
import main.extremeblocks.entities.mobs.EntityCastleSkeleton;
import main.extremeblocks.entities.mobs.EntityCastleZombie;
import main.extremeblocks.entities.mobs.EntityEvilIronGolem;
import main.extremeblocks.entities.mobs.EntityRobot;
import main.extremeblocks.network.PacketHandlerEB;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
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
	public static ArrayList<Block> blocks = JavaHelp.newArrayList();
	public static ArrayList<Item> items = JavaHelp.newArrayList();
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
		new ToolSet(Init.TRINQUANTIUM, Init.trinquantium_ingot, "Trinquantium");
		new ToolSet(Init.BRONZE, Init.bronze_ingot, "Bronze");
		new ToolSet(Init.DELVLISH, Init.delvlish_crystal, "Delvlish");
		new ToolSet(Init.SILVER, Init.silver_ingot, "Silver");
		new ToolSet(Init.GLESTER, Init.glester_rock, "Glester");
		new ToolSet(Init.METEORITE, Init.meteor, "Meteorite");
		new ToolSet(Init.SAPPHIRE, Init.sapphire, "Sapphire");
		new ToolSet(Init.ONYX, Init.onyx, "Onyx");
		new ToolSet(Init.DIAMOND, Items.emerald, "Emerald");
		new ToolSet(Init.FLUORITE, Init.fluorite, "Fluorite");
		for (Block block : blocks)
		{
			RegistryHelper.register(block);
		}
		for (Item item : items)
		{
			RegistryHelper.register(item);
		}
		Init.addRecipes();
		EntityRegistry.registerModEntity(EntityGrenade.class, "Grenade", 1, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityMolotov.class, "Molotov", 2, this, 80, 3, true);
		EBClient.registerEntity(EntityCastleZombie.class, "Castle Zombie", EnumCreatureType.creature, BiomeGenBase.plains);
		EBClient.registerEntity(EntityCastleSkeleton.class, "Castle Skeleton", EnumCreatureType.creature, BiomeGenBase.plains);
		EBClient.registerEntity(EntityEvilIronGolem.class, "Evil Iron Golem", EnumCreatureType.monster);
		EBClient.registerEntity(EntityRobot.class, "Robot", EnumCreatureType.creature);
		// EBClient <-- command right click it
		proxy.registerRenderThings();
		proxy.registerSounds();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		packetPipeline.postInitialise();
		ArrayList<Block> allBlocks = JavaHelp.newArrayList();
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
				if (Vars.addLightedBlocks) BlockLightedBlock.createPair(allBlocks.get(i));
				if (Vars.addFakeFloors) RegistryHelper.register(new BlockFakeFloor(allBlocks.get(i)));
			}
		}
	}
}