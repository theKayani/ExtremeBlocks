package main.extremeblocks.client.guis;

import java.util.Iterator;
import java.util.List;
import main.com.hk.eb.util.IOverlay;
import main.com.hk.eb.util.JavaHelp;
import main.extremeblocks.client.containers.ContainerEB;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public abstract class GuiEB extends GuiContainer
{
	private final List<IOverlay> overlays;

	public GuiEB(ContainerEB container)
	{
		super(container);
		overlays = JavaHelp.newArrayList();
	}

	@Override
	protected abstract void drawGuiContainerForegroundLayer(int a, int b);

	@Override
	protected abstract void drawGuiContainerBackgroundLayer(float eye, int dont, int know);

	public void addOverlay(IOverlay overlay)
	{
		overlays.add(overlay);
	}

	public void removeOverlay(IOverlay overlay)
	{
		overlays.remove(overlay);
	}

	public int getGuiLeft()
	{
		return guiLeft;
	}

	public int getGuiTop()
	{
		return guiTop;
	}

	public int getXSize()
	{
		return xSize;
	}

	public int getYSize()
	{
		return ySize;
	}

	public FontRenderer getFontRenderer()
	{
		return fontRendererObj;
	}

	public void renderTexture(ResourceLocation location, int x, int y, int u, int v, int width, int height)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(location);
		drawTexturedModalRect(x, y, u, v, width, height);
	}

	public void renderFullTexture(ResourceLocation location)
	{
		renderTexture(location, guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	public void drawString(Object o, int x, int y, boolean middle)
	{
		String s = o == null ? "null" : o.toString();
		int color = 4210752;
		if (middle)
		{
			int a = x - fontRendererObj.getStringWidth(s) / 2;
			int b = y - fontRendererObj.FONT_HEIGHT / 2;
			fontRendererObj.drawString(s, a, b, color);
		}
		else
		{
			fontRendererObj.drawString(s, x, y, color);
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float somethingElse)
	{
		super.drawScreen(mouseX, mouseY, somethingElse);
		for (IOverlay overlay : overlays)
		{
			if (overlay != null && overlay.isVisible() && func_146978_c(overlay.getBounds().x, overlay.getBounds().y, overlay.getBounds().width, overlay.getBounds().height, mouseX, mouseY))
			{
				overlay.draw(this, mouseX, mouseY);
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void drawHoveringText(List par1List, int par2, int par3, FontRenderer font)
	{
		//super.drawHoveringText(par1List, par2, par3, font);
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
		copyOfdrawHoveringText(par1List, par2, par3, font);
		GL11.glPopAttrib();
		GL11.glPopAttrib();
	}

	protected void copyOfdrawHoveringText(List<String> par1List, int par2, int par3, FontRenderer font)
	{
		if (!par1List.isEmpty())
		{
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			RenderHelper.disableStandardItemLighting();
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			int k = 0;
			Iterator<String> iterator = par1List.iterator();

			while (iterator.hasNext())
			{
				String s = iterator.next();
				int l = font.getStringWidth(s);

				if (l > k)
				{
					k = l;
				}
			}

			int i1 = par2 + 12;
			int j1 = par3 - 12;
			int k1 = 8;

			if (par1List.size() > 1)
			{
				k1 += 2 + (par1List.size() - 1) * 10;
			}

			if (i1 + k > width)
			{
				i1 -= 28 + k;
			}

			if (j1 + k1 + 6 > height)
			{
				j1 = height - k1 - 6;
			}

			zLevel = 300.0F;
			//itemRenderer.zLevel = 300.0F;
			int l1 = -267386864;
			drawGradientRect(i1 - 3, j1 - 4, i1 + k + 3, j1 - 3, l1, l1);
			drawGradientRect(i1 - 3, j1 + k1 + 3, i1 + k + 3, j1 + k1 + 4, l1, l1);
			drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 + k1 + 3, l1, l1);
			drawGradientRect(i1 - 4, j1 - 3, i1 - 3, j1 + k1 + 3, l1, l1);
			drawGradientRect(i1 + k + 3, j1 - 3, i1 + k + 4, j1 + k1 + 3, l1, l1);
			int i2 = 1347420415;
			int j2 = (i2 & 16711422) >> 1 | i2 & -16777216;
			drawGradientRect(i1 - 3, j1 - 3 + 1, i1 - 3 + 1, j1 + k1 + 3 - 1, i2, j2);
			drawGradientRect(i1 + k + 2, j1 - 3 + 1, i1 + k + 3, j1 + k1 + 3 - 1, i2, j2);
			drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 - 3 + 1, i2, i2);
			drawGradientRect(i1 - 3, j1 + k1 + 2, i1 + k + 3, j1 + k1 + 3, j2, j2);

			for (int k2 = 0; k2 < par1List.size(); ++k2)
			{
				String s1 = par1List.get(k2);
				font.drawStringWithShadow(s1, i1, j1, -1);

				if (k2 == 0)
				{
					j1 += 2;
				}

				j1 += 10;
			}

			zLevel = 0.0F;
			//itemRenderer.zLevel = 0.0F;
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			RenderHelper.enableStandardItemLighting();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		}
	}

}
