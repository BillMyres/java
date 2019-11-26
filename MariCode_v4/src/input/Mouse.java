package input;

import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import game.Game;
import gui.Button;
import player.Player;
import world.WorldChoice;

public class Mouse implements MouseListener{

	public static JFrame frame;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int mousex = e.getX();
		int mousey = e.getY();
		//System.out.println(mousex);
		
		if(Game.startMenuActive){
			for(Button button : Game.startMenu){
				if(mousex > button.x && mousex < button.x + button.width){
					if(mousey > button.y && mousey < button.y + button.height){
						String t = button.text;
						switch(t){
						case "Play": //System.out.println("PLAY!");
									WorldChoice.testWorld = true;
									Game.startMenuActive = false;
									Player.x -= 64;
							break;
						case "Controls":
							break;
						case "Quit":
							break;
						}
					}
				}
			}
		}
		if(Game.ready){
			//Player.y -= 256;
			Player.x = MouseInfo.getPointerInfo().getLocation().x - Game.window.getX() - 64;
			Player.y = MouseInfo.getPointerInfo().getLocation().y - Game.window.getY() - 64;
			Player.isJumping = true;
			//Player.vely -= 1;
			Player.isGrounded = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

}
