// 08/18/2018 - TvB
package com.cekeh.models;

import com.cekeh.opengl.Attribute;
import com.cekeh.opengl.Model;
import com.cekeh.opengl.ShaderProgram;
import com.cekeh.utility.Vector4f;

/** 
 * Box.java
 * 08/11/2018
 * @author Thomas
 */
public class Box extends Model {
	
	/**
	 * Create a new box on the screen
	 * @param position Position of the box [ x, y, width, height ]
	 * @param shader ShaderProgram used to display the box (ie. ColorShader, ImageShader, etc...)
	 */
	public Box(Vector4f position, ShaderProgram shader) {
		super(new Attribute[] {
			new Attribute(0, false, "vertex", new float[] {
				position.x, position.y,
				position.x + position.z, position.y,
				position.x + position.z, position.y - position.w,
				position.x, position.y - position.w
			}, 2, 0, 0, false),
			new Attribute(1, false, "texcoord", new float[] {
					0, 0,
					1, 0,
					1, 1,
					0, 1
				}, 2, 0, 0, false),
			new Attribute(2, true, "index", new int[] {
				0, 2, 1,
				2, 0, 3
			}, 2, 0, 0, false)
		}, shader);
	}
}
