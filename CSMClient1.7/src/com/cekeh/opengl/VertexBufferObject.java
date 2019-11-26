// 08/06/2018 - TvB
package com.cekeh.opengl;

import org.lwjgl.opengl.GL15;

/** 
 * VertexBufferObject.java
 * 08/06/2018
 * @author Thomas
 */
public class VertexBufferObject {

	private int handle;
	
	/**
	 * Create a new VertexBufferObject
	 */
	public VertexBufferObject() {
		handle = GL15.glGenBuffers();
	}
	
	/* Public methods */
	
	/**
	 * Bind this VertexBufferObject to an OpenGL target
	 * @param target OpenGL buffer target
	 */
	public void bind(int target) {
		GL15.glBindBuffer(target, handle);
	}
	
	/**
	 * Bind OpenGL target to null
	 * @param target OpenGL buffer target
	 */
	public void unbind(int target) {
		GL15.glBindBuffer(target, 0);
	}
	
	/**
	 * Terminate this VertexBufferObject, and clean up resources
	 */
	public void terminate() {
		GL15.glDeleteBuffers(handle);
	}
}
