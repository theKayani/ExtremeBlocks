package main.extremeblocks.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelSoldier extends ModelBase
{
	// fields
	ModelRenderer leftLeg;
	ModelRenderer rightLeg;
	ModelRenderer body;
	ModelRenderer head;
	ModelRenderer leftArm;
	ModelRenderer rightArm;

	public ModelSoldier()
	{
		textureWidth = 64;
		textureHeight = 32;

		leftLeg = new ModelRenderer(this, 0, 0);
		leftLeg.addBox(0F, 0F, 0F, 1, 2, 1);
		leftLeg.setRotationPoint(0F, 22F, 0F);
		leftLeg.setTextureSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0F, 0F, 0F);
		rightLeg = new ModelRenderer(this, 0, 0);
		rightLeg.addBox(0F, 0F, 0F, 1, 2, 1);
		rightLeg.setRotationPoint(-1F, 22F, 0F);
		rightLeg.setTextureSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0F, 0F, 0F);
		body = new ModelRenderer(this, 0, 0);
		body.addBox(0F, 0F, 0F, 2, 4, 1);
		body.setRotationPoint(-1F, 18F, 0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(0F, 0F, 0F, 1, 1, 1);
		head.setRotationPoint(-0.5F, 17F, 0F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		leftArm = new ModelRenderer(this, 0, 0);
		leftArm.addBox(0F, 0F, 0F, 1, 3, 1);
		leftArm.setRotationPoint(1F, 18F, 0F);
		leftArm.setTextureSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0F, 0F, 0F);
		rightArm = new ModelRenderer(this, 0, 0);
		rightArm.addBox(0F, 0F, 0F, 1, 3, 1);
		rightArm.setRotationPoint(-2F, 18F, 0F);
		rightArm.setTextureSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		leftLeg.render(f5);
		rightLeg.render(f5);
		body.render(f5);
		head.render(f5);
		leftArm.render(f5);
		rightArm.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
		this.head.rotateAngleY = par4 / (180F / (float) Math.PI);
		this.rightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 2.0F * par2 * 0.5F;
		this.leftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
		this.rightArm.rotateAngleZ = 0.0F;
		this.leftArm.rotateAngleZ = 0.0F;
		this.rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.rightLeg.rotateAngleY = 0.0F;
		this.leftLeg.rotateAngleY = 0.0F;
	}

}
