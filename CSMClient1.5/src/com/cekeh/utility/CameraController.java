// Last edit: 05/31/2018 - TvB
package com.cekeh.utility;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;

/**
 * Cekeh's Camera object
 * Created 05/31/2018
 * @author Thomas vanBommel (TvB)
 */
public class CameraController implements GLFWKeyCallbackI {

	private final Matrix camera;

	private float up_speed = 0;
	private float right_speed = 0;
	private float forward_speed = 0;
	
	public CameraController(Matrix camera) {
		this.camera = camera;
	}

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		switch(key) {
			case GLFW.GLFW_KEY_W:
				if(action != GLFW.GLFW_RELEASE) {
					forward_speed = 1;
				} else {
					forward_speed = 0;
				}
				break;
				
			case GLFW.GLFW_KEY_S:
				if(action != GLFW.GLFW_RELEASE) {
					forward_speed = -1;
				} else {
					forward_speed = 0;
				}
				break;
				
			case GLFW.GLFW_KEY_A:
				if(action != GLFW.GLFW_RELEASE) {
					right_speed = 1;
				} else {
					right_speed = 0;
				}
				break;
				
			case GLFW.GLFW_KEY_D:
				if(action != GLFW.GLFW_RELEASE) {
					right_speed = -1;
				} else {
					right_speed = 0;
				}
				break;
				
			case GLFW.GLFW_KEY_SPACE:
				if(action != GLFW.GLFW_RELEASE) {
					up_speed = 1;
				} else {
					up_speed = 0;
				}
				break;
				
			case GLFW.GLFW_KEY_LEFT_SHIFT:
				if(action != GLFW.GLFW_RELEASE) {
					up_speed = -1;
				} else {
					up_speed = 0;
				}
				break;
				
			case GLFW.GLFW_KEY_E:
				camera.rotate(0, 5, 0);
				break;
				
			case GLFW.GLFW_KEY_Q:
				camera.rotate(0, -5, 0);
				break;
		}
	}
	
	public void update() {
		float[] up = camera.up();
		float[] right = camera.right();
		float[] forward = camera.forward();
		
		camera.translate(
			up[0] * up_speed + right[0] * right_speed + forward[0] * forward_speed,
			up[1] * up_speed + right[1] * right_speed + forward[1] * forward_speed,
			up[2] * up_speed + right[2] * right_speed + forward[2] * forward_speed
		);
	}
	
	public Matrix getCamera() {
		return camera;
	}
}
