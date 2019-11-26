package actions;

import java.awt.Graphics;
import java.util.ArrayList;

import gui.Sprite;
import player.Player;

public class Idle {
	public static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	
	static int spriteLength =7;
	public static int currentFrame =1;
	static int framesPassed =0, cycleRate =8;
	
	
	public static void checkIsIdle(ArrayList<Sprite> sprites, Graphics g){
		//add 1 per frame
		if(Player.velx == 0.0f && Player.vely == 0.0f && !Player.isWalking){
			Player.isIdle = true;
		}
		//set sprite
		Idle.sprites = sprites;
		
		if(Player.isIdle && !Player.isJumping && !sprites.isEmpty()){
			///////////////////////////
			framesPassed++;
			if(framesPassed % cycleRate == 0 && !Player.isRunning && !Player.isWalking){//once every 6 frames
		
				checkFrameBounds();
			}
			
			cycleSprite(g);
			}
			
			///////////////////////////
		
	}
	
	public static void checkFrameBounds(){
		if(currentFrame < spriteLength){
			currentFrame++;
		}
		if(currentFrame >= spriteLength){
			currentFrame =1;
		}
		
	}
	
	public static void cycleSprite(Graphics g){
		int showFrame =0;
		//for each sprite
		for(Sprite s : sprites){
			showFrame++;
			if(showFrame == currentFrame){
				s.Active = true;//set sprite Active
			}else{
				s.Active = false;
			}
			//draw frame if active
			if(s.Active){
				if(Player.facingRight){
					g.drawImage(s.b, (int)Player.x, (int)Player.y, Player.sizeX*4, Player.sizeY*4, null);
				}else{
					
					g.drawImage(s.b, (int)Player.x + ((int)Player.sizeX*4), (int)Player.y, -Player.sizeX*4, Player.sizeY*4, null);

					
				}
			}
		}
	}
}
