package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.MouseInfo;

import javax.swing.JFrame;

import gui.Button;

public class Game{
	public static JFrame window;
	
	static boolean start=false, controls=false, quit=false;
	public static int topInset, leftInset;

	static Font font;
	static FontMetrics met;
	static int fontHeight;
	public static boolean startMenuActive = true;
	
	public static boolean ready = false;
	
	public static Button[] startMenu = new Button[3];
	
	public static void render(Graphics g){
		//FONT METRICS
		font = new Font("Courier", Font.BOLD, 36);
		met = g.getFontMetrics(font);
		fontHeight = met.getHeight()/2;
		g.setFont(font);
		//END FONT METRICS
		
		if(startGame()){
			g.setColor(Color.red);
			g.drawString("GAME STARTED", 0 + leftInset, 0 + topInset + fontHeight);
		}else{
			drawMenu(g);
		}
	}
	
	public static void drawMenu(Graphics g){
		
		if(startMenuActive){
			String title = "MariCode_v4";
			//BACKGROUND
			g.setColor(Color.black);
			g.fillRect((window.getWidth()/2) - (met.stringWidth(title)/2)-2, (window.getHeight()/2) - (fontHeight*5), (met.stringWidth(title)), fontHeight*9);
			//TITLE
			g.setColor(new Color(37, 60, 227));
			g.drawString(title, (window.getWidth()/2) - (met.stringWidth(title)/2)+2, (window.getHeight()/2) + fontHeight - (fontHeight*5)+2);
			g.setColor(new Color(227, 205, 37));
			g.drawString(title, (window.getWidth()/2) - (met.stringWidth(title)/2), (window.getHeight()/2) + fontHeight - (fontHeight*5));
			//PLAY
			String t = "Play";
				int w = met.stringWidth(t);
				int x = (window.getWidth()/2) - (w/2);
				int y = (window.getHeight()/2)- (fontHeight/2) - (fontHeight*2);
			startMenu[0] = Button.CreateButton(x, y, t, g, font, Color.white);
			//END PLAY
			//CONTROLS
			t = "Controls";
				w = met.stringWidth(t);
				x = (window.getWidth()/2) - (w/2);
				y = (window.getHeight()/2)- (fontHeight/2);
			startMenu[1] = Button.CreateButton(x, y, t, g, font, Color.white);
			//END CONTROLS
			//QUIT
			t = "QUIT";
				w = met.stringWidth(t);
				x = (window.getWidth()/2) - (w/2);
				y = (window.getHeight()/2)- (fontHeight/2) + (fontHeight*2);
			startMenu[2] = Button.CreateButton(x, y, t, g, font, Color.white);
			//END QUIT		
			//GET MOUSE POS
			int mousex = MouseInfo.getPointerInfo().getLocation().x - window.getX();
			int mousey = MouseInfo.getPointerInfo().getLocation().y - window.getY();
			//FOR EACH BUTTON IN STARTMENU
			for(Button button : startMenu){
				if(mousex > button.x && mousex < button.x + button.width){
					if(mousey > button.y && mousey < button.y + button.height){
						//System.out.println("hoovering: "+button.text);
						button = Button.CreateButton(button.x, button.y, button.text, g, font, Color.red);
					}
				}
			}
		}
		//END
	}
	
	public static boolean startGame(){
		if(start){return true;}
		if(controls){return true;}
		if(quit){return true;}
		return false;
	}
}
