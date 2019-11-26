// Last edit: 05/31/2018 - TvB
package com.cekeh.scenes;

import com.cekeh.lwjgl.opengl.Scene;
import com.cekeh.lwjgl.opengl.VertexArrayObject;
import com.cekeh.models.GUIModel;
import com.cekeh.utility.ResourceLoader;

/**
 * Cekeh's Test Scene
 * Created 05/20/2018
 * @author Thomas vanBommel (TvB)
 */
public class TestScene extends Scene {
	
	private VertexArrayObject[] models;
	
	/**
	 * Create a new test scene
	 * @throws Exception ShaderProgram error
	 */
	public TestScene() throws Exception {
		models = new VertexArrayObject[] {
			ResourceLoader.loadModel("com/cekeh/models/SlotMachine.model"),
			new GUIModel(-1, -1, 0.5f, 0.25f)
			//ResourceLoader.loadModel("com/cekeh/models/Chest.model")
		};
		
		models[0].rotate(0, 45, 0);
		//model = ResourceLoader.loadModel("com/cekeh/models/SlotMachine.model");
		//model.translate(-150, 150, -200);
		models[0].translate(0, 0, -20);
		
		for(VertexArrayObject model : models) {
			super.addVAO(model);
		}
	}
	
	/** Update the scene */
	@Override
	public void update() {
		super.update();
		
		models[0].rotate(0, 0.75f, 0);
		
		//float[] forward = models[0].forward();
		//models[0].translate(forward[0] * 0.1f, forward[1] * 0.1f, forward[2] * 0.1f);
		
		//float[] right = models[0].up();
		//models[0].translate(right[0] * 0.1f, right[1] * 0.1f, right[2] * 0.1f);
	}

	public void keyEvent(int key, int action, int mods) {
//		if(action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT) {
//			if(key == GLFW.GLFW_KEY_RIGHT) {
//				model.increaseRenderCount();
//			}else if(key == GLFW.GLFW_KEY_LEFT) {
//				model.decreaseRenderCount();
//			}
//		}
	}
}
