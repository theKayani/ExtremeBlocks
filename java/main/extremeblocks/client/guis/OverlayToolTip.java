package main.extremeblocks.client.guis;

import java.awt.Rectangle;
import java.util.List;
import main.com.hk.eb.util.IOverlay;
import main.com.hk.eb.util.JavaHelp;

public abstract class OverlayToolTip extends IOverlay
{
	private final List<String> text;

	public OverlayToolTip(Rectangle bounds)
	{
		super(bounds);
		text = JavaHelp.newArrayList();
	}

	public abstract void addText(List<String> list);

	public void setToolTipText(String... txt)
	{
		text.clear();
		if (txt != null)
		{
			for (String line : txt)
			{
				text.add(line);
			}
		}
	}

	@Override
	public void draw(GuiEB renderer, int mouseX, int mouseY)
	{
		text.clear();
		addText(text);
		List<String> list = text;
		if (list == null) return;

		List<String> formatted = JavaHelp.newArrayList();
		for (int i = 0; i < list.size(); i++)
		{
			if (i == 0)
			{
				formatted.add("\u00a7" + Integer.toHexString(15) + list.get(i));
			}
			else
			{
				formatted.add("\u00a77" + list.get(i));
			}
		}

		if (mouseX > renderer.getXSize() / 2)
		{
			int maxWidth = 0;
			for (String s : formatted)
			{
				int w = renderer.getFontRenderer().getStringWidth(s);
				if (w > maxWidth)
				{
					maxWidth = w;
				}
			}
			mouseX -= maxWidth + 18;
		}
		renderer.drawHoveringText(list, mouseX, mouseY, renderer.getFontRenderer());
	}
}