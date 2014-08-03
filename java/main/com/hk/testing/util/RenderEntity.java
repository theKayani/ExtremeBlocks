package main.com.hk.testing.util;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEntity extends RenderLiving
{
	public RenderEntity()
	{
		super(new ModelZombie(), 0.5F);
	}

	public RenderEntity(ModelBase par1ModelBase)
	{
		super(par1ModelBase, 0.5F);
	}

	public RenderEntity(ModelBase par1ModelBase, float size)
	{
		super(par1ModelBase, size);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1)
	{
		if (!(var1 instanceof ICustomEntity))
		{
			System.err.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.err.println("INVALID ENTITY REGISTERED! MUST BE A CUSTOM ENTITY! " + var1.getClass().getSimpleName() + " MUST IMPLEMENT \"ICustomEntity\"");
			System.err.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			return null;
		}
		return new ResourceLocation(((ICustomEntity) var1).getResourceLocation());
	}
}
