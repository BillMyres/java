// 08/18/2018 - TvB
package com.cekeh.utility;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

/** 
 * Loader.java
 * 08/11/2018
 * @author Thomas
 */
public class Loader {

	/**
	 * Load text from a file path
	 * @param path Location of the file
	 * @return String of data from the file at the file location
	 */
	public static String loadText(String path) {
		String text = "";
		
		InputStream stream = Loader.class.getClassLoader().getResourceAsStream(path);
		
		if(stream != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			
			String line;
			try {
				while((line = reader.readLine()) != null) {
					text += line + "\n";
				}
				
				reader.close();
			}catch(Exception e) {
				return "null";
			}
		}else {
			return "null";
		}
		
		return text;
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
	
	
}
