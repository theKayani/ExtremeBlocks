package main.extremeblocks;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import cpw.mods.fml.client.config.GuiConfig;

public class ConfigScreen extends GuiScreen
{
	private GuiScreen parentScreen;

	public ConfigScreen(GuiScreen parent)
	{
		this.parentScreen = parent;
	}

	@Override
	public void initGui()
	{
		this.buttonList.add(new GuiButton(2000, this.width / 2 - 100, this.height / 6 + 168, "Done"));
		// Add 24 for the next Row!
		this.buttonList.add(new GuiButton(2001, this.width / 2 - 100, this.height / 6 + 42, "General Config"));
		this.buttonList.add(new GuiButton(2002, this.width / 2 - 100, this.height / 6 + 66, "Ores Spawn Rate Config"));
		this.buttonList.add(new GuiButton(2003, this.width / 2 - 100, this.height / 6 + 90, "Generating Options Config"));
		this.buttonList.add(new GuiButton(2004, this.width / 2 - 100, this.height / 6 + 114, "Mobs Config"));
	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		if (button.id == 2000)
		{
			this.mc.displayGuiScreen(this.parentScreen);
		}
		if (button.id == 2001)
		{
			this.mc.displayGuiScreen(new EBGeneralGUI(this));
		}
		if (button.id == 2002)
		{
			this.mc.displayGuiScreen(new EBOresGUI(this));
		}
		if (button.id == 2003)
		{
			this.mc.displayGuiScreen(new EBGenGUI(this));
		}
		if (button.id == 2004)
		{
			this.mc.displayGuiScreen(new EBMobsGUI(this));
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, "Extreme Blocks Config", this.width / 2, 8, 16777215);
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
			super(parent, new ConfigElement(ExtremeBlocks.configFile.getCategory(category)).getChildElements(), Init.MODID, false, false, name);
		}
	}
}
