package main.extremeblocks.renderers;

import main.extremeblocks.Init;
import main.extremeblocks.entities.mobs.EntityDemon;
import main.extremeblocks.renderers.models.ModelDemon;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderDemon extends RenderLiving
{
	public RenderDemon()
	{
		super(new ModelDemon(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity e)
	{
		return new ResourceLocation(Init.MODID + ":textures/entities/demon" + (((EntityDemon) e).isEnraged() ? "" : "_calm") + ".png");
	}
}
