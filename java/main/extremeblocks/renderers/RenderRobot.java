package main.extremeblocks.renderers;

import main.extremeblocks.Init;
import main.extremeblocks.entities.mobs.EntityRobot;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRobot extends RenderBiped
{
	private static final ResourceLocation offTexture = new ResourceLocation(Init.MODID + ":textures/entities/robot_off.png");
	private static final ResourceLocation onTexture = new ResourceLocation(Init.MODID + ":textures/entities/robot_on.png");

	public RenderRobot()
	{
		super(new ModelBiped(0.0F), 0.5F);
	}

	@Override
	protected void func_82408_c(EntityLiving p_82408_1_, int p_82408_2_, float p_82408_3_)
	{
		ItemStack itemstack = p_82408_1_.func_130225_q(p_82408_2_);

		if (itemstack != null)
		{
			Item item = itemstack.getItem();

			if (item instanceof ItemArmor)
			{
				bindTexture(getArmorResource(p_82408_1_, itemstack, p_82408_2_, "overlay"));
				GL11.glColor3f(1.0F, 1.0F, 1.0F);
			}
		}
	}

	@Override
	protected int shouldRenderPass(EntityLiving p_77032_1_, int p_77032_2_, float p_77032_3_)
	{
		ItemStack itemstack = p_77032_1_.func_130225_q(p_77032_2_);

		if (itemstack != null)
		{
			Item item = itemstack.getItem();

			if (item instanceof ItemArmor)
			{
				ItemArmor itemarmor = (ItemArmor) item;
				bindTexture(getArmorResource(p_77032_1_, itemstack, p_77032_2_, null));
				ModelBiped modelbiped = p_77032_2_ == 2 ? field_82425_h : field_82423_g;
				modelbiped.bipedHead.showModel = p_77032_2_ == 0;
				modelbiped.bipedHeadwear.showModel = p_77032_2_ == 0;
				modelbiped.bipedBody.showModel = p_77032_2_ == 1 || p_77032_2_ == 2;
				modelbiped.bipedRightArm.showModel = p_77032_2_ == 1;
				modelbiped.bipedLeftArm.showModel = p_77032_2_ == 1;
				modelbiped.bipedRightLeg.showModel = p_77032_2_ == 2 || p_77032_2_ == 3;
				modelbiped.bipedLeftLeg.showModel = p_77032_2_ == 2 || p_77032_2_ == 3;
				modelbiped = net.minecraftforge.client.ForgeHooksClient.getArmorModel(p_77032_1_, itemstack, p_77032_2_, modelbiped);
				setRenderPassModel(modelbiped);
				modelbiped.onGround = mainModel.onGround;
				modelbiped.isRiding = mainModel.isRiding;
				modelbiped.isChild = mainModel.isChild;

				// Move out of if to allow for more then just CLOTH to have
				// color
				int j = itemarmor.getColor(itemstack);
				if (j != -1)
				{
					float f1 = (j >> 16 & 255) / 255.0F;
					float f2 = (j >> 8 & 255) / 255.0F;
					float f3 = (j & 255) / 255.0F;
					GL11.glColor3f(f1, f2, f3);

					if (itemstack.isItemEnchanted()) return 31;

					return 16;
				}

				GL11.glColor3f(1.0F, 1.0F, 1.0F);

				if (itemstack.isItemEnchanted()) return 15;

				return 1;
			}
		}

		return -1;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return ((EntityRobot) entity).getType() == null || ((EntityRobot) entity).getType().ordinal() % 2 == 0 ? onTexture : offTexture;
	}
}