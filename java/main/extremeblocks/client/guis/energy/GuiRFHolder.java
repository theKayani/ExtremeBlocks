package main.extremeblocks.client.guis.energy;

import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.util.List;
import main.com.hk.eb.util.IEnergyHolder;
import main.extremeblocks.client.containers.ContainerInventory;
import main.extremeblocks.client.guis.GuiInventory;
import main.extremeblocks.client.guis.OverlayToolTip;
import net.minecraft.util.EnumChatFormatting;

public abstract class GuiRFHolder extends GuiInventory
{
	protected int powerX, powerY, powerU, powerV;
	protected int indX, indY, indU, indV;
	private final IEnergyHolder holder;

	public GuiRFHolder(ContainerInventory container)
	{
		super(container);
		holder = (IEnergyHolder) tile;
	}

	public void addPowerTooltip()
	{
		OverlayToolTip tooltip = new OverlayToolTip(new Rectangle(powerX, powerY, 10, 68))
		{
			@Override
			public void addText(List<String> list)
			{
				DecimalFormat f = new DecimalFormat("#,###");
				String stored = f.format(holder.getEnergyStored());
				String max = f.format(holder.getMaxEnergyStored());
				String maxExtract = f.format(holder.getMaxExtract());
				String maxReceive = f.format(holder.getMaxReceive());
				list.add(stored + "/" + max + EnumChatFormatting.RED + " RF " + EnumChatFormatting.RESET + "Held");
				list.add("Max Extract: " + maxExtract);
				list.add("Max Receive: " + maxReceive);
			}
		};
		tooltip.setVisible(true);
		addOverlay(tooltip);
	}

	public void addRedstoneTooltip()
	{
		OverlayToolTip tooltip = new OverlayToolTip(new Rectangle(indX, indY, 10, 10))
		{
			@Override
			public void addText(List<String> list)
			{
				String s = tile.getWorldObj().isBlockIndirectlyGettingPowered(tile.xCoord, tile.yCoord, tile.zCoord) ? EnumChatFormatting.GREEN + "X" + EnumChatFormatting.RESET : EnumChatFormatting.RED + " " + EnumChatFormatting.RESET;
				list.add("[" + s + "] Redstone Powered");
			}
		};
		tooltip.setVisible(true);
		addOverlay(tooltip);
	}

	protected void renderPowerAt()
	{
		if (holder.getEnergyStored() <= 0) return;
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		int i1 = holder.getEnergyStoredScaled(67);
		drawTexturedModalRect(k + powerX, l + powerY + 67 - i1, powerU, powerV, 10, i1 + 1);
	}

	protected void renderRedstoneIndicatorAt()
	{
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k + indX, l + indY, indU, indV, 9, 9);
	}
}
