package main.extremeblocks.items;

import java.text.DecimalFormat;
import java.util.List;
import main.com.hk.eb.util.ItemCustom;
import main.extremeblocks.Init;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBattery extends ItemCustom implements IEnergyContainerItem
{
	public final int maxReceive, maxExtract, capacity;

	public ItemBattery(int capacity, int maxReceive, int maxExtract)
	{
		super("Battery", Init.tab_mainItems);
		setHasSubtypes(true);
		setMaxDamage(capacity);
		setMaxStackSize(1);
		setTextureName(Init.MODID + ":battery");
		this.capacity = capacity;
		this.maxReceive = maxReceive;
		this.maxExtract = maxExtract;
		setInfo("Can be used in EB machines as an alternative to wires. To provide RF. Redstone Flux. Upon creation, it will have zero charge. Be wary of this!");
		setShowRecipe();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean lol)
	{
		list.add(new DecimalFormat("#,###").format(getEnergyStored(stack)) + " RF Held");
		list.add("Now uses Redstone Flux!");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		ItemStack a = new ItemStack(this);
		list.add(a);
		ItemStack b = new ItemStack(this);
		b.stackTagCompound = new NBTTagCompound();
		b.stackTagCompound.setInteger("Energy", capacity);
		list.add(b);
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate)
	{
		if (container.stackTagCompound == null)
		{
			container.stackTagCompound = new NBTTagCompound();
		}
		int energy = container.stackTagCompound.getInteger("Energy");
		int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));

		if (!simulate)
		{
			energy += energyReceived;
			container.stackTagCompound.setInteger("Energy", energy);
			container.setItemDamage(getMaxEnergyStored(container) - getEnergyStored(container));
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate)
	{
		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) return 0;
		int energy = container.stackTagCompound.getInteger("Energy");
		int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));

		if (!simulate)
		{
			energy -= energyExtracted;
			container.stackTagCompound.setInteger("Energy", energy);
			container.setItemDamage(getMaxEnergyStored(container) - getEnergyStored(container));
		}
		return energyExtracted;
	}

	@Override
	public int getEnergyStored(ItemStack container)
	{
		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) return 0;
		return container.stackTagCompound.getInteger("Energy");
	}

	@Override
	public int getMaxEnergyStored(ItemStack container)
	{
		return capacity;
	}
}
