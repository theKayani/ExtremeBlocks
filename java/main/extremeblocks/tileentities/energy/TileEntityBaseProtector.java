package main.extremeblocks.tileentities.energy;

import java.text.DecimalFormat;
import java.util.List;
import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.blocks.energy.BlockBaseProtector.ProtectorUpgrade;
import main.extremeblocks.client.containers.Slots;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyContainerItem;

public class TileEntityBaseProtector extends TileEntityMachine
{
	private int counter;

	public TileEntityBaseProtector()
	{
		super("Base Protector");
		inventory = new ItemStack[ProtectorUpgrade.values().length + 4];
	}

	@Override
	public boolean update()
	{
		int stored = getEnergyStored();
		calculatePowerFromInventory();
		if (counter++ > getModifier(ProtectorUpgrade.SPEED) && getEnergyStored() > getModifier(ProtectorUpgrade.ENERGY_REDUCTION))
		{
			double x = xCoord + 0.5D;
			double y = yCoord + 0.5D;
			double z = zCoord + 0.5D;
			double add = getModifier(ProtectorUpgrade.RADIUS);
			AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(x - add, y - add, z - add, x + add, y + add, z + add);
			List<?> mobs = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, aabb);
			for (Object mob : mobs)
			{
				if (mob instanceof IMob && mob instanceof EntityLivingBase)
				{
					EntityLivingBase enemy = (EntityLivingBase) mob;
					EntityArrow arrow = getArrow(enemy.posX, enemy.posY + enemy.height + 1.0D, enemy.posZ);
					if (enemy.canEntityBeSeen(arrow) && getEnergyStored() > getModifier(ProtectorUpgrade.ENERGY_REDUCTION))
					{
						if (MPUtil.isServerSide())
						{
							worldObj.spawnEntityInWorld(arrow);
						}
						storage.extractEnergy(getModifier(ProtectorUpgrade.ENERGY_REDUCTION), false);
					}
				}
			}
			counter = 0;
		}
		return stored != getEnergyStored();
	}

	public EntityArrow getArrow(double x, double y, double z)
	{
		EntityArrow arrow = new EntityArrow(worldObj, x, y, z);
		arrow.setDamage(getModifier(ProtectorUpgrade.DAMAGE));
		arrow.setKnockbackStrength(getModifier(ProtectorUpgrade.KNOCKBACK));
		return arrow;
	}

	public int getModifier(ProtectorUpgrade upgrade)
	{
		return upgrade.getAmount(inventory[upgrade.ordinal()]);
	}

	@Override
	public int[] getBatterySlots()
	{
		return new int[] { 5, 6, 7, 8 };
	}

	@Override
	public List<Slot> getSlots()
	{
		List<Slot> s = new Slots(this, 17, 21, 5, 1);
		s.addAll(new Slots(this, 117, 9, 2, 2).setStartID(5));
		return s;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side)
	{
		return stack.getItem() instanceof IEnergyContainerItem && ((IEnergyContainerItem) stack.getItem()).getEnergyStored(stack) == 0;
	}

	@Override
	protected int[] getSlotsFor(ForgeDirection side)
	{
		return getBatterySlots();
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		for (int i = 0; i < ProtectorUpgrade.values().length; i++)
		{
			if (ProtectorUpgrade.values()[i].isValid(stack)) return true;
		}
		return super.isItemValidForSlot(slot, stack);
	}

	@Override
	public List<String> getTips(List<String> currentList)
	{
		DecimalFormat f = new DecimalFormat("#,###");
		String stored = f.format(storage.getEnergyStored());
		String max = f.format(storage.getMaxEnergyStored());
		currentList.add(stored + "/" + max + EnumChatFormatting.RED + " RF " + EnumChatFormatting.RESET + "Held");
		return currentList;
	}

	@Override
	public boolean shouldAddTip()
	{
		return true;
	}
}
