// Last edit: 06/11/2018 - TvB
package com.cekeh.opengl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

/**
 * Cekeh's Attribute object (VBO)
 * Created 06/04/2018
 * @author Thomas vanBommel (TvB)
 */
public class Attribute {

	private int handle;
	
	public final String name;
	public final int index;
	
	private final int size;
	private final int stride;
	private final int offset;
	
	private final float[] data;
	
	/**
	 * Create a new attribute object (VBO)
	 * @param name Name of the attribute
	 * @param index Index of the attribute (0, 1, 2, 3, ...)
	 * @param size Size of the vector (2D = 2, 3D = 3)
	 * @param stride Offset between consecutive attributes, 0 meaning attributes are "tightly packed"
	 * @param offset Offset of the first component
	 * @param data Attribute data to be parsed
	 */
	public Attribute(String name, int index, int size, int stride, int offset, float[] data) {
		this.name = name;
		
		this.index = index;
		this.size = size;
		this.stride = stride;
		this.offset = offset;
		this.data = data;
	}
	
	/** Bind this attribute object */
	public void bind() {
		handle = GL15.glGenBuffers();
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, handle);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, data, GL15.GL_STATIC_DRAW);
	
		GL20.glEnableVertexAttribArray(index);
		GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, stride, offset);
	}
	
	/** Clean up memory resources */
	public void cleanUp() {
		GL15.glDeleteBuffers(handle);
	}
}
