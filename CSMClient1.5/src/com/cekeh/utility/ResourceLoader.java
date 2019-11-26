// Last edit: 05/20/2018 - TvB
package com.cekeh.utility;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.cekeh.lwjgl.opengl.VertexAttribute;
import com.cekeh.models.ModelTest;

/**
 * Cekeh's Resource Loader class
 * Created 05/20/2018
 * @author Thomas vanBommel (TvB)
 */
public class ResourceLoader {

	/**
	 * Load plain text into a String object
	 * @param path The file path to the text file
	 * @return	The content of the file in a String object
	 * @throws Exception I/O Exception (incorrect file path)
	 */
	public static String loadPlainText(String path) throws Exception {
		String data = "";
		
		InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
		InputStreamReader reader = new InputStreamReader(stream);
		
//		int character;
//		
//		while((character = reader.read()) != -1) {
//			data += String.valueOf((char) character);
//		}
		
		BufferedReader br = new BufferedReader(reader);
		String line;
		
		while((line = br.readLine()) != null) {
			data += line + "\n";
		}
		
		reader.close();
		br.close();
		
		return data;
	}
	
	/**
	 * Load an image to a BufferdImage object
	 * @param path The file path to the image file
	 * @return The BufferedImage object containing information on the image file
	 * @throws Exception Incorrect file path
	 */
	public static BufferedImage loadImage(String path) throws Exception {
		InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
		
		return ImageIO.read(stream);
	}
	
	public static ModelTest loadModel(String path) throws Exception {
		long time = System.nanoTime();
		String[] data = loadPlainText(path).split("\\(|\\)");
		
		System.out.println("Time: " + (float) ((System.nanoTime() - time) / (float) 1000000000) + "s");
		
		String name = data[0];
		String vertex_string = "null";
		String index_string = "null";
		String texcoord_string = "null";
		
		for(int i = 0; i < data.length; i++) {
			switch(data[i].replace(",", "")) {
			case "VERTICES":
				vertex_string = data[i + 1];
				break;
			case "INDICES":
				index_string = data[i + 1];
				break;
			case "TEXCOORDS":
				texcoord_string = data[i + 1];
				break;
			}			
		}
		
		String[] vertex_data = vertex_string.split(",");
		String[] index_data = index_string.split(",");
		String[] texcoord_data = texcoord_string.split(",");
		
		float[] vertices = new float[vertex_data.length];
		int[] indices = new int[index_data.length];
		float[] texcoords = new float[texcoord_data.length];
		
		System.out.println(vertices.length);
		System.out.println(indices.length);
		System.out.println(texcoords.length);
		
		for(int i = 0; i < indices.length; i++) {
			indices[i] = Integer.parseInt(index_data[i]);
			
			for(int j = 0; j < 2; j++) {
				int texcoord_index = (2 * i) + j;
				
				texcoords[texcoord_index] = Float.parseFloat(texcoord_data[texcoord_index]);
			}
			
			for(int j = 0; j < 3; j++) {
				int vertice_index = (3 * i) + j;
				
				vertices[vertice_index] = Float.parseFloat(vertex_data[vertice_index]);
			}
		}
		
//		for(int i = 0; i < vertex_data.length; i++) {
//			vertices[i] = Float.parseFloat(vertex_data[i]);
//		}
//		
//		for(int i = 0; i < index_data.length; i++) {
//			indices[i] = Integer.parseInt(index_data[i]);
//		}
//		
//		for(int i = 0; i < texcoord_data.length; i++) {
//			texcoords[i] = Float.parseFloat(texcoord_data[i]);
//		}
		
		return new ModelTest(
				new VertexAttribute[] { new VertexAttribute(0, 3, 0, 0, vertices), new VertexAttribute(1, 2, 0, 0, texcoords) },
				indices
		);
	}
}
