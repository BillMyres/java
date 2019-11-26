// Last edit: 05/23/2018 - TvB
package com.cekeh.lwjgl.opengl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL30;

import com.cekeh.utility.Image;

/**
 * Cekeh's Texture object
 * Created 05/20/2018
 * @author Thomas vanBommel (TvB)
 */
public class Texture {
	
	private final Image image;
	
	private final int index;

	private final int handle;
	
	public Texture(String path, int index) throws Exception {
		image = new Image(path);
		this.index = index;
		
		handle = GL11.glGenTextures();
		
		bind();
		
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, image.getInternalFormat(), image.getWidth(), image.getHeight(), 0, image.getFormat(), image.getBufferType(), image.getMemoryLocation());
		
		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
	}
	
	/** Bind this texture for use */
	public void bind() {
		//GL13.glActiveTexture(index);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, handle);
	}
}
