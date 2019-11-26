// Last edit: 08/03/2018 - TvB
package com.cekeh.utility;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.cekeh.game.Scene;
import com.cekeh.opengl.Attribute;

/**
 * Cekeh's Loader class
 * Created 06/06/2018
 * @author Thomas vanBommel (TvB)
 */
public class Loader {

	/**
	 * Load a string from a text file
	 * @param path File path, (ie. "com/cekeh/res/shaders/test.txt")
	 * @return File contents in a string object, "null" if file is not found
	 */
	public static String loadString(String path) {
		String data = "";
		
		InputStream stream = Loader.class.getClassLoader().getResourceAsStream(path);
		
		if(stream != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			
			String line;
			try {
				while((line = reader.readLine()) != null) {
					data += line + "\n";
				}
				
				reader.close();
			} catch (Exception e) {
				data = "null";
			}
		}else {
			data = "null";
		}
		
		return data;
	}
	
	/**
	 * Load a buffered image from an image file
	 * @param path Path to the image file
	 * @return File contents in a buffered image object, null if file is not found or corrupted
	 */
	public static BufferedImage loadImage(String path) {
		InputStream stream = Loader.class.getClassLoader().getResourceAsStream(path);
		
		try {
			return ImageIO.read(stream);
		} catch (Exception e) {
			System.out.println("Reading error: [" + path + "]");
		}
		
		return null;
	}
	
	/**
	 * Load RawModel from a file
	 * @param path Path to the model file
	 * @return RawModel object
	 */
	public static RawModel loadModel(String path) {
		String[] data = loadString(path).split("\\(|\\)");
		
		String vertex_string = "null";
		String index_string = "null";
		String normal_string = "null";//
		String texcoord_string = "null";
		
		for(int i = 0; i < data.length; i++) {
			switch(data[i].replace(",", "")) {
				case "VERTICES":
					vertex_string = data[i + 1];
					break;
				case "INDICES":
					index_string = data[i + 1];
					break;
				case "NORMALS":
					normal_string = data[i + 1];
					break;
				case "TEXCOORDS":
					texcoord_string = data[i + 1];
					break;
			}
		}
		
		//System.out.println(vertex_string);
		
		String[] vertex_data = vertex_string.split(",");
		String[] index_data = index_string.split(",");
		String[] normal_data = normal_string.split(",");
		String[] texcoord_data = texcoord_string.split(",");
		
		float[] vertices = new float[vertex_data.length];
		int[] indices = new int[index_data.length];
		float[] normals = new float[normal_data.length];
		float[] texcoords = new float[texcoord_data.length];
		
		for(int i = 0; i < indices.length; i++) {
			indices[i] = Integer.parseInt(index_data[i]);
			
			for(int j = 0; j < 2; j++) {
				int texcoord_index = (2 * i) + j;
				
				texcoords[texcoord_index] = Float.parseFloat(texcoord_data[texcoord_index]);
			}
			
			for(int j = 0; j < 3; j++) {
				int vertice_index = (3 * i) + j;
				
				vertices[vertice_index] = Float.parseFloat(vertex_data[vertice_index]);
				normals[vertice_index] = Float.parseFloat(normal_data[vertice_index]);
			}
		}
		
		return new RawModel(new Attribute[] { new Attribute("position", 0, 3, 0, 0, vertices), new Attribute("normal", 1, 3, 0, 0, normals), new Attribute("texcoord", 2, 2, 0, 0, texcoords) }, indices);
	}
}
