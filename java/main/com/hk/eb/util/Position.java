package main.com.hk.eb.util;

public final class Position implements Cloneable
{
	public float x, y, z;

	public Position(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Position(Position copy)
	{
		x = copy.x;
		y = copy.y;
		z = copy.z;
	}

	public Position()
	{
		x = y = z = 0;
	}

	public final float getX()
	{
		return x;
	}

	public final Position setX(float x)
	{
		this.x = x;
		return this;
	}

	public final float getY()
	{
		return y;
	}

	public final Position setY(float y)
	{
		this.y = y;
		return this;
	}

	public final float getZ()
	{
		return z;
	}

	public final Position setZ(float z)
	{
		this.z = z;
		return this;
	}

	public final Position set(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	public final Position add(Position position)
	{
		return new Position(x + position.x, y + position.y, z + position.z);
	}

	public final Position addLocal(Position position)
	{
		x += position.x;
		y += position.y;
		z += position.z;
		return this;
	}

	public final Position subtract(Position position)
	{
		return new Position(x - position.x, y - position.y, z - position.z);
	}

	public final Position subtractLocal(Position position)
	{
		x -= position.x;
		y -= position.y;
		z -= position.z;
		return this;
	}

	public final Position multiply(Position position)
	{
		return new Position(x * position.x, y * position.y, z * position.z);
	}

	public final Position multiplyLocal(Position position)
	{
		x *= position.x;
		y *= position.y;
		z *= position.z;
		return this;
	}

	public final Position divide(Position position)
	{
		return new Position(x / position.x, y / position.y, z / position.z);
	}

	public final Position divideLocal(Position position)
	{
		x /= position.x;
		y /= position.y;
		z /= position.z;
		return this;
	}

	public final float lengthSquared()
	{
		return x * x + y * y + z * z;
	}

	public final float length()
	{
		return (float) Math.sqrt(lengthSquared());
	}

	public final float distanceSquared(Position position)
	{
		float dx = x - position.x;
		float dy = y - position.y;
		float dz = z - position.z;
		return dx * dx + dy * dy + dz * dz;
	}

	public final float distance(Position position)
	{
		return (float) Math.sqrt(distanceSquared(position));
	}

	public final Position negateLocal()
	{
		x = -x;
		y = -y;
		z = -z;
		return this;
	}

	public final Position negate()
	{
		return new Position(-x, -y, -z);
	}

	public final Position normalize()
	{
		float length = x * x + y * y + z * z;
		if (length != 1.0F && length != 0.0F)
		{
			length = (float) (1.0F / Math.sqrt(length));
			return new Position(x * length, y * length, z * length);
		}
		return copy();
	}

	public final Position reset()
	{
		x = y = z = 0;
		return this;
	}

	public final float[] toArray()
	{
		return toArray(new float[3]);
	}

	public final float[] toArray(float[] floats)
	{
		if (floats == null)
		{
			floats = new float[3];
		}
		floats[0] = x;
		floats[1] = y;
		floats[2] = z;
		return floats;
	}

	public float get(int index)
	{
		switch (index)
		{
			case 0:
				return x;
			case 1:
				return y;
			case 2:
				return z;
		}
		return 0;
	}

	public Position set(int index, float value)
	{
		switch (index)
		{
			case 0:
				x = value;
				return this;
			case 1:
				y = value;
				return this;
			case 2:
				z = value;
				return this;
		}
		return this;
	}

	public final Position copy()
	{
		return new Position(this);
	}

	@Override
	public final boolean equals(Object o)
	{
		if (!(o instanceof Position)) return false;

		if (this == o) return true;

		Position pos = (Position) o;
		if (Float.compare(x, pos.x) != 0) return false;
		if (Float.compare(y, pos.y) != 0) return false;
		if (Float.compare(z, pos.z) != 0) return false;
		return true;
	}

	@Override
	public final int hashCode()
	{
		int hash = 37;
		hash += 37 * hash + Float.floatToIntBits(x);
		hash += 37 * hash + Float.floatToIntBits(y);
		hash += 37 * hash + Float.floatToIntBits(z);
		return hash;
	}

	@Override
	public final String toString()
	{
		return "(" + x + ", " + y + ", " + z + ")";
	}
}
