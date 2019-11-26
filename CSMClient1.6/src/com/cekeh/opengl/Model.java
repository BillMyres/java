// Last edit: 07/01/2018 - TvB
package com.cekeh.opengl;

import com.cekeh.utility.RawModel;
import com.cekeh.utility.Transform;

/**
 * Cekeh's Model class
 * Created 07/01/2018
 * @author Thomas vanBommel (TvB)
 */
public class Model extends VAO {

	public Model(RawModel model, ShaderProgram shader, Transform transform) {
		this(model.attributes, model.indices, shader, transform);
	}
	
	public Model(Attribute[] attributes, int[] indices, ShaderProgram shader, Transform transform) {
		super(attributes, indices, shader, transform);
	}

	@Override
	public void update() {
		super.update();
	}
}
