// 08/13/2018 - TvB
package com.cekeh.utility;

/** 
 * Vector4f.java
 * 08/13/2018
 * @author Thomas
 */
public class Vector4f {
	
	public float x;
	public float y;
	public float z;
	public float w;
	
	/**
	 * Create a new Vector4f
	 * @param x The x / [0] value
	 * @param y The y / [1] value
	 * @param z The z / [2] value
	 * @param w The w / [3] value
	 */
	public Vector4f(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	/* Getters / setters */
	
	/**
	 * Return this vector4f as a float[] array
	 * @return new float[] { x, y, z, w }
	 */
	public float[] data() {
		return new float[] { x, y, z, w };
	}
}
