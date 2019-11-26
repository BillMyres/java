// Last edit: 08/01/2018 - TvB
package com.cekeh.game;

import com.cekeh.models.Light;
import com.cekeh.opengl.FBO;
import com.cekeh.opengl.Model;
import com.cekeh.opengl.Shader;
import com.cekeh.opengl.ShaderProgram;
import com.cekeh.opengl.Texture;
import com.cekeh.res.shaders.ColorShader;
import com.cekeh.utility.Loader;
import com.cekeh.utility.RawModel;
import com.cekeh.utility.Transform;
import com.cekeh.utility.Vector3f;
import com.cekeh.utility.Vector4f;

/**
 * Cekeh's Test Scene
 * Created 07/28/2018
 * @author Thomas vanBommel (TvB)
 */
public class TestScene extends Scene{

	private Model[] models;
	
	/**
	 * Create a new test scene
	 */
	public TestScene() {
		RawModel raw_terrain = Loader.loadModel("com/cekeh/models/FlatTerrain.model");
		RawModel raw_cube = Loader.loadModel("com/cekeh/models/Cube.model");
		
		models = new Model[] {
			new Light(),
			new Model(
				raw_terrain,
				new ColorShader(new Vector4f(0, 1, 0, 1), raw_terrain.attributes),
				new Transform()
			),
			new Model(
				raw_cube,
				new ColorShader(new Vector4f(1, 0, 0, 1), raw_cube.attributes),
				new Transform()
			)
		};
		
		models[1].transform.scale = new Vector3f(25, 25, 25);
	}
	
	/**
	 * Update the test scene
	 */
	public void update() {
		super.update();
	}

	/**
	 * Return models for updating, rendering, and cleanup
	 */
	protected Model[] getModels() {
		return models;
	}
	
	/**
	 * Get the light for the scene
	 * @return Light object
	 */
	public Model getLight() {
		return models[0];
	}
}
