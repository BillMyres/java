// Last edit: 05/31/2018 - TvB
package com.cekeh.lwjgl.opengl;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import com.cekeh.client.Client;
import com.cekeh.scenes.TestScene;
import com.cekeh.utility.CameraController;
import com.cekeh.utility.Matrix;

/**
 * Cekeh's Game object class
 * Created 05/19/2018
 * @author Thomas vanBommel (TvB)
 */
public abstract class Game {

	private Scene scene;

	public final CameraController camera_controller = new CameraController(new Matrix(true));
	
	/**
	 * Create a new game class
	 * @param width	The width of the window
	 * @param height The height of the window
	 * @param title The title of the window
	 * @throws Exception Unable to initialize GLFW
	 */
	protected Game(int width, int height, String title) throws Exception {		
		GL.createCapabilities();
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		//GL11.glDepthFunc(GL11.GL_NEVER);
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		//GL11.glEnable(GL11.GL_SCISSOR_TEST);
		//GL11.glScissor(0, 0, Client.window.getWidth(), Client.window.getHeight());
		
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		
		scene = new TestScene();
		
		if(camera_controller != null) {
			Client.window.setKeyCallback(camera_controller);
		}
	}
	
	public void keyEvent(int key, int action, int mods) {
		scene.keyEvent(key, action, mods);
	}
	
	/** Update the game */
	public void update() {
		scene.update();
		
		camera_controller.update();
	}
	
	/** Render the game */
	public void render() {
		scene.render();
	}
	
	/** Clean up memory resources */
	public void cleanUp() {
		scene.cleanUp();
	}
	
	/** Clear the render buffer (screen) */
	public void clearBuffer() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	//GET / SET
	
	
}
