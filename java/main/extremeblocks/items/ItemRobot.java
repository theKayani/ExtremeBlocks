package main.extremeblocks.items;

import main.com.hk.eb.util.ItemCustom;
import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.Init;
import main.extremeblocks.entities.mobs.EntityRobot;
import main.extremeblocks.entities.mobs.EntityRobot.RobotType;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemRobot extends ItemCustom
{
	private final RobotType type;

	public ItemRobot(RobotType type)
	{
		super(type.name + " Robot", Init.tab_mainItems);
		this.setTextureName(Init.MODID + ":" + "robot_" + type.name().toLowerCase());
		this.type = type;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int xPos, int yPos, int zPos, int sideHit, float sideX, float sideY, float sizeZ)
	{
		if (MPUtil.isServerSide())
		{
			double x = xPos;
			double y = yPos;
			double z = zPos;
			x += Facing.offsetsXForSide[sideHit];
			y += Facing.offsetsYForSide[sideHit];
			z += Facing.offsetsZForSide[sideHit];
			if (sideHit == 1 && world.getBlock(xPos, yPos, zPos).getRenderType() == 11)
			{
				y += 0.5D;
			}
			EntityRobot robot = new EntityRobot(world);
			robot.type = type;
			robot.setLocationAndAngles(x + 0.5D, y, z + 0.5D, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
			world.spawnEntityInWorld(robot);
			if (stack.hasDisplayName())
			{
				((EntityLiving) robot).setCustomNameTag(stack.getDisplayName());
			}
			if (!player.capabilities.isCreativeMode)
			{
				--stack.stackSize;
			}
		}
		return true;
	}
}
