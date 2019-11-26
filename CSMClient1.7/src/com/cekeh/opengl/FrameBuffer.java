// 08/13/2018 - TvB
package com.cekeh.opengl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL32;

/** 
 * FrameBuffer.java
 * 08/13/2018
 * @author Thomas
 */
public class FrameBuffer {

	private int handle;
	
	private int texture;
	private int render_buffer;
	
	public FrameBuffer() {
		handle = GL30.glGenFramebuffers();
		
		bind();
		
		texture = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, 512, 512, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, 0);

		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
			
		render_buffer = GL30.glGenRenderbuffers();
		GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, render_buffer);
		
		GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, GL11.GL_DEPTH_COMPONENT, 512, 512);
		GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, texture, 0);
		
		GL32.glFramebufferTexture(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, texture, 0);
		
		GL20.glDrawBuffers(GL30.GL_COLOR_ATTACHMENT0);
		
		unbind();
	}
	
	/* Public methods */
	
	/**
	 * Bind this frame buffer for use
	 */
	public void bind() {
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, handle);
	}
	
	/**
	 * Unbind this frame buffer from use
	 */
	public void unbind() {
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
	}
}
