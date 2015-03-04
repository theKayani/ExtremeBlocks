package main.extremeblocks.renderers.models;

import main.extremeblocks.tileentities.TileEntityWire;
import main.extremeblocks.tileentities.energy.PowerHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cofh.api.energy.IEnergyConnection;

public class ModelWire extends ModelBase
{
	ModelRenderer wireDown;
	ModelRenderer wireMiddle;
	ModelRenderer wireUp;
	ModelRenderer wireEast;
	ModelRenderer wireNorth;
	ModelRenderer wireSouth;
	ModelRenderer wireWest;

	public ModelWire()
	{
		textureWidth = 64;
		textureHeight = 32;

		wireDown = new ModelRenderer(this, 0, 0);
		wireDown.addBox(0F, 0F, 0F, 6, 6, 6);
		wireDown.setRotationPoint(-3F, 18F, -3F);
		wireDown.setTextureSize(64, 32);
		wireDown.mirror = true;
		setRotation(wireDown, 0F, 0F, 0F);
		wireMiddle = new ModelRenderer(this, 0, 0);
		wireMiddle.addBox(0F, 0F, 0F, 6, 6, 6);
		wireMiddle.setRotationPoint(-3F, 12F, -3F);
		wireMiddle.setTextureSize(64, 32);
		wireMiddle.mirror = true;
		setRotation(wireMiddle, 0F, 0F, 0F);
		wireUp = new ModelRenderer(this, 0, 0);
		wireUp.addBox(0F, 0F, 0F, 6, 6, 6);
		wireUp.setRotationPoint(-3F, 6F, -3F);
		wireUp.setTextureSize(64, 32);
		wireUp.mirror = true;
		setRotation(wireUp, 0F, 0F, 0F);
		wireEast = new ModelRenderer(this, 0, 0);
		wireEast.addBox(0F, 0F, 0F, 6, 6, 6);
		wireEast.setRotationPoint(3F, 12F, -3F);
		wireEast.setTextureSize(64, 32);
		wireEast.mirror = true;
		setRotation(wireEast, 0F, 0F, 0F);
		wireNorth = new ModelRenderer(this, 0, 0);
		wireNorth.addBox(0F, 0F, 0F, 6, 6, 6);
		wireNorth.setRotationPoint(-3F, 12F, 3F);
		wireNorth.setTextureSize(64, 32);
		wireNorth.mirror = true;
		setRotation(wireNorth, 0F, 0F, 0F);
		wireSouth = new ModelRenderer(this, 0, 0);
		wireSouth.addBox(0F, 0F, 0F, 6, 6, 6);
		wireSouth.setRotationPoint(-3F, 12F, -9F);
		wireSouth.setTextureSize(64, 32);
		wireSouth.mirror = true;
		setRotation(wireSouth, 0F, 0F, 0F);
		wireWest = new ModelRenderer(this, 0, 0);
		wireWest.addBox(0F, 0F, 0F, 6, 6, 6);
		wireWest.setRotationPoint(-9F, 12F, -3F);
		wireWest.setTextureSize(64, 32);
		wireWest.mirror = true;
		setRotation(wireWest, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		wireDown.render(f5);
		wireMiddle.render(f5);
		wireUp.render(f5);
		wireEast.render(f5);
		wireNorth.render(f5);
		wireSouth.render(f5);
		wireWest.render(f5);
	}

	private boolean isPipe(World world, int x, int y, int z, int x1, int y1, int z1)
	{
		TileEntity t = world.getTileEntity(x, y, z);
		return t instanceof IEnergyConnection ? ((IEnergyConnection) t).canConnectEnergy(PowerHelper.getSideAt(world, t, x1, y1, z1)) : false;
	}

	public void render(TileEntityWire te)
	{
		World world = te.getWorldObj();
		int x = te.xCoord;
		int y = te.yCoord;
		int z = te.zCoord;
		float flag = 0.0625F;
		wireMiddle.render(flag);
		if (isPipe(world, x, y - 1, z, x, y, z))
		{
			wireDown.render(flag);
		}
		if (isPipe(world, x, y + 1, z, x, y, z))
		{
			wireUp.render(flag);
		}
		if (isPipe(world, x - 1, y, z, x, y, z))
		{
			wireEast.render(flag);
		}
		if (isPipe(world, x + 1, y, z, x, y, z))
		{
			wireWest.render(flag);
		}
		if (isPipe(world, x, y, z + 1, x, y, z))
		{
			wireNorth.render(flag);
		}
		if (isPipe(world, x, y, z - 1, x, y, z))
		{
			wireSouth.render(flag);
		}
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
}
