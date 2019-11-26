// 08/13/2018 - TvB
package com.cekeh.shaders.unlit;

import com.cekeh.opengl.ShaderProgram;
import com.cekeh.opengl.ShaderStage;
import com.cekeh.utility.Loader;
import com.cekeh.utility.Vector4f;

/** 
 * ColorShader.java
 * 08/11/2018
 * @author Thomas
 */
public class ColorShader extends ShaderProgram {

	private static final String vert_source = Loader.loadText("com/cekeh/shaders/unlit/src/color_shader.vs");
	private static final String frag_source = Loader.loadText("com/cekeh/shaders/unlit/src/color_shader.fs");
	
	private Vector4f main_color;
	
	/**
	 * Create a new ColorShader
	 * @param red How much red is in the color
	 * @param green  How much green is in the color
	 * @param blue  How much blue is in the color
	 * @param alpha  Opacity of the color
	 */
	public ColorShader(float red, float green, float blue, float alpha) {
		this(new Vector4f(red, green, blue, alpha));
	}
	
	/**
	 * Create a new ColorShader
	 * @param main_color The main color for the shader
	 */
	public ColorShader(Vector4f main_color) {
		super(
			new ShaderStage[] {
				new ShaderStage(vert_source, ShaderStage.VERTEX),
				new ShaderStage(frag_source, ShaderStage.FRAGMENT)
			}, null
		);
		
		setMainColor(main_color);
	}
	
	/* Getters / setters */
	
	/**
	 * Get the main color for the shader
	 * @return The main color for the shader
	 */
	public Vector4f getMainColor() {
		return main_color;
	}
	
	/**
	 * Set the main color for the shader
	 * @param main_color Main color for the shader
	 */
	public void setMainColor(Vector4f main_color) {
		this.main_color = main_color;
		
		super.setUniformfv("main_color", main_color.data());
	}
}
