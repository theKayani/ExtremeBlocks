/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ AUTOMATICALLY GENERATED FILE!
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Generated using Big Mac Modeler!
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ The Open Source Modeler using
 * Java! ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ~Multi-Platform~ Mac, PC,
 * and (maybe) Linux! ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package main.extremeblocks.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelTest extends ModelBase
{
	private ModelRenderer box1;
	private ModelRenderer box2;

	public ModelTest()
	{
		textureWidth = 512;
		textureHeight = 256;
		box1 = new ModelRenderer(this, 0, 0);
		box1.addBox(0.0F, 0.0F, 0.0F, 0, 2, 0);
		box1.setTextureSize(512, 256);
		box1.setRotationPoint(0.0F, 180.0F, 0.0F);
		box1.mirror = false;
		setRotation(box1, 0.0F, 0.0F, 0.0F);
		box2 = new ModelRenderer(this, 0, 0);
		box2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		box2.setTextureSize(512, 256);
		box2.setRotationPoint(0.0F, 0.0F, 0.0F);
		box2.mirror = false;
		setRotation(box2, 0.0F, 0.0F, 0.0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		box1.render(f5);
		box2.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		// Set rotation angles here!
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
