// 08/19/2018 - TvB
package com.cekeh.opengl;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferUShort;

import org.lwjgl.opengl.EXTABGR;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

/** 
 * Texture.java
 * 08/13/2018
 * @author Thomas
 */
public class Texture {

	private int handle;
	
	private String name;
	
	/**
	 * Create a new Texture
	 */
	public Texture(String name, BufferedImage image) {
		this(name);
		
		setImage(image);
	}
	
	/**
	 * Create a new Texture
	 */
	public Texture(String name) {
		this.name = name;
		
		handle = GL11.glGenTextures();
		
		System.out.println("Texture handle: " + handle);
	}
	
	/* Private methods */
	
	
	
	/* Public methods */
	
	public void setImage(BufferedImage image) {
		DataBuffer buffer = image.getRaster().getDataBuffer();
		
		int type = 0;
		int format = 0;
		long location = 0;
		
		switch(buffer.getDataType()) {
			case DataBuffer.TYPE_BYTE:
				byte[] bdata = ((DataBufferByte) buffer).getData();

				System.out.println("Texture: TYPE_BYTE\n  Length: " + bdata.length + "\n  Location: " + location);
				
				System.out.println(bdata[0] + ", " + bdata[1] + ", " + bdata[2] + ", " + bdata[3]);
				
				type = GL11.GL_UNSIGNED_BYTE;
				format = EXTABGR.GL_ABGR_EXT;
				location = MemoryUtil.memAddressSafe(MemoryUtil.memAlloc(bdata.length).put(bdata));
				break;
				
			case DataBuffer.TYPE_USHORT:
				short[] sdata = ((DataBufferUShort) buffer).getData();
				
				System.out.println("Texture: TYPE_USHORT\n  Length: " + sdata.length);
				
				type = GL11.GL_UNSIGNED_SHORT;
				format = GL11.GL_RGBA;
				location = MemoryUtil.memAddressSafe(MemoryUtil.memAllocShort(sdata.length).put(sdata));
				break;
		}
		
		bind();
		
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, image.getWidth(), image.getHeight(), 0, format, type, location);
		
		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
	
		unbind();
	}
	
	/**
	 * Bind this texture to the currently active texture slot
	 */
	public void bind() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, handle);
	}
	
	/**
	 * Bind this texture to a texture slot
	 * @param slot OpenGL active texture slot
	 */
	public void bind(int slot) {
		GL13.glActiveTexture(GL13.GL_TEXTURE0 + slot);
		
		bind();
	}
	
	/**
	 * Unbind this texture from use
	 */
	public void unbind() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}
	
	/**
	 * Teraminate this texture, and clean up resources
	 */
	public void terminate() {
		GL11.glDeleteTextures(handle);
	}
	
	/* Getters / setters */
	
	/**
	 * Get the name of this texture
	 * @return Name in a string
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the handle for this texture
	 * @return OpenGL handle for this texture
	 */
	public int getHandle() {
		return handle;
	}
}
