// 08/06/2018 - TvB
package com.cekeh.opengl;

import org.lwjgl.opengl.GL30;

/** 
 * VertexArrayObject.java
 * 08/04/2018
 * @author Thomas
 */
public class VertexArrayObject {

	private int handle;
		
	/**
	 * Create a new VertexArrayObject
	 */
	public VertexArrayObject() {
		handle = GL30.glGenVertexArrays();
	}
	
	/* Public methods */					
	
	/**
	 * Bind this vertex array object for use
	 */
	public void bind() {
		GL30.glBindVertexArray(handle);
	}
	
	/**
	 * Unbind this vertex array object
	 */
	public void unbind() {
		GL30.glBindVertexArray(0);
	}
	
	/**
	 * Terminate this vertex array object, and clean up resources
	 */
	public void terminate() {
		GL30.glDeleteVertexArrays(handle);
	}
}
