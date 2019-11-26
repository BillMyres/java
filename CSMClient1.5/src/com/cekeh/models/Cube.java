// Last edit: 05/25/2018 - TvB
package com.cekeh.models;

import com.cekeh.lwjgl.opengl.ShaderProgram;
import com.cekeh.lwjgl.opengl.Texture;
import com.cekeh.lwjgl.opengl.VertexArrayObject;
import com.cekeh.lwjgl.opengl.VertexAttribute;
import com.cekeh.shaders.color_shader.ColorShader;
import com.cekeh.shaders.textured_shader.TexturedShader;

/**
 * Cekeh's Cube Model
 * Created 05/25/2018
 * @author Thomas vanBommel (TvB)
 */
public class Cube extends VertexArrayObject {

	private static final float[] vertex_position = {
		// Front
		-0.5f,  0.5f, -0.5f,	// Top-Left
		 0.5f,  0.5f, -0.5f,	// Top-Right
		 0.5f, -0.5f, -0.5f,	// Bottom-Right
		-0.5f, -0.5f, -0.5f,	// Bottom-Left
		
		// Back
		 0.5f,  0.5f,  0.5f,	// Top-Left
		-0.5f,  0.5f,  0.5f,	// Top-Right
		-0.5f, -0.5f,  0.5f,	// Bottom-Right
		 0.5f, -0.5f,  0.5f,	// Bottom-Left
		 
		// Top
		-0.5f,  0.5f,  0.5f,	// Top-Left
		 0.5f,  0.5f,  0.5f,	// Top-Right
		 0.5f,  0.5f, -0.5f,	// Bottom-Right
		-0.5f,  0.5f, -0.5f,	// Bottom-Left
		
		// Bottom
		-0.5f, -0.5f, -0.5f,	// Top-Left
		 0.5f, -0.5f, -0.5f,	// Top-Right
		 0.5f, -0.5f,  0.5f,	// Bottom-Right
		-0.5f, -0.5f,  0.5f,	// Bottom-Left
		
		// Left
		-0.5f,  0.5f,  0.5f,	// Top-Left
		-0.5f,  0.5f, -0.5f,	// Top-Right
		-0.5f, -0.5f, -0.5f,	// Bottom-Right
		-0.5f, -0.5f,  0.5f,	// Bottom-Left
		
		// Right
		 0.5f,  0.5f, -0.5f,	// Top-Left
		 0.5f,  0.5f,  0.5f,	// Top-Right
		 0.5f, -0.5f,  0.5f,	// Bottom-Right
		 0.5f, -0.5f, -0.5f,	// Bottom-Left
	};
	
	private static final float[] vertex_texcoord = {
		// Front
		0, 0,	// Top-Left
		1, 0,	// Top-Right
		1, 1,	// Bottom-Right
		0, 1,	// Bottom-Left
		
		// Back
		0, 0,	// Top-Left
		1, 0,	// Top-Right
		1, 1,	// Bottom-Right
		0, 1,	// Bottom-Left
		
		// Top
		0, 0,	// Top-Left
		1, 0,	// Top-Right
		1, 1,	// Bottom-Right
		0, 1,	// Bottom-Left
		
		// Bottom
		0, 0,	// Top-Left
		1, 0,	// Top-Right
		1, 1,	// Bottom-Right
		0, 1,	// Bottom-Left
		
		// Left
		0, 0,	// Top-Left
		1, 0,	// Top-Right
		1, 1,	// Bottom-Right
		0, 1,	// Bottom-Left
		
		// Right
		0, 0,	// Top-Left
		1, 0,	// Top-Right
		1, 1,	// Bottom-Right
		0, 1	// Bottom-Left
	};
	
	private static final float[] vertex_color = {
		// Front
		1, 0, 0, 1,		// Top-Left
		1, 0, 0, 1,		// Top-Right
		1, 0, 0, 1, 	// Bottom-Right
		1, 0, 0, 1,		// Bottom-Left
		
		// Back
		0, 1, 0, 1,		// Top-Left
		0, 1, 0, 1,		// Top-Right
		0, 1, 0, 1, 	// Bottom-Right
		0, 1, 0, 1,		// Bottom-Left
		
		// Top
		0, 0, 1, 1,		// Top-Left
		0, 0, 1, 1,		// Top-Right
		0, 0, 1, 1, 	// Bottom-Right
		0, 0, 1, 1,		// Bottom-Left
		
		// Bottom
		1, 1, 0, 1,		// Top-Left
		1, 1, 0, 1,		// Top-Right
		1, 1, 0, 1, 	// Bottom-Right
		1, 1, 0, 1,		// Bottom-Left
		
		// Left
		1, 0, 1, 1,		// Top-Left
		1, 0, 1, 1,		// Top-Right
		1, 0, 1, 1, 	// Bottom-Right
		1, 0, 1, 1,		// Bottom-Left
		
		// Right
		1, 1, 1, 1,		// Top-Left
		1, 1, 1, 1,		// Top-Right
		1, 1, 1, 1, 	// Bottom-Right
		1, 1, 1, 1,		// Bottom-Left
	};
	
	private static final int[] indices = {
		// Front
		0, 1, 2,
		2, 3, 0,
		
		// Back
		4, 5, 6,
		6, 7, 4,
		
		// Top
		8,  9, 10,
		10, 11, 8,
		
		// Bottom
		12, 13, 14,
		14, 15, 12,
		
		// Top
		16, 17, 18,
		18, 19, 16,
		
		// Bottom
		20, 21, 22,
		22, 23, 20
	};
	
	private static final VertexAttribute[] attributes = {
		// Position
		new VertexAttribute(0, 3, 0, 0, vertex_position),
			
		// Texcoord
		new VertexAttribute(1, 2, 0, 0, vertex_texcoord),
		
		// Color
		new VertexAttribute(2, 4, 0, 0, vertex_color)
	};
	
	public Cube() throws Exception {
		super(attributes, indices, new TexturedShader(new Texture[] { new Texture("images/test0.png", 0) }), true, 1);
	}
}
