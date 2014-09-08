package main.extremeblocks.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelPlate extends ModelBase
{
	private final ModelRenderer plateFloor;

	public ModelPlate()
	{
		textureWidth = 512;
		textureHeight = 256;

		plateFloor = new ModelRenderer(this, 0, 0);
		plateFloor.addBox(-7.0F, 23.0F, -7.0F, 14, 1, 14);
		plateFloor.setTextureSize(512, 256);
		plateFloor.setRotationPoint(0.0F, 0.0F, 0.0F);
		plateFloor.mirror = false;
		setRotation(plateFloor, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		plateFloor.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
