// Last edit: 05/26/2018 - TvB
package com.cekeh.lwjgl.glfw;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWWindowSizeCallbackI;
import org.lwjgl.opengl.GL11;

/**
 * Cekeh's Window object
 * Created 05/19/2018
 * @author Thomas vanBommel (TvB)
 */
public abstract class Window {
	
	private int width = 500;
	private int height = 500;
	
	private final long handle;
	
	GLFWWindowSizeCallbackI size_callback = new GLFWWindowSizeCallbackI() {
		@Override
		public void invoke(long window, int new_width, int new_height) {
			width = new_width;
			height = new_height;
			
			GL11.glViewport(0, 0, width, height);
		}
	};
	
	GLFWKeyCallbackI key_callback = new GLFWKeyCallbackI() {
		@Override
		public void invoke(long window, int key, int scancode, int action, int mods) {
			keyEvent(key, action, mods);
		}
	};
	
	protected abstract void keyEvent(int key, int action, int mods);
	
	/**
	 * Create a new window object
	 * @param width The width of the window
	 * @param height The height of the window
	 * @param title The title of the window
	 * @throws Exception Unable to initialize GLFW
	 */
	public Window(int width, int height, String title) throws Exception {
		this.width = width;
		this.height = height;
		
		handle = createWindow(width, height, title);
		
		System.out.println("Handle: " + handle);
		
		GLFW.glfwSetWindowSizeCallback(handle, size_callback);
		GLFW.glfwSetKeyCallback(handle, key_callback);
		
		GLFW.glfwMakeContextCurrent(handle);
	}
	
	/**
	 * Initialize GLFW and create a new window
	 * @param width The width of the window
	 * @param height The height of the window
	 * @param title The title of the window
	 * @return The handle / reference ID for the GLFW window
	 * @throws Exception Unable to initialize GLFW
	 */
	public static long createWindow(int width, int height, String title) throws Exception {
		GLFWErrorCallback.createPrint(System.err).set();
		
		if(!GLFW.glfwInit()) { throw new Exception("Unable to initialize GLFW"); }
				
		return GLFW.glfwCreateWindow(width, height, title, 0, 0);
	}
	
	/**
	 * Return if the window should close
	 * @return True if the window should close, false if not
	 */
	public boolean shouldClose() {
		return GLFW.glfwWindowShouldClose(handle);
	}

	/** Poll window events */
	public void pollEvents() {
		GLFW.glfwPollEvents();
	}
	
	/** Swap the window buffers */
	public void swapBuffers() {
		GLFW.glfwSwapBuffers(handle);
	}
	
	/** Clear the render buffer (screen) */
	public void clearBuffer() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	/** Set the window's key callback object */
	public void setKeyCallback(GLFWKeyCallbackI callback) {
		GLFW.glfwSetKeyCallback(handle, callback);
	}
	
	/** Terminate the window */
	public void terminate() {
		if(GLFW.glfwInit()) {
			GLFW.glfwDestroyWindow(handle);
			GLFW.glfwTerminate();
		}
	}
	
	//GET / SET
	
	/**
	 * Get the width of the window
	 * @return The width of the window
	 */
	public int getWidth() { return width; }
	
	/**
	 * Get the height of the window
	 * @return The height of the window
	 */
	public int getHeight() { return height; }
}
