package main.extremeblocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.player.PlayerEvent.NameFormat;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EBEventHandler
{
	@SubscribeEvent
	public void onConfigChanged(OnConfigChangedEvent eventArgs) 
	{
		if(eventArgs.modID.equals(Init.MODID)) Init.handleConfig();
	}

	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer && ExtremePlayer.get((EntityPlayer) event.entity) == null)
		{
			ExtremePlayer.register((EntityPlayer) event.entity);
		}
	}
}
