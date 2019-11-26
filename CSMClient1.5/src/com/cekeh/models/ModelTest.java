package com.cekeh.models;

import com.cekeh.lwjgl.opengl.Texture;
import com.cekeh.lwjgl.opengl.VertexArrayObject;
import com.cekeh.lwjgl.opengl.VertexAttribute;
import com.cekeh.shaders.textured_shader.TexturedShader;

public class ModelTest extends VertexArrayObject {

	public ModelTest(VertexAttribute[] attributes, int[] indices) throws Exception {
		super(attributes, indices, new TexturedShader(new Texture[] { new Texture("images/SlotMachine_UV.png", 0) }), true, 20000);
	}
}
