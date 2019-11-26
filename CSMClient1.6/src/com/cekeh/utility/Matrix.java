//Last edit: 07/29/2018 - TvB
package com.cekeh.utility;

/**
* Cekeh's Matrix object
* Created 06/05/2018
* @author Thomas vanBommel (TvB)
*/
public class Matrix {

	public final float[] data = {
		1, 0, 0, 0,
		0, 1, 0, 0,
		0, 0, 1, 0,
		0, 0, 0, 1
	};

	/**
	 * Create a new matrix object with specified values
	 * @param data Values to be places in the matrix
	 */
	public Matrix(float[] data) {		
		int count = this.data.length < data.length ? this.data.length : data.length;
		
		for(int i = 0; i < count; i++) {
			this.data[i] = data[i];
		}
	}
	
	/** Create new base matrix */
	public Matrix() {}
	
	/**
	 * Generate a new transformation matrix
	 * @param transform Position, rotation (Degrees), and scale of the matrix in 3D space
	 * @return Transformation matrix
	 */
//	public static Matrix transformationMatrix(Transform transform) {
//		return transformationMatrix(transform.position, transform.rotation, transform.scale);
//	}
	
	/**
	 * Generate a new transformation matrix
	 * @param position Position of the matrix in 3D space
	 * @param rotation Rotation of the matrix in 3D space (Degrees)
	 * @param scale Scale of the matrix in 3D space
	 * @return Transformation matrix
	 */
//	public static Matrix transformationMatrix(Vector3f position, Vector3f rotation, Vector3f scale) {
//		Matrix position_matrix = new Matrix();
//		position_matrix.data[3] = position.x;
//		position_matrix.data[7] = position.y;
//		position_matrix.data[11]= position.z;
//		
//		Matrix scale_matrix = new Matrix();
//		scale_matrix.data[0] = scale.x;
//		scale_matrix.data[5] = scale.y;
//		scale_matrix.data[10]= scale.z;
//		
//		Matrix xrotation_matrix = new Matrix();
//		xrotation_matrix.data[5] = (float) Math.cos(Math.toRadians(rotation.x));
//		xrotation_matrix.data[6] = (float)-Math.sin(Math.toRadians(rotation.x));
//		xrotation_matrix.data[9] = (float) Math.sin(Math.toRadians(rotation.x));
//		xrotation_matrix.data[10]= (float) Math.cos(Math.toRadians(rotation.x));
//		
//		Matrix yrotation_matrix = new Matrix();
//		yrotation_matrix.data[0] = (float) Math.cos(Math.toRadians(rotation.y));
//		yrotation_matrix.data[2] = (float) Math.sin(Math.toRadians(rotation.y));
//		yrotation_matrix.data[8] = (float)-Math.sin(Math.toRadians(rotation.y));
//		yrotation_matrix.data[10]= (float) Math.cos(Math.toRadians(rotation.y));
//		
//		Matrix zrotation_matrix = new Matrix();
//		zrotation_matrix.data[0] = (float) Math.cos(Math.toRadians(rotation.z));
//		zrotation_matrix.data[1] = (float)-Math.sin(Math.toRadians(rotation.z));
//		zrotation_matrix.data[4] = (float) Math.sin(Math.toRadians(rotation.z));
//		zrotation_matrix.data[5] = (float) Math.cos(Math.toRadians(rotation.z));
//			
//		Matrix position_scale_matrix = multiply(position_matrix, scale_matrix);
//		
//		Matrix matrix = position_scale_matrix;
//		matrix = multiply(matrix, xrotation_matrix);
//		matrix = multiply(matrix, yrotation_matrix);
//		matrix = multiply(matrix, zrotation_matrix);
//		//matrix = multiply(matrix, position_scale_matrix);
//		
//		return matrix;
//	}
	
	/**
	 * Generate object transformation matrix from transform
	 * @param transform Position rotation, and scale of the matrix
	 * @return Object transformation matrix
	 */
	public static Matrix objectMatrix(Transform transform) {
		return multiply(
			multiply(
				positionMatrix(transform.position),
				scaleMatrix(transform.scale)
			),
			rotationMatrix(transform.rotation)
		);
	}
	
	/**
	 * Generate camera transformation matrix from transform
	 * @param transform Position rotation, and scale of the matrix
	 * @return Camera transformation matrix
	 */
	public static Matrix cameraMatrix(Transform transform) {
		Matrix position = positionMatrix(transform.position);
		Matrix scale = scaleMatrix(transform.scale);
		Matrix rotation = rotationMatrix(transform.rotation);
		
		//Matrix position_scale = multiply(position, scale);
		Matrix rotation_scale = multiply(rotation, scale);
		
		Matrix matrix = multiply(
			rotation_scale,
			position
		);
		
//		System.out.println("Matrix (x, y, z): " 
//			+ matrix.data[3] + ", " 
//			+ matrix.data[7] + ", " 
//			+ matrix.data[11]);
		
		return matrix;
	}
	
	/**
	 * Generate the position matirx
	 * @param position Position to set the matrix to
	 * @return Position matrix
	 */
	public static Matrix positionMatrix(Vector3f position) {
		Matrix matrix = new Matrix();
		matrix.data[3] = position.x;
		matrix.data[7] = position.y;
		matrix.data[11]= position.z;
		
		return matrix;
	}
	
	/**
	 * Generate the scale matrix
	 * @param scale Scale to set the matrix to
	 * @return Scale matrix
	 */
	public static Matrix scaleMatrix(Vector3f scale) {
		Matrix matirx = new Matrix();
		matirx.data[0] = scale.x;
		matirx.data[5] = scale.y;
		matirx.data[10]= scale.z;
		
		return matirx;
	}
	
	/**
	 * Generate the rotation matrix
	 * @param rotation Rotation to set the matrix to
	 * @return Rotation matrix
	 */
	public static Matrix rotationMatrix(Vector3f rotation) {
		Matrix x = new Matrix();
		x.data[5] = (float) Math.cos(Math.toRadians(rotation.x));
		x.data[6] = (float)-Math.sin(Math.toRadians(rotation.x));
		x.data[9] = (float) Math.sin(Math.toRadians(rotation.x));
		x.data[10]= (float) Math.cos(Math.toRadians(rotation.x));
		
		Matrix y = new Matrix();
		y.data[0] = (float) Math.cos(Math.toRadians(rotation.y));
		y.data[2] = (float) Math.sin(Math.toRadians(rotation.y));
		y.data[8] = (float)-Math.sin(Math.toRadians(rotation.y));
		y.data[10]= (float) Math.cos(Math.toRadians(rotation.y));
		
		Matrix z = new Matrix();
		z.data[0] = (float) Math.cos(Math.toRadians(rotation.z));
		z.data[1] = (float)-Math.sin(Math.toRadians(rotation.z));
		z.data[4] = (float) Math.sin(Math.toRadians(rotation.z));
		z.data[5] = (float) Math.cos(Math.toRadians(rotation.z));
		
		return multiply(multiply(x, y), z);
	}
	
	/**
	 * Multiply two matrices together (a*b != b*a)
	 * @param a First matrix (left of the equation, *rows)
	 * @param b Second matrix (right of the equation, *columns)
	 * @return Matrix representing a * b
	 */
	public static Matrix multiply(Matrix a, Matrix b) {
		Matrix matrix = new Matrix();
		
		for(int i = 0; i < 16; i++) {
			int row = (int) Math.floor(i / 4);
			int col = i % 4;
			
			float value = 0;
			
			for(int j = 0; j < 4; j++) {
				value += a.data[(row * 4) + j] * b.data[col + (4 * j)];
			}
			
			matrix.data[i] = value;
		}
		
		return matrix;
	}
	
	public static String toString(Matrix matrix) {
		String string = "";
		
		for(int i = 0; i < 16; i += 4) {
			string += (i != 0 ? ", " : "") + matrix.data[i];
			string += ", " + matrix.data[i + 1];
			string += ", " + matrix.data[i + 2];
			string += ", " + matrix.data[i + 3] + "\n";
		}
		
		return string;
	}
}
