package actions;

import java.awt.Graphics;
import java.util.ArrayList;

import gui.Sprite;
import player.Player;

public class Walk {
	public static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	
	static int spriteLength =16;
	public static int currentFrame =1;
	static int framesPassed =0, cycleRate =4;
	

	
	
	public static void checkIsWalking(ArrayList<Sprite> sprites, Graphics g, double delta){
		
		//set sprite
		Walk.sprites = sprites;
		
		if(Player.isWalking && !Player.isJumping && !sprites.isEmpty()){
			///////////////////////////
			framesPassed++;
			if(framesPassed % cycleRate == 0){//once every 6 frames
				
				if(!Player.isRunning){
					checkFrameBounds();
				}
				
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
				}
				if(!Player.facingRight){
					g.drawImage(s.b, (int)Player.x + ((int)Player.sizeX*4), (int)Player.y, -Player.sizeX*4, Player.sizeY*4, null);
				}
			}
		}
	}
}
