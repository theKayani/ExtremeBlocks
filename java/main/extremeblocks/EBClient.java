package main.extremeblocks;

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
import main.extremeblocks.tileentities.TileEntityPlate;
import main.extremeblocks.tileentities.TileEntityTrophy;
import main.extremeblocks.tileentities.TileEntityWire;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class EBClient extends EBCommon
{
	@Override
	public void registerRenderThings()
	{
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
}
