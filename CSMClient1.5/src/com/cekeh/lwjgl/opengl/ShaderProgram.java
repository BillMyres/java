// Last edit: 05/25/2018 - TvB
package com.cekeh.lwjgl.opengl;

import org.lwjgl.opengl.GL20;

import com.cekeh.utility.ResourceLoader;

/**
 * Cekeh's Shader Program object class
 * Created 05/20/2018
 * @author Thomas vanBommel (TvB)
 */
public abstract class ShaderProgram {

	private final int handle;
	
	/**
	 * Create a new shader program
	 * @param vertex_path The file path to the vertex shader
	 * @param fragment_path The file path to the fragment shader
	 * @throws Exception I/O Exception (incorrect file path)
	 * @throws Exception Shader compilation error
	 */
	protected ShaderProgram(String vertex_path, String fragment_path) throws Exception {
		handle = GL20.glCreateProgram();
		
		bind();
		
		int vertex_handle = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
		int fragment_handle = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
	
		GL20.glShaderSource(vertex_handle, ResourceLoader.loadPlainText(vertex_path));
		GL20.glShaderSource(fragment_handle, ResourceLoader.loadPlainText(fragment_path));
		
		GL20.glCompileShader(vertex_handle);
		GL20.glCompileShader(fragment_handle);
		
		if(GL20.glGetShaderi(vertex_handle, GL20.GL_COMPILE_STATUS) != 1) {
			throw new Exception("Compiling error (vertex shader):\n" + GL20.glGetShaderInfoLog(vertex_handle));
		}
		
		if(GL20.glGetShaderi(fragment_handle, GL20.GL_COMPILE_STATUS) != 1) {
			throw new Exception("Compiling error (fragment shader):\n" + GL20.glGetShaderInfoLog(fragment_handle));
		}
		
		GL20.glAttachShader(handle, vertex_handle);
		GL20.glAttachShader(handle, fragment_handle);
		
		GL20.glBindAttribLocation(handle, 0, "position");
		
		GL20.glLinkProgram(handle);
		GL20.glValidateProgram(handle);
		
		unbind();
	}
	
	/**
	 * Set uniform variable in the shader program
	 * @param name The exact name of the variable in the shader
	 * @param data The data to be placed in that variable
	 */
	public void setUniformf(String name, float data) {
		bind();
		
		int location = GL20.glGetUniformLocation(handle, name);
		
		GL20.glUniform1f(location, data);
		
		unbind();
	}
	
	/**
	 * Set uniform variable in the shader program
	 * @param name The exact name of the variable in the shader
	 * @param data The data to be placed in that variable
	 */
	public void setUniformfv(String name, float[] data) {
		bind();
		
		int location = GL20.glGetUniformLocation(handle, name);
				
		switch(data.length) {
			case 1:
				GL20.glUniform1fv(location, data);
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
	
	/**
	 * Set uniform matrix variable in the shader program
	 * @param name The exact name of the variable in the shader
	 * @param data The data to be placed in that variable
	 */
	public void setUniformMatrixfv(String name, float[] data) {
		bind();
		
		int location = GL20.glGetUniformLocation(handle, name);
		
		float root_size = (float) Math.sqrt(data.length);
		
		int size = root_size % 1 == 0 ? (int) root_size : -1;
		
		switch(size) {
			case 2:
				GL20.glUniformMatrix2fv(location, false, data);
				break;
			
			case 3:
				GL20.glUniformMatrix3fv(location, false, data);
				break;
				
			case 4:
				GL20.glUniformMatrix4fv(location, false, data);
				break;
		}
		
		unbind();
	}
	
	/** Bind this shader program for use */
	public void bind() {
		GL20.glUseProgram(handle);
	}
	
	/** Unbind this shader program */
	public void unbind() {
		GL20.glUseProgram(0);
	}
	
	/** Clean up memory resources */
	public void cleanUp() {
		GL20.glDeleteProgram(handle);
	}
	
	//GET / SET
}
