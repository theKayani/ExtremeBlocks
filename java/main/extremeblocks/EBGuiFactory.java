package main.extremeblocks;

import java.util.List;
import java.util.Set;
import main.com.hk.eb.util.JavaHelp;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.config.DummyConfigElement.DummyCategoryElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.GuiConfigEntries;
import cpw.mods.fml.client.config.GuiConfigEntries.CategoryEntry;
import cpw.mods.fml.client.config.IConfigElement;

public class EBGuiFactory implements IModGuiFactory
{
	@Override
	public void initialize(Minecraft minecraftInstance)
	{
	}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass()
	{
		return ConfigScreen.class;
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
	{
		return null;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element)
	{
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static List<IConfigElement> getConfigElements()
	{
		List<IConfigElement> list = JavaHelp.newArrayList();
		list.add(new DummyCategoryElement<Object>("General Options", "General Options", GeneralOptions.class));
		list.add(new DummyCategoryElement<Object>("Ores Options", "Ores Options", OresOptions.class));
		list.add(new DummyCategoryElement<Object>("Generation Options", "Generation Options", GenerationOptions.class));
		list.add(new DummyCategoryElement<Object>("Mobs Options", "Mobs Options", MobsOptions.class));
		return list;
	}

	public static class ConfigScreen extends GuiConfig
	{
		public ConfigScreen(GuiScreen parentScreen)
		{
			super(parentScreen, getConfigElements(), Init.MODID, false, true, "Extreme Blocks Config");
		}
	}

	public static class GeneralOptions extends CustomEntry
	{
		public GeneralOptions(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement<?> prop)
		{
			super(owningScreen, owningEntryList, prop);
		}

		@Override
		public String getCategory()
		{
			return Config.generalConfig;
		}
	}

	public static class OresOptions extends CustomEntry
	{
		public OresOptions(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement<?> prop)
		{
			super(owningScreen, owningEntryList, prop);
		}

		@Override
		public String getCategory()
		{
			return Config.oresConfig;
		}
	}

	public static class GenerationOptions extends CustomEntry
	{
		public GenerationOptions(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement<?> prop)
		{
			super(owningScreen, owningEntryList, prop);
		}

		@Override
		public String getCategory()
		{
			return Config.worldConfig;
		}
	}

	public static class MobsOptions extends CustomEntry
	{
		public MobsOptions(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement<?> prop)
		{
			super(owningScreen, owningEntryList, prop);
		}

		@Override
		public String getCategory()
		{
			return Config.mobConfig;
		}
	}

	public abstract static class CustomEntry extends CategoryEntry
	{
		public CustomEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement<?> prop)
		{
			super(owningScreen, owningEntryList, prop);
		}

		@Override
		protected GuiScreen buildChildScreen()
		{
			String category = getCategory();
			return new GuiConfig(owningScreen, new ConfigElement<Object>(ExtremeBlocks.configFile.getCategory(category)).getChildElements(), owningScreen.modID, category, configElement.requiresWorldRestart() || owningScreen.allRequireWorldRestart, configElement.requiresMcRestart() || owningScreen.allRequireMcRestart, GuiConfig.getAbridgedConfigPath(ExtremeBlocks.configFile.toString()));
		}

		public abstract String getCategory();
	}
}