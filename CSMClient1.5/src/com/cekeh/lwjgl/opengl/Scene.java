// Last edit: 05/20/2018 - TvB
package com.cekeh.lwjgl.opengl;

import java.util.ArrayList;

/**
 * Cekeh's Scene object class
 * Created 05/20/2018
 * @author Thomas vanBommel (TvB)
 */
public abstract class Scene {

	private ArrayList<VertexArrayObject> vaos = new ArrayList<VertexArrayObject>();
	
	protected Scene() {}
	
	/** Add a Vertex Array Object (VAO) to the scene */
	public void addVAO(VertexArrayObject VAO) {
		vaos.add(VAO);
	}
	
	/** Update the scene */
	public void update() {
		for(VertexArrayObject VAO: vaos) {
			VAO.update();
		}
	}
	
	/** Render the scene */
	public void render() {
		for(VertexArrayObject VAO: vaos) {
			VAO.render();
		}
	}
	
	/** Clean up memory resources */
	public void cleanUp() {
		for(VertexArrayObject VAO: vaos) {
			VAO.cleanUp();
		}
	}
	
	public abstract void keyEvent(int key, int action, int mods);
}
