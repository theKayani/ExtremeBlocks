package main.extremeblocks.items;

import main.com.hk.eb.util.ItemCustom;
import main.com.hk.eb.util.MPUtil;
import main.com.hk.eb.util.Rand;
import main.extremeblocks.Init;
import main.extremeblocks.entities.mobs.EntityBloodwing;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemSummonBloodwing extends ItemCustom
{
	public ItemSummonBloodwing()
	{
		super("Summon Bloodwing", Init.tab_mainItems);
		setTextureName(Init.MODID + ":summon_bloodwing");
		setShowRecipe();
		setInfo("This summons a Bloodwing to fight along your side. It's already tamed as well. It might be expensive because you have to kill the Demon Spirit, but it does tons of damage!");
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float a, float b, float c)
	{
		if (MPUtil.isServerSide())
		{
			double d0 = side == 1 && world.getBlock(x, y, z).getRenderType() == 11 ? 0.5D : 0.0D;
			x += Facing.offsetsXForSide[side];
			y += Facing.offsetsYForSide[side];
			z += Facing.offsetsZForSide[side];

			EntityBloodwing bloodwing = new EntityBloodwing(world);
			bloodwing.setLocationAndAngles(x + 0.5D, y + d0, z + 0.5D, MathHelper.wrapAngleTo180_float(Rand.nextFloat() * 360.0F), 0.0F);
			bloodwing.rotationYawHead = bloodwing.renderYawOffset = bloodwing.rotationYaw;
			bloodwing.setOwner(player);
			world.spawnEntityInWorld(bloodwing);
			bloodwing.setCustomNameTag(stack.hasDisplayName() ? stack.getDisplayName() : player.getCommandSenderName() + "'s Bloodwing");
			stack.stackSize -= !player.capabilities.isCreativeMode ? 1 : 0;
		}
		return true;
	}
}
