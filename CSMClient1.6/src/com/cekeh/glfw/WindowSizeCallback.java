// Last edit: 06/13/2018 - TvB
package com.cekeh.glfw;

import org.lwjgl.glfw.GLFWWindowSizeCallbackI;
import org.lwjgl.opengl.GL11;

/**
 * Cekeh's Window Size Callback object
 * Created 06/13/2018
 * @author Thomas vanBommel (TvB)
 */
public class WindowSizeCallback implements GLFWWindowSizeCallbackI {

	/** Create a new window size callback object */
	public WindowSizeCallback() {}
	
	@Override
	public void invoke(long window, int width, int height) {
		Client.setWidth(width);
		Client.setHeight(height);
		
		GL11.glViewport(0, 0, width, height);
	}
}
