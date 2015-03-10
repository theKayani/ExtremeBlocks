package main.extremeblocks.tools;

import java.util.List;
import main.extremeblocks.Init;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHoeOverlay extends ItemHoe
{
	@SideOnly(Side.CLIENT)
	private IIcon stick;
	@SideOnly(Side.CLIENT)
	private IIcon head;
	public final int headColor;
	public final String name;

	public ItemHoeOverlay(ToolMaterial mat, int color)
	{
		super(mat);
		headColor = color;
		name = mat.name().substring(0, 1).toUpperCase() + mat.name().substring(1).toLowerCase() + " Hoe";
		setUnlocalizedName(name);
		setCreativeTab(Init.tab_tools);
	}

	@Override
	public String getUnlocalizedName()
	{
		return name;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		return name;
	}

	@Override
	public String getItemStackDisplayName(ItemStack par1ItemStack)
	{
		return name;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int flag)
	{
		return flag == 0 ? headColor : 16777215;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int flag, int flag1)
	{
		return flag1 > 0 ? stick : head;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
	{
		list.add(EnumChatFormatting.GRAY + "" + (stack.getMaxDamage() - stack.getItemDamage()) + "/" + stack.getMaxDamage() + " Durability" + EnumChatFormatting.RESET);

		super.addInformation(stack, player, list, flag);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		stick = ir.registerIcon(Init.MODID + ":stick_hoe");
		head = ir.registerIcon(Init.MODID + ":head_hoe");
	}
}
