// Last edit: 05/31/2018 - TvB
package com.cekeh.client;

import com.cekeh.games.TestGame;
import com.cekeh.lwjgl.glfw.Window;
import com.cekeh.lwjgl.opengl.Game;

/**
 * Cekeh's Client
 * Created 05/19/2018
 * @author Thomas vanBommel (TvB)
 */
public class Client extends Window{

	public static Client window;
	
	public static Game game;
	
	public static void main(String[] args) throws Exception {
		window = new Client();
		game = new TestGame();
		
		window.start();
	}
	
	/**
	 * Create a new Client object
	 * @throws Exception Unable to initialize GLFW
	 */
	private Client() throws Exception {
		super(500, 500, "CSMClient 1.5");
	}
	
	private Client start() {
		while(!super.shouldClose()) {
			super.clearBuffer();
			
			game.update();
			game.render();
			
			super.swapBuffers();
			super.pollEvents();
		}
		
		game.cleanUp();
		super.terminate();
		
		return this;
	}

	protected void keyEvent(int key, int action, int mods) {
		game.keyEvent(key, action, mods);
	}
}
