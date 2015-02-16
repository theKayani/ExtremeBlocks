package main.extremeblocks.renderers.models;

import main.extremeblocks.util.IConnector;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ModelPipe extends ModelBase
{
	ModelRenderer pipeMiddle;
	ModelRenderer pipeDown;
	ModelRenderer pipeWest;
	ModelRenderer pipeNorth;
	ModelRenderer pipeEast;
	ModelRenderer pipeSouth;
	ModelRenderer pipeUp;

	public ModelPipe()
	{
		textureWidth = 64;
		textureHeight = 32;
		pipeMiddle = new ModelRenderer(this, 0, 0);
		pipeMiddle.addBox(0F, 0F, 0F, 7, 7, 7);
		pipeMiddle.setRotationPoint(-3.5F, 11.5F, -3.5F);
		pipeMiddle.setTextureSize(64, 32);
		setRotation(pipeMiddle, 0F, 0F, 0F);
		pipeDown = new ModelRenderer(this, 30, 0);
		pipeDown.addBox(0F, 0F, 0F, 5, 5, 7);
		pipeDown.setRotationPoint(-2.5F, 25.5F, -2.5F);
		pipeDown.setTextureSize(64, 32);
		setRotation(pipeDown, 1.570796F, 0F, 0F);
		pipeWest = new ModelRenderer(this, 30, 0);
		pipeWest.addBox(0F, 0F, 0F, 5, 5, 7);
		pipeWest.setRotationPoint(-10.5F, 12.5F, 2.5F);
		pipeWest.setTextureSize(64, 32);
		setRotation(pipeWest, 0F, 1.570796F, 0F);
		pipeNorth = new ModelRenderer(this, 30, 0);
		pipeNorth.addBox(0F, 0F, 0F, 5, 5, 7);
		pipeNorth.setRotationPoint(-2.5F, 12.5F, 3.5F);
		pipeNorth.setTextureSize(64, 32);
		setRotation(pipeNorth, 0F, 0F, 0F);
		pipeEast = new ModelRenderer(this, 30, 0);
		pipeEast.addBox(0F, 0F, 0F, 5, 5, 7);
		pipeEast.setRotationPoint(3.5F, 12.5F, 2.5F);
		pipeEast.setTextureSize(64, 32);
		setRotation(pipeEast, 0F, 1.570796F, 0F);
		pipeSouth = new ModelRenderer(this, 30, 0);
		pipeSouth.addBox(0F, 0F, 0F, 5, 5, 7);
		pipeSouth.setRotationPoint(-2.5F, 12.5F, -10.5F);
		pipeSouth.setTextureSize(64, 32);
		setRotation(pipeSouth, 0F, 0F, 0F);
		pipeUp = new ModelRenderer(this, 30, 0);
		pipeUp.addBox(0F, 0F, 0F, 5, 5, 7);
		pipeUp.setRotationPoint(-2.5F, 11.5F, -2.5F);
		pipeUp.setTextureSize(64, 32);
		setRotation(pipeUp, 1.570796F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		pipeMiddle.render(f5);
		pipeDown.render(f5);
		pipeWest.render(f5);
		pipeNorth.render(f5);
		pipeEast.render(f5);
		pipeSouth.render(f5);
		pipeUp.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	private boolean isPipe(World world, int x, int y, int z)
	{
		return world.getTileEntity(x, y, z) instanceof IConnector;
	}

	public void render(TileEntity te)
	{
		World world = te.getWorldObj();
		int x = te.xCoord;
		int y = te.yCoord;
		int z = te.zCoord;
		float flag = 0.0625F;
		pipeMiddle.render(flag);
		if (isPipe(world, x, y - 1, z))
		{
			pipeDown.render(flag);
		}
		if (isPipe(world, x, y + 1, z))
		{
			pipeUp.render(flag);
		}
		if (isPipe(world, x - 1, y, z))
		{
			pipeEast.render(flag);
		}
		if (isPipe(world, x + 1, y, z))
		{
			pipeWest.render(flag);
		}
		if (isPipe(world, x, y, z + 1))
		{
			pipeNorth.render(flag);
		}
		if (isPipe(world, x, y, z - 1))
		{
			pipeSouth.render(flag);
		}
	}
}