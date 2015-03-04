package main.com.hk.eb.util;

import java.awt.Rectangle;
import main.extremeblocks.client.guis.GuiEB;

public abstract class IOverlay
{
	private Rectangle bounds;
	private boolean visible;

	public IOverlay(Rectangle bounds)
	{
		this.bounds = bounds;
	}

	public abstract void draw(GuiEB renderer, int mouseX, int mouseY);

	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}

	public boolean isVisible()
	{
		return visible;
	}

	public Rectangle getBounds()
	{
		return bounds;
	}

	public boolean isMouseInBounds(int mouseX, int mouseY)
	{
		return bounds.contains(mouseX, mouseY);
	}
}