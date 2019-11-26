// Last edit: 06/14/2018 - TvB
package com.cekeh.glfw;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowPosCallbackI;

import com.cekeh.game.Game;
import com.cekeh.utility.Vector2f;
import com.cekeh.utility.Vector3f;

/**
 * Cekeh's Client class (Entry Point)
 * Created 06/04/2018
 * @author Thomas vanBommel (TvB)
 */
public class Client {
	
	private static long handle;
	
	private static int width  = 1000;
	private static int height = 1000;
	
	private static Vector2f position = new Vector2f(0, 0);
	
	private static Game game;
	
	/**
	 * Entry point of the program
	 * @param args Starting arguments
	 * @throws Exception Unable to initialize GLFW
	 */
	public static void main(String[] args) throws Exception {
		if(!GLFW.glfwInit()) throw new Exception("Unable to initialize GLFW.");
	
		handle = GLFW.glfwCreateWindow(width, height, "CSMClient 1.6", 0, 0);
		
		GLFWVidMode primary_monitor = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());		
		GLFW.glfwSetWindowPos(handle, (primary_monitor.width() - width) / 2, (primary_monitor.height() - height) / 2);
		GLFW.glfwSetCursorPos(handle, width / 2, height / 2);
		
		GLFW.glfwMakeContextCurrent(handle);
		
		game = new Game();
		
		GLFW.glfwSetWindowSizeCallback(handle, new WindowSizeCallback());
		GLFW.glfwSetWindowPosCallback(handle, position_callback);
		GLFW.glfwSetCursorPosCallback(handle, cursor_callback);
		GLFW.glfwSetKeyCallback(handle, game.camera.key_callback);
		
		Vector3f a = new Vector3f(2, 3, 4);
		Vector3f b = new Vector3f(5, 6, 7);
		Vector3f c = Vector3f.cross(a, b);
		
		System.out.println(c.x + ", " + c.y + ", " + c.z);
		
		start();
	}
	
	private static GLFWWindowPosCallbackI position_callback = new GLFWWindowPosCallbackI() {
		@Override
		public void invoke(long window, int xpos, int ypos) {
			position.x = xpos;
			position.y = ypos;
		}
	};
	
	public static final Vector2f mouse_position = new Vector2f(width / 2, height / 2);
	
	private static GLFWCursorPosCallbackI cursor_callback = new GLFWCursorPosCallbackI() {
		@Override
		public void invoke(long window, double xpos, double ypos) {
			mouse_position.x = (float) xpos;
			mouse_position.y = (float) ypos;
		}
	};
	
	/** Start the program */
	private static void start() {		
		while(!GLFW.glfwWindowShouldClose(handle)) {
			
			centerCursor();
			//GLFW.glfwSetCursorPos(handle, width / 2, height / 2);
			
			game.update();
			game.render();
			
			
			
			GLFW.glfwSwapBuffers(handle);
			GLFW.glfwPollEvents();
		}
		
		stop();
	}
	
	/** Stop the program */
	public static void stop() {
		game.stop();
		
		GLFW.glfwSetWindowShouldClose(handle, true);
		
		GLFW.glfwDestroyWindow(handle);
		GLFW.glfwTerminate();
	}
	
	/**
	 * Get the width of the client
	 * @return The width of the client
	 */
	public static int getWidth() {
		return width;
	}
	
	/** Set the width of the client */
	public static void setWidth(int pixels) {
		width = pixels > 0 ? pixels : width;
	}
	
	/**
	 * Get the height of the client
	 * @return The height of the client
	 */
	public static int getHeight() {
		return height;
	}
	
	/** Set the height of the client */
	public static void setHeight(int pixels) {
		height = pixels > 0 ? pixels : height;
	}
	
	/**
	 * Get the game of the client
	 * @return Game object the client is running
	 */
	public static Game getGame() {
		return game;
	}
	
	/**
	 * Set the cursor position
	 * @param x X coord
	 * @param y Y coord
	 */
	public static void setCursorPosition(int x, int y) {
		GLFW.glfwSetCursorPos(handle, x, y);
	}
	
	/** Center the cursor on the client */
	public static void centerCursor() {
		GLFW.glfwSetCursorPos(handle, width / 2, height / 2);
	}
}
