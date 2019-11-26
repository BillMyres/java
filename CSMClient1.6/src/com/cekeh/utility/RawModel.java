// Last edit: 07/01/2018 - TvB
package com.cekeh.utility;

import com.cekeh.opengl.Attribute;

/**
 * Cekeh's RawModel object
 * Created 07/01/2018
 * @author Thomas vanBommel (TvB)
 */
public class RawModel {

	public final Attribute[] attributes;
	public final int[] indices;
	
	/**
	 * Create a new RawModel
	 * @param attributes Attributes for the mdoel
	 * @param indices Indices of the model (triangles)
	 */
	public RawModel(Attribute[] attributes, int[] indices) {
		this.attributes = attributes;
		this.indices = indices;
	}
}
