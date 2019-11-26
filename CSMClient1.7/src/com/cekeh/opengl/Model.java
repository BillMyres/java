// 08/13/2018 - TvB
package com.cekeh.opengl;

import org.lwjgl.opengl.GL11;

/** 
 * Model.java
 * 08/06/2018
 * @author Thomas
 */
public class Model extends VertexArrayObject{
	
	public final Attribute[] attributes;
	
	public int index_count = 0;
	
	public ShaderProgram shader_program;	
	
	public Model(Attribute[] attributes, ShaderProgram shader_program) {
		super();		
		
		this.attributes = attributes;
		this.shader_program = shader_program;
		
		super.bind();
		
		for(Attribute attribute : attributes) {
			shader_program.bindAttribute(attribute);
			
			attribute.enable();
			
			if(attribute.element_array) index_count = attribute.length;
		}
		
		super.unbind();
	}
	
	/* Public methods */
	
	/**
	 * Update this model
	 */
	public void update() {
		
	}
	
	/**
	 * Render this model
	 */
	public void render() {
		shader_program.bind();
		
		super.bind();
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, index_count, GL11.GL_UNSIGNED_INT, 0);
		
		super.unbind();
		
		shader_program.unbind();
	}
	
	/**
	 * Terminate this model, and clean up resources
	 */
	public void terminate() {
		shader_program.terminate();
		
		for(Attribute attribute : attributes) attribute.terminate();
		
		super.terminate();
	}
}
