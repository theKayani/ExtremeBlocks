package main.extremeblocks;

import java.util.ArrayList;
import main.com.hk.testing.util.BlockCustom;
import main.com.hk.testing.util.CustomTab;
import main.com.hk.testing.util.ItemCustom;
import main.com.hk.testing.util.ItemToolSet.ItemCSword;
import main.com.hk.testing.util.MPUtil;
import main.extremeblocks.blocks.BlockArmorStand;
import main.extremeblocks.blocks.BlockCabinet;
import main.extremeblocks.blocks.BlockCement;
import main.extremeblocks.blocks.BlockConsole;
import main.extremeblocks.blocks.BlockDriedSapling;
import main.extremeblocks.blocks.BlockDrill;
import main.extremeblocks.blocks.BlockFuseBlock;
import main.extremeblocks.blocks.BlockGameFloor;
import main.extremeblocks.blocks.BlockGameFloor.GameBlockType;
import main.extremeblocks.blocks.BlockHydrant;
import main.extremeblocks.blocks.BlockLantern;
import main.extremeblocks.blocks.BlockPipe;
import main.extremeblocks.blocks.BlockPowderKeg;
import main.extremeblocks.blocks.BlockPower;
import main.extremeblocks.blocks.BlockPower.PowerType;
import main.extremeblocks.blocks.BlockRewardBlock;
import main.extremeblocks.blocks.BlockTrash;
import main.extremeblocks.blocks.BlockVendingMachine;
import main.extremeblocks.blocks.BlockXrayBlock;
import main.extremeblocks.blocks.abstractblocks.BlockCompact;
import main.extremeblocks.blocks.abstractblocks.BlockOre;
import main.extremeblocks.blocks.abstractblocks.BlockSided;
import main.extremeblocks.blocks.abstractblocks.BlockStorage;
import main.extremeblocks.blocks.tileentities.TileEntityStorage;
import main.extremeblocks.blocks.tileentities.pipe.PipeType;
import main.extremeblocks.items.ItemBackpack;
import main.extremeblocks.items.ItemCellphone;
import main.extremeblocks.items.ItemCounter;
import main.extremeblocks.items.ItemExtractor;
import main.extremeblocks.items.ItemFuse;
import main.extremeblocks.items.ItemGrenade;
import main.extremeblocks.items.ItemNotes;
import main.extremeblocks.items.ItemReturner;
import main.extremeblocks.util.BlockType;
import main.extremeblocks.worldgen.WorldTypeIslands;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Init
{
	public static final String MODID = "extremeblocks";
	public static CreativeTabs tab_ebOres = new CustomTab("EB Ores");
	public static CreativeTabs tab_lightedBlocks = new CustomTab("Lighted Blocks");
	public static CreativeTabs tab_mainBlocks = new CustomTab("Main Blocks");
	public static CreativeTabs tab_misc = new CustomTab("Miscellaneous");
	public static CreativeTabs tab_fakeFloors = new CustomTab("Fake Floors");
	public static CreativeTabs tab_mainItems = new CustomTab("Main Items");
	public static CreativeTabs tab_tools = new CustomTab("Tools");
	public static CreativeTabs tab_foods = new CustomTab("Foods");

	public static WorldType custom = new WorldTypeIslands();

	public static ToolMaterial TRINQUANTIUM = EnumHelper.addToolMaterial("Trinquantium", 4, 2368, 10.0F, 4.0F, 15);
	public static ToolMaterial BRONZE = EnumHelper.addToolMaterial("Bronze", 2, 328, 5.0F, 3.0F, 12);
	public static ToolMaterial SILVER = EnumHelper.addToolMaterial("Silver", 0, 13, 6.0F, 8.0F, 7);
	public static ToolMaterial GLESTER = EnumHelper.addToolMaterial("Glester", 0, 71, 9.0F, 0.0F, 9);
	public static ToolMaterial DELVLISH = EnumHelper.addToolMaterial("Delvlish", 0, 71, 0.0F, 5.0F, 4);
	public static ToolMaterial METEORITE = EnumHelper.addToolMaterial("Meteorite", 3, 2306, 6.0F, 6.0F, 4);
	public static ToolMaterial SAPPHIRE = EnumHelper.addToolMaterial("Sapphire", 3, 1909, 7.0F, 7.0F, 14);
	public static ToolMaterial ONYX = EnumHelper.addToolMaterial("Onyx", 2, 514, 4.0F, 9.0F, 17);
	public static ToolMaterial FLUORITE = EnumHelper.addToolMaterial("Fluorite", 2, 392, 5.0F, 2.0F, 7);
	public static ToolMaterial DIAMOND = EnumHelper.addToolMaterial("Emerald", 4, 4096, 12.0F, 5.0F, 15);

	public static final Item extreme_blocks = new ItemCustom("EB", null).setTextureName(MODID + ":EB");
	public static final Item gold_coin = new ItemCustom("Gold Coin", tab_mainItems).setTextureName(MODID + ":gold_coin");
	public static final Item glester_rock = new ItemCustom("Glester Rock", tab_mainItems).setTextureName(MODID + ":glester_rock");
	public static final Item delvlish_crystal = new ItemCustom("Delvlish Crystal", tab_mainItems).setTextureName(MODID + ":delvlish_crystal");
	public static final Item silver_ingot = new ItemCustom("Silver Ingot", tab_mainItems).setTextureName(MODID + ":silver_ingot");
	public static final Item trinquantium_ingot = new ItemCustom("Trinquantium Ingot", tab_mainItems).setTextureName(MODID + ":trinquantium_ingot");
	public static final Item crushed_stone = new ItemCustom("Crushed Stone", tab_mainItems).setTextureName(MODID + ":crushed_stone");
	public static final Item bronze_medal = new ItemCustom("Bronze Medal", tab_mainItems).setTextureName(MODID + ":bronze_medal");
	public static final Item silver_medal = new ItemCustom("Silver Medal", tab_mainItems).setTextureName(MODID + ":silver_medal");
	public static final Item gold_medal = new ItemCustom("Gold Medal", tab_mainItems).setTextureName(MODID + ":gold_medal");
	public static final Item trinquantium_medal = new ItemCustom("Trinquantium Medal", tab_mainItems).setTextureName(MODID + ":trinquantium_medal");
	public static final Item copper = new ItemCustom("Copper", tab_mainItems).setTextureName(MODID + ":copper");
	public static final Item tin = new ItemCustom("Tin", tab_mainItems).setTextureName(MODID + ":tin");
	public static final Item copper_and_tin_lump = new ItemCustom("Copper and Tin Lump", tab_mainItems).setTextureName(MODID + ":copper_tin_lump");
	public static final Item bronze_ingot = new ItemCustom("Bronze Ingot", tab_mainItems).setTextureName(MODID + ":bronze_ingot");
	public static final Item sap = new ItemCustom("Sap", tab_mainItems).setTextureName(MODID + ":sap");
	public static final Item extractor = new ItemExtractor();
	public static final Item cellphone = new ItemCellphone();
	public static final Item plastic = new ItemCustom("Plastic", tab_mainItems).setTextureName(MODID + ":plastic");
	public static final Item chip = new ItemCustom("Chip", tab_mainItems).setTextureName(MODID + ":chip");
	public static final Item bone_shard = new ItemCustom("Bone Shard", tab_mainItems).setTextureName(MODID + ":bone_shard");
	public static final Item bone_sword = new ItemCSword(ToolMaterial.GOLD);
	public static final Item wrench = new ItemCustom("Wrench", tab_mainItems).setTextureName(MODID + ":wrench");
	public static final Item pipes = new ItemCustom("Pipes", tab_mainItems).setTextureName(MODID + ":pipes");
	public static final Item pipe = new ItemCustom("Pipe", tab_mainItems).setTextureName(MODID + ":pipe");
	public static final Item backpack = new ItemBackpack();
	public static final Item light = new ItemCustom("Light", tab_mainItems).setTextureName(MODID + ":light");
	public static final Item limestone = new ItemCustom("Limestone", tab_mainItems).setTextureName(MODID + ":limestone");
	public static final Item core_chip = new ItemCustom("Core Chip", tab_mainItems).setTextureName(MODID + ":core_chip");
	public static final Item game_remote = new ItemCustom("Game Remote", tab_mainItems).setTextureName(MODID + ":game_remote");
	public static final Item returner = new ItemReturner();
	public static final Item counter = new ItemCounter();
	public static final Item notes = new ItemNotes(true);
	public static final Item notes1 = new ItemNotes(false);
	public static final Item meteorite_shards = new ItemCustom("Meteorite Shards", tab_mainItems).setTextureName(MODID + ":meteorite_shards");
	public static final Item meteor = new ItemCustom("Meteor", tab_mainItems).setTextureName(MODID + ":meteorite");
	public static final Item fluorite = new ItemCustom("Fluorite", tab_mainItems).setTextureName(MODID + ":fluorite");
	public static final Item sapphire = new ItemCustom("Sapphire", tab_mainItems).setTextureName(MODID + ":sapphire");
	public static final Item onyx = new ItemCustom("Onyx", tab_mainItems).setTextureName(MODID + ":onyx");
	public static final Item fuse = new ItemFuse();
	public static final Item grenade = new ItemGrenade();
	//public static final Item mellow_weed = new ItemWeed("Mellow Weed", 1);
	//public static final Item normal_weed = new ItemWeed("Weed", 2);

	public static final Block glester_ore = new BlockOre("Glester Ore").setDrop(glester_rock);
	public static final Block silver_ore = new BlockOre("Silver Ore");
	public static final Block trinquantium_ore = new BlockOre("Trinquantium Ore");
	public static final Block copper_ore = new BlockOre("Copper Ore").setDrop(copper);
	public static final Block tin_ore = new BlockOre("Tin Ore").setDrop(tin);
	public static final Block delvlish_ore = new BlockOre("Delvlish Ore").setDrop(delvlish_crystal);
	public static final Block compact_stone = new BlockCustom(Material.rock, "Compact Stone").setHardness(3.0F).setCreativeTab(tab_mainBlocks).setBlockTextureName(MODID + ":Compact Stone");
	public static final Block silver_block = new BlockCompact("Silver Block");
	public static final Block bronze_block = new BlockCompact("Bronze Block");
	public static final Block trinquantium_block = new BlockCompact("Trinquantium Block");
	public static final Block xray_block = new BlockXrayBlock(true);
	public static final Block xray_block_un = new BlockXrayBlock(false);
	public static final Block cement_wall = new BlockCement(true);
	public static final Block plaster_wall = new BlockCustom(Material.rock, "Plaster Wall").setBlockTextureName(MODID + ":plasterwall").setCreativeTab(tab_mainBlocks);
	public static final Block weak_cement_wall = new BlockCement(false);
	public static final Block emptied_log = new BlockSided(Material.wood, "Emptied Log", "emptiedlog");
	public static final Block bone_dirt = new BlockCustom(Material.ground, "Bone Dirt").setDrop(bone_shard).setBlockTextureName(MODID + ":bone_dirt").setCreativeTab(tab_mainBlocks);
	public static final Block waste = new BlockCustom(Material.lava, "Waste").irregular().setBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.2F, 1.0F).setBlockTextureName(MODID + ":waste").setCreativeTab(tab_misc);
	public static final Block fire_hydrant = new BlockHydrant();
	public static final Block lantern = new BlockLantern();
	public static final Block stone_pillar = new BlockSided(Material.rock, "Stone Pillar", "stonepillar");
	public static final Block trash = new BlockTrash();
	public static final Block vending_machine = new BlockVendingMachine();
	public static final Block dried_sapling = new BlockDriedSapling();
	public static final Block limestone_block = new BlockCustom(Material.rock, "Block of Limestone").setCreativeTab(tab_mainBlocks).setBlockTextureName(MODID + ":limestoneblock");
	public static final Block marble = new BlockCustom(Material.glass, "Marble").setCreativeTab(tab_mainBlocks).setBlockTextureName(MODID + ":marbleblock");
	public static final Block limestone_ore = new BlockOre("Limestone Ore").setDrop(limestone);
	public static final Block fake_sand = new BlockCustom(Material.ground, "Fake Sand").setCreativeTab(tab_misc).setBlockTextureName("sand");
	public static final Block fake_gravel = new BlockCustom(Material.ground, "Fake Gravel").setCreativeTab(tab_misc).setBlockTextureName("gravel");
	public static final Block drill = new BlockDrill(true);
	public static final Block drill_pole = new BlockDrill(false);
	public static final Block red_game_floor = new BlockGameFloor(GameBlockType.Red);
	public static final Block yellow_game_floor = new BlockGameFloor(GameBlockType.Yellow);
	public static final Block green_game_floor = new BlockGameFloor(GameBlockType.Green);
	public static final Block blue_game_floor = new BlockGameFloor(GameBlockType.Blue);
	public static final Block spread_game_block = new BlockGameFloor(GameBlockType.Spread);
	public static final Block console = new BlockConsole();
	public static final Block reward_block = new BlockRewardBlock();
	public static final Block onyx_ore = new BlockOre("Onyx Ore").setDrop(onyx);
	public static final Block meteorite = new BlockOre("Meteorite").setDrop(meteorite_shards);
	public static final Block fluorite_ore = new BlockOre("Fluorite Ore").setDrop(fluorite);
	public static final Block sapphire_ore = new BlockOre("Sapphire Ore").setDrop(sapphire);
	public static final Block fuse_block = new BlockFuseBlock();
	public static final Block powder_keg = new BlockPowderKeg();

	public static final Block transportPipe = new BlockPipe(PipeType.TRANSPORT);
	public static final Block powerPipe = new BlockPipe(PipeType.POWER);
	public static final Block emitter = new BlockPower(PowerType.EMITTER);
	public static final Block receiver = new BlockPower(PowerType.RECEIVER);

	public static BlockStorage crate = new BlockStorage("Crate", Material.wood, BlockType.NORMAL)
	{
		@Override
		public Slot[] addSlotsToContainer(TileEntityStorage te)
		{
			ArrayList<Slot> slots = new ArrayList<Slot>();

			for (int i = 0; i < 3; ++i)
			{
				for (int j = 0; j < 3; ++j)
				{
					slots.add(new Slot(te, j + i * 3, 62 + j * 18, 17 + i * 18));
				}
			}

			return slots.toArray(new Slot[0]);
		}
	}.setGuiTexturePath("textures/gui/container/dispenser.png").setStorageSlots(9);

	public static BlockStorage barrel = new BlockStorage("Barrel", Material.wood, BlockType.BARREL)
	{
		@Override
		public boolean renderAsNormalBlock()
		{
			return false;
		}

		@Override
		public boolean isOpaqueCube()
		{
			return false;
		}

		@Override
		public Slot[] addSlotsToContainer(TileEntityStorage te)
		{
			ArrayList<Slot> slots = new ArrayList<Slot>();

			for (int i = 0; i < 4; ++i)
			{
				for (int j = 0; j < 2; ++j)
				{
					slots.add(new Slot(te, j + i * 2, 71 + j * 18, 8 + i * 18));
				}
			}

			return slots.toArray(new Slot[0]);
		}
	}.setGuiTexturePath(MODID + ":textures/gui/barrel.png").setStorageSlots(8).setBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.8F, 0.8F);

	public static BlockStorage cabinet = new BlockCabinet();

	public static BlockStorage strongbox = (BlockStorage) new BlockStorage("Strongbox", Material.iron, BlockType.BARREL)
	{
		@Override
		public boolean renderAsNormalBlock()
		{
			return false;
		}

		@Override
		public boolean isOpaqueCube()
		{
			return false;
		}

		@Override
		public Slot[] addSlotsToContainer(TileEntityStorage te)
		{
			ArrayList<Slot> slots = new ArrayList<Slot>();

			for (int i = 0; i < 3; ++i)
			{
				slots.add(new Slot(te, i, 62 + i * 18, 36));
			}

			return slots.toArray(new Slot[0]);
		}
	}.setGuiTexturePath(MODID + ":textures/gui/strongbox.png").setStorageSlots(3).setBounds(0.2F, 0.0F, 0.4F, 0.8F, 0.3F, 0.6F).setHardness(7.0F).setBlockTextureName("iron_block");

	public static BlockStorage armorStand = new BlockArmorStand();

	public static BlockStorage bigCrate = new BlockStorage("Big Crate", Material.wood, BlockType.NORMAL)
	{
		@Override
		public Slot[] addSlotsToContainer(TileEntityStorage te)
		{
			ArrayList<Slot> slots = new ArrayList<Slot>();

			for (int j = 0; j < 3; ++j)
			{
				for (int k = 0; k < 9; ++k)
				{
					slots.add(new Slot(te, k + j * 9, 8 + k * 18, 18 + j * 18));
				}
			}

			return slots.toArray(new Slot[0]);
		}
	}.setGuiTexturePath(MODID + ":textures/gui/chest.png").setStorageSlots(27);

	public static BlockStorage smallCrate = new BlockStorage("Small Crate", Material.wood, BlockType.NORMAL)
	{
		@Override
		public Slot[] addSlotsToContainer(TileEntityStorage te)
		{
			ArrayList<Slot> slots = new ArrayList<Slot>();

			for (int j = 0; j < 2; ++j)
			{
				for (int k = 0; k < 2; ++k)
				{
					slots.add(new Slot(te, k + j * 2, 71 + k * 18, 29 + j * 18));
				}
			}

			return slots.toArray(new Slot[0]);
		}
	}.setGuiTexturePath(MODID + ":textures/gui/smallcrate.png").setStorageSlots(4);

	public static void addRecipes()
	{
		MPUtil.addRecipe(new ItemStack(counter), "X#", 'X', core_chip, '#', Blocks.wooden_button);
		MPUtil.addRecipe(new ItemStack(counter), "X#", 'X', core_chip, '#', Blocks.stone_button);
		MPUtil.addRecipe(new ItemStack(console), "XXX", "X#X", "XXB", 'X', plastic, '#', core_chip, 'B', light);
		MPUtil.addRecipe(new ItemStack(drill), "XXX", "#B#", " # ", 'X', Items.iron_ingot, '#', Items.diamond, 'B', Items.redstone);
		MPUtil.addRecipe(new ItemStack(fake_gravel), "XX", 'X', Blocks.gravel);
		MPUtil.addRecipe(new ItemStack(fake_sand), "XX", 'X', Blocks.sand);
		MPUtil.addRecipe(new ItemStack(fire_hydrant), " # ", "#X#", "XBX", '#', Items.iron_ingot, 'X', pipes, 'B', Items.flint);
		MPUtil.addRecipe(new ItemStack(vending_machine), "##B", "#X#", "###", '#', Items.iron_ingot, 'X', core_chip, 'B', light);
		MPUtil.addRecipe(new ItemStack(trash), "##", "##", '#', Items.iron_ingot);
		MPUtil.addRecipe(new ItemStack(limestone_ore), "##", "##", '#', limestone);
		MPUtil.addRecipe(new ItemStack(game_remote), "###", "#X#", "# #", '#', plastic, 'X', chip);
		MPUtil.addRecipe(new ItemStack(light), "###", "#X#", "###", '#', Items.redstone, 'X', chip);
		MPUtil.addRecipe(new ItemStack(core_chip), "###", '#', chip);
		MPUtil.addRecipe(new ItemStack(stone_pillar), "##", "##", "##", '#', Blocks.stone);
		MPUtil.addRecipe(new ItemStack(lantern), "#", "#", '#', Blocks.glass);
		MPUtil.addRecipe(new ItemStack(Blocks.planks, 2), "#", '#', emptied_log);
		MPUtil.addRecipe(new ItemStack(backpack), "###", "#X#", "###", '#', Items.leather, 'X', Items.ender_eye);
		MPUtil.addRecipe(new ItemStack(pipes), "##", "##", '#', pipe);
		MPUtil.addRecipe(new ItemStack(wrench), "# #", "###", " # ", '#', Items.iron_ingot);
		MPUtil.addRecipe(new ItemStack(pipe), "###", '#', Items.iron_ingot);
		MPUtil.addRecipe(new ItemStack(bone_sword), "#", "#", '#', bone_shard);
		MPUtil.addRecipe(new ItemStack(Items.bone), "##", "##", '#', bone_shard);
		MPUtil.addRecipe(new ItemStack(plastic, 2), "##", "##", '#', sap);
		MPUtil.addRecipe(new ItemStack(chip), "##", "##", '#', Items.redstone);
		MPUtil.addRecipe(new ItemStack(cellphone), "##X", "#B#", "###", '#', plastic, 'X', Items.redstone, 'B', chip);
		MPUtil.addRecipe(new ItemStack(extractor), "#X#", '#', Blocks.stone, 'X', Blocks.lever);
		MPUtil.addRecipe(new ItemStack(crushed_stone, 5), "#", "#", '#', Blocks.stone);
		MPUtil.addRecipe(new ItemStack(xray_block_un), "XBX", "B#B", "XBX", '#', Blocks.torch, 'B', Items.diamond, 'X', trinquantium_ingot);
		MPUtil.addRecipe(new ItemStack(xray_block), "XBX", "B#B", "XBX", '#', xray_block_un, 'B', delvlish_crystal, 'X', glester_rock);
		MPUtil.addRecipe(new ItemStack(gold_coin, 3), " # ", "###", " # ", '#', Items.gold_nugget);
		MPUtil.addRecipe(new ItemStack(bronze_medal, 3), " # ", "###", "###", '#', bronze_ingot);
		MPUtil.addRecipe(new ItemStack(silver_medal, 3), " # ", "###", "###", '#', silver_ingot);
		MPUtil.addRecipe(new ItemStack(gold_medal, 3), " # ", "###", "###", '#', Items.gold_ingot);
		MPUtil.addRecipe(new ItemStack(copper_and_tin_lump, 3), "#X#", "X#X", '#', copper, 'X', tin);
		MPUtil.addRecipe(new ItemStack(copper_and_tin_lump, 3), "#X#", "X#X", 'X', copper, '#', tin);
		MPUtil.addRecipe(new ItemStack(trinquantium_medal, 3), " # ", "###", "###", '#', trinquantium_ingot);
		MPUtil.addRecipe(new ItemStack(weak_cement_wall), "###", "#X#", "###", '#', crushed_stone, 'X', Items.water_bucket);
		MPUtil.addRecipe(new ItemStack(fuse), "##", '#', Items.gunpowder);
		MPUtil.addRecipe(new ItemStack(powder_keg), "#X#", "#X#", '#', Items.gunpowder, 'X', fuse);
		MPUtil.addRecipe(new ItemStack(meteor), "##", "##", '#', meteorite_shards);

		MPUtil.addRecipe(new ItemStack(strongbox), "II", 'I', Items.iron_ingot);
		MPUtil.addRecipe(new ItemStack(barrel), "P", "I", "P", 'I', Items.iron_ingot, 'P', Blocks.planks);
		MPUtil.addRecipe(new ItemStack(cabinet), "PGP", 'P', Blocks.planks, 'G', Blocks.glass_pane);
		MPUtil.addRecipe(new ItemStack(armorStand), "W", "W", "W", 'W', Blocks.log);
		MPUtil.addRecipe(new ItemStack(smallCrate), "SS", "SS", 'S', Items.stick);
		MPUtil.addRecipe(new ItemStack(crate), "PP", "PP", 'P', Blocks.planks);
		MPUtil.addRecipe(new ItemStack(bigCrate), "WW", "WW", 'W', Blocks.log);
		MPUtil.addRecipe(new ItemStack(bigCrate), "WW", "WW", 'W', Blocks.log2);

		MPUtil.addCompactAndReversedRecipe(new ItemStack(limestone_block), new ItemStack(limestone));
		MPUtil.addCompactAndReversedRecipe(new ItemStack(silver_block), new ItemStack(silver_ingot));
		MPUtil.addCompactAndReversedRecipe(new ItemStack(bronze_block), new ItemStack(bronze_ingot));
		MPUtil.addCompactAndReversedRecipe(new ItemStack(trinquantium_block), new ItemStack(trinquantium_ingot));
		MPUtil.addCompactRecipe(new ItemStack(plaster_wall), new ItemStack(plastic));
		MPUtil.addCompactAndReversedRecipe(new ItemStack(compact_stone), new ItemStack(Blocks.stone));
		MPUtil.addCompactRecipe(new ItemStack(waste), new ItemStack(Items.slime_ball));

		MPUtil.addSmeltingRecipe(new ItemStack(trinquantium_ore), new ItemStack(trinquantium_ingot), 4.0F);
		MPUtil.addSmeltingRecipe(new ItemStack(silver_ore), new ItemStack(silver_ingot), 2.0F);
		MPUtil.addSmeltingRecipe(new ItemStack(copper_and_tin_lump), new ItemStack(bronze_ingot), 3.0F);
		MPUtil.addSmeltingRecipe(new ItemStack(weak_cement_wall), new ItemStack(cement_wall), 3.0F);
		MPUtil.addSmeltingRecipe(new ItemStack(limestone_block), new ItemStack(marble), 3.0F);
	}

	public static void handleConfig(FMLPreInitializationEvent event)
	{
		String spawn_rates = "Ore Spawn Rates";
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();

		Vars.alterWorld = config.get(config.CATEGORY_GENERAL, "Alter World", false).getBoolean(false);

		Vars.copperSR = config.get(spawn_rates, "Copper SR", 20).getInt(20);
		Vars.tinSR = config.get(spawn_rates, "Tin SR", 20).getInt(20);
		Vars.silverSR = config.get(spawn_rates, "Silver SR", 2).getInt(2);
		Vars.trinquantiumSR = config.get(spawn_rates, "Trinquantium SR", 1).getInt(1);
		Vars.glesterSR = config.get(spawn_rates, "Glester SR", 10).getInt(10);
		Vars.delvlishSR = config.get(spawn_rates, "Delvlish SR", 10).getInt(10);
		Vars.meteoriteSR = config.get(spawn_rates, "Meteorite SR", 5).getInt(5);
		Vars.fluoriteSR = config.get(spawn_rates, "Fluorite SR", 15).getInt(15);
		Vars.compactStoneSR = config.get(spawn_rates, "Compact Stone SR", 10).getInt(10);
		Vars.onyxSR = config.get(spawn_rates, "Onyx SR", 10).getInt(10);

		config.save();
	}
}
