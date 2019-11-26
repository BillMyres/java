// Last edit: 08/03/2018 - TvB
package com.cekeh.opengl;

import org.lwjgl.opengl.GL20;

import com.cekeh.utility.Matrix;

/**
 * Cekeh's ShaderProgram object
 * Created 06/11/2018
 * @author Thomas vanBommel (TvB)
 */
public class ShaderProgram {

	private final int handle;
	
	private Texture[] textures;
	
	public ShaderProgram(Shader[] shaders, Attribute[] attributes, Texture[] textures) {
		this.textures = textures;
		
		handle = GL20.glCreateProgram();
		
		GL20.glUseProgram(handle);
		
		for(Shader shader: shaders) {
			GL20.glAttachShader(handle, shader.handle);
		}
		
		for(Attribute attribute: attributes) {
			GL20.glBindAttribLocation(handle, attribute.index, attribute.name);
		}
		
		GL20.glLinkProgram(handle);
		GL20.glValidateProgram(handle);
		
		if(textures != null) {
			for(int i = 0; i < textures.length; i++) {
				setUniformi(textures[i].name, i);
				textures[i].bind(i);
			}
		}
		
		unbind();
	}
	
	/** Bind this shader */
	public void bind() {		
		GL20.glUseProgram(handle);
	}
	
	/** Unbind this shader */
	public void unbind() {		
		GL20.glUseProgram(0);
	}
	
	/**
	 * Set uniform (int)
	 * @param name Name of the uniform
	 * @param data Data to be placed
	 */
	public void setUniformi(String name, int data) {
		GL20.glUseProgram(handle);
		
		int location = GL20.glGetUniformLocation(handle, name);
		GL20.glUniform1i(location, data);
		
		unbind();
	}
	
	/**
	 * Set uniform (float)
	 * @param name Name of the uniform
	 * @param data Data to be placed
	 */
	public void setUniformf(String name, float data) {
		GL20.glUseProgram(handle);
		
		int location = GL20.glGetUniformLocation(handle, name);
		GL20.glUniform1f(location, data);
		
		unbind();
	}
	
	/**
	 * Set uniform (float)
	 * @param name Name of the uniform
	 * @param data Data to be placed
	 */
	public void setUniformfv(String name, float[] data) {
		GL20.glUseProgram(handle);
		
		int location = GL20.glGetUniformLocation(handle, name);
		
		switch(data.length) {
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
	
	/**
	 * Set uniform matrix (Matrix)
	 * @param name Name of the uniform
	 * @param data Data to be placed
	 */
	public void setUniformMatrix(String name, Matrix data) {
		GL20.glUseProgram(handle);
		
		int location = GL20.glGetUniformLocation(handle, name);
		GL20.glUniformMatrix4fv(location, false, data.data);
		
		unbind();
	}
	
	/**
	 * Set the textures for this shader
	 * @param tex Textures to load
	 */
	public void setTextures(Texture[] tex) {
		this.textures = tex;
		
		bind();
		
		if(textures != null) {
			for(int i = 0; i < textures.length; i++) {
				setUniformi(textures[i].name, i);
				textures[i].bind(i);
			}
		}
		
		unbind();
	}
	
	/** Clean up memory resources */
	public void cleanUp() {
		if(textures != null) {
			for(Texture texture: textures) {
				texture.cleanUp();
			}
		}
		
		GL20.glDeleteProgram(handle);
	}
}
