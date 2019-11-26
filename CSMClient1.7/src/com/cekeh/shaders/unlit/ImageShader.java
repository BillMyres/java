// 08/13/2018 - TvB
package com.cekeh.shaders.unlit;

import java.awt.image.BufferedImage;

import com.cekeh.opengl.ShaderProgram;
import com.cekeh.opengl.ShaderStage;
import com.cekeh.opengl.Texture;
import com.cekeh.utility.Loader;

/** 
 * ImageShader.java
 * 08/18/2018
 * @author Thomas
 */
public class ImageShader extends ShaderProgram {

	private static final String vert_source = Loader.loadText("com/cekeh/shaders/unlit/src/image_shader.vs");
	private static final String frag_source = Loader.loadText("com/cekeh/shaders/unlit/src/image_shader.fs");
	
	public ImageShader(BufferedImage image) {
		super(
			new ShaderStage[] {
				new ShaderStage(vert_source, ShaderStage.VERTEX),
				new ShaderStage(frag_source, ShaderStage.FRAGMENT)
			}, new Texture[] {
				new Texture("texture0", image)
			}
		);
		
//		super.bind();
//		
//		super.setUniformi("texture0", 0);
//		new Texture("texture0", image).bind();
//		
//		super.unbind();
	}
}
