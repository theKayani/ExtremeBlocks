package main.extremeblocks.renderers;

import main.extremeblocks.Init;
import main.extremeblocks.entities.mobs.EntityRobot;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRobot extends RenderBiped
{
	private static final ResourceLocation offTexture = new ResourceLocation(Init.MODID + ":textures/entities/robot_off.png");
	private static final ResourceLocation onTexture = new ResourceLocation(Init.MODID + ":textures/entities/robot_on.png");

	public RenderRobot()
	{
		super(new ModelBiped(0.0F), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return ((EntityRobot) entity).isOff ? offTexture : onTexture;
	}
}