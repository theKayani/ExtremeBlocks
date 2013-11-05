package ExtremeBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;
import ExtremeBlocks.Blocks.BlockAesthetic;
import ExtremeBlocks.Blocks.BlockBoneDirt;
import ExtremeBlocks.Blocks.BlockBronzeBlock;
import ExtremeBlocks.Blocks.BlockCementWall;
import ExtremeBlocks.Blocks.BlockCopperOre;
import ExtremeBlocks.Blocks.BlockDelvlishOre;
import ExtremeBlocks.Blocks.BlockDriedSapling;
import ExtremeBlocks.Blocks.BlockDrill;
import ExtremeBlocks.Blocks.BlockDrillPole;
import ExtremeBlocks.Blocks.BlockEmptiedLog;
import ExtremeBlocks.Blocks.BlockExtaOrdinaryStone;
import ExtremeBlocks.Blocks.BlockFakeGravel;
import ExtremeBlocks.Blocks.BlockFakeSand;
import ExtremeBlocks.Blocks.BlockFences;
import ExtremeBlocks.Blocks.BlockFireHydrant;
import ExtremeBlocks.Blocks.BlockGlesterOre;
import ExtremeBlocks.Blocks.BlockLantern;
import ExtremeBlocks.Blocks.BlockLightedBedrock;
import ExtremeBlocks.Blocks.BlockLightedBlackWool;
import ExtremeBlocks.Blocks.BlockLightedBrick;
import ExtremeBlocks.Blocks.BlockLightedBricks;
import ExtremeBlocks.Blocks.BlockLightedClay;
import ExtremeBlocks.Blocks.BlockLightedCobblestone;
import ExtremeBlocks.Blocks.BlockLightedDiamondBlock;
import ExtremeBlocks.Blocks.BlockLightedDirt;
import ExtremeBlocks.Blocks.BlockLightedEmeraldBlock;
import ExtremeBlocks.Blocks.BlockLightedEndstone;
import ExtremeBlocks.Blocks.BlockLightedGlass;
import ExtremeBlocks.Blocks.BlockLightedGoldBlock;
import ExtremeBlocks.Blocks.BlockLightedIronBlock;
import ExtremeBlocks.Blocks.BlockLightedLapisBlock;
import ExtremeBlocks.Blocks.BlockLightedMossyCobblestone;
import ExtremeBlocks.Blocks.BlockLightedNetherBrick;
import ExtremeBlocks.Blocks.BlockLightedNetherrack;
import ExtremeBlocks.Blocks.BlockLightedObsidian;
import ExtremeBlocks.Blocks.BlockLightedPlank;
import ExtremeBlocks.Blocks.BlockLightedStone;
import ExtremeBlocks.Blocks.BlockLightedWhiteWool;
import ExtremeBlocks.Blocks.BlockLimestone;
import ExtremeBlocks.Blocks.BlockLimestoneBlock;
import ExtremeBlocks.Blocks.BlockMarble;
import ExtremeBlocks.Blocks.BlockNetherCoal;
import ExtremeBlocks.Blocks.BlockNetherCopper;
import ExtremeBlocks.Blocks.BlockNetherDelvlish;
import ExtremeBlocks.Blocks.BlockNetherDiamond;
import ExtremeBlocks.Blocks.BlockNetherEmerald;
import ExtremeBlocks.Blocks.BlockNetherGlester;
import ExtremeBlocks.Blocks.BlockNetherGold;
import ExtremeBlocks.Blocks.BlockNetherIron;
import ExtremeBlocks.Blocks.BlockNetherSilver;
import ExtremeBlocks.Blocks.BlockNetherTin;
import ExtremeBlocks.Blocks.BlockNetherTrinquantium;
import ExtremeBlocks.Blocks.BlockNuclearWaste;
import ExtremeBlocks.Blocks.BlockOffBedrock;
import ExtremeBlocks.Blocks.BlockOffBlackWool;
import ExtremeBlocks.Blocks.BlockOffBrick;
import ExtremeBlocks.Blocks.BlockOffBricks;
import ExtremeBlocks.Blocks.BlockOffClay;
import ExtremeBlocks.Blocks.BlockOffCobblestone;
import ExtremeBlocks.Blocks.BlockOffDiamondBlock;
import ExtremeBlocks.Blocks.BlockOffDirt;
import ExtremeBlocks.Blocks.BlockOffEmeraldBlock;
import ExtremeBlocks.Blocks.BlockOffEndstone;
import ExtremeBlocks.Blocks.BlockOffGlass;
import ExtremeBlocks.Blocks.BlockOffGoldBlock;
import ExtremeBlocks.Blocks.BlockOffIronBlock;
import ExtremeBlocks.Blocks.BlockOffLapisBlock;
import ExtremeBlocks.Blocks.BlockOffMossyCobblestone;
import ExtremeBlocks.Blocks.BlockOffNetherBrick;
import ExtremeBlocks.Blocks.BlockOffNetherrack;
import ExtremeBlocks.Blocks.BlockOffObsidian;
import ExtremeBlocks.Blocks.BlockOffPlank;
import ExtremeBlocks.Blocks.BlockOffStone;
import ExtremeBlocks.Blocks.BlockOffWhiteWool;
import ExtremeBlocks.Blocks.BlockPS3;
import ExtremeBlocks.Blocks.BlockPlasterWall;
import ExtremeBlocks.Blocks.BlockSilverBlock;
import ExtremeBlocks.Blocks.BlockSilverOre;
import ExtremeBlocks.Blocks.BlockStonePillar;
import ExtremeBlocks.Blocks.BlockTinOre;
import ExtremeBlocks.Blocks.BlockTrinquantiumBlock;
import ExtremeBlocks.Blocks.BlockTrinquantiumOre;
import ExtremeBlocks.Blocks.BlockVendingMachine;
import ExtremeBlocks.Blocks.BlockWasteBin;
import ExtremeBlocks.Blocks.BlockWeakCementWall;
import ExtremeBlocks.Blocks.BlockXBox360;
import ExtremeBlocks.Blocks.BlockXRayBlock;
import ExtremeBlocks.Blocks.BlockXRayBlockUn;
import ExtremeBlocks.Blocks.ItemFencesBlock;
import ExtremeBlocks.Items.ItemBackPack;
import ExtremeBlocks.Items.ItemBoneShard;
import ExtremeBlocks.Items.ItemBoneSword;
import ExtremeBlocks.Items.ItemBronzeAxe;
import ExtremeBlocks.Items.ItemBronzeBar;
import ExtremeBlocks.Items.ItemBronzeHoe;
import ExtremeBlocks.Items.ItemBronzeMedal;
import ExtremeBlocks.Items.ItemBronzePickaxe;
import ExtremeBlocks.Items.ItemBronzeSpade;
import ExtremeBlocks.Items.ItemBronzeSword;
import ExtremeBlocks.Items.ItemCellphone;
import ExtremeBlocks.Items.ItemChip;
import ExtremeBlocks.Items.ItemCopper;
import ExtremeBlocks.Items.ItemCopperAndTinLump;
import ExtremeBlocks.Items.ItemCoreChip;
import ExtremeBlocks.Items.ItemCrushedStone;
import ExtremeBlocks.Items.ItemDelvlishAxe;
import ExtremeBlocks.Items.ItemDelvlishCrystal;
import ExtremeBlocks.Items.ItemDelvlishHoe;
import ExtremeBlocks.Items.ItemDelvlishPickaxe;
import ExtremeBlocks.Items.ItemDelvlishSpade;
import ExtremeBlocks.Items.ItemDelvlishSword;
import ExtremeBlocks.Items.ItemExtractor;
import ExtremeBlocks.Items.ItemExtremeBlocks;
import ExtremeBlocks.Items.ItemGameRemote;
import ExtremeBlocks.Items.ItemGlesterAxe;
import ExtremeBlocks.Items.ItemGlesterHoe;
import ExtremeBlocks.Items.ItemGlesterPickaxe;
import ExtremeBlocks.Items.ItemGlesterRock;
import ExtremeBlocks.Items.ItemGlesterSpade;
import ExtremeBlocks.Items.ItemGlesterSword;
import ExtremeBlocks.Items.ItemGoldCoin;
import ExtremeBlocks.Items.ItemGoldMedal;
import ExtremeBlocks.Items.ItemLight;
import ExtremeBlocks.Items.ItemLimeStone;
import ExtremeBlocks.Items.ItemPipe;
import ExtremeBlocks.Items.ItemPipes;
import ExtremeBlocks.Items.ItemPlastic;
import ExtremeBlocks.Items.ItemSap;
import ExtremeBlocks.Items.ItemSilverAxe;
import ExtremeBlocks.Items.ItemSilverBar;
import ExtremeBlocks.Items.ItemSilverHoe;
import ExtremeBlocks.Items.ItemSilverMedal;
import ExtremeBlocks.Items.ItemSilverPickaxe;
import ExtremeBlocks.Items.ItemSilverSpade;
import ExtremeBlocks.Items.ItemSilverSword;
import ExtremeBlocks.Items.ItemTin;
import ExtremeBlocks.Items.ItemTrinquantiumAxe;
import ExtremeBlocks.Items.ItemTrinquantiumBar;
import ExtremeBlocks.Items.ItemTrinquantiumHoe;
import ExtremeBlocks.Items.ItemTrinquantiumMedal;
import ExtremeBlocks.Items.ItemTrinquantiumPickaxe;
import ExtremeBlocks.Items.ItemTrinquantiumSpade;
import ExtremeBlocks.Items.ItemTrinquantiumSword;
import ExtremeBlocks.Items.ItemWeakenedDelvlishCrystal;
import ExtremeBlocks.Items.ItemWeakenedGlesterRock;
import ExtremeBlocks.Items.ItemWrench;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@NetworkMod(clientSideRequired = true, serverSideRequired = false)
@Mod(modid = ExtremeBlocksMain.modid, name = "Extreme Blocks", version = "2.9")

public class ExtremeBlocksMain 
{ 
	public static final String modid = "EB_ExtremeBlocks";
	public static EnumToolMaterial TRINQUANTIUM = EnumHelper.addToolMaterial("Trinquantium", 4, 2368, 10.0F, 4.0F, 15);
	public static EnumToolMaterial BRONZE = EnumHelper.addToolMaterial("Bronze", 2, 328, 5.0F, 3.0F, 12);
	public static EnumToolMaterial SILVER = EnumHelper.addToolMaterial("Silver", 0, 13, 6.0F, 12.0F, 7);
	public static EnumToolMaterial GLESTER = EnumHelper.addToolMaterial("Glester", 0, 71, 9.0F, 0.0F, 7);
	public static EnumToolMaterial DELVLISH = EnumHelper.addToolMaterial("Delvlish", 0, 71, 0.0F, 9.0F, 7);
	
    public static CreativeTabs EBBasicBlocksTab = new EBBlocks(CreativeTabs.getNextID(), "EBBlocks");
	public static CreativeTabs EBBasicItemsTab = new EBItems(CreativeTabs.getNextID(), "EBItems");
	public static CreativeTabs EBMiscTab = new EBMisc(CreativeTabs.getNextID(),"EBMisc");
	public static CreativeTabs EBOresTab = new EBOres(CreativeTabs.getNextID(),"EBOres");
	public static CreativeTabs EBToolsTab = new EBTools(CreativeTabs.getNextID(),"EBTools");
	public static CreativeTabs EBLightedBlocksTab = new EBLightedBlocks(CreativeTabs.getNextID(),"EBLightedBlocks");
	public static CreativeTabs EBFencesTab = new EBFences(CreativeTabs.getNextID(),"EBFences");

	EventManager eventmanager = new EventManager();
	public static ExtremeBlocksRegistry Registry;

	//Items
	public static final Item ExtremeBlocks = new ItemExtremeBlocks(3000);
	public static final Item GoldCoin = new ItemGoldCoin(3001); 
	public static final Item GlesterRock = new ItemGlesterRock(3002);
	public static final Item DelvlishCrystal = new ItemDelvlishCrystal(3003);
	public static final Item SilverBar = new ItemSilverBar(3004);
	public static final Item TrinquantiumBar = new ItemTrinquantiumBar(3005);
	public static final Item CrushedStone = new ItemCrushedStone(3006);
	public static final Item BronzeMedal = new ItemBronzeMedal(3007);
	public static final Item SilverMedal = new ItemSilverMedal(3008);
	public static final Item GoldMedal = new ItemGoldMedal(3009);
	public static final Item TrinquantiumMedal = new ItemTrinquantiumMedal(3010);
	public static final Item Copper = new ItemCopper(3011);
	public static final Item Tin = new ItemTin(3012);
	public static final Item CopperAndTinLump = new ItemCopperAndTinLump(3013);
	public static final Item BronzeBar = new ItemBronzeBar(3014);
	public static final Item TrinquantiumPickaxe = new ItemTrinquantiumPickaxe(3015, TRINQUANTIUM);
	public static final Item TrinquantiumAxe = new ItemTrinquantiumAxe(3016, TRINQUANTIUM);
	public static final Item TrinquantiumSpade = new ItemTrinquantiumSpade(3017, TRINQUANTIUM);
	public static final Item TrinquantiumHoe = new ItemTrinquantiumHoe(3018, TRINQUANTIUM);
	public static final Item TrinquantiumSword = new ItemTrinquantiumSword(3019, TRINQUANTIUM);
	public static final Item SilverPickaxe = new ItemSilverPickaxe(3020, SILVER);
	public static final Item SilverAxe = new ItemSilverAxe(3021, SILVER);
	public static final Item SilverSpade = new ItemSilverSpade(3022, SILVER);
	public static final Item SilverHoe = new ItemSilverHoe(3023, SILVER);
	public static final Item SilverSword = new ItemSilverSword(3024, SILVER);
	public static final Item BronzePickaxe = new ItemBronzePickaxe(3025, BRONZE);
	public static final Item BronzeAxe = new ItemBronzeAxe(3026, BRONZE);
	public static final Item BronzeSpade = new ItemBronzeSpade(3027, BRONZE);
	public static final Item BronzeHoe = new ItemBronzeHoe(3028, BRONZE);
	public static final Item BronzeSword = new ItemBronzeSword(3029, BRONZE);
	public static final Item Sap = new ItemSap(3030);
	public static final Item Extractor = new ItemExtractor(3031);
	public static final Item Cellphone = new ItemCellphone(3032);
	public static final Item Plastic = new ItemPlastic(3033);
	public static final Item Chip = new ItemChip(3034);
	public static final Item BoneShard = new ItemBoneShard(3035);
	public static final Item BoneSword = new ItemBoneSword(3036, TRINQUANTIUM);
	public static final Item Wrench = new ItemWrench(3037);
	public static final Item Pipes = new ItemPipes(3038);
	public static final Item Pipe = new ItemPipe(3039);
	public static final Item Backpack = new ItemBackPack(3040);
	public static final Item Light = new ItemLight(3041);
	public static final Item CoreChip = new ItemCoreChip(3042);
	public static final Item GameRemote = new ItemGameRemote(3043);
	public static final Item WeakenedGlesterRock = new ItemWeakenedGlesterRock(3044);
	public static final Item WeakenedDelvlishCrystal = new ItemWeakenedDelvlishCrystal(3045);
	public static final Item DelvlishPickaxe = new ItemDelvlishPickaxe(3046, DELVLISH);
	public static final Item DelvlishAxe = new ItemDelvlishAxe(3047, DELVLISH);
	public static final Item DelvlishSpade = new ItemDelvlishSpade(3048, DELVLISH);
	public static final Item DelvlishHoe = new ItemDelvlishHoe(3049, DELVLISH);
	public static final Item DelvlishSword = new ItemDelvlishSword(3050, DELVLISH);
	public static final Item GlesterPickaxe = new ItemGlesterPickaxe(3051, GLESTER);
	public static final Item GlesterAxe = new ItemGlesterAxe(3052, GLESTER);
	public static final Item GlesterSpade = new ItemGlesterSpade(3053, GLESTER);
	public static final Item GlesterHoe = new ItemGlesterHoe(3054, GLESTER);
	public static final Item GlesterSword = new ItemGlesterSword(3055, GLESTER);
	public static final Item Limestone = new ItemLimeStone(3056);

	//Blocks
	public static final Block GlesterOre = new BlockGlesterOre(4000, Material.rock);
	public static final Block SilverOre = new BlockSilverOre(4001, Material.rock);
	public static final Block TrinquantiumOre = new BlockTrinquantiumOre(4002, Material.rock);
	public static final Block DelvlishOre = new BlockDelvlishOre(4003, Material.rock);
	public static final Block ExtaOrdinaryStone = new BlockExtaOrdinaryStone(4004, Material.rock);
	public static final Block TrinquantiumBlock = new BlockTrinquantiumBlock(4005, Material.iron);
	public static final Block CopperOre = new BlockCopperOre(4006, Material.rock);
	public static final Block TinOre = new BlockTinOre(4007, Material.rock);
	public static final Block SilverBlock = new BlockSilverBlock(4008, Material.iron);
	public static final Block BronzeBlock = new BlockBronzeBlock(4009, Material.iron);
	public static final Block LightedStone = new BlockLightedStone(4010, Material.rock);
	public static final Block LightedPlank = new BlockLightedPlank(4011, Material.wood);
	public static final Block LightedBrick = new BlockLightedBrick(4012, Material.rock);
	public static final Block XRayBlock = new BlockXRayBlock(4013, Material.glass);
	public static final Block XRayBlockUn = new BlockXRayBlockUn(4014, Material.glass);
	public static final Block NetherSilver = new BlockNetherSilver(4015, Material.rock);
	public static final Block NetherCopper = new BlockNetherCopper(4016, Material.rock);
	public static final Block NetherTin = new BlockNetherTin(4017, Material.rock);
	public static final Block NetherDelvlish = new BlockNetherDelvlish(4018, Material.rock);
	public static final Block NetherGlester = new BlockNetherGlester(4019, Material.rock);
	public static final Block NetherDiamond = new BlockNetherDiamond(4020, Material.rock);
	public static final Block NetherCoal = new BlockNetherCoal(4021, Material.rock);
	public static final Block NetherGold = new BlockNetherGold(4022, Material.rock);
	public static final Block NetherEmerald = new BlockNetherEmerald(4023, Material.rock);
	public static final Block NetherIron = new BlockNetherIron(4024, Material.rock);
	public static final Block NetherTrinquantium = new BlockNetherTrinquantium(4025, Material.rock);
	public static final Block CementWall = new BlockCementWall(4026, Material.rock);
	public static final Block PlasterWall = new BlockPlasterWall(4027, Material.rock);
	public static final Block WeakCementWall = new BlockWeakCementWall(4028, Material.rock);
	public static final Block EmptiedLog = new BlockEmptiedLog(4029, Material.wood);
	public static final Block BoneDirt = new BlockBoneDirt(4030, Material.ground);
	public static final Block OffStone = new BlockOffStone(4031, Material.rock);
	public static final Block OffPlank = new BlockOffPlank(4032, Material.wood);
	public static final Block OffBrick = new BlockOffBrick(4033 , Material.rock);
	public static final Block OffDirt = new BlockOffDirt(4034, Material.ground);
	public static final Block LightedDirt = new BlockLightedDirt(4035, Material.ground);
	public static final Block Waste = new BlockNuclearWaste(4036, Material.lava);
	public static final Block OffIronBlock = new BlockOffIronBlock(4037, Material.iron);
	public static final Block OffDiamondBlock = new BlockOffDiamondBlock(4038 , Material.iron);
	public static final Block OffGlass = new BlockOffGlass(4039, Material.glass);
	public static final Block LightedIronBlock = new BlockLightedIronBlock(4040, Material.iron);
	public static final Block LightedDiamondBlock = new BlockLightedDiamondBlock(4041 , Material.iron);
	public static final Block LightedGlass = new BlockLightedGlass(4042, Material.glass);
	public static final Block LightedGoldBlock = new BlockLightedGoldBlock(4043, Material.iron);
	public static final Block OffGoldBlock = new BlockOffGoldBlock(4044, Material.iron);
	public static final Block FireHydrant = new BlockFireHydrant(4045, Material.iron);
	public static final Block Lantern = new BlockLantern(4046, Material.glass);
	public static final Block StonePillar = new BlockStonePillar(4047, Material.rock);
	public static final Block XBox360 = new BlockXBox360(4048, Material.rock);
	public static final Block PS3 = new BlockPS3(4049, Material.rock);
	public static final Block WasteBin = new BlockWasteBin(4050, Material.cactus);
	public static final Block VendingMachine = new BlockVendingMachine(4051, Material.iron);
	public static final Block DriedSapling = new BlockDriedSapling(4052, Material.plants);
	public static final Block LimestoneRock = new BlockLimestone(4053, Material.rock);
	public static final Block Marble = new BlockMarble(4054, Material.glass);
	public static final Block LimestoneBlock = new BlockLimestoneBlock(4055, Material.rock);	
	public static final Block OffObsidian = new BlockOffObsidian(4056, Material.wood);
	public static final Block LightedObsidian = new BlockLightedObsidian(4057, Material.wood);
	public static final Block OffClay = new BlockOffClay(4058, Material.clay);
	public static final Block LightedClay = new BlockLightedClay(4059, Material.clay);
	public static final Block OffBlackWool = new BlockOffBlackWool(4060, Material.cloth);
	public static final Block LightedBlackWool = new BlockLightedBlackWool(4061, Material.cloth);
	public static final Block OffWhiteWool = new BlockOffWhiteWool(4062, Material.cloth);
	public static final Block LightedWhiteWool = new BlockLightedWhiteWool(4063, Material.cloth);	
	public static final Block OffBedrock = new BlockOffBedrock(4064, Material.rock);
	public static final Block OffNetherrack = new BlockOffNetherrack(4065, Material.rock);
	public static final Block OffEmeraldBlock = new BlockOffEmeraldBlock(4066, Material.iron);
	public static final Block OffLapisBlock = new BlockOffLapisBlock(4067, Material.iron);
	public static final Block OffBricks = new BlockOffBricks(4068, Material.rock);
	public static final Block OffCobblestone = new BlockOffCobblestone(4069, Material.rock);
	public static final Block OffMossyCobblestone = new BlockOffMossyCobblestone(4070, Material.rock);
	public static final Block OffEndstone = new BlockOffEndstone(4071, Material.rock);
	public static final Block OffNetherBrick = new BlockOffNetherBrick(4072, Material.rock);	
	public static final Block LightedBedrock = new BlockLightedBedrock(4073, Material.rock);
	public static final Block LightedNetherrack = new BlockLightedNetherrack(4074, Material.rock);
	public static final Block LightedEmeraldBlock = new BlockLightedEmeraldBlock(4075, Material.iron);
	public static final Block LightedLapisBlock = new BlockLightedLapisBlock(4076, Material.iron);
	public static final Block LightedBricks = new BlockLightedBricks(4077, Material.rock);
	public static final Block LightedCobblestone = new BlockLightedCobblestone(4078, Material.rock);
	public static final Block LightedMossyCobblestone = new BlockLightedMossyCobblestone(4079, Material.rock);
	public static final Block LightedEndstone = new BlockLightedEndstone(4080, Material.rock);
	public static final Block LightedNetherBrick = new BlockLightedNetherBrick(4081, Material.rock);
	public static final Block FakeSand = new BlockFakeSand(4082, Material.sand);
	public static final Block FakeGravel = new BlockFakeGravel(4083, Material.ground);
	public static final Block Drill = new BlockDrill(4084, Material.iron);
	public static final Block DrillPole = new BlockDrillPole(4085, Material.iron);
	public static final Block Aesthetic = new BlockAesthetic(4086, Material.ground);
	public static final Block Fences = new BlockFences(4087, Material.wood);

	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		GameRegistry.registerBlock(Fences, ItemFencesBlock.class, modid + (Fences.getUnlocalizedName().substring(5)));
		GameRegistry.registerWorldGenerator(eventmanager);
		
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
		
		Block.commandBlock.setCreativeTab(EBMiscTab);
		
		gameRegistry();

		fenceRecipeRegistry();
		
		recipeRegistry();
	}
	private static void gameRegistry() 
	{
		//Block Registry
		Registry.RegisterBlock(GlesterOre, "GlesterOre", "Glester Ore");
		Registry.RegisterBlock(SilverOre, "SilverOre", "Silver Ore");
		Registry.RegisterBlock(TrinquantiumOre, "TrinquantiumOre", "Trinquantium Ore");
		Registry.RegisterBlock(DelvlishOre, "DelvlishOre", "Delvlish Ore");
		Registry.RegisterBlock(ExtaOrdinaryStone, "ExtaOrdinaryStone", "Extraordinary Stone");
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
		Registry.RegisterBlock(XBox360,"XBox360", "Xbox 360");
		Registry.RegisterBlock(PS3,"PS3", "PS3");
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
		Registry.RegisterItem(GameRemote, "GameRemote", "Game Remote");
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
	}
	
	private static void recipeRegistry()
	{	
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
				new ItemStack(ExtremeBlocksMain.XBox360), new Object[] 
						{
					"###", "#XB", "###", '#', ExtremeBlocksMain.Plastic, 'X', ExtremeBlocksMain.Chip, 'B', ExtremeBlocksMain.Light
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.GameRemote), new Object[] 
						{
					"###", "#X#", "# #", '#', ExtremeBlocksMain.Plastic, 'X', ExtremeBlocksMain.Chip
						});
		GameRegistry.addRecipe(
				new ItemStack(ExtremeBlocksMain.PS3), new Object[] 
						{
					"###", "#X#", "###", '#', ExtremeBlocksMain.Plastic, 'X', ExtremeBlocksMain.CoreChip
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
				new ItemStack(ExtremeBlocksMain.ExtaOrdinaryStone), new Object[] 
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
	private void fenceRecipeRegistry()
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
}

