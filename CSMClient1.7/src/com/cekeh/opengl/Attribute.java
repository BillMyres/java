// 08/06/2018 - TvB
package com.cekeh.opengl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

/** 
 * Attribute.java
 * 08/06/2018
 * @author Thomas
 */
public class Attribute extends VertexBufferObject {
	
	public final String name;
	
	public final int index;
	public final int size;
	public final int stride;
	public final int pointer;
	public final int type;
	public final int target;
	public final int length;
	
	public final boolean element_array;
	public final boolean normalized;
	
	/**
	 * Create a new Attribute (float[] data)
	 * @param index Index of this specific vertex attribite
	 * @param element_array Specifies if this attribute should be bound to GL_ELEMENT_ARRAY_BUFFER
	 * @param name GLSL attribute name
	 * @param data Data that makes up the attribute
	 * @param size Size of the vector being used, 2 = 2D (x, y), 3 = 3D (x, y, z), 4 = 4D (x, y, z, w), etc...
	 * @param stride Offset between vertex attributes, 0 = tightly packed.
	 * @param pointer Offset of the first component
	 * @param normalized Should fixed-point values be normalized
	 */
	public Attribute(int index, boolean element_array, String name, float[] data, int size, int stride, int pointer, boolean normalized) {
		super();
		
		this.index = index;
		this.element_array = element_array;
		this.name = name;
		this.size = size;
		this.stride = stride;
		this.pointer = pointer;
		this.normalized = normalized;
		
		this.type = GL11.GL_FLOAT;
		this.length = data.length;
		
		target = element_array ? GL15.GL_ELEMENT_ARRAY_BUFFER : GL15.GL_ARRAY_BUFFER;
		
		super.bind(target);
		GL15.glBufferData(target, data, GL15.GL_STATIC_DRAW);
		super.unbind(target);
	}
	
	/**
	 * Create a new Attribute (int[] data)
	 * @param index Index of this specific vertex attribite
	 * @param element_array Specifies if this attribute should be bound to GL_ELEMENT_ARRAY_BUFFER
	 * @param name GLSL attribute name
	 * @param data Data that makes up the attribute
	 * @param size Size of the vector being used, 2 = 2D (x, y), 3 = 3D (x, y, z), 4 = 4D (x, y, z, w), etc...
	 * @param stride Offset between vertex attributes, 0 = tightly packed.
	 * @param pointer Offset of the first component
	 * @param normalized Should fixed-point values be normalized
	 */
	public Attribute(int index, boolean element_array, String name, int[] data, int size, int stride, int pointer, boolean normalized) {
		super();
		
		this.index = index;
		this.element_array = element_array;
		this.name = name;
		this.size = size;
		this.stride = stride;
		this.pointer = pointer;
		this.normalized = normalized;
		
		this.type = GL11.GL_INT;
		this.length = data.length;
		
		target = element_array ? GL15.GL_ELEMENT_ARRAY_BUFFER : GL15.GL_ARRAY_BUFFER;
		
		super.bind(target);
		GL15.glBufferData(target, data, GL15.GL_STATIC_DRAW);
		super.unbind(target);
	}
	
	/* Public methods */
	
	/**
	 * Enable this attribute
	 */
	public void enable() {
		super.bind(target);
		
		GL20.glEnableVertexAttribArray(index);
		GL20.glVertexAttribPointer(index, size, type, normalized, stride, pointer);
	}
}
