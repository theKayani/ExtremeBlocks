package main.extremeblocks;

import main.com.hk.testing.util.MPUtil;
import main.extremeblocks.entities.mobs.EntityEvilIronGolem;
import main.extremeblocks.entities.mobs.EntityRobot;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EBEventHandler
{
	@SubscribeEvent
	public void onLivingDrops(LivingDropsEvent event)
	{
		boolean isArrow = event.source.damageType == "arrow" && ((EntityArrow) event.source.getSourceOfDamage()).shootingEntity instanceof EntityRobot;
		if (event.source.getSourceOfDamage() instanceof EntityRobot || isArrow)
		{
			EntityRobot robot = (EntityRobot) (isArrow ? ((EntityArrow) event.source.getSourceOfDamage()).shootingEntity : event.source.getSourceOfDamage());
			if (robot.onTask)
			{
				for (EntityItem stack : event.drops)
				{
					robot.inv.addItemStack(stack.getEntityItem());
				}
				event.setResult(Result.DENY);
			}
		}
	}

	@SubscribeEvent
	public void onConfigChanged(OnConfigChangedEvent event)
	{
		if (event.modID.equals(Init.MODID)) Init.handleConfig();
		if (!Vars.addMobs)
		{
			Vars.addRobot = Vars.addEvilIronGolem = Vars.addCastleZombie = Vars.addCastleSkeleton = false;
		}
	}

	@SubscribeEvent
	public void onPlayerSetBlock(PlayerInteractEvent event)
	{
		if (MPUtil.isClientSide()) return;
		boolean spawn = false;
		if (event.action == Action.RIGHT_CLICK_BLOCK)
		{
			if (event.entityPlayer.getHeldItem() != null && Block.getBlockFromItem(event.entityPlayer.getHeldItem().getItem()) == Blocks.pumpkin)
			{
				boolean atY = event.world.getBlock(event.x, event.y, event.z) == Init.trinquantium_block && event.world.getBlock(event.x, event.y - 1, event.z) == Init.trinquantium_block;
				boolean atX = event.world.getBlock(event.x - 1, event.y, event.z) == Init.trinquantium_block && event.world.getBlock(event.x + 1, event.y, event.z) == Init.trinquantium_block;
				boolean atZ = event.world.getBlock(event.x, event.y, event.z - 1) == Init.trinquantium_block && event.world.getBlock(event.x, event.y, event.z + 1) == Init.trinquantium_block;
				System.err.println("Vars: " + atY + ", " + atX + ", " + atZ);
				if (atY && atX)
				{
					spawn = true;
					event.world.setBlockToAir(event.x - 1, event.y, event.z);
					event.world.setBlockToAir(event.x + 1, event.y, event.z);
				}
				else if (atY && atZ)
				{
					spawn = true;
					event.world.setBlockToAir(event.x, event.y, event.z - 1);
					event.world.setBlockToAir(event.x, event.y, event.z + 1);
				}
				if (spawn)
				{
					event.world.setBlockToAir(event.x, event.y + 1, event.z);
					event.world.setBlockToAir(event.x, event.y, event.z);
					event.world.setBlockToAir(event.x, event.y - 1, event.z);
					EntityEvilIronGolem evilIronGolem = new EntityEvilIronGolem(event.world);
					evilIronGolem.setLocationAndAngles(event.x + 0.5D, event.y - 0.95D, event.z + 0.5D, 0.0F, 0.0F);
					event.world.spawnEntityInWorld(evilIronGolem);
				}
			}
		}
	}
}
