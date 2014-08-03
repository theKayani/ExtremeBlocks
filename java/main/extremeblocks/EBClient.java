package main.extremeblocks;

import main.extremeblocks.blocks.tileentities.TileEntityConsole;
import main.extremeblocks.blocks.tileentities.TileEntityFuse;
import main.extremeblocks.blocks.tileentities.TileEntityPowerEmitter;
import main.extremeblocks.blocks.tileentities.TileEntityPowerPipe;
import main.extremeblocks.blocks.tileentities.TileEntityPowerReceiver;
import main.extremeblocks.blocks.tileentities.TileEntityRewardBlock;
import main.extremeblocks.blocks.tileentities.TileEntityStorage;
import main.extremeblocks.blocks.tileentities.TileEntityTransportPipe;
import main.extremeblocks.blocks.tileentities.pipe.TileEntityAbstractPipe;
import main.extremeblocks.entities.EntityGrenade;
import main.extremeblocks.util.ItemRenderPipe;
import main.extremeblocks.util.TileEntityPipeRender;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class EBClient extends EBCommon
{
	public void registerRenderThings()
	{	
		GameRegistry.registerTileEntity(TileEntityConsole.class, "Console");
		GameRegistry.registerTileEntity(TileEntityRewardBlock.class, "RewardBlock");
		GameRegistry.registerTileEntity(TileEntityFuse.class, "Fuse");
		GameRegistry.registerTileEntity(TileEntityPowerEmitter.class, "Power Emitter");
		GameRegistry.registerTileEntity(TileEntityPowerReceiver.class, "Power Receiver");
		GameRegistry.registerTileEntity(TileEntityAbstractPipe.class, "Pipe");
		GameRegistry.registerTileEntity(TileEntityStorage.class, "Storage");

		ClientRegistry.registerTileEntity(TileEntityTransportPipe.class, "Transport Pipe", new TileEntityPipeRender("transport"));
		ClientRegistry.registerTileEntity(TileEntityPowerPipe.class, "Power Pipe", new TileEntityPipeRender("power"));

		RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderSnowball(Init.grenadeX1));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderSnowball(Init.grenadeX2));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderSnowball(Init.grenadeX4));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderSnowball(Init.grenadeX6));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderSnowball(Init.grenadeX8));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderSnowball(Init.grenadeX10));

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Init.transportPipe), new ItemRenderPipe("transport"));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Init.powerPipe), new ItemRenderPipe("power"));
	}

	public void registerSounds()
	{
	}
}
