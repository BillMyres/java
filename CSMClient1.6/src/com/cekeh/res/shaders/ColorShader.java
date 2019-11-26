// Last edit: 07/29/2018 - TvB
package com.cekeh.res.shaders;

import com.cekeh.opengl.Attribute;
import com.cekeh.opengl.Shader;
import com.cekeh.opengl.ShaderProgram;
import com.cekeh.utility.Vector4f;

/**
 * Cekeh's Color Shader
 * Created 07/29/2018
 * @author Thomas vanBommel (TvB)
 */
public class ColorShader extends ShaderProgram{

	public ColorShader(Vector4f color, Attribute[] attributes) {
		super(
			new Shader[] {
				new Shader("com/cekeh/res/shaders/color.vs", Shader.VERTEX_SHADER),
				new Shader("com/cekeh/res/shaders/color.fs", Shader.FRAGMENT_SHADER)
			}, attributes, null
		);
		
		this.setUniformfv("main_color", new float[] { color.x, color.y, color.z, color.w });
	}

}
