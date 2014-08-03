package main.extremeblocks.entities;

import main.com.hk.testing.util.MPUtil;
import main.com.hk.testing.util.Rand;
import main.extremeblocks.network.PacketGrenade;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityGrenade extends EntityThrowable
{
	public float power;

	public EntityGrenade(World par1World)
	{
		super(par1World);
	}

	public EntityGrenade(World par1World, EntityLivingBase par2EntityLivingBase)
	{
		super(par1World, par2EntityLivingBase);
	}

	public EntityGrenade(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
	}
	
	public EntityGrenade(World par1World, EntityLivingBase par2EntityLivingBase, float power)
	{
		super(par1World, par2EntityLivingBase);
		this.power = power;
	}

	protected void onImpact(MovingObjectPosition mop)
	{
		switch (mop.sideHit)
		{
			case 0: // BOTTOM
				mop.blockY--;
				break;
			case 1: // TOP
				mop.blockY++;
				break;
			case 2: // EAST
				mop.blockZ--;
				break;
			case 3: // WEST
				mop.blockZ++;
				break;
			case 4: // NORTH
				mop.blockX--;
				break;
			case 5: // SOUTH
				mop.blockX++;
				break;
		}
		if (MPUtil.isServerSide())
		{
			this.worldObj.newExplosion(this, mop.blockX, mop.blockY, mop.blockZ, power, true, false);
			this.setDead();
		}
	}
}
