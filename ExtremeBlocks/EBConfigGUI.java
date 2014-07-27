package main.extremeblocks;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.config.GuiConfig;

public class EBConfigGUI extends GuiConfig
{
	public EBConfigGUI(GuiScreen parent) 
	{
		super(parent, new ConfigElement(ExtremeBlocks.configFile.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
				Init.MODID, false, false, GuiConfig.getAbridgedConfigPath(ExtremeBlocks.configFile.toString()));
	}
}
