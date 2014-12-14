package main.extremeblocks;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import cpw.mods.fml.client.config.GuiConfig;

@SuppressWarnings("unchecked")
public class ConfigScreen extends GuiScreen
{
	private GuiScreen parentScreen;

	public ConfigScreen(GuiScreen parent)
	{
		parentScreen = parent;
	}

	@Override
	public void initGui()
	{
		buttonList.add(new GuiButton(2000, width / 2 - 100, height / 6 + 168, "Done"));
		// Add 24 for the next Row!
		buttonList.add(new GuiButton(2001, width / 2 - 100, height / 6 + 42, "General Config"));
		buttonList.add(new GuiButton(2002, width / 2 - 100, height / 6 + 66, "Ores Spawn Rate Config"));
		buttonList.add(new GuiButton(2003, width / 2 - 100, height / 6 + 90, "Generating Options Config"));
		buttonList.add(new GuiButton(2004, width / 2 - 100, height / 6 + 114, "Mobs Config"));
	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		if (button.id == 2000)
		{
			mc.displayGuiScreen(parentScreen);
		}
		if (button.id == 2001)
		{
			mc.displayGuiScreen(new EBGeneralGUI(this));
		}
		if (button.id == 2002)
		{
			mc.displayGuiScreen(new EBOresGUI(this));
		}
		if (button.id == 2003)
		{
			mc.displayGuiScreen(new EBGenGUI(this));
		}
		if (button.id == 2004)
		{
			mc.displayGuiScreen(new EBMobsGUI(this));
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawDefaultBackground();
		drawCenteredString(fontRendererObj, "Extreme Blocks Config", width / 2, 8, 16777215);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	public static class EBGeneralGUI extends EBGUI
	{
		public EBGeneralGUI(GuiScreen parent)
		{
			super(parent, Vars.CG, "General Config");
		}
	}

	public static class EBOresGUI extends EBGUI
	{
		public EBOresGUI(GuiScreen parent)
		{
			super(parent, Vars.SR, "Ore Spawn Rate Config");
		}
	}

	public static class EBGenGUI extends EBGUI
	{
		public EBGenGUI(GuiScreen parent)
		{
			super(parent, Vars.GO, "Generating Options Config");
		}
	}

	public static class EBMobsGUI extends EBGUI
	{
		public EBMobsGUI(GuiScreen parent)
		{
			super(parent, Vars.CM, "Mobs Config");
		}
	}

	public static class EBGUI extends GuiConfig
	{
		public EBGUI(GuiScreen parent, String category, String name)
		{
			super(parent, new ConfigElement<Object>(ExtremeBlocks.configFile.getCategory(category)).getChildElements(), Init.MODID, false, false, name);
		}
	}
}
