// Last edit: 08/03/2018 - TvB
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
 * Cekeh's Texture object
 * Created 06/11/2018
 * @author Thomas vanBommel (TvB)
 */
public class Texture {
	
	public final String name;
	
	public final int handle;
	
	private final int width;
	private final int height;

	private final int format;
	
	public Texture(String name, int handle, int width, int height, int format) {
		this.name = name;
		this.handle = handle;
		this.width = width;
		this.height = height;
		this.format = format;
	}
	
	/**
	 * Create a new texture object
	 * @param image Image to create the texture from
	 */
	public Texture(String name, BufferedImage image) {
		this.name = name;
		
		handle = GL11.glGenTextures();
		
		width = image.getWidth();
		height = image.getHeight();
		
		DataBuffer buffer = image.getRaster().getDataBuffer();
		int buffer_type = 0;
		
		long memory_location = 0;
		
		switch(buffer.getDataType()) {
			case DataBuffer.TYPE_BYTE:
				byte[] byte_data = ((DataBufferByte) buffer).getData();
								
				format = EXTABGR.GL_ABGR_EXT;
				buffer_type = GL11.GL_UNSIGNED_BYTE;
				memory_location = MemoryUtil.memAddressSafe(MemoryUtil.memAlloc(byte_data.length).put(byte_data));
				break;
					
			case DataBuffer.TYPE_USHORT:
				short[] ushort_data = ((DataBufferUShort) buffer).getData();
				
				format = GL11.GL_RGBA;
				buffer_type = GL11.GL_UNSIGNED_BYTE;
				memory_location = MemoryUtil.memAddressSafe(MemoryUtil.memAllocShort(ushort_data.length).put(ushort_data));
				break;
				
			default:
				System.out.println("Unsupported image type");
				
				format = 0;
				memory_location = 0;
		}
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, handle);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, format, buffer_type, memory_location);
		
		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
	
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}
	
	/**
	 * Bind this texture to a texture slot
	 * @param index Texture slot to bind texture to
	 */
	public void bind(int index) {	
		GL13.glActiveTexture(GL13.GL_TEXTURE0 + index);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, handle);
	}
	
	public void unbind() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}
	
	/** Clean up memory resources */
	public void cleanUp() {
		GL11.glDeleteTextures(handle);
	}
}
