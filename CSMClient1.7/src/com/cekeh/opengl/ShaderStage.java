// 08/06/2018 - TvB
package com.cekeh.opengl;

import org.lwjgl.opengl.GL20;

/** 
 * ShaderStage.java
 * 08/06/2018
 * @author Thomas
 */
public class ShaderStage {
	
	public static final int VERTEX = GL20.GL_VERTEX_SHADER;
	public static final int FRAGMENT = GL20.GL_FRAGMENT_SHADER;
	
	public final int handle;
	
	/**
	 * Create a new ShaderStage
	 * @param source Source of the shader
	 * @param type One of OpenGL's shader types (VERTEX, FRAGMENT, GEOMETRY, etc...)
	 */
	public ShaderStage(String source, int type) {
		handle = GL20.glCreateShader(type);
		
		GL20.glShaderSource(handle, source);
		GL20.glCompileShader(handle);
		
		if(GL20.glGetShaderi(handle, GL20.GL_COMPILE_STATUS) != 1) {
			System.out.println(
				"ShaderStage compilation error: \n" + 
				GL20.glGetShaderInfoLog(handle)
			);
		}
	}
}
