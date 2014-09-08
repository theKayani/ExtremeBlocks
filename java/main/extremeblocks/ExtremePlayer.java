package main.extremeblocks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtremePlayer implements IExtendedEntityProperties
{
	public final EntityPlayer player;

	public ExtremePlayer(EntityPlayer player)
	{
		this.player = player;
	}

	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = new NBTTagCompound();
		compound.setTag(ExtremePlayer.NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(ExtremePlayer.NAME);
	}

	@Override
	public void init(Entity entity, World world)
	{
	}

	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(ExtremePlayer.NAME, new ExtremePlayer(player));
	}

	public static final ExtremePlayer get(EntityPlayer player)
	{
		return (ExtremePlayer) player.getExtendedProperties(ExtremePlayer.NAME);
	}

	public final static String NAME = ExtremePlayer.class.getSimpleName();// "ExtremePlayer";
}
