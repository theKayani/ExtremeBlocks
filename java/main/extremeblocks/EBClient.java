package main.extremeblocks;

import main.extremeblocks.blocks.tileentities.TileEntityConsole;
import main.extremeblocks.blocks.tileentities.TileEntityFuse;
import main.extremeblocks.blocks.tileentities.TileEntityPlate;
import main.extremeblocks.blocks.tileentities.TileEntityRewardBlock;
import main.extremeblocks.blocks.tileentities.TileEntityStorage;
import main.extremeblocks.entities.EntityGrenade;
import main.extremeblocks.entities.EntityMolotov;
import main.extremeblocks.entities.mobs.EntityCastleSkeleton;
import main.extremeblocks.entities.mobs.EntityCastleZombie;
import main.extremeblocks.entities.mobs.EntityEvilIronGolem;
import main.extremeblocks.entities.mobs.EntityRobot;
import main.extremeblocks.renderers.ItemRendererTile;
import main.extremeblocks.renderers.RenderCastleSkeleton;
import main.extremeblocks.renderers.RenderCastleZombie;
import main.extremeblocks.renderers.RenderEvilIronGolem;
import main.extremeblocks.renderers.RenderRobot;
import main.extremeblocks.renderers.TileEntityPlateRenderer;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
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
		GameRegistry.registerTileEntity(TileEntityRewardBlock.class, "RewardBlock");
		GameRegistry.registerTileEntity(TileEntityFuse.class, "Fuse");
		GameRegistry.registerTileEntity(TileEntityStorage.class, "Storage");

		ClientRegistry.registerTileEntity(TileEntityPlate.class, "Plate", new TileEntityPlateRenderer());

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Init.plate), new ItemRendererTile());

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

	public static void registerEntity(Class<? extends EntityLiving> entityClass, String mobName, EnumCreatureType creature, BiomeGenBase... biomes)
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
		if (biomes != null && biomes.length != 0)
		{
			for (int i = 0; i < biomes.length; i++)
			{
				if (biomes[i] != null)
				{
					EntityRegistry.addSpawn(entityClass, 10, 1, 3, creature, BiomeGenBase.getBiomeGenArray()[i]);
				}
			}
		}
	}
}
