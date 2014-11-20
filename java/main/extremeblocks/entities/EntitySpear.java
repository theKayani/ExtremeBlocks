package main.extremeblocks.entities;

import io.netty.buffer.ByteBuf;
import main.extremeblocks.Init;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntitySpear extends EntityArrow implements IProjectile, IEntityAdditionalSpawnData
{
	public EntitySpear(World world)
	{
		super(world);
	}

	public EntitySpear(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}

	public EntitySpear(World world, EntityLivingBase shooter, EntityLivingBase target, float speed, float damage)
	{
		super(world, shooter, target, speed, damage);
	}

	public EntitySpear(World world, EntityLivingBase shooter, float speed)
	{
		super(world, shooter, speed);
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer p_70100_1_)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		boolean inGround = nbt.getBoolean("inGround");
		if (!worldObj.isRemote && inGround && arrowShake <= 0)
		{
			boolean flag = canBePickedUp == 1 || canBePickedUp == 2 && p_70100_1_.capabilities.isCreativeMode;

			if (canBePickedUp == 1 && !p_70100_1_.inventory.addItemStackToInventory(new ItemStack(Init.spear, 1)))
			{
				flag = false;
			}

			if (flag)
			{
				playSound("random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
				p_70100_1_.onItemPickup(this, 1);
				setDead();
			}
		}
	}

	@Override
	public void writeSpawnData(ByteBuf buffer)
	{
		buffer.writeInt(shootingEntity != null ? shootingEntity.getEntityId() : -1);
	}

	@Override
	public void readSpawnData(ByteBuf buffer)
	{
		Entity shooter = worldObj.getEntityByID(buffer.readInt());
		if (shooter instanceof EntityLivingBase)
		{
			shootingEntity = shooter;
		}
	}
}