// Last edit: 05/23/2018 - TvB
package com.cekeh.utility;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferDouble;
import java.awt.image.DataBufferFloat;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferShort;
import java.awt.image.DataBufferUShort;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.opengl.EXTABGR;
import org.lwjgl.opengl.EXTBGRA;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.system.MemoryUtil;

/**
 * Cekeh's Image object
 * Created 05/23/2018
 * @author Thomas vanBommel (TvB)
 */
public class Image {

	private final int width;
	private final int height;
	
	private final long memory_location;
	
	private final int buffer_type;
	
	private int internal_format;
	private int format;
	
	/**
	 * Create a new Image object
	 * @param path File path to the image
	 * @throws Exception Unable to load image
	 */
	public Image(String path) throws Exception {
		BufferedImage image = ResourceLoader.loadImage(path);
		
		width = image.getWidth();
		height = image.getHeight();
		
		DataBuffer buffer = image.getRaster().getDataBuffer();
		
		internal_format = GL11.GL_RGBA;
		format = GL11.GL_RGBA;
		
		switch(buffer.getDataType()) {
			case DataBuffer.TYPE_BYTE:
				buffer_type = GL11.GL_UNSIGNED_BYTE;
				
				System.out.println("[byte]");
				
				byte[] byte_data = ((DataBufferByte) buffer).getData();
				
				ByteBuffer byte_buffer = MemoryUtil.memAlloc(byte_data.length).put(byte_data);
				byte_buffer.flip();
				
				memory_location = MemoryUtil.memAddressSafe(byte_buffer);
				
				//internal_format = GL11.GL_RGB;
				//format = GL12.GL_BGR;
				//format = GL11.GL_RED;
				format = EXTABGR.GL_ABGR_EXT;
				break;
				
			case DataBuffer.TYPE_DOUBLE:
				buffer_type = GL11.GL_DOUBLE;
				
				double[] double_data = ((DataBufferDouble) buffer).getData();
				
				DoubleBuffer double_buffer = MemoryUtil.memAllocDouble(double_data.length).put(double_data);
				double_buffer.flip();
				
				memory_location = MemoryUtil.memAddressSafe(double_buffer);	
				break;
						
			case DataBuffer.TYPE_FLOAT:
				buffer_type = GL11.GL_FLOAT;
				
				float[] float_data = ((DataBufferFloat) buffer).getData();
				
				FloatBuffer float_buffer = MemoryUtil.memAllocFloat(float_data.length).put(float_data);
				float_buffer.flip();
				
				memory_location = MemoryUtil.memAddressSafe(float_buffer);
				break;
				
			case DataBuffer.TYPE_INT:
				buffer_type = GL11.GL_UNSIGNED_INT;
				
				int[] int_data = ((DataBufferInt) buffer).getData();
				
				IntBuffer int_buffer = MemoryUtil.memAllocInt(int_data.length).put(int_data);
				int_buffer.flip();
				
				memory_location = MemoryUtil.memAddressSafe(int_buffer);
				break;
				
			case DataBuffer.TYPE_SHORT:
				buffer_type = GL11.GL_SHORT;
				
				short[] short_data = ((DataBufferShort) buffer).getData();
				
				ShortBuffer short_buffer = MemoryUtil.memAllocShort(short_data.length).put(short_data);
				short_buffer.flip();
				
				memory_location = MemoryUtil.memAddressSafe(short_buffer);
				break;
				
			case DataBuffer.TYPE_USHORT:
				buffer_type = GL11.GL_UNSIGNED_SHORT;
				
				System.out.println("[ushort]");
				
				short[] ushort_data = ((DataBufferUShort) buffer).getData();
				
				ShortBuffer ushort_buffer = MemoryUtil.memAllocShort(ushort_data.length).put(ushort_data);
				ushort_buffer.flip();
				
				memory_location = MemoryUtil.memAddressSafe(ushort_buffer);
				break;
				
			default:
				throw new Exception("Unsupported texture format: [" + buffer.getDataType() + "]");
		}
	}
	
	//GET / SET
	
	/** Get the width of the image */
	public int getWidth() {
		return width;
	}
	
	/** Get the height of the image */
	public int getHeight() {
		return height;
	}
	
	/** Get the memory location of the image */
	public long getMemoryLocation() {
		return memory_location;
	}
	
	/** Get the image buffer type */
	public int getBufferType() {
		return buffer_type;
	}
	
	/** Get the image internal format */
	public int getInternalFormat() {
		return internal_format;
	}
	
	/** Get the image format */
	public int getFormat() {
		return format;
	}
}
