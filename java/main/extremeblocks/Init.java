package main.extremeblocks;

import main.com.hk.eb.util.BlockCustom;
import main.com.hk.eb.util.CustomTab;
import main.com.hk.eb.util.ItemCustom;
import main.com.hk.eb.util.MPUtil;
import main.com.hk.eb.util.RegistryHelper;
import main.extremeblocks.Vars.Mob;
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
import main.extremeblocks.blocks.abstracts.BlockBuild;
import main.extremeblocks.blocks.abstracts.BlockCompact;
import main.extremeblocks.blocks.abstracts.BlockEBOre;
import main.extremeblocks.blocks.abstracts.BlockSided;
import main.extremeblocks.blocks.abstracts.BlockStorage;
import main.extremeblocks.entities.mobs.robot.RobotType;
import main.extremeblocks.items.ItemBackpack;
import main.extremeblocks.items.ItemBattery;
import main.extremeblocks.items.ItemCellphone;
import main.extremeblocks.items.ItemCounter;
import main.extremeblocks.items.ItemEBGuide;
import main.extremeblocks.items.ItemEdible;
import main.extremeblocks.items.ItemExtractor;
import main.extremeblocks.items.ItemFurnaceUpgrade;
import main.extremeblocks.items.ItemFurnaceUpgrade.FurnaceUpgrade;
import main.extremeblocks.items.ItemFuse;
import main.extremeblocks.items.ItemGrenade;
import main.extremeblocks.items.ItemMarker;
import main.extremeblocks.items.ItemMolotov;
import main.extremeblocks.items.ItemPestleMortar;
import main.extremeblocks.items.ItemRobot;
import main.extremeblocks.items.ItemSeed;
import main.extremeblocks.items.ItemSorter;
import main.extremeblocks.items.ItemSpear;
import main.extremeblocks.items.ItemSummonBloodwing;
import main.extremeblocks.items.ItemWeed;
import main.extremeblocks.misc.SortingSystem;
import main.extremeblocks.worldgen.GenManager;
import main.extremeblocks.worldgen.GenManager.Gen;
import main.extremeblocks.worldgen.Generation;
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
	public static final String VERSION = "6.7";
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
	public static final Item gold_coin = new ItemCustom("Gold Coin", tab_mainItems).setInfo("This item can be traded with a farmer villager for some valuable trades!").setShowRecipe().setTextureName(MODID + ":gold_coin");
	public static final Item glester_rock = new ItemCustom("Glester Rock", tab_mainItems).setInfo("Can be crafted into multiple tools").setTextureName(MODID + ":glester_rock");
	public static final Item delvlish_crystal = new ItemCustom("Delvlish Crystal", tab_mainItems).setInfo("Can be crafted into multiple tools").setTextureName(MODID + ":delvlish_crystal");
	public static final Item silver_ingot = new ItemCustom("Silver Ingot", tab_mainItems).setInfo("Can be crafted into multiple tools. Acquired by smelting silver ore").setTextureName(MODID + ":silver_ingot");
	public static final Item trinquantium_ingot = new ItemCustom("Trinquantium Ingot", tab_mainItems).setInfo("Can be crafted into multiple tools. Acquired by smelting trinquantium ore").setTextureName(MODID + ":trinquantium_ingot");
	public static final Item crushed_stone = new ItemCustom("Crushed Stone", tab_mainItems).setInfo("Used in multiple recipes.").setShowRecipe().setTextureName(MODID + ":crushed_stone");
	public static final Item copper = new ItemCustom("Copper", tab_mainItems).setInfo("Can be combined with tin to make copper and tin lumps").setTextureName(MODID + ":copper");
	public static final Item tin = new ItemCustom("Tin", tab_mainItems).setInfo("Can be combined with copper to make copper and tin lumps").setTextureName(MODID + ":tin");
	public static final Item copper_and_tin_lump = new ItemCustom("Copper and Tin Lump", tab_mainItems).setInfo("Can be smelted into Bronze Ingots").setTextureName(MODID + ":copper_tin_lump");
	public static final Item bronze_ingot = new ItemCustom("Bronze Ingot", tab_mainItems).setInfo("Can be crafted into multiple tools").setTextureName(MODID + ":bronze_ingot");
	public static final Item sap = new ItemCustom("Sap", tab_mainItems).setTextureName(MODID + ":sap");
	public static final Item extractor = new ItemExtractor();
	public static final Item cellphone = new ItemCellphone();
	public static final Item plastic = new ItemCustom("Plastic", tab_mainItems).setShowRecipe().setInfo("Used for multiple different recipes. A very important element to the mod really!").setTextureName(MODID + ":plastic");
	public static final Item chip = new ItemCustom("Chip", tab_mainItems).setInfo("The basis of all the mod's electronics").setTextureName(MODID + ":chip");
	public static final Item bone_shard = new ItemCustom("Bone Shard", tab_mainItems).setInfo("Can be used in a crafting recipe to create bones").setTextureName(MODID + ":bone_shard");
	public static final Item wrench = new ItemCustom("Wrench", tab_mainItems).setInfo("Simple multi-purpose tool.").setTextureName(MODID + ":wrench");
	public static final Item pipes = new ItemCustom("Pipes", tab_mainItems).setShowRecipe().setTextureName(MODID + ":pipes");
	public static final Item pipe = new ItemCustom("Pipe", tab_mainItems).setShowRecipe().setTextureName(MODID + ":pipe");
	public static final Item backpack = new ItemBackpack();
	public static final Item light = new ItemCustom("Light", tab_mainItems).setShowRecipe().setTextureName(MODID + ":light");
	public static final Item limestone = new ItemCustom("Limestone", tab_mainItems).setInfo("Used in a multitude of crafting recipes").setTextureName(MODID + ":limestone");
	public static final Item core_chip = new ItemCustom("Core Chip", tab_mainItems).setShowRecipe().setInfo("A step up from the simple chip. Used for some of the advanced technologies").setTextureName(MODID + ":core_chip");
	public static final Item counter = new ItemCounter();
	public static final Item meteorite_shards = new ItemCustom("Meteorite Shards", tab_mainItems).setInfo("Obtained by mining meteorites.").setTextureName(MODID + ":meteorite_shards");
	public static final Item meteor = new ItemCustom("Meteor", tab_mainItems).setShowRecipe().setInfo("Can be crafted into multiple tools.").setTextureName(MODID + ":meteorite");
	public static final Item fluorite = new ItemCustom("Fluorite", tab_mainItems).setInfo("Can be crafted into multiple tools.").setTextureName(MODID + ":fluorite");
	public static final Item sapphire = new ItemCustom("Sapphire", tab_mainItems).setInfo("Can be crafted into multiple tools.").setTextureName(MODID + ":sapphire");
	public static final Item onyx = new ItemCustom("Onyx", tab_mainItems).setInfo("Can be crafted into multiple tools.").setTextureName(MODID + ":onyx");
	public static final Item fuse = new ItemFuse();
	public static final Item grenade = new ItemGrenade();
	public static final Item molotov = new ItemMolotov();
	public static final Item spear = new ItemSpear();
	public static final Item mellow_weed = new ItemWeed("Mellow Weed", 1).setTextureName(MODID + ":mellow_weed");
	public static final Item normal_weed = new ItemWeed("Weed", 2).setTextureName(MODID + ":normal_weed");
	public static final Item hemp_leaves = new ItemWeed("Hemp Leaves", 0).setTextureName(MODID + ":hemp_leaves");
	public static final Item pestle_mortar = new ItemPestleMortar();
	public static final Item stone_stick = new ItemCustom("Stone Rod", tab_mainItems).setShowRecipe().setTextureName(MODID + ":stone_stick");
	public static final Item orange = new ItemEdible("Orange", 4).setTextureName(MODID + ":orange");
	public static final Item banana = new ItemEdible("Banana", 2).setTextureName(MODID + ":banana");
	public static final Item peach = new ItemEdible("Peach", 3).setTextureName(MODID + ":peach");
	public static final Item grapes = new ItemEdible("Grapes", 1).setTextureName(MODID + ":grapes");
	public static final Item power_core = new ItemCustom("Power Core", tab_mainItems).setShowRecipe().setTextureName(MODID + ":power_core");
	public static final Item computer_ai = new ItemCustom("Computer AI Chip", tab_mainItems).setShowRecipe().setTextureName(MODID + ":computer_ai");
	public static final Item robot_head = new ItemCustom("Robot Head", tab_mainItems).setShowRecipe().setTextureName(MODID + ":robot_head");
	public static final Item robot_arm = new ItemCustom("Robot Arm", tab_mainItems).setShowRecipe().setTextureName(MODID + ":robot_arm");
	public static final Item robot_leg = new ItemCustom("Robot Leg", tab_mainItems).setShowRecipe().setTextureName(MODID + ":robot_leg");
	public static final Item robot_torso = new ItemCustom("Robot Torso", tab_mainItems).setShowRecipe().setTextureName(MODID + ":robot_torso");
	public static final Item robot = new ItemCustom("Complete Robot", tab_mainItems).setShowRecipe().setInfo("Can be crafed with multiple other tools to create their corresponding robot counterparts!").setTextureName(MODID + ":robot");
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
	public static final Item sorter_component = new ItemCustom("Sorter Component", tab_mainItems).setShowRecipe().setTextureName(MODID + ":sorter_component");
	public static final Item spirit_fragment = new ItemCustom("Spirit Fragment", Init.tab_mainItems).setInfo("Obtained by killing the Demon Spirit. Used in some different recipes that are very useful!").setTextureName(Init.MODID + ":spirit_fragment");
	public static final Item eb_guide = new ItemEBGuide();
	public static final Item marker = new ItemMarker();
	public static final Item bat_wing = new ItemCustom("Bat Wing", tab_mainItems).setTextureName(MODID + ":bat_wing").setInfo("10% chance to drop from a bat. It's used to create the Bloodwing summon!");
	public static final Item summon_bloodwing = new ItemSummonBloodwing();

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
	public static final Block compact_stone = new BlockCustom(Material.rock, "Compact Stone").setInfo("Compact way to save stone").setShowRecipe().setHardness(3.0F).setCreativeTab(tab_mainBlocks).setBlockTextureName(MODID + ":Compact Stone");
	public static final Block silver_block = new BlockCompact("Silver Block");
	public static final Block bronze_block = new BlockCompact("Bronze Block");
	public static final Block trinquantium_block = new BlockCompact("Trinquantium Block");
	public static final Block xray_block = new BlockXrayBlock(true).setInfo("Allows you to see through blocks to the next open block.").setShowRecipe();
	public static final Block xray_block_un = new BlockXrayBlock(false).setInfo("");
	public static final Block cement_wall = new BlockCement(true);
	public static final Block plaster_wall = new BlockCustom(Material.rock, "Plaster Wall").setShowRecipe().setHardness(1.0F).setBlockTextureName(MODID + ":plasterwall").setCreativeTab(tab_mainBlocks);
	public static final Block weak_cement_wall = new BlockCement(false);
	public static final Block emptied_log = new BlockSided(Material.wood, "Emptied Log", "emptiedlog").setHardness(2.0F);
	public static final Block bone_dirt = new BlockCustom(Material.ground, "Bone Dirt").setInfo("Drops a bone shard upon breaking.").setDrop(bone_shard).setBlockTextureName(MODID + ":bone_dirt").setCreativeTab(tab_mainBlocks).setHardness(0.6F);
	public static final Block nuclear_waste = new BlockWaste().setShowRecipe().setInfo("This block gives off tons of random potion effects when a mob touches it. This includes the player. The effects could be good, or bad.");
	public static final Block fire_hydrant = new BlockHydrant().setInfo("Upon right clicking with anything, you will extinguish all fires in a 3 block radius. BUT, if you right click it with a wrench, if will spawn a block if water on top of the hydrant!").setShowRecipe();
	public static final Block lantern = new BlockLantern().setShowRecipe().setInfo("Gives off some nice light. It isn't too powerful but it's nice.").setHardness(0.3F);
	public static final Block stone_pillar = new BlockSided(Material.rock, "Stone Pillar", "stonepillar").setShowRecipe().setHardness(1.5F);
	public static final Block trash = new BlockTrash().setShowRecipe().setInfo("This block will allow you to destroy anything that you don't want anymore. If the config option for traditional trash can is enabled. It will destry items like a cactus. But if not, you could right click on it and place items in the Gui. Then power it with redstone and hit that empty button.").setHardness(3.0F);
	public static final Block vending_machine = new BlockVendingMachine().setShowRecipe().setInfo("This block will allow you to gamble in Minecraft! Right click it with a diamond and it will give you a random item in all of Minecraft back!");
	public static final Block dried_sapling = new BlockDriedSapling();
	public static final Block limestone_block = new BlockCustom(Material.rock, "Block of Limestone").setShowRecipe().setInfo("A compact way to save space when you have too much limestone. This can also be smelted to create a Marble block.").setHardness(1.0F).setCreativeTab(tab_mainBlocks).setBlockTextureName(MODID + ":limestoneblock");
	public static final Block marble = new BlockCustom(Material.glass, "Marble").setInfo("Nice decoration block.").setHardness(0.6F).setCreativeTab(tab_mainBlocks).setBlockTextureName(MODID + ":marbleblock");
	public static final Block limestone_ore = new BlockEBOre("Limestone Ore").setDrop(limestone);
	public static final Block fake_sand = new BlockCustom(Material.sand, "Fake Sand").setShowRecipe().setInfo("Simply. Fake sand, it will not drop if there's no block under it.").setHardness(0.5F).setCreativeTab(tab_mainBlocks).setBlockTextureName("sand");
	public static final Block fake_gravel = new BlockCustom(Material.ground, "Fake Gravel").setShowRecipe().setInfo("Simply. Fake gravel, it will not drop if there's no block under it.").setHardness(0.6F).setCreativeTab(tab_mainBlocks).setBlockTextureName("gravel");
	public static final Block drill = new BlockDrill(true).setShowRecipe().setInfo("When placed down on the ground, it will drill all the way down to an open block, or to bedrock. Then the contents are stored in it's inventory. To retrieve the items, you have to right click it.");
	public static final Block drill_pole = new BlockDrill(false);
	public static final Block onyx_ore = new BlockEBOre("Onyx Ore").setDrop(onyx);
	public static final Block meteorite = new BlockEBOre("Meteorite").setDrop(meteorite_shards);
	public static final Block fluorite_ore = new BlockEBOre("Fluorite Ore").setDrop(fluorite);
	public static final Block sapphire_ore = new BlockEBOre("Sapphire Ore").setDrop(sapphire);
	public static final Block fuse_block = new BlockFuseBlock();
	public static final Block powder_keg = new BlockPowderKeg().setShowRecipe().setInfo("This powder keg will blow up when there is a fire next to it. This can be used with the fuse to set traps!");
	public static final Block eb_table = new BlockEBTable().setShowRecipe().setInfo("This crafting table replacement can be used just to register all of the Extreme Blocks Mod's recipes. There are some config options that you can play with to maximize enjoyment.");
	public static final Block cannabis_plant = new BlockHemp().setInfo("Similar to sugar cane, it can be grown and then used for weed.");
	public static final Block plate = new BlockPlate().setShowRecipe().setInfo("You can right click on this to place the item in the plate. You can use this as a kind of Item Frame.");
	public static final Block wire = new BlockWire().setShowRecipe().setInfo("This block can transfer EB Joules through other wire blocks placed next to it. You can connect this to energy receivers to use energy and also energy emmiters to create energy.");
	public static final Block charger = new BlockCharger().setShowRecipe().setInfo("This block can charge batteries for you. When connected with a wire, it can receive energy an store it in batteries.");
	public static final Block cooker_off = new BlockCooker(false).setShowRecipe().setInfo("This block is just like a furnace. But a lot better! It can use EB Joules to cook it's items. You can even upgrade it. By placing a Furnace upgrade in it's upgrade slot, you can speed up the cooking process");
	public static final Block cooker_on = new BlockCooker(true);
	public static final Block generator = new BlockGenerator().setShowRecipe().setInfo("This generator generates energy on fuels! You can place anything that can be used in a furnace, into the generator's slot and it will create energy and send it to nearby wires.");
	public static final Block tomato_crop = new BlockCrop("Tomato Crop", tomato_seeds, tomato);
	public static final Block cucumber_crop = new BlockCrop("Cucumber Crop", cucumber_seeds, cucumber);
	public static final Block arrow_security = new BlockProtector().setInfo("This block will take in energy and use it to shoot at mobs in a 10 block radius. It will shoot arrows as long as it has atleast 50 EB Joules.").setShowRecipe();
	public static final Block recipe_revert = new BlockRecipeRevert().setShowRecipe().setInfo("This very overpowered block will allow you to revert any recipe you want. Just place it into the slot and it will show you the recipe to make it and also, give you back all the items.");
	public static final Block gold_trophy = new BlockTrophy(TrophyType.GOLD);
	public static final Block bronze_trophy = new BlockTrophy(TrophyType.BRONZE);
	public static final Block diamond_trophy = new BlockTrophy(TrophyType.DIAMOND);
	public static final Block silver_trophy = new BlockTrophy(TrophyType.SILVER);
	public static final Block trinquantium_trophy = new BlockTrophy(TrophyType.TRINQUANTIUM);
	public static final Block iron_trophy = new BlockTrophy(TrophyType.IRON);
	public static final Block altar = new BlockAltar().setShowRecipe().setInfo("When placed in a four block high colum, it will spawn in the Demon Spirit boss and allow you to fight it!");

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
			ExtremeBlocks.blocks.add(new BlockBuild(GenManager.getGens().get(i)));
		}
		BlockStorage.initBlocks();
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
		MPUtil.addRecipe(new ItemStack(robot_leg), "III", "ICI", "G G", 'I', Items.iron_ingot, 'C', core_chip, 'G', Items.gold_ingot);
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
		MPUtil.addRecipe(new ItemStack(eb_guide, 2), "SP", "SP", 'P', Items.paper, 'S', Items.stick);
		MPUtil.addRecipe(new ItemStack(marker), "RGB", "PIP", "PIP", 'R', new ItemStack(Items.dye, 1, 1), 'G', new ItemStack(Items.dye, 1, 2), 'B', new ItemStack(Items.dye, 1, 4), 'P', plastic, 'I', new ItemStack(Items.dye, 1, 0));
		MPUtil.addRecipe(new ItemStack(summon_bloodwing), "ECE", "WSW", "ECE", 'E', Items.spider_eye, 'S', spirit_fragment, 'C', Items.egg, 'W', bat_wing);

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
		Vars.guidePausesGame = cfg.getBool("Guide Pauses Game", false, "Will using the EB Guide pause the whole game?");

		Vars.alterWorld = cfg.getBool("Alter World", true, "Allow EB to alter your world and generate structures and features");
		for (Class<? extends Generation> clazz : GenManager.getGens())
		{
			Vars.gens.put(clazz, new Boolean(cfg.shouldGen(clazz.getAnnotation(Gen.class).name())));
		}

		Vars.addMobs = cfg.getBool("Allow Mobs", true, "Allow Mobs to work in the game. If this is false, all the EB mobs are disabled.");

		Class<?>[] classes = EBClient.removeMobs.keySet().toArray(new Class<?>[0]);
		if (Vars.addMobs)
		{
			for (Class<?> classe : classes)
			{
				Mob m = classe.getAnnotation(Mob.class);
				EBClient.removeMobs.put(classe, cfg.allowMob(m.getName(), m.getVanillaName()));
			}
		}

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
