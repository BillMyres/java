// Last edit: 05/20/2018 - TvB
package com.cekeh.shaders.color_shader;

import com.cekeh.lwjgl.opengl.ShaderProgram;

/**
 * Cekeh's Color Shader
 * Created 05/20/2018
 * @author Thomas vanBommel (TvB)
 */
public class ColorShader extends ShaderProgram {

	/**
	 * Create a new Color Shader
	 * @param color float[4] specified color for the shader
	 * @throws Exception ShaderProgram error
	 */
	public ColorShader(float[] color) throws Exception {
		super("com/cekeh/shaders/color_shader/ColorShader.vs", "com/cekeh/shaders/color_shader/ColorShader.fs");
	
		setColor(color);
	}
	
	/** Set the color of the shader */
	public void setColor(float[] color) {
		super.setUniformfv("color", color);
	}
}
