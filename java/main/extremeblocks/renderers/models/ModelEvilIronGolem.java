package main.extremeblocks.renderers.models;

import main.extremeblocks.entities.mobs.EntityEvilIronGolem;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelEvilIronGolem extends ModelBase
{
	public ModelRenderer ironGolemHead;
	public ModelRenderer ironGolemBody;
	public ModelRenderer ironGolemRightArm;
	public ModelRenderer ironGolemLeftArm;
	public ModelRenderer ironGolemLeftLeg;
	public ModelRenderer ironGolemRightLeg;

	public ModelEvilIronGolem()
	{
		this(0.0F);
	}

	public ModelEvilIronGolem(float scale)
	{
		this(scale, -7.0F);
	}

	public ModelEvilIronGolem(float saleX, float saleY)
	{
		short short1 = 128;
		short short2 = 128;
		this.ironGolemHead = (new ModelRenderer(this)).setTextureSize(short1, short2);
		this.ironGolemHead.setRotationPoint(0.0F, 0.0F + saleY, -2.0F);
		this.ironGolemHead.setTextureOffset(0, 0).addBox(-4.0F, -12.0F, -5.5F, 8, 10, 8, saleX);
		this.ironGolemHead.setTextureOffset(24, 0).addBox(-1.0F, -5.0F, -7.5F, 2, 4, 2, saleX);
		this.ironGolemBody = (new ModelRenderer(this)).setTextureSize(short1, short2);
		this.ironGolemBody.setRotationPoint(0.0F, 0.0F + saleY, 0.0F);
		this.ironGolemBody.setTextureOffset(0, 40).addBox(-9.0F, -2.0F, -6.0F, 18, 12, 11, saleX);
		this.ironGolemBody.setTextureOffset(0, 70).addBox(-4.5F, 10.0F, -3.0F, 9, 5, 6, saleX + 0.5F);
		this.ironGolemRightArm = (new ModelRenderer(this)).setTextureSize(short1, short2);
		this.ironGolemRightArm.setRotationPoint(0.0F, -7.0F, 0.0F);
		this.ironGolemRightArm.setTextureOffset(60, 21).addBox(-13.0F, -2.5F, -3.0F, 4, 30, 6, saleX);
		this.ironGolemLeftArm = (new ModelRenderer(this)).setTextureSize(short1, short2);
		this.ironGolemLeftArm.setRotationPoint(0.0F, -7.0F, 0.0F);
		this.ironGolemLeftArm.setTextureOffset(60, 58).addBox(9.0F, -2.5F, -3.0F, 4, 30, 6, saleX);
		this.ironGolemLeftLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(short1, short2);
		this.ironGolemLeftLeg.setRotationPoint(-4.0F, 18.0F + saleY, 0.0F);
		this.ironGolemLeftLeg.setTextureOffset(37, 0).addBox(-3.5F, -3.0F, -3.0F, 6, 16, 5, saleX);
		this.ironGolemRightLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(short1, short2);
		this.ironGolemRightLeg.mirror = true;
		this.ironGolemRightLeg.setTextureOffset(60, 0).setRotationPoint(5.0F, 18.0F + saleY, 0.0F);
		this.ironGolemRightLeg.addBox(-3.5F, -3.0F, -3.0F, 6, 16, 5, saleX);
	}

	public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float f6)
	{
		this.setRotationAngles(f1, f2, f3, f4, f5, f6, entity);
		this.ironGolemHead.render(f6);
		this.ironGolemBody.render(f6);
		this.ironGolemLeftLeg.render(f6);
		this.ironGolemRightLeg.render(f6);
		this.ironGolemRightArm.render(f6);
		this.ironGolemLeftArm.render(f6);
	}

	public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float f6, Entity entity)
	{
		this.ironGolemHead.rotateAngleY = f4 / (180F / (float) Math.PI);
		this.ironGolemHead.rotateAngleX = f5 / (180F / (float) Math.PI);
		this.ironGolemLeftLeg.rotateAngleX = -1.5F * this.getMathDone(f1, 13.0F) * f2;
		this.ironGolemRightLeg.rotateAngleX = 1.5F * this.getMathDone(f1, 13.0F) * f2;
		this.ironGolemLeftLeg.rotateAngleY = 0.0F;
		this.ironGolemRightLeg.rotateAngleY = 0.0F;
	}

	public void setLivingAnimations(EntityLivingBase entity, float x, float y, float z)
	{
		EntityEvilIronGolem entityirongolem = (EntityEvilIronGolem) entity;
		int i = entityirongolem.attackTimer;
		if (i > 0)
		{
			this.ironGolemRightArm.rotateAngleX = -2.0F + 1.5F * this.getMathDone((float) i - z, 10.0F);
			this.ironGolemLeftArm.rotateAngleX = -2.0F + 1.5F * this.getMathDone((float) i - z, 10.0F);
		}
		else
		{
			this.ironGolemRightArm.rotateAngleX = (-0.2F + 1.5F * this.getMathDone(x, 13.0F)) * y;
			this.ironGolemLeftArm.rotateAngleX = (-0.2F - 1.5F * this.getMathDone(x, 13.0F)) * y;
		}
	}

	private float getMathDone(float x, float z)
	{
		return (Math.abs(x % z - z * 0.5F) - z * 0.25F) / (z * 0.25F);
	}
}