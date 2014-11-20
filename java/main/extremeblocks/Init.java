package main.extremeblocks;

import main.com.hk.eb.util.BlockCustom;
import main.com.hk.eb.util.CustomTab;
import main.com.hk.eb.util.ItemCustom;
import main.com.hk.eb.util.MPUtil;
import main.com.hk.eb.util.RegistryHelper;
import main.extremeblocks.blocks.BlockAltar;
import main.extremeblocks.blocks.BlockCement;
import main.extremeblocks.blocks.BlockCharger;
import main.extremeblocks.blocks.BlockCooker;
import main.extremeblocks.blocks.BlockCrop;
import main.extremeblocks.blocks.BlockDriedSapling;
import main.extremeblocks.blocks.BlockDrill;
import main.extremeblocks.blocks.BlockEBTable;
import main.extremeblocks.blocks.BlockFuseBlock;
import main.extremeblocks.blocks.BlockGenerator;
import main.extremeblocks.blocks.BlockHemp;
import main.extremeblocks.blocks.BlockHydrant;
import main.extremeblocks.blocks.BlockLantern;
import main.extremeblocks.blocks.BlockPlate;
import main.extremeblocks.blocks.BlockPowderKeg;
import main.extremeblocks.blocks.BlockProtector;
import main.extremeblocks.blocks.BlockRecipeRevert;
import main.extremeblocks.blocks.BlockTrash;
import main.extremeblocks.blocks.BlockTrophy;
import main.extremeblocks.blocks.BlockTrophy.TrophyType;
import main.extremeblocks.blocks.BlockVendingMachine;
import main.extremeblocks.blocks.BlockWaste;
import main.extremeblocks.blocks.BlockWire;
import main.extremeblocks.blocks.BlockXrayBlock;
import main.extremeblocks.blocks.abstractblocks.BlockBuild;
import main.extremeblocks.blocks.abstractblocks.BlockCompact;
import main.extremeblocks.blocks.abstractblocks.BlockEBOre;
import main.extremeblocks.blocks.abstractblocks.BlockSided;
import main.extremeblocks.blocks.abstractblocks.BlockStorage;
import main.extremeblocks.entities.mobs.EntityRobot.RobotType;
import main.extremeblocks.items.ItemBackpack;
import main.extremeblocks.items.ItemBattery;
import main.extremeblocks.items.ItemCellphone;
import main.extremeblocks.items.ItemCounter;
import main.extremeblocks.items.ItemEdible;
import main.extremeblocks.items.ItemExtractor;
import main.extremeblocks.items.ItemFurnaceUpgrade;
import main.extremeblocks.items.ItemFurnaceUpgrade.FurnaceUpgrade;
import main.extremeblocks.items.ItemFuse;
import main.extremeblocks.items.ItemGrenade;
import main.extremeblocks.items.ItemMolotov;
import main.extremeblocks.items.ItemPestleMortar;
import main.extremeblocks.items.ItemRobot;
import main.extremeblocks.items.ItemSeed;
import main.extremeblocks.items.ItemSorter;
import main.extremeblocks.items.ItemSpear;
import main.extremeblocks.items.ItemWeed;
import main.extremeblocks.misc.SortingSystem;
import main.extremeblocks.worldgen.GenManager;
import main.extremeblocks.worldgen.WorldTypeIslands;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;

public class Init
{
	public static final String MODID = "extremeblocks";
	public static final String VERSION = "6.4";
	public static CreativeTabs tab_mainBlocks = new CustomTab("Main Blocks");
	public static CreativeTabs tab_mainItems = new CustomTab("Main Items");
	public static CreativeTabs tab_tools = new CustomTab("Tools");
	public static CreativeTabs tab_lightedBlocks;
	public static CreativeTabs tab_fakeFloors;

	public static WorldType islands = new WorldTypeIslands();
	public static ToolMaterial TRINQUANTIUM_T = EnumHelper.addToolMaterial("Trinquantium", 4, 2368, 10.0F, 5.0F, 15);
	public static ToolMaterial BRONZE_T = EnumHelper.addToolMaterial("Bronze", 2, 328, 5.0F, 2.0F, 12);
	public static ToolMaterial SILVER_T = EnumHelper.addToolMaterial("Silver", 0, 13, 6.0F, 3.0F, 7);
	public static ToolMaterial GLESTER_T = EnumHelper.addToolMaterial("Glester", 0, 71, 9.0F, 0.0F, 9);
	public static ToolMaterial DELVLISH_T = EnumHelper.addToolMaterial("Delvlish", 0, 71, 0.0F, 3.0F, 4);
	public static ToolMaterial METEORITE_T = EnumHelper.addToolMaterial("Meteorite", 3, 2306, 6.0F, 3.0F, 4);
	public static ToolMaterial SAPPHIRE_T = EnumHelper.addToolMaterial("Sapphire", 3, 1909, 7.0F, 5.0F, 14);
	public static ToolMaterial ONYX_T = EnumHelper.addToolMaterial("Onyx", 2, 514, 4.0F, 3.0F, 17);
	public static ToolMaterial FLUORITE_T = EnumHelper.addToolMaterial("Fluorite", 2, 392, 5.0F, 2.0F, 7);
	public static ToolMaterial DIAMOND_T = EnumHelper.addToolMaterial("Emerald", 4, 4096, 12.0F, 6.0F, 15);
	public static ToolMaterial SPIRIT_T = EnumHelper.addToolMaterial("Sprit", 4, 1999, 13.0F, 7.0F, 25);
	public static ToolMaterial SUPER_T = EnumHelper.addToolMaterial("Super", 1000000 - 4, 1000000 - 4, 1000000 - 4, 1000000 - 4, 1000000 - 4);

	//public static ArmorMaterial TRINQUANTIUM_A = EnumHelper.addArmorMaterial("Trinquantium", 2368, new int[] {}, 15);
	//public static ArmorMaterial BRONZE_A = EnumHelper.addToolMaterial("Bronze", 2, 328, 5.0F, 2.0F, 12);
	//public static ArmorMaterial SILVER_A = EnumHelper.addToolMaterial("Silver", 0, 13, 6.0F, 3.0F, 7);
	//public static ArmorMaterial GLESTER_A = EnumHelper.addToolMaterial("Glester", 0, 71, 9.0F, 0.0F, 9);
	//public static ArmorMaterial DELVLISH_A = EnumHelper.addToolMaterial("Delvlish", 0, 71, 0.0F, 3.0F, 4);
	//public static ArmorMaterial METEORITE_A = EnumHelper.addToolMaterial("Meteorite", 3, 2306, 6.0F, 3.0F, 4);
	//public static ArmorMaterial SAPPHIRE_A = EnumHelper.addToolMaterial("Sapphire", 3, 1909, 7.0F, 5.0F, 14);
	//public static ArmorMaterial ONYX_A = EnumHelper.addToolMaterial("Onyx", 2, 514, 4.0F, 3.0F, 17);
	//public static ArmorMaterial FLUORITE_A = EnumHelper.addToolMaterial("Fluorite", 2, 392, 5.0F, 2.0F, 7);
	//public static ArmorMaterial DIAMOND_A = EnumHelper.addToolMaterial("Emerald", 4, 4096, 12.0F, 6.0F, 15);
	//public static ArmorMaterial SPIRIT_A = EnumHelper.addToolMaterial("Sprit", 4, 1999, 13.0F, 7.0F, 25);
	//public static ArmorMaterial SUPER_A = EnumHelper.addToolMaterial("Super", 1000000 - 4, 1000000 - 4, 1000000 - 4, 1000000 - 4, 1000000 - 4);

	public static final Item extreme_blocks = new ItemCustom("EB", null).setTextureName(MODID + ":EB");
	public static final Item gold_coin = new ItemCustom("Gold Coin", tab_mainItems).setTextureName(MODID + ":gold_coin");
	public static final Item glester_rock = new ItemCustom("Glester Rock", tab_mainItems).setTextureName(MODID + ":glester_rock");
	public static final Item delvlish_crystal = new ItemCustom("Delvlish Crystal", tab_mainItems).setTextureName(MODID + ":delvlish_crystal");
	public static final Item silver_ingot = new ItemCustom("Silver Ingot", tab_mainItems).setTextureName(MODID + ":silver_ingot");
	public static final Item trinquantium_ingot = new ItemCustom("Trinquantium Ingot", tab_mainItems).setTextureName(MODID + ":trinquantium_ingot");
	public static final Item crushed_stone = new ItemCustom("Crushed Stone", tab_mainItems).setTextureName(MODID + ":crushed_stone");
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
	public static final Item wrench = new ItemCustom("Wrench", tab_mainItems).setTextureName(MODID + ":wrench");
	public static final Item pipes = new ItemCustom("Pipes", tab_mainItems).setTextureName(MODID + ":pipes");
	public static final Item pipe = new ItemCustom("Pipe", tab_mainItems).setTextureName(MODID + ":pipe");
	public static final Item backpack = new ItemBackpack();
	public static final Item light = new ItemCustom("Light", tab_mainItems).setTextureName(MODID + ":light");
	public static final Item limestone = new ItemCustom("Limestone", tab_mainItems).setTextureName(MODID + ":limestone");
	public static final Item core_chip = new ItemCustom("Core Chip", tab_mainItems).setTextureName(MODID + ":core_chip");
	public static final Item counter = new ItemCounter();
	public static final Item meteorite_shards = new ItemCustom("Meteorite Shards", tab_mainItems).setTextureName(MODID + ":meteorite_shards");
	public static final Item meteor = new ItemCustom("Meteor", tab_mainItems).setTextureName(MODID + ":meteorite");
	public static final Item fluorite = new ItemCustom("Fluorite", tab_mainItems).setTextureName(MODID + ":fluorite");
	public static final Item sapphire = new ItemCustom("Sapphire", tab_mainItems).setTextureName(MODID + ":sapphire");
	public static final Item onyx = new ItemCustom("Onyx", tab_mainItems).setTextureName(MODID + ":onyx");
	public static final Item fuse = new ItemFuse();
	public static final Item grenade = new ItemGrenade();
	public static final Item molotov = new ItemMolotov();
	public static final Item spear = new ItemSpear();
	public static final Item mellow_weed = new ItemWeed("Mellow Weed", 1).setTextureName(MODID + ":mellow_weed");
	public static final Item normal_weed = new ItemWeed("Weed", 2).setTextureName(MODID + ":normal_weed");
	public static final Item hemp_leaves = new ItemWeed("Hemp Leaves", 0).setTextureName(MODID + ":hemp_leaves");
	public static final Item pestle_mortar = new ItemPestleMortar();
	public static final Item stone_stick = new ItemCustom("Stone Rod", tab_mainItems).setTextureName(MODID + ":stone_stick");
	public static final Item orange = new ItemEdible("Orange", 4).setTextureName(MODID + ":orange");
	public static final Item banana = new ItemEdible("Banana", 2).setTextureName(MODID + ":banana");
	public static final Item peach = new ItemEdible("Peach", 3).setTextureName(MODID + ":peach");
	public static final Item grapes = new ItemEdible("Grapes", 1).setTextureName(MODID + ":grapes");
	public static final Item power_core = new ItemCustom("Power Core", tab_mainItems).setTextureName(MODID + ":power_core");
	public static final Item computer_ai = new ItemCustom("Computer AI Chip", tab_mainItems).setTextureName(MODID + ":computer_ai");
	public static final Item robot_head = new ItemCustom("Robot Head", tab_mainItems).setTextureName(MODID + ":robot_head");
	public static final Item robot_arm = new ItemCustom("Robot Arm", tab_mainItems).setTextureName(MODID + ":robot_arm");
	public static final Item robot_leg = new ItemCustom("Robot Leg", tab_mainItems).setTextureName(MODID + ":robot_leg");
	public static final Item robot_torso = new ItemCustom("Robot Torso", tab_mainItems).setTextureName(MODID + ":robot_torso");
	public static final Item robot = new ItemCustom("Complete Robot", tab_mainItems).setTextureName(MODID + ":robot");
	public static final Item robot_warrior = new ItemRobot(RobotType.WARRIOR);
	public static final Item robot_farmer = new ItemRobot(RobotType.FARMER);
	public static final Item robot_archer = new ItemRobot(RobotType.ARCHER);
	public static final Item robot_hunter = new ItemRobot(RobotType.HUNTER);
	public static final Item robot_miner = new ItemRobot(RobotType.MINER);
	public static final Item battery = new ItemBattery(2000);
	public static final Item furnace_fast_upgrade = new ItemFurnaceUpgrade(FurnaceUpgrade.SPEED, 1).setTextureName(MODID + ":furnace_upgrade");
	public static final Item tomato_seeds = new ItemSeed("Tomato Seeds", ItemSeed.TOMATO_CROP);
	public static final Item tomato = new ItemEdible("Tomato", 3).setTextureName(MODID + ":tomato");
	public static final Item cucumber_seeds = new ItemSeed("Cucumber Seeds", ItemSeed.CUCUMBER_CROP);
	public static final Item cucumber = new ItemEdible("Cucumber", 3).setTextureName(MODID + ":cucumber");
	public static final Item sorter_component = new ItemCustom("Sorter Component", tab_mainItems).setTextureName(MODID + ":sorter_component");
	public static final Item spirit_fragment = new ItemCustom("Spirit Fragment", Init.tab_mainItems).setTextureName(Init.MODID + ":spirit_fragment");

	public static BlockStorage crate;
	public static BlockStorage barrel;
	public static BlockStorage cabinet;
	public static BlockStorage strongbox;
	public static BlockStorage big_crate;
	public static BlockStorage large_crate;
	public static BlockStorage small_crate;
	public static final Block glester_ore = new BlockEBOre("Glester Ore").setDrop(glester_rock);
	public static final Block silver_ore = new BlockEBOre("Silver Ore");
	public static final Block trinquantium_ore = new BlockEBOre("Trinquantium Ore");
	public static final Block copper_ore = new BlockEBOre("Copper Ore").setDrop(copper);
	public static final Block tin_ore = new BlockEBOre("Tin Ore").setDrop(tin);
	public static final Block delvlish_ore = new BlockEBOre("Delvlish Ore").setDrop(delvlish_crystal);
	public static final Block compact_stone = new BlockCustom(Material.rock, "Compact Stone").setHardness(3.0F).setCreativeTab(tab_mainBlocks).setBlockTextureName(MODID + ":Compact Stone");
	public static final Block silver_block = new BlockCompact("Silver Block");
	public static final Block bronze_block = new BlockCompact("Bronze Block");
	public static final Block trinquantium_block = new BlockCompact("Trinquantium Block");
	public static final Block xray_block = new BlockXrayBlock(true);
	public static final Block xray_block_un = new BlockXrayBlock(false);
	public static final Block cement_wall = new BlockCement(true);
	public static final Block plaster_wall = new BlockCustom(Material.rock, "Plaster Wall").setHardness(1.0F).setBlockTextureName(MODID + ":plasterwall").setCreativeTab(tab_mainBlocks);
	public static final Block weak_cement_wall = new BlockCement(false);
	public static final Block emptied_log = new BlockSided(Material.wood, "Emptied Log", "emptiedlog").setHardness(2.0F);
	public static final Block bone_dirt = new BlockCustom(Material.ground, "Bone Dirt").setDrop(bone_shard).setBlockTextureName(MODID + ":bone_dirt").setCreativeTab(tab_mainBlocks).setHardness(0.6F);
	public static final Block nuclear_waste = new BlockWaste();
	public static final Block fire_hydrant = new BlockHydrant();
	public static final Block lantern = new BlockLantern().setHardness(0.3F);
	public static final Block stone_pillar = new BlockSided(Material.rock, "Stone Pillar", "stonepillar").setHardness(1.5F);
	public static final Block trash = new BlockTrash().setHardness(3.0F);
	public static final Block vending_machine = new BlockVendingMachine();
	public static final Block dried_sapling = new BlockDriedSapling();
	public static final Block limestone_block = new BlockCustom(Material.rock, "Block of Limestone").setHardness(1.0F).setCreativeTab(tab_mainBlocks).setBlockTextureName(MODID + ":limestoneblock");
	public static final Block marble = new BlockCustom(Material.glass, "Marble").setHardness(0.6F).setCreativeTab(tab_mainBlocks).setBlockTextureName(MODID + ":marbleblock");
	public static final Block limestone_ore = new BlockEBOre("Limestone Ore").setDrop(limestone);
	public static final Block fake_sand = new BlockCustom(Material.sand, "Fake Sand").setHardness(0.5F).setCreativeTab(tab_mainBlocks).setBlockTextureName("sand");
	public static final Block fake_gravel = new BlockCustom(Material.ground, "Fake Gravel").setHardness(0.6F).setCreativeTab(tab_mainBlocks).setBlockTextureName("gravel");
	public static final Block drill = new BlockDrill(true);
	public static final Block drill_pole = new BlockDrill(false);
	public static final Block onyx_ore = new BlockEBOre("Onyx Ore").setDrop(onyx);
	public static final Block meteorite = new BlockEBOre("Meteorite").setDrop(meteorite_shards);
	public static final Block fluorite_ore = new BlockEBOre("Fluorite Ore").setDrop(fluorite);
	public static final Block sapphire_ore = new BlockEBOre("Sapphire Ore").setDrop(sapphire);
	public static final Block fuse_block = new BlockFuseBlock();
	public static final Block powder_keg = new BlockPowderKeg();
	public static final Block eb_table = new BlockEBTable();
	public static final Block cannabis_plant = new BlockHemp();
	public static final Block plate = new BlockPlate();
	public static final Block wire = new BlockWire();
	public static final Block charger = new BlockCharger();
	public static final Block cooker_off = new BlockCooker(false);
	public static final Block cooker_on = new BlockCooker(true);
	public static final Block generator = new BlockGenerator();
	public static final Block tomato_crop = new BlockCrop("Tomato Crop", tomato_seeds, tomato);
	public static final Block cucumber_crop = new BlockCrop("Cucumber Crop", cucumber_seeds, cucumber);
	public static final Block arrow_security = new BlockProtector();
	public static final Block recipe_revert = new BlockRecipeRevert();
	public static final Block gold_trophy = new BlockTrophy(TrophyType.GOLD);
	public static final Block bronze_trophy = new BlockTrophy(TrophyType.BRONZE);
	public static final Block diamond_trophy = new BlockTrophy(TrophyType.DIAMOND);
	public static final Block silver_trophy = new BlockTrophy(TrophyType.SILVER);
	public static final Block trinquantium_trophy = new BlockTrophy(TrophyType.TRINQUANTIUM);
	public static final Block iron_trophy = new BlockTrophy(TrophyType.IRON);
	public static final Block altar = new BlockAltar();

	public Init()
	{
		if (Vars.addFakeFloors)
		{
			tab_fakeFloors = new CustomTab("Fake Floors");
		}
		if (Vars.addLightedBlocks)
		{
			tab_lightedBlocks = new CustomTab("Lighted Blocks");
		}
		for (int i = 0; i < SortingSystem.values().length; i++)
		{
			ItemSorter sorter = new ItemSorter(SortingSystem.values()[i]);
			RegistryHelper.register(sorter);
			MPUtil.addRecipe(new ItemStack(sorter), sorter.getRecipe());
		}
		for (int i = 0; i < GenManager.getGens().size(); i++)
		{
			BlockBuild sorter = new BlockBuild(GenManager.getGens().get(i));
			RegistryHelper.register(sorter);
		}
		BlockStorage.initBlocks();

		// HIGHLY WANTED FEATURES:
		//
		// World Generations and Structures	
		// More Friendly/Tamable/Interactive Mobs
		// Fixing current bugs, glitches, and crashes
	}

	public static void addRecipes()
	{
		MPUtil.addRecipe(new ItemStack(counter), "X#", 'X', core_chip, '#', Blocks.wooden_button);
		MPUtil.addRecipe(new ItemStack(counter), "X#", 'X', core_chip, '#', Blocks.stone_button);
		MPUtil.addRecipe(new ItemStack(drill), "XXX", "#B#", " S ", 'X', Items.iron_ingot, '#', Items.diamond, 'B', Items.redstone, 'S', Blocks.diamond_block);
		MPUtil.addRecipe(new ItemStack(fake_gravel), "XX", 'X', Blocks.gravel);
		MPUtil.addRecipe(new ItemStack(fake_sand), "XX", 'X', Blocks.sand);
		MPUtil.addRecipe(new ItemStack(fire_hydrant), " # ", "#X#", "XBX", '#', Items.iron_ingot, 'X', pipes, 'B', Items.flint);
		MPUtil.addRecipe(new ItemStack(vending_machine), "##B", "#X#", "###", '#', Items.iron_ingot, 'X', core_chip, 'B', light);
		MPUtil.addRecipe(new ItemStack(trash), "##", "##", '#', Items.iron_ingot);
		MPUtil.addRecipe(new ItemStack(limestone_ore), "##", "##", '#', limestone);
		MPUtil.addRecipe(new ItemStack(light), "###", "#X#", "###", '#', Items.redstone, 'X', chip);
		MPUtil.addRecipe(new ItemStack(core_chip), "###", '#', chip);
		MPUtil.addRecipe(new ItemStack(stone_pillar), "##", "##", "##", '#', Blocks.stone);
		MPUtil.addRecipe(new ItemStack(lantern), "#", "#", '#', Blocks.glass);
		MPUtil.addRecipe(new ItemStack(Blocks.planks, 2), "#", '#', emptied_log);
		MPUtil.addRecipe(new ItemStack(backpack), "###", "#X#", "###", '#', Items.leather, 'X', Blocks.ender_chest);
		MPUtil.addRecipe(new ItemStack(pipes), "##", "##", '#', pipe);
		MPUtil.addRecipe(new ItemStack(wrench), "# #", "###", " # ", '#', Items.iron_ingot);
		MPUtil.addRecipe(new ItemStack(pipe), "###", '#', Items.iron_ingot);
		MPUtil.addRecipe(new ItemStack(Items.bone), "##", "##", '#', bone_shard);
		MPUtil.addRecipe(new ItemStack(plastic, 2), "##", "##", '#', sap);
		MPUtil.addRecipe(new ItemStack(chip), "##", "##", '#', Items.redstone);
		MPUtil.addRecipe(new ItemStack(cellphone), "##X", "#B#", "###", '#', plastic, 'X', Items.redstone, 'B', chip);
		MPUtil.addRecipe(new ItemStack(extractor), "#X#", '#', Blocks.stone, 'X', Blocks.lever);
		MPUtil.addRecipe(new ItemStack(crushed_stone, 5), "#", "#", '#', Blocks.stone);
		MPUtil.addRecipe(new ItemStack(xray_block_un, 2), "XBX", "B#B", "XBX", '#', spirit_fragment, 'B', Items.diamond, 'X', trinquantium_ingot);
		MPUtil.addRecipe(new ItemStack(xray_block), "XBX", "B#B", "XBX", '#', xray_block_un, 'B', delvlish_crystal, 'X', glester_rock);
		MPUtil.addRecipe(new ItemStack(gold_coin, 3), " # ", "###", " # ", '#', Items.gold_nugget);
		MPUtil.addRecipe(new ItemStack(bronze_trophy, 3), " # ", "###", "###", '#', bronze_ingot);
		MPUtil.addRecipe(new ItemStack(silver_trophy, 3), " # ", "###", "###", '#', silver_ingot);
		MPUtil.addRecipe(new ItemStack(gold_trophy, 3), " # ", "###", "###", '#', Items.gold_ingot);
		MPUtil.addRecipe(new ItemStack(diamond_trophy, 3), " # ", "###", "###", '#', Items.diamond);
		MPUtil.addRecipe(new ItemStack(trinquantium_trophy, 3), " # ", "###", "###", '#', trinquantium_ingot);
		MPUtil.addRecipe(new ItemStack(iron_trophy, 3), " # ", "###", "###", '#', Items.iron_ingot);
		MPUtil.addRecipe(new ItemStack(copper_and_tin_lump, 3), "#X#", "X#X", '#', copper, 'X', tin);
		MPUtil.addRecipe(new ItemStack(copper_and_tin_lump, 3), "#X#", "X#X", 'X', copper, '#', tin);
		MPUtil.addRecipe(new ItemStack(weak_cement_wall), "###", "#X#", "###", '#', crushed_stone, 'X', Items.water_bucket);
		MPUtil.addRecipe(new ItemStack(fuse, 2), "##", '#', Items.gunpowder);
		MPUtil.addRecipe(new ItemStack(powder_keg), "#X#", '#', Items.gunpowder, 'X', fuse);
		MPUtil.addRecipe(new ItemStack(meteor), "##", "##", '#', meteorite_shards);
		MPUtil.addRecipe(new ItemStack(strongbox), "ICI", 'I', Items.iron_ingot, 'C', crate);
		MPUtil.addRecipe(new ItemStack(barrel), "P", "I", "P", 'I', Items.iron_ingot, 'P', Blocks.planks);
		MPUtil.addRecipe(new ItemStack(cabinet), "PGP", 'P', Blocks.planks, 'G', Blocks.glass_pane);
		MPUtil.addRecipe(new ItemStack(small_crate), "SS", "SS", 'S', Items.stick);
		MPUtil.addRecipe(new ItemStack(big_crate), "WW", "WW", 'W', Blocks.log);
		MPUtil.addRecipe(new ItemStack(big_crate), "WW", "WW", 'W', Blocks.log2);
		MPUtil.addRecipe(new ItemStack(crate), "WW", 'W', Blocks.log);
		MPUtil.addRecipe(new ItemStack(crate), "WW", 'W', Blocks.log2);
		MPUtil.addRecipe(new ItemStack(large_crate), "WW", "WW", 'W', crate);
		MPUtil.addRecipe(new ItemStack(stone_stick), "SS", "SS", "SS", 'S', crushed_stone);
		MPUtil.addRecipe(new ItemStack(pestle_mortar), "  R", "RSR", " S ", 'S', crushed_stone, 'R', stone_stick);
		MPUtil.addRecipe(new ItemStack(power_core), "RCR", "CDC", "RCR", 'R', chip, 'C', core_chip, 'D', Items.diamond);
		MPUtil.addRecipe(new ItemStack(computer_ai), " D ", "GCG", " C ", 'G', Items.gold_nugget, 'C', core_chip, 'D', Items.diamond);
		MPUtil.addRecipe(new ItemStack(robot_head), "III", "ICI", "RIR", 'I', Items.iron_ingot, 'C', computer_ai, 'R', Items.redstone);
		MPUtil.addRecipe(new ItemStack(robot_arm), "II", "II", "RR", 'I', Items.iron_ingot, 'R', Items.redstone);
		MPUtil.addRecipe(new ItemStack(robot_leg), "III", "ICI", "GG", 'I', Items.iron_ingot, 'C', core_chip, 'G', Items.gold_ingot);
		MPUtil.addRecipe(new ItemStack(robot_torso), "RIR", "IPI", "III", 'I', Items.iron_ingot, 'R', Items.redstone, 'P', power_core);
		MPUtil.addRecipe(new ItemStack(robot), " H ", "ATA", "L L", 'H', robot_head, 'A', robot_arm, 'T', robot_torso, 'L', robot_leg);
		MPUtil.addRecipe(new ItemStack(plate), "S S", "PPP", 'P', Blocks.planks, 'S', Items.stick);
		MPUtil.addRecipe(new ItemStack(battery, 1, battery.getMaxDamage()), " T ", "TRT", "CCC", 'T', tin, 'R', Items.redstone, 'C', copper);
		MPUtil.addRecipe(new ItemStack(wire, 10), "GPG", 'G', Blocks.glass_pane, 'P', pipe);
		MPUtil.addRecipe(new ItemStack(generator), "IGI", "IBI", "ICI", 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'B', new ItemStack(battery, 1, battery.getMaxDamage()), 'C', core_chip);
		MPUtil.addRecipe(new ItemStack(charger), "IBI", "IBI", 'I', Items.iron_ingot, 'B', new ItemStack(battery, 1, battery.getMaxDamage()));
		MPUtil.addRecipe(new ItemStack(sorter_component), "S S", " I ", "S S", 'I', Items.iron_ingot, 'S', Items.stick);
		MPUtil.addRecipe(new ItemStack(furnace_fast_upgrade), "III", "GGG", "DDD", 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'D', Items.diamond);
		MPUtil.addRecipe(new ItemStack(arrow_security), "IDI", "IGI", "IBI", 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'D', Blocks.dispenser, 'B', Blocks.iron_block);
		MPUtil.addRecipe(new ItemStack(recipe_revert), "BIB", "ICI", "III", 'I', Items.diamond, 'C', Blocks.crafting_table, 'B', Blocks.diamond_block);
		MPUtil.addRecipe(new ItemStack(molotov), "GFG", 'G', Items.gunpowder, 'F', Items.flint_and_steel);
		MPUtil.addRecipe(new ItemStack(cooker_off), "ICI", "IPI", "BBB", 'I', Items.iron_ingot, 'C', charger, 'P', core_chip, 'B', Blocks.iron_block);
		MPUtil.addRecipe(new ItemStack(altar), "IOI", "IGI", "IMI", 'I', Blocks.sandstone, 'O', glester_rock, 'G', Init.delvlish_crystal, 'M', meteorite);
		MPUtil.addRecipe(new ItemStack(nuclear_waste), "III", "IFI", "III", 'I', Items.slime_ball, 'F', spirit_fragment);
		MPUtil.addRecipe(new ItemStack(spear), "  F", " S ", "S  ", 'F', Items.flint, 'S', Items.stick);

		MPUtil.addShapelessRecipe(new ItemStack(robot_warrior), robot, Items.golden_sword);
		MPUtil.addShapelessRecipe(new ItemStack(robot_farmer), robot, Items.golden_hoe);
		MPUtil.addShapelessRecipe(new ItemStack(robot_archer), robot, Items.bow);
		MPUtil.addShapelessRecipe(new ItemStack(robot_miner), robot, Items.golden_pickaxe);
		MPUtil.addShapelessRecipe(new ItemStack(robot_hunter), robot, Items.iron_sword, Items.bow);

		MPUtil.addCompactAndReversedRecipe(new ItemStack(limestone_block), new ItemStack(limestone));
		MPUtil.addCompactAndReversedRecipe(new ItemStack(silver_block), new ItemStack(silver_ingot));
		MPUtil.addCompactAndReversedRecipe(new ItemStack(bronze_block), new ItemStack(bronze_ingot));
		MPUtil.addCompactAndReversedRecipe(new ItemStack(trinquantium_block), new ItemStack(trinquantium_ingot));
		MPUtil.addCompactRecipe(new ItemStack(plaster_wall), new ItemStack(plastic));
		MPUtil.addCompactAndReversedRecipe(new ItemStack(compact_stone), new ItemStack(Blocks.stone));
		MPUtil.addCompactAndReversedRecipe(new ItemStack(grenade), new ItemStack(Items.gunpowder));

		MPUtil.addRecipe(new ItemStack(eb_table), "C", 'C', Blocks.crafting_table);
		MPUtil.addRecipe(new ItemStack(Blocks.crafting_table), "E", 'E', eb_table);

		MPUtil.addSmeltingRecipe(new ItemStack(trinquantium_ore), new ItemStack(trinquantium_ingot), 4.0F);
		MPUtil.addSmeltingRecipe(new ItemStack(silver_ore), new ItemStack(silver_ingot), 2.0F);
		MPUtil.addSmeltingRecipe(new ItemStack(copper_and_tin_lump), new ItemStack(bronze_ingot), 3.0F);
		MPUtil.addSmeltingRecipe(new ItemStack(weak_cement_wall), new ItemStack(cement_wall), 3.0F);
		MPUtil.addSmeltingRecipe(new ItemStack(limestone_block), new ItemStack(marble), 3.0F);
	}

	public static void handleConfig()
	{
		Config cfg = new Config(ExtremeBlocks.configFile);
		Configuration c = cfg.config;
		String CG = Config.generalConfig;
		Vars.checkVersion = c.get(CG, "Check Version", true, "Check to see if the current EB version is outdated").getBoolean();
		Vars.addLightedBlocks = c.get(CG, "Add Lighted Blocks", false).setRequiresMcRestart(true).getBoolean(false);
		Vars.addFakeFloors = c.get(CG, "Add Fake Floors", false).setRequiresMcRestart(true).getBoolean(false);
		Vars.xrayBlockWork = c.get(CG, "X-Ray Blocks Work", true, "Do X Ray blocks Work? Or is it like Glass?").setRequiresWorldRestart(true).getBoolean(true);
		Vars.customCraftingTable = c.get(CG, "Custom Crafting Table for EB Recipes", true, "Will only the Custom Table be used for EB Recipes? If False, both Crafting tables will be used for them.").setRequiresMcRestart(true).getBoolean(true);
		Vars.addVanillaRecipes = c.get(CG, "Add Vanilla Recipes", true, "The EB Crafting Table also have the vanilla recipes").setRequiresMcRestart(true).getBoolean(true);
		Vars.counterMessage = c.get(CG, "Counter Message", "", "The message that will be displayed on the Counter (Leave blank for your username)").getString();
		Vars.traditionalTrash = cfg.getBool("Traditional Trash Can", false, "The trash can has the same, destroying items, function instead of a Gui");

		Vars.alterWorld = cfg.getBool("Alter World", true, "Allow EB to alter your world and generate structures and features");
		Vars.genHouse = cfg.shouldGen("House");
		Vars.genCastle = cfg.shouldGen("Castle");
		Vars.genDriedTree = cfg.shouldGen("Dried Tree");

		Vars.addMobs = cfg.getBool("Allow Mobs", true, "Allow Mobs to work in the game. If this is false, all the EB mobs are disabled.");
		Vars.addCastleSkeleton = cfg.allowMob("Castle Skeleton", "Skeleton");
		Vars.addCastleZombie = cfg.allowMob("Castle Zombie", "Zombie");
		Vars.addEvilIronGolem = cfg.allowMob("Evil Iron Golem", "Iron Golem");
		Vars.addRobot = cfg.allowMob("Robot", "Villager");
		Vars.addDemon = cfg.allowMob("Demon", "Blaze");

		Vars.copperSR = cfg.getSpawnRate("Copper", 20);
		Vars.tinSR = cfg.getSpawnRate("Tin", 20);
		Vars.silverSR = cfg.getSpawnRate("Silver", 2);
		Vars.trinquantiumSR = cfg.getSpawnRate("Trinquantium", 1);
		Vars.glesterSR = cfg.getSpawnRate("Glester", 10);
		Vars.delvlishSR = cfg.getSpawnRate("Delvlish", 10);
		Vars.meteoriteSR = cfg.getSpawnRate("Meteorite", 5);
		Vars.fluoriteSR = cfg.getSpawnRate("Fluorite", 15);
		Vars.compactStoneSR = cfg.getSpawnRate("Compact Stone", 10);
		Vars.onyxSR = cfg.getSpawnRate("Onyx", 10);
		Vars.boneDirtSR = cfg.getSpawnRate("Bone Dirt", 5);
		if (cfg.config.hasChanged())
		{
			cfg.config.save();
		}
	}

	public static void addReplacements()
	{
		RegistryHelper.addReplacement("emptiedLog", "logWood");
	}
}
