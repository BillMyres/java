// Last edit: 06/11/2018 - TvB
package com.cekeh.opengl;

import org.lwjgl.opengl.GL20;

import com.cekeh.utility.Loader;

/**
 * Cekeh's Shader object
 * Created 06/06/2018
 * @author Thomas vanBommel (TvB)
 */
public class Shader {

	public static final int VERTEX_SHADER = 35633;
	public static final int FRAGMENT_SHADER = 35632;
	//public static final int GEOMETRY_SHADER = 356xx;
	
	public final int handle;
	
	/**
	 * Create a new shader object
	 * @param path Path to the shader source file
	 * @param type Type of shader to create
	 */
	public Shader(String path, int type) {
		handle = GL20.glCreateShader(type);
		
		GL20.glShaderSource(handle, Loader.loadString(path));
		GL20.glCompileShader(handle);
		
		if(GL20.glGetShaderi(handle, GL20.GL_COMPILE_STATUS) != 1) {
			System.out.println("Compiling error: [ " + path + " ]\n" + GL20.glGetShaderInfoLog(handle));
		}
	}
}
