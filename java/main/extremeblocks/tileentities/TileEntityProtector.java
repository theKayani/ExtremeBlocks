package main.extremeblocks.tileentities;

import java.util.List;
import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.util.IConnector;
import main.extremeblocks.util.IPlayerMessage;
import main.extremeblocks.util.Power.IPowerReceiver;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityProtector extends TileEntity implements IConnector, IPowerReceiver, IPlayerMessage
{
	public static final float AMOUNT_NEEDED = 50.0F;
	public int counter;
	public float overallPower;

	@SuppressWarnings("unchecked")
	@Override
	public void updateEntity()
	{
		boolean reset = false;
		counter++;
		List<EntityLiving> list = worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(-10D + xCoord, -10D + yCoord, -10D + zCoord, 10D + xCoord, 10D + yCoord, 10D + zCoord));
		for (int i = 0; i < list.size(); i++)
		{
			EntityLiving entityLiving = list.get(i);
			if (entityLiving instanceof IMob)
			{
				if (canWork() && entityLiving != null && counter > 10)
				{
					if (MPUtil.isServerSide())
					{
						EntityArrow arrow = new EntityArrow(worldObj, entityLiving.posX, entityLiving.posY + entityLiving.height + 2.0D, entityLiving.posZ);
						arrow.setDamage(arrow.getDamage() * 2.0D);
						worldObj.spawnEntityInWorld(arrow);
					}
					overallPower -= AMOUNT_NEEDED;
					reset = true;
				}
			}
		}

		if (reset)
		{
			counter = 0;
		}
	}

	@Override
	public void receivePower(ForgeDirection sideReceived, float power)
	{
		overallPower += power;
	}

	public boolean canWork()
	{
		return overallPower > AMOUNT_NEEDED;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		overallPower = nbt.getFloat("Overall Power");
		counter = nbt.getInteger("Counter");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setFloat("Overall Power", overallPower);
		nbt.setInteger("Counter", counter);
	}

	@Override
	public boolean canConnect(World world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public boolean onClickedOn(EntityPlayer player)
	{
		MPUtil.sendMessage("Overall Power: " + overallPower, player);
		return true;
	}
}
