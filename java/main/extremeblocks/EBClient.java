package main.extremeblocks;

import java.awt.Color;
import java.util.Map;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.Rand;
import main.extremeblocks.Vars.Mob;
import main.extremeblocks.blocks.BlockTrophy.TrophyType;
import main.extremeblocks.client.guis.GuiEBGuide;
import main.extremeblocks.entities.EntityGrenade;
import main.extremeblocks.entities.EntityMolotov;
import main.extremeblocks.entities.EntitySpear;
import main.extremeblocks.entities.mobs.EntityBloodwing;
import main.extremeblocks.entities.mobs.EntityCastleSkeleton;
import main.extremeblocks.entities.mobs.EntityCastleZombie;
import main.extremeblocks.entities.mobs.EntityDemon;
import main.extremeblocks.entities.mobs.EntityEvilIronGolem;
import main.extremeblocks.entities.mobs.EntityRobot;
import main.extremeblocks.misc.SpawnDetail;
import main.extremeblocks.renderers.ItemRendererPipe;
import main.extremeblocks.renderers.ItemRendererTile;
import main.extremeblocks.renderers.RenderBloodwing;
import main.extremeblocks.renderers.RenderCastleSkeleton;
import main.extremeblocks.renderers.RenderCastleZombie;
import main.extremeblocks.renderers.RenderDemon;
import main.extremeblocks.renderers.RenderEvilIronGolem;
import main.extremeblocks.renderers.RenderRobot;
import main.extremeblocks.renderers.RenderSpear;
import main.extremeblocks.renderers.TileEntityPlateRenderer;
import main.extremeblocks.renderers.TileEntityTrophyRenderer;
import main.extremeblocks.renderers.TileEntityWireRenderer;
import main.extremeblocks.tileentities.TileEntityCharger;
import main.extremeblocks.tileentities.TileEntityCooker;
import main.extremeblocks.tileentities.TileEntityDrill;
import main.extremeblocks.tileentities.TileEntityFuse;
import main.extremeblocks.tileentities.TileEntityGenerator;
import main.extremeblocks.tileentities.TileEntityPlate;
import main.extremeblocks.tileentities.TileEntityProtector;
import main.extremeblocks.tileentities.TileEntityRevertingStation;
import main.extremeblocks.tileentities.TileEntityStorage;
import main.extremeblocks.tileentities.TileEntityTrash;
import main.extremeblocks.tileentities.TileEntityTrophy;
import main.extremeblocks.tileentities.TileEntityWire;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class EBClient extends EBCommon
{
	@Override
	public void registerRenderThings()
	{
		GameRegistry.registerTileEntity(TileEntityFuse.class, "Fuse");
		GameRegistry.registerTileEntity(TileEntityStorage.class, "Storage");
		GameRegistry.registerTileEntity(TileEntityCharger.class, "Charger");
		GameRegistry.registerTileEntity(TileEntityCooker.class, "Cooker");
		GameRegistry.registerTileEntity(TileEntityGenerator.class, "Generator");
		GameRegistry.registerTileEntity(TileEntityProtector.class, "Protector");
		GameRegistry.registerTileEntity(TileEntityDrill.class, "Drill");
		GameRegistry.registerTileEntity(TileEntityRevertingStation.class, "Reverting Station");
		GameRegistry.registerTileEntity(TileEntityTrash.class, "Trash Can");

		ClientRegistry.registerTileEntity(TileEntityPlate.class, "Plate", new TileEntityPlateRenderer());
		ClientRegistry.registerTileEntity(TileEntityWire.class, "Wire", new TileEntityWireRenderer());
		ClientRegistry.registerTileEntity(TileEntityTrophy.class, "Trophy", new TileEntityTrophyRenderer(null));

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Init.plate), new ItemRendererTile());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Init.wire), new ItemRendererPipe());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Init.gold_trophy), new TileEntityTrophyRenderer(TrophyType.GOLD));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Init.iron_trophy), new TileEntityTrophyRenderer(TrophyType.IRON));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Init.diamond_trophy), new TileEntityTrophyRenderer(TrophyType.DIAMOND));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Init.bronze_trophy), new TileEntityTrophyRenderer(TrophyType.BRONZE));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Init.trinquantium_trophy), new TileEntityTrophyRenderer(TrophyType.TRINQUANTIUM));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Init.silver_trophy), new TileEntityTrophyRenderer(TrophyType.SILVER));

		RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderSnowball(Init.grenade));
		RenderingRegistry.registerEntityRenderingHandler(EntityBloodwing.class, new RenderBloodwing());
		RenderingRegistry.registerEntityRenderingHandler(EntityMolotov.class, new RenderSnowball(Init.molotov));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpear.class, new RenderSpear());
		RenderingRegistry.registerEntityRenderingHandler(EntityCastleZombie.class, new RenderCastleZombie());
		RenderingRegistry.registerEntityRenderingHandler(EntityCastleSkeleton.class, new RenderCastleSkeleton());
		RenderingRegistry.registerEntityRenderingHandler(EntityEvilIronGolem.class, new RenderEvilIronGolem());
		RenderingRegistry.registerEntityRenderingHandler(EntityRobot.class, new RenderRobot());
		RenderingRegistry.registerEntityRenderingHandler(EntityDemon.class, new RenderDemon());
		// ExtremeBlocks <-- command right click it
	}

	@Override
	public void openEBGuide()
	{
		Minecraft.getMinecraft().displayGuiScreen(new GuiEBGuide());
	}

	@Override
	public void registerSounds()
	{
	}

	public static void registerThrowable(Class<? extends Entity> entityClass, String mobName)
	{
		EntityRegistry.registerModEntity(entityClass, mobName, nextID(), ExtremeBlocks.instance, 80, 3, true);
	}

	public static void registerEntity(Class<? extends EntityLiving> entityClass, String mobName)
	{
		int id = nextID();
		EntityRegistry.registerGlobalEntityID(entityClass, mobName, id);
		EntityRegistry.registerModEntity(entityClass, mobName, id, ExtremeBlocks.instance, 80, 3, true);
		addMob(entityClass);
	}

	public static void registerEntity(Class<? extends EntityLiving> entityClass, String mobName, EnumCreatureType creature, Color foreground, Color background, SpawnDetail... spawns)
	{
		int id = nextID();
		EntityRegistry.registerGlobalEntityID(entityClass, mobName, id, foreground.getRGB(), background.getRGB());
		EntityRegistry.registerModEntity(entityClass, mobName, id, ExtremeBlocks.instance, 80, 3, true);
		if (spawns != null && spawns.length > 0)
		{
			for (SpawnDetail spawn : spawns)
			{
				if (spawn != null)
				{
					EntityRegistry.addSpawn(entityClass, spawn.chanceToSpawn, spawn.minAmountToSpawn, spawn.maxAmountToSpawn, creature, spawn.biome);
				}
			}
		}
		addMob(entityClass);
	}

	public static void registerEntity(Class<? extends EntityLiving> entityClass, String mobName, EnumCreatureType creature, SpawnDetail... spawns)
	{
		Color color = new Color(Rand.nextInt(256), Rand.nextInt(256), Rand.nextInt(256));
		switch (creature)
		{
			case ambient:
				EBClient.registerEntity(entityClass, mobName, creature, color, color, spawns);
				break;
			case creature:
				EBClient.registerEntity(entityClass, mobName, creature, Color.WHITE, Color.BLACK, spawns);
				break;
			case monster:
				EBClient.registerEntity(entityClass, mobName, creature, Color.BLACK, Color.WHITE, spawns);
				break;
			case waterCreature:
				EBClient.registerEntity(entityClass, mobName, creature, Color.BLUE.brighter(), Color.BLUE.darker(), spawns);
				break;
		}
	}

	private static int nextID()
	{
		int i = entityID++;
		if (EntityList.getStringFromID(i) != null) return nextID();
		return i;
	}

	private static void addMob(Class<? extends EntityLivingBase> clazz)
	{
		if (clazz.isAnnotationPresent(Mob.class))
		{
			removeMobs.put(clazz, new Boolean(false));
		}
		else throw new IllegalArgumentException(clazz.getSimpleName() + " does not have the Mob.class annotation");
	}

	private static int entityID = 1;
	public static Map<Class<?>, Boolean> removeMobs = JavaHelp.newHashMap();
}
