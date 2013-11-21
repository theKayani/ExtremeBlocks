package ExtremeBlocks;

import ExtremeBlocks.Blocks.BlockBoneDirt;
import ExtremeBlocks.Blocks.BlockBronzeBlock;
import ExtremeBlocks.Blocks.BlockCementWall;
import ExtremeBlocks.Blocks.BlockCopperOre;
import ExtremeBlocks.Blocks.BlockDelvlishOre;
import ExtremeBlocks.Blocks.BlockEmptiedLog;
import ExtremeBlocks.Blocks.BlockExtaOrdinaryStone;
import ExtremeBlocks.Blocks.BlockFakeSands;
import ExtremeBlocks.Blocks.BlockGlesterOre;
import ExtremeBlocks.Blocks.BlockLightedBrick;
import ExtremeBlocks.Blocks.BlockLightedDiamondBlock;
import ExtremeBlocks.Blocks.BlockLightedDirt;
import ExtremeBlocks.Blocks.BlockLightedIronBlock;
import ExtremeBlocks.Blocks.BlockLightedPlank;
import ExtremeBlocks.Blocks.BlockLightedStone;
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
import ExtremeBlocks.Blocks.BlockOffBrick;
import ExtremeBlocks.Blocks.BlockOffDiamondBlock;
import ExtremeBlocks.Blocks.BlockOffDirt;
import ExtremeBlocks.Blocks.BlockOffGlass;
import ExtremeBlocks.Blocks.BlockOffIronBlock;
import ExtremeBlocks.Blocks.BlockOffPlank;
import ExtremeBlocks.Blocks.BlockOffStone;
import ExtremeBlocks.Blocks.BlockPlasterWall;
import ExtremeBlocks.Blocks.BlockSilverBlock;
import ExtremeBlocks.Blocks.BlockSilverOre;
import ExtremeBlocks.Blocks.BlockTinOre;
import ExtremeBlocks.Blocks.BlockTrinquantiumBlock;
import ExtremeBlocks.Blocks.BlockTrinquantiumOre;
import ExtremeBlocks.Blocks.BlockWeakCementWall;
import ExtremeBlocks.Blocks.BlockXRayBlock;
import ExtremeBlocks.Blocks.BlockXRayBlockUn;
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
import ExtremeBlocks.Items.ItemReturner;
import ExtremeBlocks.Items.ItemReturnerPS3;
import ExtremeBlocks.Items.ItemSap;
import ExtremeBlocks.Items.ItemSilverAxe;
import ExtremeBlocks.Items.ItemSilverBar;
import ExtremeBlocks.Items.ItemSilverHoe;
import ExtremeBlocks.Items.ItemSilverMedal;
import ExtremeBlocks.Items.ItemSilverPickaxe;
import ExtremeBlocks.Items.ItemSilverSpade;
import ExtremeBlocks.Items.ItemSilverSword;
import ExtremeBlocks.Items.ItemTester;
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
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Registry 
{
	public static EnumToolMaterial TRINQUANTIUM = EnumHelper.addToolMaterial("Trinquantium", 4, 2368, 10.0F, 4.0F, 15);
	public static EnumToolMaterial BRONZE = EnumHelper.addToolMaterial("Bronze", 2, 328, 5.0F, 3.0F, 12);
	public static EnumToolMaterial SILVER = EnumHelper.addToolMaterial("Silver", 0, 13, 6.0F, 12.0F, 7);
	public static EnumToolMaterial GLESTER = EnumHelper.addToolMaterial("Glester", 0, 71, 9.0F, 0.0F, 7);
	public static EnumToolMaterial DELVLISH = EnumHelper.addToolMaterial("Delvlish", 0, 71, 0.0F, 9.0F, 7);

	public static ExtremeBlocksMain Main;

	public static int GlesterOreID = 2000;
	public static int SilverOreID = 2001;
	public static int TrinquantiumOreID = 2002;
	public static int DelvlishOreID = 2003;
	public static int ExtraOrdinaryStoneID = 2004;
	public static int TrinquantiumBlockID = 2005;
	public static int CopperOreID = 2006;
	public static int TinOreID = 2007;
	public static int SilverBlockID = 2008;
	public static int BronzeBlockID = 2009;
	public static int LightedStoneID = 2010;
	public static int LightedPlankID = 2011;
	public static int LightedBrickID = 2012;
	public static int XRayBlockID = 2013;
	public static int XRayBlockUnID = 2014;
	public static int NetherSilverID = 2015;
	public static int NetherCopperID = 2016;
	public static int NetherTinID = 2017;
	public static int NetherDelvlishID = 2018;
	public static int NetherGlesterID = 2019;
	public static int NetherDiamondID = 2020;
	public static int NetherCoalID = 2021;
	public static int NetherGoldID = 2022;
	public static int NetherEmeraldID = 2023;
	public static int NetherIronID = 2024;
	public static int NetherTrinquantiumID = 2025;
	public static int CementWallID = 2026;
	public static int PlasterWallID = 2027;
	public static int WeakCementWallID = 2028;
	public static int EmptiedLogID = 2029;
	public static int BoneDirtID = 2030;
	public static int OffStoneID = 2031;
	public static int OffPlankID = 2032;
	public static int OffBrickID = 2033;
	public static int OffDirtID = 2034;
	public static int LightedDirtID = 2035;
	public static int WasteID = 2036;
	public static int OffIronBlockID = 2037;
	public static int OffDiamondBlockID = 2038;
	public static int OffGlassID = 2039;
	public static int LightedIronBlockID = 2040;
	public static int LightedDiamondBlockID = 2041;
	
	public static int SandedBrickID = 2095;
	public static int SandedBedrockID = 2096;
	public static int SandedPlankID = 2097;
	public static int SandedStoneID = 2098;
	public static int SandedGlassID = 2099;
	public static int SandedDirtID = 2100;




	public static int ExtremeBlocksID = 3840 - 255;
	public static int GoldCoinID = 3841 - 255; 
	public static int GlesterRockID = 3842 - 255;
	public static int DelvlishCrystalID = 3843 - 255;
	public static int SilverBarID = 3844 - 255;
	public static int TrinquantiumBarID = 3845 - 255;
	public static int CrushedStoneID = 3846 - 255;
	public static int BronzeMedalID = 3847 - 255;
	public static int SilverMedalID = 3848 - 255;
	public static int GoldMedalID = 3849 - 255;
	public static int TrinquantiumMedalID = 3850 - 255;
	public static int CopperID = 3851 - 255;
	public static int TinID = 3852 - 255;
	public static int CopperAndTinLumpID = 3853 - 255;
	public static int BronzeBarID = 3854 - 255;
	public static int TrinquantiumPickaxeID = 3855 - 255;
	public static int TrinquantiumAxeID = 3856 - 255;
	public static int TrinquantiumSpadeID = 3857 - 255;
	public static int TrinquantiumHoeID = 3858 - 255;
	public static int TrinquantiumSwordID = 3859 - 255;	
	public static int SilverPickaxeID = 3860 - 255;
	public static int SilverAxeID = 3861 - 255;
	public static int SilverSpadeID = 3862 - 255;
	public static int SilverHoeID = 3863 - 255;
	public static int SilverSwordID = 3864 - 255;	
	public static int BronzePickaxeID = 3865 - 255;
	public static int BronzeAxeID = 3866 - 255;
	public static int BronzeSpadeID = 3867 - 255;
	public static int BronzeHoeID = 3868 - 255;
	public static int BronzeSwordID = 3869 - 255;
	public static int SapID = 3870 - 255;
	public static int ExtractorID = 3871 - 255;
	public static int CellphoneID = 3872 - 255;
	public static int PlasticID = 3873 - 255;
	public static int ChipID = 3874 - 255;
	public static int BoneShardID = 3875 - 255;
	public static int BoneSwordID = 3876 - 255;
	public static int WrenchID = 3877 - 255;
	public static int PipesID = 3878 - 255;
	public static int PipeID = 3879 - 255;
	public static int BackpackID = 3880 - 255;
	public static int LightID = 3881 - 255;
	public static int CoreChipID = 3882 - 255;
	public static int GameRemoteID = 3883 - 255;
	public static int WeakenedGlesterRockID = 3884 - 255;
	public static int WeakenedDelvlishCrystalID = 3885 - 255;
	public static int DelvlishPickaxeID = 3886 - 255;
	public static int DelvlishAxeID = 3887 - 255;
	public static int DelvlishSpadeID = 3888 - 255;
	public static int DelvlishHoeID = 3889 - 255;
	public static int DelvlishSwordID = 3890 - 255;
	public static int GlesterPickaxeID = 3891 - 255;
	public static int GlesterAxeID = 3892 - 255;
	public static int GlesterSpadeID = 3893 - 255;
	public static int GlesterHoeID = 3894 - 255;
	public static int GlesterSwordID = 3895 - 255;
	public static int LimeStoneID = 3896 - 255;
	public static int ReturnerID = 3897 - 255;
	public static int ReturnerPS3ID = 3898 - 255;
	public static int TesterID = 3899 - 255;

	public static String CATEGORY_EB = "Extreme" + " " + "Blocks" + " " + "Mod";


	public static void RegisterBlock(Block block, String name1, String name2)
	{
		GameRegistry.registerBlock(block,name1);
		LanguageRegistry.addName(block, name2);
	}

	public static void RegisterItem(Item item, String name1, String name2)
	{
		GameRegistry.registerItem(item,name1);
		LanguageRegistry.addName(item, name2);
	}

	public static void addItemIDs(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		ConfigCategory catalog = new ConfigCategory(CATEGORY_EB);

		config.load();

		ExtremeBlocksID = config.getItem("ExtremeBlocks", 3841).getInt() - 256;
		GoldCoinID = config.getItem("GoldCoin", 3842).getInt() - 256; 
		GlesterRockID = config.getItem("GlesterRock", 3843).getInt() - 256;
		DelvlishCrystalID = config.getItem("DelvlishCrystal", 3844).getInt() - 256;
		SilverBarID = config.getItem("SilverBar", 3845).getInt() - 256;
		TrinquantiumBarID = config.getItem("TrinquantiumBar", 3846).getInt() - 256;
		CrushedStoneID = config.getItem("CrushedStone", 3847).getInt() - 256;
		BronzeMedalID = config.getItem("BronzeMedal", 3848).getInt() - 256;
		SilverMedalID = config.getItem("SilverMedal", 3849).getInt() - 256;
		GoldMedalID = config.getItem("GoldMedal", 3850).getInt() - 256;
		TrinquantiumMedalID = config.getItem("TrinquantiumMedal", 3851).getInt() - 256;
		CopperID = config.getItem("Copper", 3852).getInt() - 256;
		TinID = config.getItem("Tin", 3853).getInt() - 256;
		CopperAndTinLumpID = config.getItem("CopperAndTinLump", 3854).getInt() - 256;
		BronzeBarID = config.getItem("BronzeBar", 3855).getInt() - 256;	
		TrinquantiumPickaxeID = config.getItem("TrinquantiumPickaxe", 3856).getInt() - 256;
		TrinquantiumAxeID = config.getItem("TrinquantiumAxe", 3857).getInt() - 256;
		TrinquantiumSpadeID = config.getItem("TrinquantiumSpade", 3858).getInt() - 256;
		TrinquantiumHoeID = config.getItem("TrinquantiumHoe", 3859).getInt() - 256;
		TrinquantiumSwordID = config.getItem("TrinquantiumSword", 3860).getInt() - 256;	
		SilverPickaxeID = config.getItem("SilverPickaxe", 3861).getInt() - 256;
		SilverAxeID = config.getItem("SilverAxe", 3862).getInt() - 256;
		SilverSpadeID = config.getItem("SilverSpade", 3863).getInt() - 256;
		SilverHoeID = config.getItem("SilverHoe", 3864).getInt() - 256;
		SilverSwordID = config.getItem("SilverSword", 3865).getInt() - 256;	
		BronzePickaxeID = config.getItem("BronzePickaxe", 3866).getInt() - 256;
		BronzeAxeID = config.getItem("BronzeAxe", 3867).getInt() - 256;
		BronzeSpadeID = config.getItem("BronzeSpade", 3868).getInt() - 256;
		BronzeHoeID = config.getItem("BronzeHoe", 3869).getInt() - 256;
		BronzeSwordID = config.getItem("BronzeSword", 3870).getInt() - 256;
		SapID = config.getItem("Sap", 3871).getInt() - 256;
		ExtractorID = config.getItem("Tap", 3872).getInt() - 256;
		CellphoneID = config.getItem("Cellphone", 3873).getInt() - 256;
		PlasticID = config.getItem("Plastic", 3874).getInt() - 256;
		ChipID = config.getItem("Chip", 3875).getInt() - 256;
		BoneShardID  = config.getItem("BoneShard", 3876).getInt() - 256;
		BoneSwordID= config.getItem("BoneDagger", 3877).getInt() - 256;
		WrenchID = config.getItem("Wrench", 3878).getInt() - 256;
		PipesID = config.getItem("Pipes", 3879).getInt() - 256;
		PipeID = config.getItem("Pipe", 3880).getInt() - 256;
		BackpackID = config.getItem("Backpack", 3881).getInt() - 256;
		LightID = config.getItem("Light", 3882).getInt() - 256;
		CoreChipID = config.getItem("CoreChip", 3883).getInt() - 256;
		GameRemoteID = config.getItem("GameRemote", 3884).getInt() - 256;
		WeakenedGlesterRockID = config.getItem("WeakenedGlesterRock", 3885).getInt() - 256;
		WeakenedDelvlishCrystalID = config.getItem("WeakenedDelvlishCrystal", 3886).getInt() - 256;
		DelvlishPickaxeID = config.getItem("DelvlishPickaxe", 3887).getInt() - 256;
		DelvlishAxeID = config.getItem("DelvlishAxe", 3888).getInt() - 256;
		DelvlishSpadeID = config.getItem("DelvlishSpade", 3889).getInt() - 256;
		DelvlishHoeID = config.getItem("DelvlishHoe", 3890).getInt() - 256;
		DelvlishSwordID = config.getItem("DelvlishSword", 3891).getInt() - 256;
		GlesterPickaxeID = config.getItem("GlesterPickaxe", 3892).getInt() - 256;
		GlesterAxeID = config.getItem("GlesterAxe", 3893).getInt() - 256;
		GlesterSpadeID = config.getItem("GlesterSpade", 3894).getInt() - 256;
		GlesterHoeID = config.getItem("GlesterHoe", 3895).getInt() - 256;
		GlesterSwordID = config.getItem("GlesterSword", 3896).getInt() - 256;
		LimeStoneID = config.getItem("Limestone", 3897).getInt() - 256;
		ReturnerID = config.getItem("Returner", 3898).getInt() - 256;
		ReturnerPS3ID = config.getItem("ReturnerPS3", 3899).getInt() - 256;
		TesterID = config.getItem("Tester", 3900).getInt() - 256;

		config.save();
	}

	public static void addBlockIDs(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		config.load();

		
		GlesterOreID = config.getBlock("GlesterOre", 2000).getInt();
		SilverOreID = config.getBlock("SilverOre", 2001).getInt();
		TrinquantiumOreID = config.getBlock("TrinquantiumOre", 2002).getInt();
		DelvlishOreID = config.getBlock("DelvlishOre", 2003).getInt();
		ExtraOrdinaryStoneID = config.getBlock("ExtraOrdinaryStone", 2004).getInt();
		TrinquantiumBlockID = config.getBlock("TrinquantiumBlock", 2005).getInt();
		CopperOreID = config.getBlock("CopperOre", 2006).getInt();
		TinOreID = config.getBlock("TinOre", 2007).getInt();
		SilverBlockID = config.getBlock("SilverBlock", 2008).getInt();
		BronzeBlockID = config.getBlock("BronzeBlock", 2009).getInt();
		LightedStoneID = config.getBlock("LightedStone", 2010).getInt();
		LightedPlankID = config.getBlock("LightedPlank", 2011).getInt();
		LightedBrickID = config.getBlock("LightedBrick", 2012).getInt();
		XRayBlockID = config.getBlock("XRayBlock", 2013).getInt();
		XRayBlockUnID = config.getBlock("XRayBlockUn", 2014).getInt();
		NetherSilverID = config.getBlock("NetherSilver", 2015).getInt();
		NetherCopperID = config.getBlock("NetherCopper", 2016).getInt();
		NetherTinID = config.getBlock("NetherTin", 2017).getInt();
		NetherDelvlishID = config.getBlock("NetherDelvlish", 2018).getInt();
		NetherGlesterID = config.getBlock("NetherGlester", 2019).getInt();
		NetherDiamondID = config.getBlock("NetherDiamond", 2020).getInt();
		
		 NetherCoalID = config.getBlock("NetherCoal", 2021).getInt();
		 NetherGoldID = config.getBlock("NetherGold", 2022).getInt();
		 NetherEmeraldID = config.getBlock("NetherEmerald", 2023).getInt();
		 NetherIronID = config.getBlock("NetherIron", 2024).getInt();
		 NetherTrinquantiumID = config.getBlock("NetherTrinquantium", 2025).getInt();
		 CementWallID = config.getBlock("CementWall", 2026).getInt();
		 PlasterWallID = config.getBlock("PlasterWall", 2027).getInt();
		 WeakCementWallID = config.getBlock("WeakCementWall", 2028).getInt();
		 EmptiedLogID = config.getBlock("EmptiedLog", 2029).getInt();
		 BoneDirtID = config.getBlock("BoneDirt", 2030).getInt();
		 OffStoneID = config.getBlock("OffStone", 2031).getInt();
		 OffPlankID = config.getBlock("OffPlank", 2032).getInt();
		 OffBrickID = config.getBlock("OffBrick", 2033).getInt();
		 OffDirtID = config.getBlock("OffDirt", 2034).getInt();
		 LightedDirtID = config.getBlock("LightedDirt", 2035).getInt();
		 WasteID = config.getBlock("Waste", 2036).getInt();
		 OffIronBlockID = config.getBlock("OffIronBlock", 2037).getInt();
		 OffDiamondBlockID = config.getBlock("OffDiamondBlock", 2038).getInt();
		 OffGlassID = config.getBlock("OffGlass", 2039).getInt();
		 LightedIronBlockID = config.getBlock("LightedIronBlock", 2040).getInt();
		 LightedDiamondBlockID = config.getBlock("LightedDiamondBlock", 2020).getInt();
		
		SandedBrickID = config.getBlock("SandedBrick", 2095).getInt();
		SandedBedrockID = config.getBlock("SandedBedrock", 2096).getInt();
		SandedPlankID = config.getBlock("SandedPlank", 2097).getInt();
		SandedStoneID = config.getBlock("SandedStone", 2098).getInt();
		SandedGlassID = config.getBlock("SandedGlass", 2099).getInt();
		SandedDirtID = config.getBlock("SandedDirt", 2100).getInt();

		config.save();
	}
	public static void addExtraConfigOptions(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		config.load();

		//Vars.CFRestartXbox = config.get(CATEGORY_EB,"RestartXbox", false, "Restart the Xbox Game. WARNING: Is Glitchy").getBoolean(false);
		//Vars.CFRestartPS3 = config.get(CATEGORY_EB,"RestartPS3", false, "Restart the PS3 Game. WARNING: Is Glitchy").getBoolean(false);	
		Vars.CFXrayBlockOn = config.get(CATEGORY_EB,"XrayBlockOn", false, "Set true for it to not work").getBoolean(false);
		Vars.CFAlterWorld = config.get(CATEGORY_EB,"AlterWorld", true, "Do you want the Mod to generate things in the world").getBoolean(true);
		Vars.CFUseMaterials = config.get(CATEGORY_EB,"UseMaterials", true, "Do you want the Mods Tool Materials or vanilla Materials").getBoolean(true);
		Vars.CFBedrockBlocks = config.get(CATEGORY_EB,"BedrockBlocks", true, "Do you want the Mods Blocks made out of Bedrock to break").getBoolean(true);
		//Vars.CFDoGamesWork = config.get(CATEGORY_EB,"DoGamesWork", false, "Do the PS3 and Xbox Games work").getBoolean(false);
		
		Vars.CFEnableConfig = config.get(CATEGORY_EB,"EnableConfig", false, "Restart the game for it to work").getBoolean(false);

		//config.addCustomCategoryComment(CATEGORY_EB, "Enable Config to Activate Options!");
		config.addCustomCategoryComment(CATEGORY_EB, "True or False for Game Options! Play around!");	
		config.save();
	}

	public static void addIDs(FMLPreInitializationEvent event)
	{
		addBlockIDs(event);
		addItemIDs(event);
		addExtraConfigOptions(event);
	}

	public static void addItems()
	{
		Main.TrinquantiumPickaxe = new ItemTrinquantiumPickaxe(TrinquantiumPickaxeID, Vars.getTrinquantium());
		Main.TrinquantiumAxe = new ItemTrinquantiumAxe(TrinquantiumAxeID, Vars.getTrinquantium());
		Main.TrinquantiumSpade = new ItemTrinquantiumSpade(TrinquantiumSpadeID, Vars.getTrinquantium());
		Main.TrinquantiumHoe = new ItemTrinquantiumHoe(TrinquantiumHoeID, Vars.getTrinquantium());
		Main.TrinquantiumSword = new ItemTrinquantiumSword(TrinquantiumSwordID, Vars.getTrinquantium());
		Main.SilverPickaxe = new ItemSilverPickaxe(SilverPickaxeID, Vars.getSilver());
		Main.SilverAxe = new ItemSilverAxe(SilverAxeID, Vars.getSilver());
		Main.SilverSpade = new ItemSilverSpade(SilverSpadeID, Vars.getSilver());
		Main.SilverHoe = new ItemSilverHoe(SilverHoeID, Vars.getSilver());
		Main.SilverSword = new ItemSilverSword(SilverSwordID, Vars.getSilver());
		Main.BronzePickaxe = new ItemBronzePickaxe(BronzePickaxeID, Vars.getBronze());
		Main.BronzeAxe = new ItemBronzeAxe(BronzeAxeID, Vars.getBronze());
		Main.BronzeSpade = new ItemBronzeSpade(BronzeSpadeID, Vars.getBronze());
		Main.BronzeHoe = new ItemBronzeHoe(BronzeHoeID, Vars.getBronze());
		Main.BronzeSword = new ItemBronzeSword(BronzeSwordID, Vars.getBronze());
		Main.Sap = new ItemSap(SapID);
		Main.Extractor = new ItemExtractor(ExtractorID);
		Main.Cellphone = new ItemCellphone(CellphoneID);
		Main.Plastic = new ItemPlastic(PlasticID);
		Main.Chip = new ItemChip(ChipID);
		Main.BoneShard = new ItemBoneShard(BoneShardID);
		Main.BoneSword = new ItemBoneSword(BoneSwordID, Vars.getTrinquantium());
		Main.Wrench = new ItemWrench(WrenchID);	
		Main.ExtremeBlocks = new ItemExtremeBlocks(ExtremeBlocksID);
		Main.GoldCoin = new ItemGoldCoin(GoldCoinID); 
		Main.GlesterRock = new ItemGlesterRock(GlesterRockID);
		Main.DelvlishCrystal = new ItemDelvlishCrystal(DelvlishCrystalID);
		Main.SilverBar = new ItemSilverBar(SilverBarID);
		Main.TrinquantiumBar = new ItemTrinquantiumBar(TrinquantiumBarID);
		Main.CrushedStone = new ItemCrushedStone(CrushedStoneID);
		Main.BronzeMedal = new ItemBronzeMedal(BronzeMedalID);
		Main.SilverMedal = new ItemSilverMedal(SilverMedalID);
		Main.GoldMedal = new ItemGoldMedal(GoldMedalID);
		Main.TrinquantiumMedal = new ItemTrinquantiumMedal(TrinquantiumMedalID);
		Main.Copper = new ItemCopper(CopperID);
		Main.Tin = new ItemTin(TinID);
		Main.CopperAndTinLump = new ItemCopperAndTinLump(CopperAndTinLumpID);
		Main.BronzeBar = new ItemBronzeBar(BronzeBarID);
		Main.Pipes = new ItemPipes(PipesID);
		Main.Pipe = new ItemPipe(PipeID);
		Main.Backpack = new ItemBackPack(BackpackID);
		Main.Light = new ItemLight(LightID);
		Main.CoreChip = new ItemCoreChip(CoreChipID);
		Main.GameRemote = new ItemGameRemote(GameRemoteID);	
		Main.WeakenedGlesterRock = new ItemWeakenedGlesterRock(WeakenedGlesterRockID);
		Main.WeakenedDelvlishCrystal = new ItemWeakenedDelvlishCrystal(WeakenedDelvlishCrystalID);
		Main.DelvlishPickaxe = new ItemDelvlishPickaxe(DelvlishPickaxeID, Vars.getDelvlish());
		Main.DelvlishAxe = new ItemDelvlishAxe(DelvlishAxeID, Vars.getDelvlish());
		Main.DelvlishSpade = new ItemDelvlishSpade(DelvlishSpadeID, Vars.getDelvlish());
		Main.DelvlishHoe = new ItemDelvlishHoe(DelvlishHoeID, Vars.getDelvlish());
		Main.DelvlishSword = new ItemDelvlishSword(DelvlishSwordID, Vars.getDelvlish());
		Main.GlesterPickaxe = new ItemGlesterPickaxe(GlesterPickaxeID, Vars.getGlester());
		Main.GlesterAxe = new ItemGlesterAxe(GlesterAxeID, Vars.getGlester());
		Main.GlesterSpade = new ItemGlesterSpade(GlesterSpadeID, Vars.getGlester());
		Main.GlesterHoe = new ItemGlesterHoe(GlesterHoeID, Vars.getGlester());
		Main.GlesterSword = new ItemGlesterSword(GlesterSwordID, Vars.getGlester());
		Main.Limestone = new ItemLimeStone(LimeStoneID);
		Main.Returner = new ItemReturner(ReturnerID);
		Main.ReturnerPS3 = new ItemReturnerPS3(ReturnerPS3ID);
		Main.Tester = new ItemTester(TesterID);
	}

	public static void addBlocks()
	{
		Main.GlesterOre = new BlockGlesterOre(GlesterOreID, Material.rock);
		Main.SilverOre = new BlockSilverOre(SilverOreID, Material.rock);
		Main.TrinquantiumOre = new BlockTrinquantiumOre(TrinquantiumOreID, Material.rock);
		Main.DelvlishOre = new BlockDelvlishOre(DelvlishOreID, Material.rock);
		Main.ExtraOrdinaryStone = new BlockExtaOrdinaryStone(ExtraOrdinaryStoneID, Material.rock);
		Main.TrinquantiumBlock = new BlockTrinquantiumBlock(TrinquantiumBlockID, Material.iron);
		Main.CopperOre = new BlockCopperOre(CopperOreID, Material.rock);
		Main.TinOre = new BlockTinOre(TinOreID, Material.rock);
		Main.SilverBlock = new BlockSilverBlock(SilverBlockID, Material.iron);
		Main.BronzeBlock = new BlockBronzeBlock(BronzeBlockID, Material.iron);
		Main.LightedStone = new BlockLightedStone(LightedStoneID, Material.rock);
		Main.LightedPlank = new BlockLightedPlank(LightedPlankID, Material.wood);
		Main.LightedBrick = new BlockLightedBrick(LightedBrickID, Material.rock);
		Main.XRayBlock = new BlockXRayBlock(XRayBlockID, Material.glass);
		Main.XRayBlockUn = new BlockXRayBlockUn(XRayBlockUnID, Material.glass);
		Main.NetherSilver = new BlockNetherSilver(NetherSilverID, Material.rock);
		Main.NetherCopper = new BlockNetherCopper(NetherCopperID, Material.rock);
		Main.NetherTin = new BlockNetherTin(NetherTinID, Material.rock);
		Main.NetherDelvlish = new BlockNetherDelvlish(NetherDelvlishID, Material.rock);
		Main.NetherGlester = new BlockNetherGlester(NetherGlesterID, Material.rock);
		Main.NetherDiamond = new BlockNetherDiamond(NetherDiamondID, Material.rock);	
		Main.NetherCoal = new BlockNetherCoal(NetherCoalID, Material.rock);
		Main.NetherGold = new BlockNetherGold(NetherGoldID, Material.rock);
		Main.NetherEmerald = new BlockNetherEmerald(NetherEmeraldID, Material.rock);
		Main.NetherIron = new BlockNetherIron(NetherIronID, Material.rock);
		Main.NetherTrinquantium = new BlockNetherTrinquantium(NetherTrinquantiumID, Material.rock);
		Main.CementWall = new BlockCementWall(CementWallID, Material.rock);
		Main.PlasterWall = new BlockPlasterWall(PlasterWallID, Material.rock);
		Main.WeakCementWall = new BlockWeakCementWall(WeakCementWallID, Material.rock);
		Main.EmptiedLog = new BlockEmptiedLog(EmptiedLogID, Material.wood);
		Main.BoneDirt = new BlockBoneDirt(BoneDirtID, Material.ground);
		Main.OffStone = new BlockOffStone(OffStoneID, Material.rock);
		Main.OffPlank = new BlockOffPlank(OffPlankID, Material.wood);
		Main.OffBrick = new BlockOffBrick(OffBrickID, Material.rock);
		Main.OffDirt = new BlockOffDirt(OffDirtID, Material.ground);
		Main.LightedDirt = new BlockLightedDirt(LightedDirtID, Material.ground);
		Main.Waste = new BlockNuclearWaste(WasteID, Material.lava);
		Main.OffIronBlock = new BlockOffIronBlock(OffIronBlockID, Material.iron);
		Main.OffDiamondBlock = new BlockOffDiamondBlock(OffDiamondBlockID, Material.iron);
		Main.OffGlass = new BlockOffGlass(OffGlassID, Material.glass);
		Main.LightedIronBlock = new BlockLightedIronBlock(LightedIronBlockID, Material.iron);
		Main.LightedDiamondBlock = new BlockLightedDiamondBlock(LightedDiamondBlockID, Material.iron);
		
		Main.SandedBrick = new BlockFakeSands(SandedBrickID, Material.rock, "Brick", Block.soundStoneFootstep);
		Main.SandedBedrock = new BlockFakeSands(SandedBedrockID, Material.rock, "Bedrock", Block.soundStoneFootstep);
		Main.SandedPlank = new BlockFakeSands(SandedPlankID, Material.wood, "Plank", Block.soundWoodFootstep);
		Main.SandedStone = new BlockFakeSands(SandedStoneID, Material.rock, "Stone", Block.soundStoneFootstep);
		Main.SandedGlass = new BlockFakeSands(SandedGlassID, Material.glass, "Glass", Block.soundGlassFootstep);
		Main.SandedDirt = new BlockFakeSands(SandedDirtID, Material.ground, "Dirt", Block.soundGrassFootstep);
	}
}
