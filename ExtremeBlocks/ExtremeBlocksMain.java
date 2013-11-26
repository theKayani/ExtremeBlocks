package ExtremeBlocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ExtremeBlocks.Blocks.ItemFencesBlock;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@NetworkMod(clientSideRequired = true, serverSideRequired = false)
@Mod(modid = ExtremeBlocksMain.modid, name = "Extreme Blocks", version = "3.5")

public class ExtremeBlocksMain 
{ 
	public static final String modid = "EB_ExtremeBlocks";

	public static CreativeTabs EBBasicBlocksTab = new EBBlocks(CreativeTabs.getNextID(), "EBBlocks");
	public static CreativeTabs EBBasicItemsTab = new EBItems(CreativeTabs.getNextID(), "EBItems");
	public static CreativeTabs EBToolsTab = new EBTools(CreativeTabs.getNextID(), "EBBlocks");
	public static CreativeTabs EBMiscTab = new EBMisc(CreativeTabs.getNextID(), "EBItems");
	public static CreativeTabs EBLightedBlocksTab = new EBLightedBlocks(CreativeTabs.getNextID(), "EBBlocks");
	public static CreativeTabs EBFencesTab = new EBFences(CreativeTabs.getNextID(), "EBItems");
	public static CreativeTabs EBOresTab = new EBOres(CreativeTabs.getNextID(), "EBItems");

	EventManager eventmanager = new EventManager();

	//Items
	public static Item ExtremeBlocks;
	public static Item GoldCoin; 
	public static Item GlesterRock;
	public static Item DelvlishCrystal;
	public static Item SilverBar;
	public static Item TrinquantiumBar;
	public static Item CrushedStone;
	public static Item BronzeMedal;
	public static Item SilverMedal;
	public static Item GoldMedal;
	public static Item TrinquantiumMedal;
	public static Item Copper;
	public static Item Tin;
	public static Item CopperAndTinLump;
	public static Item BronzeBar;
	public static Item TrinquantiumPickaxe;
	public static Item TrinquantiumAxe;
	public static Item TrinquantiumSpade;
	public static Item TrinquantiumHoe;
	public static Item TrinquantiumSword;	
	public static Item SilverPickaxe;
	public static Item SilverAxe;
	public static Item SilverSpade;
	public static Item SilverHoe;
	public static Item SilverSword;	
	public static Item BronzePickaxe;
	public static Item BronzeAxe;
	public static Item BronzeSpade;
	public static Item BronzeHoe;
	public static Item BronzeSword;
	public static Item Sap;
	public static Item Extractor;
	public static Item Cellphone;
	public static Item Plastic;
	public static Item Chip;
	public static Item BoneShard;
	public static Item BoneSword;
	public static Item Wrench;
	public static Item Pipes;
	public static Item Pipe;
	public static Item Backpack;
	public static Item Light;
	public static Item CoreChip;
	public static Item GameRemote;
	public static Item WeakenedGlesterRock;
	public static Item WeakenedDelvlishCrystal;
	public static Item DelvlishPickaxe;
	public static Item DelvlishAxe;
	public static Item DelvlishSpade;
	public static Item DelvlishHoe;
	public static Item DelvlishSword;	
	public static Item GlesterPickaxe;
	public static Item GlesterAxe;
	public static Item GlesterSpade;
	public static Item GlesterHoe;
	public static Item GlesterSword;
	public static Item Limestone;

	//New Console Stuff!!!
	public static Item Returner;
	public static Item Notes;
	public static Item Notes1;

	//Helps you keep track of anything you need! Like a Counter!
	public static Item Counter;

	//Blocks
	public static Block GlesterOre;
	public static Block SilverOre;
	public static Block TrinquantiumOre;
	public static Block DelvlishOre;
	public static Block ExtraOrdinaryStone;
	public static Block TrinquantiumBlock;
	public static Block CopperOre;
	public static Block TinOre;
	public static Block SilverBlock;
	public static Block BronzeBlock;
	public static Block LightedStone;
	public static Block LightedPlank;
	public static Block LightedBrick;
	public static Block XRayBlock;
	public static Block XRayBlockUn;
	public static Block NetherSilver;
	public static Block NetherCopper;
	public static Block NetherTin;
	public static Block NetherDelvlish;
	public static Block NetherGlester;
	public static Block NetherDiamond;	
	public static Block NetherCoal;
	public static Block NetherGold;
	public static Block NetherEmerald;
	public static Block NetherIron;
	public static Block NetherTrinquantium;
	public static Block CementWall;
	public static Block PlasterWall;
	public static Block WeakCementWall;
	public static Block EmptiedLog;
	public static Block BoneDirt;
	public static Block OffStone;
	public static Block OffPlank;
	public static Block OffBrick;
	public static Block OffDirt;
	public static Block LightedDirt;
	public static Block Waste;
	public static Block OffIronBlock;
	public static Block OffDiamondBlock;
	public static Block OffGlass;
	public static Block LightedIronBlock;
	public static Block LightedDiamondBlock;
	
	
	public static Block LightedGlass;
	public static Block LightedGoldBlock;
	public static Block OffGoldBlock;
	public static Block FireHydrant;
	public static Block Lantern;
	public static Block StonePillar;
	public static Block WasteBin;
	public static Block VendingMachine;
	public static Block DriedSapling;
	public static Block LimestoneRock;
	public static Block Marble;
	public static Block LimestoneBlock;	
	public static Block OffObsidian;
	public static Block LightedObsidian;
	public static Block OffClay;
	public static Block LightedClay;
	public static Block OffBlackWool;
	public static Block LightedBlackWool;
	
	public static Block OffWhiteWool;
	public static Block LightedWhiteWool;
	public static Block OffBedrock;
	public static Block OffNetherrack;
	public static Block OffEmeraldBlock;
	public static Block OffLapisBlock;
	public static Block OffBricks;
	public static Block OffCobblestone;
	public static Block OffMossyCobblestone;
	public static Block OffEndstone;
	public static Block OffNetherBrick;
	public static Block LightedBedrock;
	public static Block LightedNetherrack;
	public static Block LightedEmeraldBlock;
	public static Block LightedLapisBlock;
	public static Block LightedBricks;
	public static Block LightedCobblestone;
	public static Block LightedMossyCobblestone;
	public static Block LightedEndstone;
	public static Block LightedNetherBrick;
	public static Block FakeSand;
	public static Block FakeGravel;
	public static Block Drill;
	public static Block DrillPole;
	public static Block Aesthetic;
	public static Block Fences;
	public static Block FakedPlanks;

	//Game floors/blocks for the PS3 and the Xbox 360
	public static Block RedGameFloor;
	public static Block BlueGameFloor;
	public static Block GreenGameFloor;
	public static Block YellowGameFloor;
	public static Block SpreadGameBlock;
	
	//This is Clone, I named it that because it 
	//looks like planks but actually is a command block!
	public static Block Clone;

	//These are Sanded Blocks!
	//These blocks don't look like Sand but do Drop like Sand!
	public static Block SandedBrick;
	public static Block SandedBedrock;
	public static Block SandedPlank;
	public static Block SandedStone;
	public static Block SandedGlass;
	public static Block SandedDirt;

	//Work In Progress!!
	public static Block SandLauncher;
	
	//The Console that replaced PS3 and Xbox-360!
	public static Block Console;
	
	//This block is in the game, Click it to get a reward!
	public static Block RewardBlock;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Registry.addEBConfig(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		GameRegistry.registerWorldGenerator(eventmanager);

		Block.commandBlock.setCreativeTab(EBMiscTab);

		Registry.addItems();
		Registry.addBlocks();

		gameRegistry();

		recipeRegistry();
	}
	private static void gameRegistry() 
	{
		GameRegistry.registerBlock(Fences, ItemFencesBlock.class, modid + (Fences.getUnlocalizedName().substring(5)));

		LanguageRegistry.addName(new ItemStack(Fences, 1, 0), "Iron Fence");
		LanguageRegistry.addName(new ItemStack(Fences, 1, 1), "Gold Fence");
		LanguageRegistry.addName(new ItemStack(Fences, 1, 2), "Diamond Fence");
		LanguageRegistry.addName(new ItemStack(Fences, 1, 3), "Emerald Fence");
		LanguageRegistry.addName(new ItemStack(Fences, 1, 4), "Lapis Fence");
		LanguageRegistry.addName(new ItemStack(Fences, 1, 5), "Bedrock Fence");
		LanguageRegistry.addName(new ItemStack(Fences, 1, 6), "Stone Brick Fence");
		LanguageRegistry.addName(new ItemStack(Fences, 1, 7), "Bricks Fence");
		LanguageRegistry.addName(new ItemStack(Fences, 1, 8), "Cobblestone Fence");
		LanguageRegistry.addName(new ItemStack(Fences, 1, 9), "Endstone Fence");
		LanguageRegistry.addName(new ItemStack(Fences, 1, 10), "Glass Fence");
		LanguageRegistry.addName(new ItemStack(Fences, 1, 11), "Mossy Cobblestone Fence");
		LanguageRegistry.addName(new ItemStack(Fences, 1, 12), "Dirt Fence");
		LanguageRegistry.addName(new ItemStack(Fences, 1, 13), "Netherrack Fence");
		LanguageRegistry.addName(new ItemStack(Fences, 1, 14), "Obsidian Fence");
		LanguageRegistry.addName(new ItemStack(Fences, 1, 15), "Stone Fence");


		fenceRecipeRegistry();

		oreRecipeRegistry();

		//Block Registry
		Registry.RegisterBlock(GlesterOre, "GlesterOre", "Glester Ore");
		Registry.RegisterBlock(SilverOre, "SilverOre", "Silver Ore");
		Registry.RegisterBlock(TrinquantiumOre, "TrinquantiumOre", "Trinquantium Ore");
		Registry.RegisterBlock(DelvlishOre, "DelvlishOre", "Delvlish Ore");
		Registry.RegisterBlock(ExtraOrdinaryStone, "ExtraOrdinaryStone", "Extraordinary Stone");
		Registry.RegisterBlock(TrinquantiumBlock, "TrinquantiumBlock", "Trinquantium Block");
		Registry.RegisterBlock(SilverBlock, "SilverBlock", "Silver Block");
		Registry.RegisterBlock(BronzeBlock, "BronzeBlock", "Bronze Block");
		Registry.RegisterBlock(CopperOre, "CopperOre", "Copper Ore");			
		Registry.RegisterBlock(TinOre, "TinOre", "Tin Ore");
		Registry.RegisterBlock(LightedStone,"LightedStone", "Lighted Stone");
		Registry.RegisterBlock(LightedPlank,"LightedPlank", "Lighted Planks");
		Registry.RegisterBlock(LightedBrick,"LightedBrick", "Lighted Bricks");
		Registry.RegisterBlock(XRayBlock,"XRayBlock", "X-Ray Block");
		Registry.RegisterBlock(XRayBlockUn,"XRayBlockUn", "Unfinished X-Ray Block");
		Registry.RegisterBlock(NetherSilver,"NetherSilver", "Nether Silver");
		Registry.RegisterBlock(NetherCopper,"NetherCopper", "Nether Copper");
		Registry.RegisterBlock(NetherTin,"NetherTin", "Nether Tin");
		Registry.RegisterBlock(NetherDelvlish,"NetherDelvlish", "Nether Delvlish");
		Registry.RegisterBlock(NetherGlester,"NetherGlester", "Nether Glester");
		Registry.RegisterBlock(NetherDiamond,"NetherDiamond", "Nether Diamond");
		Registry.RegisterBlock(NetherCoal,"NetherCoal", "Nether Coal");
		Registry.RegisterBlock(NetherGold,"NetherGold", "Nether Gold");
		Registry.RegisterBlock(NetherEmerald,"NetherEmerald", "Nether Emerald");
		Registry.RegisterBlock(NetherIron,"NetherIron", "Nether Iron");
		Registry.RegisterBlock(NetherTrinquantium,"NetherTrinquantium", "Nether Trinquantium");
		Registry.RegisterBlock(CementWall,"CementWall", "Cement Wall");
		Registry.RegisterBlock(WeakCementWall, "WeakCementWall", "Weak Cement Wall");
		Registry.RegisterBlock(PlasterWall, "PlasterWall", "Plaster Wall");
		Registry.RegisterBlock(EmptiedLog,"EmptiedLog", "Dried Log");
		Registry.RegisterBlock(BoneDirt,"BoneDirt", "Bone Dirt");
		Registry.RegisterBlock(OffStone,"OffStone", "Off Stone");
		Registry.RegisterBlock(OffPlank,"OffPlank", "Off Plank");
		Registry.RegisterBlock(OffBrick,"OffBrick", "Off Brick");
		Registry.RegisterBlock(OffDirt,"OffDirt", "Off Dirt");
		Registry.RegisterBlock(LightedDirt,"LightedDirt", "Lighted Dirt");
		Registry.RegisterBlock(Waste,"Waste", "Nuclear Sewage");
		Registry.RegisterBlock(OffGlass,"OffGlass", "Off Glass");
		Registry.RegisterBlock(OffGoldBlock,"OffGoldBlock", "Off Gold Block");
		Registry.RegisterBlock(OffIronBlock,"OffIronBlock", "Off Iron Block");
		Registry.RegisterBlock(OffDiamondBlock,"OffDiamondBlock", "Off Diamond Block");
		Registry.RegisterBlock(LightedGlass,"LightedGlass", "Lighted Glass");
		Registry.RegisterBlock(LightedGoldBlock,"LightedGoldBlock", "Lighted Gold Block");
		Registry.RegisterBlock(LightedIronBlock,"LightedIronBlock", "Lighted Iron Block");
		Registry.RegisterBlock(LightedDiamondBlock,"LightedDiamondBlock", "Lighted Diamond Block");
		Registry.RegisterBlock(FireHydrant,"FireHydrant", "Fire Hydrant");
		Registry.RegisterBlock(Lantern,"Lantern", "Lantern");
		Registry.RegisterBlock(StonePillar,"StonePillar", "Stone Pillar");
		Registry.RegisterBlock(WasteBin,"WasteBin", "Waste Bin");
		Registry.RegisterBlock(VendingMachine,"VendingMachine", "Vending Machine");
		Registry.RegisterBlock(DriedSapling,"DriedSapling", "Dried Sapling");
		Registry.RegisterBlock(LimestoneRock,"LimestoneRock", "Limestone Ore");
		Registry.RegisterBlock(LimestoneBlock,"LimestoneBlock", "Limestone Block");
		Registry.RegisterBlock(Marble,"Marble", "Marble Block");	
		Registry.RegisterBlock(OffWhiteWool,"OffWhiteWool", "Off White Wool");
		Registry.RegisterBlock(OffBricks,"OffBricks", "Off Bricks");
		Registry.RegisterBlock(OffCobblestone,"OffCobblestone", "Off Cobblestone");
		Registry.RegisterBlock(OffMossyCobblestone,"OffMossyCobblestone", "Off Mossy Cobblestone");
		Registry.RegisterBlock(OffEndstone,"OffEndstone", "Off Endstone");
		Registry.RegisterBlock(OffBedrock,"OffBedrock", "Off Bedrock");
		Registry.RegisterBlock(OffNetherrack,"OffNetherrack", "Off Netherrack");
		Registry.RegisterBlock(OffNetherBrick,"OffNetherBrick", "Off Nether Brick");
		Registry.RegisterBlock(OffEmeraldBlock,"OffEmeraldBlock", "Off Emerald Block");
		Registry.RegisterBlock(OffLapisBlock,"OffLapisBlock", "Off Lapis Block");
		Registry.RegisterBlock(OffObsidian,"OffObsidian", "Off Obsidian");
		Registry.RegisterBlock(OffBlackWool,"OffBlackWool", "Off Black Wool");
		Registry.RegisterBlock(OffClay,"OffClay", "Off Clay");	
		Registry.RegisterBlock(LightedWhiteWool,"LightedWhiteWool", "Lighted White Wool");
		Registry.RegisterBlock(LightedBricks,"LightedBricks", "Lighted Bricks");
		Registry.RegisterBlock(LightedCobblestone,"LightedCobblestone", "Lighted Cobblestone");
		Registry.RegisterBlock(LightedMossyCobblestone,"LightedMossyCobblestone", "Lighted Mossy Cobblestone");
		Registry.RegisterBlock(LightedEndstone,"LightedEndstone", "Lighted Endstone");
		Registry.RegisterBlock(LightedBedrock,"LightedBedrock", "Lighted Bedrock");
		Registry.RegisterBlock(LightedNetherrack,"LightedNetherrack", "Lighted Netherrack");
		Registry.RegisterBlock(LightedNetherBrick,"LightedNetherBrick", "Lighted Nether Brick");
		Registry.RegisterBlock(LightedEmeraldBlock,"LightedEmeraldBlock", "Lighted Emerald Block");
		Registry.RegisterBlock(LightedLapisBlock,"LightedLapisBlock", "Lighted Lapis Block");
		Registry.RegisterBlock(LightedObsidian,"LightedObsidian", "Lighted Obsidian");
		Registry.RegisterBlock(LightedBlackWool,"LightedBlackWool", "Lighted Black Wool");
		Registry.RegisterBlock(LightedClay,"LightedClay", "Lighted Clay");	
		Registry.RegisterBlock(Drill,"Drill", "One-Block Drill");
		Registry.RegisterBlock(Aesthetic,"Aesthetic", "Aesthetic");
		Registry.RegisterBlock(FakeGravel,"FakeGravel", "Fake Gravel");
		Registry.RegisterBlock(FakeSand,"FakeSand", "Fake Sand");
		Registry.RegisterBlock(FakedPlanks,"FakedPlanks", "Fake Floor - Planks");
		Registry.RegisterBlock(RedGameFloor,"RedGameFloor", "Game Floor");
		Registry.RegisterBlock(GreenGameFloor,"GreenGameFloor", "Game Floor");
		Registry.RegisterBlock(BlueGameFloor,"BlueGameFloor", "Game Floor");
		Registry.RegisterBlock(YellowGameFloor,"YellowGameFloor", "Game Floor");
		Registry.RegisterBlock(SpreadGameBlock,"SpreadGameBlock", "Game Floor");
		Registry.RegisterBlock(Clone,"Clone", "Command Block");
		Registry.RegisterBlock(SandedBrick,"SandedBrick", "Sand-Like Bricks");
		Registry.RegisterBlock(SandedPlank,"SandedPlank", "Sand-Like Planks");
		Registry.RegisterBlock(SandedGlass,"SandedGlass", "Sand-Like Glass");
		Registry.RegisterBlock(SandedStone,"SandedStone", "Sand-Like Stone");
		Registry.RegisterBlock(SandedBedrock,"SandedBedrock", "Sand-Like Bedrock");
		Registry.RegisterBlock(SandLauncher,"SandLauncher", "Sand-Launcher");
		Registry.RegisterBlock(Console,"Console", "Console");
		Registry.RegisterBlock(RewardBlock,"RewardBlock", "Reward Block");

		//Item Registry
		Registry.RegisterItem(GoldCoin, "GoldCoin", "Gold Coin");
		Registry.RegisterItem(ExtremeBlocks, "ExtremeBlocks", "Extreme Blocks");
		Registry.RegisterItem(GlesterRock, "GlesterRock", "Glester Dust");
		Registry.RegisterItem(DelvlishCrystal, "DelvlishCrystal", "Delvlish Crystal");
		Registry.RegisterItem(TrinquantiumBar, "TrinquantiumBar", "Trinquantium Bar");
		Registry.RegisterItem(SilverBar, "SilverBar", "Silver Bar");
		Registry.RegisterItem(CrushedStone, "CrushedStone", "Crushed Stone");
		Registry.RegisterItem(BronzeMedal, "BronzeMedal", "Bronze Trophy");
		Registry.RegisterItem(SilverMedal, "SilverMedal", "Silver Trophy");
		Registry.RegisterItem(GoldMedal, "GoldMedal", "Gold Trophy");
		Registry.RegisterItem(TrinquantiumMedal, "TrinquantiumMedal", "Trinquantium Trophy");
		Registry.RegisterItem(Copper, "Copper", "Copper");
		Registry.RegisterItem(Tin, "Tin", "Tin");
		Registry.RegisterItem(CopperAndTinLump, "CopperAndTinLump", "Copper And Tin Lump");
		Registry.RegisterItem(BronzeBar, "BronzeBar", "Bronze Bar");
		Registry.RegisterItem(TrinquantiumPickaxe, "TrinquantiumPickaxe", "Trinquantium Pickaxe");
		Registry.RegisterItem(TrinquantiumAxe, "TrinquantiumAxe", "Trinquantium Axe");
		Registry.RegisterItem(TrinquantiumSpade, "TrinquantiumSpade", "Trinquantium Shovel");
		Registry.RegisterItem(TrinquantiumHoe, "TrinquantiumHoe", "Trinquantium Hoe");
		Registry.RegisterItem(TrinquantiumSword, "TrinquantiumSword", "Trinquantium Sword");
		Registry.RegisterItem(SilverPickaxe, "SilverPickaxe", "SilverPickaxe");
		Registry.RegisterItem(SilverAxe, "SilverAxe", "Silver Axe");
		Registry.RegisterItem(SilverSpade, "SilverSpade", "Silver Shovel");
		Registry.RegisterItem(SilverHoe, "SilverHoe", "Silver Hoe");
		Registry.RegisterItem(SilverSword, "SilverSword", "Silver Sword");
		Registry.RegisterItem(BronzePickaxe, "BronzePickaxe", "BronzePickaxe");
		Registry.RegisterItem(BronzeAxe, "BronzeAxe", "Bronze Axe");
		Registry.RegisterItem(BronzeSpade, "BronzeSpade", "Bronze Shovel");
		Registry.RegisterItem(BronzeHoe, "BronzeHoe", "Bronze Hoe");
		Registry.RegisterItem(BronzeSword, "BronzeSword", "Bronze Sword");
		Registry.RegisterItem(Extractor, "Extractor", "Sap Tap");
		Registry.RegisterItem(Sap, "Sap", "Sap");
		Registry.RegisterItem(Plastic, "Plastic", "Plastic");
		Registry.RegisterItem(Cellphone, "Cellphone", "Cellphone");
		Registry.RegisterItem(Chip, "Chip", "Chip");
		Registry.RegisterItem(BoneShard, "BoneShard", "Bone Shard");
		Registry.RegisterItem(BoneSword, "BoneDagger", "Bone Dagger");
		Registry.RegisterItem(Wrench, "Wrench", "Wrench");
		Registry.RegisterItem(Pipes, "Pipes", "Pipes");
		Registry.RegisterItem(Pipe, "Pipe", "Pipe");
		Registry.RegisterItem(Backpack, "Backpack", "Backpack");
		Registry.RegisterItem(Light, "Light", "Light");
		Registry.RegisterItem(CoreChip, "CoreChip", "Core Chip");
		Registry.RegisterItem(GameRemote, "GameRemote", "Controller");
		Registry.RegisterItem(WeakenedGlesterRock, "WeakenedGlesterRock", "Weak Glester Dust");
		Registry.RegisterItem(WeakenedDelvlishCrystal, "WeakenedDelvlishCrystal", "Weak Delvlish Crystal");
		Registry.RegisterItem(DelvlishPickaxe, "DelvlishPickaxe", "Delvlish Pickaxe");
		Registry.RegisterItem(DelvlishAxe, "DelvlishAxe", "Delvlish Axe");
		Registry.RegisterItem(DelvlishSpade, "DelvlishSpade", "Delvlish Shovel");
		Registry.RegisterItem(DelvlishHoe, "DelvlishHoe", "Delvlish Hoe");
		Registry.RegisterItem(DelvlishSword, "DelvlishSword", "Delvlish Sword");
		Registry.RegisterItem(GlesterPickaxe, "GlesterPickaxe", "Glester Pickaxe");
		Registry.RegisterItem(GlesterAxe, "GlesterAxe", "Glester Axe");
		Registry.RegisterItem(GlesterSpade, "GlesterSpade", "Glester Shovel");
		Registry.RegisterItem(GlesterHoe, "GlesterHoe", "Glester Hoe");
		Registry.RegisterItem(GlesterSword, "GlesterSword", "Glester Sword");
		Registry.RegisterItem(Limestone, "Limestone", "Limestone Fragment");
		Registry.RegisterItem(Returner, "Returner", "Returner");
		Registry.RegisterItem(Counter, "Counter", "Counter");
		Registry.RegisterItem(Notes, "Notes", "Game Notes");
		Registry.RegisterItem(Notes1, "Notes1", "Game Notes");
	}

	private static void recipeRegistry()
	{	
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Counter), new Object[] 
						{
					"X#", 'X', ExtremeBlocksMain.CoreChip, '#', Block.woodenButton
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Counter), new Object[] 
						{
					"X#", 'X', ExtremeBlocksMain.CoreChip, '#', Block.stoneButton
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.SandLauncher), new Object[] 
						{
					"X#X", "X#X", "XBX", 'X', Block.stone, '#', Block.sand, 'B', ExtremeBlocksMain.ExtraOrdinaryStone
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Console), new Object[] 
						{
					"XXX", "X#X", "XXB", 'X', ExtremeBlocksMain.Plastic, '#', ExtremeBlocksMain.CoreChip, 'B', ExtremeBlocksMain.Light
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Drill), new Object[] 
						{
					"XXX", "#B#", " # ", 'X', Item.ingotIron, '#', Item.diamond, 'B', Item.redstone
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.FakeGravel), new Object[] 
						{
					"XX", 'X', Block.gravel
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.FakeSand), new Object[] 
						{
					"XX", 'X', Block.sand
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LightedMossyCobblestone, 3), new Object[] 
						{
					" # ", "#X#"," # ", '#', Block.cobblestoneMossy, 'X', Block.torchWood
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LightedEndstone, 3), new Object[] 
						{
					" # ", "#X#"," # ", '#', Block.whiteStone, 'X', Block.torchWood
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LightedNetherBrick, 3), new Object[] 
						{
					" # ", "#X#"," # ", '#', Block.netherBrick, 'X', Block.torchWood
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LightedBedrock, 3), new Object[] 
						{
					" # ", "#X#"," # ", '#', Block.bedrock, 'X', Block.torchWood
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LightedNetherrack, 3), new Object[] 
						{
					" # ", "#X#"," # ", '#', Block.netherrack, 'X', Block.torchWood
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LightedEmeraldBlock, 3), new Object[] 
						{
					" # ", "#X#"," # ", '#', Block.blockEmerald, 'X', Block.torchWood
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LightedLapisBlock, 3), new Object[] 
						{
					" # ", "#X#"," # ", '#', Block.blockLapis, 'X', Block.torchWood
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LightedBlackWool, 3), new Object[] 
						{
					" # ", "#X#"," # ", '#', new ItemStack(Block.cloth, 1, 1), 'X', Block.torchWood
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LightedWhiteWool, 3), new Object[] 
						{
					" # ", "#X#"," # ", '#', new ItemStack(Block.cloth, 1, 2), 'X', Block.torchWood
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LightedClay, 3), new Object[] 
						{
					" # ", "#X#"," # ", '#', Block.blockClay, 'X', Block.torchWood
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LightedObsidian, 3), new Object[] 
						{
					" # ", "#X#"," # ", '#', Block.obsidian, 'X', Block.torchWood
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LightedBricks, 3), new Object[] 
						{
					" # ", "#X#"," # ", '#', Block.brick, 'X', Block.torchWood
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LightedCobblestone, 3), new Object[] 
						{
					" # ", "#X#"," # ", '#', Block.cobblestone, 'X', Block.torchWood
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.FireHydrant), new Object[] 
						{
					" # ", "#X#","XBX", '#', Item.ingotIron, 'X', ExtremeBlocksMain.Pipes, 'B', Item.flint
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Waste), new Object[] 
						{
					"###", "###","###", '#', Item.slimeBall
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.VendingMachine), new Object[] 
						{
					"##B", "#X#","###", '#', Item.ingotIron, 'X', ExtremeBlocksMain.CoreChip, 'B', ExtremeBlocksMain.Light
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.WasteBin), new Object[] 
						{
					"##", "##", '#', Item.ingotIron
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LimestoneRock), new Object[] 
						{
					"##", "##", '#', ExtremeBlocksMain.Limestone
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.DelvlishCrystal), new Object[] 
						{
					"##", "##", '#', ExtremeBlocksMain.WeakenedDelvlishCrystal
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.GlesterRock), new Object[] 
						{
					"##", "##", '#', ExtremeBlocksMain.WeakenedGlesterRock
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.GameRemote), new Object[] 
						{
					"###", "#X#", "# #", '#', ExtremeBlocksMain.Plastic, 'X', ExtremeBlocksMain.Chip
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Light), new Object[] 
						{
					"###", "#X#", "###", '#', Item.redstone, 'X', ExtremeBlocksMain.Chip
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.CoreChip), new Object[] 
						{
					"###", '#', ExtremeBlocksMain.Chip
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.StonePillar), new Object[] 
						{
					"##", "##", "##", '#', Block.stone
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Lantern), new Object[] 
						{
					"#","#", '#', Block.glass
						});
		GameRegistry.addRecipe(
				new ItemStack(Block.planks, 2), new Object[] 
						{
					"#", '#', ExtremeBlocksMain.EmptiedLog
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Backpack), new Object[] 
						{
					"###", "#X#", "###", '#', Item.leather, 'X', Item.eyeOfEnder 
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Pipes), new Object[] 
						{
					"##", "##", '#', ExtremeBlocksMain.Pipe
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Wrench), new Object[] 
						{
					"#  #", "###"," # ", '#', Item.ingotIron
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.ExtraOrdinaryStone), new Object[] 
						{
					"###", "###","###", '#', Block.stone
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Pipe), new Object[] 
						{
					"###", '#', Item.ingotIron
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.BoneSword), new Object[] 
						{
					"#", "#", '#', ExtremeBlocksMain.BoneShard
						});
		GameRegistry.addRecipe(
				new ItemStack(Item.bone), new Object[] 
						{
					"##", "##", '#', ExtremeBlocksMain.BoneShard
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Plastic, 2), new Object[] 
						{
					"##", "##", '#', ExtremeBlocksMain.Sap
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Chip), new Object[] 
						{
					"##", "##", '#', Item.redstone
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Cellphone), new Object[] 
						{
					"##X", "#B#", "###", '#', ExtremeBlocksMain.Plastic, 'X', Item.redstone, 'B', ExtremeBlocksMain.Chip
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.PlasterWall), new Object[] 
						{
					"###", "###", "###", '#', ExtremeBlocksMain.Plastic
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Extractor), new Object[] 
						{
					"#X#", '#', Block.stone, 'X', Block.lever
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.CrushedStone, 5), new Object[] 
						{
					"#", "#", '#', Block.stone
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.TrinquantiumBlock), new Object[] 
						{
					"###", "###","###", '#', ExtremeBlocksMain.TrinquantiumBar
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LightedStone, 3), new Object[] 
						{
					" # ", "#X#"," # ", '#', Block.stone, 'X', Block.torchWood
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LightedDirt, 3), new Object[] 
						{
					" # ", "#X#"," # ", '#', Block.dirt, 'X', Block.torchWood
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LightedGlass, 3), new Object[] 
						{
					" # ", "#X#"," # ", '#', Block.glass, 'X', Block.torchWood
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LightedGoldBlock, 3), new Object[] 
						{
					" # ", "#X#"," # ", '#', Block.blockGold, 'X', Block.torchWood
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LightedIronBlock, 3), new Object[] 
						{
					" # ", "#X#"," # ", '#', Block.blockIron, 'X', Block.torchWood
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LightedDiamondBlock, 3), new Object[] 
						{
					" # ", "#X#"," # ", '#', Block.blockDiamond, 'X', Block.torchWood
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LightedBrick, 3), new Object[] 
						{
					" # ", "#X#"," # ", '#', Block.stoneBrick, 'X', Block.torchWood
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.XRayBlockUn), new Object[] 
						{
					"XBX", "B#B","XBX", '#', Block.torchWood, 'B', Item.diamond, 'X', ExtremeBlocksMain.TrinquantiumBar
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.XRayBlock), new Object[] 
						{
					"XBX", "B#B","XBX", '#', ExtremeBlocksMain.XRayBlockUn, 'B', ExtremeBlocksMain.DelvlishCrystal, 'X', ExtremeBlocksMain.GlesterRock
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LightedPlank, 3), new Object[] 
						{
					" # ", "#X#"," # ", '#', Block.planks, 'X', Block.torchWood
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.SilverBlock), new Object[] 
						{
					"###", "###","###", '#', ExtremeBlocksMain.SilverBar
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.BronzeBlock), new Object[] 
						{
					"###", "###","###", '#', ExtremeBlocksMain.BronzeBar
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.GoldCoin, 3), new Object[] 
						{
					" # ", "###"," # ", '#', Item.goldNugget
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.BronzeMedal, 3), new Object[] 
						{
					" # ", "###","###", '#', ExtremeBlocksMain.BronzeBar
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.SilverMedal, 3), new Object[] 
						{
					" # ", "###","###", '#', ExtremeBlocksMain.SilverBar
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.GoldMedal, 3), new Object[] 
						{
					" # ", "###","###", '#', Item.ingotGold
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.CopperAndTinLump, 3), new Object[] 
						{
					"#X#","X#X", '#', ExtremeBlocksMain.Copper, 'X', ExtremeBlocksMain.Tin
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.CopperAndTinLump, 3), new Object[] 
						{
					"#X#","X#X", 'X', ExtremeBlocksMain.Copper, '#', ExtremeBlocksMain.Tin
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.TrinquantiumMedal, 3), new Object[] 
						{
					" # ", "###","###", '#', ExtremeBlocksMain.TrinquantiumBar
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.TrinquantiumAxe), new Object[] 
						{
					"## ", "#X "," X ", '#', ExtremeBlocksMain.TrinquantiumBar, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.TrinquantiumPickaxe), new Object[] 
						{
					"###", " X "," X ", '#', ExtremeBlocksMain.TrinquantiumBar, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.TrinquantiumSpade), new Object[] 
						{
					" # ", " X "," X ", '#', ExtremeBlocksMain.TrinquantiumBar, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.TrinquantiumSword), new Object[] 
						{
					" # ", " # "," X ", '#', ExtremeBlocksMain.TrinquantiumBar, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.TrinquantiumHoe), new Object[] 
						{
					"## ", " X "," X ", '#', ExtremeBlocksMain.TrinquantiumBar, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.GlesterAxe), new Object[] 
						{
					"## ", "#X "," X ", '#', ExtremeBlocksMain.GlesterRock, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.GlesterPickaxe), new Object[] 
						{
					"###", " X "," X ", '#', ExtremeBlocksMain.GlesterRock, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.GlesterSpade), new Object[] 
						{
					" # ", " X "," X ", '#', ExtremeBlocksMain.GlesterRock, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.GlesterSword), new Object[] 
						{
					" # ", " # "," X ", '#', ExtremeBlocksMain.GlesterRock, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.GlesterHoe), new Object[] 
						{
					"## ", " X "," X ", '#', ExtremeBlocksMain.GlesterRock, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.DelvlishAxe), new Object[] 
						{
					"## ", "#X "," X ", '#', ExtremeBlocksMain.DelvlishCrystal, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.DelvlishPickaxe), new Object[] 
						{
					"###", " X "," X ", '#', ExtremeBlocksMain.DelvlishCrystal, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.DelvlishSpade), new Object[] 
						{
					" # ", " X "," X ", '#', ExtremeBlocksMain.DelvlishCrystal, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.DelvlishSword), new Object[] 
						{
					" # ", " # "," X ", '#', ExtremeBlocksMain.DelvlishCrystal, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.DelvlishHoe), new Object[] 
						{
					"## ", " X "," X ", '#', ExtremeBlocksMain.DelvlishCrystal, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.SilverSpade), new Object[] 
						{
					" # ", " X "," X ", '#', ExtremeBlocksMain.SilverBar, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.SilverSword), new Object[] 
						{
					" # ", " # "," X ", '#', ExtremeBlocksMain.SilverBar, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.SilverHoe), new Object[] 
						{
					"## ", " X "," X ", '#', ExtremeBlocksMain.SilverBar, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.SilverAxe), new Object[] 
						{
					"## ", "#X "," X ", '#', ExtremeBlocksMain.SilverBar, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.SilverPickaxe), new Object[] 
						{
					"###", " X "," X ", '#', ExtremeBlocksMain.SilverBar, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.BronzePickaxe), new Object[] 
						{
					"###", " X "," X ", '#', ExtremeBlocksMain.BronzeBar, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.BronzeAxe), new Object[] 
						{
					"## ", "#X "," X ", '#', ExtremeBlocksMain.BronzeBar, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.BronzeSpade), new Object[] 
						{
					" # ", " X "," X ", '#', ExtremeBlocksMain.BronzeBar, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.BronzeHoe), new Object[] 
						{
					"## ", " X "," X ", '#', ExtremeBlocksMain.BronzeBar, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.BronzeSword), new Object[] 
						{
					" # ", " # "," X ", '#', ExtremeBlocksMain.BronzeBar, 'X', Item.stick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.WeakCementWall), new Object[] 
						{
					"###", "#X#", "###", '#', ExtremeBlocksMain.CrushedStone, 'X', Item.bucketWater
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.LimestoneBlock), new Object[] 
						{
					"###", "###", "###", '#', ExtremeBlocksMain.Limestone
						});
		GameRegistry.addSmelting(ExtremeBlocksMain.TrinquantiumOre.blockID, new ItemStack(TrinquantiumBar), 4.0F);
		GameRegistry.addSmelting(ExtremeBlocksMain.NetherTrinquantium.blockID, new ItemStack(TrinquantiumBar), 3.0F);
		GameRegistry.addSmelting(ExtremeBlocksMain.NetherGold.blockID, new ItemStack(Item.ingotGold), 3.0F);
		GameRegistry.addSmelting(ExtremeBlocksMain.NetherIron.blockID, new ItemStack(Item.ingotIron), 3.0F);
		GameRegistry.addSmelting(ExtremeBlocksMain.NetherSilver.blockID, new ItemStack(SilverBar), 3.0F);
		GameRegistry.addSmelting(ExtremeBlocksMain.SilverOre.blockID, new ItemStack(SilverBar), 2.0F);
		GameRegistry.addSmelting(ExtremeBlocksMain.CopperAndTinLump.itemID, new ItemStack(BronzeBar), 3.0F);
		GameRegistry.addSmelting(ExtremeBlocksMain.WeakCementWall.blockID, new ItemStack(CementWall), 3.0F);
		GameRegistry.addSmelting(ExtremeBlocksMain.LimestoneRock.blockID, new ItemStack(Marble), 3.0F);
	}
	private static void fenceRecipeRegistry()
	{	
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Fences, 4, 0), new Object[] 
						{
					"XBX", "XBX", 'X', Item.stick, 'B', Block.blockIron
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Fences, 4, 1), new Object[] 
						{
					"XBX", "XBX", 'X', Item.stick, 'B', Block.blockGold
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Fences, 4, 2), new Object[] 
						{
					"XBX", "XBX", 'X', Item.stick, 'B', Block.blockDiamond
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Fences, 4, 3), new Object[] 
						{
					"XBX", "XBX", 'X', Item.stick, 'B', Block.blockEmerald
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Fences, 4, 4), new Object[] 
						{
					"XBX", "XBX", 'X', Item.stick, 'B', Block.blockLapis
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Fences, 4, 5), new Object[] 
						{
					"XBX", "XBX", 'X', Item.stick, 'B', Block.bedrock
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Fences, 4, 6), new Object[] 
						{
					"XBX", "XBX", 'X', Item.stick, 'B', Block.stoneBrick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Fences, 4, 7), new Object[] 
						{
					"XBX", "XBX", 'X', Item.stick, 'B', Block.brick
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Fences, 4, 8), new Object[] 
						{
					"XBX", "XBX", 'X', Item.stick, 'B', Block.cobblestone
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Fences, 4, 9), new Object[] 
						{
					"XBX", "XBX", 'X', Item.stick, 'B', Item.enderPearl
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Fences, 4, 10), new Object[] 
						{
					"XBX", "XBX", 'X', Item.stick, 'B', Block.glass
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Fences, 4, 11), new Object[] 
						{
					"XBX", "XBX", 'X', Item.stick, 'B', Block.cobblestoneMossy
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Fences, 4, 12), new Object[] 
						{
					"XBX", "XBX", 'X', Item.stick, 'B', Block.dirt
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Fences, 4, 13), new Object[] 
						{
					"XBX", "XBX", 'X', Item.stick, 'B', Block.netherrack
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Fences, 4, 14), new Object[] 
						{
					"XBX", "XBX", 'X', Item.stick, 'B', Block.obsidian
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.Fences, 4, 15), new Object[] 
						{
					"XBX", "XBX", 'X', Item.stick, 'B', Block.stone
						});
	}

	public static void oreRecipeRegistry()
	{
		GameRegistry.addRecipe(
				new ItemStack(Block.oreDiamond), new Object[] 
						{
					"#", "X",'X', Block.stone, '#', Item.diamond
						});

		GameRegistry.addRecipe(
				new ItemStack(Block.oreCoal), new Object[] 
						{
					"#", "X",'X', Block.stone, '#', Item.coal
						});

		GameRegistry.addRecipe(
				new ItemStack(Block.oreEmerald), new Object[] 
						{
					"#", "X",'X', Block.stone, '#', Item.emerald
						});

		GameRegistry.addRecipe(
				new ItemStack(Block.oreGold), new Object[] 
						{
					"#", "X",'X', Block.stone, '#', Item.ingotGold
						});

		GameRegistry.addRecipe(
				new ItemStack(Block.oreIron), new Object[] 
						{
					"#", "X",'X', Block.stone, '#', Item.ingotIron
						});

		GameRegistry.addRecipe(
				new ItemStack(Block.oreRedstone), new Object[] 
						{
					"#", "X",'X', Block.stone, '#', Item.redstone
						});

		GameRegistry.addRecipe(
				new ItemStack(Block.oreLapis), new Object[] 
						{
					"#", "X",'X', Block.stone, '#', new ItemStack(Item.dyePowder, 1)
						});

		GameRegistry.addRecipe(
				new ItemStack(Block.oreNetherQuartz), new Object[] 
						{
					"#", "X",'X', Block.netherrack, '#', Item.netherQuartz
						});
	}
}

