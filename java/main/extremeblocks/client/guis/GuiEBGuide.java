package main.extremeblocks.client.guis;

import main.com.hk.eb.util.Info;
import main.com.hk.eb.util.RecipeUtils;
import main.extremeblocks.Guide;
import main.extremeblocks.Init;
import main.extremeblocks.Vars;
import main.extremeblocks.util.DecodedRecipe;
import main.extremeblocks.util.IBattery;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiEBGuide extends GuiScreen
{
	private static final ResourceLocation guideTexture = new ResourceLocation(Init.MODID + ":textures/gui/guide.png");
	private static final ResourceLocation guideRecipeTexture = new ResourceLocation(Init.MODID + ":textures/gui/guide_recipe.png");
	private int imageWidth = 230;
	private int imageHeight = 180;
	private int zeroX;
	private int bookTotalPages;
	private int currPage;

	public GuiEBGuide()
	{
		bookTotalPages = Guide.size + 1;
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return Vars.guidePausesGame;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui()
	{
		zeroX = (width - imageWidth) / 2;
		buttonList.clear();

		buttonList.add(new GuiButton(0, width / 2 - 100, 6 + imageHeight, "Done"));
		buttonList.add(new NextPageButton(1, zeroX + imageWidth - 33, 156, true));
		buttonList.add(new NextPageButton(2, zeroX + 8, 156, false));
	}

	@Override
	protected void actionPerformed(GuiButton p_146284_1_)
	{
		if (p_146284_1_.enabled)
		{
			if (p_146284_1_.id == 0)
			{
				mc.displayGuiScreen((GuiScreen) null);
			}
			else if (p_146284_1_.id == 1)
			{
				if (currPage < bookTotalPages - 1)
				{
					++currPage;
				}
				else
				{
					currPage = 0;
				}
			}
			else if (p_146284_1_.id == 2)
			{
				if (currPage > 0)
				{
					--currPage;
				}
				else
				{
					currPage = bookTotalPages - 1;
				}
			}
		}
	}

	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
	{
		renderPage(currPage);
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}

	public void renderPage(int page)
	{
		String s = isItemPage(page);
		if (s != null)
		{
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			mc.getTextureManager().bindTexture(guideTexture);
			drawTexturedModalRect(zeroX, 2, 0, 0, imageWidth, imageHeight);
			addPageNumber(page);
			drawNewLine(s);
		}
		else
		{
			page--;
			Info o = Guide.infos.get(page);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			mc.getTextureManager().bindTexture(o.getElements().showRecipe ? guideRecipeTexture : guideTexture);
			drawTexturedModalRect(zeroX, 2, 0, 0, imageWidth, imageHeight);
			addPageNumber(page + 1);
			drawNewLine(getStringFor(page, o));

			if (o.getElements().showRecipe)
			{
				ItemStack is = o instanceof Block ? new ItemStack((Block) o) : new ItemStack((Item) o);
				renderRecipe(is);
			}
		}
	}

	public String getStringFor(int page, Info o)
	{
		String name = "";
		if (o instanceof Block)
		{
			name = "-" + ((Block) o).getLocalizedName() + "-\n";
		}
		else if (o instanceof Item)
		{
			name = "-" + ((Item) o).getUnlocalizedName() + "-\n";
		}
		else if (!(o instanceof Item) && !(o instanceof Block)) return o == null ? "null" : o.getClass().getName();
		return EnumChatFormatting.BOLD + name + EnumChatFormatting.RESET + o.getInfo();
	}

	public String isItemPage(int page)
	{
		switch (page)
		{
			case 0:
				return "This is the Extreme Blocks Mod Guide! Here, you will find the information to every single unique item and block. What this means is if something has a special function. It will be here. Else, it won't. If you think something does have a use, and it's not here. Please Let me know! I will fix it!. Please note that there is a Config option that will allow you to pause the game when this is used!\nP.S. It's Alphabetized!";
		}
		return null;
	}

	public void renderRecipe(ItemStack stack)
	{
		int gridX = zeroX + imageWidth / 2 - 80;
		int gridY = imageHeight - 71;
		stack = setDifferences(stack);
		DecodedRecipe rec = new DecodedRecipe(stack);
		ItemStack[] recipeItems = rec.getRecipeItems();
		if (recipeItems != null)
		{
			for (int i = 0; i < recipeItems.length; i++)
			{
				if (recipeItems[i] != null)
				{
					int[] pos = getPosFor(i);
					renderItemAt(1 + gridX + pos[0], 1 + gridY + pos[1], recipeItems[i].getItem());
				}
			}
			stack = stack.copy();
			stack.stackSize = RecipeUtils.getRecipeFor(stack).getRecipeOutput().stackSize;
		}
		renderItemAt(gridX + 72, gridY + 18, stack);
	}

	public int[] getPosFor(int i)
	{
		int[] pos = new int[] { 0, 0 };
		pos[0] = i % 3 * 18;
		pos[1] = i / 3 * 18;
		return pos;
	}

	public void renderItemAt(int x, int y, Object o)
	{
		RenderHelper.enableGUIStandardItemLighting();
		drawItemStack(o instanceof Block ? new ItemStack((Block) o) : o instanceof Item ? new ItemStack((Item) o) : o instanceof ItemStack ? ((ItemStack) o).copy() : null, x, y);
		RenderHelper.disableStandardItemLighting();
	}

	private void drawItemStack(ItemStack stack, int x, int y)
	{
		GL11.glTranslatef(0.0F, 0.0F, 32.0F);
		zLevel = 200.0F;
		itemRender.zLevel = 200.0F;
		FontRenderer font = null;
		if (stack != null)
		{
			font = stack.getItem().getFontRenderer(stack);
		}
		if (font == null)
		{
			font = fontRendererObj;
		}
		itemRender.renderItemAndEffectIntoGUI(font, mc.getTextureManager(), stack, x, y);
		itemRender.renderItemOverlayIntoGUI(font, mc.getTextureManager(), stack, x, y);
		zLevel = 0.0F;
		itemRender.zLevel = 0.0F;
	}

	public void drawNewLine(String line)
	{
		fontRendererObj.drawSplitString(line, zeroX + 15, 18, 200, 0);
	}

	public void addPageNumber(int page)
	{
		page++;
		String s = page + "/" + bookTotalPages;
		int l = fontRendererObj.getStringWidth(s);
		fontRendererObj.drawString(s, zeroX + imageWidth - 33 - l, imageHeight - 20, 0);
	}

	public ItemStack setDifferences(ItemStack stack)
	{
		if (stack.getItem() instanceof IBattery)
		{
			stack.setItemDamage(((IBattery) stack.getItem()).getMaxHeldPower());
		}
		return stack;
	}

	@SideOnly(Side.CLIENT)
	public static class NextPageButton extends GuiButton
	{
		private final boolean next;

		public NextPageButton(int id, int x, int y, boolean normal)
		{
			super(id, x, y, 23, 13, "");
			next = normal;
		}

		@Override
		public void drawButton(Minecraft mc, int x, int y)
		{
			if (visible)
			{
				boolean flag = x >= xPosition && y >= yPosition && x < xPosition + width && y < yPosition + height;
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				mc.getTextureManager().bindTexture(guideTexture);
				int k = 3;
				int l = 194;

				if (flag)
				{
					k += 23;
				}

				if (next)
				{
					l += 14;
				}

				drawTexturedModalRect(xPosition, yPosition, k, l, 17, 8);
			}
		}
	}
}