// Last edit: 05/20/2018 - TvB
package com.cekeh.lwjgl.opengl;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL15;
import org.lwjgl.system.MemoryUtil;

/**
 * Cekeh's Vertex Buffer Object (VBO)
 * Created 05/20/2018
 * @author Thomas vanBommel (TvB)
 */
public class VertexBufferObject {

	private final int handle;
	
	private final int target;
	
	private final int length;
	
	
	public VertexBufferObject(float[] data, int target) {
		this.target = target;
		
		handle = GL15.glGenBuffers();
		length = data.length;
		
		bind();
		
		GL15.glBufferData(target, data, GL15.GL_STATIC_DRAW);
	}
	
	public VertexBufferObject(int[] data, int target) {
		this.target = target;
		
		handle = GL15.glGenBuffers();
		length = data.length;
		
		bind();
		
		GL15.glBufferData(target, data, GL15.GL_STATIC_DRAW);
	}
	
	/** Bind this VBO to the target */
	public void bind() {
		GL15.glBindBuffer(target, handle);
	}
	
	/** Unbind this VBO from the target */
	public void unbind() {
		GL15.glBindBuffer(target, 0);
	}
	
	/** Clean up the memory resources */
	public void cleanUp() {
		GL15.glDeleteBuffers(handle);
	}
	
	// GET / SET
	
	/** Get the length of the Vertex Buffer Object (VBO) array 
	 * @return The length of the VBO array */
	public int getLength() { return length; }
	
//	/**
//	 * Get the handle of this VBO
//	 * @return The handle / reference ID for this openGL object
//	 */
//	public int getHandle() {
//		return handle;
//	}
}
