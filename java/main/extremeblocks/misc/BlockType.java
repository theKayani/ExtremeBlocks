package main.extremeblocks.misc;

public enum BlockType
{
	NORMAL(false, false), CABINET(true, true), BARREL(true, false);
	private boolean shouldDrop;
	private boolean canPlaceOnSide;

	private BlockType(boolean shouldDrop, boolean canPlaceOnSide)
	{
		this.shouldDrop = shouldDrop;
		this.canPlaceOnSide = canPlaceOnSide;
	}

	public boolean shouldDrop()
	{
		return this.shouldDrop;
	}

	public boolean canPlaceOnSide()
	{
		return canPlaceOnSide;
	}
}
