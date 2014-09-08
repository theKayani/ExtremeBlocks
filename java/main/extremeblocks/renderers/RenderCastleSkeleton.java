package main.extremeblocks.renderers;

import main.extremeblocks.Init;
import net.minecraft.client.renderer.entity.RenderSkeleton;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.util.ResourceLocation;

public class RenderCastleSkeleton extends RenderSkeleton
{
	private static final ResourceLocation skeletonTextures = new ResourceLocation(Init.MODID + ":textures/entities/castle_skeleton.png");

	protected ResourceLocation getEntityTexture(EntitySkeleton p_110775_1_)
	{
		return skeletonTextures;
	}
}
