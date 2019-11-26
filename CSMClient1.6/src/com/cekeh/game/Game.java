// Last edit: 07/28/2018 - TvB
package com.cekeh.game;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

/**
 * Cekeh's Game object
 * Created 06/04/2018
 * @author Thomas vanBommel (TvB)
 */
public class Game {
	
	public Scene scene;
	
	public Camera camera;
	
	/** Create a new game object */
	public Game() {
		GL.createCapabilities();
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_BLEND);
		
		GL11.glCullFace(GL11.GL_BACK);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		camera = new Camera();
		scene = new TestScene();
		
		camera.transform.position.y = -1;
	}
	
	/** Update the game */
	public void update() {
		scene.update();
		
		camera.update();
	}
	
	/** Render the game */
	public void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		scene.render();
	}
	
	/** Stop the game */
	public void stop() {
		scene.cleanUp();
		
		GL.destroy();
	}
}
