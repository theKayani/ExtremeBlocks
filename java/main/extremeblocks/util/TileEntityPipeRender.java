package main.extremeblocks.util;

import main.extremeblocks.Init;
import main.extremeblocks.blocks.tileentities.TileEntityPowerPipe;
import main.extremeblocks.blocks.tileentities.TileEntityTransportPipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityPipeRender extends TileEntitySpecialRenderer
{
	private final ResourceLocation textures;
	private ModelPipe model;
	private RenderItem customRenderItem;

	public TileEntityPipeRender(String fileName)
	{
		customRenderItem = new RenderItem()
		{
			@Override
			public boolean shouldBob()
			{
				return false;
			}

			@Override
			public boolean shouldSpreadItems()
			{
				return false;
			}
		};
		customRenderItem.setRenderManager(RenderManager.instance);
		this.model = new ModelPipe();
		this.textures = new ResourceLocation(Init.MODID + ":textures/models/" + fileName + "_pipe.png");
	}

	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale)
	{
		FontRenderer fr = this.func_147498_b();

		if (te instanceof TileEntityPowerPipe)
		{
			renderPower((TileEntityPowerPipe) te, x, y, z, scale);
		}
		else if (te instanceof TileEntityTransportPipe)
		{
			renderItems((TileEntityTransportPipe) te, x, y, z, scale);
		}

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		this.model.render(te);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	private void renderItems(TileEntityTransportPipe te, double x, double y, double z, float scale)
	{
		ItemStack[] items = te.items.toArray(new ItemStack[0]);

		for (int i = 0; i < items.length; i++)
		{
			if (items[i] != null && items[i].getItem() != null) doRenderItem(items[i], x + (i / 0.2D), y, z + (i / 0.2D));
		}
	}

	public void doRenderItem(ItemStack itemstack, double x, double y, double z)
	{
		EntityItem item = new EntityItem(null);
		item.age = 0;
		item.hoverStart = (float) (Math.PI * 2.0F);
		item.setEntityItemStack(itemstack);

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		GL11.glScalef(0.7F, 0.7F, 0.7F);
		customRenderItem.doRender(item, 0, 0, 0, 0, 0);
		GL11.glPopMatrix();
	}

	public void renderPower(TileEntityPowerPipe te, double x, double y, double z, float scale)
	{
		float f1 = te.power;
		GL11.glPushMatrix();
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);

		if (f1 > 0.0F)
		{
			long time = te.getWorldObj().getTotalWorldTime();
			Tessellator tessellator = Tessellator.instance;
			this.bindTexture(new ResourceLocation("textures/entity/beacon_beam.png"));
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, 10497.0F);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, 10497.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDepthMask(true);
			OpenGlHelper.glBlendFunc(770, 1, 1, 0);
			tessellator.startDrawingQuads();
			tessellator.setColorRGBA(255, 255, 255, 32);
			double d7 = 0.5D + Math.sin(2.356194490192345D) * 0.1D;
			double d9 = 0.5D + Math.cos(2.356194490192345D) * 0.1D;
			double d11 = 0.5D + Math.sin(Math.PI / 4D) * 0.1D;
			double d13 = 0.5D + Math.cos(Math.PI / 4D) * 0.1D;
			double d15 = 0.5D + Math.sin(3.9269908169872414D) * 0.1D;
			double d17 = 0.5D + Math.cos(3.9269908169872414D) * 0.1D;
			double d19 = 0.5D + Math.sin(5.497787143782138D) * 0.1D;
			double d21 = 0.5D + Math.cos(5.497787143782138D) * 0.1D;
			double d23 = (0.25D * 1) + 0.5D;
			double cd = 0.0D;
			double ab = 1.0D;
			double bc = 2.0D * time * 0.2F - (float) MathHelper.floor_float(-time * 0.1F);
			double ad = (2.0D * 1) * (0.5D / 0.2D) + bc + 1.0D;

			tessellator.addVertexWithUV(x + d7, y + d23, z + d9, cd, bc); // A
			tessellator.addVertexWithUV(x + d7, y + 0.5F, z + d9, ab, bc); // B
			tessellator.addVertexWithUV(x + d11, y + 0.5F, z + d13, ab, ad); // C
			tessellator.addVertexWithUV(x + d11, y + d23, z + d13, cd, ad); // D

			tessellator.addVertexWithUV(x + d19, y + d23, z + d21, cd, bc); // A
			tessellator.addVertexWithUV(x + d19, y + 0.5F, z + d21, ab, bc); // B
			tessellator.addVertexWithUV(x + d15, y + 0.5F, z + d17, ab, ad); // C
			tessellator.addVertexWithUV(x + d15, y + d23, z + d17, cd, ad); // D

			tessellator.addVertexWithUV(x + d11, y + d23, z + d13, cd, bc); // A
			tessellator.addVertexWithUV(x + d11, y + 0.5F, z + d13, ab, bc); // B
			tessellator.addVertexWithUV(x + d19, y + 0.5F, z + d21, ab, ad); // C
			tessellator.addVertexWithUV(x + d19, y + d23, z + d21, cd, ad); // D

			tessellator.addVertexWithUV(x + d15, y + d23, z + d17, cd, bc); // A
			tessellator.addVertexWithUV(x + d15, y + 0.5F, z + d17, ab, bc); // B
			tessellator.addVertexWithUV(x + d7, y + 0.5F, z + d9, ab, ad); // C
			tessellator.addVertexWithUV(x + d7, y + d23, z + d9, cd, ad); // D

			tessellator.draw();
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glDepthMask(false);

			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDepthMask(true);
		}

		GL11.glAlphaFunc(GL11.GL_GREATER, 0.5F);
		GL11.glPopMatrix();
	}

	public void render(double x, double y, double z)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}