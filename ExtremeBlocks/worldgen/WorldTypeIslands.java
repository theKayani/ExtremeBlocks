package main.extremeblocks.worldgen;

import main.extremeblocks.Vars;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerDeepOcean;
import net.minecraft.world.gen.layer.GenLayerIsland;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldTypeIslands extends WorldType
{
	public WorldTypeIslands()
	{
		super("Islands");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslateName()
	{
		return this.getWorldTypeName();
	}

	public IChunkProvider getChunkGenerator(World world, String generatorOptions)
	{
		return new ChunkStrandedManager(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
	}

	@Override
	public boolean isCustomizable()
	{
		return true;
	}

	public void onGUICreateWorldPress()
	{
		Vars.playingIslands = true;
		WorldGenOneTree.spawned = false;
	}

	@SideOnly(Side.CLIENT)
	public void onCustomizeButton(Minecraft mc, GuiCreateWorld guiCreateWorld)
	{
		mc.displayGuiScreen(new GuiCustomizeIslands(guiCreateWorld, Vars.numbOfTrees, Vars.numbOfPumps, Vars.numbOfCactus, Vars.numbOfMelons));
		Vars.numbOfCactus =
		Vars.spawnedCactus =
		Vars.numbOfPumps =
		Vars.spawnedPumps =
		Vars.numbOfMelons =
		Vars.spawnedMelons =
		Vars.numbOfTrees =
		Vars.spawnedTrees = 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String func_151359_c()
	{
		return this.getTranslateName();
	}

	@Override
	public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer)
	{
		GenLayer parent = new GenLayerIsland(200L);
		new GenLayerAddIsland(200L, parent);
		// new GenLayerAddMushroomIsland(200L, parent);
		// new GenLayerShore(200L, parent);
		new GenLayerDeepOcean(200L, parent);
		return parent;
	}
}
