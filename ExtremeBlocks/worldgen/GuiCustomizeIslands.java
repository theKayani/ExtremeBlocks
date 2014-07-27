package main.extremeblocks.worldgen;

import main.com.hk.testing.util.MathHelp;
import main.extremeblocks.Vars;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiScreen;

public class GuiCustomizeIslands extends GuiScreen
{
	private GuiCreateWorld parentGui;
	private int trees, pumps, cactus, melons;

	public GuiCustomizeIslands(GuiCreateWorld parentGui, int trees, int pumps, int cactus, int melons)
	{
		this.parentGui = parentGui;
		this.trees = trees;
		this.pumps = pumps;
		this.cactus = cactus;
		this.melons = melons;
	}

	public void initGui()
	{
		this.buttonList.add(new GuiButton(0, this.width / 2 - 155, this.height - 28, 150, 20, "Done"));
		this.buttonList.add(new GuiButton(1, this.width / 2 + 5, this.height - 28, 150, 20, "Cancel"));

		this.buttonList.add(new GuiButton(10, 15, 25, 25, 20, "+"));
		this.buttonList.add(new GuiButton(20, 80, 25, 25, 20, "-"));

		this.buttonList.add(new GuiButton(11, 15, 65, 25, 20, "+"));
		this.buttonList.add(new GuiButton(21, 80, 65, 25, 20, "-"));

		this.buttonList.add(new GuiButton(12, 15, 105, 25, 20, "+"));
		this.buttonList.add(new GuiButton(22, 80, 105, 25, 20, "-"));

		this.buttonList.add(new GuiButton(13, 15, 145, 25, 20, "+"));
		this.buttonList.add(new GuiButton(23, 80, 145, 25, 20, "-"));
	}

	protected void actionPerformed(GuiButton b)
	{
		switch (b.id)
		{
			case 0:
			{
				Vars.numbOfPumps += pumps;
				Vars.numbOfTrees += trees;
				Vars.numbOfCactus += cactus;
				Vars.numbOfMelons += melons;
				this.mc.displayGuiScreen(this.parentGui);
				break;
			}
			case 1:
			{
				this.mc.displayGuiScreen(this.parentGui);
				break;
			}
			case 10:
			{
				trees += trees < 20 ? 1 : 0;
				break;
			}
			case 20:
			{
				trees -= trees > 0 ? 1 : 0;
				break;
			}

			case 11:
			{
				pumps += pumps < 20 ? 1 : 0;
				break;
			}
			case 21:
			{
				pumps -= pumps > 0 ? 1 : 0;
				break;
			}

			case 12:
			{
				cactus += cactus < 20 ? 1 : 0;
				break;
			}
			case 22:
			{
				cactus -= cactus > 0 ? 1 : 0;
				break;
			}

			case 13:
			{
				melons += melons < 20 ? 1 : 0;
				break;
			}
			case 23:
			{
				melons -= melons > 0 ? 1 : 0;
				break;
			}
		}
	}

	public void drawScreen(int par1, int par2, float par3)
	{
		this.drawDefaultBackground();

		drawString("Number of Trees to Spawn", 85, 15);
		drawString(Vars.numbOfTrees + trees, 60, 34);

		drawString("Number of Pumpkins to Spawn", 85, 55);
		drawString(Vars.numbOfPumps + pumps, 60, 74);

		drawString("Number of Cactus to Spawn", 85, 95);
		drawString(Vars.numbOfCactus + cactus, 60, 114);

		drawString("Number of Melons to Spawn", 85, 135);
		drawString(Vars.numbOfMelons + melons, 60, 154);

		super.drawScreen(par1, par2, par3);
	}

	public void drawString(Object str, int x, int y)
	{
		this.drawCenteredString(this.fontRendererObj, String.valueOf(str), x, y, 16777215);
	}
}
