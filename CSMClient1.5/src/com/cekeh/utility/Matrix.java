// Last edit: 05/31/2018 - TvB
package com.cekeh.utility;

/**
 * Cekeh's Matrix object
 * Created 05/24/2018
 * @author Thomas vanBommel (TvB)
 */
public class Matrix {
	
	private boolean reversed = false;
	
	private final float[] position = { 0, 0, 0 };
	private final float[] rotation = { 0, 0, 0 };
	private final float[] scale    = { 1, 1, 1 };
	
	/** Create a new matrix object */
	public Matrix() {}
	
	/** Create a new matrix object that will "reverse" the rotation and position/scale matrices */
	public Matrix(boolean reversed) {
		this.reversed = reversed;
	}
	
	/** Translate this matrix in 3D space */
	public Matrix translate(float x, float y, float z) {
		position[0] += x;
		position[1] += y;
		position[2] += z;
		
		return this;
	}
	
	/** Rotate this matrix in 3D space */
	public Matrix rotate(float x, float y, float z) {
		float temp;
		
		rotation[0] = (temp = rotation[0] + x) >= 360 ? temp - 360 : temp;
		rotation[1] = (temp = rotation[1] + y) >= 360 ? temp - 360 : temp;
		rotation[2] = (temp = rotation[2] + z) >= 360 ? temp - 360 : temp;
		
		return this;
	}
	
	/** Scale this matrix in 3D space */
	public Matrix scale(float x, float y, float z) {
		scale[0] *= x;
		scale[1] *= y;
		scale[2] *= z;
		
		return this;
	}
	
	/**
	 * Multiply two matrices together !(a * b != b * a)
	 * @param a The first matrix (left of the equation)
	 * @param b The second matrix (right of the equation)
	 * @return A float[16] matrix that represents a * b
	 */
	private float[] multiply(float[] a, float[] b) {
		float[] matrix = new float[16];
		
		for(int i = 0; i < 16; i++) {
			int row = (int) Math.floor(i / 4);
			int col = i % 4;

			float value = 0;
			
			for(int j = 0; j < 4; j++) {
				value += a[(row * 4) + j] * b[col + (4 * j)];
			}
			
			matrix[i] = value;
		}
		
		return matrix;
	}
	
	/**
	 * Generate the transformation matrix from position, rotation, and scale of this transform
	 * @return float[16] transformation matrix
	 */
	public float[] generate() {
		float[] position_scale_data = {
			scale[0], 	0, 			0, 			position[0],
			0, 			scale[1], 	0, 			position[1],
			0, 			0, 			scale[2], 	position[2],
			0, 			0, 			0, 			1
		};
		
		float[] xrotation_data = {
			1, 0, 												0, 												0,
			0, (float) Math.cos(Math.toRadians(rotation[0])), 	(float)-Math.sin(Math.toRadians(rotation[0])), 	0,
			0, (float) Math.sin(Math.toRadians(rotation[0])), 	(float) Math.cos(Math.toRadians(rotation[0])), 	0,
			0, 0, 												0, 												1
		};
		
		float[] yrotation_data = {
			(float) Math.cos(Math.toRadians(rotation[1])), 	0, (float) Math.sin(Math.toRadians(rotation[1])), 	0,
			0, 												1, 0, 												0,
			(float)-Math.sin(Math.toRadians(rotation[1])), 	0, (float) Math.cos(Math.toRadians(rotation[1])), 	0,
			0, 												0, 0, 												1
		};
		
		float[] zrotation_data = {
			(float) Math.cos(Math.toRadians(rotation[2])), 	(float)-Math.sin(Math.toRadians(rotation[2])),	0, 0,
			(float) Math.sin(Math.toRadians(rotation[2])), 	(float) Math.cos(Math.toRadians(rotation[2])), 	0, 0,
			0, 												0, 												1, 0,
			0, 												0, 												0, 1
		};
		
		float[] rotation_matrix = multiply(multiply(yrotation_data, xrotation_data), zrotation_data);
		
		if(reversed) {
			return multiply(rotation_matrix, position_scale_data);
		}
		
		return multiply(position_scale_data, rotation_matrix);
	}
	
	public float[] right() {
		float[] matrix = generate();
		
		if(reversed) {
			return new float[] { matrix[0], matrix[4], -matrix[8] };
		}
		
		return new float[] { matrix[0], matrix[4], matrix[8] };
	}
	
	public float[] up() {
		float[] matrix = generate();
		
		if(reversed) {
			return new float[] { matrix[1], -matrix[5], matrix[9] };
		}
		
		return new float[] { matrix[1], matrix[5], matrix[9] };
	}
	
	public float[] forward() {
		float[] matrix = generate();
		
		if(reversed) {
			return new float[] { -matrix[2], matrix[6], matrix[10] };
		}
		
		return new float[] { matrix[2], matrix[6], matrix[10] };
	}
	
	/**
	 * Create a string representing this matrix
	 * @return A String with the matrix data
	 */
	public String toString() {
		float[] data = generate();
		String string = "";
		
		for(int i = 0; i < 16; i+=4) {
			string += String.format("%.2f, %.2f, %.2f, %.2f,\n", data[i], data[i + 1], data[i + 2], data[i + 3]);
		}
		
		return string;
	}
}
