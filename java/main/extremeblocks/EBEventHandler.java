package main.extremeblocks;

import main.com.hk.eb.util.MPUtil;
import main.com.hk.eb.util.Rand;
import main.extremeblocks.entities.mobs.EntityEvilIronGolem;
import main.extremeblocks.entities.mobs.EntityRobot;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EBEventHandler
{
	private static boolean sent = false;

	@SubscribeEvent
	public void onEntityJoin(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			if (VersionChecker.shouldUpdate())
			{
				if (!sent && Vars.checkVersion)
				{
					sent = true;
					MPUtil.sendMessage("New EB Version Available: " + VersionChecker.getNewVersion() + ", Log: " + VersionChecker.getMessage(), (EntityPlayer) event.entity);
				}
				else if (!sent)
				{
					Vars.logger.info("Checking Versions was disabled.");
				}
			}
			else
			{
				Vars.logger.info("No New Version for Extreme Block found! Good Job!");
			}
		}
	}

	@SubscribeEvent
	public void onBlockBreak(BreakEvent event)
	{
		if (event.block == Blocks.double_plant)
		{
			if (event.world.rand.nextInt(10) == 0)
			{
				MPUtil.dropItemAsEntity(event.world, event.x, event.y, event.z, false, new ItemStack(Rand.nextBoolean() ? Init.cucumber_seeds : Init.tomato_seeds));
			}
		}
		if (event.block == Blocks.leaves)
		{
			if (event.world.rand.nextInt(10) == 0)
			{
				Item[] stacks = new Item[] { Init.banana, Init.grapes, Init.peach, Init.orange };
				MPUtil.dropItemAsEntity(event.world, event.x, event.y, event.z, false, new ItemStack(stacks[Rand.nextInt(stacks.length)]));
			}
		}
	}

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
		if (event.modID.equals(Init.MODID))
		{
			Init.handleConfig();
		}
		if (!Vars.addMobs)
		{
			Vars.addDemon = Vars.addRobot = Vars.addEvilIronGolem = Vars.addCastleZombie = Vars.addCastleSkeleton = false;
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
