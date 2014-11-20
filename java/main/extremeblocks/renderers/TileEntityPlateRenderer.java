package main.extremeblocks.renderers;

import main.extremeblocks.Init;
import main.extremeblocks.renderers.models.ModelPlate;
import main.extremeblocks.tileentities.TileEntityPlate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityPlateRenderer extends TileEntitySpecialRenderer
{
	private final ResourceLocation textures;
	private ModelPlate model;
	private RenderItem customRenderItem;

	public TileEntityPlateRenderer()
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
		model = new ModelPlate();
		textures = new ResourceLocation(Init.MODID + ":textures/models/wooden_plate.png");
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale)
	{
		if (te != null)
		{
			renderItems((TileEntityPlate) te, x, y, z, scale);
		}

		render(x, y, z);
	}

	private void renderItems(TileEntityPlate te, double x, double y, double z, float scale)
	{
		if (te.inventory[0] != null && te.inventory[0].getItem() != null)
		{
			doRenderItem(te.inventory[0], x, y, z);
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

	public void render(double x, double y, double z)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}