package main.extremeblocks.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMedal extends ModelBase
{
	private ModelRenderer modelBase;
	private ModelRenderer modelBase2;
	private ModelRenderer modelBase23;
	private ModelRenderer modelBase24;

	public ModelMedal()
	{
		textureWidth = 512;
		textureHeight = 256;

		modelBase = new ModelRenderer(this, 0, 0);
		modelBase.addBox(-7.0F, 21.0F, -7.0F, 14, 3, 14);
		modelBase.setTextureSize(512, 256);
		modelBase.setRotationPoint(0.0F, 0.0F, 0.0F);
		modelBase.mirror = false;
		setRotation(modelBase, 0.0F, 0.0F, 0.0F);

		modelBase23 = new ModelRenderer(this, 0, 0);
		modelBase23.addBox(-6.0F, 18.0F, -6.0F, 12, 3, 12);
		modelBase23.setTextureSize(512, 256);
		modelBase23.setRotationPoint(0.0F, 0.0F, 0.0F);
		modelBase23.mirror = false;
		setRotation(modelBase23, 0.0F, 0.0F, 0.0F);

		modelBase24 = new ModelRenderer(this, 0, 0);
		modelBase24.addBox(-3.0F, 9.0F, -3.0F, 6, 10, 6);
		modelBase24.setTextureSize(512, 256);
		modelBase24.setRotationPoint(0.0F, 0.0F, 0.0F);
		modelBase24.mirror = false;
		setRotation(modelBase24, 0.0F, 0.0F, 0.0F);

		modelBase2 = new ModelRenderer(this, 0, 0);
		modelBase2.addBox(-1.5F, 6.0F, -1.5F, 3, 3, 3);
		modelBase2.setTextureSize(512, 256);
		modelBase2.setRotationPoint(0.0F, 0.0F, 0.0F);
		modelBase2.mirror = false;
		setRotation(modelBase2, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		modelBase.render(f5);
		modelBase2.render(f5);
		modelBase23.render(f5);
		modelBase24.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
