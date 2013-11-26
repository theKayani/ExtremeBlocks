package ExtremeBlocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Vars 
{	
	public static ExtremeBlocksMain Main;
	
	//Console- Start	
	public static int x;
	public static int y;
	public static int z;
	public static int timesTried;
	
	public static boolean isSure = false;
	public static boolean startedGame = false;
	public static boolean playing = false;
	public static boolean gotReward = false;
	public static boolean isStarting = false;
	public static boolean isCheated = false;
	//Console- End
	
	//Config- Start
	public static boolean CFRestartConsole;
	public static boolean CFXrayBlockOn;
	public static boolean CFAlterWorld;
	public static boolean CFUseMaterials;
	public static boolean CFBedrockBlocks;
	public static boolean CFDoGamesWork;
	public static boolean CFEnableConfig;
	
	public static int CFOreSpawnRateCaTaA;
	public static int CFOreSpawnRateDaGaL;
	public static int CFOreSpawnRateBaS;
	public static int CFOreSpawnRateT;
	public static int CFGameDifficulty;
	
	public static String CFCounterMessage;	
	//Config- End

	
	//Functions- Start
	public static EnumToolMaterial getTrinquantium()
	{
		if(CFUseMaterials)
		{
			return Registry.TRINQUANTIUM;
		}
		else if(!CFUseMaterials)
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
		else if(!CFUseMaterials)
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
		else if(!CFUseMaterials)
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
		else if(!CFUseMaterials)
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
		else if(!CFUseMaterials)
		{
			return EnumToolMaterial.GOLD;
		}
		return Vars.getGlester();
	}
	
	public static boolean isAllowed(int number, int min, int max)
	{
		if(number < min || number > max)
		{
			return false;
		}
		else if(number <= max && number >= min)
		{
			return true;
		}
		return Vars.isAllowed(number, min, max);
	}
	
	public static int isNumber(int number, int min, int max)
	{
		if(number < min)
		{
			return min + 1;
		}
		else if(number > max)
		{
			return max - 1;
		}
		else if(number <= max && number >= min)
		{
			return number;
		}
		return Vars.isNumber(number, min, max);
	}
	
	//Functions- End
}
