// Last edit: 05/31/2018 - TvB
package com.cekeh.lwjgl.opengl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL31;

import com.cekeh.client.Client;
import com.cekeh.utility.Matrix;

/**
 * Cekeh's Vertex Array Object (VAO)
 * Created 05/20/2018
 * @author Thomas vanBommel (TvB)
 */
public class VertexArrayObject extends Matrix {

	private final int handle;
	
	private final VertexBufferObject index_VBO;
	
	private ShaderProgram shader;
	
	private int render_count = 1;
	
	private final boolean is3D;
	
	/**
	 * Create a new Vertex Array Object (VAO)
	 * @param attributes The attributes of the VAO
	 * @param indices The indices (triangles) of the VAO
	 */
	public VertexArrayObject(VertexAttribute[] attributes, int[] indices, ShaderProgram shader, boolean is3D, int count) {
		this.shader = shader;
		this.is3D = is3D;
		this.render_count = count;
		
		handle = GL30.glGenVertexArrays();
		
		bind();
		
		for(VertexAttribute attribute : attributes) {
			attribute.bind();
		}
		
		index_VBO = new VertexBufferObject(indices, GL15.GL_ELEMENT_ARRAY_BUFFER);
		
		unbind();
	}
	
	/** Bind this VAO for use */
	public void bind() {
		GL30.glBindVertexArray(handle);
	}
	
	/** Unbind this Vertex Array Object (VAO) */
	public void unbind() {
		GL30.glBindVertexArray(0);
	}
	
	Matrix view_matrix = new Matrix().translate(0, 0, -2);
	
	/** Update the Vertex Array Object (VAO) */
	public void update() {
		shader.setUniformf("is3D", is3D ? 1 : 0);
		
		shader.setUniformMatrixfv("transformation_matrix", generate());
		//shader.setUniformMatrixfv("view_matrix", view_matrix.generate());
		shader.setUniformMatrixfv("view_matrix", Client.window.game.camera_controller.getCamera().generate());
		
		shader.setUniformf("aspect_ratio", (float) Client.window.getWidth() / (float) Client.window.getHeight());
	}
	
	/** Render the Vertex Array Object (VAO) */
	public void render() {
		shader.bind();
		
		bind();

		//GL11.glDrawElements(GL11.GL_TRIANGLES, index_VBO.getLength(), GL11.GL_UNSIGNED_INT, 0);
		GL31.glDrawElementsInstanced(GL11.GL_TRIANGLES, index_VBO.getLength(), GL11.GL_UNSIGNED_INT, 0, render_count);
		
		unbind();
		
		shader.unbind();
	}
	
	/** Clean up the memory resources */
	public void cleanUp() {		
		GL30.glDeleteVertexArrays(handle);
	}
	
	public void increaseRenderCount() {
		render_count += 100;
	}
	
	public void decreaseRenderCount() {
		render_count -= 100;
	}
}
