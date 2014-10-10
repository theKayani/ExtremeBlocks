package main.extremeblocks.renderers;

import main.extremeblocks.Init;
import main.extremeblocks.blocks.BlockMedal;
import main.extremeblocks.blocks.BlockMedal.MedalType;
import main.extremeblocks.renderers.models.ModelMedal;
import main.extremeblocks.tileentities.TileEntityMedal;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class TileEntityMedalRenderer extends TileEntitySpecialRenderer implements IItemRenderer
{
	private final ModelMedal model;

	public TileEntityMedalRenderer(MedalType type)
	{
		model = new ModelMedal();
	}

	public void render(MedalType type, double x, double y, double z)
	{
		if (type == null) return;
		String loc = type.name.toLowerCase();
		switch (type)
		{
			case BRONZE:
				// loc = Init.MODID + ":textures/models/Bronze Block.png";
				break;
			case DIAMOND:
				// loc = "textures/blocks/diamond_block.png";
				break;
			case GOLD:
				// loc = "textures/blocks/gold_block.png";
				break;
			case IRON:
				// loc = "textures/blocks/iron_block.png";
				break;
			case SILVER:
				// loc = Init.MODID + ":textures/blocks/Silver Block.png";
				break;
			case TRINQUANTIUM:
				// loc = Init.MODID + ":textures/blocks/Trinquantium Block.png";
				break;

		}
		if (loc == null) return;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Init.MODID + ":textures/models/" + loc.toLowerCase() + "_medal.png"));
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale)
	{
		render(((TileEntityMedal) te).type, x, y, z);
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		render(getType(item), 0.0D, 0.0D, 0.0D);
	}

	private MedalType getType(ItemStack item)
	{
		if (item == null || !(item.getItem() instanceof ItemBlock)) return null;
		Block block = ((ItemBlock) item.getItem()).field_150939_a;
		if (block instanceof BlockMedal) return ((BlockMedal) block).type;
		return null;
	}
}
