package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Button extends Rectangle{
	public int x, y, width, height;
	public String text;
	
	
	public Button(int x, int y, int width, int height, String text){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
	}
	
	public static Button CreateButton(int x1, int y1, String text1, Graphics g1, Font font1, Color c){
		FontMetrics f = g1.getFontMetrics(font1);
		
		//Button.x = x;
		//Button.y = y;
		//Button.text = text;
		//Button.width = f.stringWidth(text);
		//Button.height = f.getHeight()/2;
		int width1 = f.stringWidth(text1);
		int height1= f.getHeight()/2;
		
		
		g1.setColor(Color.black);
		g1.fillRect(x1, y1, f.stringWidth(text1), height1);
		g1.setColor(c);
		g1.drawString(text1, x1, y1+height1);
		
		return(new Button(x1, y1, width1, height1, text1));
	}
}
