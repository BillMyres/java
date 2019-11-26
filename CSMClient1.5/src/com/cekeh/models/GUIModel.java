package com.cekeh.models;

import com.cekeh.lwjgl.opengl.Texture;
import com.cekeh.lwjgl.opengl.VertexArrayObject;
import com.cekeh.lwjgl.opengl.VertexAttribute;
import com.cekeh.shaders.textured_shader.TexturedShader;

public class GUIModel extends VertexArrayObject {

	public GUIModel(float x, float y, float width, float height) throws Exception {
		super(
			new VertexAttribute[] {
				new VertexAttribute(0, 2, 0, 0, new float[] {
					x, y,
					x + width, y,
					x, y + height,
					
					x, y + height,
					x + width, y,
					x + width, y + height
				}),
				new VertexAttribute(1, 2, 0, 0, new float[] {
					0, 1,
					1, 1,
					0, 0,
					
					0, 0,
					1, 1,
					1, 0
				})
			},
			new int[] {
				0, 1, 2,
				3, 4, 5
			},
			new TexturedShader(new Texture[] { new Texture("images/TVB.png", 0) }),
			false,
			1
		);
	}
}
