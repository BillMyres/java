// Last edit: 05/31/2018 - TvB
package com.cekeh.lwjgl.opengl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

/**
 * Cekeh's Vertex Attribute object
 * Created 05/20/2018
 * @author Thomas vanBommel (TvB)
 */
public class VertexAttribute {

	private VertexBufferObject vbo;
	
	private final int index;
	
	private final int vector_size;
	
	private final int stride;
	private final int start_offset;
	
	private final float[] data;
	
	/**
	 * Create a new Vertex Attribute (VBO)
	 * @param index The index of the attribute (0 usually is "position")
	 * @param vector_size The size of the vectors in the float[] data
	 * @param stride How many index's to skip until the next vector, 0 = tightly packed
	 * @param start_offset Where in the data to start
	 * @param data The data for the Vertex Buffer Object (VBO)
	 */
	public VertexAttribute(int index, int vector_size, int stride, int start_offset, float[] data) {
		this.index = index;
		
		this.vector_size = vector_size;
		
		this.stride = stride * 4;
		this.start_offset = start_offset * 4;
		
		this.data = data;
	}
	
	/** Bind this Vertex Attribute (VBO) */
	public void bind() {
		vbo = new VertexBufferObject(data, GL15.GL_ARRAY_BUFFER);
		
		GL20.glEnableVertexAttribArray(index);
		GL20.glVertexAttribPointer(index, vector_size, GL11.GL_FLOAT, false, stride, start_offset);
	}
}
