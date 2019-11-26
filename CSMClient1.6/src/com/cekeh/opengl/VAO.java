// Last edit: 07/29/2018 - TvB
package com.cekeh.opengl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import com.cekeh.glfw.Client;
import com.cekeh.utility.Matrix;
import com.cekeh.utility.Transform;
import com.cekeh.utility.Vector3f;

/**
 * Cekeh's VAO object (VAO)
 * Created 06/04/2018
 * @author Thomas vanBommel (TvB)
 */
public abstract class VAO {

	private int handle;
	
	private final Attribute[] attributes;
	private final int[] indices;
	
	public ShaderProgram shader;
	public Transform transform;
	
	/** Create a new VAO object */
	public VAO(Attribute[] attributes, int[] indices, ShaderProgram shader, Transform transform) {
		this.attributes = attributes;
		this.indices = indices;
		this.shader = shader;
		this.transform = transform;
		
		handle = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(handle);
		
		for(Attribute attribute : attributes) {
			attribute.bind();
		}
		
		int index = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, index);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);
		
		GL30.glBindVertexArray(0);
	}
	
	/** Update the VAO */
	public void update() {
		transform.update();
		
		if(shader != null) {
			Matrix transformation_matrix = Matrix.objectMatrix(transform);
			Matrix view_matrix = Client.getGame().camera.transform.matrix;
			Matrix projection_matrix = Client.getGame().camera.projectionMatrix();
			
			shader.setUniformMatrix("transformation_matrix", transformation_matrix);
			shader.setUniformMatrix("view_matrix", view_matrix);
			shader.setUniformMatrix("projection_matrix", projection_matrix);
		
			Vector3f light_position = Client.getGame().scene.getLight().transform.position;
			shader.setUniformfv("light_position", new float[] { light_position.x, light_position.y, light_position.z });
			
			Vector3f camera_position = Client.getGame().camera.transform.position;
			shader.setUniformfv("camera_position", new float[] { camera_position.x, camera_position.y, camera_position.z });
		}
	}
	
	/** Render the VAO */
	public void render() {
		if(shader != null) { shader.bind(); }
		
		GL30.glBindVertexArray(handle);
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, indices.length, GL11.GL_UNSIGNED_INT, 0);

		GL30.glBindVertexArray(0);
		
		if(shader != null) { shader.unbind(); }
	}
	
	/** Clean up memory resources */
	public void cleanUp() {
		for(Attribute attribute : attributes) {
			attribute.cleanUp();
		}
		
		if(shader != null) { shader.cleanUp(); }
		
		GL30.glDeleteVertexArrays(handle);
	}
}
