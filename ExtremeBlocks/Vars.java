package ExtremeBlocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Vars 
{	
	public static ExtremeBlocksMain Main;
	
	//Xbox 360- Start
	public static int Gx;
	public static int Gy;
	public static int Gz;

	public static boolean startedGame = false;
	public static boolean playing = false;
	//Xbox 360- End


	//PS3- Start
	public static int x;
	public static int y;
	public static int z;

	public static int randomPS3;

	public static boolean startedGameps3 = false;
	public static boolean playingps3 = false;
	//PS3- End
	
	//Config- Start

	public static boolean CFRestartXbox = false;
	public static boolean CFRestartPS3 = false;
	public static boolean CFXrayBlockOn = false;
	public static boolean CFAlterWorld = true;
	public static boolean CFUseMaterials = true;
	public static boolean CFBedrockBlocks = true;
	public static boolean CFDoGamesWork = false;
	public static boolean CFEnableConfig = false;
	
	//Config- End
	
	//Functions- Start
	public static EnumToolMaterial getTrinquantium()
	{
		if(CFUseMaterials)
		{
			return Registry.TRINQUANTIUM;
		}
		if(!CFUseMaterials)
		{
			return EnumToolMaterial.EMERALD;
		}
		return Vars.getTrinquantium();
	}
	
	public static EnumToolMaterial getSilver()
	{
		if(CFUseMaterials)
		{
			return Registry.SILVER;
		}
		if(!CFUseMaterials)
		{
			return EnumToolMaterial.IRON;
		}
		return Vars.getSilver();
	}
	
	public static EnumToolMaterial getBronze()
	{
		if(CFUseMaterials)
		{
			return Registry.BRONZE;
		}
		if(!CFUseMaterials)
		{
			return EnumToolMaterial.STONE;
		}
		return Vars.getBronze();
	}
	
	public static EnumToolMaterial getDelvlish()
	{
		if(CFUseMaterials)
		{
			return Registry.DELVLISH;
		}
		if(!CFUseMaterials)
		{
			return EnumToolMaterial.GOLD;
		}
		return Vars.getDelvlish();
	}
	
	public static EnumToolMaterial getGlester()
	{
		if(CFUseMaterials)
		{
			return Registry.GLESTER;
		}
		if(!CFUseMaterials)
		{
			return EnumToolMaterial.GOLD;
		}
		return Vars.getGlester();
	}
	
	//Functions- End
}
