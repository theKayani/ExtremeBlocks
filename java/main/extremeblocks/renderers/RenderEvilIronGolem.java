package main.extremeblocks.renderers;

import main.extremeblocks.Init;
import main.extremeblocks.renderers.models.ModelEvilIronGolem;
import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderEvilIronGolem extends RenderLiving
{
	private static final ResourceLocation evilIronGolemTextures = new ResourceLocation(Init.MODID + ":textures/entities/evil_iron_golem.png");
	private final ModelEvilIronGolem evilIronGolemModel;

	public RenderEvilIronGolem()
	{
		super(new ModelEvilIronGolem(), 0.5F);
		this.evilIronGolemModel = (ModelEvilIronGolem) this.mainModel;
	}

	protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
	{
		super.rotateCorpse(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
		if ((double) p_77043_1_.limbSwingAmount >= 0.01D)
		{
			float f3 = 13.0F;
			float f4 = p_77043_1_.limbSwing - p_77043_1_.limbSwingAmount * (1.0F - p_77043_4_) + 6.0F;
			float f5 = (Math.abs(f4 % f3 - f3 * 0.5F) - f3 * 0.25F) / (f3 * 0.25F);
			GL11.glRotatef(6.5F * f5, 0.0F, 0.0F, 1.0F);
		}
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return evilIronGolemTextures;
	}
}
