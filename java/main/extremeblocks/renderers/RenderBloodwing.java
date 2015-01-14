package main.extremeblocks.renderers;

import main.extremeblocks.renderers.models.ModelBloodwing;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBloodwing extends RenderLiving
{
	private static final ResourceLocation batTextures = new ResourceLocation("textures/entity/bat.png");

	public RenderBloodwing()
	{
		super(new ModelBloodwing(), 0.25F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float f)
	{
		GL11.glScalef(0.35F, 0.35F, 0.35F);
	}

	@Override
	protected void rotateCorpse(EntityLivingBase entity, float x, float y, float z)
	{
		GL11.glTranslatef(0.0F, -0.1F, 0.0F);
		super.rotateCorpse(entity, x, y, z);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return batTextures;
	}
}