// Last edit: 08/03/2018 - TvB
package com.cekeh.game;

import org.lwjgl.opengl.GL11;

import com.cekeh.opengl.Attribute;
import com.cekeh.opengl.FBO;
import com.cekeh.opengl.Model;
import com.cekeh.opengl.Shader;
import com.cekeh.opengl.ShaderProgram;
import com.cekeh.opengl.Texture;
import com.cekeh.utility.Loader;
import com.cekeh.utility.RawModel;
import com.cekeh.utility.Transform;

/**
 * Cekeh's Scene object
 * Created 07/01/2018
 * @author Thomas vanBommel (TvB)
 */
public abstract class Scene {
	
	FBO fbo;
	
	Model gui;
	
	float[] vertices = {
		0, 0,
		0, 1,
		1, 1,
		1, 1,
		1, 0, 
		0, 0
	};
	
	float[] texcoords = {
		0.0f, 1.0f,
		0.0f, 0.0f,
		1.0f, 0.0f,
		1.0f, 0.0f,
		1.0f, 1.0f, 
		0.0f, 1.0f
	};
	
	public ShaderProgram shader;
	
	/**
	 * Create a new scene object
	 */
	public Scene() {
		fbo = new FBO();
		
		RawModel raw = new RawModel(
			new Attribute[] { 
				new Attribute("position", 0, 2, 0, 0, vertices),
				new Attribute("texcoord", 1, 2, 0, 0, texcoords)
			},
			new int[] { 0, 2, 1, 3, 5, 4 }
		);
		
		shader = new ShaderProgram(
			new Shader[] {
				new Shader("com/cekeh/res/shaders/gui.vs", Shader.VERTEX_SHADER),
				new Shader("com/cekeh/res/shaders/gui.fs", Shader.FRAGMENT_SHADER)
			},
			raw.attributes,
			new Texture[] {
				new Texture("texture0", fbo.texture_handle, 512, 512, GL11.GL_RGB),
				new Texture("texture1", Loader.loadImage("com/cekeh/res/textures/heightmap0.png"))
			}
		);
		
		gui = new Model(
			raw,
			shader,
			new Transform()
		);
		
		//shader.setTextures(new Texture[] {
//				new Texture("texture0", fbo.texture_handle, 512, 512, GL11.GL_RGB)
//			});
	}
	
	protected abstract Model[] getModels();
	public abstract Model getLight();
	
	/** Update the scene */
	public void update() {
		for(Model model: getModels()) {
			model.update();
		}
		
		gui.update();
	}
	
	/** Render the scene */
	public void render() {
		
		fbo.bind();

		for(Model model: getModels()) {
			model.render();
		}
		
		fbo.unbind();
		
		for(Model model: getModels()) {
			model.render();
		}
		
//		shader.setTextures(new Texture[] {
//			new Texture("texture0", fbo.texture_handle, 512, 512, GL11.GL_RGBA)
//		});
		
		gui.render();
	}
	
	/** Clean up memory resources */
	public void cleanUp() {
		for(Model model: getModels()) {
			model.cleanUp();
		}
		
		gui.cleanUp();
	}
}
