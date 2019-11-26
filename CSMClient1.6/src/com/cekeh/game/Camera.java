// Last edit: 07/02/2018 - TvB
package com.cekeh.game;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;

import com.cekeh.glfw.Client;
import com.cekeh.opengl.FBO;
import com.cekeh.opengl.Texture;
import com.cekeh.utility.Loader;
import com.cekeh.utility.Matrix;
import com.cekeh.utility.Transform;
import com.cekeh.utility.Vector2f;
import com.cekeh.utility.Vector3f;

/**
 * Cekeh's Camera object
 * Created 06/12/2018
 * @author Thomas vanBommel (TvB)
 */
public class Camera {

	public Transform transform;
	
	public float field_of_view;
	public float near;
	public float far;
	
	private float forward = 0;
	private float right = 0;
	
	private boolean shift = false;
	
	public final GLFWKeyCallbackI key_callback = new GLFWKeyCallbackI() {
		@Override
		public void invoke(long window, int key, int scancode, int action, int mods) {
			switch(key) {
				case GLFW.GLFW_KEY_LEFT_SHIFT:
				case GLFW.GLFW_KEY_RIGHT_SHIFT:
					if(action == GLFW.GLFW_RELEASE) {
						shift = false;
					}else {
						shift = true;
					}
					break;
			
				case GLFW.GLFW_KEY_W:
					if(action == GLFW.GLFW_RELEASE) {
						forward = 0;
					}else {
						forward = 1;
					}
					break;
					
				case GLFW.GLFW_KEY_A:
					if(action == GLFW.GLFW_RELEASE) {
						right = 0;
					}else {
						right = 1;
					}
					break;
					
				case GLFW.GLFW_KEY_S:
					if(action == GLFW.GLFW_RELEASE) {
						forward = 0;
					}else {
						forward = -1;
					}
					break;
					
				case GLFW.GLFW_KEY_D:
					if(action == GLFW.GLFW_RELEASE) {
						right = 0;
					}else {
						right = -1;
					}
					break;
					
				case GLFW.GLFW_KEY_P:
					if(action == GLFW.GLFW_RELEASE) {
						System.out.println("PPRESSED!");
						Client.getGame().scene.shader.setTextures(new Texture[] {
							testing0
						});
					}else {
						Client.getGame().scene.shader.setTextures(new Texture[] {
							testing1
						});
					}
					break;
			}
		}
	};
	
	/** 
	 * Create a default camera 
	 * position = (0, 0, 0)
	 * rotation = (0, 0, 0)
	 * scale = (1, 1, 1)
	 * field_of_view = (65)
	 * near = (0.5f)
	 * far = (500)
	 */
	public Camera() {
		this(new Transform(true), 90, 0.5f, 5000);
		transform.rotation.x = 0;
		transform.rotation.y = 0;
		transform.rotation.z = 0;
	}
	
	Texture testing0;
	Texture testing1;
	
	/**
	 * Create a new camera object with specified transformation
	 * @param transform Position, rotation, and scale of the camera, default (0, 0, 0), (0, 0, 0), (1, 1, 1)
	 * @param field_of_view Field of view for the camera, default (65)
	 * @param near The nearest the camera can see, default (0.5f)
	 * @param far The furthest the camera can see, default (500)
	 */
	public Camera(Transform transform, float field_of_view, float near, float far) {
		this.transform = transform;
		this.field_of_view = field_of_view;
		this.near = near;
		this.far = far;
		
		testing0 = new Texture("texture0", Loader.loadImage("com/cekeh/res/textures/heightmap0.png"));
		testing1 = new Texture("texture0", Loader.loadImage("com/cekeh/res/textures/normalmap0.png"));
	}
	
	public Vector2f angle = new Vector2f(0, 0);
	
	/** Update this camera object */
	public void update() {
		transform.update();
		
		Vector3f fwd = transform.forward();
		Vector3f rgt = transform.right();
		Vector3f up  = transform.up();
		
		transform.position.x += (fwd.x * (shift ? forward * 3 : forward) + rgt.x * right) * 0.9f;
		transform.position.y += (fwd.y * (shift ? forward * 3 : forward) + rgt.y * right) * 0.9f;
		transform.position.z += (fwd.z * (shift ? forward * 3 : forward) + rgt.z * right) * 0.9f;
		
		Vector2f mouse_movement = new Vector2f(Client.mouse_position.x - (Client.getWidth() / 2), Client.mouse_position.y - (Client.getHeight() / 2));
		
		angle.x += mouse_movement.y * 0.25f;
		angle.x = angle.x > 90 ? 90 : angle.x;
		angle.x = angle.x < -90 ? -90 : angle.x;
		
		angle.y += mouse_movement.x * 0.25f;
		angle.y = angle.y > 360 ? angle.y -= 360 : angle.y;
		angle.y = angle.y < 0 ? angle.y += 360 : angle.y;

//		System.out.println(String.format("fwd: %.1f, %.1f, %.1f", fwd.x, fwd.y, fwd.z));
//		System.out.println(String.format("rgt: %.1f, %.1f, %.1f", rgt.x, rgt.y, rgt.z));
//		System.out.println(String.format("up: %.1f, %.1f, %.1f", up.x, up.y, up.z));
		
		transform.rotation.y = angle.y;
		transform.rotation.x = angle.x;
		
		//System.out.println("Camera (x, y, z): " + transform.position.x + ", " + transform.position.y + ", " + transform.position.z);
	}
	
	/**
	 * Generate projection matrix for this camera object
	 * @return Projection matrix
	 */
	public Matrix projectionMatrix() {
		return projectionMatrix(field_of_view, near, far);
	}
	
	/**
	 * Generate a new projection matrix
	 * @param field_of_view Field of view for the camera
	 * @param near Nearest the camera can see
	 * @param far Furthest the camera can see
	 * @return Projection matrix
	 */
	public static Matrix projectionMatrix(float field_of_view, float near, float far) {
		float aspect_ratio = (float) Client.getWidth() / (float) Client.getHeight();
		float scale = 1f / (float) Math.tan(Math.toRadians(field_of_view) / 2f);
		
		return new Matrix(new float[] {
			scale, 	0, 						0, 								0,
			0,		scale * aspect_ratio,	0, 								0,
			0,		0,						-((far + near) / (far - near)),	-((2f * near * far) / (far - near)),
			0,		0,						-1,								0
		});
	}
}
