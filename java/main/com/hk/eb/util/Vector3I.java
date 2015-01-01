package main.com.hk.eb.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Vector3I implements Cloneable, java.io.Serializable
{
	static final long serialVersionUID = 1;
	private static final Logger logger = LogManager.getLogger(Vector3I.class.getSimpleName());
	public final static Vector3I ZERO = new Vector3I(0, 0, 0);
	public final static Vector3I UNIT_X = new Vector3I(1, 0, 0);
	public final static Vector3I UNIT_Y = new Vector3I(0, 1, 0);
	public final static Vector3I UNIT_Z = new Vector3I(0, 0, 1);
	public final static Vector3I UNIT_XYZ = new Vector3I(1, 1, 1);
	/**
	 * the x value of the vector.
	 */
	public int x;
	/**
	 * the y value of the vector.
	 */
	public int y;
	/**
	 * the z value of the vector.
	 */
	public int z;

	/**
	 * Constructor instantiates a new <code>Vector3I</code> with default values
	 * of (0,0,0).
	 * 
	 */
	public Vector3I()
	{
		x = y = z = 0;
	}

	/**
	 * Constructor instantiates a new <code>Vector3I</code> with provides
	 * values.
	 * 
	 * @param x
	 *            the x value of the vector.
	 * @param y
	 *            the y value of the vector.
	 * @param z
	 *            the z value of the vector.
	 */
	public Vector3I(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Constructor instantiates a new <code>Vector3I</code> with the entities 
	 * position.
	 * 
	 * @param entity
	 *            the entity to copy the vector's values from.
	 */
	public Vector3I(Entity entity)
	{
		x = MathHelper.floor_double(entity.posX);
		y = MathHelper.floor_double(entity.posY);
		z = MathHelper.floor_double(entity.posZ);
	}

	/**
	 * Constructor instantiates a new <code>Vector3I</code> with the 
	 * single value.
	 * 
	 * @param val
	 *            the x, y, and z value of the vector.
	 */
	public Vector3I(int val)
	{
		x = val;
		y = val;
		z = val;
	}

	/**
	 * Constructor instantiates a new <code>Vector3I</code> that is a copy of
	 * the provided vector
	 * 
	 * @param copy
	 *            The Vector3I to copy
	 */
	public Vector3I(Vector3I copy)
	{
		this.set(copy);
	}

	/**
	 * <code>set</code> sets the x,y,z values of the vector based on passed
	 * parameters.
	 * 
	 * @param x
	 *            the x value of the vector.
	 * @param y
	 *            the y value of the vector.
	 * @param z
	 *            the z value of the vector.
	 * @return this vector
	 */
	public Vector3I set(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	/**
	 * <code>set</code> sets the x,y,z values of the vector by copying the
	 * supplied vector.
	 * 
	 * @param vect
	 *            the vector to copy.
	 * @return this vector
	 */
	public Vector3I set(Vector3I vect)
	{
		x = vect.x;
		y = vect.y;
		z = vect.z;
		return this;
	}

	/**
	 * 
	 * <code>add</code> adds a provided vector to this vector creating a
	 * resultant vector which is returned. If the provided vector is null, null
	 * is returned.
	 * 
	 * @param vec
	 *            the vector to add to this.
	 * @return the resultant vector.
	 */
	public Vector3I add(Vector3I vec)
	{
		return new Vector3I(x + vec.x, y + vec.y, z + vec.z);
	}

	/**
	 * 
	 * <code>add</code> adds the values of a provided vector storing the values
	 * in the supplied vector.
	 * 
	 * @param vec
	 *            the vector to add to this
	 * @param result
	 *            the vector to store the result in
	 * @return result returns the supplied result vector.
	 */
	public Vector3I add(Vector3I vec, Vector3I result)
	{
		result.x = x + vec.x;
		result.y = y + vec.y;
		result.z = z + vec.z;
		return result;
	}

	/**
	 * <code>addLocal</code> adds a provided vector to this vector internally,
	 * and returns a handle to this vector for easy chaining of calls. If the
	 * provided vector is null, null is returned.
	 * 
	 * @param vec
	 *            the vector to add to this vector.
	 * @return this
	 */
	public Vector3I addLocal(Vector3I vec)
	{
		x += vec.x;
		y += vec.y;
		z += vec.z;
		return this;
	}

	/**
	 * 
	 * <code>add</code> adds the provided values to this vector, creating a new
	 * vector that is then returned.
	 * 
	 * @param addX
	 *            the x value to add.
	 * @param addY
	 *            the y value to add.
	 * @param addZ
	 *            the z value to add.
	 * @return the result vector.
	 */
	public Vector3I add(int addX, int addY, int addZ)
	{
		return new Vector3I(x + addX, y + addY, z + addZ);
	}

	/**
	 * <code>addLocal</code> adds the provided values to this vector internally,
	 * and returns a handle to this vector for easy chaining of calls.
	 * 
	 * @param addX
	 *            value to add to x
	 * @param addY
	 *            value to add to y
	 * @param addZ
	 *            value to add to z
	 * @return this
	 */
	public Vector3I addLocal(int addX, int addY, int addZ)
	{
		x += addX;
		y += addY;
		z += addZ;
		return this;
	}

	/**
	 * 
	 * <code>scaleAdd</code> multiplies this vector by a scalar then adds the
	 * given Vector3I.
	 * 
	 * @param scalar
	 *            the value to multiply this vector by.
	 * @param add
	 *            the value to add
	 */
	public Vector3I scaleAdd(int scalar, Vector3I add)
	{
		x = x * scalar + add.x;
		y = y * scalar + add.y;
		z = z * scalar + add.z;
		return this;
	}

	/**
	 * 
	 * <code>scaleAdd</code> multiplies the given vector by a scalar then adds
	 * the given vector.
	 * 
	 * @param scalar
	 *            the value to multiply this vector by.
	 * @param mult
	 *            the value to multiply the scalar by
	 * @param add
	 *            the value to add
	 */
	public Vector3I scaleAdd(int scalar, Vector3I mult, Vector3I add)
	{
		x = mult.x * scalar + add.x;
		y = mult.y * scalar + add.y;
		z = mult.z * scalar + add.z;
		return this;
	}

	/**
	 * 
	 * <code>dot</code> calculates the dot product of this vector with a
	 * provided vector. If the provided vector is null, 0 is returned.
	 * 
	 * @param vec
	 *            the vector to dot with this vector.
	 * @return the resultant dot product of this vector and a given vector.
	 */
	public int dot(Vector3I vec)
	{
		return x * vec.x + y * vec.y + z * vec.z;
	}

	/**
	 * <code>rotate</code> rotates a copy of this
	 * vector the specified degrees from it's current point;
	 * 
	 * @param degrees the degrees to rotate it.
	 * @return a new vector with the specified rotation
	 */
	public Vector3I rotate(float degrees)
	{
		int x1 = (int) (x * Math.cos(Math.toRadians(degrees)) - z * Math.sin(Math.toRadians(degrees)));
		int z1 = (int) (x * Math.sin(Math.toRadians(degrees)) + z * Math.cos(Math.toRadians(degrees)));
		return new Vector3I(x1, y, z1);
	}

	/**
	 * <code>rotate</code> rotates this vector the specified degrees 
	 * from it's current point;
	 * 
	 * @param degrees the degrees to rotate it.
	 * @return a new vector with the specified rotation
	 */
	public Vector3I rotateLocal(float degrees)
	{
		int x1 = (int) (x * Math.cos(degrees) - y * Math.sin(degrees));
		int y1 = (int) (x * Math.sin(degrees) + y * Math.cos(degrees));
		x = x1;
		y = y1;
		return this;
	}

	/**
	 * <code>cross</code> calculates the cross product of this vector with a
	 * parameter vector v.
	 * 
	 * @param v
	 *            the vector to take the cross product of with this.
	 * @return the cross product vector.
	 */
	public Vector3I cross(Vector3I v)
	{
		return cross(v, null);
	}

	/**
	 * <code>cross</code> calculates the cross product of this vector with a
	 * parameter vector v. The result is stored in <code>result</code>
	 * 
	 * @param v
	 *            the vector to take the cross product of with this.
	 * @param result
	 *            the vector to store the cross product result.
	 * @return result, after recieving the cross product vector.
	 */
	public Vector3I cross(Vector3I v, Vector3I result)
	{
		return cross(v.x, v.y, v.z, result);
	}

	/**
	 * <code>cross</code> calculates the cross product of this vector with a
	 * parameter vector v. The result is stored in <code>result</code>
	 * 
	 * @param otherX
	 *            x component of the vector to take the cross product of with
	 *            this.
	 * @param otherY
	 *            y component of the vector to take the cross product of with
	 *            this.
	 * @param otherZ
	 *            z component of the vector to take the cross product of with
	 *            this.
	 * @param result
	 *            the vector to store the cross product result.
	 * @return result, after recieving the cross product vector.
	 */
	public Vector3I cross(int otherX, int otherY, int otherZ, Vector3I result)
	{
		if (result == null)
		{
			result = new Vector3I();
		}
		int resX = y * otherZ - z * otherY;
		int resY = z * otherX - x * otherZ;
		int resZ = x * otherY - y * otherX;
		result.set(resX, resY, resZ);
		return result;
	}

	/**
	 * <code>crossLocal</code> calculates the cross product of this vector with
	 * a parameter vector v.
	 * 
	 * @param v
	 *            the vector to take the cross product of with this.
	 * @return this.
	 */
	public Vector3I crossLocal(Vector3I v)
	{
		return crossLocal(v.x, v.y, v.z);
	}

	/**
	 * <code>crossLocal</code> calculates the cross product of this vector with
	 * a parameter vector v.
	 * 
	 * @param otherX
	 *            x component of the vector to take the cross product of with
	 *            this.
	 * @param otherY
	 *            y component of the vector to take the cross product of with
	 *            this.
	 * @param otherZ
	 *            z component of the vector to take the cross product of with
	 *            this.
	 * @return this.
	 */
	public Vector3I crossLocal(int otherX, int otherY, int otherZ)
	{
		int tempx = y * otherZ - z * otherY;
		int tempy = z * otherX - x * otherZ;
		z = x * otherY - y * otherX;
		x = tempx;
		y = tempy;
		return this;
	}

	/**
	 * Projects this vector onto another vector
	 * 
	 * @param other
	 *            The vector to project this vector onto
	 * @return A new vector with the projection result
	 */
	public Vector3I project(Vector3I other)
	{
		int n = dot(other); // A . B
		int d = other.lengthSquared(); // |B|^2
		return new Vector3I(other).normalizeLocal().multLocal(n / d);
	}

	/**
	 * Projects this vector onto another vector, stores the result in this
	 * vector
	 * 
	 * @param other
	 *            The vector to project this vector onto
	 * @return This Vector3I, set to the projection result
	 */
	public Vector3I projectLocal(Vector3I other)
	{
		int n = dot(other); // A . B
		int d = other.lengthSquared(); // |B|^2
		return set(other).normalizeLocal().multLocal(n / d);
	}

	/**
	 * Returns true if this vector is a unit vector (length() ~= 1), returns
	 * false otherwise.
	 * 
	 * @return true if this vector is a unit vector (length() ~= 1), or false
	 *         otherwise.
	 */
	public boolean isUnitVector()
	{
		int len = length();
		return 0.99f < len && len < 1.01f;
	}

	/**
	 * <code>length</code> calculates the magnitude of this vector.
	 * 
	 * @return the length or magnitude of the vector.
	 */
	public int length()
	{
		return (int) Math.sqrt(lengthSquared());
	}

	/**
	 * <code>lengthSquared</code> calculates the squared value of the magnitude
	 * of the vector.
	 * 
	 * @return the magnitude squared of the vector.
	 */
	public int lengthSquared()
	{
		return x * x + y * y + z * z;
	}

	/**
	 * <code>distanceSquared</code> calculates the distance squared between this
	 * vector and vector v.
	 * 
	 * @param v
	 *            the second vector to determine the distance squared.
	 * @return the distance squared between the two vectors.
	 */
	public int distanceSquared(Vector3I v)
	{
		double dx = x - v.x;
		double dy = y - v.y;
		double dz = z - v.z;
		return (int) (dx * dx + dy * dy + dz * dz);
	}

	/**
	 * <code>distance</code> calculates the distance between this vector and
	 * vector v.
	 * 
	 * @param v
	 *            the second vector to determine the distance.
	 * @return the distance between the two vectors.
	 */
	public int distance(Vector3I v)
	{
		return (int) Math.sqrt(distanceSquared(v));
	}

	/**
	 * 
	 * <code>mult</code> multiplies this vector by a scalar. The resultant
	 * vector is returned.
	 * 
	 * @param scalar
	 *            the value to multiply this vector by.
	 * @return the new vector.
	 */
	public Vector3I mult(int scalar)
	{
		return new Vector3I(x * scalar, y * scalar, z * scalar);
	}

	/**
	 * 
	 * <code>mult</code> multiplies this vector by a scalar. The resultant
	 * vector is supplied as the second parameter and returned.
	 * 
	 * @param scalar
	 *            the scalar to multiply this vector by.
	 * @param product
	 *            the product to store the result in.
	 * @return product
	 */
	public Vector3I mult(int scalar, Vector3I product)
	{
		if (null == product)
		{
			product = new Vector3I();
		}
		product.x = x * scalar;
		product.y = y * scalar;
		product.z = z * scalar;
		return product;
	}

	/**
	 * <code>multLocal</code> multiplies this vector by a scalar internally, and
	 * returns a handle to this vector for easy chaining of calls.
	 * 
	 * @param scalar
	 *            the value to multiply this vector by.
	 * @return this
	 */
	public Vector3I multLocal(int scalar)
	{
		x *= scalar;
		y *= scalar;
		z *= scalar;
		return this;
	}

	/**
	 * <code>multLocal</code> multiplies a provided vector to this vector
	 * internally, and returns a handle to this vector for easy chaining of
	 * calls. If the provided vector is null, null is returned.
	 * 
	 * @param vec
	 *            the vector to mult to this vector.
	 * @return this
	 */
	public Vector3I multLocal(Vector3I vec)
	{
		if (null == vec)
		{
			logger.warn("Provided vector is null, null returned.");
			return null;
		}
		x *= vec.x;
		y *= vec.y;
		z *= vec.z;
		return this;
	}

	/**
	 * <code>multLocal</code> multiplies this vector by 3 scalars internally,
	 * and returns a handle to this vector for easy chaining of calls.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return this
	 */
	public Vector3I multLocal(int x, int y, int z)
	{
		this.x *= x;
		this.y *= y;
		this.z *= z;
		return this;
	}

	/**
	 * <code>multLocal</code> multiplies a provided vector to this vector
	 * internally, and returns a handle to this vector for easy chaining of
	 * calls. If the provided vector is null, null is returned.
	 * 
	 * @param vec
	 *            the vector to mult to this vector.
	 * @return this
	 */
	public Vector3I mult(Vector3I vec)
	{
		if (null == vec)
		{
			logger.warn("Provided vector is null, null returned.");
			return null;
		}
		return mult(vec, null);
	}

	/**
	 * <code>multLocal</code> multiplies a provided vector to this vector
	 * internally, and returns a handle to this vector for easy chaining of
	 * calls. If the provided vector is null, null is returned.
	 * 
	 * @param vec
	 *            the vector to mult to this vector.
	 * @param store
	 *            result vector (null to create a new vector)
	 * @return this
	 */
	public Vector3I mult(Vector3I vec, Vector3I store)
	{
		if (null == vec)
		{
			logger.warn("Provided vector is null, null returned.");
			return null;
		}
		if (store == null)
		{
			store = new Vector3I();
		}
		return store.set(x * vec.x, y * vec.y, z * vec.z);
	}

	/**
	 * <code>divide</code> divides the values of this vector by a scalar and
	 * returns the result. The values of this vector remain untouched.
	 * 
	 * @param scalar
	 *            the value to divide this vectors attributes by.
	 * @return the result <code>Vector</code>.
	 */
	public Vector3I divide(int scalar)
	{
		scalar = 1 / scalar;
		return new Vector3I(x * scalar, y * scalar, z * scalar);
	}

	/**
	 * <code>divideLocal</code> divides this vector by a scalar internally, and
	 * returns a handle to this vector for easy chaining of calls. Dividing by
	 * zero will result in an exception.
	 * 
	 * @param scalar
	 *            the value to divides this vector by.
	 * @return this
	 */
	public Vector3I divideLocal(int scalar)
	{
		scalar = 1 / scalar;
		x *= scalar;
		y *= scalar;
		z *= scalar;
		return this;
	}

	/**
	 * <code>divide</code> divides the values of this vector by a scalar and
	 * returns the result. The values of this vector remain untouched.
	 * 
	 * @param scalar
	 *            the value to divide this vectors attributes by.
	 * @return the result <code>Vector</code>.
	 */
	public Vector3I divide(Vector3I scalar)
	{
		return new Vector3I(x / scalar.x, y / scalar.y, z / scalar.z);
	}

	/**
	 * <code>divideLocal</code> divides this vector by a scalar internally, and
	 * returns a handle to this vector for easy chaining of calls. Dividing by
	 * zero will result in an exception.
	 * 
	 * @param scalar
	 *            the value to divides this vector by.
	 * @return this
	 */
	public Vector3I divideLocal(Vector3I scalar)
	{
		x /= scalar.x;
		y /= scalar.y;
		z /= scalar.z;
		return this;
	}

	/**
	 * 
	 * <code>negate</code> returns the negative of this vector. All values are
	 * negated and set to a new vector.
	 * 
	 * @return the negated vector.
	 */
	public Vector3I negate()
	{
		return new Vector3I(-x, -y, -z);
	}

	/**
	 * 
	 * <code>negateLocal</code> negates the internal values of this vector.
	 * 
	 * @return this.
	 */
	public Vector3I negateLocal()
	{
		x = -x;
		y = -y;
		z = -z;
		return this;
	}

	/**
	 * 
	 * <code>subtract</code> subtracts the values of a given vector from those
	 * of this vector creating a new vector object. If the provided vector is
	 * null, null is returned.
	 * 
	 * @param vec
	 *            the vector to subtract from this vector.
	 * @return the result vector.
	 */
	public Vector3I subtract(Vector3I vec)
	{
		return new Vector3I(x - vec.x, y - vec.y, z - vec.z);
	}

	/**
	 * <code>subtractLocal</code> subtracts a provided vector to this vector
	 * internally, and returns a handle to this vector for easy chaining of
	 * calls. If the provided vector is null, null is returned.
	 * 
	 * @param vec
	 *            the vector to subtract
	 * @return this
	 */
	public Vector3I subtractLocal(Vector3I vec)
	{
		if (null == vec)
		{
			logger.warn("Provided vector is null, null returned.");
			return null;
		}
		x -= vec.x;
		y -= vec.y;
		z -= vec.z;
		return this;
	}

	/**
	 * 
	 * <code>subtract</code>
	 * 
	 * @param vec
	 *            the vector to subtract from this
	 * @param result
	 *            the vector to store the result in
	 * @return result
	 */
	public Vector3I subtract(Vector3I vec, Vector3I result)
	{
		if (result == null)
		{
			result = new Vector3I();
		}
		result.x = x - vec.x;
		result.y = y - vec.y;
		result.z = z - vec.z;
		return result;
	}

	/**
	 * 
	 * <code>subtract</code> subtracts the provided values from this vector,
	 * creating a new vector that is then returned.
	 * 
	 * @param subtractX
	 *            the x value to subtract.
	 * @param subtractY
	 *            the y value to subtract.
	 * @param subtractZ
	 *            the z value to subtract.
	 * @return the result vector.
	 */
	public Vector3I subtract(int subtractX, int subtractY, int subtractZ)
	{
		return new Vector3I(x - subtractX, y - subtractY, z - subtractZ);
	}

	/**
	 * <code>subtractLocal</code> subtracts the provided values from this vector
	 * internally, and returns a handle to this vector for easy chaining of
	 * calls.
	 * 
	 * @param subtractX
	 *            the x value to subtract.
	 * @param subtractY
	 *            the y value to subtract.
	 * @param subtractZ
	 *            the z value to subtract.
	 * @return this
	 */
	public Vector3I subtractLocal(int subtractX, int subtractY, int subtractZ)
	{
		x -= subtractX;
		y -= subtractY;
		z -= subtractZ;
		return this;
	}

	/**
	 * <code>normalize</code> returns the unit vector of this vector.
	 * 
	 * @return unit vector of this vector.
	 */
	public Vector3I normalize()
	{
		// int length = length();
		// if (length != 0) {
		// return divide(length);
		// }
		//
		// return divide(1);
		int length = x * x + y * y + z * z;
		if (length != 1f && length != 0f)
		{
			length = (int) (1.0f / Math.sqrt(length));
			return new Vector3I(x * length, y * length, z * length);
		}
		return clone();
	}

	/**
	 * <code>normalizeLocal</code> makes this vector into a unit vector of
	 * itself.
	 * 
	 * @return this.
	 */
	public Vector3I normalizeLocal()
	{
		int length = x * x + y * y + z * z;
		if (length != 1f && length != 0f)
		{
			length = (int) (1.0f / Math.sqrt(length));
			x *= length;
			y *= length;
			z *= length;
		}
		return this;
	}

	/**
	 * <code>maxLocal</code> computes the maximum value for each component in
	 * this and <code>other</code> vector. The result is stored in this vector.
	 * 
	 * @param other
	 */
	public Vector3I maxLocal(Vector3I other)
	{
		x = other.x > x ? other.x : x;
		y = other.y > y ? other.y : y;
		z = other.z > z ? other.z : z;
		return this;
	}

	/**
	 * <code>minLocal</code> computes the minimum value for each component in
	 * this and <code>other</code> vector. The result is stored in this vector.
	 * 
	 * @param other
	 */
	public Vector3I minLocal(Vector3I other)
	{
		x = other.x < x ? other.x : x;
		y = other.y < y ? other.y : y;
		z = other.z < z ? other.z : z;
		return this;
	}

	/**
	 * <code>zero</code> resets this vector's data to zero internally.
	 */
	public Vector3I zero()
	{
		x = y = z = 0;
		return this;
	}

	/**
	 * <code>angleBetween</code> returns (in radians) the angle between two
	 * vectors. It is assumed that both this vector and the given vector are
	 * unit vectors (iow, normalized).
	 * 
	 * @param otherVector
	 *            a unit vector to find the angle against
	 * @return the angle in radians.
	 */
	public int angleBetween(Vector3I otherVector)
	{
		int dotProduct = dot(otherVector);
		int angle = (int) Math.acos(dotProduct);
		return angle;
	}

	/**
	 * Sets this vector to the interpolation by changeAmnt from this to the
	 * finalVec this=(1-changeAmnt)*this + changeAmnt * finalVec
	 * 
	 * @param finalVec
	 *            The final vector to interpolate towards
	 * @param changeAmnt
	 *            An amount between 0.0 - 1.0 representing a precentage change
	 *            from this towards finalVec
	 */
	public Vector3I interpolate(Vector3I finalVec, int changeAmnt)
	{
		x = (1 - changeAmnt) * x + changeAmnt * finalVec.x;
		y = (1 - changeAmnt) * y + changeAmnt * finalVec.y;
		z = (1 - changeAmnt) * z + changeAmnt * finalVec.z;
		return this;
	}

	/**
	 * Sets this vector to the interpolation by changeAmnt from beginVec to
	 * finalVec this=(1-changeAmnt)*beginVec + changeAmnt * finalVec
	 * 
	 * @param beginVec
	 *            the beging vector (changeAmnt=0)
	 * @param finalVec
	 *            The final vector to interpolate towards
	 * @param changeAmnt
	 *            An amount between 0.0 - 1.0 representing a precentage change
	 *            from beginVec towards finalVec
	 */
	public Vector3I interpolate(Vector3I beginVec, Vector3I finalVec, int changeAmnt)
	{
		x = (1 - changeAmnt) * beginVec.x + changeAmnt * finalVec.x;
		y = (1 - changeAmnt) * beginVec.y + changeAmnt * finalVec.y;
		z = (1 - changeAmnt) * beginVec.z + changeAmnt * finalVec.z;
		return this;
	}

	/**
	 * Check a vector... if it is null or its ints are NaN or infinite, return
	 * false. Else return true.
	 * 
	 * @param vector
	 *            the vector to check
	 * @return true or false as stated above.
	 */
	public static boolean isValidVector(Vector3I vector)
	{
		if (vector == null) return false;
		if (Float.isNaN(vector.x) || Float.isNaN(vector.y) || Float.isNaN(vector.z)) return false;
		if (Float.isInfinite(vector.x) || Float.isInfinite(vector.y) || Float.isInfinite(vector.z)) return false;
		return true;
	}

	@Override
	public Vector3I clone()
	{
		return new Vector3I(x, y, z);
	}

	/**
	 * Saves this Vector3I into the given int[] object.
	 * 
	 * @param ints
	 *            The int[] to take this Vector3I. If null, a new int[3] is
	 *            created.
	 * @return The array, with X, Y, Z int values in that order
	 */
	public int[] toArray(int[] ints)
	{
		if (ints == null)
		{
			ints = new int[3];
		}
		ints[0] = x;
		ints[1] = y;
		ints[2] = z;
		return ints;
	}

	/**
	 * are these two vectors the same? they are is they both have the same x,y,
	 * and z values.
	 * 
	 * @param o
	 *            the object to compare for equality
	 * @return true if they are equal
	 */
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Vector3I)
		{
			Vector3I c = (Vector3I) o;
			return c.x == x && c.y == y && c.z == z;
		}
		return false;
	}

	/**
	 * <code>hashCode</code> returns a unique code for this vector object based
	 * on it's values. If two vectors are logically equivalent, they will return
	 * the same hash code value.
	 * 
	 * @return the hash code value of this vector.
	 */
	@Override
	public int hashCode()
	{
		int hash = 37;
		hash += 37 * hash + Integer.bitCount(x);
		hash += 37 * hash + Integer.bitCount(y);
		hash += 37 * hash + Integer.bitCount(z);
		return hash;
	}

	/**
	 * <code>toString</code> returns the string representation of this vector.
	 * The format is:
	 * 
	 * (X, Y, Z)
	 * 
	 * @return the string representation of this vector.
	 */
	@Override
	public String toString()
	{
		return "(" + x + ", " + y + ", " + z + ")";
	}

	public int getX()
	{
		return x;
	}

	public Vector3I setX(int x)
	{
		this.x = x;
		return this;
	}

	public int getY()
	{
		return y;
	}

	public Vector3I setY(int y)
	{
		this.y = y;
		return this;
	}

	public int getZ()
	{
		return z;
	}

	public Vector3I setZ(int z)
	{
		this.z = z;
		return this;
	}

	/**
	 * @param index
	 * @return x value if index == 0, y value if index == 1 or z value if index
	 *         == 2
	 * @throws IllegalArgumentException
	 *             if index is not one of 0, 1, 2.
	 */
	public int get(int index)
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
		throw new IllegalArgumentException("index must be either 0, 1 or 2");
	}

	/**
	 * @param index
	 *            which field index in this vector to set.
	 * @param value
	 *            to set to one of x, y or z.
	 * @throws IllegalArgumentException
	 *             if index is not one of 0, 1, 2.
	 */
	public void set(int index, int value)
	{
		switch (index)
		{
			case 0:
				x = value;
				return;
			case 1:
				y = value;
				return;
			case 2:
				z = value;
				return;
		}
		throw new IllegalArgumentException("index must be either 0, 1 or 2");
	}

	public Vector3I absLocal()
	{
		x = Math.abs(x);
		y = Math.abs(y);
		z = Math.abs(z);
		return this;
	}

	public Vector3I abs()
	{
		return new Vector3I(Math.abs(x), Math.abs(y), Math.abs(z));
	}
}
