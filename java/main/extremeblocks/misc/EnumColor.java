package main.extremeblocks.misc;

import java.awt.Color;
import main.com.hk.eb.util.JavaHelp;
import main.extremeblocks.Init;
import net.minecraft.util.ResourceLocation;

public enum EnumColor
{
	Red("0xFF0000", true), Blue("0x0000FF", true), White("0xFFFFFF", true), Green("0x00FF00", true), Orange("0xFFCC00", true), Yellow("0xFFFF00", true), Black("0x000000", true), Purple("0x800080", true);

	public final int colorMultiplier;
	public final int r, g, b;
	private boolean hasTexture = false;

	private EnumColor(String color)
	{
		this(color, false);
	}

	private EnumColor(String color, boolean hasTexture)
	{
		Color c = Color.decode(color);
		r = c.getRed();
		g = c.getGreen();
		b = c.getBlue();

		colorMultiplier = c.getRGB();// Integer.decode(color);
		this.hasTexture = hasTexture;
	}

	public ResourceLocation getTexture()
	{
		// return new ResourceLocation(Init.MODID +
		// ":textures/entities/soldier_" + this.name() + ".png");
		return new ResourceLocation(Init.MODID + ":textures/blocks/Silver Block.png");
	}

	public static EnumColor randomColor()
	{
		EnumColor color = JavaHelp.getRandomElementFrom(values());

		if (color.hasTexture) return color;
		return randomColor();
	}
}