// Last edit: 08/01/2018 - TvB
package com.cekeh.models;

import com.cekeh.opengl.Model;
import com.cekeh.opengl.Shader;
import com.cekeh.opengl.ShaderProgram;
import com.cekeh.utility.Loader;
import com.cekeh.utility.RawModel;
import com.cekeh.utility.Transform;
import com.cekeh.utility.Vector3f;

/**
 * Cekeh's Light object
 * Created 07/15/2018
 * @author Thomas vanBommel (TvB)
 */
public class Light extends Model {

	private static RawModel model = Loader.loadModel("com/cekeh/models/Cube.model"); 
	
	private float count = 0f;
	private float speed = 0.01f;
	
	public Light() {
		super(model, new ShaderProgram(new Shader[] { new Shader("com/cekeh/res/shaders/white.vs", Shader.VERTEX_SHADER), new Shader("com/cekeh/res/shaders/white.fs", Shader.FRAGMENT_SHADER) }, model.attributes, null), new Transform());
	
		transform.position = new Vector3f(-10, 15, 10);
		transform.scale = new Vector3f(0.25f, 0.25f, 0.25f);
	}
	
	@Override
	public void update() {
		super.update();
		
		count += speed;
		
		transform.position.x = 25 * (float) Math.cos(count);
		transform.position.z = 25 * (float) -Math.sin(count);
	}
}
