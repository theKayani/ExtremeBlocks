package main.com.hk.testing.util;

import net.minecraft.util.ResourceLocation;

public interface ICustomEntity
{
	/**
	 * Used by {@link RenderEntity} to register a {@link ResourceLocation} that will be used to render the given Entity
	 * 
	 * @return Overridden to get the image/texture for the Entity that this is Registered to
	 */
	public String getResourceLocation();
}
