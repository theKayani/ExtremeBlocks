package main.extremeblocks.blocks.tileentities;

import main.com.hk.testing.util.MPUtil;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.blocks.tileentities.builder.Builder;
import main.extremeblocks.network.AbstractPacket;
import main.extremeblocks.network.IPacketReceiver;
import main.extremeblocks.network.PacketPowerReceiver;
import main.extremeblocks.util.IPlayerMessage;
import main.extremeblocks.util.IPower.IPowerReceiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPowerReceiver extends TileEntity implements IPowerReceiver, IPlayerMessage, IPacketReceiver<PacketPowerReceiver>
{
	public Builder builder;
	public boolean isBuilding;
	public int buildingMode;
	public ForgeDirection sideToBuild = ForgeDirection.UNKNOWN;
	public int power, stage, counter;
	public int baseX, baseY, baseZ;
	public boolean done;

	public void updateEntity()
	{
		if (MPUtil.isServerSide())
		{
			if(done) return;
			MPUtil.sendToAll(new PacketPowerReceiver(this));
			
			baseX = xCoord + sideToBuild.offsetX * Builder.getDistance(buildingMode);
			baseY = yCoord + sideToBuild.offsetY * Builder.getDistance(buildingMode);
			baseZ = zCoord + sideToBuild.offsetZ * Builder.getDistance(buildingMode);
			builder = new Builder(worldObj, baseX, baseY, baseZ);

			if (counter++ >= 50 && isBuilding)
			{
				if(power >= builder.getPowerNeeded(buildingMode))
				{
					builder.buildStructure(buildingMode, stage);
					power -= builder.getPowerNeeded(buildingMode);
					stage++;
					counter = 0;
				}
			}

			if (power < 0) power = 0;
			done = builder.done;
		}
	}

	@Override
	public void receivePower(ForgeDirection sideReceived, int power)
	{
		if (MPUtil.isServerSide())
		{
			if (sideReceived == ForgeDirection.DOWN || sideReceived == ForgeDirection.UP) return;

			this.sideToBuild = sideReceived;
			this.power += power;
		}
	}

	@Override
	public boolean onClickedOn(EntityPlayer player)
	{
		if(MPUtil.isServerSide() && player instanceof EntityPlayerMP) MPUtil.sendTo(new PacketPowerReceiver(this), (EntityPlayerMP) player);
		player.openGui(ExtremeBlocks.instance, 3, worldObj, xCoord, yCoord, zCoord);
		return true;
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);

		nbt.setBoolean("Is Building", isBuilding);    
		nbt.setInteger("Building Mode", buildingMode);
		nbt.setInteger("Power", power);               
		nbt.setInteger("Stage", stage);               
		nbt.setInteger("Counter", counter);           
		nbt.setInteger("Base X", baseX);              
		nbt.setInteger("Base Y", baseY);              
		nbt.setInteger("Base Z", baseZ);              
		nbt.setBoolean("Done", done);
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);

		this.isBuilding = nbt.getBoolean("Is Building");                             
		this.buildingMode = nbt.getInteger("Building Mode");                                   
		this.power = nbt.getInteger("Power");
		this.stage = nbt.getInteger("Stage");
		this.counter = nbt.getInteger("Counter");                     
		this.baseX = nbt.getInteger("Base X");
		this.baseY = nbt.getInteger("Base Y");
		this.baseZ = nbt.getInteger("Base Z");                            
		this.done = nbt.getBoolean("Done");   
	}
	
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

	@Override
	public void receivePacket(PacketPowerReceiver packet)
	{
		isBuilding = packet.isBuilding;
		buildingMode = packet.buildingMode;
		power = packet.power;
		stage = packet.stage;
		counter = packet.counter;
		baseX = packet.baseX;
		baseY = packet.baseY;
		baseZ = packet.baseZ;
		done = packet.done;
	}
}
