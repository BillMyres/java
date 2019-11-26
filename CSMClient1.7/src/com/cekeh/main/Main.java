// 08/04/2018 - TvB
package com.cekeh.main;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

/** 
 * Main.java
 * 08/04/2018
 * @author Thomas
 */
public class Main {

	private static long window;
	private static Game game;
	
	public static void main(String[] args) throws Exception {
		initialize();
		
		while(!GLFW.glfwWindowShouldClose(window)) {			
			update();
			render();
		}
		
		terminate();
	}
	
	/* Private methods */
	
	/**
	 * Initialize everything needed for the program (GLFW, OpenGL, OpenAL, Game, etc...)
	 * @throws Exception Unable to initialize
	 */
	private static void initialize() throws Exception {
		// GLFW
		window = createWindow(512, 512, "CSMClient v1.7");
		
		// OpenGL
		GL.createCapabilities();
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_BLEND);
		
		GL11.glCullFace(GL11.GL_BACK);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		// Game
		if(game == null) game = new Game();
	}
	
	/**
	 * Update the program
	 */
	private static void update() {
		GLFW.glfwPollEvents();
		
		game.update();
	}
	
	/**
	 * Render the program
	 */
	private static void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		game.render();
		
		GLFW.glfwSwapBuffers(window);
	}
	
	/* Public methods */
	
	/**
	 * Initializes GLFW and creates a new window 
	 * @param width Width of the window
	 * @param height Height of the window
	 * @param title Title for the window
	 * @return The handle for the window, 0 if unable to initialize GLFW
	 * @throws Exception Unable to initialize GLFW
	 */
	public static long createWindow(int width, int height, String title) throws Exception {
		if(!GLFW.glfwInit()) throw new Exception("Unable to initialize GLFW");
		
		long handle = GLFW.glfwCreateWindow(width, height, title, 0, 0);
		
		GLFWVidMode monitor = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		GLFW.glfwSetWindowPos(handle, (monitor.width() - width) / 2, (monitor.height() - height) / 2);
		
		GLFW.glfwMakeContextCurrent(handle);
		
		return handle;
	}
	
	/**
	 * Terminate the program and clean up resources
	 */
	public static void terminate() {
		game.terminate();
		
		GLFW.glfwSetWindowShouldClose(window, true);
		GLFW.glfwDestroyWindow(window);
		GLFW.glfwTerminate();
	}
	
	/* Getters / Setters */
	
	/**
	 * Get the current window handle
	 * @return Window handle
	 */
	public long getWindow() {
		return window;
	}
}
