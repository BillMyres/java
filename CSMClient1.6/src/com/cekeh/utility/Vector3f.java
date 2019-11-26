// Last edit: 06/15/2018 - TvB
package com.cekeh.utility;

/**
 * Cekeh's Vector3f object
 * Created 06/04/2018
 * @author Thomas vanBommel (TvB)
 */
public class Vector3f {

	public float x;
	public float y;
	public float z;
	
	/**
	 * Create a new Vector3f object
	 * @param x The x value
	 * @param y The y value
	 * @param z The z value
	 */
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3f add(Vector3f a) {
		x += a.x;
		y += a.y;
		z += a.z;
		
		return this;
	}
	
	public static Vector3f multiply(Vector3f a, float b) {
		return new Vector3f(a.x * b, a.y * b, a.z * b);
	}
	
	public static Vector3f multiply(Vector3f a, Vector3f b) {
		return new Vector3f(a.x * b.x, a.y * b.y, a.z * b.z);
	}
	
	public static float dot(Vector3f a, Vector3f b) {
		return a.x * b.x + a.y * b.y + a.z * b.z;
	}
	
	public static Vector3f cross(Vector3f a, Vector3f b) {
		return new Vector3f(
			a.y * b.z - a.z * b.y,
			a.z * b.x - a.x * b.z,
			a.x * b.y - a.y * b.x
		);
	}
}
