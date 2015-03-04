package main.extremeblocks;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import main.com.hk.eb.util.BlockCustom;
import main.com.hk.eb.util.IInfo;
import main.com.hk.eb.util.IInitialization;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.RegistryHelper;
import main.extremeblocks.blocks.abstracts.BlockFakeFloor;
import main.extremeblocks.blocks.abstracts.BlockLightedBlock;
import main.extremeblocks.entities.EntityGrenade;
import main.extremeblocks.entities.EntityMolotov;
import main.extremeblocks.entities.EntitySpear;
import main.extremeblocks.entities.mobs.EntityBloodwing;
import main.extremeblocks.entities.mobs.EntityCastleSkeleton;
import main.extremeblocks.entities.mobs.EntityCastleZombie;
import main.extremeblocks.entities.mobs.EntityDemon;
import main.extremeblocks.entities.mobs.EntityEvilIronGolem;
import main.extremeblocks.entities.mobs.EntityRobot;
import main.extremeblocks.network.PacketHandlerEB;
import main.extremeblocks.tileentities.TileEntityEnchantmentExtractor;
import main.extremeblocks.tileentities.TileEntityRevertingStation;
import main.extremeblocks.tileentities.TileEntityTrash;
import main.extremeblocks.tools.ColorToolSet;
import main.extremeblocks.util.SpawnDetail;
import main.extremeblocks.worldgen.GenCastle;
import main.extremeblocks.worldgen.GenCatacombs;
import main.extremeblocks.worldgen.GenDriedTree;
import main.extremeblocks.worldgen.GenHouse;
import main.extremeblocks.worldgen.GenManager;
import main.extremeblocks.worldgen.GenSunkenShip;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

@Mod(modid = Init.MODID, name = "Extreme Blocks", version = Init.VERSION, guiFactory = "main.extremeblocks.EBGuiFactory")
public class ExtremeBlocks
{
	@SidedProxy(clientSide = "main.extremeblocks.EBClient", serverSide = "main.extremeblocks.EBCommon")
	public static EBCommon proxy;
	@Instance(Init.MODID)
	public static ExtremeBlocks instance;
	public static Configuration configFile;
	public static List<Block> blocks = JavaHelp.newArrayList();
	public static List<Item> items = JavaHelp.newArrayList();
	public static final PacketHandlerEB packetPipeline = new PacketHandlerEB();
	public static final UUID ebUUID = new UUID(5881171344827434468L, 6415942623990768627L);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new EBEventHandler());
		MinecraftForge.ORE_GEN_BUS.register(new EBEventHandler());
		MinecraftForge.TERRAIN_GEN_BUS.register(new EBEventHandler());
		FMLCommonHandler.instance().bus().register(new EBEventHandler());
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		GenManager.registerGeneration(GenDriedTree.class);
		GenManager.registerGeneration(GenCastle.class);
		GenManager.registerGeneration(GenHouse.class);
		GenManager.registerGeneration(GenSunkenShip.class);
		GenManager.registerGeneration(GenCatacombs.class);
		configFile = new Configuration(event.getSuggestedConfigurationFile());
		configFile.load();
		Init.handleConfig();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		packetPipeline.initialise();
		new Init();
		new ColorToolSet(Init.trinquantium_ingot, Init.TRINQUANTIUM_T, 0xFF8400);
		new ColorToolSet(Init.bronze_ingot, Init.BRONZE_T, 0x6E3E0A);
		new ColorToolSet(Init.delvlish_crystal, Init.DELVLISH_T, 0xFF0000);
		new ColorToolSet(Init.silver_ingot, Init.SILVER_T, 0xBABABA);
		new ColorToolSet(Init.glester_rock, Init.GLESTER_T, 0xF3FF00);
		new ColorToolSet(Init.meteor, Init.METEORITE_T, 0x890000);
		new ColorToolSet(Init.sapphire, Init.SAPPHIRE_T, 0x0600C1);
		new ColorToolSet(Init.onyx, Init.ONYX_T, 0x722472);
		new ColorToolSet(Items.emerald, Init.DIAMOND_T, 0x12B500);
		new ColorToolSet(Init.fluorite, Init.FLUORITE_T, 0x1BABAB);
		new ColorToolSet(Init.spirit_fragment, Init.SPIRIT_T, 0x732A2A);
		new ColorToolSet(Blocks.bedrock, Init.SUPER_T, 0x3F3F3F);
		GameRegistry.registerWorldGenerator(new GenManager(), 1);
		Init.addReplacements();
		for (Block block : blocks)
		{
			if (!(block instanceof IInfo))
			{
				System.err.println(block.getClass().getSimpleName() + " DOESN'T IMPLEMENT INFO!");
			}
			if (block instanceof IInitialization)
			{
				//BlockCustom
				((IInitialization) block).init();
			}
			else if (block instanceof BlockCustom)
			{
				RegistryHelper.register(block);
				if (((BlockCustom) block).getTileClass() != null)
				{
					GameRegistry.registerTileEntity(((BlockCustom) block).getTileClass(), ((BlockCustom) block).getTileClass().getName());
				}
			}
		}
		for (Item item : items)
		{
			if (!(item instanceof IInfo))
			{
				System.err.println(item.getClass().getSimpleName() + " DOESN'T IMPLEMENT INFO!");
			}
			RegistryHelper.register(item);
		}
		Init.addRecipes();
		FMLInterModComms.sendMessage("Waila", "register", "main.extremeblocks.WailaIntegration.addWailaStuff");
		EBCommon.registerThrowable(EntityGrenade.class, "Grenade");
		EBCommon.registerThrowable(EntityMolotov.class, "Molotov");
		EBCommon.registerThrowable(EntitySpear.class, "Spear");
		EBCommon.registerEntity(EntityRobot.class, "Robot");
		EBCommon.registerEntity(EntityBloodwing.class, "Bloodwing");
		EBCommon.registerEntity(EntityDemon.class, "Demon Spirit", EnumCreatureType.monster);
		EBCommon.registerEntity(EntityCastleZombie.class, "Castle Zombie", EnumCreatureType.monster, SpawnDetail.getForAllBiomes(10, 2, 5));
		EBCommon.registerEntity(EntityCastleSkeleton.class, "Castle Skeleton", EnumCreatureType.monster, SpawnDetail.getForAllBiomes(10, 2, 5));
		EBCommon.registerEntity(EntityEvilIronGolem.class, "Evil Iron Golem", EnumCreatureType.monster, SpawnDetail.getForBiomes(10, 1, 1, BiomeGenBase.hell, BiomeGenBase.sky));
		// EBClient <-- command right click it

		//GameRegistry.registerTileEntity(TileEntityFuse.class, ebUUID + "Fuse");
		//GameRegistry.registerTileEntity(TileEntityStorage.class, ebUUID + "Storage");
		//GameRegistry.registerTileEntity(TileEntityDrill.class, ebUUID + "Drill");
		GameRegistry.registerTileEntity(TileEntityRevertingStation.class, ebUUID + "Reverting Station");
		GameRegistry.registerTileEntity(TileEntityTrash.class, ebUUID + "Trash Can");
		GameRegistry.registerTileEntity(TileEntityEnchantmentExtractor.class, ebUUID + "Enchantment Extractor");
		proxy.registerRenderThings();
		proxy.registerSounds();
		addVillagerTrade();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		packetPipeline.postInitialise();

		List<Block> allBlocks = JavaHelp.newArrayList();
		for (int i = 0; i < Item.itemRegistry.getKeys().size(); i++)
		{
			Block block = Block.getBlockById(i);
			if (block != null && block != Blocks.air)
			{
				allBlocks.add(block);
			}
		}
		for (int i = 0; i < allBlocks.size(); i++)
		{
			Block b = allBlocks.get(i);

			if (b.isNormalCube() && b.isOpaqueCube())
			{
				if (Vars.addLightedBlocks)
				{
					BlockLightedBlock.createPair(b);
				}
				if (Vars.addFakeFloors)
				{
					RegistryHelper.register(new BlockFakeFloor(b));
				}
			}
			if (b.getClass().getName().startsWith("main.extremeblocks"))
			{
				if (b.getLocalizedName().contains("tile.") || b.getLocalizedName().contains(".name"))
				{
					System.err.println("ADD LOCALIZATION FOR " + b.getLocalizedName() + "!");
				}
			}
		}
		for (Block block : blocks)
		{
			if (block instanceof IInitialization)
			{
				((IInitialization) block).postInit();
			}
		}
		new Guide();
	}

	private void addVillagerTrade()
	{
		VillagerRegistry.instance().registerVillageTradeHandler(0, new IVillageTradeHandler()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
			{
				int i = random.nextInt(15);
				ItemStack rand = null;
				switch (i)
				{
					case 0:
						rand = new ItemStack(Items.diamond, 2);
						break;
					case 1:
						rand = new ItemStack(Items.apple, 9);
						break;
					case 2:
						rand = new ItemStack(Items.blaze_rod, 6);
						break;
					case 3:
						rand = new ItemStack(Items.coal, 32);
						break;
					case 4:
						rand = new ItemStack(Items.beef, 3);
						break;
					case 5:
						rand = new ItemStack(Items.gold_ingot);
						break;
					case 6:
						rand = new ItemStack(Items.gold_nugget);
						break;
					case 7:
						rand = new ItemStack(Items.wooden_hoe);
						break;
					case 8:
						rand = new ItemStack(Items.quartz, 16);
						break;
					case 9:
						rand = new ItemStack(Items.bone, 7);
						break;
				}
				if (rand != null)
				{
					recipeList.add(new MerchantRecipe(new ItemStack(Init.gold_coin), rand));
				}
			}
		});
	}
}