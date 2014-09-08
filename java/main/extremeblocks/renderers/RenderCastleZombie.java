package main.extremeblocks.renderers;

import main.extremeblocks.Init;
import main.extremeblocks.entities.mobs.EntityCastleZombie;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;

public class RenderCastleZombie extends RenderZombie
{
	private static final ResourceLocation zombieTextures = new ResourceLocation(Init.MODID + ":textures/entities/castle_zombie.png");
	private static final ResourceLocation zombieVillagerTextures = new ResourceLocation(Init.MODID + ":textures/entities/castle_zombie_villager.png");

	protected ResourceLocation getEntityTexture(EntityZombie entity)
	{
		return entity.isVillager() ? zombieVillagerTextures : zombieTextures;
	}
}
