package main.extremeblocks.blocks.tileentities.guis;

import main.com.hk.testing.util.MPUtil;
import main.extremeblocks.Init;
import main.extremeblocks.blocks.tileentities.TileEntityPowerReceiver;
import main.extremeblocks.blocks.tileentities.builder.Builder;
import main.extremeblocks.blocks.tileentities.containers.ContainerPowerReceiver;
import main.extremeblocks.network.PacketPowerReceiver;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiPowerReceiver extends GuiContainer
{
	private TileEntityPowerReceiver receiver;

	public GuiPowerReceiver(TileEntityPowerReceiver receiver)
	{
		super(new ContainerPowerReceiver(receiver));
		this.receiver = receiver;
	}

	public void updateScreen()
	{
		super.updateScreen();
		
		if(receiver.done)
		{
			receiver.isBuilding = receiver.done = false;
			receiver.stage = receiver.counter = receiver.buildingMode = 0;
			sendPacket();
		}
	}

	public void initGui()
	{
		super.initGui();
		this.buttonList.add(new GuiButton(0, 140, 70, 50, 20, "Change"));
		this.buttonList.add(new GuiButton(1, 220, 70, 50, 20, "Done"));
	}

	public void actionPerformed(GuiButton b) 
	{
		switch(b.id)
		{
			case 0:
			{
				if(!receiver.done && !receiver.isBuilding)
				{
					if(receiver.buildingMode >= 0 && receiver.buildingMode < Builder.maxStructures()) receiver.buildingMode++;
					else receiver.buildingMode = 0;
				}
				break;
			}
			case 1:
			{
				if(receiver.buildingMode != 0 && !receiver.isBuilding)
				{
					receiver.isBuilding = true;
				}
				break;
			}
		}
		sendPacket();
	}

	@Override
	public void drawGuiContainerForegroundLayer(int param1, int param2)
	{
		fontRendererObj.drawString("Power Receiver", 6, 6, 4210752);
		fontRendererObj.drawString("Current Power: " + receiver.power, 6, 62, 4210752);
		fontRendererObj.drawString("Power Needed: " + Builder.getPowerNeeded(receiver.buildingMode), 6, 74, 4210752);
		fontRendererObj.drawString("Max Stages: " + Builder.getMaxStages(receiver.buildingMode), 6, 86, 4210752);
		fontRendererObj.drawString("Structure: " + Builder.getStructure(receiver.buildingMode), 6, 98, 4210752);
		fontRendererObj.drawString("Stage: " + receiver.stage, 6, 110, 4210752);
		fontRendererObj.drawString("Is Building: " + receiver.isBuilding, 6, 122, 4210752);
		fontRendererObj.drawString("Done: " + receiver.done, 6, 134, 4210752);
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(new ResourceLocation(Init.MODID + ":textures/gui/power_receiver.png"));
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}
	
	public void sendPacket()
	{
		if(MPUtil.isClientSide()) MPUtil.sendToServer(new PacketPowerReceiver(receiver));
	}
}
