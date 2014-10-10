package main.extremeblocks;

import main.extremeblocks.blocks.BlockMedal.MedalType;
import main.extremeblocks.entities.EntityGrenade;
import main.extremeblocks.entities.EntityMolotov;
import main.extremeblocks.entities.mobs.EntityCastleSkeleton;
import main.extremeblocks.entities.mobs.EntityCastleZombie;
import main.extremeblocks.entities.mobs.EntityEvilIronGolem;
import main.extremeblocks.entities.mobs.EntityRobot;
import main.extremeblocks.renderers.ItemRendererPipe;
import main.extremeblocks.renderers.ItemRendererTile;
import main.extremeblocks.renderers.RenderCastleSkeleton;
import main.extremeblocks.renderers.RenderCastleZombie;
import main.extremeblocks.renderers.RenderEvilIronGolem;
import main.extremeblocks.renderers.RenderRobot;
import main.extremeblocks.renderers.TileEntityMedalRenderer;
import main.extremeblocks.renderers.TileEntityPlateRenderer;
import main.extremeblocks.renderers.TileEntityWireRenderer;
import main.extremeblocks.tileentities.TileEntityCharger;
import main.extremeblocks.tileentities.TileEntityConsole;
import main.extremeblocks.tileentities.TileEntityCooker;
import main.extremeblocks.tileentities.TileEntityDrill;
import main.extremeblocks.tileentities.TileEntityFuse;
import main.extremeblocks.tileentities.TileEntityGenerator;
import main.extremeblocks.tileentities.TileEntityMedal;
import main.extremeblocks.tileentities.TileEntityPlate;
import main.extremeblocks.tileentities.TileEntityProtector;
import main.extremeblocks.tileentities.TileEntityRevertingStation;
import main.extremeblocks.tileentities.TileEntityRewardBlock;
import main.extremeblocks.tileentities.TileEntityStorage;
import main.extremeblocks.tileentities.TileEntityWire;
import main.extremeblocks.util.SpawnDetail;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.EntityLiving;
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
		GameRegistry.registerTileEntity(TileEntityConsole.class, "Console");
		GameRegistry.registerTileEntity(TileEntityRewardBlock.class, "Reward Block");
		GameRegistry.registerTileEntity(TileEntityFuse.class, "Fuse");
		GameRegistry.registerTileEntity(TileEntityStorage.class, "Storage");
		GameRegistry.registerTileEntity(TileEntityCharger.class, "Charger");
		GameRegistry.registerTileEntity(TileEntityCooker.class, "Cooker");
		GameRegistry.registerTileEntity(TileEntityGenerator.class, "Generator");
		GameRegistry.registerTileEntity(TileEntityProtector.class, "Protector");
		GameRegistry.registerTileEntity(TileEntityDrill.class, "Drill");
		GameRegistry.registerTileEntity(TileEntityRevertingStation.class, "Reverting Station");

		ClientRegistry.registerTileEntity(TileEntityPlate.class, "Plate", new TileEntityPlateRenderer());
		ClientRegistry.registerTileEntity(TileEntityWire.class, "Wire", new TileEntityWireRenderer());
		ClientRegistry.registerTileEntity(TileEntityMedal.class, "Medal", new TileEntityMedalRenderer(null));

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Init.plate), new ItemRendererTile());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Init.wire), new ItemRendererPipe());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Init.gold_medal), new TileEntityMedalRenderer(MedalType.GOLD));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Init.iron_medal), new TileEntityMedalRenderer(MedalType.IRON));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Init.diamond_medal), new TileEntityMedalRenderer(MedalType.DIAMOND));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Init.bronze_medal), new TileEntityMedalRenderer(MedalType.BRONZE));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Init.trinquantium_medal), new TileEntityMedalRenderer(MedalType.TRINQUANTIUM));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Init.silver_medal), new TileEntityMedalRenderer(MedalType.SILVER));

		RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderSnowball(Init.grenade));
		RenderingRegistry.registerEntityRenderingHandler(EntityMolotov.class, new RenderSnowball(Init.molotov));
		RenderingRegistry.registerEntityRenderingHandler(EntityCastleZombie.class, new RenderCastleZombie());
		RenderingRegistry.registerEntityRenderingHandler(EntityCastleSkeleton.class, new RenderCastleSkeleton());
		RenderingRegistry.registerEntityRenderingHandler(EntityEvilIronGolem.class, new RenderEvilIronGolem());
		RenderingRegistry.registerEntityRenderingHandler(EntityRobot.class, new RenderRobot());
		// ExtremeBlocks <-- command right click it
	}

	@Override
	public void registerSounds()
	{
	}

	public static void registerEntity(Class<? extends EntityLiving> entityClass, String mobName, EnumCreatureType creature, SpawnDetail... spawns)
	{
		int id = EntityRegistry.findGlobalUniqueEntityId();
		if (creature == EnumCreatureType.monster)
		{
			EntityRegistry.registerGlobalEntityID(entityClass, mobName, id, 0, 16777215);
		}
		else
		{
			EntityRegistry.registerGlobalEntityID(entityClass, mobName, id, 16777215, 0);
		}
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
	}
}
