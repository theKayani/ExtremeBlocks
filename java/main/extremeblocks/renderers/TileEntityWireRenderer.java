package main.extremeblocks.renderers;

import main.extremeblocks.renderers.models.ModelWire;
import main.extremeblocks.tileentities.TileEntityWire;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityWireRenderer extends TileEntitySpecialRenderer
{
	private static final ResourceLocation PIPE_LOCATION = new ResourceLocation("textures/blocks/coal_block.png");
	private ModelWire model;

	public TileEntityWireRenderer()
	{
		model = new ModelWire();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(PIPE_LOCATION);
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		model.render((TileEntityWire) te);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	public void render(double x, double y, double z)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(PIPE_LOCATION);
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}
