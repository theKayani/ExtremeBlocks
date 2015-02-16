package main.extremeblocks.renderers.models;

import main.extremeblocks.tileentities.TileEntityWire;
import main.extremeblocks.tileentities.pipe.WireLogic;
import main.extremeblocks.util.IConnector;
import main.extremeblocks.util.PowerHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ModelWire extends ModelBase
{
	// fields
	ModelRenderer wireDown;
	ModelRenderer wireMiddle;
	ModelRenderer wireUp;
	ModelRenderer wireEast;
	ModelRenderer wireNorth;
	ModelRenderer wireSouth;
	ModelRenderer wireWest;
	ModelRenderer wirePSouth;
	ModelRenderer wirePWest;
	ModelRenderer wirePEast;
	ModelRenderer wirePDown;
	ModelRenderer wirePNorth;
	ModelRenderer wirePUp;

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
		wirePSouth = new ModelRenderer(this, 0, 12);
		wirePSouth.addBox(0F, 0F, 0F, 2, 2, 10);
		wirePSouth.setRotationPoint(-1F, 14F, -10F);
		wirePSouth.setTextureSize(64, 32);
		wirePSouth.mirror = true;
		setRotation(wirePSouth, 0F, 0F, 0F);
		wirePWest = new ModelRenderer(this, 0, 20);
		wirePWest.addBox(0F, 0F, 0F, 10, 2, 2);
		wirePWest.setRotationPoint(-10F, 14F, -1F);
		wirePWest.setTextureSize(64, 32);
		wirePWest.mirror = true;
		setRotation(wirePWest, 0F, 0F, 0F);
		wirePEast = new ModelRenderer(this, 0, 20);
		wirePEast.addBox(0F, 0F, 0F, 10, 2, 2);
		wirePEast.setRotationPoint(0F, 14F, -1F);
		wirePEast.setTextureSize(64, 32);
		wirePEast.mirror = true;
		setRotation(wirePEast, 0F, 0F, 0F);
		wirePDown = new ModelRenderer(this, 0, 12);
		wirePDown.addBox(0F, 0F, 0F, 2, 10, 2);
		wirePDown.setRotationPoint(-1F, 15F, -1F);
		wirePDown.setTextureSize(64, 32);
		wirePDown.mirror = true;
		setRotation(wirePDown, 0F, 0F, 0F);
		wirePNorth = new ModelRenderer(this, 0, 12);
		wirePNorth.addBox(0F, 0F, 0F, 2, 2, 10);
		wirePNorth.setRotationPoint(-1F, 14F, 0F);
		wirePNorth.setTextureSize(64, 32);
		wirePNorth.mirror = true;
		setRotation(wirePNorth, 0F, 0F, 0F);
		wirePUp = new ModelRenderer(this, 0, 12);
		wirePUp.addBox(0F, 0F, 0F, 2, 10, 2);
		wirePUp.setRotationPoint(-1F, 5F, -1F);
		wirePUp.setTextureSize(64, 32);
		wirePUp.mirror = true;
		setRotation(wirePUp, 0F, 0F, 0F);
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

		wirePSouth.render(f5);
		wirePWest.render(f5);
		wirePEast.render(f5);
		wirePDown.render(f5);
		wirePNorth.render(f5);
		wirePUp.render(f5);
	}

	private boolean isPipe(World world, int x, int y, int z, int x1, int y1, int z1)
	{
		return world.getTileEntity(x, y, z) instanceof IConnector && ((IConnector) world.getTileEntity(x, y, z)).canConnect(world, x1, y1, z1);
	}

	public void render(TileEntity te)
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
			renderPower(wirePDown, flag, (TileEntityWire) te, x, y - 1, z);
		}
		if (isPipe(world, x, y + 1, z, x, y, z))
		{
			wireUp.render(flag);
			renderPower(wirePUp, flag, (TileEntityWire) te, x, y + 1, z);
		}
		if (isPipe(world, x - 1, y, z, x, y, z))
		{
			wireEast.render(flag);
			renderPower(wirePEast, flag, (TileEntityWire) te, x - 1, y, z);
		}
		if (isPipe(world, x + 1, y, z, x, y, z))
		{
			wireWest.render(flag);
			renderPower(wirePWest, flag, (TileEntityWire) te, x + 1, y, z);
		}
		if (isPipe(world, x, y, z + 1, x, y, z))
		{
			wireNorth.render(flag);
			renderPower(wirePNorth, flag, (TileEntityWire) te, x, y, z + 1);
		}
		if (isPipe(world, x, y, z - 1, x, y, z))
		{
			wireSouth.render(flag);
			renderPower(wirePSouth, flag, (TileEntityWire) te, x, y, z - 1);
		}
	}

	private void renderPower(ModelRenderer m, float flag, TileEntityWire te, int x, int y, int z)
	{
		ForgeDirection side = PowerHelper.getSideAt(te.getWorldObj(), te, x, y, z);
		WireLogic log = WireLogic.getLogicForSide(te, side);
		if (log != null && te.overallPower > 0.0F && (log.isEmitter() || log.isReceiver() || log.isWire()))
		{
			m.render(flag);
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
