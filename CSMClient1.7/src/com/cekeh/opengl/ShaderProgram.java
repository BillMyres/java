// 08/13/2018 - TvB
package com.cekeh.opengl;

import org.lwjgl.opengl.GL20;

/** 
 * ShaderProgram.java
 * 08/06/2018
 * @author Thomas
 */
public class ShaderProgram {

	private int handle;
	
	private Texture[] textures;
	
	/**
	 * Create a new ShaderProgram
	 * @param stages Stages of the shader program, (ie. vertex, geometry, fragment, etc...)
	 * @param textures Textures to add to the shader program
	 */
	public ShaderProgram(ShaderStage[] stages, Texture[] textures) {
		this.textures = textures;
		
		handle = GL20.glCreateProgram();
		
		bind();
		
		for(ShaderStage stage : stages) GL20.glAttachShader(handle, stage.handle);
	
		GL20.glLinkProgram(handle);
		GL20.glValidateProgram(handle);
		
		if(textures != null) {
			for(int i = 0; i < textures.length; i++) {
				setUniformi(textures[i].getName(), i);
				textures[i].bind(i);
			}
		}
		
		unbind();
	}
	
	/* Public methods */
	
	/**
	 * Bind this shader for use
	 */
	public void bind() {
		GL20.glUseProgram(handle);
		
		if(textures != null) {
			for(int i = 0; i < textures.length; i++) {
				int location = GL20.glGetUniformLocation(handle, textures[i].getName());
				GL20.glUniform1i(location, i);
				textures[i].bind(i);
			}
		}
	}
	
	/**
	 * Bind model attribute to work with this shader
	 * @param attribute Model attribute (vertices, indices, texcoords...)
	 */
	public void bindAttribute(Attribute attribute) {
		bind();
		
		GL20.glBindAttribLocation(handle, attribute.index, attribute.name);
		
		unbind();
	}
	
	/**
	 * Unbind this shader from being used
	 */
	public void unbind() {
		GL20.glUseProgram(0);
	}
	
	/**
	 * Terminate this shader, and clean up resources
	 */
	public void terminate() {
		if(textures != null) for(Texture texture : textures) texture.terminate();
		
		GL20.glDeleteProgram(handle);
	}
	
	/* Getters / setters */
	
	/**
	 * Change a texture in the shader
	 * @param index Index of the texture
	 * @param texture Texture to replace it with
	 */
//	public void setTexture(int index, Texture texture) {
//		if(textures != null) {
//			if(textures.length - 1 < index) {
//				if(texture != null) textures[index] = texture;
//			}
//		}
//	}
	
	/**
	 * Add texture to this shader
	 * @param texture Texture to add
	 * @return Index of the texture
	 */
//	public int addTexture(Texture texture) {
//		if(textures == null) textures = new Texture[0];
//		
//		Texture[] temp = new Texture[textures.length + 1];
//		
//		for(int i = 0; i < textures.length; i++) {
//			temp[i] = textures[i];
//		}
//		
//		temp[textures.length + 1] = texture;
//		
//		textures = temp;
//		
//		return textures.length;
//	}
	
	/**
	 * Set a uniform (int) variable in this shader
	 * @param name Name of the variable
	 */
	public void setUniformi(String name, int data) {
		setUniformiv(name, new int[] { data });
	}
	
	/**
	 * Set a uniform (int[]) variable in this shader
	 * @param name Name of the variable
	 * @param data Data to be placed in the variable
	 */
	public void setUniformiv(String name, int[] data) {
		bind();
		
		int location = GL20.glGetUniformLocation(handle, name);
		
		switch(data.length) {
			case 1:
				GL20.glUniform1i(location, data[0]);
				break;
			case 2:
				GL20.glUniform2iv(location, data);
				break;
			case 3:
				GL20.glUniform3iv(location, data);
				break;
			case 4:
				GL20.glUniform4iv(location, data);
				break;
		}
		
		unbind();
	}
	
	/**
	 * Set a uniform (float) variable in this shader
	 * @param name Name of the variable
	 * @param data Data to be placed in the variable
	 */
	public void setUniformf(String name, float data) {
		setUniformfv(name, new float[] { data });
	}
	
	/**
	 * Set a uniform (float[]) variable in this shader
	 * @param name Name of the variable
	 * @param data Data to be placed in the variable
	 */
	public void setUniformfv(String name, float[] data) {
		bind();
		
		int location = GL20.glGetUniformLocation(handle, name);
		
		switch(data.length) {
			case 1:
				GL20.glUniform1f(location, data[0]);
				break;
			case 2:
				GL20.glUniform2fv(location, data);
				break;
			case 3:
				GL20.glUniform3fv(location, data);
				break;
			case 4:
				GL20.glUniform4fv(location, data);
				break;
		}
		
		unbind();
	}
}
