// 08/18/2018 - TvB
package com.cekeh.main;

import com.cekeh.models.Box;
import com.cekeh.opengl.FrameBuffer;
import com.cekeh.opengl.Model;
import com.cekeh.shaders.unlit.ColorShader;
import com.cekeh.shaders.unlit.ImageShader;
import com.cekeh.utility.Loader;
import com.cekeh.utility.Vector4f;

/** 
 * Game.java
 * 08/04/2018
 * @author Thomas
 */
public class Game {

	Model[] models;
	
	FrameBuffer buffer;
	
	public Game() {		
		models = new Model[] {
			new Box(new Vector4f(-1, 1, 1, 1), new ColorShader(1, 0, 0.5f, 1)),
			new Box(new Vector4f(0, 0, 1, 1), new ImageShader(Loader.loadImage("com/cekeh/textures/test1.png")))
		};
		
		buffer = new FrameBuffer();
	}
	
	/* Public methods */
	
	/**
	 * Update the game
	 */
	public void update() {
		for(Model model : models) model.update();
	}
	
	/**
	 * Render the game
	 */
	public void render() {
		for(Model model : models) model.render();
		
		//buffer.bind();
		
		//models[0].render();
		
		//buffer.unbind();
		
		//models[1].render();
	}
	
	/**
	 * Terminate the game, and clean up any resources
	 */
	public void terminate() {
		for(Model model : models) model.terminate();
	}
}
