package main.com.hk.eb.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Vector3F implements Cloneable, java.io.Serializable
{
	static final long serialVersionUID = 1;
	private static final Logger logger = LogManager.getLogger(Vector3F.class.getSimpleName());
	public final static Vector3F ZERO = new Vector3F(0, 0, 0);
	public final static Vector3F NAN = new Vector3F(Float.NaN, Float.NaN, Float.NaN);
	public final static Vector3F UNIT_X = new Vector3F(1, 0, 0);
	public final static Vector3F UNIT_Y = new Vector3F(0, 1, 0);
	public final static Vector3F UNIT_Z = new Vector3F(0, 0, 1);
	public final static Vector3F UNIT_XYZ = new Vector3F(1, 1, 1);
	public final static Vector3F POSITIVE_INFINITY = new Vector3F(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
	public final static Vector3F NEGATIVE_INFINITY = new Vector3F(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
	/**
	 * the x value of the vector.
	 */
	public float x;
	/**
	 * the y value of the vector.
	 */
	public float y;
	/**
	 * the z value of the vector.
	 */
	public float z;

	/**
	 * Constructor instantiates a new <code>Vector3f</code> with default values
	 * of (0,0,0).
	 * 
	 */
	public Vector3F()
	{
		x = y = z = 0;
	}

	/**
	 * Constructor instantiates a new <code>Vector3f</code> with provides
	 * values.
	 * 
	 * @param x
	 *            the x value of the vector.
	 * @param y
	 *            the y value of the vector.
	 * @param z
	 *            the z value of the vector.
	 */
	public Vector3F(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3F(Entity e)
	{
		x = (float) e.posX;
		y = (float) e.posY;
		z = (float) e.posZ;
	}

	/**
	 * Constructor instantiates a new <code>Vector3f</code> with the 
	 * single value.
	 * 
	 * @param val
	 *            the x, y, and z value of the vector.
	 */
	public Vector3F(float val)
	{
		x = val;
		y = val;
		z = val;
	}

	/**
	 * Constructor instantiates a new <code>Vector3f</code> that is a copy of
	 * the provided vector
	 * 
	 * @param copy
	 *            The Vector3f to copy
	 */
	public Vector3F(Vector3F copy)
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
	public Vector3F set(float x, float y, float z)
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
	public Vector3F set(Vector3F vect)
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
	public Vector3F add(Vector3F vec)
	{
		if (null == vec)
		{
			logger.warn("Provided vector is null, null returned.");
			return null;
		}
		return new Vector3F(x + vec.x, y + vec.y, z + vec.z);
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
	public Vector3F add(Vector3F vec, Vector3F result)
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
	public Vector3F addLocal(Vector3F vec)
	{
		if (null == vec)
		{
			logger.warn("Provided vector is null, null returned.");
			return null;
		}
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
	public Vector3F add(float addX, float addY, float addZ)
	{
		return new Vector3F(x + addX, y + addY, z + addZ);
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
	public Vector3F addLocal(float addX, float addY, float addZ)
	{
		x += addX;
		y += addY;
		z += addZ;
		return this;
	}

	/**
	 * 
	 * <code>scaleAdd</code> multiplies this vector by a scalar then adds the
	 * given Vector3f.
	 * 
	 * @param scalar
	 *            the value to multiply this vector by.
	 * @param add
	 *            the value to add
	 */
	public Vector3F scaleAdd(float scalar, Vector3F add)
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
	public Vector3F scaleAdd(float scalar, Vector3F mult, Vector3F add)
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
	public float dot(Vector3F vec)
	{
		if (null == vec)
		{
			logger.warn("Provided vector is null, 0 returned.");
			return 0;
		}
		return x * vec.x + y * vec.y + z * vec.z;
	}

	/**
	 * <code>rotate</code> rotates a copy of this
	 * vector the specified degrees from it's current point;
	 * 
	 * @param degrees the degrees to rotate it.
	 * @return a new vector with the specified rotation
	 */
	public Vector3F rotate(float degrees)
	{
		float x1 = (float) (x * Math.cos(Math.toRadians(degrees)) - z * Math.sin(Math.toRadians(degrees)));
		float z1 = (float) (x * Math.sin(Math.toRadians(degrees)) + z * Math.cos(Math.toRadians(degrees)));
		return new Vector3F(x1, y, z1);
	}

	/**
	 * <code>rotate</code> rotates this vector the specified degrees 
	 * from it's current point;
	 * 
	 * @param degrees the degrees to rotate it.
	 * @return a new vector with the specified rotation
	 */
	public Vector3F rotateLocal(float degrees)
	{
		float x1 = (float) (x * Math.cos(degrees) - y * Math.sin(degrees));
		float y1 = (float) (x * Math.sin(degrees) + y * Math.cos(degrees));
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
	public Vector3F cross(Vector3F v)
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
	public Vector3F cross(Vector3F v, Vector3F result)
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
	public Vector3F cross(float otherX, float otherY, float otherZ, Vector3F result)
	{
		if (result == null)
		{
			result = new Vector3F();
		}
		float resX = y * otherZ - z * otherY;
		float resY = z * otherX - x * otherZ;
		float resZ = x * otherY - y * otherX;
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
	public Vector3F crossLocal(Vector3F v)
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
	public Vector3F crossLocal(float otherX, float otherY, float otherZ)
	{
		float tempx = y * otherZ - z * otherY;
		float tempy = z * otherX - x * otherZ;
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
	public Vector3F project(Vector3F other)
	{
		float n = dot(other); // A . B
		float d = other.lengthSquared(); // |B|^2
		return new Vector3F(other).normalizeLocal().multLocal(n / d);
	}

	/**
	 * Projects this vector onto another vector, stores the result in this
	 * vector
	 * 
	 * @param other
	 *            The vector to project this vector onto
	 * @return This Vector3f, set to the projection result
	 */
	public Vector3F projectLocal(Vector3F other)
	{
		float n = dot(other); // A . B
		float d = other.lengthSquared(); // |B|^2
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
		float len = length();
		return 0.99f < len && len < 1.01f;
	}

	public Vector3I floored()
	{
		return new Vector3I(MathHelper.floor_float(x), MathHelper.floor_float(y), MathHelper.floor_float(z));
	}

	/**
	 * <code>length</code> calculates the magnitude of this vector.
	 * 
	 * @return the length or magnitude of the vector.
	 */
	public float length()
	{
		return (float) Math.sqrt(lengthSquared());
	}

	/**
	 * <code>lengthSquared</code> calculates the squared value of the magnitude
	 * of the vector.
	 * 
	 * @return the magnitude squared of the vector.
	 */
	public float lengthSquared()
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
	public float distanceSquared(Vector3F v)
	{
		double dx = x - v.x;
		double dy = y - v.y;
		double dz = z - v.z;
		return (float) (dx * dx + dy * dy + dz * dz);
	}

	/**
	 * <code>distance</code> calculates the distance between this vector and
	 * vector v.
	 * 
	 * @param v
	 *            the second vector to determine the distance.
	 * @return the distance between the two vectors.
	 */
	public float distance(Vector3F v)
	{
		return (float) Math.sqrt(distanceSquared(v));
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
	public Vector3F mult(float scalar)
	{
		return new Vector3F(x * scalar, y * scalar, z * scalar);
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
	public Vector3F mult(float scalar, Vector3F product)
	{
		if (null == product)
		{
			product = new Vector3F();
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
	public Vector3F multLocal(float scalar)
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
	public Vector3F multLocal(Vector3F vec)
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
	public Vector3F multLocal(float x, float y, float z)
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
	public Vector3F mult(Vector3F vec)
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
	public Vector3F mult(Vector3F vec, Vector3F store)
	{
		if (null == vec)
		{
			logger.warn("Provided vector is null, null returned.");
			return null;
		}
		if (store == null)
		{
			store = new Vector3F();
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
	public Vector3F divide(float scalar)
	{
		scalar = 1f / scalar;
		return new Vector3F(x * scalar, y * scalar, z * scalar);
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
	public Vector3F divideLocal(float scalar)
	{
		scalar = 1f / scalar;
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
	public Vector3F divide(Vector3F scalar)
	{
		return new Vector3F(x / scalar.x, y / scalar.y, z / scalar.z);
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
	public Vector3F divideLocal(Vector3F scalar)
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
	public Vector3F negate()
	{
		return new Vector3F(-x, -y, -z);
	}

	/**
	 * 
	 * <code>negateLocal</code> negates the internal values of this vector.
	 * 
	 * @return this.
	 */
	public Vector3F negateLocal()
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
	public Vector3F subtract(Vector3F vec)
	{
		return new Vector3F(x - vec.x, y - vec.y, z - vec.z);
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
	public Vector3F subtractLocal(Vector3F vec)
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
	public Vector3F subtract(Vector3F vec, Vector3F result)
	{
		if (result == null)
		{
			result = new Vector3F();
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
	public Vector3F subtract(float subtractX, float subtractY, float subtractZ)
	{
		return new Vector3F(x - subtractX, y - subtractY, z - subtractZ);
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
	public Vector3F subtractLocal(float subtractX, float subtractY, float subtractZ)
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
	public Vector3F normalize()
	{
		// float length = length();
		// if (length != 0) {
		// return divide(length);
		// }
		//
		// return divide(1);
		float length = x * x + y * y + z * z;
		if (length != 1f && length != 0f)
		{
			length = (float) (1.0f / Math.sqrt(length));
			return new Vector3F(x * length, y * length, z * length);
		}
		return clone();
	}

	/**
	 * <code>normalizeLocal</code> makes this vector into a unit vector of
	 * itself.
	 * 
	 * @return this.
	 */
	public Vector3F normalizeLocal()
	{
		float length = x * x + y * y + z * z;
		if (length != 1f && length != 0f)
		{
			length = (float) (1.0f / Math.sqrt(length));
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
	public Vector3F maxLocal(Vector3F other)
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
	public Vector3F minLocal(Vector3F other)
	{
		x = other.x < x ? other.x : x;
		y = other.y < y ? other.y : y;
		z = other.z < z ? other.z : z;
		return this;
	}

	/**
	 * <code>zero</code> resets this vector's data to zero internally.
	 */
	public Vector3F zero()
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
	public float angleBetween(Vector3F otherVector)
	{
		float dotProduct = dot(otherVector);
		float angle = (float) Math.acos(dotProduct);
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
	public Vector3F interpolate(Vector3F finalVec, float changeAmnt)
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
	public Vector3F interpolate(Vector3F beginVec, Vector3F finalVec, float changeAmnt)
	{
		x = (1 - changeAmnt) * beginVec.x + changeAmnt * finalVec.x;
		y = (1 - changeAmnt) * beginVec.y + changeAmnt * finalVec.y;
		z = (1 - changeAmnt) * beginVec.z + changeAmnt * finalVec.z;
		return this;
	}

	/**
	 * Check a vector... if it is null or its floats are NaN or infinite, return
	 * false. Else return true.
	 * 
	 * @param vector
	 *            the vector to check
	 * @return true or false as stated above.
	 */
	public static boolean isValidVector(Vector3F vector)
	{
		if (vector == null) return false;
		if (Float.isNaN(vector.x) || Float.isNaN(vector.y) || Float.isNaN(vector.z)) return false;
		if (Float.isInfinite(vector.x) || Float.isInfinite(vector.y) || Float.isInfinite(vector.z)) return false;
		return true;
	}

	@Override
	public Vector3F clone()
	{
		return new Vector3F(x, y, z);
	}

	/**
	 * Saves this Vector3f into the given float[] object.
	 * 
	 * @param floats
	 *            The float[] to take this Vector3f. If null, a new float[3] is
	 *            created.
	 * @return The array, with X, Y, Z float values in that order
	 */
	public float[] toArray(float[] floats)
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
		if (!(o instanceof Vector3F)) return false;
		if (this == o) return true;
		Vector3F comp = (Vector3F) o;
		if (Float.compare(x, comp.x) != 0) return false;
		if (Float.compare(y, comp.y) != 0) return false;
		if (Float.compare(z, comp.z) != 0) return false;
		return true;
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
		hash += 37 * hash + Float.floatToIntBits(x);
		hash += 37 * hash + Float.floatToIntBits(y);
		hash += 37 * hash + Float.floatToIntBits(z);
		return hash;
	}

	/**
	 * <code>toString</code> returns the string representation of this vector.
	 * The format is:
	 * 
	 * org.jme.math.Vector3f [X=XX.XXXX, Y=YY.YYYY, Z=ZZ.ZZZZ]
	 * 
	 * @return the string representation of this vector.
	 */
	@Override
	public String toString()
	{
		return "(" + x + ", " + y + ", " + z + ")";
	}

	public float getX()
	{
		return x;
	}

	public Vector3F setX(float x)
	{
		this.x = x;
		return this;
	}

	public float getY()
	{
		return y;
	}

	public Vector3F setY(float y)
	{
		this.y = y;
		return this;
	}

	public float getZ()
	{
		return z;
	}

	public Vector3F setZ(float z)
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
	public void set(int index, float value)
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

	public Vector3F absLocal()
	{
		x = Math.abs(x);
		y = Math.abs(y);
		z = Math.abs(z);
		return this;
	}

	public Vector3F abs()
	{
		return new Vector3F(Math.abs(x), Math.abs(y), Math.abs(z));
	}
}
