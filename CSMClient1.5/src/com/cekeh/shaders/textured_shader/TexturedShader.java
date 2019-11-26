package com.cekeh.shaders.textured_shader;

import com.cekeh.lwjgl.opengl.ShaderProgram;
import com.cekeh.lwjgl.opengl.Texture;

public class TexturedShader extends ShaderProgram {
	
	private Texture[] textures = null;
	
	public TexturedShader(Texture[] textures) throws Exception {
		super("com/cekeh/shaders/textured_shader/TexturedShader.vs", "com/cekeh/shaders/textured_shader/TexturedShader.fs");
			
		this.textures = textures;
	}
	
	@Override
	public void bind() {
		super.bind();
		
		if(textures != null) {
			for(Texture texture : textures) {
				texture.bind();
			}
		}
	}
}
