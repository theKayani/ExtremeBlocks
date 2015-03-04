package main.com.hk.eb.util;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileEntityEB extends TileEntity
{
	public abstract void readFrom(NBTTagCompound nbt);

	public abstract void writeTo(NBTTagCompound nbt);

	public abstract boolean update();

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		readFrom(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		writeTo(nbt);
	}

	@Override
	public void updateEntity()
	{
		if (update())
		{
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}

	public void onNeighborBlockChange(Block block)
	{
	}

	public boolean onBlockActivated(EntityPlayer player, ForgeDirection side, Vector3F sideHit)
	{
		return false;
	}

	public void onBlockAdded()
	{

	}

	public void onBlockPlacedBy(EntityLivingBase entity, ItemStack stack)
	{

	}

	public int getLightOpacity()
	{
		return worldObj.getBlock(xCoord, yCoord, zCoord).getLightOpacity();
	}

	public int getLightValue()
	{
		return worldObj.getBlock(xCoord, yCoord, zCoord).getLightValue();
	}

	public void onEntityCollidedWithBlock(Entity entity)
	{

	}

	public void breakBlock(Block block, int meta)
	{

	}

	public boolean canWrench()
	{
		return false;
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();
		writeTo(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		readFrom(pkt.func_148857_g());
	}

	public boolean isRedstonePowered()
	{
		return worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
	}
}
