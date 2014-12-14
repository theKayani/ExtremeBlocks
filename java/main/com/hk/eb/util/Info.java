package main.com.hk.eb.util;

public interface Info
{
	public String getInfo();

	public Elements getElements();

	public static class Elements
	{
		public final boolean isUnique, showRecipe;

		public Elements(boolean isUnique, boolean showRecipe)
		{
			this.isUnique = isUnique;
			this.showRecipe = showRecipe;
		}
	}
}
