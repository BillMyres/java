//Last edit: 07/29/2018 - TvB
package com.cekeh.utility;

/**
* Cekeh's Transform object
* Created 06/05/2018
* @author Thomas vanBommel (TvB)
*/
public class Transform {

	public Vector3f position;
	public Vector3f rotation;
	public Vector3f scale;
	
	public Matrix matrix;
	
	public boolean isCamera = false;
	
	/** 
	 * Create a new default camera transform object 
	 * position = (0, 0, 0)
	 * rotation = (0, 0, 0)
	 * scale = (1, 1, 1)
	 */
	public Transform(boolean isCamera) {
		this();
		
		this.isCamera = isCamera;
	}
	
	/** 
	 * Create a new default transform object 
	 * position = (0, 0, 0)
	 * rotation = (0, 0, 0)
	 * scale = (1, 1, 1)
	 */
	public Transform() {
		this(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
	}
	
	/**
	 * Create a new Transform object
	 * @param position The position of the transform in 3D space
	 * @param rotation The rotation of the transform in 3D space
	 * @param scale The scale of the transform in 3D space
	 */
	public Transform(Vector3f position, Vector3f rotation, Vector3f scale) {
		this.position = position;
		this.rotation = rotation;
		this.scale    = scale;
		
		if(isCamera) {
			matrix = Matrix.cameraMatrix(this);
		}else {
			matrix = Matrix.objectMatrix(this);
		}
	}
	
	/** Update this transform */
	public void update() {
		if(isCamera) {
			matrix = Matrix.cameraMatrix(this);
		}else {
			matrix = Matrix.objectMatrix(this);
		}
	}
	
	/**
	 * Get the right vector based off the position, rotation, and scale
	 * @return Right vector of the transform
	 */
	public Vector3f right() {
		return new Vector3f(matrix.data[0], matrix.data[1], matrix.data[2]);
	}
	
	/**
	 * Get the up vector based off the position, rotation, and scale
	 * @return Up vector of the transform
	 */
	public Vector3f up() {		
		return new Vector3f(matrix.data[4], matrix.data[5], matrix.data[6]);
	}
	
	/**
	 * Get the forward vector based off the position, rotation, and scale
	 * @return Forward vector of the transform
	 */
	public Vector3f forward() {
		return new Vector3f(matrix.data[8], matrix.data[9], matrix.data[10]);
	}
}
